package com.guru.order.data;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.guru.order.data.vo.GroupCommodityVO;
import com.guru.order.data.vo.GroupVO;
import com.guru.order.data.vo.SubTypeVO;

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

	SubTypeVO getSubType(int groupId);

	List<Integer> getGroupsBySubTypeId(Integer subTypeId);

	void saveCommodity(String commodityName, Calendar expiryDate);

	GroupCommodityVO getGroupCommodityDetails(int groupId, int commodityId);

}
