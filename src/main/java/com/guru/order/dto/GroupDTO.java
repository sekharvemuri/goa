package com.guru.order.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GroupDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String groupName;
	private String users;
	private List<CommodityDTO> commodities;
	private List<OrderData> orderData = new ArrayList<OrderData>();

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String name) {
		this.groupName = name;
	}

	public String getUsers() {
		return users;
	}

	public void setUsers(String users) {
		this.users = users;
	}

	public List<CommodityDTO> getCommodities() {
		return commodities;
	}

	public void setCommodities(List<CommodityDTO> commodities) {
		this.commodities = commodities;
	}

	public List<OrderData> getOrderData() {
		return orderData;
	}

	public void setOrderData(List<OrderData> pastData) {
		this.orderData = pastData;
	}

	public void addOrderData(OrderData object) {
		if (this.orderData == null) {
			this.orderData = new ArrayList<OrderData>();
		}
		this.orderData.add(object);
	}

}
