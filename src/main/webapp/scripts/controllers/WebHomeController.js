app.controller("WebHomeController", ['$scope', 'groupData', '$location', 
	function($scope, groupData, $location){
	$scope.commodities = groupData.commodities;
	$scope.groups = groupData.groups;
	$scope.columnHeadings = ["Comodity", "Expiry date", "Prev sell price", "Prev sell date", "Prev sell qty", "Order price", "Buy qty", "Sell qty", "Delete"];
	
	$scope.getUserCount = function(users){
		var userArray = users.split(",");
		return userArray.length;
	}
	
	$scope.getExpiryDates = function(comodityId){
		var commodity = _.find($scope.commodities, function(commodity){
			return commodity.name == comodityId;
		});
		if(commodity){
			return commodity.expiryDates.split(',');
		} else{
			return [];
		}
	}
	
	$scope.deleteCommodity = function(groupIndex, orderIndex){
		$scope.groups[groupIndex].orderData.splice(orderIndex, 1);
	}
	
	$scope.addCommodity = function(groupIndex){
		var newCommodity = {
            "comodityId":"",
            "prevOrderValue":"-",
            "sellDate":"-",
            "quantity":"-"
        };
		$scope.groups[groupIndex].orderData.push(newCommodity);
	}
	
	$scope.postOrder = function(){
		for(var i=0; i< $scope.groups.length; i++){
			for(var j=0; j<$scope.groups[i].orderData.length; j++){
				if(!$scope.groups[i].orderData[j].comodityId){
					alert("Please select commodity in group "+$scope.groups[i].groupName);
					return;
				}
				if(!$scope.groups[i].orderData[j].expiryDate){
					alert("Please select expirty date for "+ $scope.groups[i].orderData[j].comodityId +" in group "+$scope.groups[i].groupName);
					return;
				}
				if(!$scope.groups[i].orderData[j].orderPrice){
					alert("Please enter order price for "+ $scope.groups[i].orderData[j].comodityId +" in group "+$scope.groups[i].groupName);
					return;
				}
				if(!$scope.groups[i].orderData[j].buyQuantity && !$scope.groups[i].orderData[j].sellQuantity){
					alert("Please enter sell quantity/buy quantity for "+ $scope.groups[i].orderData[j].comodityId +" in group "+$scope.groups[i].groupName);
					return;
				}
				if($scope.groups[i].orderData[j].buyQuantity){
					$scope.groups[i].orderData[j].option = "BUY"; 
				}
				if($scope.groups[i].orderData[j].sellQuantity){
					$scope.groups[i].orderData[j].option = "SELL"; 
				}
			}
		}
		sessionStorage.setItem("orderData", JSON.stringify($scope.groups));
		sessionStorage.setItem("commodityData", JSON.stringify($scope.commodities));
		sessionStorage.setItem("groupData", JSON.stringify(groupData));
		$location.path("placeOrder");
	}
	
	$scope.formatNumber = function($event){
		var k = $event.keyCode;
		var a=[];
	    for (i = 48; i < 58; i++)
	        a.push(i);
	    if (!(a.indexOf(k)>=0))
	        $event.preventDefault();
	}
	
	$scope.formatPrice = function($event, value){
		var k = $event.keyCode;
		var a=[];
	    for (i = 48; i < 58; i++)
	        a.push(i);
	    a.push(46);
	    if (!(a.indexOf(k)>=0))
	        $event.preventDefault();
	    
	    if(k == 46 && value.indexOf('.') != -1){
	    	$event.preventDefault();
	    }
	}
}]);