package com.guru.order.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import au.com.bytecode.opencsv.CSVWriter;

import com.guru.order.dto.GroupDTO;
import com.guru.order.dto.OrderDTO;
import com.guru.order.dto.OrderData;
import com.guru.order.utils.Constants;

@Component
public class CsvGenerator {

	private CSVWriter writer;
	private List<String[]> sellData = new ArrayList<String[]>();
	private List<String[]> buyData = new ArrayList<String[]>();
	
	public void generateOrderSheet(OrderDTO order) {
		parseOrderData(order);
		
		try {
			initializeWriter();
			
			// Sell option
			addHeader();
			writer.writeAll(sellData);
			
			addEmptyRow();
			addEmptyRow();
			addEmptyRow();
			
			// Buy option
			addHeader();
			writer.writeAll(buyData);
			
			saveData();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	private void parseOrderData(OrderDTO order) {
		if (order == null || order.getGroups() == null) {
			return;
		}
		
		int sellRefNum = 0;
		int buyRefNum = 0;
		
		for (GroupDTO group : order.getGroups()) {
			if (group.getOrderData() != null) {
				for (OrderData orderData : group.getOrderData()) {
					String[] candidates = group.getUsers().split(",");
					if (Constants.BUY_SELL_OPTION.BUY.getOptionType().equals(orderData.getSellBuyOption())) {
						for (String canId : candidates) {
							buyData.add(new String[] {String.valueOf(++buyRefNum),"O","U","1","0",String.valueOf(Constants.BUY_SELL_OPTION.BUY.getOption()),"Instrument Name",orderData.getComodity().getComodityId().toString(),"Expiry Date","","","1","","","",orderData.getOrderValue().toString(),"",orderData.getQuantity().toString(),"","","",canId,"Product Type","",group.getGroupName(),""});
						}
					}
					else {
						for (String canId : candidates) {
							sellData.add(new String[] {String.valueOf(++sellRefNum),"O","U","1","0",String.valueOf(Constants.BUY_SELL_OPTION.SELL.getOption()),"Instrument Name",orderData.getComodity().getComodityId().toString(),"Expiry Date","","","1","","","",orderData.getOrderValue().toString(),"",orderData.getQuantity().toString(),"","","",canId,"Product Type","",group.getGroupName(),""});
						}
					}
				}
			}
		}
	}

	private void saveData() throws IOException {
		writer.flush();
		writer.close();
	}
	
	private void addEmptyRow() {
		writer.writeNext(new String[] {"","","","","","","","","","","","","","","","","","","","","","","","","",""});
	}

	private void addHeader() {
		writer.writeNext(new String[] {"Reference No.","Open/Close","Cover/UnCover","Book Type","MIT Type","Buy/Sell","Instrument Name","Symbol","Expiry Date","Strike Price","Option Type","Instruction","Good Till Date/Days","Special Terms","MF Qty","Price","Trigger Price","Quantity","Disclosed Qty.","Participant","Pro/Client","a/c No.","Product Type","Remarks","Group ID","Protection %"});
	}

	private void initializeWriter() throws IOException {
		writer = new CSVWriter(new FileWriter(new File("c:\\guruorder\\OrderSheet.csv")), CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER);
	}
	
	public static void main(String[] args) {
		CsvGenerator gen = new CsvGenerator();
		gen.generateOrderSheet(getOrder());
	}

	private static OrderDTO getOrder() {
		OrderDTO order = new OrderDTO();
		order.setGroups(getGroups());
		return order;
	}

	private static List<GroupDTO> getGroups() {
		List<GroupDTO> groups = new ArrayList<GroupDTO>();
		GroupDTO group = null;
		
		group = new GroupDTO();
		group.setGroupName("A");
		group.setUsers("90129");
		group.setOrderData(getGroupA_OrderDataList());
		groups.add(group);
		
		group = new GroupDTO();
		group.setGroupName("B");
		group.setUsers("69757,90140,90141,90142,90127");
		group.setOrderData(getGroupB_OrderDataList());
		groups.add(group);
		
		return groups;
	}
	
	private static List<OrderData> getGroupB_OrderDataList() {
		List<OrderData> list = new ArrayList<OrderData>();
		OrderData data = null;
		
		data = new OrderData();
		/*data.setComodityId("COPPERM");*/
		data.setOrderValue(368.20f);
		data.setSellBuyOption(Constants.BUY_SELL_OPTION.SELL.getOptionType());
		data.setQuantity(1);
		list.add(data);
		
		data = new OrderData();
		/*data.setComodityId("COPPERM");*/
		data.setOrderValue(340.20f);
		data.setSellBuyOption(Constants.BUY_SELL_OPTION.BUY.getOptionType());
		data.setQuantity(1);
		list.add(data);
		
		data = new OrderData();
		/*data.setComodityId("COPPERM");*/
		data.setOrderValue(333.20f);
		data.setSellBuyOption(Constants.BUY_SELL_OPTION.BUY.getOptionType());
		data.setQuantity(1);
		list.add(data);
		
		data = new OrderData();
		/*data.setComodityId("LEADMINI");*/
		data.setOrderValue(120.20f);
		data.setSellBuyOption(Constants.BUY_SELL_OPTION.SELL.getOptionType());
		data.setQuantity(1);
		list.add(data);
		
		data = new OrderData();
		/*data.setComodityId("LEADMINI");*/
		data.setOrderValue(110.40f);
		data.setSellBuyOption(Constants.BUY_SELL_OPTION.BUY.getOptionType());
		data.setQuantity(1);
		list.add(data);
		
		data = new OrderData();
		/*data.setComodityId("LEADMINI");*/
		data.setOrderValue(106.00f);
		data.setSellBuyOption(Constants.BUY_SELL_OPTION.BUY.getOptionType());
		data.setQuantity(1);
		list.add(data);
		
		data = new OrderData();
		/*data.setComodityId("NICKELM");*/
		data.setOrderValue(745.60f);
		data.setSellBuyOption(Constants.BUY_SELL_OPTION.SELL.getOptionType());
		data.setQuantity(1);
		list.add(data);
		
		data = new OrderData();
		/*data.setComodityId("NICKELM");*/
		data.setOrderValue(643.10f);
		data.setSellBuyOption(Constants.BUY_SELL_OPTION.BUY.getOptionType());
		data.setQuantity(1);
		list.add(data);
		
		return list;
	}

	private static List<OrderData> getGroupA_OrderDataList() {
		List<OrderData> list = new ArrayList<OrderData>();
		OrderData data = null;
		
		data = new OrderData();
		/*data.setComodityId("COPPER");*/
		data.setOrderValue(361.10f);
		data.setSellBuyOption(Constants.BUY_SELL_OPTION.SELL.getOptionType());
		data.setQuantity(1);
		list.add(data);
		
		data = new OrderData();
		/*data.setComodityId("COPPER");*/
		data.setOrderValue(333.10f);
		data.setSellBuyOption(Constants.BUY_SELL_OPTION.BUY.getOptionType());
		data.setQuantity(1);
		list.add(data);
		
		data = new OrderData();
		/*data.setComodityId("LEAD");*/
		data.setOrderValue(119.60f);
		data.setSellBuyOption(Constants.BUY_SELL_OPTION.SELL.getOptionType());
		data.setQuantity(1);
		list.add(data);
		
		data = new OrderData();
		/*data.setComodityId("LEAD");*/
		data.setOrderValue(109.80f);
		data.setSellBuyOption(Constants.BUY_SELL_OPTION.BUY.getOptionType());
		data.setQuantity(1);
		list.add(data);
		
		data = new OrderData();
		/*data.setComodityId("NICKEL");*/
		data.setOrderValue(755.70f);
		data.setSellBuyOption(Constants.BUY_SELL_OPTION.SELL.getOptionType());
		data.setQuantity(1);
		list.add(data);
		
		data = new OrderData();
		/*data.setComodityId("NICKEL");*/
		data.setOrderValue(660.80f);
		data.setSellBuyOption(Constants.BUY_SELL_OPTION.BUY.getOptionType());
		data.setQuantity(1);
		list.add(data);
		
		data = new OrderData();
		/*data.setComodityId("NICKEL");*/
		data.setOrderValue(638.80f);
		data.setSellBuyOption(Constants.BUY_SELL_OPTION.BUY.getOptionType());
		data.setQuantity(1);
		list.add(data);
		
		return list;
	}
	
}
