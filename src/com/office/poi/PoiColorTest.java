package com.office.poi;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;


public class PoiColorTest {
	public static void main(String[] args) throws IOException {
		HSSFWorkbook wb = new HSSFWorkbook();

		HSSFSheet sheet = wb.createSheet("Sheet1");
		HSSFRow row = sheet.createRow((short) 1);
		
		HSSFCellStyle style = wb.createCellStyle();
		style.setFillForegroundColor(HSSFColor.AQUA.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		HSSFFont font = wb.createFont();
		font.setColor(HSSFColor.RED.index);
		style.setFont(font);
		
		HSSFCell cell=  row.createCell((short)1,HSSFCell.CELL_TYPE_STRING);
		cell.setCellStyle(style);
		cell.setCellValue("001010");
		
		//获取
		Hashtable<Integer, HSSFColor> ht= HSSFColor.getIndexHash();
		
		File f = new File("C://test.xls");

		FileOutputStream fos = new FileOutputStream(f);
		wb.write(fos);
		fos.close();

		Desktop.getDesktop().open(f);
	}
}
