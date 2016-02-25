app.controller("ComodityFamilyModalCtrl", function($scope, $uibModalInstance, option, comodityFamily, comodities){
	$scope.allComodities = comodities || [];
	$scope.allComodities = _.pluck(comodities, "comodityName");
	$scope.comodityFamily = comodityFamily;
	$scope.heading = "";
	$scope.expiryDates = [];
	
	$scope.filterComodities = function(){
		$scope.leftComodities = [];
		for(var i=0; i<$scope.allComodities.length; i++){
			if($scope.rightComodities.indexOf($scope.allComodities[i]) == -1){
				$scope.leftComodities.push($scope.allComodities[i]);
			}
		}
	}
	
	if(option == 'NEW'){	
		$scope.heading = "Create comodityFamily family";
		$scope.comodityFamily = {
			"comodityFamilyId" : 0,
			"comodityFamilyName" : "",
			"mainInterval": "",
			"subInterval1":"",
			"subInterval2":"",
			"subInterval3":""
		};
		$scope.leftComodities = $scope.allComodities;
		$scope.rightComodities = [];
	} else{
		$scope.heading = "Edit Comodity Family";
		$scope.comodityFamily = angular.copy(comodityFamily);
		$scope.rightComodities = $scope.comodityFamily.comodities.split(",");
		$scope.filterComodities();
	}
	
	$scope.addComodity = function(){
		if($scope.selectedComodity){
			$scope.rightComodities.push($scope.selectedComodity);
			$scope.leftComodities.splice($scope.leftComodities.indexOf($scope.selectedComodity), 1);
		}
	}
	
	$scope.removeComodity = function(){
		if($scope.selectedComodityToDel){
			$scope.leftComodities.push($scope.selectedComodityToDel);
			$scope.rightComodities.splice($scope.rightComodities.indexOf($scope.selectedComodityToDel), 1);
		}
	}
	
	$scope.addValue = function($event){
		var key = $event.which;
		if(key==13 && $scope.expiryDate != "" && $scope.expiryDates.indexOf($scope.expiryDate) == -1){
			$scope.expiryDates.push($scope.expiryDate);
		}
	}
	
	$scope.ok = function () {
		if(!$scope.comodityFamily.comodityFamilyName){
			$uibModalInstance.dismiss('cancel');
		}
    	$uibModalInstance.close($scope.comodityFamily);
  	}

  	$scope.cancel = function () {
    	$uibModalInstance.dismiss('cancel');
  	}
});