package com.sf.module.EDI.EDIImpl.report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.sf.module.EDI.EDIImpl.report.utils.ReportUtils;
import com.sf.module.EDI.EDIImpl.reportModel.IReportModel;
import com.sf.module.EDI.EDIImpl.reportModel.area.BodyArea;
import com.sf.module.EDI.EDIImpl.reportModel.area.HeadArea;
import com.sf.module.EDI.EDIImpl.reportModel.area.IArea;
import com.sf.module.EDI.EDIImpl.reportModel.area.TailArea;
import com.sf.module.EDI.EDIImpl.reportModel.area.TitleArea;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.ICell;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.IFormat;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.align.AlignFormat;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.border.BorderFormat;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.color.ColorFormat;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.font.FontFormat;

/**
 * 描述：Excel报表
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-4-17      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class ExcelReport implements IReport {

	/**
	 * 宽度系数，即列宽映射到excel中的列宽
	 */
	public static double WIDTH_RATIO = 36.5;

	private IReportModel reportModel;

	private List<Map<String, Object>> data;

	private ReportHelp help;

	public ExcelReport() {

	}

	public ExcelReport(IReportModel model, List<Map<String, Object>> data,
			ReportHelp help) {
		this.reportModel = model;
		this.data = data;
		this.help = help;
	}

	@Override
	public void setReportModel(IReportModel model) {
		this.reportModel = model;
	}

	@Override
	public void setReportData(List<Map<String, Object>> data) {
		this.data = data;

	}

	@Override
	public void setReportHelp(ReportHelp help) {
		this.help = help;
	}

	int row = 0;

	@Override
	public boolean doReport(File file) throws Exception {
		row = 0;
		TitleArea titleA = reportModel.getTitle();
		int rowLimit = titleA.getFileRowLimit();
		// List<Integer> widths = titleA.getWidthList();
		int fileNameNumb = 0;
		int bodyRowNumber = 0;
		boolean multiFile = false;

		FileOutputStream fos = null;
		try {
			if (!ReportUtils.creatNewFile(file)) {
				return false;
			}

			HSSFWorkbook wb = new HSSFWorkbook();

			// 设置工作表名称

			HSSFSheet sheet = wb
					.createSheet(help.getSheetName() == null ? "Sheet1" : help
							.getSheetName());

			// 设置列宽--!为了兼容上一个版本!
			for (int i = 0; i < reportModel.getMaxColumnCount(); i++) {
				sheet.setColumnWidth((short) i,
						(short) (titleA.getWidth(i) * WIDTH_RATIO));
			}
			// sheet.setDefaultColumnWidth((short) 12);

			// head
			HeadArea head = reportModel.getHead();
			// 循环设置表头值
			for (Iterator<Map<String, Object>> iterator = data.iterator(); iterator
					.hasNext();) {
				Map<String, Object> row = iterator.next();
				head.setValue(row);
			}
			writeToSheet(wb, sheet, head);
			// body
			BodyArea body = reportModel.getBody();
			TailArea tail = reportModel.getTail();

			for (Iterator<Map<String, Object>> iterator = data.iterator(); iterator
					.hasNext();) {
				bodyRowNumber++;
				Map<String, Object> rowMap = iterator.next();
				body.setValue(rowMap);
				tail.setValue(rowMap);
				writeToSheet(wb, sheet, body);

				if (rowLimit > 0 && bodyRowNumber == rowLimit
						&& iterator.hasNext()) {
					multiFile = true;
					// 写尾巴
					writeToSheet(wb, sheet, tail);
					// 写文件
					try {
						fos = new FileOutputStream(ReportUtils.continuousFile(
								file, fileNameNumb++));

						wb.write(fos);

					} finally {
						if (fos != null) {
							try {
								fos.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
					bodyRowNumber = 0;
					row = 0;
					clearCacheMap();

					wb = new HSSFWorkbook();

					sheet = wb
							.createSheet(help.getSheetName() == null ? "Sheet1"
									: help.getSheetName());

					// 设置列宽--!为了兼容上一个版本!
					for (int i = 0; i < reportModel.getMaxColumnCount(); i++) {
						sheet.setColumnWidth((short) i,
								(short) (titleA.getWidth(i) * WIDTH_RATIO));
					}
					// sheet.setDefaultColumnWidth((short) 12);
					// 写表头
					writeToSheet(wb, sheet, head);
				}

			}
			// tail
			writeToSheet(wb, sheet, tail);

			// 文件名
			if (multiFile) {
				file = ReportUtils.continuousFile(file, fileNameNumb);
			}
			fos = new FileOutputStream(file);

			wb.write(fos);

			return true;
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 样式表缓存
	 */
	private Map<String, HSSFCellStyle> styleMap = new HashMap<String, HSSFCellStyle>();
	/**
	 * 字体表缓存
	 */
	private Map<String, HSSFFont> fontMap = new HashMap<String, HSSFFont>();

	/**
	 * <pre>
	 * 清空缓存Map
	 * </pre>
	 */
	private void clearCacheMap() {
		styleMap.clear();
		fontMap.clear();
	}

	private HSSFCellStyle getCellStyle(HSSFWorkbook wb, ICell cell) {

		IFormat format = cell.getFormat();
		if (format == null) {// 为空返回默认值
			String key = "default";
			HSSFCellStyle style = styleMap.get(key);
			if (style == null) {
				style = wb.createCellStyle();
				String fontKey = "fontFamily:宋体,style:0,size:10";
				HSSFFont deffont = fontMap.get(fontKey);
				if (deffont == null) {
					deffont = wb.createFont();
					deffont.setFontName("宋体");
					// deffont.setColor(HSSFColor.BLACK.index);//黑色
					// deffont.setFontHeightInPoints((short)10);//10号字
					// deffont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
					// deffont.setItalic(true);//倾斜
					fontMap.put(fontKey, deffont);
				}

				style.setFont(deffont);

				styleMap.put(key, style);
			}
			return style;
		} else {
			StringBuffer styleKey = new StringBuffer();
			StringBuffer fontKey = new StringBuffer();
			AlignFormat align = format.getAlign();
			if (align != null) {
				styleKey.append("align:");
				styleKey.append(align.getAlignment());
				styleKey.append(",valign:");
				styleKey.append(align.getVerticalAlignment());
				styleKey.append(",wrap:");
				styleKey.append(align.isWrap());

			} else {
				// 默认
				styleKey.append("align:0,valign:2,wrap:false");
			}
			FontFormat font = format.getFont();
			if (font != null) {
				styleKey.append(";fontFamily:");
				fontKey.append("fontFamily:");// fontkey没有';'
				styleKey.append(font.getFamily());
				fontKey.append(font.getFamily());
				styleKey.append(",style:");
				fontKey.append(",style:");
				styleKey.append(font.getStyle());
				fontKey.append(font.getStyle());
				styleKey.append(",size:");
				fontKey.append(",size:");
				styleKey.append(font.getSize());
				fontKey.append(font.getSize());

			} else {
				// 默认字体
				styleKey.append(";fontFamily:宋体,style:0,size:10");
				fontKey.append("fontFamily:宋体,style:0,size:10");

			}

			// 文字颜色
			ColorFormat color = format.getColor();
			if (color != null && color.getForeground() != 0) {
				styleKey.append(",fontColor:");
				fontKey.append(",fontColor:");
				styleKey.append(color.getForeground());
				fontKey.append(color.getForeground());
			}

			BorderFormat border = format.getBorder();
			if (border != null) {
				styleKey.append(";top:");
				styleKey.append(border.isTop());
				styleKey.append(",left:");
				styleKey.append(border.isLeft());
				styleKey.append(",bottom:");
				styleKey.append(border.isBottom());
				styleKey.append(",right:");
				styleKey.append(border.isRight());
			} else {
				styleKey.append(";top:false,left:false,bottom:false,right:false");
			}

			// 背景颜色
			if (color != null && color.getBackground() != 0) {
				styleKey.append(";background:");
				styleKey.append(color.getBackground());
			}

			// -------------
			HSSFCellStyle style = styleMap.get(styleKey.toString());
			if (style == null) {
				style = wb.createCellStyle();
				if (align != null) {// 对齐
					// 弄成HSSFCellStyle中一样的
					style.setAlignment((short) align.getAlignment());
					style.setVerticalAlignment((short) align
							.getVerticalAlignment());
					style.setWrapText(align.isWrap());

				}
				HSSFFont fontcache = fontMap.get(fontKey.toString());// 字体
				if (fontcache == null) {
					fontcache = wb.createFont();
					if (font != null) {
						fontcache.setFontName(font.getFamily());
						int fontstyle = font.getStyle();
						if (fontstyle == FontFormat.BOLD) {
							fontcache.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
						} else if (fontstyle == FontFormat.ITALIC) {
							fontcache.setItalic(true);// 倾斜
						} else if (fontstyle == FontFormat.BOLD_ITALIC) {
							fontcache.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
							fontcache.setItalic(true);// 倾斜
						}
						fontcache.setFontHeightInPoints((short) font.getSize());// 号字
					} else {
						fontcache.setFontName("宋体");
					}
					// 颜色
					if (color != null && color.getForeground() != 0) {
						fontcache.setColor((short) color.getForeground());
					}
					fontMap.put(fontKey.toString(), fontcache);
				}
				style.setFont(fontcache);
				// 边框
				if (border != null) {
					if (border.isTop()) {
						style.setBorderTop(HSSFCellStyle.BORDER_THIN);
					}
					if (border.isLeft()) {
						style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					}
					if (border.isBottom()) {
						style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					}
					if (border.isRight()) {
						style.setBorderRight(HSSFCellStyle.BORDER_THIN);
					}
				}

				// 背景颜色
				if (color != null && color.getBackground() != 0) {
					style.setFillForegroundColor((short) color.getBackground());
					style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				}

				// 缓存
				styleMap.put(styleKey.toString(), style);
			}
			return style;
		}
	}

	/**
	 * <pre>
	 * 写入Excel
	 * </pre>
	 * 
	 * @param wb
	 * @param sheet
	 * @param area
	 */
	@SuppressWarnings("deprecation")
	private void writeToSheet(HSSFWorkbook wb, HSSFSheet sheet, IArea area) {
		for (int i = 0; i < area.getRowCount(); i++) {
			HSSFRow hfrow = sheet.createRow(row++);
			for (int j = 0; j < area.getColumnCount(); j++) {
				ICell acell = area.getCellAt(i, j);
				if (acell == null)
					continue;
				HSSFCell ecell = hfrow.createCell((short) j);

				// 设置格式
				ecell.setCellStyle(getCellStyle(wb, acell));

				// Object o = acell.getResult();
				Object o = acell.getFormatResult();
				// 数字形式保存会丢失数字精度
				// if (o instanceof BigDecimal) {
				// ecell.setCellValue(Double.valueOf(o.toString()));
				// } else if (o instanceof Integer) {
				// ecell.setCellValue(Double.valueOf(o.toString()));
				// } else {
				// ecell.setCellValue(o.toString());
				// }
				if (o != null) {
					ecell.setCellValue(o.toString());
				} else {
					ecell.setCellValue(ICell.NULL_VALUE);
				}
			}
		}
	}

}
