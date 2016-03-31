package com.guru.order.dto;

import java.io.Serializable;
import java.util.List;

public class CommodityFamilyDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private List<CommodityDTO> commodites;

	public CommodityFamilyDTO() {
		super();
	}

	public CommodityFamilyDTO(Long id, String name) {
		this.id = id.intValue();
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

	public List<CommodityDTO> getCommodites() {
		return commodites;
	}

	public void setCommodites(List<CommodityDTO> commodites) {
		this.commodites = commodites;
	}

}
