<%@page import="com.guru.order.dto.OrderData"%>
<%@page import="com.guru.order.dto.GroupDTO"%>
<%@page import="java.util.List"%>
<html>
<head>
<title>Confirm Order Executions</title>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.2.0.min.js"></script>
<script type="text/javascript" src="./lib/jquery.tabletojson.js"></script>
<script type="text/javascript">
	if (!String.prototype.startsWith) {
		String.prototype.startsWith = function(searchString, position) {
			position = position || 0;
			return this.indexOf(searchString, position) === position;
		};
	}

	function getJsonData() {
		var myRows = [];
		var $headers = $("th");
		var $rows = $("tbody tr").each(function(index) {
			$cells = $(this).find("td");
			myRows[index] = {};
			
			$cells.each(function(cellIndex) {
				var key = $($headers[cellIndex]).attr('label');
				if (key != undefined && key != "") {
					var data = $(this).html();
					if (data.startsWith("<input ")) {
						data = $(this).children(0).val();
					}
					myRows[index][key] = data;
				}
			});
		});

		var myObj = {"executionsList" : myRows};
		return (JSON.stringify(myObj));
	}
	
	function submitChanges() {
		$('#message').html("");
		var jsonData = getJsonData();
		$.ajax({
            url: 'executed',
            type: 'post',
            data : jsonData,
            contentType: 'application/json',
            success: function (data) {
                if (data == "success") {
                	alert('Saved Successfully');
                	
                }
                else {
                	alert("Failed to save. Error Message : " + data);
                	$('#message').html(data);
                }
            },
            error: function(data) {
            	alert('error');
            },
            fail: function(data) {
            	alert('fail');
            },
            always: function(data) {
            	alert('always');
            }
        });
	}
</script>
</head>
<body>

<%
List<GroupDTO> ordersList = (List<GroupDTO>) request.getAttribute("ordersList");
%>

	<div align="center">
		<h3><u>Confirm Order Executions</u></h3>
		<table id="dataTable" border="1">
			<tr>
				<th style="visibility: hidden; display: none;">compareId</th>
				<th label="groupName">Group</th>
				<th label="commodityName">Symbol</th>
				<th>Order Price</th>
				<th label="buySellIndicator">Order Type</th>
				<th>Order Qty</th>
				<th label="unitPrice">Executed Price</th>
				<th label="tradeQuantity">Executed Qty</th>
			</tr>
			<tbody>
			<%
			if (ordersList != null) {
				for (GroupDTO groupDto : ordersList) {
					for (OrderData orderData : groupDto.getOrderData()) {
				%>
					<tr>
						<td style="visibility: hidden; display: none;"><%=orderData.getWorkOrderId() %></td>
						<td><%=groupDto.getGroupName() %></td>
						<td><%=orderData.getCommodity().getName() %></td>
						<td><%=orderData.getOrderPrice()%></td>
						<td><%=orderData.getOrderType()%></td>
						<td><%=orderData.getQuantity() %></td>
						<td><input type="text" value=""></td>
						<td><input type="text" value=""></td>
					</tr>
					<%
					}
				}
			} %>
			</tbody>
		</table>
		<br/>
		<button onclick="submitChanges()">Submit</button>
		<div id="message" style="color: red;"></div>
	</div>
</body>
</html>