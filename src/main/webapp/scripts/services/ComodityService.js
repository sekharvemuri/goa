app.service("ComodityService", ['$http', function($http){
	var service = {
		getComodities: function(){
			return $http.get("scripts/services/UserService.js").then(function(response){
				console.log("Getting comodities");
				var comodities = [{
					"comodityId": 1,
					"comodityName": "Zinc"
				}, {
					"comodityId": 2,
					"comodityName": "Copper"
				}, {
					"comodityId": 3,
					"comodityName":"Lead"
				}, {
					"comodityId": 4,
					"comodityName":"Gold"
				}, {
					"comodityId": 5,
					"comodityName":"Aluminium"
				}, {
					"comodityId": 6,
					"comodityName": "Nickel"
				}];
				return comodities;
			}, function(error){
				console.log("error::: ");
			});
		},
		
		createComodity: function(comodity){
			var config = {
				data: comodity,
				headers: {
					"Content-Type": "application/json"
				}
			};
			return $http.post("rest/comodity", config).then(function(response){
				console.log("Create comodity", comodity);
				return response;
			}, function(error){
				console.log("Create comodity", comodity);
				console.log(error);
			});
		},
		
		updateComodity: function(comodity){
			var config = {
				data: comodity,
				headers: {
					"Content-Type": "application/json"
				}
			};
			return $http.put("rest/comodity", config).then(function(response){
				console.log("Update comodity", comodity);
				return response;
			}, function(error){
				console.log("Update comodity", comodity);
				console.log(error);
			});
		},
		
		deleteComodity: function(comodity){
			var config = {
				data: comodity,
				headers: {
					"Content-Type": "application/json"
				}
			};
			return $http.delete("rest/comodity", config).then(function(response){
				console.log("Delete comodity", comodity);
				return response;
			}, function(error){
				console.log("Delete comodity", comodity);
				console.log(error);
			});
		},
		
		getComoditiesByGroup: function(){
			return $http.get("rest/order/get").then(function(response){
				var comodities = [{
						"groupId": 1,
						"groupName": "Group 1",
						"comodities": [{
							"comodityId": 1,
							"comodityName": "Zinc"
						}]
					}, {
						"groupId": 2,
						"groupName": "Group 2",
						"comodities": [{
							"comodityId": 2,
							"comodityName": "Copper"
						}, {
							"comodityId": 3,
							"comodityName": "Lead"
						}]
					}, {
						"groupId": 3,
						"groupName": "Group 3",
						"comodities": []
					}];
				return comodities;
			}, function(error){
				console.log("error::: ");
			});
		}
	};
	return service;
}]);