package com.guru.order.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.guru.order.converter.CommodityConverter;
import com.guru.order.data.CommodityDao;
import com.guru.order.data.vo.CommodityVO;
import com.guru.order.dto.CommodityDTO;
import com.guru.order.service.CommodityService;

@Component
public class CommodityServiceImpl implements CommodityService {

	private CommodityDao commodityDao;

	public List<CommodityDTO> getCommodities() {
		List<CommodityVO> symbolsList = null;
		commodityDao.getCommodities();
		return CommodityConverter.getCommodities(symbolsList);
	}
	


	@Override
	public Map<Integer, CommodityVO> getCommodityIntervals(Set<Integer> commodityIDsSet) {
		return commodityDao.getCommodityIntervals(commodityIDsSet);
	}

	@Autowired
	public void setCommodityDao(CommodityDao commodityDao) {
		this.commodityDao = commodityDao;
	}

}
