package com.guru.order.service.csv;

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
import com.guru.order.utils.DateUtils;

@Component
public class CsvGenerator {

	private CSVWriter writer;
	private List<String[]> sellData = null;
	private List<String[]> buyData = null;
	
	public void generateOrderSheet(OrderDTO order, String filePath) {
		try {
			initializeWriter(filePath);
			parseOrderData(order);
			
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
					
					if (Constants.BUY_SELL_OPTION.BUY.getOptionType().equals(orderData.getOption())) {
						for (String canId : candidates) {
							buyData.add(new String[] {
									String.valueOf(++buyRefNum),
									"O",
									"U",
									"1",
									"0",
									String.valueOf(Constants.BUY_SELL_OPTION.BUY
											.getOption()),
									"Instrument Name",
									String.valueOf(orderData.getCommodity()
											.getId()),
									DateUtils.formatToDDMMMYY(orderData
											.getExpiryDate()), "", "", "1", "",
									"", "",
									String.valueOf(orderData.getOrderValue()),
									"",
									String.valueOf(orderData.getQuantity()),
									"", "", "", canId, "Product Type", "",
									group.getGroupName(), "" });
						}
					}
					else {
						for (String canId : candidates) {
							sellData.add(new String[] {
									String.valueOf(++sellRefNum),
									"O",
									"U",
									"1",
									"0",
									String.valueOf(Constants.BUY_SELL_OPTION.SELL
											.getOption()),
									"Instrument Name",
									String.valueOf(orderData.getCommodity()
											.getId()),
									DateUtils.formatToDDMMMYY(orderData
											.getExpiryDate()), "", "", "1", "",
									"", "",
									String.valueOf(orderData.getOrderValue()),
									"",
									String.valueOf(orderData.getQuantity()),
									"", "", "", canId, "Product Type", "",
									group.getGroupName(), "" });
						}
					}
				}
			}
		}
	}

	private void saveData() throws IOException {
		writer.flush();
		writer.close();
		writer = null;
	}
	
	private void addEmptyRow() {
		writer.writeNext(new String[] {"","","","","","","","","","","","","","","","","","","","","","","","","",""});
	}

	private void addHeader() {
		writer.writeNext(new String[] {"Reference No.","Open/Close","Cover/UnCover","Book Type","MIT Type","Buy/Sell","Instrument Name","Symbol","Expiry Date","Strike Price","Option Type","Instruction","Good Till Date/Days","Special Terms","MF Qty","Price","Trigger Price","Quantity","Disclosed Qty.","Participant","Pro/Client","a/c No.","Product Type","Remarks","Group ID","Protection %"});
	}

	private void initializeWriter(String filePath) throws IOException {
		File file = new File(filePath);
		if(file.exists()) {
			file.delete();
		}
		writer = new CSVWriter(new FileWriter(new File(filePath)), CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER);
		sellData = new ArrayList<String[]>();
		buyData = new ArrayList<String[]>();
	}
	
}
