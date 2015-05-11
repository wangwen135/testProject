package com.sf.module.EDI.EDIImpl.report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sf.module.EDI.EDIImpl.report.utils.ReportUtils;
import com.sf.module.EDI.EDIImpl.reportModel.IReportModel;
import com.sf.module.EDI.EDIImpl.reportModel.area.BodyArea;
import com.sf.module.EDI.EDIImpl.reportModel.area.HeadArea;
import com.sf.module.EDI.EDIImpl.reportModel.area.IArea;
import com.sf.module.EDI.EDIImpl.reportModel.area.TailArea;
import com.sf.module.EDI.EDIImpl.reportModel.area.TitleArea;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.ICell;

/**
 * 描述：文本报表
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-2-14      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class TxtReport implements IReport {

	private IReportModel reportModel;

	private List<Map<String, Object>> data;

	private ReportHelp help;

	/**
	 * 回车换行--只会判断一次
	 */
	public String CRLF = "\r\n";

	public TxtReport() {

	}

	public TxtReport(IReportModel model, List<Map<String, Object>> data,
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

	/**
	 * <pre>
	 * 转换回车换行符号
	 * </pre>
	 * 
	 * @return
	 */
	private void convertCRLF() {
		String crlf = help.getCrlf();
		if ("回车换行".equals(crlf)) {
			crlf = "\r\n";
		} else if ("回车".equals(crlf)) {
			crlf = "\r";
		} else if ("换行".equals(crlf)) {
			crlf = "\n";
		} else if ("TAB".equals(crlf)) {
			crlf = "\t";
		}

		this.CRLF = crlf;
		// 如果为null
		if (CRLF == null) {
			CRLF = ICell.NULL_VALUE;
		}
	}

	@Override
	public boolean doReport(File file) throws Exception {
		convertCRLF();
		TitleArea titleA = reportModel.getTitle();
		int rowLimit = titleA.getFileRowLimit();
		int fileNameNumb = 1;
		int bodyRowNumber = 0;

		BufferedWriter bw = null;
		try {
			if (!ReportUtils.creatNewFile(file)) {
				return false;
			}
			bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file), help.getFileEncode()));

			// head
			HeadArea head = reportModel.getHead();
			// 循环设置表头
			for (Iterator<Map<String, Object>> iterator = data.iterator(); iterator
					.hasNext();) {
				Map<String, Object> row = iterator.next();
				head.setValue(row);
			}
			writeData(bw, head);
			// body
			BodyArea body = reportModel.getBody();
			TailArea tail = reportModel.getTail();
			for (Iterator<Map<String, Object>> iterator = data.iterator(); iterator
					.hasNext();) {
				bodyRowNumber++;
				Map<String, Object> row = iterator.next();
				body.setValue(row);
				tail.setValue(row);
				writeData(bw, body);

				if (rowLimit > 0 && bodyRowNumber == rowLimit
						&& iterator.hasNext()) {
					// 写尾巴
					writeData(bw, tail);
					// 写文件
					bw.flush();

					bw.close();// 关闭一般不会出错

					bodyRowNumber = 0;

					bw = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(ReportUtils.continuousFile(
									file, fileNameNumb++)),
							help.getFileEncode()));

					// 写表头
					writeData(bw, head);
				}

			}
			// tail
			writeData(bw, tail);

			bw.flush();
			return true;

		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * <pre>
	 * 写数据
	 * </pre>
	 * 
	 * @param bw
	 * @param area
	 * @throws IOException
	 */
	private void writeData(BufferedWriter bw, IArea area) throws IOException {
		for (int i = 0; i < area.getRowCount(); i++) {
			for (int j = 0; j < area.getColumnCount(); j++) {
				ICell acell = area.getCellAt(i, j);

				// if (acell == null) continue;//空单元格忽略
				if (acell != null) {// 空单元格计算分隔符号
					// Object o = acell.getResult();
					Object o = acell.getFormatResult();
					if (o != null) {
						bw.write(o.toString());
					} else {
						bw.write(ICell.NULL_VALUE);
					}
				}
				if (help.getSeparator() != null)
					bw.write(help.getSeparator());
			}
			// 添加完了换行
			bw.write(CRLF);
		}
	}

}
