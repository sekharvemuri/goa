package com.guru.order.service;

import java.io.Reader;
import java.util.List;

import com.guru.order.dto.GroupDTO;
import com.guru.order.dto.OrderConfirmationDTO;
import com.guru.order.dto.OrderDTO;

public interface OrderService {

	OrderDTO getNewOrder();
	void saveOrder(OrderDTO order);
	void saveOrderConfirmation(Reader reader)  throws Exception;
	List<GroupDTO> getCurrentOrders();
	void saveExecutedOrderByGroupName(List<OrderConfirmationDTO> executionsList);
}
