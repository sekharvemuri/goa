app.service("OrderService", ['$http', function($http){
	var service = {
		placeOrder: function(order){
			return $http({
				method: 'POST',
				url: "rest/order/place/",
				data: order,
				headers: {
						"Content-Type": "application/json"
					}
				}).then(function(response){
					console.log(response.data);
					return response.data;
				}, function(error){
					console.log("error::: ");
					return error;
				});
		}
	};
	return service;
}]);