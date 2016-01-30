app.controller("GroupController", ['$scope', '$uibModal', 'groups', 'GroupService', '$location',
	function($scope, $uibModal, groups, GroupService, $location){
	$scope.groups = groups.groups || [];
	
	$scope.editGroup = function(index){
		console.log("Editing Group: ", index);
		var modalInstance = $uibModal.open({
	    	animation: true,
	    	templateUrl: 'group-modal.html',
	      	controller: 'GroupModalCtrl',
	      	resolve: {
	      		option: function(){
	      			return "EDIT";
	      		},
	      		group : function(){
	      			return $scope.groups[index];
	      		}
	      	}
	    });

	    modalInstance.result.then(function (group) {
	    	if(group && group.groupName){
	    		console.log("Group after editing: ", group.groupName);
	    		GroupService.updateGroup($scope.groups[index]).then(function(response){
					$location.path("groups");
				});
	    	}
	    });
	}
	
	$scope.deleteGroup = function(index){
		console.log("Deleting Group: ", index);
		GroupService.deleteGroup($scope.groups[index]).then(function(response){
			$location.path("groups");
		});
	}
	
	$scope.addGroup = function(){
		var modalInstance = $uibModal.open({
	    	animation: true,
	    	templateUrl: 'group-modal.html',
	      	controller: 'GroupModalCtrl',
	      	resolve: {
	      		option: function(){
	      			return "NEW";
	      		},
	      		group : function(){
	      			return false;
	      		}
	      	}
	    });

	    modalInstance.result.then(function (group) {
	    	if(group && group.groupName){
	    		GroupService.createGroup(group).then(function(){
	    			$scope.groups.push(group);
	    			$location.path("groups");
	    		});
	    	}
	    });
	}
	
	$scope.configureComodities = function(){
		$location.path("configureComodities");
	}
	
	$scope.configureUsers = function(){
		$location.path("configureUsers");
	}
}]);