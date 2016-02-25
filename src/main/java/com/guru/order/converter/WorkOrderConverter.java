package com.guru.order.converter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.guru.order.data.vo.WorkOrderVO;
import com.guru.order.dto.GroupDTO;
import com.guru.order.dto.OrderDTO;
import com.guru.order.dto.OrderData;

public class WorkOrderConverter {

	public static List<WorkOrderVO> convertOrderDtoToWorkOrder(OrderDTO orderDto) {
		List<WorkOrderVO> list = null;
		if (orderDto != null) {
			Calendar todayCal = Calendar.getInstance();

			list = new ArrayList<WorkOrderVO>();

			for (GroupDTO groupDto : orderDto.getGroups()) {
				if (groupDto.getOrderData() != null) {
					for (OrderData orderData : groupDto.getOrderData()) {
						String[] candidates = groupDto.getUsers().split(",");
						for (String canId : candidates) {
							WorkOrderVO vo = new WorkOrderVO();
							vo.setGroupName(groupDto.getGroupName());
							vo.setCandidateId(Long.parseLong(canId.trim()));
							vo.setCommodityName(orderData.getCommodity().getName());
							vo.setOrderType(orderData.getOption());
							vo.setOrderAmount(orderData.getOrderValue());
							vo.setOrderQuantity(orderData.getQuantity());
							vo.setOrderTime(todayCal);
							vo.setExpiryDate(orderData.getExpiryDateAsDate());
							list.add(vo);
						}
					}
				}
			}
		}
		return list;
	}


}
