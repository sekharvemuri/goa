package com.guru.order.data;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.guru.order.data.vo.CommodityVO;
import com.guru.order.data.vo.GroupVO;
import com.guru.order.data.vo.RecentExecutionVO;
import com.guru.order.data.vo.WorkOrderVO;
import com.guru.order.dto.OrderConfirmationDTO;

public interface WorkOrderDao {

	void movePreviousOrdersToBackup();
	void saveWorkOrders(List<WorkOrderVO> list);
	Calendar getRecentOrderTime(String orderType);
	List<WorkOrderVO> getOrders(String orderType/*, Calendar orderTime*/);
	List<WorkOrderVO> getNextOrders();
	Map<String, List<String>> getGroupUsers();
	Map<String, List<CommodityVO>> getGroupCommodities();
	List<GroupVO> getAdHocGroups();
	List<RecentExecutionVO> getRecentExecutions();
	void saveOrderConfirmation(List<OrderConfirmationDTO> list);
	void saveExecutedOrderByGroupName(List<OrderConfirmationDTO> list);
	
}
