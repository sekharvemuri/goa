/**
 * 
 */
package com.guru.order.app.resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.guru.order.dto.ComodityDTO;
import com.guru.order.dto.ExpiryDateDTO;
import com.guru.order.dto.GroupDTO;
import com.guru.order.dto.OrderDTO;
import com.guru.order.dto.OrderData;
import com.guru.order.service.OrderService;

/**
 * @author RPILLARISETT
 * 
 */
@Component
@Path("order")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class OrderResourceImpl {

	OrderService orderService;

	/**
	 * http://localhost:8080/<appname>/rest/order/place/orderone
	 * 
	 * @param order
	 * @return
	 */

	@GET
	@Path("/place/{order}")
	public Response placeOrder(@PathParam("order") String ordernum) {
		String result = "Your placed order for order number :: " + ordernum;
		return Response.status(Status.OK).entity(result).build();
	}

	@POST
	@Path("/place")
	public Response placeOrderDto(OrderDTO order) {
		System.out.println("order recieved");
		for(GroupDTO group: order.getGroups()){
			for(OrderData orderData : group.getOrderData()){
				System.out.println(group.getGroupName()+" : "+orderData.getComodity().getComodityName());
			}
		}
		this.getOrderService().saveOrder(order);
		return Response.status(Status.OK).build();
	}

	/**
	 * http://localhost:8080/<appname>/rest/order/get?orderid=1
	 * 
	 * @param orderId
	 * @return
	 */

	@GET
	@Path("/get")
	public OrderDTO getOrder(@QueryParam("orderid") Integer orderId) {
		OrderDTO orderDTO = new OrderDTO();
		
		List<GroupDTO> groups = this.getGroups();
		orderDTO.setGroups(groups);
		
		java.util.List<ComodityDTO> comodities = new ArrayList<ComodityDTO>(0);
		ComodityDTO comodityDTO = new ComodityDTO();
		comodityDTO.setComodityId(1);
		comodityDTO.setComodityName("Zinc");
		comodities.add(comodityDTO);
		comodityDTO = new ComodityDTO();
		comodityDTO.setComodityId(2);
		comodityDTO.setComodityName("Copper");
		comodities.add(comodityDTO);
		comodityDTO = new ComodityDTO();
		comodityDTO.setComodityId(3);
		comodityDTO.setComodityName("Lead");
		comodities.add(comodityDTO);
		orderDTO.setComodities(comodities);
		
		List<String> previousOrderTimeList = new ArrayList<String>();
		String[] previousOrderTimestamps = {"1440650753000", "1443066953000", "1446094553000"};
		Collections.addAll(previousOrderTimeList, previousOrderTimestamps);
		orderDTO.setPreviousOrderTimes(previousOrderTimeList);
		
		List<ExpiryDateDTO> expiryDateList = new ArrayList<ExpiryDateDTO>();
		ExpiryDateDTO expiryDTO = new ExpiryDateDTO();
		expiryDTO.setExpiryDate(new Date());
		expiryDTO.setExpiryDateId(1);
		expiryDTO.setExpiryDateInMillis(System.currentTimeMillis());
		expiryDateList.add(expiryDTO);
		orderDTO.setExpiryDates(expiryDateList);
		
		return orderDTO;
	}
	
	public List<GroupDTO> getGroups(){
		List<GroupDTO> groups = new ArrayList<GroupDTO>();
		
		GroupDTO group1 = new GroupDTO();
		group1.setGroupName("Group 1");
		group1.setUsers("1978, 1878, 1838, 15435");
		List<OrderData> orders1 = new ArrayList<OrderData>();
		group1.setOrderData(orders1);
		groups.add(group1);
		
		GroupDTO group2 = new GroupDTO();
		group2.setGroupName("Group 2");
		group2.setUsers("1978, 1878, 1838, 15435");
		List<OrderData> orders2 = new ArrayList<OrderData>();
		OrderData orderData1 = new OrderData();
		ComodityDTO comodity = new ComodityDTO();
		comodity.setComodityId(1);
		comodity.setComodityName("Zinc");
		orderData1.setComodity(comodity);
		long timestamp = 1449887687;
		orderData1.setExpiryDate(new Long(timestamp));
		orderData1.setOrderAverageValue(new Float(240.5));
		orderData1.setOrderValue(new Float(300.0));
		orderData1.setQuantity(99);
		orderData1.setSellBuyOption("BUY");
		orders2.add(orderData1);
		
		OrderData orderData2 = new OrderData();
		ComodityDTO comodity1 = new ComodityDTO();
		comodity1.setComodityId(2);
		comodity1.setComodityName("Copper");
		orderData2.setComodity(comodity1);
		long timestamp1 = 1449887687;
		orderData2.setExpiryDate(new Long(timestamp1));
		orderData2.setOrderAverageValue(new Float(240.5));
		orderData2.setOrderValue(new Float(300.0));
		orderData2.setQuantity(99);
		orderData2.setSellBuyOption("BUY");
		orders2.add(orderData2);
		
		group2.setOrderData(orders2);
		groups.add(group2);
		
		return groups;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	@Autowired
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

}
