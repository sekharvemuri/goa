package com.guru.order.dto;

import java.io.Serializable;
import java.util.Calendar;

import com.guru.order.utils.DateUtils;

public class OrderData implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long workOrderId;
	private CommodityDTO commodity;
	private Long expiryDate;
	private String option;
	private int quantity;
	private float orderAverageValue;
	private float orderValue;
	private float lastSoldValue;
	private int lastSoldQuantity;

	public Long getWorkOrderId() {
		return workOrderId;
	}

	public void setWorkOrderId(Long workOrderId) {
		this.workOrderId = workOrderId;
	}

	public CommodityDTO getCommodity() {
		return commodity;
	}

	public void setCommodity(CommodityDTO commodity) {
		this.commodity = commodity;
	}

	public Long getExpiryDate() {
		return expiryDate;
	}
	
	public Calendar getExpiryDateAsDate() {
		return DateUtils.getCalendar(this.getExpiryDate());
	}

	public void setExpiryDate(Long endDate) {
		this.expiryDate = endDate;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getOrderAverageValue() {
		return orderAverageValue;
	}

	public void setOrderAverageValue(float orderAverageValue) {
		this.orderAverageValue = orderAverageValue;
	}

	public float getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(float unitValue) {
		this.orderValue = unitValue;
	}

	public float getLastSoldValue() {
		return lastSoldValue;
	}

	public void setLastSoldValue(float lastSoldValue) {
		this.lastSoldValue = lastSoldValue;
	}

	public int getLastSoldQuantity() {
		return lastSoldQuantity;
	}

	public void setLastSoldQuantity(int lastSoldQuantity) {
		this.lastSoldQuantity = lastSoldQuantity;
	}

}
