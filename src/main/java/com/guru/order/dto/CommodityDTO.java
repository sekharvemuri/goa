package com.guru.order.dto;

import java.io.Serializable;
import java.util.List;

public class CommodityDTO implements Serializable, Comparable<CommodityDTO> {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private float mainInterval1;
	private float subInterval1;
	private float subInterval2;
	private float subInterval3;
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

	public float getMainInterval1() {
		return mainInterval1;
	}

	public void setMainInterval1(float mainInterval1) {
		this.mainInterval1 = mainInterval1;
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
