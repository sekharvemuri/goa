app.service("UserService", ['$http', function($http){
	var service = {
		getUsers: function(){
			return $http.get("rest/order/get").then(function(response){
				console.log("Getting users");
				var users = [{
					"userId": 1,
					"userName": "Test1",
					"email": "test@gmail.com",
					"mobile": "1234567890"
				}, {
					"userId": 2,
					"userName": "Test2",
					"email": "test@gmail.com",
					"mobile": "1234567890"
				}, {
					"userId": 3,
					"userName":"Test3",
					"email": "test@gmail.com",
					"mobile": "1234567890"
				}];
				return users;
			}, function(error){
				console.log("error::: ");
			});
		},
		
		createUser: function(user){
			var config = {
				data: user,
				headers: {
					"Content-Type": "application/json"
				}
			};
			return $http.post("rest/user", config).then(function(response){
				console.log("Create user", user);
				return response;
			}, function(error){
				console.log("Create user", user);
				console.log(error);
			});
		},
		
		updateUser: function(user){
			var config = {
				data: user,
				headers: {
					"Content-Type": "application/json"
				}
			};
			return $http.put("rest/user", config).then(function(response){
				console.log("Update user", user);
				return response;
			}, function(error){
				console.log("Update user", user);
				console.log(error);
			});
		},
		
		deleteUser: function(user){
			var config = {
				data: user,
				headers: {
					"Content-Type": "application/json"
				}
			};
			return $http.delete("rest/user", config).then(function(response){
				console.log("Delete user", user);
				return response;
			}, function(error){
				console.log("Delete user", user);
				console.log(error);
			});
		},
		
		getUsersByGroup: function(){
			return $http.get("rest/order/get").then(function(response){
				var users = [{
						"groupId": 1,
						"groupName": "Group 1",
						"users": [{
							"userId": 1,
							"userName": "Test User 1"
						}]
					}, {
						"groupId": 2,
						"groupName": "Group 2",
						"users": [{
							"userId": 2,
							"userName": "Test user 2"
						}, {
							"userId": 3,
							"userName": "Test user 3"
						}]
					}, {
						"groupId": 3,
						"groupName": "Group 3",
						"users": []
					}];
				return users;
			}, function(error){
				console.log("error::: ");
			});
		}
	};
	return service;
}]);