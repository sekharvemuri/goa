package com.guru.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.guru.order.data.vo.GroupVO;
import com.guru.order.dto.GroupDTO;

public class GroupConverter {

	public static List<GroupDTO> getGroups(List<GroupVO> list) {
		List<GroupDTO> dtosList = new ArrayList<GroupDTO>(list.size());
		for (GroupVO vo : list) {
			dtosList.add(getGroup(vo));
		}
		return dtosList;
	}

	private static GroupDTO getGroup(GroupVO vo) {
		return new GroupDTO(vo.getId(), vo.getName());
	}

}
