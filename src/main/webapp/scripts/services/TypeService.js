app.service("TypeService", ['$http', function($http){
	var service = {
		getComodities:function(){
			return $http.get("rest/commodity").then(function(response){
				return response.data;
			}, function(){
				console.log("error::: ");
			});
		},	
		getSubtypes: function(){
			return $http.get("scripts/services/UserService.js").then(function(response){
				var subtypes = [{
					"subtypeId": 1,
					"subtypeName": "Subtype 1"
				}, {
					"subtypeId": 2,
					"subtypeName": "Subtype 2"
				}, {
					"subtypeId": 3,
					"subtypeName": "Subtype 3"
				}, {
					"subtypeId": 4,
					"subtypeName": "Subtype 4"
				}, {
					"subtypeId": 5,
					"subtypeName": "Subtype 5"
				}, {
					"subtypeId": 6,
					"subtypeName": "Subtype 6"
				}, {
					"subtypeId": 7,
					"subtypeName": "Subtype 7"
				}];
				return subtypes;
			}, function(error){
				console.log("error::: ");
			});
		},
		
		getTypes: function(){
			return $http.get("scripts/services/UserService.js").then(function(response){
				var types = [{
					"typeId": 1,
					"typeName": "Zinc",
					"subtypes": "Subtype 1, Subtype 2, Subtype 3"
				}, {
					"typeId": 2,
					"typeName": "Copper",
					"subtypes": "Subtype 1, Subtype 2, Subtype 3"
				}, {
					"typeId": 3,
					"typeName":"Lead",
					"subtypes": "Subtype 1"
				}, {
					"typeId": 4,
					"typeName":"Gold",
					"subtypes": "Subtype 2, Subtype 3"
				}, {
					"typeId": 5,
					"typeName":"Aluminium",
					"subtypes": "Subtype 3"
				}];
				return types;
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
		
		updateType: function(type){
			var config = {
				data: type,
				headers: {
					"Content-Type": "application/json"
				}
			};
			return $http.put("rest/comodity", config).then(function(response){
				return response;
			}, function(error){
				console.log(error);
			});
		},
		
		deleteType: function(type){
			var config = {
				data: type,
				headers: {
					"Content-Type": "application/json"
				}
			};
			return $http.delete("rest/comodity", config).then(function(response){
				return response;
			}, function(error){
				console.log(error);
			});
		}
	};
	return service;
}]);