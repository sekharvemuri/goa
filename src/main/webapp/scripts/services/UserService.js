app.service("UserService", ['$http', function($http){
	var service = {
			getUsers: function(){
				return $http.get("rest/user").then(function(response){
					return response.data;
				}, function(){
					console.log("error::: ");
				});
			},
			
		getUsersDummy: function(){
			return $http.get("scripts/services/UserService.js").then(function(response){
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
			return $http.post("rest/user/add", user).then(function(response){
				return response;
			}, function(error){
				console.log(error);
			});
		},
		
		updateUser: function(user){
			console.log(user);
			return $http.put("rest/user/update", user).then(function(response){
				return response;
			}, function(error){
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
			return $http.delete("rest/user/delete", config).then(function(response){
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