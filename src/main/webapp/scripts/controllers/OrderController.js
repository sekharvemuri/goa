app.controller("OrderController", ['$scope', '$location', 'OrderService', function($scope, $location, OrderService){
	$scope.months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
	$scope.groups = JSON.parse(sessionStorage.getItem("orderData"));
	$scope.commodities = JSON.parse(sessionStorage.getItem("commodityData"));
	$scope.order = JSON.parse(sessionStorage.getItem("groupData"));
	$scope.nonEmptyGroups = [];
	$scope.emptyGroups = [];

	for(var i=0; i<$scope.groups.length; i++){
		if($scope.groups[i].orderData.length != 0){
			$scope.nonEmptyGroups.push($scope.groups[i]);
		} else{
			$scope.emptyGroups.push($scope.groups[i]);
		}
	}

	$scope.getClass = function(grpIndex){
		var group = $scope.groups[grpIndex];
		if(group.orderData.length > 0){
			return "grp-success";
		} else{
			return "grp-default";
		}
	}

	$scope.isOpened = function(index){
		if(index == 0){
			return true;
		} else{
			return false;
		}
	}

	$scope.getCommodityName = function(comodityId){
		var commodityName = "";
		for(var i=0; i< $scope.commodities.length; i++){
			if($scope.commodities[i].id == comodityId){
				commodityName = $scope.commodities[i].name;
				break;
			}
		}
		return commodityName;
	}

	$scope.formatDate = function(timeStamp){
		var date = new Date(parseInt(timeStamp));
		return date.getDate()+" "+$scope.getMonth(date.getMonth())+" "+date.getFullYear();
	}

	$scope.getMonth = function(month){
		var iMonth = parseInt(month);
		return $scope.months[iMonth];
	}

	$scope.submitOrder = function(){
		$scope.order.groups = $scope.groups;
		console.log("Posting date: ", $scope.order);
		OrderService.placeOrder($scope.order).then(function(data){
			console.log("order placed");
			$location.path("/");
		}, function(error){
			console.log("placing order failed");
			$location.path("/");
		});
	}
}]);