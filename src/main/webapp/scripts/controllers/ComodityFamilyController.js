app.controller("ComodityFamilyController", ['$scope', '$uibModal', 'comodityFamilies', 'comodities', 'ComodityService', '$location',
	function($scope, $uibModal, comodityFamilies, comodites, ComodityService, $location){
		$scope.comodityFamilies = comodityFamilies || [];
		$scope.allComodites = comodites;
		
		$scope.editComodityFamily = function(index){
			var modalInstance = $uibModal.open({
		    	animation: true,
		    	templateUrl: 'comodityFamily-modal.html',
		      	controller: 'ComodityFamilyModalCtrl',
		      	resolve: {
		      		option: function(){
		      			return "EDIT";
		      		},
		      		comodityFamily : function(){
		      			return $scope.comodityFamilies[index];
		      		},
		      		comodities: function(){
		      			return $scope.allComodites;
		      		}
		      	}
		    });

		    modalInstance.result.then(function (comodityFamily) {
		    	if(comodityFamily && comodityFamily.comodityFamilyName){
		    		ComodityService.updateComodityFamily($scope.comodityFamilies[index]).then(function(response){
						$location.path("comodities");
					});
		    	}
		    });
		}
		
		$scope.getComodities = function(comodities){
			if(comodities && comodities.length > 0){
				return comodities.split(",");
			}
			return [];
		}
		
		$scope.getExpiryDates = function(expiryDates){
			if(expiryDates && expiryDates.length > 0){
				return expiryDates.split(",");
			}
			return [];
		}
		
		$scope.deleteComodityFamily = function(index){
			console.log("Deleting comodityFamily: ", index);
			ComodityFamilyService.deleteComodityFamily($scope.comodities[index]).then(function(response){
				$location.path("comodities");
			});
		}
		
		$scope.addComodityFamily = function(){
			var modalInstance = $uibModal.open({
		    	animation: true,
		    	templateUrl: 'comodityFamily-modal.html',
		      	controller: 'ComodityFamilyModalCtrl',
		      	resolve: {
		      		option: function(){
		      			return "NEW";
		      		},
		      		comodityFamily : function(){
		      			return false;
		      		},
		      		comodities: function(){
		      			return $scope.allComodities;
		      		}
		      	}
		    });

		    modalInstance.result.then(function (comodityFamily) {
		    	if(comodityFamily && comodityFamily.comodityFamilyName){
		    		ComodityFamilyService.createComodityFamily(comodityFamily).then(function(){
		    			$scope.comodities.push(comodityFamily);
		    			$location.path("comodities");
		    		});
		    	}
		    });
		}
}]);