package com.guru.order.data.vo;

import java.io.Serializable;
import java.util.Calendar;

public class RecentExecutionVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int subTypeId;
	private int groupId;
	private String groupName;
	private int commodityId;
	private String commodityName;
	private Float intervalAmt;
	private Calendar recentExecutionTime;

	public int getSubTypeId() {
		return subTypeId;
	}

	public void setSubTypeId(int subTypeId) {
		this.subTypeId = subTypeId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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

	public Float getIntervalAmt() {
		return intervalAmt;
	}

	public void setIntervalAmt(Float intervalAmt) {
		this.intervalAmt = intervalAmt;
	}

	public Calendar getRecentExecutionTime() {
		return recentExecutionTime;
	}

	public void setRecentExecutionTime(Calendar recentExecutionTime) {
		this.recentExecutionTime = recentExecutionTime;
	}

}
