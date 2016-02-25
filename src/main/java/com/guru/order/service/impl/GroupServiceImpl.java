package com.guru.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.guru.order.converter.GroupConverter;
import com.guru.order.data.GroupsDao;
import com.guru.order.data.vo.GroupVO;
import com.guru.order.dto.GroupDTO;
import com.guru.order.service.GoaProperties;
import com.guru.order.service.GroupService;

@Component
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupsDao groupDao;
	@Autowired
	private GoaProperties goaProperties;

	@Override
	public List<GroupDTO> getGroups() {
		List<GroupVO> list = null;
		list = groupDao.getGroups();
		return GroupConverter.getGroups(list);
	}

}
