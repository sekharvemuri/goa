package com.guru.order.data.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.guru.order.data.WorkOrderDao;
import com.guru.order.data.vo.CommodityVO;
import com.guru.order.data.vo.GroupVO;
import com.guru.order.data.vo.RecentExecutionVO;
import com.guru.order.data.vo.WorkOrderVO;
import com.guru.order.dto.OrderConfirmationDTO;
import com.guru.order.utils.CollectionUtils;
import com.guru.order.utils.Constants;
import com.guru.order.utils.DateUtils;

@Component
public class WorkOrderDaoImpl extends BaseDao implements WorkOrderDao {

	String INSERT_SQL = " insert into work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date) "
			+ " values ((select id from groups where name=?), (select id from commodity where name=?), ?, ?, ?, ?, ?, ?)";

	public void movePreviousOrdersToBackup() {
		String insertQuery = "insert into previous_work_order (select * from work_order)";
		String deleteQuery = "delete from work_order";
		getJdbcTemplate().execute(insertQuery);
		getJdbcTemplate().execute(deleteQuery);
	}
	
	public void saveWorkOrders(final List<WorkOrderVO> list) {
		if (list != null && !list.isEmpty()) {
			getJdbcTemplate().batchUpdate(INSERT_SQL,
					new BatchPreparedStatementSetter() {

						public void setValues(PreparedStatement ps, int i)
								throws SQLException {
							WorkOrderVO vo = list.get(i);
							ps.setString(1, vo.getGroupName());
							ps.setString(2, vo.getCommodityName());
							ps.setString(3, vo.getOrderType());
							ps.setLong(4, vo.getCandidateId());
							ps.setInt(5, vo.getOrderQuantity());
							ps.setFloat(6, vo.getOrderAmount());
							ps.setTimestamp(7, DateUtils.getSqlTimeStamp(vo.getOrderTime()));
							ps.setTimestamp(8, DateUtils.getSqlTimeStamp(vo.getExpiryDate()));
						}

						public int getBatchSize() {
							return list.size();
						}
					});
		}
	}
	
	@Override
	public void saveWorkOrders(WorkOrderVO vo) {
		getJdbcTemplate().update(INSERT_SQL, new Object[]{vo.getGroupName(), vo.getCommodityName(), vo.getOrderType(), vo.getCandidateId(), 
				vo.getOrderQuantity(), vo.getOrderAmount(), DateUtils.getSqlTimeStamp(vo.getOrderTime()), DateUtils.getSqlTimeStamp(vo.getExpiryDate())});
	}

	@Override
	public Calendar getRecentOrderTime(String orderType) {
		String query = "select distinct order_time from work_order where order_type='" + orderType + "' and executed_time is null order by order_time desc";
		Calendar recentOrderTime = getJdbcTemplate().query(query, new ResultSetExtractor<Calendar>() {

			@Override
			public Calendar extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if(rs != null && rs.next()) {
					return DateUtils.getCalendar(rs.getTimestamp(1));
				}
				return null;
			}
			
		});
		return recentOrderTime;
	}

	@Override
	public List<WorkOrderVO> getOrders(String orderType) {
		String query = "select g.name as groupName, c.name as commodityName, c.id as commodityId, avg(wo.order_amount) orderAmount, "
				+ " wo.id as workOrderId, wo.order_quantity as orderQuantity, wo.order_time as orderTime, wo.order_type as orderType, wo.expiry_date expiryDate "
				+ " from groups g, commodity c, work_order wo where g.id=wo.group_id and c.id = wo.cmdty_id and wo.executed_amount is null "
				+ " and wo.order_type = ? "
				+ " group by wo.group_id, wo.cmdty_id, wo.order_time";
		
		List<WorkOrderVO> ordersList = getJdbcTemplate().query(query, new Object[] {orderType}, new ResultSetExtractor<List<WorkOrderVO>>() {

			@Override
			public List<WorkOrderVO> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<WorkOrderVO> list = null;
				if (rs != null) {
					list = new ArrayList<WorkOrderVO>();
					while (rs.next()) {
						WorkOrderVO vo = new WorkOrderVO();
						vo.setId(rs.getLong("workOrderId"));
						vo.setGroupName(rs.getString("groupName"));
						vo.setCommodityId(rs.getInt("commodityId"));
						vo.setCommodityName(rs.getString("commodityName"));
						vo.setOrderAmount(rs.getFloat("orderAmount"));
						vo.setOrderQuantity(rs.getInt("orderQuantity"));
						vo.setOrderTime(DateUtils.getCalendar(rs.getTimestamp("orderTime")));
						vo.setOrderType(rs.getString("orderType"));
						Calendar expiryDateCal = DateUtils.getCalendar(rs.getTimestamp("expiryDate"));
						DateUtils.trimToDate(expiryDateCal);
						vo.setExpiryDate(expiryDateCal);
						list.add(vo);
					}
				}
				return list;
			}
			
		});
		return ordersList;
	}
	
	@Override
	public List<WorkOrderVO> getNextOrders() {
		String query = "select g.name as groupName, c.name as commodityName, c.id as commodityId, avg(wo.order_amount) orderAmount, "
				+ " wo.order_quantity as orderQuantity, wo.order_time as orderTime, wo.order_type as orderType, wo.expiry_date expiryDate "
				+ " from groups g, commodity c, next_work_order wo where g.id=wo.group_id and c.id = wo.cmdty_id "
				+ " group by wo.group_id, wo.cmdty_id";
		
		List<WorkOrderVO> ordersList = getJdbcTemplate().query(query, new ResultSetExtractor<List<WorkOrderVO>>() {

			@Override
			public List<WorkOrderVO> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<WorkOrderVO> list = null;
				if (rs != null) {
					list = new ArrayList<WorkOrderVO>();
					while (rs.next()) {
						WorkOrderVO vo = new WorkOrderVO();
						vo.setGroupName(rs.getString("groupName"));
						vo.setCommodityId(rs.getInt("commodityId"));
						vo.setCommodityName(rs.getString("commodityName"));
						vo.setOrderAmount(rs.getFloat("orderAmount"));
						vo.setOrderQuantity(rs.getInt("orderQuantity"));
						vo.setOrderTime(DateUtils.getCalendar(rs.getTimestamp("orderTime")));
						vo.setOrderType(rs.getString("orderType"));
						vo.setExpiryDate(DateUtils.getCalendar(rs.getTimestamp("expiryDate")));
						list.add(vo);
					}
				}
				return list;
			}
			
		});
		return ordersList;
	}

	@Override
	public Map<String, List<String>> getGroupUsers() {
		String query = "select g.id as groupId, g.name as groupName, gc.cand_id as candidateId from groups g, group_candidates gc where g.id=gc.group_id";
		return getJdbcTemplate().query(query, new ResultSetExtractor<Map<String, List<String>>> () {

			@Override
			public Map<String, List<String>> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<String, List<String>> resultsMap = null;
				if (rs != null) {
					resultsMap = new HashMap<String, List<String>>();
					while (rs.next()) {
						String groupName = rs.getString("groupName");
						List<String> candidatesList = resultsMap.get(groupName);
						if (candidatesList == null) {
							candidatesList = new ArrayList<String>();
							resultsMap.put(groupName, candidatesList);
						}
						candidatesList.add(rs.getString("candidateId"));
					}
				}
				return resultsMap;
			}
			
		});
	}
	
	@Override
	public Map<String, List<CommodityVO>> getGroupCommodities() {
		String query = "select g.id as groupId, g.name as groupName, c.id as commodityId, c.name as commodityName "
				+ " from groups g, commodity c, group_commodity gc "
				+ " where g.id=gc.group_id and c.id = gc.cmdty_id";
		return getJdbcTemplate().query(query, new ResultSetExtractor<Map<String, List<CommodityVO>>> () {

			@Override
			public Map<String, List<CommodityVO>> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<String, List<CommodityVO>> map = new HashMap<String, List<CommodityVO>>();
				while (rs.next()) {
					String groupName = rs.getString("groupName");
					List<CommodityVO> commoditiesList = map.get(groupName);
					if (commoditiesList == null) {
						commoditiesList = new ArrayList<CommodityVO>();
						map.put(groupName, commoditiesList);
					}
					int commodityId = rs.getInt("commodityId");
					String commodityName = rs.getString("commodityName");
					commoditiesList.add(new CommodityVO(commodityId, commodityName));
				}
				return map;
			}
			
		});
	}

	@Override
	public List<GroupVO> getAdHocGroups() {
		String query = "select g.id as id, g.name as name from groups g where g.id not in (select group_id from sub_type_groups)";
		return getJdbcTemplate().query(query, new BeanPropertyRowMapper<GroupVO>(GroupVO.class));
	}

	@Override
	public List<RecentExecutionVO> getRecentExecutions() {
		String query = "select re.sub_type_id as subTypeId, g.id as groupId, g.name as groupName, c.id as commodityId, c.name as commodityName, "
				+ " re.interval_amt as intervalAmt, re.recent_exec_date as recentExecutionTime "
				+ " from recent_executions re, groups g, commodity c "
				+ " where g.id=re.group_id and c.id=re.cmdty_id "
				+ " order by sub_type_id asc, recent_exec_date desc";
		return getJdbcTemplate().query(query, new BeanPropertyRowMapper<RecentExecutionVO>(RecentExecutionVO.class));
	}
	
	@Override
	public void saveOrderConfirmation(final Map<Long, Map<String, List<OrderConfirmationDTO>>> map) {
		if (map == null) {
			return;
		}
		
		for (Long candidateId : map.keySet()) {
			Map<String, List<OrderConfirmationDTO>> orderTypeMap = map.get(candidateId);
			if (orderTypeMap != null) {
				for (String orderType : orderTypeMap.keySet()) {
					if (Constants.BUY_SELL_OPTION.BUY.toString().equalsIgnoreCase(orderType)) {
						//saveBuyOrderConfirmations(orderTypeMap.get(orderType));
					}
					else {
						//saveSellOrderConfirmations(orderTypeMap.get(orderType));
					}
				}
			}
		}
		
		/*if (CollectionUtils.isNotEmpty(list)) {
			String query = "update work_order set executed_amount=?, executed_quantity=?, executed_time=? where candidate_id=? and cmdty_id=(select id from commodity where name=?) and order_type=?";
			final Timestamp executedTime = DateUtils.getSqlTimeStamp(Calendar.getInstance());
			getJdbcTemplate().batchUpdate(query,
					new BatchPreparedStatementSetter() {

						@Override
						public void setValues(PreparedStatement ps, int i)
								throws SQLException {
							OrderConfirmationDTO dto = list.get(i);
							ps.setFloat(1, dto.getUnitPrice());
							ps.setInt(2, dto.getTradeQuantity());
							ps.setTimestamp(3, executedTime);
							ps.setLong(4, dto.getCandidateId());
							ps.setString(5, dto.getCandidateName());
							ps.setString(6, dto.getBuySellIndicator());
						}

						@Override
						public int getBatchSize() {
							return list.size();
						}
				
			});
		}*/
	}

	@Override
	public void saveExecutedOrderByGroupName(final List<OrderConfirmationDTO> list) {
		if (CollectionUtils.isNotEmpty(list)) {
			String query = "update work_order set executed_amount=?, executed_quantity=?, executed_time=? where cmdty_id=(select id from commodity where name=?) and order_type=? and group_id=(select id from groups where name=?)";
			final Timestamp executedTime = DateUtils.getSqlTimeStamp(Calendar.getInstance());
			getJdbcTemplate().batchUpdate(query,
					new BatchPreparedStatementSetter() {

						@Override
						public void setValues(PreparedStatement ps, int i)
								throws SQLException {
							OrderConfirmationDTO dto = list.get(i);
							ps.setFloat(1, dto.getUnitPrice());
							ps.setInt(2, dto.getTradeQuantity());
							ps.setTimestamp(3, executedTime);
							ps.setString(4, dto.getCommodityName());
							ps.setString(5, dto.getBuySellIndicator());
							ps.setString(6, dto.getGroupName());
						}

						@Override
						public int getBatchSize() {
							return list.size();
						}
				
			});
		}
	}

	@Override
	public void saveTradedOrders(final List<OrderConfirmationDTO> list, int groupId, String groupName) {
		final Calendar executedTime = Calendar.getInstance();
		String query = "update work_order set executed_amount=?, executed_quantity=?, executed_time=? "
				+ " where candidate_id=? "
				+ " and cmdty_id=(select id from commodity where name=?) "
				+ " and DATE_FORMAT(expiry_Date, '%e-%m-%Y')=? "
				+ " and order_type=?";
		
		for (OrderConfirmationDTO dto : list) {
			String str = String.format("update work_order set executed_amount=%s, executed_quantity=%s, executed_time='%s' "
					+ " where candidate_id=%s "
					+ " and cmdty_id=(select id from commodity where name='%s') "
					+ " and DATE_FORMAT(expiry_Date, )='%s' "
					+ " and order_type='%s'", dto.getUnitPrice(), dto.getTradeQuantity(), DateUtils.getSqlTimeStamp(executedTime),
					dto.getCandidateId(), dto.getCommodityName(), DateUtils.formatToDDMMYYYY(dto.getExpirtyDate().getTime()),
					dto.getBuySellIndicator(), dto.getTradeQuantity(), dto.getUnitPrice());
			System.out.println(str);
			
			Object[] params = new Object[] {dto.getUnitPrice(), dto.getTradeQuantity(), DateUtils.getSqlTimeStamp(executedTime), 
					dto.getCandidateId(), dto.getCommodityName(), DateUtils.formatToDDMMYYYY(dto.getExpirtyDate()), dto.getBuySellIndicator().toUpperCase()};
			getJdbcTemplate().update(query, params);
		}
		
	}

	@Override
	public List<WorkOrderVO> getTradedOrders(String orderType) {
		String query = "select wo.group_id as groupId, g.name as groupName, wo.cmdty_id as commodityId, c.name as commodityName, "
				+ " avg(wo.executed_amount) as tradedPrice, avg(executed_quantity) as tradeQuantity, avg(wo.executed_time) as executedTime "
				+ " from work_order wo, groups g, commodity c where wo.group_id = g.id and wo.cmdty_id = c.id "
				+ " and wo.executed_amount is not null and wo.order_type=? "
				+ " group by wo.group_id, wo.cmdty_id, wo.expiry_date";

		List<WorkOrderVO> ordersList = getJdbcTemplate().query(query, new Object[] {orderType}, new ResultSetExtractor<List<WorkOrderVO>>() {

			@Override
			public List<WorkOrderVO> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<WorkOrderVO> list = null;
				if (rs != null) {
					list = new ArrayList<WorkOrderVO>();
					while (rs.next()) {
						WorkOrderVO vo = new WorkOrderVO();
						vo.setGroupId(rs.getLong("groupId"));
						vo.setGroupName(rs.getString("groupName"));
						vo.setCommodityId(rs.getInt("commodityId"));
						vo.setCommodityName(rs.getString("commodityName"));
						vo.setExecutedAmount(rs.getFloat("tradedPrice"));
						vo.setExecutedQuantity(rs.getInt("tradeQuantity"));
						Calendar executedTime = DateUtils.getCalendar(rs.getTimestamp("executedTime"));
						DateUtils.trimToDate(executedTime);
						vo.setExecutedTime(executedTime);
						list.add(vo);
					}
				}
				return list;
			}
			
		});
		return ordersList;		
	}

	@Override
	public void saveNextWorkOrders(final List<WorkOrderVO> list) {
		String query = " insert into next_work_order (group_id, cmdty_id, order_type, candidate_id, order_quantity, order_amount, order_time, expiry_date, "
				+ " prev_sell_price, prev_sell_date, prev_sell_qty) "
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		if (list != null && !list.isEmpty()) {
			getJdbcTemplate().batchUpdate(query,
					new BatchPreparedStatementSetter() {

						public void setValues(PreparedStatement ps, int i)
								throws SQLException {
							WorkOrderVO vo = list.get(i);
							ps.setLong(1, vo.getGroupId());
							ps.setLong(2, vo.getCommodityId());
							ps.setString(3, vo.getOrderType());
							ps.setLong(4, vo.getCandidateId());
							ps.setInt(5, vo.getOrderQuantity());
							ps.setFloat(6, vo.getOrderAmount());
							ps.setTimestamp(7, DateUtils.getSqlTimeStamp(vo.getOrderTime()));
							ps.setTimestamp(8, DateUtils.getSqlTimeStamp(vo.getExpiryDate()));
							ps.setFloat(9, vo.getExecutedAmount());
							ps.setTimestamp(10, DateUtils.getSqlTimeStamp(vo.getPreviousSellDate()));
							ps.setInt(11, vo.getPreviousSellQty());
						}

						public int getBatchSize() {
							return list.size();
						}
					});
		}
	}

}
