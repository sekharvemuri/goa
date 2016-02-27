package com.guru.order.dto;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import com.guru.order.utils.DateUtils;

public class OrderData implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long workOrderId;
	private CommodityDTO commodity;
	private String expiryDate;
	private float prevSellValue;
	private String prevSellDate;
	private int prevSellQuantity;
	private float orderPrice;
	private String orderType;
	private int quantity;

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

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public float getPrevSellValue() {
		return prevSellValue;
	}

	public void setPrevSellValue(float prevSellValue) {
		this.prevSellValue = prevSellValue;
	}

	public String getPrevSellDate() {
		return prevSellDate;
	}

	public void setPrevSellDate(String prevSellDate) {
		this.prevSellDate = prevSellDate;
	}

	public int getPrevSellQuantity() {
		return prevSellQuantity;
	}

	public void setPrevSellQuantity(int prevSellQuantity) {
		this.prevSellQuantity = prevSellQuantity;
	}

	public float getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(float orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getExpiryDateAsDate() {
		try {
			return DateUtils.getDateAsddMMMyy(expiryDate);
		} catch (ParseException e) {
			return null;
		}
	}

	public Calendar getExpiryDateAsCal() {
		try {
			return DateUtils.getCalendarAsddMMMyy(expiryDate);
		} catch (ParseException e) {
			return null;
		}
	}

}
