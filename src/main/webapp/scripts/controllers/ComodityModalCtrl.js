app.controller("ComodityModalCtrl", function($scope, $uibModalInstance, option, comodity){
	$scope.heading = "";
	if(option == 'NEW'){	
		$scope.heading = "Create comodity";
		$scope.comodity = {
			"comodityId" : 0,
			"comodityName" : ""
		};
	} else{
		$scope.heading = "Edit comodity";
		$scope.comodity = angular.copy(comodity);
	}
	
	$scope.ok = function () {
		if(!$scope.comodity.comodityName){
			$uibModalInstance.dismiss('cancel');
		}
    	$uibModalInstance.close($scope.comodity);
  	}

  	$scope.cancel = function () {
    	$uibModalInstance.dismiss('cancel');
  	}
});