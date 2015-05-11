package com.swing.edi.report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.swing.edi.report.utils.ReportUtils;
import com.swing.edi.reportModel.IReportModel;
import com.swing.edi.reportModel.area.BodyArea;
import com.swing.edi.reportModel.area.HeadArea;
import com.swing.edi.reportModel.area.TailArea;

public class TxtReport implements IReport {

	private IReportModel reportModel;

	private List<Map<String, String>> data;

	public TxtReport() {

	}

	public TxtReport(IReportModel model, List<Map<String, String>> data) {
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

	@Override
	public void doReport(File file) {
		//需要先删除原文件？
		BufferedWriter bw = null;
		try {
			if (!ReportUtils.creatNewFile(file)) {
				System.out.println("创建文件失败！");
				return;
			}

			bw = new BufferedWriter(new FileWriter(file));

			// head
			HeadArea head = reportModel.getHead();
			head.setValue(data.get(0));
			writeData(bw, head.getValue());
			// body
			BodyArea body = reportModel.getBody();
			TailArea tail = reportModel.getTail();
			for (Iterator<Map<String, String>> iterator = data.iterator(); iterator
					.hasNext();) {
				Map<String, String> row = iterator.next();
				body.setValue(row);
				tail.setValue(row);
				writeData(bw, body.getValue());
			}
			// tail
			writeData(bw, tail.getValue());

			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
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

	public void writeData(BufferedWriter bw, List<List<Object>> data)
			throws IOException {
		for (Iterator<List<Object>> iterator = data.iterator(); iterator
				.hasNext();) {
			List<Object> list = (List<Object>) iterator.next();
			for (Iterator<Object> iterator2 = list.iterator(); iterator2
					.hasNext();) {
				Object object = (Object) iterator2.next();
				if (object != null) {

					bw.write(object.toString());
				} else {
					// 为空时
					bw.write("\t");
				}
				// 录完一个添加空格
				bw.write("\t");
			}
			// 添加完了换行
			bw.write("\r\n");
		}
	}

}
