var app = angular.module("CommoditiesApp", ['ngRoute', 'ui.bootstrap'])
	.config(['$routeProvider', function($routeProvider){
		$routeProvider.when('/', {
			templateUrl: './views/home.html',
			controller: 'HomeController',
			resolve: {
				groupData: function(GroupService){
					return GroupService.getGroupData();
				}
			}
		}).when('/placeOrder', {
			templateUrl: './views/placeOrder.html',
			controller: 'OrderController'
		}).when('/groups', {
			templateUrl: './views/groups.html',
			controller: 'GroupController',
			resolve: {
				groups: function(GroupService){
					return GroupService.getGroupData();
				}
			}
		}).when('/comodities', {
			templateUrl: './views/comodities.html',
			controller: 'ComodityController',
			resolve: {
				comodities: function(ComodityService){
					return ComodityService.getComodities();
				}
			}
		}).when('/types', {
			templateUrl: './views/types.html',
			controller: 'TypeController'
		}).when('/users', {
			templateUrl: './views/users.html',
			controller: 'UserController',
			resolve: {
				users: function(UserService){
					return UserService.getUsers();
				}
			}
		}).when('/configureComodities', {
			templateUrl: './views/configureComodities.html',
			controller: 'ConfigureComodityController',
			resolve: {
				comodities: function(ComodityService){
					return ComodityService.getComodities();
				},
				comoditiesByGroup: function(ComodityService){
					return ComodityService.getComoditiesByGroup();
				}
			}
		}).when('/configureUsers', {
			templateUrl: './views/configureUsers.html',
			controller: 'ConfigureUserController',
			resolve: {
				users: function(UserService){
					return UserService.getUsers();
				},
				usersByGroup: function(UserService){
					return UserService.getUsersByGroup();
				}
			}
		});
	}]);