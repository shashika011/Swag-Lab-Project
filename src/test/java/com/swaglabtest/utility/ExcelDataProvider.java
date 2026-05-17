package com.swaglabtest.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {
	XSSFWorkbook wb;

	public ExcelDataProvider() 
	{
		File src= new File("./TestData/TestData.xlsx");
		try {
		FileInputStream inpstr= new FileInputStream(src);
		wb= new XSSFWorkbook(inpstr);
		}catch(IOException excp)
		{
			System.out.println("Unable to read file"+excp.getMessage());
		}		
	}	
	
	public String getStringdata(String getStringdata, int row, int col)
	{
		String value=wb.getSheet(getStringdata).getRow(row).getCell(col).getStringCellValue();
		return value.isEmpty() ? "" : value;
	}
	
	public String getStringdata(int getSheetindex, int row, int col)
	{
		String value= wb.getSheetAt(getSheetindex).getRow(row).getCell(col).getStringCellValue();
		return value.isEmpty() ? "" : value;
	}
	
	public double getIntegerData(String getStringdata, int row, int col)
	{
		return wb.getSheet(getStringdata).getRow(row).getCell(col).getNumericCellValue();
	}
	
	public double getIntegerData(int sheetIndex, int row, int col)
	{
		return wb.getSheetAt(sheetIndex).getRow(row).getCell(col).getNumericCellValue();
	}
	
	public String getCurrencyData(int sheetIndex, int row, int col) {
	    double value = wb.getSheetAt(sheetIndex).getRow(row).getCell(col).getNumericCellValue();
	    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
	    return currencyFormatter.format(value);
	}
	public String getCurrencyData(String sheetName, int row, int col) {
	    double value = wb.getSheet(sheetName).getRow(row).getCell(col).getNumericCellValue();
	    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
	    return currencyFormatter.format(value);
	}

}
