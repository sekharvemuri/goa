package com.guru.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.guru.order.dto.OrderDTO;
import com.guru.order.service.CsvGenerator;
import com.guru.order.service.OrderService;

@Component
public class OrderServiceImpl implements OrderService {

	private CsvGenerator csvGenerator;

	@Override
	public OrderDTO getNewOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrder(OrderDTO order) {
		if (order == null || order.getGroups() == null) {
			return;
		}

		// TODO: Saving in DB

		this.getCsvGenerator().generateOrderSheet(order);
	}

	public CsvGenerator getCsvGenerator() {
		return csvGenerator;
	}

	@Autowired
	public void setCsvGenerator(CsvGenerator csvGenerator) {
		this.csvGenerator = csvGenerator;
	}

}
