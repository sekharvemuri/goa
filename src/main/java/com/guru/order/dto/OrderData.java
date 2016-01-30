package com.guru.order.dto;

import java.io.Serializable;

public class OrderData implements Serializable {

	private static final long serialVersionUID = 1L;
	private ComodityDTO comodity;
	private Long expiryDate;
	private String sellBuyOption;
	private int quantity;
	private float orderAverageValue;
	private float orderValue;

	public ComodityDTO getComodity() {
		return comodity;
	}

	public void setComodity(ComodityDTO comodity) {
		this.comodity = comodity;
	}

	public Long getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Long expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getSellBuyOption() {
		return sellBuyOption;
	}

	public void setSellBuyOption(String sellBuyOption) {
		this.sellBuyOption = sellBuyOption;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Float getOrderAverageValue() {
		return orderAverageValue;
	}

	public void setOrderAverageValue(Float orderAverageValue) {
		this.orderAverageValue = orderAverageValue;
	}

	public Float getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(Float orderValue) {
		this.orderValue = orderValue;
	}

}
