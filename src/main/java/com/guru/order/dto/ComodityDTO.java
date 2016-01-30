package com.guru.order.dto;

import java.io.Serializable;

public class ComodityDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer comodityId;
	private String comodityName;

	public Integer getComodityId() {
		return comodityId;
	}

	public void setComodityId(Integer comodityId) {
		this.comodityId = comodityId;
	}

	public String getComodityName() {
		return comodityName;
	}

	public void setComodityName(String comodityName) {
		this.comodityName = comodityName;
	}

}
