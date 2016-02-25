package com.guru.order.service.impl;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.guru.order.data.GroupsDao;
import com.guru.order.data.WorkOrderDao;
import com.guru.order.data.vo.WorkOrderVO;
import com.guru.order.service.excel.WorkOrderGenerator;

@Component
public class WorkOrderServiceImpl {

	@Autowired
	private WorkOrderGenerator workOrderGenerator;
	@Autowired
	private GroupsDao groupsDao;
	@Autowired
	private WorkOrderDao workOrderDao;

	public void saveWorkOrders(InputStream fis) {
		Map<String, List<String>> groupCandidatesMap = workOrderGenerator
				.getGroupCandidates(null);
		groupsDao.saveGroupCandidates(groupCandidatesMap);

		List<WorkOrderVO> workOrdersList = workOrderGenerator
				.getWorkOrders(fis);

		for (WorkOrderVO vo : workOrdersList) {
			groupsDao.saveCommodity(vo.getCommodityName());
			groupsDao.saveGroupCommodity(vo.getGroupName(),
					vo.getCommodityName());
		}

		for (WorkOrderVO vo : workOrdersList) {
			System.out.println(vo.toString());
			workOrderDao.saveWorkOrders(vo);
		}
	}

}
