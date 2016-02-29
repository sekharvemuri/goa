app.controller("TypeModalCtrl", function($scope, $uibModalInstance, option, type, subtypes){
	$scope.allSubtypes = subtypes || [];
	$scope.allSubtypes = _.pluck(subtypes, "subtypeName");
	$scope.type = type;
	$scope.heading = "";
	
	$scope.filterSubtypes = function(){
		$scope.leftSubtypes = [];
		for(var i=0; i<$scope.allSubtypes.length; i++){
			if($scope.rightSubtypes.indexOf($scope.allSubtypes[i]) == -1){
				$scope.leftSubtypes.push($scope.allSubtypes[i]);
			}
		}
	}
	
	if(option == 'NEW'){	
		$scope.heading = "Create type";
		$scope.type = {
			"typeId" : 0,
			"typeName" : "",
		};
		$scope.leftSubtypes = $scope.allSubtypes;
		$scope.rightSubtypes = [];
	} else{
		$scope.heading = "Edit Type";
		$scope.type = angular.copy(type);
		$scope.rightSubtypes = $scope.type.subtypes.split(",");
		$scope.filterSubtypes();
		$scope.selectedSubtype = $scope.leftSubtypes.length > 0 ? $scope.leftSubtypes[0]: "";
		$scope.selectedSubtypeToDel = $scope.rightSubtypes.length > 0 ? $scope.rightSubtypes[0]: "";
	}
	
	$scope.addSubtype = function(){
		if($scope.selectedSubtype){
			if($scope.rightSubtypes.indexOf($scope.selectedSubtype) == -1){
				$scope.rightSubtypes.push($scope.selectedSubtype);
			}
			$scope.leftSubtypes.splice($scope.leftSubtypes.indexOf($scope.selectedSubtype), 1);
			$scope.selectedSubtype = $scope.leftSubtypes.length > 0 ? $scope.leftSubtypes[0]: "";
		}
	}
	
	$scope.removeSubtype = function(){
		if($scope.selectedSubtypeToDel){
			if($scope.leftSubtypes.indexOf($scope.selectedSubtypeToDel) == -1){				
				$scope.leftSubtypes.push($scope.selectedSubtypeToDel);
			}
			$scope.rightSubtypes.splice($scope.rightSubtypes.indexOf($scope.selectedSubtypeToDel), 1);
			$scope.selectedSubtypeToDel = $scope.rightSubtypes.length > 0 ? $scope.rightSubtypes[0]: "";
		}
	}
	
	$scope.ok = function () {
		if(!$scope.type.typeName){
			$uibModalInstance.dismiss('cancel');
		}
    	$uibModalInstance.close($scope.type);
  	}

  	$scope.cancel = function () {
    	$uibModalInstance.dismiss('cancel');
  	}
});