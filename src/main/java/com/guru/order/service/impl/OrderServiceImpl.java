package com.guru.order.service.impl;

import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.guru.order.converter.CommodityConverter;
import com.guru.order.converter.WorkOrderConverter;
import com.guru.order.data.GroupsDao;
import com.guru.order.data.WorkOrderDao;
import com.guru.order.data.vo.CommodityVO;
import com.guru.order.data.vo.GroupVO;
import com.guru.order.data.vo.SubTypeVO;
import com.guru.order.data.vo.WorkOrderVO;
import com.guru.order.dto.CommodityDTO;
import com.guru.order.dto.GroupDTO;
import com.guru.order.dto.OrderConfirmationDTO;
import com.guru.order.dto.OrderDTO;
import com.guru.order.dto.OrderData;
import com.guru.order.service.CommodityService;
import com.guru.order.service.GoaProperties;
import com.guru.order.service.OrderService;
import com.guru.order.service.csv.CsvGenerator;
import com.guru.order.service.csv.CsvUtils;
import com.guru.order.service.excel.PriceWorkSheetGenerator;
import com.guru.order.service.mail.MailHelper;
import com.guru.order.utils.CollectionUtils;
import com.guru.order.utils.Constants;
import com.guru.order.utils.DateUtils;
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
	@Autowired
	private GoaProperties goaProperties;

	public OrderDTO getNewOrder() {
		System.out.println("SendMail:" + goaProperties.sendMail);
		List<GroupDTO> groupDTOsList = new ArrayList<GroupDTO>();
		List<GroupVO> groupsList = groupsDao.getGroupsWithCandidates();
		for (GroupVO groupVo : groupsList) {
			getGroupDtoByGroupName(groupVo.getName(), groupDTOsList);
		}

		List<WorkOrderVO> sellOrders = getSellOrders();
		List<WorkOrderVO> buyOrders = workOrderDao
				.getOrders(Constants.BUY_SELL_OPTION.BUY.getOptionType());

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
		List<WorkOrderVO> sellOrders = workOrderDao
				.getOrders(Constants.BUY_SELL_OPTION.SELL.getOptionType());
		List<WorkOrderVO> nextOrders = workOrderDao.getNextOrders();
		if (CollectionUtils.isNotEmpty(nextOrders)) {
			sellOrders.addAll(nextOrders);
		}
		return sellOrders;
	}

	private void sortOrderData(List<GroupDTO> groupDTOsList) {
		if (CollectionUtils.isNotEmpty(groupDTOsList)) {
			for (GroupDTO dto : groupDTOsList) {
				if (CollectionUtils.isNotEmpty(dto.getOrderData())) {
					Collections.sort(dto.getOrderData(),
							new Comparator<OrderData>() {

								@Override
								public int compare(OrderData o1, OrderData o2) {
									int compare = o1.getCommodity().getId()
											- o2.getCommodity().getId();
									if (compare == 0) {
										compare = o2.getOption().compareTo(
												o1.getOption());
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
			} else {
				Collections.sort(usersList);
				dto.setUsers(StringUtils.join(usersList, ", "));
			}
		}
	}

	private void mergeGroupsWithCommodities(List<GroupDTO> groupDTOsList) {
		if (CollectionUtils.isNotEmpty(groupDTOsList)) {
			Map<String, List<CommodityVO>> groupCommoditiesMap = workOrderDao
					.getGroupCommodities();
			for (GroupDTO dto : groupDTOsList) {
				List<CommodityDTO> commoditiesDtoList = CommodityConverter
						.getCommodities(groupCommoditiesMap.get(dto
								.getGroupName()));
				Collections.sort(commoditiesDtoList);
				dto.setCommodities(commoditiesDtoList);
			}
		}
	}

	private void parseIntoGroupDTO(List<WorkOrderVO> sellOrders,
			List<GroupDTO> dtosList) {
		if (CollectionUtils.isNotEmpty(sellOrders)) {
			for (WorkOrderVO workOrderVO : sellOrders) {
				GroupDTO dto = getGroupDtoByGroupName(
						workOrderVO.getGroupName(), dtosList);
				OrderData orderData = buildOrderData(workOrderVO);
				dto.addOrderData(orderData);
			}
		}
	}

	private OrderData buildOrderData(WorkOrderVO workOrderVO) {
		OrderData orderData = new OrderData();
		CommodityDTO commodityDTO = new CommodityDTO(
				workOrderVO.getCommodityId(), workOrderVO.getCommodityName());
		orderData.setCommodity(commodityDTO);
		if (workOrderVO.getExpiryDate() != null) {
			orderData.setExpiryDate(workOrderVO.getExpiryDate()
					.getTimeInMillis());
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
			for (GroupDTO listItem : dtosList) {
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
		String currentTime = sdf.format(new Date());

		try {
			saveWorkOdersInDB(orderDto);
			if (orderDto != null
					&& CollectionUtils.isNotEmpty(orderDto.getGroups())) {
				String csvFilePath = "c:\\guruorder\\OrderSheet-" + currentTime
						+ ".csv";
				this.csvGenerator.generateOrderSheet(orderDto, csvFilePath);

				String priceWorkSheetFilePath = "c:\\guruorder\\PriceWorkOutSheet-"
						+ currentTime + ".xls";
				this.priceWorkSheetGenerator.generatePriceWorkOrderSheet(
						orderDto.getGroups(), priceWorkSheetFilePath);

				// mailHelper.sendMail(new String[] {csvFilePath,
				// priceWorkSheetFilePath}, currentTime);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional
	private void saveWorkOdersInDB(OrderDTO orderDto) {
		if (orderDto != null
				&& CollectionUtils.isNotEmpty(orderDto.getGroups())) {
			List<WorkOrderVO> voList = WorkOrderConverter
					.convertOrderDtoToWorkOrder(orderDto);
			this.workOrderDao.movePreviousOrdersToBackup();
			this.workOrderDao.saveWorkOrders(voList);
		}
	}

	@Override
	public void saveOrderConfirmation(Reader reader) throws Exception {
		// convert the Reader to List of OrderConfirmationDTO
		List<OrderConfirmationDTO> tradesList = csvUtils
				.getExecutionsList(reader);

		// get the NewOrder from DB
		OrderDTO orderDto = getNewOrder();

		// for each Group in NewOrder iterate through each symbol & order type
		// check for each candidate exists in the confirmation list or not ?
		List<GroupDTO> groupDtosList = orderDto.getGroups();
		for (GroupDTO groupDto : groupDtosList) {
			// get users list from group
			String usersStr = groupDto.getUsers();
			String[] users = usersStr.split(",");

			// get all orders based
			List<OrderData> ordersList = groupDto.getOrderData();
			for (OrderData orderData : ordersList) {
				String commodityName = orderData.getCommodity().getName();
				String orderType = orderData.getOption();
				Map<String, List<OrderConfirmationDTO>> confirmOrdersMap = getTradedCandidates(
						tradesList, usersStr, commodityName, orderType,
						orderData.getExpiryDateAsDate());
				if (confirmOrdersMap.size() > 0) {
					Set<String> keySet = confirmOrdersMap.keySet();
					if (confirmOrdersMap.size() == 1) {
						String key = keySet.iterator().next();
						List<OrderConfirmationDTO> confirmOrdersList = confirmOrdersMap
								.get(key);
						if (users.length == confirmOrdersList.size()) {
							workOrderDao.saveTradedOrders(confirmOrdersList,
									groupDto.getGroupId(),
									groupDto.getGroupName());
							tradesList.removeAll(confirmOrdersList);
						}
					} else {
						// not happy path
						// example: multiple BUYs per group, symbol & expiry
						// date
					}
				}
			}

		}
	}

	// @Override
	// public void saveOrderConfirmation(Reader reader) throws Exception {
	// List<OrderConfirmationDTO> list = csvUtils.getExecutionsList(reader);
	// Map<String, Map<Long, List<OrderConfirmationDTO>>> map =
	// parseInToOrderTypeAndCandidate(list);
	// OrderDTO orderDto = getNewOrder();
	//
	// //Process SELL orders first
	// Map<Long, List<OrderConfirmationDTO>> sellOrdersMap = map.get("SELL");
	// for (Long candidateId : sellOrdersMap.keySet()) {
	// List<OrderConfirmationDTO> ordersList = sellOrdersMap.get(candidateId);
	// ordersList = sortOrdersByPrice(ordersList, "asc");
	//
	// for (OrderConfirmationDTO dto : ordersList) {
	// List<String> groupNamesLst = getGroupsContainsOrder(orderDto.getGroups(),
	// dto);
	//
	// // get each group and check for other users also have the same confirm
	// order or not.
	// // get the sub list from OrderDTO (new order).
	// // because in first group there is a chance of existing single user that
	// contain similar order
	//
	// }
	// }
	//
	// //Map<Long, Map<String, List<OrderConfirmationDTO>>> map =
	// parseInToCandidateAndOrderType(list);
	//
	// /*
	// * (120, Lead Mini, SELL, 60156, Group A)
	// * (120, Lead Mini, SELL, 60156, Group B)
	// *
	// * symbol wise, candidates,
	// *
	// Map<candidateID, Map<orderType, List<VO>>>
	//
	// each candidate can have multiple SELL, BUY orders.
	//
	// SELL orders - 1st lowest order will get executes.
	// for (SELL orders) {
	// get each vo (100, Lead Mini, SELL, 60156, Group A)
	// get list of records with candidateId, orderType, commodity from DB where
	// executed price is NULL, in order price ascending order //for SELL
	// get 1st db record (100, Lead Mini, SELL, 60156, 101, )
	// update DB
	//
	// }
	//
	//
	// Question : if both groups have same order price and only if one of the
	// order executed
	// Solution :
	//
	// * */
	//
	// workOrderDao.saveOrderConfirmation(map);
	// }

	private Map<String, List<OrderConfirmationDTO>> getTradedCandidates(
			List<OrderConfirmationDTO> tradesList, String users,
			String commodityName, String orderType, Calendar expiryDate) {
		Map<String, List<OrderConfirmationDTO>> map = new HashMap<String, List<OrderConfirmationDTO>>();
		List<OrderConfirmationDTO> list = null;
		String orderExpiryDateStr = DateUtils.formatToDDMMYYYY(expiryDate);
		for (OrderConfirmationDTO dto : tradesList) {
			String tradedCandId = String.valueOf(dto.getCandidateId());
			if (users.contains(tradedCandId)
					&& commodityName.equalsIgnoreCase(dto.getCommodityName())
					&& orderType.equalsIgnoreCase(dto.getBuySellIndicator())) {
				String tradeExpiryDateStr = DateUtils.formatToDDMMYYYY(dto
						.getExpirtyDate());
				if (orderExpiryDateStr.equalsIgnoreCase(tradeExpiryDateStr)) {
					String key = DateUtils.formatToDDMMYYYY(expiryDate
							.getTime());// + "," + dto.getUnitPrice();
					list = map.get(key);
					if (list == null) {
						list = new ArrayList<OrderConfirmationDTO>();
						map.put(key, list);
					}
					list.add(dto);
				}
			}
		}
		return map;
	}

	@Override
	public void createNextOrders() {
		List<WorkOrderVO> nextSellOrders = processNextBuyOrdersForTradedSellOrders();
		if (CollectionUtils.isNotEmpty(nextSellOrders)) {
			workOrderDao.saveNextWorkOrders(nextSellOrders);
		}
		
	}

	private List<WorkOrderVO> processNextBuyOrdersForTradedSellOrders() {
		List<WorkOrderVO> tradedList = workOrderDao.getTradedOrders("SELL");
		if (CollectionUtils.isEmpty(tradedList)) {
			return null;
		}
		
		Set<Integer> commodityIDsSet = getCommodityIDsFromWorkOrders(tradedList);
		Map<Integer, CommodityVO> commodityIntervalsMap = getCommodityIntervals(commodityIDsSet);

		//Set<Long> groupIds = getGroupIDsFromWorkOrders(tradedList);
		Map<String, List<String>> groupCandidatesMap = workOrderDao.getGroupUsers();
		
		List<WorkOrderVO> nextOrdersList = new ArrayList<WorkOrderVO>();
		for (WorkOrderVO tradedVo : tradedList) {
			Long groupId = tradedVo.getGroupId();
			if (validateIsGroupInSubType(groupId)) {
				generateNextBuyOrders(tradedVo, groupCandidatesMap.get(tradedVo.getGroupName()), 
						commodityIntervalsMap.get(tradedVo.getCommodityId()), nextOrdersList);
			} else {
				//TODO: for group-commodity-intervals
			}
		}
		return nextOrdersList;
	}
	
	

	private void generateNextBuyOrders(WorkOrderVO tradedVo, List<String> candidatesList,
			CommodityVO commodityVO, List<WorkOrderVO> nextOrdersList) {
		for(String candidateId : candidatesList) {
			WorkOrderVO nextOrder = new WorkOrderVO();
			nextOrder.setGroupId(tradedVo.getGroupId());
			nextOrder.setCommodityId(tradedVo.getCommodityId());
			nextOrder.setCandidateId(Long.valueOf(candidateId));
			nextOrder.setPreviousSellDate(tradedVo.getExecutedTime());
			nextOrder.setPreviousSellPrice(tradedVo.getExecutedAmount());
			nextOrder.setPreviousSellQty(tradedVo.getExecutedQuantity());
			nextOrder.setOrderType("BUY");
			nextOrder.setOrderQuantity(tradedVo.getExecutedQuantity());
			nextOrder.setOrderAmount(tradedVo.getExecutedAmount() - commodityVO.getSubInterval1());
			//TODO: need to set the expiry date
			nextOrdersList.add(nextOrder);
		}
	}

	private boolean validateIsGroupInSubType(Long groupId) {
		SubTypeVO subType = groupsDao.getSubType(groupId);
		return (subType == null) ? false : true;
	}

	private Map<Integer, CommodityVO> getCommodityIntervals(Set<Integer> commodityIDsSet) {
		return commodityService.getCommodityIntervals(commodityIDsSet);
	}
	
//	private Set<Long> getGroupIDsFromWorkOrders(List<WorkOrderVO> tradedList) {
//		Set<Long> groupIDsSet = new HashSet<Long>();
//		for (WorkOrderVO tradedVo : tradedList) {
//			groupIDsSet.add(tradedVo.getGroupId());
//		}
//		return groupIDsSet;
//	}

	private Set<Integer> getCommodityIDsFromWorkOrders(List<WorkOrderVO> tradedList) {
		Set<Integer> commodityIDsSet = new HashSet<Integer>();
		for (WorkOrderVO tradedVo : tradedList) {
			commodityIDsSet.add(tradedVo.getCommodityId());
		}
		return commodityIDsSet;
	}

}
