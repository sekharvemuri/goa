package com.guru.order.dto;

import java.io.Serializable;
import java.util.List;

public class CommodityDTO implements Serializable, Comparable<CommodityDTO> {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private List<String> expiryDates;

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

	public List<String> getExpiryDates() {
		return expiryDates;
	}

	public void setExpiryDates(List<String> expiryDates) {
		this.expiryDates = expiryDates;
	}

	@Override
	public int compareTo(CommodityDTO other) {
		return ((this.name == null) ? -1 : (this.name
				.compareTo(other.getName())));
	}

}
