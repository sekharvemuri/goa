package com.guru.order.service;

import java.io.Reader;

import com.guru.order.dto.OrderDTO;

public interface OrderService {

	OrderDTO getNewOrder();

	void saveOrder(OrderDTO order);

	void saveTradedOrders(Reader reader) throws Exception;

	void createNextOrders();

}
