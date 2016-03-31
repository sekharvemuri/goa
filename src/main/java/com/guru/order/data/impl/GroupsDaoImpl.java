package com.guru.order.data.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.guru.order.data.GroupsDao;
import com.guru.order.data.vo.CandidateVO;
import com.guru.order.data.vo.CommodityVO;
import com.guru.order.data.vo.GroupCommodityVO;
import com.guru.order.data.vo.GroupVO;
import com.guru.order.data.vo.SubTypeVO;

@Component
public class GroupsDaoImpl extends BaseDao implements GroupsDao {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<GroupVO> getGroups() {
		String query = "select id, name from groups order by name";
		try {
			return ((List<GroupVO>) getJdbcTemplate().query(query, new BeanPropertyRowMapper(GroupVO.class)));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<GroupVO> getGroupsWithCandidates() {
		String query = "select id, name from groups g, group_candidates gc where g.id=gc.group_id group by id, name order by name";
		try {
			return ((List<GroupVO>) getJdbcTemplate().query(query, new BeanPropertyRowMapper(GroupVO.class)));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void addGroup(GroupVO group) {
		String query = "insert into groups (name) values (?)";
		try {
			getJdbcTemplate().update(query, new Object[] {group.getName()});
		} catch (Exception e) {
			System.out.println(String.format("Group already exists : %s", group.getName()));
		}
	}

	@Override
	public Collection<GroupVO> getGroupCommodities() {
		String query = "select g.id as groupId, g.name as groupName, c.id as commodityId, c.name as commodityName "
				+ " from groups g, commodity c, group_commodity gc where g.id=gc.group_id and c.id=gc.cmdty_id";
		return getJdbcTemplate().query(query, new ResultSetExtractor<Collection<GroupVO>>() {

			@Override
			public Collection<GroupVO> extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				Map<String, GroupVO> map = new HashMap<String, GroupVO>();
				while(rs.next()) {
					String groupName = rs.getString("groupName");
					GroupVO group = map.get(groupName);
					if (group == null) {
						int groupId = rs.getInt("groupId");
						group = new GroupVO(groupId, groupName);
						map.put(groupName, group);
					}
					group.addCommodity(new CommodityVO(rs.getInt("commodityId"), rs.getString("commodityName")));
				}
				return map.values();
			}
			
		});
	}

	@Override
	public Collection<GroupVO> getGroupCandidates() {
		String query = "select g.id as groupId, g.name as groupName, c.id as candidateId "
				+ " from groups g, candidates c, group_candidates gc where g.id=gc.group_id and c.id=gc.cand_id";
		return getJdbcTemplate().query(query, new ResultSetExtractor<Collection<GroupVO>>() {

			@Override
			public Collection<GroupVO> extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				Map<String, GroupVO> map = new HashMap<String, GroupVO>();
				while(rs.next()) {
					String groupName = rs.getString("groupName");
					GroupVO group = map.get(groupName);
					if (group == null) {
						int groupId = rs.getInt("groupId");
						group = new GroupVO(groupId, groupName);
						map.put(groupName, group);
					}
					group.addCandidate(new CandidateVO(rs.getLong("candidateId")));
				}
				return map.values();
			}
		});
	}

	@Transactional
	@Override
	public void configureCommodities(final GroupVO group) {
		getJdbcTemplate().update("delete from group_commodity where group_id=?", new Object[] {group.getId()});
		getJdbcTemplate().batchUpdate("insert into group_commodity (group_id, cmdty_id) values (?, ?)", new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, group.getId());
				ps.setInt(2, (group.getCommodities().get(i)).getId());
			}
			
			@Override
			public int getBatchSize() {
				return group.getCommodities().size();
			}
		});
	}

	@Override
	public void configureCandidates(final GroupVO group) {
		getJdbcTemplate().update("delete from group_candidates where group_id=?", new Object[] {group.getId()});
		getJdbcTemplate().batchUpdate("insert into group_candidates (group_id, cand_id) values (?, ?)", new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, group.getId());
				ps.setLong(2, (group.getCandidates().get(i)).getId());
			}
			
			@Override
			public int getBatchSize() {
				return group.getCommodities().size();
			}
		});
	}

	@Override
	public void saveGroupCandidates(Map<String, List<String>> groupCandidatesMap) {
		if (groupCandidatesMap == null || groupCandidatesMap.isEmpty()) {
			return;
		}
		
		Set<String> groupNameSet = groupCandidatesMap.keySet();
		Set<String> candidatesSet = new HashSet<String>();
		
		for (String groupName : groupNameSet) {
			List<String> candidatesList = groupCandidatesMap.get(groupName);
			candidatesSet.addAll(candidatesList);
		}
		
		// get groups available from DB
		//List<String> dbGroupNames = this.getGroupNames();
		
		// remove the db names from request names.
		//Set<String> nonDbGroups = CollectionUtil
		//groupNameSet.removeAll(dbGroupNames);

		// save what ever left, means, not exist in db.
		for (String groupName : groupNameSet) {
			this.addGroup(new GroupVO(groupName));
		}
		
		//List<String> dbCandidates = getCandidates();
		//candidatesSet.removeAll(dbCandidates);
		addCandidates(new ArrayList<String>(candidatesSet));
		
		groupNameSet = groupCandidatesMap.keySet();
		String query = "insert into group_candidates (group_id, cand_id) values ((select id from groups where NAME=?), ?)";
		for (String groupName : groupNameSet) {
			List<String> candidatesList = groupCandidatesMap.get(groupName);
			for (String candId : candidatesList) {
				try {
					getJdbcTemplate().update(query, new Object[] {groupName, Long.valueOf(candId)});
				} catch (Exception e) {
					System.out.println(String.format("Exception while inserting Group-Candidate(%s, %s)", groupName, candId));
				}
			}
		}
	}

	private void addCandidates(final List<String> candidateSet) {
		String query = "insert into candidates (id) values (?)";
		for (String candId : candidateSet) {
			try {
				getJdbcTemplate().update(query, new Object[] {Long.valueOf(candId)});
			} catch (Exception e) {
				System.out.println(String.format("Candidate already exists : %s", candId));
			}
		}
	}

	@Override
	public void saveGroupCommodity(String groupName, String commodityName) {
		String query = "insert group_commodity (group_id, cmdty_id) values ((select id from groups where NAME=?), (select id from commodity where NAME=?))";
		try {
			getJdbcTemplate().update(query, new Object[] {groupName, commodityName});
		} catch (Exception e) {
			System.out.println(String.format("Group-Commodity already exists : %s, %s", groupName, commodityName));
		}
	}

	@Override
	public void saveCommodity(String commodityName) {
		String query = "insert into commodity (NAME) values (?)";
		try {
			getJdbcTemplate().update(query, new Object[] {commodityName});
		} catch (Exception e) {
			System.out.println(String.format("Commodity already exists : %s", commodityName));
		}
	}

	@Override
	public List<Long> getCandidatesByGroupName(String groupName) {
		String query = "select c.id from candidates c, group_candidates gc, groups g where gc.group_id=g.id and c.id=gc.cand_id and g.name='" + groupName + "' ";
		return getJdbcTemplate().queryForList(query, Long.class);
	}

	@Override
	public SubTypeVO getSubType(int groupId) {
		String query = "select id, type_id as typeId, name from sub_types st, sub_type_groups stg where st.id=stg.sub_type_id and stg.group_id=?";
		return getJdbcTemplate().query(query, new Object[] {groupId}, new ResultSetExtractor<SubTypeVO>() {

			@Override
			public SubTypeVO extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				SubTypeVO subTypeVO = null;
				if (rs.next()) {
					subTypeVO = new SubTypeVO();
					subTypeVO.setId(rs.getInt("id"));
					subTypeVO.setTypeId(rs.getInt("typeId"));
					subTypeVO.setName(rs.getString("name"));
				}
				return subTypeVO;
			}
		});
	}

	@Override
	public List<Integer> getGroupsBySubTypeId(Integer subTypeId) {
		String query = "select group_id from sub_type_groups where sub_type_id=?";
		return getJdbcTemplate().queryForList(query, new Object[] {subTypeId}, Integer.class);
	}

	@Override
	public void saveCommodity(String commodityName, Calendar expiryDate) {
		String query = "insert into commodity_expiry_date (cmdt_id, expiry_date) values ("
				+ " (select id from commodity where name=?), ?)";
		Object[] params = new Object[] {commodityName, expiryDate.getTime()};
		try {
			getJdbcTemplate().update(query, params);
		} catch (Exception e) {
			System.out.println(String.format("commodity_expiry_date exists for %s, %s", commodityName, expiryDate.getTime()));
		}
	}

	@Override
	public GroupCommodityVO getGroupCommodityDetails(int groupId,
			int commodityId) {
		String query = "select group_id as groupId, cmdty_id as commodityId, sell_interval as sellInterval, "
				+ " buy_interval as buyInterval from group_commodity where group_id=? and cmdty_id=?";
		Object[] params = new Object[] {groupId, commodityId};
		try {
			return getJdbcTemplate().query(query, params, new ResultSetExtractor<GroupCommodityVO>() {

				@Override
				public GroupCommodityVO extractData(ResultSet rs)
						throws SQLException, DataAccessException {
					GroupCommodityVO vo = null;
					if (rs.next()) {
						vo = new GroupCommodityVO();
						vo.setGroupId(rs.getInt("groupId"));
						vo.setCommodityId(rs.getInt("commodityId"));
						vo.setSellInterval(rs.getFloat("sellInterval"));
						vo.setBuyInterval(rs.getFloat("buyInterval"));
					}
					return vo;
				}
				
			});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
