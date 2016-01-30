package com.guru.order.dto;

import java.io.Serializable;

public class CommodityDTO implements Serializable, Comparable<CommodityDTO> {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	
	public CommodityDTO() {
		super();
	}

	public CommodityDTO(String name) {
		super();
		this.name = name;
	}

	public CommodityDTO(int id, String name) {
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

	@Override
	public int compareTo(CommodityDTO other) {
		return ((this.name == null) ? -1 : (this.name.compareTo(other.getName())));
	}

}
