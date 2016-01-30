app.controller("ComodityController", ['$scope', '$uibModal', 'comodities', 'ComodityService', '$location',
	function($scope, $uibModal, comodities, ComodityService, $location){
		$scope.comodities = comodities || [];
		
		$scope.editComodity = function(index){
			console.log("Editing comodity: ", index);
			var modalInstance = $uibModal.open({
		    	animation: true,
		    	templateUrl: 'comodity-modal.html',
		      	controller: 'ComodityModalCtrl',
		      	resolve: {
		      		option: function(){
		      			return "EDIT";
		      		},
		      		comodity : function(){
		      			return $scope.comodities[index];
		      		}
		      	}
		    });

		    modalInstance.result.then(function (comodity) {
		    	if(comodity && comodity.comodityName){
		    		console.log("Comodity after editing: ", comodity.comodityName);
		    		ComodityService.updateComodity($scope.comodities[index]).then(function(response){
						$location.path("comodities");
					});
		    	}
		    });
		}
		
		$scope.deleteComodity = function(index){
			console.log("Deleting comodity: ", index);
			ComodityService.deleteComodity($scope.comodities[index]).then(function(response){
				$location.path("comodities");
			});
		}
		
		$scope.addComodity = function(){
			var modalInstance = $uibModal.open({
		    	animation: true,
		    	templateUrl: 'comodity-modal.html',
		      	controller: 'ComodityModalCtrl',
		      	resolve: {
		      		option: function(){
		      			return "NEW";
		      		},
		      		comodity : function(){
		      			return false;
		      		}
		      	}
		    });

		    modalInstance.result.then(function (comodity) {
		    	if(comodity && comodity.comodityName){
		    		ComodityService.createComodity(comodity).then(function(){
		    			$scope.comodities.push(comodity);
		    			$location.path("comodities");
		    		});
		    	}
		    });
		}
}]);