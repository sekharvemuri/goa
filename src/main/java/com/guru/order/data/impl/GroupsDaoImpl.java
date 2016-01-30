package com.guru.order.data.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.guru.order.data.GroupsDao;
import com.guru.order.data.vo.CandidateVO;
import com.guru.order.data.vo.CommodityVO;
import com.guru.order.data.vo.GroupVO;

@Component
public class GroupsDaoImpl extends BaseDao implements GroupsDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<GroupVO> getAllGroups() {
		String query = "select id, name from groups g, group_candidates gc where g.id=gc.group_id group by id, name";
		return ((List<GroupVO>) getJdbcTemplate().query(query, new BeanPropertyRowMapper(GroupVO.class)));
	}

	@Override
	public void addGroup(GroupVO group) {
		String query = "insert into groups (name) values (?)";
		getJdbcTemplate().update(query, new Object[] {group.getName()});
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
	
	
}
