package com.guru.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.guru.order.converter.CommodityConverter;
import com.guru.order.data.CommodityDao;
import com.guru.order.data.vo.CommodityVO;
import com.guru.order.dto.CommodityDTO;
import com.guru.order.service.CommodityService;

@Component
public class CommodityServiceImpl implements CommodityService {

	private CommodityDao symbolsDao;

	public List<CommodityDTO> getCommodities() {
		List<CommodityVO> symbolsList = null;
		symbolsDao.getCommodities();
		return CommodityConverter.getCommodities(symbolsList);
	}

	@Autowired
	public void setSymbolsDao(CommodityDao symbolsDao) {
		this.symbolsDao = symbolsDao;
	}

}
