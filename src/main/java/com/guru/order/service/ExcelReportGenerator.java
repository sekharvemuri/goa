package com.guru.order.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.guru.order.dto.OrderDTO;

public class ExcelReportGenerator {
	
	private HSSFWorkbook workBook;
	private HSSFSheet sheet;
	private int rowNum = -1;

	public void generateExcel(OrderDTO order) {
		try {
			initializeWorkBook();
			initializeWorkSheet();
			addHeader();
			writeFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	private void writeFile() throws FileNotFoundException, IOException {
		FileOutputStream fos = new FileOutputStream(new File("c:\\guruorder\\OrderSheet.csv"));
		workBook.write(fos);
	}

	private void addHeader() {
		HSSFRow row = getNewRow();
		int colNum = -1;
		addCell(row, "Reference No.", ++colNum);
		addCell(row, "Open/Close", ++colNum);
		addCell(row, "Cover/UnCover", ++colNum);
		addCell(row, "Book Type", ++colNum);
		addCell(row, "MIT Type", ++colNum);
		addCell(row, "Buy/Sell", ++colNum);
		addCell(row, "Instrument Name", ++colNum);
		addCell(row, "Symbol", ++colNum);
		addCell(row, "Expiry Date", ++colNum);
		addCell(row, "Strike Price", ++colNum);
		addCell(row, "Option Type", ++colNum);
		addCell(row, "Instruction", ++colNum);
		addCell(row, "Good Till Date/Days", ++colNum);
		addCell(row, "Special Terms", ++colNum);
		addCell(row, "MF Qty", ++colNum);
		addCell(row, "Price", ++colNum);
		addCell(row, "Trigger Price", ++colNum);
		addCell(row, "Quantity", ++colNum);
		addCell(row, "Disclosed Qty.", ++colNum);
		addCell(row, "Participant", ++colNum);
		addCell(row, "Pro/Client", ++colNum);
		addCell(row, "a/c No.", ++colNum);
		addCell(row, "Product Type", ++colNum);
		addCell(row, "Remarks", ++colNum);
		addCell(row, "Group ID", ++colNum);
		addCell(row, "Protection %", ++colNum);
	}

	private void addCell(HSSFRow row, String data, int colNum) {
		HSSFCell cell = row.createCell(colNum);
		cell.setCellValue(data);
	}

	private HSSFRow getNewRow() {
		return sheet.createRow(++rowNum);
	}

	private void initializeWorkSheet() {
		sheet = workBook.createSheet();
	}

	private void initializeWorkBook() throws FileNotFoundException, IOException {
		workBook = new HSSFWorkbook();
	}

	public static void main(String[] args) {
		ExcelReportGenerator gen = new ExcelReportGenerator();
		gen.generateExcel(null);
	}
}
