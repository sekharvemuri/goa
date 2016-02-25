package com.guru.order.data.vo;

import java.io.Serializable;

public class SubTypeVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int typeId;
	private String name;
	private String description;
	
	public SubTypeVO() {
		super();
	}

	public SubTypeVO(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public SubTypeVO(int id, String name, int typeId) {
		super();
		this.id = id;
		this.name = name;
		this.typeId = typeId;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
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

}
