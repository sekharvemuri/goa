package com.guru.order.dto;

import java.io.Serializable;
import java.util.Date;

public class ExpiryDateDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer expiryDateId;
	private Long expiryDateInMillis;
	private Date expiryDate;

	public Integer getExpiryDateId() {
		return expiryDateId;
	}

	public void setExpiryDateId(Integer expiryDateId) {
		this.expiryDateId = expiryDateId;
	}

	public Long getExpiryDateInMillis() {
		return expiryDateInMillis;
	}

	public void setExpiryDateInMillis(Long expiryDateInMillis) {
		this.expiryDateInMillis = expiryDateInMillis;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

}
