app.controller("HeaderController", ['$scope', '$location', function($scope, $location){
	$scope.showGroups = function(){
		$location.path("groups");
	}
	
	$scope.showComodities = function(){
		$location.path("comodities");
	}
	
	$scope.showTypes = function(){
		$location.path("types");
	}
	
	$scope.showUsers = function(){
		$location.path("users");
	}
}]);