package com.guru.order.data.vo;

import java.io.Serializable;
import java.util.Calendar;

import com.guru.order.utils.DateUtils;

public class WorkOrderVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long groupId;
	private String groupName;
	private Long candidateId;
	private int commodityId;
	private String commodityName;
	private Float previousSellPrice;
	private Calendar previousSellDate;
	private int previousSellQty;
	private String orderType;
	private Float orderAmount;
	private int orderQuantity;
	private Calendar orderTime;
	private Calendar expiryDate;
	private Float executedAmount;
	private int executedQuantity;
	private Calendar executedTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public int getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(int commodityId) {
		this.commodityId = commodityId;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public Float getPreviousSellPrice() {
		return previousSellPrice;
	}

	public void setPreviousSellPrice(Float previousSellPrice) {
		this.previousSellPrice = previousSellPrice;
	}

	public Calendar getPreviousSellDate() {
		return previousSellDate;
	}

	public void setPreviousSellDate(Calendar previousSellDate) {
		this.previousSellDate = previousSellDate;
	}

	public int getPreviousSellQty() {
		return previousSellQty;
	}

	public void setPreviousSellQty(int previousSellQty) {
		this.previousSellQty = previousSellQty;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Float getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Float orderAmount) {
		this.orderAmount = orderAmount;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public Calendar getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Calendar orderTime) {
		this.orderTime = orderTime;
	}

	public Calendar getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Calendar expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Float getExecutedAmount() {
		return executedAmount;
	}

	public void setExecutedAmount(Float executedAmount) {
		this.executedAmount = executedAmount;
	}

	public int getExecutedQuantity() {
		return executedQuantity;
	}

	public void setExecutedQuantity(int executedQuantity) {
		this.executedQuantity = executedQuantity;
	}

	public Calendar getExecutedTime() {
		return executedTime;
	}

	public void setExecutedTime(Calendar executedTime) {
		this.executedTime = executedTime;
	}

	@Override
	public String toString() {
		return String.format("Saving GrupName:%s, Commodity:%s, Candidate:%s, OrderType:%s, OrderQuantity:%s, orderAmount:%s, OrderTime:%s, ExpiryTime:%s",
				getGroupName(), getCommodityName(), getCandidateId(), getOrderType(), getOrderQuantity(), getOrderAmount(), DateUtils.getSqlTimeStamp(getOrderTime()), DateUtils.getSqlTimeStamp(getExpiryDate()));
	}
}
