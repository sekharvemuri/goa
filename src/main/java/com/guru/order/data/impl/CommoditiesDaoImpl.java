package com.guru.order.data.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.guru.order.data.CommodityDao;
import com.guru.order.data.vo.CommodityVO;

@Component
public class CommoditiesDaoImpl extends BaseDao implements CommodityDao {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<CommodityVO> getCommodities() {
		String query = "select id, name from commodity order by name";
		return ((List<CommodityVO>) getJdbcTemplate().query(query,
				new BeanPropertyRowMapper(CommodityVO.class)));
	}

	public void addCommodity(CommodityVO commodity) throws Exception {
		String query = "insert into commodity (name) values (?)";
		getJdbcTemplate().update(query, new Object[] { commodity.getName() });
	}

	@Override
	public void updateCommodity(CommodityVO commodityVO) throws Exception {
		String query = "update commodity set name = ? where id = ?";
		getJdbcTemplate().update(query,
				new Object[] { commodityVO.getName(), commodityVO.getId() });
	}

	@Override
	public void deleteCommodity(int id) throws Exception {
		String query = "delete from commodity where id = ?";
		getJdbcTemplate().update(query, new Object[] { id });
	}
	
	@Override
	public Map<Integer, CommodityVO> getCommodityIntervals(Set<Integer> commodityIDsSet) {
		String query = "select id, name, main_interval as mainInterval, sub_interval_1 as subInterval1, sub_interval_2 as subInterval2, sub_interval_3 as subInterval3 "
				+ " from commodity";
		return getJdbcTemplate().query(query, new ResultSetExtractor<Map<Integer, CommodityVO>> () {

			@Override
			public Map<Integer, CommodityVO> extractData(ResultSet rs)
					throws SQLException, DataAccessException {
				Map<Integer, CommodityVO> results = new HashMap<Integer, CommodityVO>();
				while (rs.next()) {
					CommodityVO vo = new CommodityVO();
					vo.setId(rs.getInt("id"));
					vo.setName(rs.getString("name"));
					vo.setMainInterval(rs.getFloat("mainInterval"));
					vo.setSubInterval1(rs.getFloat("subInterval1"));
					vo.setSubInterval2(rs.getFloat("subInterval2"));
					vo.setSubInterval3(rs.getFloat("subInterval3"));
					results.put(vo.getId(), vo);
				}
				return results;
			}
			
		});
	}

	@Override
	public List<Date> getExpiryDates(int commodityId) {
		String query = "select expiry_date as expiryDate from commodity_expiry_date where cmdt_id=? order by expiry_date";
		Object[] params = new Object[] {commodityId };
		return getJdbcTemplate().queryForList(query, params, Date.class);
	}

}
