package com.guru.order.service.excel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Component;

import com.guru.order.dto.GroupDTO;
import com.guru.order.dto.OrderData;
import com.guru.order.utils.CollectionUtils;
import com.guru.order.utils.Constants;
import com.guru.order.utils.DateUtils;

@Component
public class PriceWorkSheetGenerator extends BasePoiUtil {

	private int snoColNum = 0;
	private int symbolColNum = 1;
	private int expiryDateColNum = 2;
	private int prevPriceColNum = 3;
	private int prevDateColNum = 4;
	private int prevQtyColNum = 5;
	private int orderPriceColNum = 6;
	private int buyColNum = 7;
	private int sellColNum = 8;

	private HSSFCellStyle boldStyle;
	private HSSFCellStyle centerStyle;

	public void generatePriceWorkOrderSheet(List<GroupDTO> groupsList,
			String filePath) throws IOException {
		if (CollectionUtils.isEmpty(groupsList)) {
			return;
		}

		sortGroupsByGroupName(groupsList);

		int rownum = -1;
		HSSFWorkbook workBook = geNewtWorkBook();
		HSSFSheet sheet = workBook.createSheet("Sheet1");
		boldStyle = getStyle(workBook, true);

		centerStyle = workBook.createCellStyle();
		centerStyle.setAlignment(CellStyle.ALIGN_CENTER);
		centerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		setColumnWidth(sheet);
		addReportHeaderRow(sheet, ++rownum);
		addEmptyRow(sheet, ++rownum);

		for (GroupDTO groupDto : groupsList) {
			addGroupNameRow(sheet, ++rownum, groupDto);
			// addEmptyRow(sheet, ++rownum);
			rownum = addGroupHeaderRow(sheet, rownum);

			if (CollectionUtils.isEmpty(groupDto.getOrderData())) {
				continue;
			}

			Map<String, List<OrderData>> ordersMap = parseBySymbol(groupDto
					.getOrderData());
			List<String> symbolsList = new ArrayList<String>(ordersMap.keySet());
			Collections.sort(symbolsList);

			int sno = 0;
			for (String symbol : symbolsList) {
				List<OrderData> subList = ordersMap.get(symbol);
				if (CollectionUtils.isEmpty(subList)) {
					continue;
				}

				sortOrdersByTypeAndExpiryDate(subList);

				++rownum;
				sheet.addMergedRegion(new CellRangeAddress(rownum, (rownum
						+ subList.size() - 1), snoColNum, snoColNum));
				sheet.addMergedRegion(new CellRangeAddress(rownum, (rownum
						+ subList.size() - 1), symbolColNum, symbolColNum));

				HSSFRow row = createRow(sheet, rownum);
				HSSFCell snoCell = addStringCell(row, snoColNum,
						String.valueOf(++sno));
				snoCell.setCellStyle(centerStyle);
				HSSFCell symbolCell = addStringCell(row, symbolColNum, symbol);
				symbolCell.setCellStyle(centerStyle);

				OrderData order = subList.get(0);
				addOrderDataRow(row, order);

				for (int i = 1; i < subList.size(); i++) {
					order = subList.get(i);
					row = createRow(sheet, ++rownum);
					addOrderDataRow(row, order);
				}
			}

			addEmptyRow(sheet, ++rownum);
		}

		saveToFile(workBook, filePath);
	}

	private void setColumnWidth(HSSFSheet sheet) {
		sheet.setColumnWidth(snoColNum, 2000);
		sheet.setColumnWidth(symbolColNum, 5000);
		sheet.setColumnWidth(expiryDateColNum, 3000);
		sheet.setColumnWidth(prevPriceColNum, 3000);
		sheet.setColumnWidth(prevDateColNum, 3000);
		sheet.setColumnWidth(prevQtyColNum, 2000);
		sheet.setColumnWidth(orderPriceColNum, 3000);
		sheet.setColumnWidth(buyColNum, 2000);
		sheet.setColumnWidth(sellColNum, 2000);
	}

	private void sortOrdersByTypeAndExpiryDate(List<OrderData> subList) {
		Collections.sort(subList, new Comparator<OrderData>() {

			@Override
			public int compare(OrderData o1, OrderData o2) {
				if (o1 == o2) {
					return 0;
				}
				if (o1 == null) {
					return -1;
				}
				int compare = o2.getOrderType().compareTo(o1.getOrderType());
				if (compare != 0) {
					compare = (o1.getExpiryDate() != null) ? (o1
							.getExpiryDate().compareTo(o2.getExpiryDate()))
							: -1;
				}
				return compare;
			}
		});
	}

	private void addOrderDataRow(HSSFRow row, OrderData order) {
		addStringCell(row, expiryDateColNum, order.getExpiryDate());
		addStringCell(row, prevPriceColNum,
				String.valueOf(order.getPrevSellValue()));
		addStringCell(row, prevDateColNum, order.getPrevSellDate());
		addStringCell(row, prevQtyColNum,
				String.valueOf(order.getPrevSellQuantity()));
		addStringCell(row, orderPriceColNum,
				String.valueOf(order.getOrderPrice()));
		String buyQty = "-";
		String sellQty = "-";
		if (Constants.BUY_SELL_OPTION.BUY.getOptionType().equals(
				order.getOrderType())) {
			buyQty = String.valueOf(order.getQuantity());
		} else {
			sellQty = String.valueOf(order.getQuantity());
		}
		addStringCell(row, buyColNum, buyQty);
		addStringCell(row, sellColNum, sellQty);
	}

	private Map<String, List<OrderData>> parseBySymbol(
			List<OrderData> ordersList) {
		Map<String, List<OrderData>> map = new HashMap<String, List<OrderData>>();
		for (OrderData order : ordersList) {
			List<OrderData> subList = map.get(order.getCommodity().getName());
			if (subList == null) {
				subList = new ArrayList<OrderData>();
				map.put(order.getCommodity().getName(), subList);
			}
			subList.add(order);
		}
		return map;
	}

	private int addGroupHeaderRow(HSSFSheet sheet, int rownum) {
		HSSFRow row1 = sheet.createRow(++rownum);
		sheet.addMergedRegion(new CellRangeAddress(rownum, rownum,
				prevPriceColNum, prevQtyColNum));
		addStringCell(row1, prevPriceColNum, "Existing Sell Position",
				boldStyle);

		HSSFRow row2 = sheet.createRow(++rownum);
		addStringCell(row2, snoColNum, "Sl no", boldStyle);
		addStringCell(row2, symbolColNum, "Symbol", boldStyle);
		addStringCell(row2, expiryDateColNum, "Expiry Date", boldStyle);
		addStringCell(row2, prevPriceColNum, "P Price", boldStyle);
		addStringCell(row2, prevDateColNum, "P Date", boldStyle);
		addStringCell(row2, prevQtyColNum, "Qty.", boldStyle);
		addStringCell(row2, orderPriceColNum, "Order Price", boldStyle);
		addStringCell(row2, buyColNum, "Buy", boldStyle);
		addStringCell(row2, sellColNum, "Sell", boldStyle);
		return rownum;
	}

	private void addGroupNameRow(HSSFSheet sheet, int rownum, GroupDTO groupDto) {
		HSSFRow row = sheet.createRow(rownum);
		addEmptyCell(row, 0);
		addStringCell(row, 1, groupDto.getGroupName(), boldStyle);

		String idsData = "ID NO's. " + groupDto.getUsers();
		addStringCell(row, 2, idsData);
	}

	private void addReportHeaderRow(HSSFSheet sheet, int rownum) {
		sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, snoColNum,
				prevDateColNum));
		sheet.addMergedRegion(new CellRangeAddress(rownum, rownum,
				orderPriceColNum, sellColNum));

		HSSFRow row = sheet.createRow(rownum);
		addStringCell(row, snoColNum, "PRICE WORK OUT SHEET", boldStyle);
		addStringCell(row, orderPriceColNum,
				"Date: " + DateUtils.formatToDDMMYYYY(new Date()), boldStyle);
		addEmptyCell(row, symbolColNum);
		addEmptyCell(row, expiryDateColNum);
		addEmptyCell(row, prevPriceColNum);
		addEmptyCell(row, prevDateColNum);
		addEmptyCell(row, prevQtyColNum);
		addEmptyCell(row, buyColNum);
		addEmptyCell(row, sellColNum);
	}

	private void sortGroupsByGroupName(List<GroupDTO> groupsList) {
		Collections.sort(groupsList, new Comparator<GroupDTO>() {

			@Override
			public int compare(GroupDTO group1, GroupDTO group2) {
				return group1.getGroupName().compareTo(group2.getGroupName());
			}
		});
	}

}
