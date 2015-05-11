package com.swing.edi.report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.swing.edi.report.utils.ReportUtils;
import com.swing.edi.reportModel.IReportModel;
import com.swing.edi.reportModel.area.BodyArea;
import com.swing.edi.reportModel.area.HeadArea;
import com.swing.edi.reportModel.area.IArea;
import com.swing.edi.reportModel.area.TailArea;
import com.swing.edi.reportModel.area.cell.ICell;
import com.swing.edi.reportModel.area.cell.part.PartEnum;

public class ExcelReport implements IReport {

	private IReportModel reportModel;

	private List<Map<String, String>> data;

	public ExcelReport() {

	}

	public ExcelReport(IReportModel model, List<Map<String, String>> data) {
		this.reportModel = model;
		this.data = data;
	}

	@Override
	public void setReportModel(IReportModel model) {
		this.reportModel = model;
	}

	@Override
	public void setReportData(List<Map<String, String>> data) {
		this.data = data;

	}

	int row = 0;

	@Override
	public void doReport(File file) {
		row = 0;
		FileOutputStream fos = null;
		try {
			if (!ReportUtils.creatNewFile(file)) {
				System.out.println("创建文件失败！");
				return;
			}

			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFCellStyle style = wb.createCellStyle();
			HSSFSheet sheet = wb.createSheet();
			sheet.setDefaultColumnWidth((short) 15);

			// head
			HeadArea head = reportModel.getHead();
			head.setValue(data.get(0));
			writeToSheet(sheet, style, head);
			// body
			BodyArea body = reportModel.getBody();
			TailArea tail = reportModel.getTail();
			for (Iterator<Map<String, String>> iterator = data.iterator(); iterator
					.hasNext();) {
				Map<String, String> row = iterator.next();
				body.setValue(row);
				tail.setValue(row);
				writeToSheet(sheet, style, body);
			}
			// tail
			writeToSheet(sheet, style, tail);

			fos = new FileOutputStream(file);

			wb.write(fos);

		} catch (IOException e) {
			e.printStackTrace();
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

	@SuppressWarnings("deprecation")
	private void writeToSheet(HSSFSheet sheet, HSSFCellStyle style, IArea area) {
		for (int i = 0; i < area.getRowCount(); i++) {
			HSSFRow hfrow = sheet.createRow(row++);
			for (int j = 0; j < area.getColumnCount(); j++) {
				ICell acell = area.getCellAt(i, j);
				if (acell == null)
					continue;
				HSSFCell ecell = hfrow.createCell((short) j);
				if (acell.findPart(PartEnum.CRLF) != null) {
					style.setWrapText(true);
					ecell.setCellStyle(style);
				}
				Object o = acell.getResult();
				// 数字形式保存会丢失数字精度
				// if (o instanceof BigDecimal) {
				// ecell.setCellValue(Double.valueOf(o.toString()));
				// } else if (o instanceof Integer) {
				// ecell.setCellValue(Double.valueOf(o.toString()));
				// } else {
				// ecell.setCellValue(o.toString());
				// }
				ecell.setCellValue(o.toString());
			}
		}
	}

	@SuppressWarnings({ "deprecation", "unused" })
	private void writeData(HSSFSheet sheet, List<List<Object>> rowData) {
		for (int i = 0; i < rowData.size(); i++) {
			HSSFRow hfrow = sheet.createRow(row++);
			List<Object> list = rowData.get(i);
			for (int j = 0; j < list.size(); j++) {

				HSSFCell cell = hfrow.createCell((short) j);
				Object o = list.get(j);
				if (o != null) {
					cell.setCellValue(o.toString());
				}
			}
		}
	}
}
