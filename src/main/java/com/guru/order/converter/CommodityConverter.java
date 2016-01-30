package com.guru.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.guru.order.data.vo.CommodityVO;
import com.guru.order.dto.CommodityDTO;
import com.guru.order.utils.CollectionUtils;

public class CommodityConverter {
	
	public static List<CommodityDTO> getCommodities(List<CommodityVO> symbolsList) {
		List<CommodityDTO> dtoList = new ArrayList<CommodityDTO>();
		if (CollectionUtils.isNotEmpty(symbolsList)) {
			for (CommodityVO vo : symbolsList) {
				dtoList.add(getCommodityDto(vo));
			}
		}
		return dtoList;
	}

	private static CommodityDTO getCommodityDto(CommodityVO vo) {
		return new CommodityDTO(vo.getId(), vo.getName());
	}

}
