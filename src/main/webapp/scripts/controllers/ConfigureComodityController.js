app.controller("ConfigureComodityController", ['$scope', '$location', 'comodities', 'comoditiesByGroup',
	function($scope, $location, comodities, comoditiesByGroup){
		$scope.allComodities = comodities || [];
		$scope.comoditiesByGroup = comoditiesByGroup || [];
		$scope.selectedGroup = "";
		
		$scope.leftComodities = [];
		$scope.rightComodities = [];
		
		$scope.changeGroup = function(){
			console.log($scope.selectedGroup);
		}
		
		$scope.$watch("selectedGroup", function(newValue, oldValue){
			if(newValue){
				for(var i=0; i<$scope.comoditiesByGroup.length; i++){
					if($scope.comoditiesByGroup[i].groupId == newValue){
						$scope.filterComodities(i);
					}
				}
			}
		})
		
		$scope.filterComodities = function(index){
			var comodityIds = $scope.getComodityIds($scope.allComodities);
			var groupComodityIds = $scope.getComodityIds($scope.comoditiesByGroup[index].comodities);
			$scope.leftComodities = [];
			$scope.rightComodities = angular.copy($scope.comoditiesByGroup[index].comodities);
			
			for(var i=0; i< comodityIds.length; i++){
				if(groupComodityIds.indexOf(comodityIds[i]) == -1){
					$scope.leftComodities.push($scope.allComodities[i]);
				}
			}
			$scope.selectedLeftGroup = $scope.leftComodities.length > 0 ? $scope.leftComodities[0].comodityId : "";
			$scope.selectedRightGroup = $scope.rightComodities.length > 0 ? $scope.rightComodities[0].comodityId : "";
		}
		
		$scope.getComodityIds = function(comodityArray){
			var idArray = [];
			for(var i=0; i<comodityArray.length; i++){
				idArray.push(comodityArray[i].comodityId);
			}
			return idArray;
		}
		
		$scope.removeComodity = function(){
			for(var i=0; i< $scope.rightComodities.length; i++){
				if($scope.rightComodities[i].comodityId == $scope.selectedRightGroup){
					$scope.leftComodities.push($scope.rightComodities[i]);
					$scope.rightComodities.splice(i, 1);
					$scope.selectedRightGroup = $scope.rightComodities.length > 0 ? $scope.rightComodities[0].comodityId : "";
					return;
				}
			}
		}
		
		$scope.addComodity = function(){
			for(var i=0; i< $scope.leftComodities.length; i++){
				if($scope.leftComodities[i].comodityId == $scope.selectedRightGroup){
					$scope.rightComodities.push($scope.leftComodities[i]);
					$scope.leftComodities.splice(i, 1);
					$scope.selectedRightGroup = $scope.leftComodities.length > 0 ? $scope.leftComodities[0].comodityId : "";
					return;
				}
			}
		}
	}
]);