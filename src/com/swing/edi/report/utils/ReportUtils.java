package com.swing.edi.report.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.swing.edi.report.ExcelReport;
import com.swing.edi.report.TxtReport;
import com.swing.edi.reportModel.ReportModel;

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
			file.getParentFile().mkdirs();
			return file.createNewFile();
		}
	}

	public static void main(String[] args) {
		try {
			excel();
			txt();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void excel() throws Exception {
		File f2 = new File("d:\\user\\313921\\我的文档\\demo.xls");
		// 导出报表
		StringBuilder sb = new StringBuilder();
		File f1 = new File("d:\\user\\313921\\我的文档\\aaa.xml");

		BufferedReader br = new BufferedReader(new FileReader(f1));
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();

		ReportModel rModel = new ReportModel();
		rModel.fromXml(sb.toString());

		ExcelReport xlsrp = new ExcelReport(rModel, getAnalogData(100));
		xlsrp.doReport(f2);
	}

	public static void txt() throws Exception {

		File f2 = new File("d:\\user\\313921\\我的文档\\demo.txt");
		// 导出报表
		StringBuilder sb = new StringBuilder();
		File f1 = new File("d:\\user\\313921\\我的文档\\aaa.xml");

		BufferedReader br = new BufferedReader(new FileReader(f1));
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();

		ReportModel rModel = new ReportModel();
		rModel.fromXml(sb.toString());

		TxtReport txtrp = new TxtReport(rModel, getAnalogData(100));
		txtrp.doReport(f2);
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
	public static List<Map<String, String>> getAnalogData(int rowCount) {

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (int i = 0; i < rowCount; i++) {
			Map<String, String> row = new HashMap<String, String>();
			row.put("id", i + "_abc_" + i);
			row.put("bno", i + "1235" + i);
			row.put("gdesc", "托寄物内容" + i);
			row.put("aweight", i + "12.2" + i);
			row.put("weight", i + ".1" + i);
			row.put("weight_unit", "KG");
			row.put("quantity", i + "/单位");
			row.put("pcs", "5.1" + i);
			row.put("cusvalue", "10" + i);
			row.put("cus_currency_symbol", "RMB");
			row.put("price", "135.1" + i);
			row.put("invoice_value", "432" + i);
			row.put("hscode", "TX32" + i);
			row.put("gmodel", "规格03型号0" + i);
			row.put("srccode", "0012" + i);
			row.put("srcname", "原寄地名称x" + i);
			row.put("descode", "1235" + i);
			row.put("desname", "目的地名称x" + i);
			row.put("gate_type", "" + i);
			row.put("customs_date", "2011-02-12");
			row.put("exp_customs_date", "2011-02-12");
			row.put("custype", "类别102" + i);
			row.put("cusbatch", "报关批次" + i);
			row.put("velist", "车次" + i);
			row.put("srccompany", "寄件公司" + i);
			row.put("srcaddr", "寄件地址" + i);
			row.put("srcpname", "寄件联络人" + i);
			row.put("srctel", "寄件电话" + i);
			row.put("srcmobile", "寄件手机" + i);
			row.put("src_union_no", "寄件公司统编号" + i);
			row.put("src_business_unit_code", "寄件公司经营单位编码" + i);
			row.put("src_postal_code", "寄件地址邮政编码" + i);
			row.put("src_city_code", "寄件城市三字代码" + i);
			row.put("src_city_name", "寄件城市名称" + i);
			row.put("srcstate", "寄件州、省名称" + i);
			row.put("srccountry", "寄件国别地区代码" + i);
			row.put("src_country_code", "寄件国别数字代码" + i);
			row.put("src_country_name", "寄件国别地区名称" + i);
			row.put("descompany", "收件公司" + i);
			row.put("desaddr", "收件地址" + i);
			row.put("despname", "收件联络人" + i);
			row.put("destel", "收件电话" + i);
			row.put("desmobile", "收件手机" + i);
			row.put("des_union_no", "收件公司统编号" + i);
			row.put("des_business_unit_code", "收件公司经营单位编码" + i);
			row.put("des_postal_code", "收件地址邮政编码" + i);
			row.put("des_city_code", "收件城市三字代码" + i);
			row.put("des_city_name", "收件城市名称" + i);
			row.put("desstate", "收件州、省名称" + i);
			row.put("descountry", "收件国别地区代码" + i);
			row.put("des_country_code", "收件国别数字代码" + i);
			row.put("des_country_name", "收件国别地区名称" + i);
			row.put("tariff", "123" + i);
			row.put("tariff_value", "123" + i);
			row.put("vat", "111" + i);
			row.put("vat_value", "11" + i);
			row.put("payment_type", "付款方式" + i);
			row.put("feeamt", "123" + i);
			row.put("insurance", "123" + i);
			row.put("service_charge", "123" + i);
			row.put("miscellaneous", "123" + i);
			row.put("send_date", "2012-01-12");
			row.put("send_time", "20:12:52");
			row.put("send_gl_time", "寄件格林日期时间" + i);
			row.put("packageno", "袋(包)号" + i);
			row.put("packagenum", "袋(包)数" + i);
			row.put("express_type", "快件类型" + i);
			row.put("fdacode", "FDA指标" + i);
			row.put("packing", "包装方式" + i);
			row.put("storages", "货物存处" + i);
			row.put("mawb", "主提单号" + i);
			row.put("kjno", "KJ单号" + i);
			row.put("flightno", "车牌/航班号" + i);
			row.put("transport_company_name", "运输公司名称" + i);
			row.put("start_city_code", "始发城市代码" + i);
			row.put("start_city_name", "始发城市名称" + i);
			row.put("end_city_code", "目的城市代码" + i);
			row.put("end_city_name", "目的城市名称" + i);
			row.put("exemption_code", "征免性质代码" + i);
			row.put("exemption_name", "征免性质名称" + i);
			row.put("clearance_code", "验放代码" + i);
			row.put("containerno", "集装箱号" + i);
			row.put("trade_type", "贸易方式" + i);
			row.put("transaction_method_code", "成交方式代码" + i);
			row.put("transaction_method_name", "成交方式名称" + i);
			row.put("transport_mode", "运输方式" + i);
			row.put("source", "数据来源" + i);
			row.put("opdt", "操作日期" + i);
			row.put("oparea", "操作地区" + i);
			row.put("op_gl_time", "操作格林时间" + i);
			row.put("note", "备注" + i);
			row.put("modify_time", "修改时间" + i);
			row.put("modify_by", "修改人" + i);
			row.put("modify_area", "修改地区" + i);
			row.put("modify_gl_time", "修改格林时间" + i);
			row.put("tr_gdesc", "托寄物内容翻译" + i);
			row.put("tr_srccompany", "寄件公司翻译" + i);
			row.put("tr_srcaddr", "寄件地址翻译" + i);
			row.put("tr_srcname", "寄件联络人翻译" + i);
			row.put("tr_descompany", "收件公司翻译" + i);
			row.put("tr_desaddr", "收件地址翻译" + i);
			row.put("tr_desname", "收件联络人翻译" + i);
			row.put("tr_flag", "翻译标示" + i);
			row.put("sadinfo", "参考资料审核途径" + i);
			row.put("selected", "是否选择" + i);
			row.put("upsign", "修改标志" + i);
			row.put("adsign", "审核标志" + i);
			row.put("cfsign", "审核确认标志" + i);
			row.put("passign", "清关标志" + i);
			row.put("confirm_gl_time", "审核确认格林时间" + i);
			row.put("confirm_area", "审核确认地区" + i);
			row.put("confirm_time", "审核确认时间" + i);
			row.put("confirm_by", "审核确认人" + i);
			row.put("audit_area", "审核地区" + i);
			row.put("audit_gl_time", "审核格林时间" + i);
			row.put("audit_by", "审核人" + i);
			row.put("audit_time", "审核时间" + i);
			row.put("psrcnm", "列印原寄地代码" + i);
			row.put("pdesnm", "列印目的地代码" + i);
			row.put("bak1", "备用字段1" + i);
			row.put("bak2", "备用字段2" + i);
			row.put("bak3", "备用字段3" + i);
			row.put("bak4", "备用字段4" + i);
			row.put("produce_src", "原产地" + i);
			row.put("fee_currency", "运费币别" + i);
			row.put("tranflag", "品名精确翻译匹配标志" + i);
			row.put("hscodeflag", "商品编码精确匹配标志" + i);
			row.put("senssign", "敏感品名标志" + i);
			row.put("org_cusbatch", "原始报关批次" + i);
			// #####################################################
			list.add(row);
		}
		return list;
	}

}
