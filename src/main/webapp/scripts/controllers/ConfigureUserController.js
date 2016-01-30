app.controller("ConfigureUserController", ['$scope', '$location', 'users', 'usersByGroup',
	function($scope, $location, users, usersByGroup){
		$scope.allUsers = users || [];
		$scope.usersByGroup = usersByGroup || [];
		$scope.selectedGroup = "";
		
		$scope.leftUsers = [];
		$scope.rightUsers = [];
		
		$scope.$watch("selectedGroup", function(newValue, oldValue){
			if(newValue){
				for(var i=0; i<$scope.usersByGroup.length; i++){
					if($scope.usersByGroup[i].groupId == newValue){
						$scope.filterUsers(i);
					}
				}
			}
		});
		
		$scope.filterUsers = function(index){
			var userIds = $scope.getUserIds($scope.allUsers);
			var groupUserIds = $scope.getUserIds($scope.usersByGroup[index].users);
			$scope.leftUsers = [];
			$scope.rightUsers = angular.copy($scope.usersByGroup[index].users);
			
			for(var i=0; i< userIds.length; i++){
				if(groupUserIds.indexOf(userIds[i]) == -1){
					$scope.leftUsers.push($scope.allUsers[i]);
				}
			}
			$scope.selectedLeftGroup = $scope.leftUsers.length > 0 ? $scope.leftUsers[0].userId : "";
			$scope.selectedRightGroup = $scope.rightUsers.length > 0 ? $scope.rightUsers[0].userId : "";
		}
		
		$scope.getUserIds = function(userArray){
			var idArray = [];
			for(var i=0; i<userArray.length; i++){
				idArray.push(userArray[i].userId);
			}
			return idArray;
		}
		
		$scope.removeUser = function(){
			for(var i=0; i< $scope.rightUsers.length; i++){
				if($scope.rightUsers[i].userId == $scope.selectedRightGroup){
					$scope.leftUsers.push($scope.rightUsers[i]);
					$scope.rightUsers.splice(i, 1);
					$scope.selectedRightGroup = $scope.rightUsers.length > 0 ? $scope.rightUsers[0].userId : "";
					return;
				}
			}
		}
		
		$scope.addUser = function(){
			for(var i=0; i< $scope.leftUsers.length; i++){
				if($scope.leftUsers[i].userId == $scope.selectedRightGroup){
					$scope.rightUsers.push($scope.leftUsers[i]);
					$scope.leftUsers.splice(i, 1);
					$scope.selectedRightGroup = $scope.leftUsers.length > 0 ? $scope.leftUsers[0].userId : "";
					return;
				}
			}
		}
	}
]);