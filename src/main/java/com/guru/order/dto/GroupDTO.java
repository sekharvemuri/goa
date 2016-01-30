package com.guru.order.dto;

import java.io.Serializable;
import java.util.List;

public class GroupDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String groupName;
	private String users;
	private List<OrderData> orderData;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getUsers() {
		return users;
	}

	public void setUsers(String users) {
		this.users = users;
	}

	public List<OrderData> getOrderData() {
		return orderData;
	}

	public void setOrderData(List<OrderData> orderData) {
		this.orderData = orderData;
	}

}
