package com.guru.order.data;

import java.util.List;

import com.guru.order.data.vo.CommodityVO;

public interface CommodityDao {

	/**
	 * Returns the list of all Commodities from DB.
	 * 
	 * @return List<CommodityVO>
	 */
	List<CommodityVO> getCommodities();

	/**
	 * Saves the given Commodity to the DB.
	 * 
	 * @param symbol
	 *            is of type CommodityVO
	 * @throws Exception
	 */
	void addCommodity(CommodityVO commodity) throws Exception;
	
}
