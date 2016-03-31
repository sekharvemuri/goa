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
import com.guru.order.data.vo.GroupCommodityVO;
import com.guru.order.data.vo.GroupVO;
import com.guru.order.data.vo.RecentTradedSubTypeVO;
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
			getGroupDtoByGroupName(groupVo.getId(), groupVo.getName(), groupDTOsList);
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
		orderDTO.setCommodities(this.commodityService.getCommoditiesWithExpiryDates());
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
										compare = o2.getOrderType().compareTo(
												o1.getOrderType());
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
				GroupDTO dto = getGroupDtoByGroupName(workOrderVO.getGroupId(),
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
			orderData.setExpiryDate(DateUtils.formatToDDMMMYY(workOrderVO.getExpiryDate().getTime()));
		}
		orderData.setWorkOrderId(workOrderVO.getId());
		orderData.setOrderType(workOrderVO.getOrderType());
		orderData.setQuantity(workOrderVO.getOrderQuantity());
		orderData.setOrderPrice(workOrderVO.getOrderAmount());
		return orderData;
	}

	private GroupDTO getGroupDtoByGroupName(int groupId, String groupName,
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
			groupDTO = new GroupDTO(groupId, groupName);
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
	public void saveTradedOrders(Reader reader) throws Exception {
		// convert the Reader to List of OrderConfirmationDTO
		List<OrderConfirmationDTO> tradesList = csvUtils
				.getExecutionsList(reader);
		
		List<OrderConfirmationDTO> buyTradedList = filterTradedListByOrderType(tradesList, "BUY");
		List<OrderConfirmationDTO> sellTradedList = filterTradedListByOrderType(tradesList, "SELL");

		// get the NewOrder from DB
		OrderDTO orderDto = getNewOrder();
		
		// initialize recentTradedSubTypes;
		List<RecentTradedSubTypeVO> recentTradedSubTypes = new ArrayList<RecentTradedSubTypeVO>();
		Calendar tradedTime = Calendar.getInstance();

		// for each Group in NewOrder iterate through each symbol & order type
		// check for each candidate exists in the confirmation list or not ?
		List<GroupDTO> groupDtosList = orderDto.getGroups();
		for (GroupDTO groupDto : groupDtosList) {
			// get all orders based
			if (CollectionUtils.isNotEmpty(buyTradedList)) {
				List<OrderData> buyOrdersList = filterByOrderType(groupDto.getOrderData(), "BUY");
				processTradedOrders(buyTradedList, recentTradedSubTypes, tradedTime,
						groupDto, buyOrdersList);	
			}
			
			if (CollectionUtils.isNotEmpty(sellTradedList)) {
				List<OrderData> sellOrdersList = filterByOrderType(groupDto.getOrderData(), "SELL");
				processTradedOrders(sellTradedList, recentTradedSubTypes, tradedTime,
						groupDto, sellOrdersList);	
			}
		}
		
		processMiscTradedItems(buyTradedList, tradedTime);
		processMiscTradedItems(sellTradedList, tradedTime);
		
		updateRecentTradedSubTypes(recentTradedSubTypes);
	}

	private void processMiscTradedItems(List<OrderConfirmationDTO> tradedList, Calendar tradedTime) {
		if (CollectionUtils.isEmpty(tradedList)) {
			return;
		}
		workOrderDao.saveMiscTradedItems(tradedList, tradedTime);
	}

	private void processTradedOrders(List<OrderConfirmationDTO> tradesList,
			List<RecentTradedSubTypeVO> recentTradedSubTypes,
			Calendar tradedTime, GroupDTO groupDto, List<OrderData> ordersList) {
		if (CollectionUtils.isEmpty(tradesList) || CollectionUtils.isEmpty(ordersList)) {
			return;
		}
		// get users list from group
		String usersStr = groupDto.getUsers();
		String[] users = usersStr.split(",");
		
		for (OrderData orderData : ordersList) {
			String commodityName = orderData.getCommodity().getName();
			Map<String, List<OrderConfirmationDTO>> confirmOrdersMap = getTradedCandidates(
					tradesList, usersStr, commodityName, orderData);
			if (confirmOrdersMap.size() > 0) {
				Set<String> keySet = confirmOrdersMap.keySet();
				if (confirmOrdersMap.size() == 1) {
					String key = keySet.iterator().next();
					List<OrderConfirmationDTO> confirmOrdersList = confirmOrdersMap
							.get(key);
					if (users.length == confirmOrdersList.size()) {
						workOrderDao.saveTradedOrders(confirmOrdersList);
						tradesList.removeAll(confirmOrdersList);
					}
				} else {
					// not happy path
					// example: multiple BUYs per group, symbol & expiry
					// date
				}
				
				SubTypeVO subTypeVO = groupsDao.getSubType(groupDto.getGroupId());
				RecentTradedSubTypeVO recentTradedSubType = new RecentTradedSubTypeVO();
				if (subTypeVO != null) {
					recentTradedSubType.setSubTypeId(subTypeVO.getId());
				}
				recentTradedSubType.setGroupId(groupDto.getGroupId());
				recentTradedSubType.setCommodity(new CommodityVO(commodityName));
				recentTradedSubType.setRecentExecDate(tradedTime);
				recentTradedSubTypes.add(recentTradedSubType);
			}
		}
	}

	private List<OrderData> filterByOrderType(List<OrderData> ordersList,
			final String orderType) {
		List<OrderData> subList = new ArrayList<OrderData>();
		for (OrderData orderData : ordersList) {
			if (orderType.equalsIgnoreCase(orderData.getOrderType())) {
				subList.add(orderData);
			}
		}
		Collections.sort(subList, new Comparator<OrderData>() {

			@Override
			public int compare(OrderData o1, OrderData o2) {
				int value = 0;
				if ("BUY".equalsIgnoreCase(orderType)) {
					value = new Float(o2.getOrderPrice() - o1.getOrderPrice()).intValue();
				} else {
					value = new Float(o2.getOrderPrice() - o2.getOrderPrice()).intValue();
				}
				return value;
			}
		});
		return subList;
	}
	
	private List<OrderConfirmationDTO> filterTradedListByOrderType(
			List<OrderConfirmationDTO> tradesList, final String orderType) {
		List<OrderConfirmationDTO> subList = new ArrayList<OrderConfirmationDTO>();
		for (OrderConfirmationDTO dto : tradesList) {
			if (orderType.equalsIgnoreCase(dto.getBuySellIndicator())) {
				subList.add(dto);
			}
		}
		Collections.sort(subList, new Comparator<OrderConfirmationDTO>() {

			@Override
			public int compare(OrderConfirmationDTO o1, OrderConfirmationDTO o2) {
				int value = 0;
				if ("BUY".equalsIgnoreCase(orderType)) {
					value = new Float(o2.getUnitPrice() - o1.getUnitPrice()).intValue();
				} else {
					value = new Float(o2.getUnitPrice() - o2.getUnitPrice()).intValue();
				}
				return value;
			}
		});
		return subList;
	}

	private void updateRecentTradedSubTypes(List<RecentTradedSubTypeVO> recentTradedSubTypes) {
		// this should be done only for BUY trades.
		workOrderDao.updateRecentTradedSubTypes(recentTradedSubTypes);
	}

	private Map<String, List<OrderConfirmationDTO>> getTradedCandidates(
			List<OrderConfirmationDTO> tradesList, String users,
			String commodityName, OrderData orderData) {
		Map<String, List<OrderConfirmationDTO>> map = new HashMap<String, List<OrderConfirmationDTO>>();
		List<OrderConfirmationDTO> list = null;
		Calendar expiryDate = DateUtils.getCalendarAsddMMMyy(orderData.getExpiryDate());
		String orderExpiryDateStr = DateUtils.formatToDDMMYYYY(expiryDate);
		for (OrderConfirmationDTO dto : tradesList) {
			String tradedCandId = String.valueOf(dto.getCandidateId());
			if (users.contains(tradedCandId)
					&& commodityName.equalsIgnoreCase(dto.getCommodityName())
					&& orderData.getOrderType().equalsIgnoreCase(dto.getBuySellIndicator())) {
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
					dto.setOrderPrice(orderData.getOrderPrice());
					list.add(dto);
				}
			}
		}
		return map;
	}

	@Override
	public void createNextOrders() {
		List<WorkOrderVO> nextOrdersList = new ArrayList<WorkOrderVO>();
		processNextBuyOrdersForSellTrades(nextOrdersList);
		processNextSellOrdersForBuyTrades(nextOrdersList);
		if (CollectionUtils.isNotEmpty(nextOrdersList)) {
			workOrderDao.saveNextWorkOrders(nextOrdersList);
		}
	}

	private List<WorkOrderVO> processNextBuyOrdersForSellTrades(List<WorkOrderVO> nextOrdersList) {
		List<WorkOrderVO> tradedList = workOrderDao.getTradedOrders("SELL");
		if (CollectionUtils.isEmpty(tradedList)) {
			return null;
		}
		
		Set<Integer> commodityIDsSet = getCommodityIDsFromWorkOrders(tradedList);
		Map<Integer, CommodityVO> commodityIntervalsMap = getCommodityIntervals(commodityIDsSet);

		//Set<Long> groupIds = getGroupIDsFromWorkOrders(tradedList);
		Map<String, List<String>> groupCandidatesMap = workOrderDao.getGroupUsers();
		
		for (WorkOrderVO tradedVo : tradedList) {
			int groupId = tradedVo.getGroupId();
			if (validateIsGroupInSubType(groupId)) {
				CommodityVO commodityVO = commodityIntervalsMap.get(tradedVo.getCommodityId());
				generateNextOrders(tradedVo, groupCandidatesMap.get(tradedVo.getGroupName()), 
						commodityVO.getSubInterval1(), "BUY", nextOrdersList);
			} else {
				// get Group-Commodity BUY interval
				GroupCommodityVO groupCommodityVO = groupsDao.getGroupCommodityDetails(groupId, tradedVo.getCommodityId());
				if (groupCommodityVO != null) {
					generateNextOrders(tradedVo, groupCandidatesMap.get(tradedVo.getGroupName()), 
							groupCommodityVO.getBuyInterval(), "BUY", nextOrdersList);
				}
			}
		}
		return nextOrdersList;
	}

	private void generateNextOrders(WorkOrderVO tradedVo, List<String> candidatesList,
			float interval, String orderType, List<WorkOrderVO> nextOrdersList) {
		if (interval <= 0.0) {
			return;
		}
		for(String candidateId : candidatesList) {
			WorkOrderVO nextOrder = new WorkOrderVO();
			nextOrder.setGroupId(tradedVo.getGroupId());
			nextOrder.setCommodityId(tradedVo.getCommodityId());
			nextOrder.setCandidateId(Long.valueOf(candidateId));
			nextOrder.setPreviousSellDate(tradedVo.getExecutedTime());
			nextOrder.setPreviousSellPrice(tradedVo.getExecutedAmount());
			nextOrder.setPreviousSellQty(tradedVo.getExecutedQuantity());
			nextOrder.setOrderType(orderType);
			nextOrder.setOrderQuantity(tradedVo.getExecutedQuantity());
			float newOrderValue = 0;
			if ("BUY".equalsIgnoreCase(orderType)) {
				newOrderValue = tradedVo.getExecutedAmount() - interval;
			} else if ("SELL".equalsIgnoreCase(orderType)) {
				newOrderValue = tradedVo.getExecutedAmount() + interval;
			}
			//TODO: upto .2 decimals only 
			// 1st decimal should be nearing to 5
			// 2nd decimal should be rounded to 0. 
			nextOrder.setOrderAmount(newOrderValue);
			nextOrder.setExpiryDate(tradedVo.getExpiryDate());
			nextOrdersList.add(nextOrder);
		}
	}

	private boolean validateIsGroupInSubType(int groupId) {
		SubTypeVO subType = groupsDao.getSubType(groupId);
		return (subType == null) ? false : true;
	}

	private Map<Integer, CommodityVO> getCommodityIntervals(Set<Integer> commodityIDsSet) {
		return commodityService.getCommodityIntervals(commodityIDsSet);
	}
	
	private Set<Integer> getCommodityIDsFromWorkOrders(List<WorkOrderVO> tradedList) {
		Set<Integer> commodityIDsSet = new HashSet<Integer>();
		for (WorkOrderVO tradedVo : tradedList) {
			commodityIDsSet.add(tradedVo.getCommodityId());
		}
		return commodityIDsSet;
	}
	
	private void processNextSellOrdersForBuyTrades(List<WorkOrderVO> nextOrdersList) {
		List<WorkOrderVO> tradedList = workOrderDao.getTradedOrders("BUY");
		if (CollectionUtils.isEmpty(tradedList)) {
			return;
		}
		
		Set<Integer> commodityIDsSet = getCommodityIDsFromWorkOrders(tradedList);
		Map<Integer, CommodityVO> commodityIntervalsMap = getCommodityIntervals(commodityIDsSet);
		Map<String, List<String>> groupCandidatesMap = workOrderDao.getGroupUsers();
		
		//TODO: get the commodity families and all commodities in that family.
		// in Map<familyName, List<CommodityVO>> which includes intervals too
		
		for (WorkOrderVO tradedVo : tradedList) {
			int groupId = tradedVo.getGroupId();
			SubTypeVO subType = groupsDao.getSubType(groupId);
			if (subType != null) {
				// generate next SELL order for traded BUY order
				// interval should be picked from COMMODITY.1st-Main-Interval
				int commodityId = tradedVo.getCommodityId();
				CommodityVO commodityVO = commodityIntervalsMap.get(commodityId);
				generateNextOrders(tradedVo, groupCandidatesMap.get(tradedVo.getGroupName()), commodityVO.getMainInterval(), "SELL", nextOrdersList);
				
				// based on subTypeId, get other subTypes which are under this Type
				List<Integer> subTypeIdsList = workOrderDao.getAllSubTypeIdsByGroupId(groupId);
				
				// the order should be descending order of recent executed subType
				List<Integer> recentTradedSubTypesList = workOrderDao.getRecentTradedSubTypes(subTypeIdsList, groupId, commodityId);
				
				// get the 1st sub type in the descending order
				if (CollectionUtils.isNotEmpty(recentTradedSubTypesList)) {
					int iter = 0;
					for (Integer tradedSubTypeId : recentTradedSubTypesList) {
						// get the groups in that sub type.
						List<Integer> groupsListBySubType = groupsDao.getGroupsBySubTypeId(tradedSubTypeId);
						Map<Integer, Integer> groupCommodityIdMap = workOrderDao.getGroupCommodityIdsMap(tradedVo.getCommodityFamilyId());
						
						// iterate through the groups.
						for (Integer groupIdBySubType : groupsListBySubType) {
							// if mapping between group and any of the commodity in that family exists.

							// check for any SELL positions for the Group-Commodity
							if (groupCommodityIdMap.get(groupIdBySubType) != null) {
								// if not there, place the SELL order.
								Float interval = (iter == 0) ? commodityVO.getSubInterval2() : commodityVO.getSubInterval3();
								generateNextOrders(tradedVo, groupCandidatesMap.get(tradedVo.getGroupName()), interval, "SELL", nextOrdersList);
							}
						}
						
						iter++;
					}
				}
			} else {
				// generate next SELL order for traded BUY order
				// interval should be picked from GROUP-COMMODITY combination.
				GroupCommodityVO groupCommodityVO = groupsDao.getGroupCommodityDetails(groupId, tradedVo.getCommodityId());
				if (groupCommodityVO != null) {
					generateNextOrders(tradedVo, groupCandidatesMap.get(tradedVo.getGroupName()), 
							groupCommodityVO.getSellInterval(), "SELL", nextOrdersList);
				}
				
			}
		}
	}

}
