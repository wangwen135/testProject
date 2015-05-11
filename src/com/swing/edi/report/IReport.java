package com.swing.edi.report;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.swing.edi.reportModel.IReportModel;

public interface IReport {
	//定义具体的报表类
	//将数据和模型构造，然后从报表类导出报表
	void setReportModel(IReportModel model);
	
	void setReportData(List<Map<String, String>> data);
	
	void doReport(File file);
}
