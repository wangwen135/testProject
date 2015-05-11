package com.sf.module.EDI.EDIImpl.report.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


import com.sf.module.EDI.EDIImpl.TempletDragEditor;
import com.sf.module.EDI.EDIImpl.report.ExcelReport;
import com.sf.module.EDI.EDIImpl.report.ReportHelp;
import com.sf.module.EDI.EDIImpl.report.TxtReport;
import com.sf.module.EDI.EDIImpl.reportModel.ReportModel;
import com.sf.module.EDI.EDIImpl.reportModel.area.BodyArea;
import com.sf.module.EDI.EDIImpl.reportModel.area.IArea;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.ICell;

/**
 * 描述：报表工具类
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-2-3      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class ReportUtils {

	/**
	 * 文件名检查正则表达式
	 */
	public static final String FILE_NAME_CHECK_REGEX = "[\\\\/:*?\"<>|]";

	/**
	 * 文件名检查正则表达式的编译表示形式
	 */
	public static Pattern fileNamePattern = Pattern
			.compile(FILE_NAME_CHECK_REGEX);

	/**
	 * <pre>
	 * 检查文件名，返回文件名中的特殊字符
	 * </pre>
	 * 
	 * @param fileName
	 *            文件名
	 * @return 文件名中的特殊字符，如果没有特殊字符则返回空
	 */
	public static String checkFileName(String fileName) {
		Matcher mt = fileNamePattern.matcher(fileName);
		StringBuffer strBuf = new StringBuffer();
		boolean b = false;
		while (mt.find()) {
			if (b) {
				strBuf.append(" ");
			}
			b = true;
			strBuf.append(mt.group());
		}
		return strBuf.toString();
	}

	/**
	 * <pre>
	 * 替换文件名中的特殊字符
	 * </pre>
	 * 
	 * @param fileName
	 *            文件名
	 * @return 新的无特殊字符的文件名
	 */
	public static String replaceFileName(String fileName) {
		Matcher mt = fileNamePattern.matcher(fileName);
		return mt.replaceAll("");
	}

	/**
	 * <pre>
	 * 复制文件
	 * </pre>
	 * 
	 * @param src
	 *            原文件
	 * @param desc
	 *            目标文件
	 * @throws IOException
	 */
	public static void copyFile(File src, File desc) throws IOException {
		BufferedInputStream ins = null;
		BufferedOutputStream outs = null;
		try {
			ins = new BufferedInputStream(new FileInputStream(src));

			if (!desc.getParentFile().mkdirs()) {
				if (!desc.getParentFile().exists()) {
					throw new IOException("创建目标文件必要的上级目录失败");
				}
			}

			outs = new BufferedOutputStream(new FileOutputStream(desc));

			byte[] b = new byte[8192];
			int len;
			while ((len = ins.read(b)) != -1) {
				outs.write(b, 0, len);
			}

			outs.flush();

		} finally {
			if (ins != null) {
				try {
					ins.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (outs != null) {
				try {
					outs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * <pre>
	 * 创建文件和必须的上级目录
	 * </pre>
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static boolean creatNewFile(File file) throws IOException {
		if (file.exists()) {
			return true;
		} else {
			// 创建新文件
			if (!file.getParentFile().mkdirs()) {
				if (!file.getParentFile().exists()) {
					return false;
				}
			}
			return file.createNewFile();
		}
	}

	/**
	 * <pre>
	 * 连续的文件名，将指定数字拼接到文件后缀名之前
	 * </pre>
	 * 
	 * @param fileName
	 *            原文件名
	 * @param number
	 *            数字
	 * @return 拼接之后的文件名
	 */
	public static String continuousFile(String fileName, int number) {
		StringBuffer strbuf = new StringBuffer(fileName);
		if (fileName.contains(".")) {
			strbuf.insert(fileName.lastIndexOf('.'), number);
		} else {
			strbuf.append(number);
		}
		return strbuf.toString();
	}

	/**
	 * <pre>
	 * 连续的文件名，将指定数字拼接到文件后缀名之前
	 * 如果 number = 0 直接返回file
	 * </pre>
	 * 
	 * @param file
	 *            原文件
	 * @param number
	 *            序号
	 * @return 目标文件
	 */
	public static File continuousFile(File file, int number) {
		if (number == 0) {
			return file;
		}
		File p = file.getParentFile();
		String name = continuousFile(file.getName(), number);
		return new File(p, name);
	}

	/**
	 * <pre>
	 * 根据模板和数据计算文件名
	 * </pre>
	 * 
	 * @param xml
	 *            文件名模板
	 * @param data
	 *            数据
	 * @return 返回计算之后的文件名
	 * @throws Exception
	 */
	public static String getFileName(String xml, List<Map<String, Object>> data)
			throws Exception {
		Document doc = DocumentHelper.parseText(xml);
		Element root = doc.getRootElement();
		BodyArea fileNameArea = new BodyArea();
		fileNameArea.fromXml(root);
		return getFileName(fileNameArea, data);

	}

	/**
	 * <pre>
	 * 根据模板和数据计算文件名
	 * </pre>
	 * 
	 * @param area
	 *            文件名区域
	 * @param data
	 *            数据
	 * @return 返回计算之后的文件名
	 * @throws Exception
	 */
	public static String getFileName(IArea area, List<Map<String, Object>> data)
			throws Exception {
		// 设置全局变量
		area.setReportModel(new ReportModel());
		area.rebuildChildContext();
		for (Map<String, Object> map : data) {
			area.setValue(map);
		}
		List<List<Object>> contentList = area.getValue();
		List<Object> nameList = contentList.get(0);
		StringBuilder sb_fileName = new StringBuilder();
		for (Object object : nameList) {
			if (object != null)
				sb_fileName.append(object);
		}
		return sb_fileName.toString();
	}

	/**
	 * <pre>
	 * 导出报表
	 * </pre>
	 * 
	 * @param template
	 *            模板
	 * @param file
	 *            导出文件
	 * @param data
	 *            数据
	 * @param fileType
	 *            文件类型
	 * @param separator
	 *            文本文件列分隔符
	 * @throws Exception
	 */
	public static boolean doReport(String template, File file,
			List<Map<String, Object>> data, ReportHelp help) throws Exception {
		ReportModel rModel = new ReportModel();
		rModel.fromXml(template);
		rModel.rebuildChildContext();
		String fileType = help.getFiletype();
		if (TempletDragEditor.FILE_TYPE_EXCEL.equals(fileType)) {
			ExcelReport xlsrp = new ExcelReport(rModel, data, help);
			return xlsrp.doReport(file);
		} else if (TempletDragEditor.FILE_TYPE_TXT.equals(fileType)) {
			TxtReport txtrp = new TxtReport(rModel, data, help);
			return txtrp.doReport(file);
		}
		return false;
	}

	/**
	 * <pre>
	 * 格式该cell并返回值
	 * </pre>
	 * 
	 * @param cell
	 * @return
	 */
	public static String formatCellResult(ICell cell) {
		if (cell == null) {
			return ICell.NULL_VALUE;
		}
		// 格式化
		return "";
	}

	/**
	 * <pre>
	 * 获取模拟数据
	 * </pre>
	 * 
	 * @param rowCount
	 *            行数
	 * @return
	 */
	public static List<Map<String, Object>> getAnalogData(int rowCount) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < rowCount; i++) {
			Map<String, Object> row = new HashMap<String, Object>();
			row.put("id", "SF23123152234");
			row.put("bno", "12354565485");
			row.put("sub_waybill_no", "子运单号");
			row.put("gdesc", "托寄物内容");
			row.put("aweight", 1352.2);
			row.put("weight", 113);
			row.put("weight_unit", "KG");
			row.put("quantity", "1/单位");
			row.put("pcs", 5.12335);
			row.put("cusvalue", 100);
			row.put("cus_currency_symbol", "RMB");
			row.put("price", "135.123");
			row.put("invoice_value", "4134322");
			row.put("hscode", "TX32232");
			row.put("gmodel", "规格03型号01");
			row.put("srccode", "00121");
			row.put("srcname", "原寄地名称xxxxxxx");
			row.put("descode", "12352");
			row.put("desname", "目的地名称xxxxxxxxx");
			row.put("gate_type", "1");
			row.put("customs_date", "2011-02-12");
			row.put("exp_customs_date", "2011-02-12");
			row.put("custype", "类别102");
			row.put("cusbatch", "报关批次");
			row.put("velist", "车次");
			row.put("srccompany", "寄件公司");
			row.put("srcaddr", "寄件地址");
			row.put("srcpname", "寄件联络人");
			row.put("srctel", "寄件电话");
			row.put("srcmobile", "寄件手机");
			row.put("src_union_no", "寄件公司统编号");
			row.put("src_business_unit_code", "寄件公司经营单位编码");
			row.put("src_postal_code", "寄件地址邮政编码");
			row.put("src_city_code", "寄件城市三字代码");
			row.put("src_city_name", "寄件城市名称");
			row.put("srcstate", "寄件州、省名称");
			row.put("srccountry", "寄件国别地区代码");
			row.put("src_country_code", "寄件国别数字代码");
			row.put("src_country_name", "寄件国别地区名称");
			row.put("descompany", "收件公司");
			row.put("desaddr", "收件地址");
			row.put("despname", "收件联络人");
			row.put("destel", "收件电话");
			row.put("desmobile", "收件手机");
			row.put("des_union_no", "收件公司统编号");
			row.put("des_business_unit_code", "收件公司经营单位编码");
			row.put("des_postal_code", "收件地址邮政编码");
			row.put("des_city_code", "收件城市三字代码");
			row.put("des_city_name", "收件城市名称");
			row.put("desstate", "收件州、省名称");
			row.put("descountry", "收件国别地区代码");
			row.put("des_country_code", "收件国别数字代码");
			row.put("des_country_name", "收件国别地区名称");
			row.put("tariff", "1.2");// 关税税率
			row.put("tariff_value", "1.1");// 关税
			row.put("vat", "1.3");// 增值税税率
			row.put("vat_value", "1.4");// 增值税
			row.put("consumption_tax_rate", "1.5");// 消费税税率
			row.put("consumption_tax", "1.6");// 消费税

			row.put("payment_type", "付款方式");
			row.put("feeamt", "运费");
			row.put("insurance", "保险费");
			row.put("service_charge", "服务费");
			row.put("miscellaneous", "杂费");
			row.put("send_date", "寄件日期");
			row.put("send_time", "寄件时间");
			row.put("send_gl_time", "寄件格林日期时间");
			row.put("packageno", "袋(包)号");
			row.put("packagenum", "袋(包)数");
			row.put("express_type", "快件类型");
			row.put("fdacode", "FDA指标");
			row.put("packing", "包装方式");
			row.put("storages", "货物存处");
			row.put("mawb", "主提单号");
			row.put("kjno", "KJ单号");
			row.put("flightno", "车牌/航班号");
			row.put("transport_company_name", "运输公司名称");
			row.put("start_city_code", "始发城市代码");
			row.put("start_city_name", "始发城市名称");
			row.put("end_city_code", "目的城市代码");
			row.put("end_city_name", "目的城市名称");
			row.put("exemption_code", "征免性质代码");
			row.put("exemption_name", "征免性质名称");
			row.put("clearance_code", "验放代码");
			row.put("containerno", "集装箱号");
			row.put("trade_type", "贸易方式");
			row.put("transaction_method_code", "成交方式代码");
			row.put("transaction_method_name", "成交方式名称");
			row.put("transport_mode", "运输方式");
			row.put("source", "数据来源");
			row.put("opdt", "操作日期");
			row.put("oparea", "操作地区");
			row.put("op_gl_time", "操作格林时间");
			row.put("note", "备注");
			row.put("modify_time", "修改时间");
			row.put("modify_by", "修改人");
			row.put("modify_area", "修改地区");
			row.put("modify_gl_time", "修改格林时间");
			row.put("tr_gdesc", "托寄物内容翻译");
			row.put("tr_srccompany", "寄件公司翻译");
			row.put("tr_srcaddr", "寄件地址翻译");
			row.put("tr_srcname", "寄件联络人翻译");
			row.put("tr_descompany", "收件公司翻译");
			row.put("tr_desaddr", "收件地址翻译");
			row.put("tr_desname", "收件联络人翻译");
			row.put("tr_flag", "翻译标示");
			row.put("sadinfo", "参考资料审核途径");
			row.put("selected", "是否选择");
			row.put("upsign", "修改标志");
			row.put("adsign", "审核标志");
			row.put("cfsign", "审核确认标志");
			row.put("passign", "清关标志");
			row.put("confirm_gl_time", "审核确认格林时间");
			row.put("confirm_area", "审核确认地区");
			row.put("confirm_time", "审核确认时间");
			row.put("confirm_by", "审核确认人");
			row.put("audit_area", "审核地区");
			row.put("audit_gl_time", "审核格林时间");
			row.put("audit_by", "审核人");
			row.put("audit_time", "审核时间");
			row.put("psrcnm", "列印原寄地代码");
			row.put("pdesnm", "列印目的地代码");
			row.put("bak1", "备用字段1");
			row.put("bak2", "备用字段2");
			row.put("bak3", "备用字段3");
			row.put("bak4", "备用字段4");
			row.put("produce_src", "原产地");
			row.put("fee_currency", "运费币别");
			row.put("tranflag", "品名精确翻译匹配标志");
			row.put("hscodeflag", "商品编码精确匹配标志");
			row.put("senssign", "敏感品名标志");
			row.put("org_cusbatch", "原始报关批次");

			row.put("sensvalueflag", "N");// 敏感价值标识
			row.put("maxpcsflag", "N");// 最大件数标识
			row.put("maxaweightflag", "N");// 最大重量标识
			row.put("src_business_unit_name", "寄件经营单位公司名称");
			row.put("des_business_unit_name", " 收件经营单位公司名称");

			// #####################################################
			list.add(row);
		}
		return list;
	}

}
