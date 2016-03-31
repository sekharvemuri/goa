package com.guru.order.service.excel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;

import com.guru.order.data.vo.WorkOrderVO;
import com.guru.order.utils.CollectionUtils;
import com.guru.order.utils.StringUtils;

@Component
public class WorkOrderGenerator extends BasePoiUtil {

	public Map<String, List<String>> getGroupCandidates(InputStream inputStream) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("Group A", StringUtils.toList("90178"));
		map.put("Group B", StringUtils.toList("90125, 90138,8712"));
		map.put("Group C", StringUtils.toList("69757, 90140,90142,90127,90141,90183"));
		map.put("Group D", StringUtils.toList("69756, 90135,90120,90137,90139,90160,90166"));
		map.put("Group E", StringUtils.toList("90149, 90159,90180,90104,90152,90154,90174,90121,90162,90129,90143"));
		map.put("Group F", StringUtils.toList("90111, 90170,90124,90163,90175,90173,90164,90189,90195,90190,90197"));
		map.put("Group G", StringUtils.toList("90156, 90130,90150,90188,90196,90187"));
		map.put("Group H", StringUtils.toList("90133, 90119,90148,90155,90179,90184,90186,90153,90144,90145,90157,90147,90167"));
		map.put("Group I", StringUtils.toList("69757, 90140,90142,90127,90141,69756,90137,90135,90125,90138,90120,90139 ,8712,90183,90160"));
		map.put("Group J", StringUtils.toList("0140, 90142,90137,90139 ,90120,90125,69757,90135,90160,90183"));
		map.put("Group K", StringUtils.toList("90131"));
		map.put("Group L", StringUtils.toList("90162, 90129"));
		map.put("Group M", StringUtils.toList("90159"));
		map.put("Group N", StringUtils.toList("90181"));
		map.put("Group O", StringUtils.toList("90143"));
		map.put("Group P", StringUtils.toList("90157, 90147,90167"));
		map.put("Group Q", StringUtils.toList("90153, 90144,90145"));
		map.put("Group R", StringUtils.toList("69641"));
		map.put("Group S", StringUtils.toList("90181"));
		map.put("Group T", StringUtils.toList("90177"));
		map.put("Group U", StringUtils.toList("90104, 90152,90154,90174"));
		map.put("Group V", StringUtils.toList("90121"));
		map.put("Group W", StringUtils.toList("90175, 90173,90164"));
		map.put("Group X", StringUtils.toList("90176"));
		map.put("Group Y", StringUtils.toList("90146, 90171"));
		return map;
	}

	public List<WorkOrderVO> getWorkOrders(InputStream inputStream) {
		Map<String, List<String>> groupCandidatesMap = getGroupCandidates(inputStream);
		List<WorkOrderVO> list = new ArrayList<WorkOrderVO>();
		HSSFWorkbook workbook = null;
		Calendar orderTime = Calendar.getInstance();
		try {
			workbook = new HSSFWorkbook(inputStream);
			HSSFSheet sheet = workbook.getSheetAt(1);
			HSSFRow row = null;
			int lastRowIndex = sheet.getLastRowNum();
			String symbol = "";

			for (int rowIndex = 0; rowIndex <= lastRowIndex; rowIndex++) {
				row = sheet.getRow(rowIndex);
				if (row == null) {
					continue;
				}

				String groupName = StringUtils.trim(getCellStringValue(row, 0));
				if (groupName == null || !groupName.startsWith("Group")) {
					continue;
				}
				if (!"Group A A".equals(groupName)) {
					String[] gnArray = groupName.split(" ");
					groupName = gnArray[0] + " " + gnArray[gnArray.length - 1];
				}

				if (StringUtils.trim(row.getCell(1).getStringCellValue()) != "")
					symbol = StringUtils.trim(row.getCell(1)
							.getStringCellValue());

				Double orderPrice = getNumericCellValue(row, 6);
				if (orderPrice != null && orderPrice == 0.0d) {
					continue;
				}

				List<String> candidatesList = groupCandidatesMap.get(groupName);
				if (CollectionUtils.isEmpty(candidatesList)) {
					continue;
				}

				Double prevSellPrice = row.getCell(3).getNumericCellValue();
				Double prevSellQty = row.getCell(5).getNumericCellValue();
				Double buyQty = row.getCell(7).getNumericCellValue();
				Double sellQty = row.getCell(8).getNumericCellValue();

				for (String candId : candidatesList) {
					WorkOrderVO vo = new WorkOrderVO();
					vo.setGroupName(groupName);
					vo.setCommodityName(symbol);
					vo.setCandidateId(Long.valueOf(candId));
					vo.setExpiryDate(getCellCalendarValue(row, 2));
					vo.setPreviousSellPrice(prevSellPrice.floatValue());
					vo.setPreviousSellDate(getCellCalendarValue(row, 4));
					vo.setPreviousSellQty(prevSellQty.intValue());
					vo.setOrderAmount(orderPrice.floatValue());
					if (vo.getPreviousSellDate() != null) {
						vo.setOrderTime(vo.getPreviousSellDate());
					} else {
						vo.setOrderTime(orderTime);
					}
					if (buyQty != null && buyQty > 0.0d) {
						vo.setOrderType("BUY");
						vo.setOrderQuantity(buyQty.intValue());
					} else {
						vo.setOrderType("SELL");
						vo.setOrderQuantity(sellQty.intValue());
					}
					list.add(vo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (workbook != null)
					workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	private double getNumericCellValue(HSSFRow row, int colIndex) {
		HSSFCell cell = row.getCell(colIndex);
		try {
		return (cell == null) ? 0.0d : cell.getNumericCellValue();
		} catch (Exception e) {
			System.out.println(cell.getStringCellValue());
			throw new RuntimeException(e);
		}
	}

	private String getCellStringValue(HSSFRow row, int colIndex) {
		HSSFCell cell = row.getCell(colIndex);
		return (cell == null) ? null : cell.getStringCellValue();
	}

	private Calendar getCellCalendarValue(HSSFRow row, int colIndex) {
		HSSFCell cell = row.getCell(colIndex);
		if (cell != null && cell.getDateCellValue() != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(cell.getDateCellValue());
			return cal;
		}
		return null;
	}

}
