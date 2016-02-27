package com.guru.order.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.guru.order.data.vo.CommodityVO;
import com.guru.order.dto.CommodityDTO;

public interface CommodityService {

	List<CommodityDTO> getCommodities();

	void updateCommodity(CommodityDTO commodityDTO);
	
	void deleteCommodity(CommodityDTO commodityDTO);
	
	void addCommodity(CommodityDTO commodityDTO);
	
	Map<Integer, CommodityVO> getCommodityIntervals(Set<Integer> commodityIDsSet);

}
