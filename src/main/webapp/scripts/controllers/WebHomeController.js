app.controller("WebHomeController",['$scope','groupData','$location',
	function($scope, groupData, $location) {
		$scope.commodities = groupData.commodities;
		$scope.groups = groupData.groups;
		$scope.columnHeadings = [ "COMMODITY", "EXPIRY DATE", "PREVIOUS SELL PRICE", "PREVIOUS SELL DATE",
									"PREVIOUS SELL QUANTITY", "ORDER PRICE", "ORDER TYPE", "QUANTITY", "DELETE" ];
		$scope.orderTypes = [ "BUY", "SELL" ];
		$scope.sendMail = false;

		$scope.getUserCount = function(users) {
			var userArray = users.split(",");
			return userArray.length;
		}
		
		$scope.getExpiryDates = function(groupIndex, orderIndex, comodityName) {
			var commodity = _.find($scope.commodities, function(commodity) {
				return commodity.name == comodityName;
			});
			// $scope.groups[groupIndex].orderData[orderIndex].expiryDate = "";
			if (commodity) {
				return commodity.expiryDates;
			} else {
				return [];
			}
		}

		$scope.changeExpiryDate = function(groupIndex, orderIndex) {
			console.log($scope.groups[groupIndex].orderData[orderIndex]);
		}

		$scope.deleteCommodity = function(groupIndex, orderIndex) {
			$scope.groups[groupIndex].orderData.splice(orderIndex, 1);
		}

		$scope.addCommodity = function(groupIndex) {
			var newCommodity = {
				"comodityId" : "",
				"prevSellValue" : "-",
				"sellDate" : "-",
				"quantity" : "-"
			};
			$scope.groups[groupIndex].orderData.push(newCommodity);
		}

		$scope.postOrder = function() {
			for ( var i = 0; i < $scope.groups.length; i++) {
				for ( var j = 0; j < $scope.groups[i].orderData.length; j++) {
					if (!$scope.groups[i].orderData[j].commodity.name) {
						alert("Please select 'Commodity' in group "+ $scope.groups[i].groupName);
						return;
					}
					if (!$scope.groups[i].orderData[j].expiryDate) {
						alert("Please select 'Expirty Date' for "+ $scope.groups[i].orderData[j].commodity.name+ " in group "+ $scope.groups[i].groupName);
						return;
					}
					if (!$scope.groups[i].orderData[j].orderPrice) {
						alert("Please enter 'Oorder Price' for "+ $scope.groups[i].orderData[j].commodity.name+ " in group "+ $scope.groups[i].groupName);
						return;
					}
					if (!$scope.groups[i].orderData[j].quantity) {
						alert("Please enter 'Quantity' for "+ $scope.groups[i].orderData[j].commodity.name+ " in group "+ $scope.groups[i].groupName);
						return;
					}
					if ($scope.groups[i].orderData[j].buyQuantity) {
						$scope.groups[i].orderData[j].option = "BUY";
					}
					if ($scope.groups[i].orderData[j].sellQuantity) {
						$scope.groups[i].orderData[j].option = "SELL";
					}
				}
			}
			console.log($scope.sendMail);
			sessionStorage.setItem("orderData", JSON.stringify($scope.groups));
			sessionStorage.setItem("commodityData", JSON.stringify($scope.commodities));
			sessionStorage.setItem("groupData", JSON.stringify(groupData));
			$location.path("placeOrder");
		}

		$scope.formatNumber = function($event) {
			var k = $event.keyCode;
			var a = [];
			for (i = 48; i < 58; i++)
				a.push(i);
			if (!(a.indexOf(k) >= 0))
				$event.preventDefault();
		}

		$scope.formatPrice = function($event, value) {
			var k = $event.keyCode;
			var a = [];
			for (i = 48; i < 58; i++)
				a.push(i);
			a.push(46);
			if (!(a.indexOf(k) >= 0))
				$event.preventDefault();

			if (k == 46 && value.indexOf('.') != -1) {
				$event.preventDefault();
			}
		}
} ]);