package com.guru.order.data.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommodityVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private float mainInterval;
	private float subInterval1;
	private float subInterval2;
	private float subInterval3;
	private List<Date> expiryDates;

	public CommodityVO() {
		super();
	}

	public CommodityVO(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getMainInterval() {
		return mainInterval;
	}

	public void setMainInterval(float mainInterval) {
		this.mainInterval = mainInterval;
	}

	public float getSubInterval1() {
		return subInterval1;
	}

	public void setSubInterval1(float subInterval1) {
		this.subInterval1 = subInterval1;
	}

	public float getSubInterval2() {
		return subInterval2;
	}

	public void setSubInterval2(float subInterval2) {
		this.subInterval2 = subInterval2;
	}

	public float getSubInterval3() {
		return subInterval3;
	}

	public void setSubInterval3(float subInterval3) {
		this.subInterval3 = subInterval3;
	}

	public List<Date> getExpiryDates() {
		return expiryDates;
	}

	public void setExpiryDates(List<Date> expiryDates) {
		this.expiryDates = expiryDates;
	}

	public void addExpiryDate(Date expiryDate) {
		if (expiryDates == null) {
			expiryDates = new ArrayList<Date>();
		}
		if (expiryDate != null) {
			expiryDates.add(expiryDate);
		}
	}
}
