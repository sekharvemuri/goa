package com.guru.order.service.impl;

import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.guru.order.converter.CommodityConverter;
import com.guru.order.converter.WorkOrderConverter;
import com.guru.order.data.GroupsDao;
import com.guru.order.data.WorkOrderDao;
import com.guru.order.data.vo.CommodityVO;
import com.guru.order.data.vo.GroupVO;
import com.guru.order.data.vo.WorkOrderVO;
import com.guru.order.dto.CommodityDTO;
import com.guru.order.dto.GroupDTO;
import com.guru.order.dto.OrderConfirmationDTO;
import com.guru.order.dto.OrderDTO;
import com.guru.order.dto.OrderData;
import com.guru.order.service.CommodityService;
import com.guru.order.service.OrderService;
import com.guru.order.service.csv.CsvGenerator;
import com.guru.order.service.csv.CsvUtils;
import com.guru.order.service.excel.PriceWorkSheetGenerator;
import com.guru.order.service.mail.MailHelper;
import com.guru.order.utils.CollectionUtils;
import com.guru.order.utils.Constants;
import com.guru.order.utils.StringUtils;

@Component
public class OrderServiceImpl implements OrderService {

	@Autowired
	private CommodityService commodityService;
	@Autowired
	private GroupsDao groupsDao;
	@Autowired
	private WorkOrderDao workOrderDao;
	@Autowired
	private CsvGenerator csvGenerator;
	@Autowired
	private CsvUtils csvUtils;
	@Autowired
	private PriceWorkSheetGenerator priceWorkSheetGenerator;
	@Autowired
	private MailHelper mailHelper;

	public OrderDTO getNewOrder() {
		List<GroupDTO> groupDTOsList = new ArrayList<GroupDTO>();
		List<GroupVO> groupsList = groupsDao.getAllGroups();
		for (GroupVO groupVo : groupsList) {
			getGroupDtoByGroupName(groupVo.getName(), groupDTOsList);
		}
		
		List<WorkOrderVO> sellOrders = getSellOrders();
		List<WorkOrderVO> buyOrders = workOrderDao.getOrders(Constants.BUY_SELL_OPTION.BUY.getOptionType());

		parseIntoGroupDTO(sellOrders, groupDTOsList);
		parseIntoGroupDTO(buyOrders, groupDTOsList);
		mergeGroupsWithCommodities(groupDTOsList);
		mergeGroupsWithUserIDs(groupDTOsList);
		sortOrderData(groupDTOsList);
		
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setCommodities(this.commodityService.getCommodities());
		orderDTO.setGroups(groupDTOsList);
		return orderDTO;
	}

	private List<WorkOrderVO> getSellOrders() {
		List<WorkOrderVO> sellOrders = workOrderDao.getOrders(Constants.BUY_SELL_OPTION.SELL.getOptionType());
		List<WorkOrderVO> nextOrders = workOrderDao.getNextOrders();
		if (CollectionUtils.isNotEmpty(nextOrders)) {
			sellOrders.addAll(nextOrders);
		}
		return sellOrders;
	}

	private void sortOrderData(List<GroupDTO> groupDTOsList) {
		if (CollectionUtils.isNotEmpty(groupDTOsList)) {
			for(GroupDTO dto : groupDTOsList) {
				if (CollectionUtils.isNotEmpty(dto.getOrderData())) {
					Collections.sort(dto.getOrderData(), new Comparator<OrderData>() {

						@Override
						public int compare(OrderData o1, OrderData o2) {
							int compare = o1.getCommodity().getId() - o2.getCommodity().getId();
							if (compare == 0) {
								compare = o2.getOption().compareTo(o1.getOption());
								// TODO: compare based on expiry date
							}
							return compare;
						}
					});
				}
			}
		}
	}

	private void mergeGroupsWithUserIDs(List<GroupDTO> groupDTOsList) {
		if (CollectionUtils.isEmpty(groupDTOsList)) {
			return;
		}
		Map<String, List<String>> groupUsersMap = workOrderDao.getGroupUsers();
		for (GroupDTO dto : groupDTOsList) {
			List<String> usersList = groupUsersMap.get(dto.getGroupName());
			if (CollectionUtils.isEmpty(usersList)) {
				dto.setUsers("");
			}
			else {
				Collections.sort(usersList);
				dto.setUsers(StringUtils.join(usersList, ", "));
			}
		}
	}
	
	private void mergeGroupsWithCommodities(List<GroupDTO> groupDTOsList) {
		if (CollectionUtils.isNotEmpty(groupDTOsList)) {
			Map<String, List<CommodityVO>> groupCommoditiesMap = workOrderDao.getGroupCommodities();
			for (GroupDTO dto : groupDTOsList) {
				List<CommodityDTO> commoditiesDtoList = CommodityConverter.getCommodities(groupCommoditiesMap.get(dto.getGroupName()));
				Collections.sort(commoditiesDtoList);
				dto.setCommodities(commoditiesDtoList);
			}
		}
	}

	private void parseIntoGroupDTO(List<WorkOrderVO> sellOrders, List<GroupDTO> dtosList) {
		if (CollectionUtils.isNotEmpty(sellOrders)) {
			for (WorkOrderVO workOrderVO : sellOrders) {
				GroupDTO dto = getGroupDtoByGroupName(workOrderVO.getGroupName(), dtosList);
				OrderData orderData = buildOrderData(workOrderVO);
				dto.addOrderData(orderData);
			}
		}
	}

	private OrderData buildOrderData(WorkOrderVO workOrderVO) {
		OrderData orderData = new OrderData();
		CommodityDTO commodityDTO = new CommodityDTO(workOrderVO.getCommodityId(), workOrderVO.getCommodityName());
		orderData.setCommodity(commodityDTO);
		if (workOrderVO.getExpiryDate() != null) {
			orderData.setExpiryDate(workOrderVO.getExpiryDate().getTimeInMillis());
		}
		orderData.setWorkOrderId(workOrderVO.getId());
		orderData.setOption(workOrderVO.getOrderType());
		orderData.setQuantity(workOrderVO.getOrderQuantity());
		orderData.setOrderAverageValue(workOrderVO.getOrderAmount());
		orderData.setOrderValue(workOrderVO.getOrderAmount());
		return orderData;
	}

	private GroupDTO getGroupDtoByGroupName(String groupName,
			List<GroupDTO> dtosList) {
		GroupDTO groupDTO = null;
		if (CollectionUtils.isNotEmpty(dtosList)) {
			for(GroupDTO listItem : dtosList) {
				if (groupName.equals(listItem.getGroupName())) {
					groupDTO = listItem;
					break;
				}
			}
		}
		if (groupDTO == null) {
			groupDTO = new GroupDTO();
			groupDTO.setGroupName(groupName);
			dtosList.add(groupDTO);
		}
		return groupDTO;
	}

	@Override
	public void saveOrder(OrderDTO orderDto) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-hhmmss");
		String currentTime =sdf.format(new Date());
		
		try {
			saveWorkOdersInDB(orderDto);
			if (orderDto != null && CollectionUtils.isNotEmpty(orderDto.getGroups())) {
				String csvFilePath = "c:\\guruorder\\OrderSheet-" + currentTime + ".csv";
				this.csvGenerator.generateOrderSheet(orderDto, csvFilePath);
				
				String priceWorkSheetFilePath = "c:\\guruorder\\PriceWorkOutSheet-" + currentTime + ".xls";
				this.priceWorkSheetGenerator.generatePriceWorkOrderSheet(orderDto.getGroups(), priceWorkSheetFilePath);
				
				//mailHelper.sendMail(new String[] {csvFilePath, priceWorkSheetFilePath}, currentTime);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	private void saveWorkOdersInDB (OrderDTO orderDto) {
		if (orderDto != null && CollectionUtils.isNotEmpty(orderDto.getGroups())) {
			List<WorkOrderVO> voList = WorkOrderConverter.convertOrderDtoToWorkOrder(orderDto);
			this.workOrderDao.movePreviousOrdersToBackup();
			this.workOrderDao.saveWorkOrders(voList);
		}
	}

	@Override
	public void saveOrderConfirmation(Reader reader) throws Exception {
		List<OrderConfirmationDTO> list = csvUtils.getExecutionsList(reader);
		workOrderDao.saveOrderConfirmation(list);
	}

	@Override
	public List<GroupDTO> getCurrentOrders() {
		List<GroupDTO> groupDTOsList = new ArrayList<GroupDTO>();
		List<WorkOrderVO> sellOrders = workOrderDao.getOrders(Constants.BUY_SELL_OPTION.SELL.getOptionType());
		List<WorkOrderVO> buyOrders = workOrderDao.getOrders(Constants.BUY_SELL_OPTION.BUY.getOptionType());
		parseIntoGroupDTO(sellOrders, groupDTOsList);
		parseIntoGroupDTO(buyOrders, groupDTOsList);
		mergeGroupsWithCommodities(groupDTOsList);
		sortOrderData(groupDTOsList);
		return groupDTOsList;
	}

	@Override
	public void saveExecutedOrderByGroupName(List<OrderConfirmationDTO> executionsList) {
		List<OrderConfirmationDTO> list = new ArrayList<OrderConfirmationDTO>();
		for (OrderConfirmationDTO executedOrder : executionsList) {
			if (executedOrder.getUnitPrice() != null) {
				list.add(executedOrder);
			}
		}
		workOrderDao.saveExecutedOrderByGroupName(list);
	}
}
