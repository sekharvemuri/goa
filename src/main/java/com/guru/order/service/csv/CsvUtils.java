package com.guru.order.service.csv;

import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.guru.order.dto.OrderConfirmationDTO;
import com.guru.order.utils.DateUtils;
import com.guru.order.utils.StringUtils;

import au.com.bytecode.opencsv.CSVReader;

@Component
public class CsvUtils {

	public List<OrderConfirmationDTO> getExecutionsList(Reader reader) {
		CSVReader csvReader = null;
		List<OrderConfirmationDTO> list = new ArrayList<OrderConfirmationDTO>();

		try {
			// Default separator is comma
			// Default quote character is double quote
			// Start reading from line number 2 (line numbers start from zero)
			csvReader = new CSVReader(reader, ',', '"', 1);

			// Read CSV line by line and use the string array as you want
			String[] nextLine;
			while ((nextLine = csvReader.readNext()) != null) {
				if (nextLine != null) {
					list.add(parseToOrderConfirmation(nextLine));
					System.out.println(Arrays.toString(nextLine));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (csvReader != null) {
				try {
					csvReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	private OrderConfirmationDTO parseToOrderConfirmation(String[] nextLine)
			throws ParseException {
		OrderConfirmationDTO dto = new OrderConfirmationDTO();
		// Column-Numbering is from 0 to n-1
		dto.setCandidateId(Long.valueOf(nextLine[4])); // ClientCode-column
		dto.setCandidateName(nextLine[5]); // ClientName-column
		dto.setCommodityName(nextLine[7]); // Symbol-column
		dto.setExpirtyDate(DateUtils.parseToDDMMYYYY(nextLine[9])); // ExpiryDate-Column
		dto.setBuySellIndicator(StringUtils.toUpperCase(nextLine[15])); //BuySellIndicator
		dto.setTradeQuantity(Integer.parseInt(nextLine[16]));
		dto.setUnitPrice(Float.parseFloat(nextLine[17]));
		dto.setTradeValue(Float.parseFloat(nextLine[18]));
		return dto;
	}

}
