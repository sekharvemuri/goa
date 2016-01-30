package com.guru.order.service;

import com.guru.order.dto.OrderDTO;

public interface OrderService {

	OrderDTO getNewOrder();

	void saveOrder(OrderDTO order);

}
