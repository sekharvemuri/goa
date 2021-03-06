package com.guru.order.data;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.guru.order.data.vo.CommodityFamilyVO;
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

	void updateCommodity(CommodityVO commodityVO) throws Exception;
	
	void deleteCommodity(int id) throws Exception;
	
	Map<Integer, CommodityVO> getCommodityIntervals(Set<Integer> commodityIDsSet);

	List<Date> getExpiryDates(int commodityId);

	List<CommodityFamilyVO> getCommodityFamilies();

	List<CommodityVO> getCommoditiesByFamily(Long familyId);

}
