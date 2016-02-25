package com.guru.order.data;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.guru.order.data.vo.GroupVO;

public interface GroupsDao {
	
	List<GroupVO> getGroups();
	
	List<GroupVO> getGroupsWithCandidates();
	
	void addGroup(GroupVO group);
	
	Collection<GroupVO> getGroupCommodities();
	
	Collection<GroupVO> getGroupCandidates();
	
	void configureCommodities(GroupVO group);
	
	void configureCandidates(GroupVO group);

	void saveGroupCandidates(Map<String, List<String>> groupCandidatesMap);

	void saveCommodity(String commodityName);

	void saveGroupCommodity(String groupName, String commodityName);

	List<Long> getCandidatesByGroupName(String groupName);

}
