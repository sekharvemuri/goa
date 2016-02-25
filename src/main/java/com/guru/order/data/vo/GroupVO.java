package com.guru.order.data.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GroupVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private List<CommodityVO> commodities;
	private List<CandidateVO> candidates;

	public GroupVO() {
		super();
	}

	public GroupVO(String name) {
		super();
		this.name = name;
	}
	
	public GroupVO(int id, String name) {
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

	public List<CommodityVO> getCommodities() {
		return commodities;
	}

	public void setCommodities(List<CommodityVO> commodities) {
		this.commodities = commodities;
	}

	public List<CandidateVO> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<CandidateVO> candidates) {
		this.candidates = candidates;
	}

	public void addCommodity(CommodityVO commodityVO) {
		if(this.commodities == null) {
			this.commodities = new ArrayList<CommodityVO>();
		}
		this.commodities.add(commodityVO);
	}

	public void addCandidate(CandidateVO candidateVO) {
		if (this.candidates == null) {
			this.candidates = new ArrayList<CandidateVO>();
		}
		this.candidates.add(candidateVO);
	}

}
