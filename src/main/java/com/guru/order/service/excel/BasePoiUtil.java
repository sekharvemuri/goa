package com.guru.order.service.excel;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

public class BasePoiUtil {
	
	public HSSFWorkbook geNewtWorkBook() {
		return new HSSFWorkbook();
	}
	
	public HSSFRow createRow(HSSFSheet sheet, int rownum) {
		return sheet.createRow(rownum);
	}
	
	public void addEmptyRow(HSSFSheet sheet, int rownum) {
		createRow(sheet, rownum);
	}
	
	public HSSFCell addEmptyCell(HSSFRow row, int colnum) {
		return addStringCell(row, colnum, "");
	}
	
	public HSSFCell addStringCell(HSSFRow row, int colnum, String data) {
		HSSFCell cell = row.createCell(colnum);
		cell.setCellValue(data);
		return cell;
	}
	
	public void addStringCell(HSSFRow row, int colnum, String data, HSSFCellStyle style) {
		HSSFCell cell = row.createCell(colnum);
		cell.setCellValue(data);
		if (style != null) {
			cell.setCellStyle(style);
		}
	}
	
	public void saveToFile(HSSFWorkbook workBook, String fileName) throws IOException {
		FileOutputStream out = new FileOutputStream(fileName);
	    workBook.write(out);
	    out.close();
	    workBook.close();
	}
	
	public HSSFCellStyle getStyle(HSSFWorkbook workbook, boolean isBold) {
		HSSFFont font = workbook.createFont();
		font.setBold(isBold);
		
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		style.setAlignment(CellStyle.ALIGN_CENTER);
//		style.setBorderBottom((short) 1);
//		style.setBorderRight((short) 1);
//		style.setBorderTop((short) 1);
//		style.setBorderLeft((short) 1);
		return style;
	}

}
