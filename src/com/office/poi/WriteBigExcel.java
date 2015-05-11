package com.office.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class WriteBigExcel {

	public static void main(String[] args) {
		try {
			write_Excel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void write_Excel() throws IOException {

		FileOutputStream output = new FileOutputStream(new File("测试excel.xlsx")); // 读取的文件路径
		SXSSFWorkbook wb = new SXSSFWorkbook(10000);// 内存中保留 10000
													// 条数据，以免内存溢出，其余写入 硬盘

		for (int sn = 0; sn < 3; sn++) {// 可以生成N多个工作表
			Sheet sheet = wb.createSheet(String.valueOf(sn));

			wb.setSheetName(sn, "sheet名称" + sn);

			System.out.println("开始生成第[" + (sn + 1) + "]个工作表");

			for (int i = 0; i < 1000000; i++) {
				if (i % 10000 == 0) {
					System.out.println("第[" + i + "]行数据");
				}

				Row row = sheet.createRow(i);
				String[] s = new String[] { "字段一" + i, "字段阿斯蒂芬" + i,
						"字段实得分三" + i, "字段四似懂非懂" + i };

				for (int cols = 0; cols < s.length; cols++) {
					Cell cell = row.createCell(cols);
					cell.setCellType(XSSFCell.CELL_TYPE_STRING);// 文本格式
					sheet.setColumnWidth(cols, s[cols].length() * 384); // 设置单元格宽度
					cell.setCellValue(s[cols]);// 写入内容
				}

			}
		}
		System.out.println("写入文件....");
		wb.write(output);
		output.close();
		System.out.println("完成!");
	}

}
