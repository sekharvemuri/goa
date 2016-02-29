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
										"expiryDates":"01-MAR-16,02-MAR-16,03-MAR-16"
									},{
										"id":"2",
										"name":"COPPER",
										"expiryDates":"29-APR-16,30-APR-16,31-APR-16"
									},{
										"id":"3",
										"name":"LEAD",
										"expiryDates":"01-JUN-16,02-JUN-16,03-JUN-16"
									}
								],
								"groups":[{
									"groupName":"Group A",
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
							            "commodity":{
							            	"id": 1,
							            	"name" : "COPPER"
							            },
							            "expiryDate":"29-APR-16",
							            "prevSellValue":"361.10",
							            "prevSellDate":"31-MAR-16",
							            "prevSellQuantity":"1",
							            "orderPrice":"100.00",
							            "orderType":"BUY",
							            "quantity":"1"
							            
							        },{
							        	"commodity":{
							            	"name" : "COPPER"
							            },
							            "prevSellValue":"333.10",
							            "prevSellDate":"12-MAR-16",
							            "prevSellQuantity":"1"
							        },{
							        	"commodity":{
							            	"name" : "LEAD"
							            },
							            "expiryDate":"01-JUN-16",
							            "prevSellValue":"755.70",
							            "prevSellDate":"31-APR-16",
							            "prevSellQuantity":"1",
							            "orderPrice":"100.00",
							            "orderType":"SELL",
							            "quantity":"1"
							        },{
							        	"commodity":{
							            	"name" : "ALLUMINIUM"
							            },
							            "prevSellValue":"660.80",
							            "prevSellDate":"31-MAY-16",
							            "prevSellQuantity":"1"
							        },{
							        	"commodity":{
							            	"name" : "ALLUMINIUM"
							            },
							            "prevSellValue":"638.80",
							            "prevSellDate":"31-JAN-16",
							            "prevSellQuantity":"1"
							        }]
							    },{
							        "groupName":"Group B",
							        "users":"69757,90140,90141,90142,90127",
									"commodities":[{
											"name":"ALLUMINIUM"
										},{
											"name":"COPPER"
										}
									],
							        "orderData":[{
							        	"commodity":{
							            	"name" : "COPPER"
							            },
							            "prevSellValue":"368.20",
							            "prevSellDate":"31-DEC-16",
							            "prevSellQuantity":"1"
							        },{
							        	"commodity":{
							            	"name" : "COPPER"
							            },
							            "prevSellValue":"348.20",
							            "prevSellDate":"31-OCT-16",
							            "prevSellQuantity":"2"
							        },{
							        	"commodity":{
							            	"name" : "COPPER"
							            },
							            "prevSellValue":"333.20",
							            "prevSellDate":"31-AUG-16",
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