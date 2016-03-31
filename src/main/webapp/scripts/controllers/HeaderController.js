app.controller("HeaderController", ['$scope', '$location', function($scope, $location){
	$scope.showWebHome = function(){
		$(".cd-panel-close").click();
		$location.path("");
	}
	
	$scope.showAdhocOrder = function(){
		$(".cd-panel-close").click();
		$location.path("adhoc");
	}
	
	$scope.showGroups = function(){
		$(".cd-panel-close").click();
		$location.path("groups");
	}
	
	$scope.showComodities = function(){
		$(".cd-panel-close").click();
		$location.path("comodities");
	}
	
	$scope.showComodityFamilies = function(){
		$(".cd-panel-close").click();
		$location.path("comodityFamily");
	}
	
	$scope.showTypes = function(){
		$(".cd-panel-close").click();
		$location.path("types");
	}
	
	$scope.showUsers = function(){
		$(".cd-panel-close").click();
		$location.path("users");
	}
	
	$scope.showUploadTrades = function(){
		$(".cd-panel-close").click();
		$location.path("views/upload.html");
	}
}]);