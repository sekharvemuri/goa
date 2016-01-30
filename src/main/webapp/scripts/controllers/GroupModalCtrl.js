app.controller("GroupModalCtrl", function($scope, $uibModalInstance, option, group){
	$scope.heading = "";
	if(option == 'NEW'){	
		$scope.heading = "Create group";
		$scope.group = {
			"groupId" : 0,
			"groupName" : ""
		};
	} else{
		$scope.heading = "Edit group";
		$scope.group = angular.copy(group);
	}
	
	$scope.ok = function () {
		if(!$scope.group.groupName){
			$uibModalInstance.dismiss('cancel');
		}
    	$uibModalInstance.close($scope.group);
  	}

  	$scope.cancel = function () {
    	$uibModalInstance.dismiss('cancel');
  	}
});