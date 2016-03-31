package com.guru.order.data.vo;

import java.io.Serializable;

public class GroupCommodityVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int groupId;
	private int commodityId;
	private float buyInterval;
	private float sellInterval;

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(int commodityId) {
		this.commodityId = commodityId;
	}

	public float getBuyInterval() {
		return buyInterval;
	}

	public void setBuyInterval(float buyInterval) {
		this.buyInterval = buyInterval;
	}

	public float getSellInterval() {
		return sellInterval;
	}

	public void setSellInterval(float sellInterval) {
		this.sellInterval = sellInterval;
	}

}
