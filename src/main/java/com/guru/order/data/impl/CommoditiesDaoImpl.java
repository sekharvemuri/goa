package com.guru.order.data.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import com.guru.order.data.CommodityDao;
import com.guru.order.data.vo.CommodityVO;

@Component
public class CommoditiesDaoImpl extends BaseDao implements CommodityDao {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<CommodityVO> getCommodities() {
		String query = "select id, name from commodity";
		return ((List<CommodityVO>) getJdbcTemplate().query(query, new BeanPropertyRowMapper(CommodityVO.class)));
	}

	public void addCommodity(CommodityVO commodity) throws Exception {
		String query = "insert into commodity (name) values (?)";
		getJdbcTemplate().update(query, new Object[] {commodity.getName()});
	}

}
