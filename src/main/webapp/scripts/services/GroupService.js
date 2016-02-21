app.service("GroupService", ['$http', function($http){
	var service = {
		getGroupData: function(){
			return $http.get("rest/order").then(function(response){
				return response.data;
			}, function(){
				console.log("error::: ");
			});
		},
		
		getGroupDataNew: function(){
			return $http.get("rest/order").then(function(response){
				var result = {"commodities":[{
										"id":"1",
										"name":"ALLUMINIUM",
										"expiryDates":"01-MAR-2016,02-MAR-2016,03-MAR-2016"
									},{
										"id":"2",
										"name":"COPPER",
										"expiryDates":"29-APR-2016,30-APR-2016,31-APR-2016"
									},{
										"id":"3",
										"name":"LEAD",
										"expiryDates":"01-JUN-2016,02-JUN-2016,03-JUN-2016"
									}
								],
								"groups":[{
									"groupName":"A",
							        "users":"90129",
									"commodities":[{
											"name":"ALLUMINIUM"
										},{
											"name":"COPPER"
										},{
											"name":"LEAD"
										}
									],
									"orderData":[{
							            "comodityId":"COPPER",
							            "prevOrderValue":"361.10",
							            "prevSellDate":"31-MAR-2016",
							            "prevSellQuantity":"1"
							        },{
							            "comodityId":"COPPER",
							            "prevOrderValue":"333.10",
							            "prevSellDate":"12-MAR-2016",
							            "prevSellQuantity":"1"
							        },{
							            "comodityId":"LEAD",
							            "prevOrderValue":"755.70",
							            "prevSellDate":"31-APR-2016",
							            "prevSellQuantity":"1"
							        },{
							            "comodityId":"ALLUMINIUM",
							            "prevOrderValue":"660.80",
							            "prevSellDate":"31-MAY-2016",
							            "prevSellQuantity":"1"
							        },{
							            "comodityId":"ALLUMINIUM",
							            "prevOrderValue":"638.80",
							            "prevSellDate":"31-JAN-2016",
							            "prevSellQuantity":"1"
							        }]
							    },{
							        "groupName":"B",
							        "users":"69757,90140,90141,90142,90127",
									"commodities":[{
											"name":"ALLUMINIUM"
										},{
											"name":"COPPER"
										}
									],
							        "orderData":[{
							            "comodityId":"COPPER",
							            "prevOrderValue":"368.20",
							            "prevSellDate":"31-DEC-2016",
							            "prevSellQuantity":"1"
							        },{
							            "comodityId":"COPPER",
							            "prevOrderValue":"348.20",
							            "prevSellDate":"31-OCT-2016",
							            "prevSellQuantity":"2"
							        },{
							            "comodityId":"COPPER",
							            "prevOrderValue":"333.20",
							            "prevSellDate":"31-AUG-2016",
							            "prevSellQuantity":"3"
							        }]
							    }]
							};
				return result;
			}, function(){
				console.log("error::: ");
			})
		},
		
		createGroup: function(group){
			var config = {
				data: group,
				headers: {
					"Content-Type": "application/json"
				}
			};
			return $http.post("rest/group", config).then(function(response){
				console.log("Create group", group);
				return response;
			}, function(error){
				console.log("Create group", group);
				console.log(error);
			});
		},
		
		updateGroup: function(group){
			var config = {
				data: group,
				headers: {
					"Content-Type": "application/json"
				}
			};
			return $http.put("rest/group", config).then(function(response){
				console.log("Update group", group);
				return response;
			}, function(error){
				console.log("Update group", group);
				console.log(error);
			});
		},
		
		deleteGroup: function(group){
			var config = {
				data: group,
				headers: {
					"Content-Type": "application/json"
				}
			};
			return $http.delete("rest/group", config).then(function(response){
				console.log("Delete group", group);
				return response;
			}, function(error){
				console.log("Delete group", group);
				console.log(error);
			});
		}
	};
	return service;
}]);