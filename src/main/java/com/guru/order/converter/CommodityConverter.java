package com.guru.order.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.guru.order.data.vo.CommodityVO;
import com.guru.order.dto.CommodityDTO;
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
		if (CollectionUtils.isNotEmpty(vo.getExpiryDates())) {
			List<String> list = new ArrayList<String>(vo.getExpiryDates()
					.size());
			for (Date date : vo.getExpiryDates()) {
				list.add(DateUtils.formatToDDMMMYY(date));
			}
			dto.setExpiryDates(list);
		}
		return dto;
	}

}
