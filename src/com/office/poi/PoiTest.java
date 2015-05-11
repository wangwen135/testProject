package com.office.poi;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

public class PoiTest {

	public static final short BLACK = HSSFColor.BLACK.index;
	public static final short RED = HSSFColor.RED.index;

	public static void main(String[] args) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		// wb.setPrintArea(sheetIndex, startColumn, endColumn, startRow, endRow)

		HSSFSheet sheet = wb.createSheet("Sheet1");
		// 设置列宽

		// sheet.setDefaultColumnStyle((short)0, createStyle(wb));
		sheet.setColumnWidth((short) 0, (short) (4 * 256));
		sheet.setColumnWidth((short) 1, (short) (12 * 256));
		sheet.setColumnWidth((short) 2, (short) (9 * 256));
		sheet.setColumnWidth((short) 3, (short) (13 * 256));
		sheet.setColumnWidth((short) 4, (short) (14 * 256));
		sheet.setColumnWidth((short) 5, (short) (13 * 256));
		sheet.setColumnWidth((short) 6, (short) (9 * 256));
		sheet.setColumnWidth((short) 7, (short) (15 * 256));
		sheet.setColumnWidth((short) 8, (short) (16 * 256));
		sheet.setColumnWidth((short) 9, (short) (8 * 256));
		sheet.setColumnWidth((short) 10, (short) (10.5 * 256));
		sheet.setColumnWidth((short) 11, (short) (8 * 256));
		sheet.setColumnWidth((short) 12, (short) (10 * 256));
		sheet.setColumnWidth((short) 13, (short) (10 * 256));
		sheet.setColumnWidth((short) 14, (short) (9 * 256));
		sheet.setColumnWidth((short) 15, (short) (14 * 256));
		sheet.setColumnWidth((short) 16, (short) (8 * 256));

		// 合并
		sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 16));
		// 合并
		sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 16));

		HSSFRow hfrow = sheet.createRow(0);

		hfrow.setHeightInPoints(70.5f);// 设置行高

		HSSFCell cell = hfrow.createCell((short) 0);

		HSSFCellStyle style = createStyle(wb, false, true, 11, false, BLACK,
				true);
		cell.setCellStyle(style);

		HSSFRichTextString str = new HSSFRichTextString(
				"理货结果类型：\n0、已查验理货，无异常  1、品名不符  2、数量不符   3、重量不符  4、价值不符  5、件数不符   6、分单（在备注栏注明与该票分单的其他单号）  7、错发件  8、发票填写与运单不符   9、货物内附带发货清单、货物标价牌  10、违禁品   11、未查验(货未到）  12、其他_______（注明原因）\n注：理货结果请按以上原因代码进行归类");
		HSSFFont font = createFont(wb, 11, false, BLACK);
		str.applyFont(font);

		font = createFont(wb, 11, true, BLACK);
		str.applyFont(0, 8, font);

		font = createFont(wb, 11, false, RED);
		str.applyFont(str.length() - 18, str.length(), font);

		cell.setCellValue(str);

		for (int i = 1; i < 17; i++) {// 画线
			cell = hfrow.createCell((short) i);
			style = createStyle(wb, false, true, 11, false, BLACK, true);
			cell.setCellStyle(style);
		}

		hfrow = sheet.createRow(1);
		hfrow.setHeightInPoints(42.75f);// 设置行高
		cell = hfrow.createCell((short) 0);

		style = createStyle(wb, true, true, 16, true, BLACK, false);
		cell.setCellStyle(style);
		cell.setCellValue(new HSSFRichTextString("国际件理货信息表"));

		cell = hfrow.createCell((short) 16);// 画线
		style = createStyle(wb, false, true, 11, false, BLACK, false);
		cell.setCellStyle(style);

		hfrow = sheet.createRow(2);
		hfrow.setHeightInPoints(24f);

		createCell(wb, sheet, hfrow, 0, "序号", true);
		createCell(wb, sheet, hfrow, 1, "日期", false);
		createCell(wb, sheet, hfrow, 2, "口岸", false);
		createCell(wb, sheet, hfrow, 3, "报关批次", false);
		createCell(wb, sheet, hfrow, 4, "单号", false);
		createCell(wb, sheet, hfrow, 5, "英文品名", false);
		createCell(wb, sheet, hfrow, 6, "中文品名", false);
		createCell(wb, sheet, hfrow, 7, "更正后英文品名", false);
		createCell(wb, sheet, hfrow, 8, "更正后的中文品名", false);
		createCell(wb, sheet, hfrow, 9, "数量", false);
		createCell(wb, sheet, hfrow, 10, "更正后数量", false);
		createCell(wb, sheet, hfrow, 11, "申报价值", false);
		createCell(wb, sheet, hfrow, 12, "更正后价值", false);
		createCell(wb, sheet, hfrow, 13, "有无发票", false);
		createCell(wb, sheet, hfrow, 14, "是否滞留", false);
		createCell(wb, sheet, hfrow, 15, "理货结果\n（填写代码）", true);
		createCell(wb, sheet, hfrow, 16, "备注", false);

		
		hfrow = sheet.createRow(3);
		hfrow.setHeightInPoints(24f);

		createCell(wb, sheet, hfrow, 0, "0 ", true);
		createCell(wb, sheet, hfrow, 1, "1 ", true);
		createCell(wb, sheet, hfrow, 2, "2 ", true);
		createCell(wb, sheet, hfrow, 3, "3 ", true);
		createCell(wb, sheet, hfrow, 4, "4 ", true);
		createCell(wb, sheet, hfrow, 5, "5 ", true);
		createCell(wb, sheet, hfrow, 6, "6 ", true);
		createCell(wb, sheet, hfrow, 7, "7 ", true);
		createCell(wb, sheet, hfrow, 8, "8 ", true);
		createCell(wb, sheet, hfrow, 9, "9 ", true);
		createCell(wb, sheet, hfrow, 10, "10", true);
		createCell(wb, sheet, hfrow, 11, "11", true);
		createCell(wb, sheet, hfrow, 12, "12", true);
		createCell(wb, sheet, hfrow, 13, "13", true);
		createCell(wb, sheet, hfrow, 14, "14", true);
		createCell(wb, sheet, hfrow, 15, "15", true);
		createCell(wb, sheet, hfrow, 16, "16", true);

		File f = new File("C://aaa//test.xls");

		FileOutputStream fos = new FileOutputStream(f);
		wb.write(fos);
		fos.close();

		Desktop.getDesktop().open(f);
	}

	public static HSSFCell createCell(HSSFWorkbook wb, HSSFSheet sheet,
			HSSFRow hfrow, int column, Object obj, boolean wrapText) {
		HSSFCell cell = hfrow.createCell((short) column);

		HSSFCellStyle createStyle = createStyle(wb, true, true, 10, false,
				BLACK, wrapText);

		if (column == 7 || column == 8 || column == 10 || column == 12
				|| column == 13 || column == 14 || column == 15) {
			createStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
			createStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		}

		cell.setCellStyle(createStyle);
		if (obj != null) {
			if (obj instanceof String) {
				cell.setCellValue(new HSSFRichTextString((String) obj));
			} else if (obj instanceof Double) {
				cell.setCellValue((Double) obj);
			} else if (obj instanceof Date) {
				cell.setCellValue((Date) obj);
			} else {
				cell.setCellValue(new HSSFRichTextString(obj.toString()));
			}
		}

		return cell;
	}

	public static HSSFCellStyle createStyle(HSSFWorkbook wb) {
		return createStyle(wb, true, true, 10, false, HSSFColor.BLACK.index,
				false);
	}

	public static HSSFCellStyle createStyle(HSSFWorkbook wb, boolean center,
			boolean vcenter, int fontSize, boolean fontBold, short color,
			boolean wrapText) {
		HSSFCellStyle style = wb.createCellStyle();
		// 设置边框
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);

		if (vcenter) {
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		}

		if (center) {
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		}
		if (wrapText) {
			style.setWrapText(true);
		}

		HSSFFont font = createFont(wb, fontSize, fontBold, color);
		style.setFont(font);

		return style;
	}

	public static HSSFFont createFont(HSSFWorkbook wb) {
		return createFont(wb, 10, false, HSSFColor.BLACK.index);
	}

	public static HSSFFont createFont(HSSFWorkbook wb, int fontSize,
			boolean fontBold, short color) {
		HSSFFont font = wb.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) fontSize);

		if (fontBold) {
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		}
		font.setColor(color);
		return font;
	}
}
