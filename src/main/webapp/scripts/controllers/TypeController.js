app.controller("TypeController", ['$scope', '$uibModal', 'types', 'subtypes', 'TypeService', '$location',
	function($scope, $uibModal, types, subtypes, TypeService, $location){
		$scope.types = types || [];
		$scope.allSubtypes = subtypes;
		
		$scope.editType = function(index){
			var modalInstance = $uibModal.open({
		    	animation: true,
		    	templateUrl: 'type-modal.html',
		      	controller: 'TypeModalCtrl',
		      	resolve: {
		      		option: function(){
		      			return "EDIT";
		      		},
		      		type : function(){
		      			return $scope.types[index];
		      		},
		      		subtypes: function(){
		      			return $scope.allSubtypes;
		      		}
		      	}
		    });

		    modalInstance.result.then(function (type) {
		    	if(type && type.typeName){
		    		TypeService.updateType($scope.types[index]).then(function(response){
						$location.path("types");
					});
		    	}
		    });
		}
		
		$scope.getSubtypes = function(subtypes){
			if(subtypes && subtypes.length > 0){
				return subtypes.split(",");
			}
			return [];
		}
		
		$scope.deleteType = function(index){
			console.log("Deleting type: ", index);
			TypeService.deleteType($scope.types[index]).then(function(response){
				$location.path("types");
			});
		}
		
		$scope.addType = function(){
			var modalInstance = $uibModal.open({
		    	animation: true,
		    	templateUrl: 'type-modal.html',
		      	controller: 'TypeModalCtrl',
		      	resolve: {
		      		option: function(){
		      			return "NEW";
		      		},
		      		type : function(){
		      			return false;
		      		},
		      		subtypes: function(){
		      			return $scope.allSubtypes;
		      		}
		      	}
		    });

		    modalInstance.result.then(function (type) {
		    	if(type && type.typeName){
		    		TypeService.createType(type).then(function(){
		    			$scope.comodities.push(type);
		    			$location.path("types");
		    		});
		    	}
		    });
		}
}]);