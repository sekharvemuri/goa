app.controller("UserController", ['$scope', '$uibModal', 'users', 'UserService', '$location',
  	function($scope, $uibModal, users, UserService, $location){
  		$scope.users = users || [];
  		
  		$scope.editUser = function(index){
  			console.log("Editing user: ", index);
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
  		    	if(user && user.userName){
  		    		console.log("User after editing: ", user.userName);
  		    		UserService.updateUser($scope.users[index]).then(function(response){
  						$location.path("users");
  					});
  		    	}
  		    });
  		}
  		
  		$scope.deleteUser = function(index){
  			console.log("Deleting user: ", index);
  			UserService.deleteUser($scope.users[index]).then(function(response){
  				$location.path("users");
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
  		    	if(user && user.userName){
  		    		UserService.createUser(user).then(function(){
  		    			$scope.users.push(user);
  		    			$location.path("users");
  		    		});
  		    	}
  		    });
  		}
  }]);