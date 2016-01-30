app.service("GroupService", ['$http', function($http){
	var service = {
		getGroupData: function(){
			return $http.get("rest/order").then(function(response){
				return response.data;
			}, function(){
				console.log("error::: ");
			});
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