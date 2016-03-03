app.controller("UserController", ['$scope', '$uibModal', 'users', 'UserService', '$location', '$route',
  	function($scope, $uibModal, users, UserService, $location, $route){
  		$scope.users = users || [];
  		
  		$scope.editUser = function(index){
  			var modalInstance = $uibModal.open({
  		    	animation: true,
  		    	templateUrl: 'user-modal.html',
  		      	controller: 'UserModalCtrl',
  		      	resolve: {
  		      		option: function(){
  		      			return "EDIT";
  		      		},
  		      		user : function(){
  		      			return $scope.users[index];
  		      		}
  		      	}
  		    });

  		    modalInstance.result.then(function (user) {
  		    	if(user && user.name){
  		    		console.log("User after editing: ", user.name);
  		    		UserService.updateUser(user).then(function(response){
  		    			$route.reload();
  					});
  		    	}
  		    });
  		}
  		
  		$scope.deleteUser = function(index){
  			UserService.deleteUser($scope.users[index]).then(function(response){
  				$route.reload();
  			});
  		}
  		
  		$scope.addUser = function(){
  			var modalInstance = $uibModal.open({
  		    	animation: true,
  		    	templateUrl: 'user-modal.html',
  		      	controller: 'UserModalCtrl',
  		      	resolve: {
  		      		option: function(){
  		      			return "NEW";
  		      		},
  		      		user : function(){
  		      			return false;
  		      		}
  		      	}
  		    });

  		    modalInstance.result.then(function (user) {
  		    	if(user && user.name){
  		    		UserService.createUser(user).then(function(){
  		    			$scope.users.push(user);
  		    			$route.reload();
  		    		});
  		    	}
  		    });
  		}
  }]);