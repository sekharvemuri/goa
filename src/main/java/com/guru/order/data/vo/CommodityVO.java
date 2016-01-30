package com.guru.order.data.vo;

import java.io.Serializable;

public class CommodityVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;

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

}
