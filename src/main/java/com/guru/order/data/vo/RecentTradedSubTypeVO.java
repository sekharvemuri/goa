package com.guru.order.data.vo;

import java.io.Serializable;
import java.util.Calendar;

public class RecentTradedSubTypeVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int subTypeId;
	private int groupId;
	private CommodityVO commodity;
	private int commodityFamilyId;
	private Calendar recentExecDate;

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

	public CommodityVO getCommodity() {
		return commodity;
	}

	public void setCommodity(CommodityVO commoditVo) {
		this.commodity = commoditVo;
	}

	public int getCommodityFamilyId() {
		return commodityFamilyId;
	}

	public void setCommodityFamilyId(int commodityFamilyId) {
		this.commodityFamilyId = commodityFamilyId;
	}

	public Calendar getRecentExecDate() {
		return recentExecDate;
	}

	public void setRecentExecDate(Calendar recentExecDate) {
		this.recentExecDate = recentExecDate;
	}

}
