app.controller("ModalController", function($scope, $uibModalInstance, commodities, commodity, expiryDates){
	$scope.months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
	$scope.commodity = {
		"comodity": "",
  		"expiryDate": "",
  		"orderValue": 0,
  		"sellBuyOption": "",
  		"orderAverageValue": 0,
  		"quantity": 0
	};
	if(commodity && commodity.commodity.comodityId){
		$scope.commodity = commodity;
	}
	$scope.commodities = commodities;
	$scope.expiryDates = expiryDates;
	$scope.status = {
		opened: false
	};
	$scope.format="dd MMM yyyy";

	$scope.formatDate = function(timeStamp){
		var date = new Date(parseInt(timeStamp));
		return date.getDate()+" "+$scope.getMonth(date.getMonth())+" "+date.getFullYear();
	}
	
	$scope.getMonth = function(month){
		var iMonth = parseInt(month);
		return $scope.months[iMonth];
	}
	
	$scope.open = function($event) {
		$scope.status.opened = true;
	}

	$scope.ok = function () {
		if(!$scope.commodity.comodity.comodityId){
			$uibModalInstance.dismiss('cancel');
		}
    	$uibModalInstance.close($scope.commodity);
  	}

  	$scope.cancel = function () {
    	$uibModalInstance.dismiss('cancel');
  	}

  	$scope.formatValue = function(value){
  		if(value == 0){
  			return 'N/A';
  		}
  		return value;
  	}
});