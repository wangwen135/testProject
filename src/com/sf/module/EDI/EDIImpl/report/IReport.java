package com.sf.module.EDI.EDIImpl.report;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.sf.module.EDI.EDIImpl.reportModel.IReportModel;

/**
 * 描述：报表接口
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
public interface IReport {
	// 定义具体的报表类
	// 将数据和模型构造，然后从报表类导出报表
	void setReportModel(IReportModel model);

	void setReportData(List<Map<String, Object>> data);
	
	void setReportHelp(ReportHelp help);

	/**
	 * <pre>
	 * 导出报表，将异常抛出
	 * </pre>
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	boolean doReport(File file) throws Exception;
}
