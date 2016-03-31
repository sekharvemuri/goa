package com.guru.order.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.guru.order.data.vo.CommodityFamilyVO;
import com.guru.order.data.vo.CommodityVO;
import com.guru.order.dto.CommodityDTO;
import com.guru.order.dto.CommodityFamilyDTO;
import com.guru.order.utils.CollectionUtils;
import com.guru.order.utils.DateUtils;

public class CommodityConverter {

	public static List<CommodityDTO> getCommodities(
			List<CommodityVO> symbolsList) {
		List<CommodityDTO> dtoList = new ArrayList<CommodityDTO>();
		if (CollectionUtils.isNotEmpty(symbolsList)) {
			for (CommodityVO vo : symbolsList) {
				dtoList.add(getCommodityDto(vo));
			}
		}
		return dtoList;
	}

	private static CommodityDTO getCommodityDto(CommodityVO vo) {
		CommodityDTO dto = new CommodityDTO(vo.getId(), vo.getName());
		dto.setMainInterval1(vo.getMainInterval());
		dto.setSubInterval1(vo.getSubInterval1());
		dto.setSubInterval2(vo.getSubInterval2());
		dto.setSubInterval3(vo.getSubInterval3());
		dto.setExpiryDates(getExpiryDatesAsString(vo.getExpiryDates()));
		return dto;
	}

	public static CommodityFamilyDTO getCommodityFamilyDTO(CommodityFamilyVO vo) {
		return new CommodityFamilyDTO(vo.getId(), vo.getName());
	}

	public static List<String> getExpiryDatesAsString(List<Date> expiryDates) {
		List<String> list = new ArrayList<String>();
		if (CollectionUtils.isNotEmpty(expiryDates)) {
			for (Date date : expiryDates) {
				list.add(DateUtils.formatToDDMMMYY(date));
			}
		}
		return list;
	}

}
