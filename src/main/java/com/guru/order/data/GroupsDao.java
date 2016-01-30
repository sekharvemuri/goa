package com.guru.order.data;

import java.util.Collection;
import java.util.List;

import com.guru.order.data.vo.GroupVO;

public interface GroupsDao {
	
	List<GroupVO> getAllGroups();
	
	void addGroup(GroupVO group);
	
	Collection<GroupVO> getGroupCommodities();
	
	Collection<GroupVO> getGroupCandidates();
	
	void configureCommodities(GroupVO group);
	
	void configureCandidates(GroupVO group);

}
