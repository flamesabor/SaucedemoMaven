package com.webapp.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	private static XSSFWorkbook excelWbook;
	private static XSSFSheet excelWSheet; 
	
	public static String getCellData(int RowNum, int ColNum) throws IOException
	{
		FileInputStream ExcelFile = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources"+"/TestData.xlsx");
		excelWbook = new XSSFWorkbook(ExcelFile);
		excelWSheet = excelWbook.getSheetAt(0);
		return excelWSheet.getRow(RowNum).getCell(ColNum).getStringCellValue();
	}

}
 