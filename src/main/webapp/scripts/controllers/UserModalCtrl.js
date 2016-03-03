app.controller("UserModalCtrl", function($scope, $uibModalInstance, option, user){
	$scope.heading = "";
	if(option == 'NEW'){	
		$scope.heading = "Create user";
		$scope.user = {
			"name" : "",
			"email": "",
			"mobileNumber": ""
		};
	} else{
		$scope.heading = "Edit user";
		$scope.user = angular.copy(user);
	}
	
	$scope.ok = function () {
		if(!$scope.user.name){
			$uibModalInstance.dismiss('cancel');
		}
    	$uibModalInstance.close($scope.user);
  	}

  	$scope.cancel = function () {
    	$uibModalInstance.dismiss('cancel');
  	}
});