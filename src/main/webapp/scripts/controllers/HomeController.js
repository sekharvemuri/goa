app.controller("HomeController", ['$scope', 'groupData', '$uibModal', '$location', 
	function($scope, groupData, $uibModal, $location){
    $scope.months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
	$scope.commodities = groupData.commodities;
	$scope.groups = groupData.groups;
	$scope.expiryDates = groupData.expiryDates;
	$scope.previousOrderTimes = [];//groupData.previousOrderTimes;
	$scope.dateValue = "";//$scope.previousOrderTimes[$scope.previousOrderTimes.length-1];

	$scope.isOpen = function(index){
		return true;
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

	$scope.addNewCommodity = function(grpIndex){
		var modalInstance = $uibModal.open({
	    	animation: true,
	     	templateUrl: 'myModalContent.html',
	      	controller: 'ModalController',
	      	resolve: {
	      		commodities: function(){
	      			return $scope.commodities;
	      		},
	      		commodity : function(){
	      			return false;
	      		},
	      		expiryDates: function(){
	      			return $scope.expiryDates;
	      		}
	      	}
	    });

	    modalInstance.result.then(function (selectedItem) {
	    	if(selectedItem){
	    		$scope.groups[grpIndex].orderData.push(selectedItem);
	    	}
	    }, function () {
	    });
	}

	$scope.removeCommodity = function(groupIndex, commodityIndex){
		$scope.groups[groupIndex].orderData.splice(commodityIndex, 1);
	}

	$scope.editCommodity = function(groupIndex, commodityIndex){
		var modalInstance = $uibModal.open({
	    	animation: true,
	     	templateUrl: 'myModalContent.html',
	      	controller: 'ModalController',
	      	resolve: {
	      		commodities: function(){
	      			return $scope.commodities;
	      		},
	      		commodity: function(){
	      			return $scope.groups[groupIndex].orderData[commodityIndex];
	      		},
	      		expiryDates: function(){
	      			return $scope.expiryDates;
	      		}
	      	}
	    });

	    modalInstance.result.then(function (selectedItem) {
	    	$scope.groups[groupIndex].orderData[commodityIndex] = selectedItem;
	    }, function(test) {
	    });
	}

	$scope.placeOrder = function(){
		sessionStorage.setItem("orderData", JSON.stringify($scope.groups));
		sessionStorage.setItem("commodityData", JSON.stringify($scope.commodities));
		sessionStorage.setItem("groupData", JSON.stringify(groupData));
		$location.path("placeOrder");
	}

	$scope.getClass = function(grpIndex){
		var group = $scope.groups[grpIndex];
		//console.log(grpIndex, group);
		if(group.orderData.length > 0){
			return "grp-success";
		} else{
			return "grp-default";
		}
	}

	$scope.getCurrentDate = function(){
		return new Date();
	}

	$scope.refreshData = function(){
		console.log("test");
	}
}]);