package com.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	FileInputStream inputStream;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell column;
	String path;

	public ExcelUtils(String path) {
		//super();
		this.path = path;
	}

	public int getRowCount(String sheetName) throws IOException {
		inputStream = new FileInputStream(path);
		workbook = new XSSFWorkbook(inputStream);
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		workbook.close();
		inputStream.close();
		return rowCount;
	}

	public int getCellCount(String sheetName, int rowNum) throws IOException {
		inputStream = new FileInputStream(path);
		workbook = new XSSFWorkbook(inputStream);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		int colCount = row.getLastCellNum();
		workbook.close();
		inputStream.close();
		return colCount;
	}

	public String getData(String sheetName, int rowNum, int colNum) throws IOException {
		inputStream = new FileInputStream(path);
		workbook = new XSSFWorkbook(inputStream);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		column = row.getCell(colNum);
		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(column);
		} catch (Exception e) {
			data = "";
		}
//		workbook.close();
//		inputStream.close();
		return data;
	}

	public String[][] getCellDataValues(String sheetName, String path) {
		ExcelUtils utils=new ExcelUtils(path);
		try {
			int totalRows=utils.getRowCount(sheetName);
			int totalColumns=utils.getCellCount(sheetName, 1);
			String[][] data=new String[totalRows][totalColumns];
			for(int i=1;i<=totalRows;i++) {
				for(int j=0;j<totalColumns;j++) {
					data[i-1][j]=utils.getData(sheetName, i, j);
				}
			}
			return data;
		} catch (Exception e) {
			return null;
		}
	}

}
