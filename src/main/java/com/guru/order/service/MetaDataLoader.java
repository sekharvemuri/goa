package com.guru.order.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;

import com.guru.order.dto.CommodityDTO;
import com.guru.order.dto.ExpiryDateDTO;
import com.guru.order.dto.GroupDTO;

public class MetaDataLoader {
	
	public static String GROUPS = "GROUPS";
	public static String COMMODITIES = "COMMODITIES";
	public static String EXPIRY_DATES = "EXPIRY_DATES";
	
	public static void loadData(ServletContext context) {
		System.out.println("Loading MetaData");
		ResourceBundle rBundle = ResourceBundle.getBundle("metadata");
		System.out.println("rBundle : " + rBundle);
		
		try {
			List<ExpiryDateDTO> expiryDatesList = getExpiryDates(rBundle);
			context.setAttribute(EXPIRY_DATES, expiryDatesList);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		List<CommodityDTO> cmdtyList = getCommodities(rBundle);
		context.setAttribute(COMMODITIES, cmdtyList);
		
		List<GroupDTO> groupsList = getGroups(rBundle);
		context.setAttribute(GROUPS, groupsList);
	}

	private static List<ExpiryDateDTO> getExpiryDates(ResourceBundle rBundle)
			throws ParseException {
		List<ExpiryDateDTO> expiryDatesList = new ArrayList<ExpiryDateDTO>();
		String expiryDatesStr = rBundle.getString("expiry.dates");
		String[] expiryDatesArray = expiryDatesStr.split(",");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		for (String dateStr : expiryDatesArray) {
			ExpiryDateDTO dto = new ExpiryDateDTO();
			Date expiryDate = sdf.parse(dateStr);
			dto.setExpiryDate(expiryDate);
			dto.setExpiryDateInMillis(expiryDate.getTime());
			expiryDatesList.add(dto);
		}
		return expiryDatesList;
	}

	private static List<GroupDTO> getGroups(ResourceBundle rBundle) {
		String groupStr = rBundle.getString("groups");
		String[] groupArray = groupStr.split(",");
		List<GroupDTO> groupsList = new ArrayList<GroupDTO>();
		for (String groupName : groupArray) {
			String candidateKey = groupName.replace(" ", ".")+".candidates";
			String candidates = rBundle.getString(candidateKey.toLowerCase());
			
			GroupDTO groupDto = new GroupDTO();
			groupDto.setGroupName(groupName);
			groupDto.setUsers(candidates.replace(",", ", "));
			groupsList.add(groupDto);
		}
		return groupsList;
	}

	private static List<CommodityDTO> getCommodities(ResourceBundle rBundle) {
		String commodityStr = rBundle.getString("commodities");
		String[] cmdtyArray = commodityStr.split(",");
		List<CommodityDTO> cmdtyList = new ArrayList<CommodityDTO>();
		for (String cmd : cmdtyArray) {
			cmdtyList.add(new CommodityDTO(cmd.toUpperCase()));
		}
		return cmdtyList;
	}

}
