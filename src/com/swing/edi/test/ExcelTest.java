package com.swing.edi.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

public class ExcelTest {
	public static void main(String[] args) throws IOException {
		HSSFWorkbook wb = new HSSFWorkbook();
			
		HSSFSheet sheet = wb.createSheet();
		
		//sheet.setDisplayGridlines(false);
		
		sheet.setDefaultColumnWidth((short) 50);
		HSSFRow row = sheet.createRow(0);
		
		row.setHeightInPoints((float) (70 * 0.75));
		
		
		HSSFCell cell = row.createCell((short) 0);

		cell.setCellValue("aaaaaaaaaaa");

		sheet.addMergedRegion(new Region((short) 0, (short) 0, (short) 0,
				(short) 1));

		FileOutputStream fos = new FileOutputStream("d://aa.xls");

		wb.write(fos);

		fos.close();

	}
}
