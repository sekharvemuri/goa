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
		List<CommodityVO> commodityVOs = null;
		commodityVOs = commodityDao.getCommodities();
		for (CommodityVO listItem : commodityVOs) {
			listItem.setExpiryDates(commodityDao.getExpiryDates(listItem.getId()));
		}
		return CommodityConverter.getCommodities(commodityVOs);
	}

	@Override
	public void updateCommodity(CommodityDTO dto) {
		try {
			commodityDao.updateCommodity(new CommodityVO(dto.getId(), dto
					.getName()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addCommodity(CommodityDTO commodityDTO) {
		try {
			commodityDao
					.addCommodity(new CommodityVO(0, commodityDTO.getName()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteCommodity(CommodityDTO commodityDTO) {
		try {
			commodityDao.deleteCommodity(commodityDTO.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
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
