package com.guru.order.data.vo;

import java.io.Serializable;
import java.util.List;

public class TypeVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String description;
	private List<SubTypeVO> subTypes;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<SubTypeVO> getSubTypes() {
		return subTypes;
	}

	public void setSubTypes(List<SubTypeVO> subTypes) {
		this.subTypes = subTypes;
	}

}
