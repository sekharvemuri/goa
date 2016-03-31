package com.guru.order.dto;

import java.io.Serializable;
import java.util.Calendar;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.guru.order.utils.DateUtils;

public class OrderConfirmationDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int compareId;
	private String groupName;
	private Long candidateId;
	private String candidateName;
	private String commodityName;
	private String buySellIndicator;
	private int tradeQuantity;
	private Float unitPrice;
	private Float tradeValue;
	private Calendar executedTime;
	private Calendar expirtyDate;
	private float orderPrice;

	public int getCompareId() {
		return compareId;
	}

	public void setCompareId(int compareId) {
		this.compareId = compareId;
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

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getBuySellIndicator() {
		return buySellIndicator;
	}

	public void setBuySellIndicator(String buySellIndicator) {
		this.buySellIndicator = buySellIndicator;
	}

	public int getTradeQuantity() {
		return tradeQuantity;
	}

	public void setTradeQuantity(int tradeQuantity) {
		this.tradeQuantity = tradeQuantity;
	}

	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Float getTradeValue() {
		return tradeValue;
	}

	public void setTradeValue(Float tradeValue) {
		this.tradeValue = tradeValue;
	}

	public Calendar getExecutedTime() {
		return executedTime;
	}

	public void setExecutedTime(Calendar executedTime) {
		this.executedTime = executedTime;
	}

	public Calendar getExpirtyDate() {
		return expirtyDate;
	}

	public void setExpirtyDate(Calendar expirtyDate) {
		this.expirtyDate = expirtyDate;
	}

	public float getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(float orderPrice) {
		this.orderPrice = orderPrice;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(candidateId).append(commodityName)
				.append(expirtyDate).append(buySellIndicator)
				.append(tradeQuantity).append(unitPrice).hashCode();
	}

	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder("");
		sBuilder.append(candidateId).append(commodityName)
				.append(DateUtils.formatToDDMMYYYY(expirtyDate))
				.append(buySellIndicator).append(tradeQuantity)
				.append(unitPrice);
		return sBuilder.toString();
	}

}
