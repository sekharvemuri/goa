package com.guru.order.dto;

import java.io.Serializable;
import java.util.List;

public class OrderDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<GroupDTO> groups;
	private List<CommodityDTO> commodities;
	private List<ExpiryDateDTO> expiryDates;
	private List<String> previousOrderTimes;
	private String orderTime;
	private List<OrderConfirmationDTO> executionsList;

	public List<GroupDTO> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupDTO> groups) {
		this.groups = groups;
	}

	public List<CommodityDTO> getCommodities() {
		return commodities;
	}

	public void setCommodities(List<CommodityDTO> comodities) {
		this.commodities = comodities;
	}

	public List<ExpiryDateDTO> getExpiryDates() {
		return expiryDates;
	}

	public void setExpiryDates(List<ExpiryDateDTO> expiryDates) {
		this.expiryDates = expiryDates;
	}

	public List<String> getPreviousOrderTimes() {
		return previousOrderTimes;
	}

	public void setPreviousOrderTimes(List<String> previousOrderTimes) {
		this.previousOrderTimes = previousOrderTimes;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public List<OrderConfirmationDTO> getExecutionsList() {
		return executionsList;
	}

	public void setExecutionsList(List<OrderConfirmationDTO> executionsList) {
		this.executionsList = executionsList;
	}
	
}