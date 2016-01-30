package com.guru.order.data.vo;

import java.io.Serializable;

public class CandidateVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String mobile;
	private String email;

	public CandidateVO() {
		super();
	}

	public CandidateVO(long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
