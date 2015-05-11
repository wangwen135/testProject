package com.sf.module.EDI.EDIImpl.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 描述：
 * 键和视图定义
 * 1.数据库字段定义
 * 2.排序字段定义
 * 3.Groovy脚本字段定义
 * 
 * <pre>
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-6-14       313921         Create
 *  2    2012-12-07      313921         修改
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class KeyAndViewDefine {

	/**
	 * 数据库字段列表
	 */
	private static List<String> dataBaseFieldList = new ArrayList<String>();
	static {
		dataBaseFieldList.add("id");
		dataBaseFieldList.add("bno");
		dataBaseFieldList.add("gdesc");
		dataBaseFieldList.add("aweight");
		dataBaseFieldList.add("weight");
		dataBaseFieldList.add("weight_unit");
		dataBaseFieldList.add("quantity");
		dataBaseFieldList.add("pcs");
		dataBaseFieldList.add("cusvalue");
		dataBaseFieldList.add("cus_currency_symbol");
		dataBaseFieldList.add("price");
		dataBaseFieldList.add("invoice_value");
		dataBaseFieldList.add("hscode");
		dataBaseFieldList.add("gmodel");
		dataBaseFieldList.add("srccode");
		dataBaseFieldList.add("srcname");
		dataBaseFieldList.add("descode");
		dataBaseFieldList.add("desname");
		dataBaseFieldList.add("gate_type");
		dataBaseFieldList.add("customs_date");
		dataBaseFieldList.add("exp_customs_date");
		dataBaseFieldList.add("custype");
		dataBaseFieldList.add("cusbatch");
		dataBaseFieldList.add("velist");
		dataBaseFieldList.add("srccompany");
		dataBaseFieldList.add("srcaddr");
		dataBaseFieldList.add("srcpname");
		dataBaseFieldList.add("srctel");
		dataBaseFieldList.add("srcmobile");
		dataBaseFieldList.add("src_union_no");
		dataBaseFieldList.add("src_business_unit_code");
		dataBaseFieldList.add("src_postal_code");
		dataBaseFieldList.add("src_city_code");
		dataBaseFieldList.add("src_city_name");
		dataBaseFieldList.add("srcstate");
		dataBaseFieldList.add("srccountry");
		dataBaseFieldList.add("src_country_code");
		dataBaseFieldList.add("src_country_name");
		dataBaseFieldList.add("descompany");
		dataBaseFieldList.add("desaddr");
		dataBaseFieldList.add("despname");
		dataBaseFieldList.add("destel");
		dataBaseFieldList.add("desmobile");
		dataBaseFieldList.add("des_union_no");
		dataBaseFieldList.add("des_business_unit_code");
		dataBaseFieldList.add("des_postal_code");
		dataBaseFieldList.add("des_city_code");
		dataBaseFieldList.add("des_city_name");
		dataBaseFieldList.add("desstate");
		dataBaseFieldList.add("descountry");
		dataBaseFieldList.add("des_country_code");
		dataBaseFieldList.add("des_country_name");
		dataBaseFieldList.add("tariff");
		dataBaseFieldList.add("tariff_value");
		dataBaseFieldList.add("vat");
		dataBaseFieldList.add("vat_value");
		dataBaseFieldList.add("payment_type");
		dataBaseFieldList.add("feeamt");
		dataBaseFieldList.add("insurance");
		dataBaseFieldList.add("service_charge");
		dataBaseFieldList.add("miscellaneous");
		dataBaseFieldList.add("send_date");
		dataBaseFieldList.add("send_time");
		dataBaseFieldList.add("send_gl_time");
		dataBaseFieldList.add("packageno");
		dataBaseFieldList.add("packagenum");
		dataBaseFieldList.add("express_type");
		dataBaseFieldList.add("fdacode");
		dataBaseFieldList.add("packing");
		dataBaseFieldList.add("storages");
		dataBaseFieldList.add("mawb");
		dataBaseFieldList.add("kjno");
		dataBaseFieldList.add("flightno");
		dataBaseFieldList.add("transport_company_name");
		dataBaseFieldList.add("start_city_code");
		dataBaseFieldList.add("start_city_name");
		dataBaseFieldList.add("end_city_code");
		dataBaseFieldList.add("end_city_name");
		dataBaseFieldList.add("exemption_code");
		dataBaseFieldList.add("exemption_name");
		dataBaseFieldList.add("clearance_code");
		dataBaseFieldList.add("containerno");
		dataBaseFieldList.add("trade_type");
		dataBaseFieldList.add("transaction_method_code");
		dataBaseFieldList.add("transaction_method_name");
		dataBaseFieldList.add("transport_mode");
		dataBaseFieldList.add("source");
		dataBaseFieldList.add("opdt");
		dataBaseFieldList.add("oparea");
		dataBaseFieldList.add("op_gl_time");
		dataBaseFieldList.add("note");
		dataBaseFieldList.add("modify_time");
		dataBaseFieldList.add("modify_by");
		dataBaseFieldList.add("modify_area");
		dataBaseFieldList.add("modify_gl_time");
		dataBaseFieldList.add("tr_gdesc");
		dataBaseFieldList.add("tr_srccompany");
		dataBaseFieldList.add("tr_srcaddr");
		dataBaseFieldList.add("tr_srcname");
		dataBaseFieldList.add("tr_descompany");
		dataBaseFieldList.add("tr_desaddr");
		dataBaseFieldList.add("tr_desname");
		dataBaseFieldList.add("tr_flag");
		dataBaseFieldList.add("sadinfo");
		dataBaseFieldList.add("selected");
		dataBaseFieldList.add("upsign");
		dataBaseFieldList.add("adsign");
		dataBaseFieldList.add("cfsign");
		dataBaseFieldList.add("passign");
		dataBaseFieldList.add("senssign");
		dataBaseFieldList.add("tranflag");
		dataBaseFieldList.add("hscodeflag");
		dataBaseFieldList.add("produce_src");
		dataBaseFieldList.add("fee_currency");
		dataBaseFieldList.add("bak1");
		dataBaseFieldList.add("bak2");
		dataBaseFieldList.add("bak3");
		dataBaseFieldList.add("bak4");
		dataBaseFieldList.add("psrcnm");
		dataBaseFieldList.add("pdesnm");
		dataBaseFieldList.add("audit_by");
		dataBaseFieldList.add("audit_time");
		dataBaseFieldList.add("audit_area");
		dataBaseFieldList.add("audit_gl_time");
		dataBaseFieldList.add("confirm_by");
		dataBaseFieldList.add("confirm_time");
		dataBaseFieldList.add("confirm_area");
		dataBaseFieldList.add("confirm_gl_time");
		dataBaseFieldList.add("org_cusbatch");
		dataBaseFieldList.add("deal_flag");
		dataBaseFieldList.add("edi_flag");
		dataBaseFieldList.add("sub_waybill_no");
		dataBaseFieldList.add("consumption_tax_rate");
		dataBaseFieldList.add("consumption_tax");
		dataBaseFieldList.add("sensvalueflag");
		dataBaseFieldList.add("maxpcsflag");
		dataBaseFieldList.add("maxaweightflag");
		dataBaseFieldList.add("src_business_unit_name");
		dataBaseFieldList.add("des_business_unit_name");
	}

	/**
	 * <pre>
	 * 数据库字段中是否包含该key
	 * </pre>
	 * 
	 * @param key
	 * @return
	 */
	public static boolean containsDBKey(String key) {
		return dataBaseFieldList.contains(key);
	}

	/**
	 * <pre>
	 * 返回结构上不可修改的数据库字段list
	 * </pre>
	 * 
	 * @return
	 */
	public static List<String> getDBFieldList() {
		return Collections.unmodifiableList(dataBaseFieldList);
	}

	/**
	 * <pre>
	 * 获取数据库字段数组
	 * </pre>
	 * 
	 * @return
	 */
	public static String[] getDBFieldArrays() {
		return dataBaseFieldList.toArray(new String[0]);
	}

	/**
	 * 一个KEY和显示对应关系表为以后国际化做准备
	 */
	private static Map<String, String> keyAndView = new HashMap<String, String>();
	static {

		keyAndView.put("count", "总数");
		keyAndView.put("GLOBAL_CONTEXT", "全局上下文");		
		keyAndView.put("", " ");
		//##########################################
		keyAndView.put("id", "编号");
		keyAndView.put("bno", "运单编号");
		keyAndView.put("gdesc", "托寄物内容");
		keyAndView.put("aweight", "实际重量");
		keyAndView.put("weight", "计费重量");
		keyAndView.put("weight_unit", "重量单位");
		keyAndView.put("quantity", "数量/单位");
		keyAndView.put("pcs", "件数");
		keyAndView.put("cusvalue", "申报价值");
		keyAndView.put("cus_currency_symbol", "申报币别");
		keyAndView.put("price", "单价");
		keyAndView.put("invoice_value", "发票申报价值");
		keyAndView.put("hscode", "商品编码");
		keyAndView.put("gmodel", "规格型号");
		keyAndView.put("srccode", "原寄地代码");
		keyAndView.put("srcname", "原寄地名称");
		keyAndView.put("descode", "目的地代码");
		keyAndView.put("desname", "目的地名称");
		keyAndView.put("gate_type", "报关类型");
		keyAndView.put("customs_date", "报关日期");
		keyAndView.put("exp_customs_date", "预报关日期");
		keyAndView.put("custype", "类别");
		keyAndView.put("cusbatch", "报关批次");
		keyAndView.put("velist", "车次");
		keyAndView.put("srccompany", "寄件公司");
		keyAndView.put("srcaddr", "寄件地址");
		keyAndView.put("srcpname", "寄件联络人");
		keyAndView.put("srctel", "寄件电话");
		keyAndView.put("srcmobile", "寄件手机");
		keyAndView.put("src_union_no", "寄件公司统编号");
		keyAndView.put("src_business_unit_code", "寄件公司经营单位编码");
		keyAndView.put("src_postal_code", "寄件地址邮政编码");
		keyAndView.put("src_city_code", "寄件城市三字代码");
		keyAndView.put("src_city_name", "寄件城市名称");
		keyAndView.put("srcstate", "寄件州、省名称");
		keyAndView.put("srccountry", "寄件国别地区代码");
		keyAndView.put("src_country_code", "寄件国别数字代码");
		keyAndView.put("src_country_name", "寄件国别地区名称");
		keyAndView.put("descompany", "收件公司");
		keyAndView.put("desaddr", "收件地址");
		keyAndView.put("despname", "收件联络人");
		keyAndView.put("destel", "收件电话");
		keyAndView.put("desmobile", "收件手机");
		keyAndView.put("des_union_no", "收件公司统编号");
		keyAndView.put("des_business_unit_code", "收件公司经营单位编码");
		keyAndView.put("des_postal_code", "收件地址邮政编码");
		keyAndView.put("des_city_code", "收件城市三字代码");
		keyAndView.put("des_city_name", "收件城市名称");
		keyAndView.put("desstate", "收件州、省名称");
		keyAndView.put("descountry", "收件国别地区代码");
		keyAndView.put("des_country_code", "收件国别数字代码");
		keyAndView.put("des_country_name", "收件国别地区名称");
		keyAndView.put("tariff", "关税税率");
		keyAndView.put("tariff_value", "关税");
		keyAndView.put("vat", "增值税税率");
		keyAndView.put("vat_value", "增值税");
		keyAndView.put("payment_type", "付款方式");
		keyAndView.put("feeamt", "运费");
		keyAndView.put("insurance", "保险费");
		keyAndView.put("service_charge", "服务费");
		keyAndView.put("miscellaneous", "杂费");
		keyAndView.put("send_date", "寄件日期");
		keyAndView.put("send_time", "寄件时间");
		keyAndView.put("send_gl_time", "寄件格林日期时间");
		keyAndView.put("packageno", "袋(包)号");
		keyAndView.put("packagenum", "袋(包)数");
		keyAndView.put("express_type", "快件类型");
		keyAndView.put("fdacode", "FDA指标");
		keyAndView.put("packing", "包装方式");
		keyAndView.put("storages", "货物存处");
		keyAndView.put("mawb", "主提单号");
		keyAndView.put("kjno", "KJ单号");
		keyAndView.put("flightno", "车牌/航班号");
		keyAndView.put("transport_company_name", "运输公司名称");
		keyAndView.put("start_city_code", "始发口岸代码");
		keyAndView.put("start_city_name", "始发口岸名称");
		keyAndView.put("end_city_code", "目的口岸代码");
		keyAndView.put("end_city_name", "目的口岸名称");
		keyAndView.put("exemption_code", "征免性质代码");
		keyAndView.put("exemption_name", "征免性质名称");
		keyAndView.put("clearance_code", "验放代码");
		keyAndView.put("containerno", "集装箱号");
		keyAndView.put("trade_type", "贸易方式");
		keyAndView.put("transaction_method_code", "成交方式代码");
		keyAndView.put("transaction_method_name", "成交方式名称");
		keyAndView.put("transport_mode", "运输方式");
		keyAndView.put("source", "数据来源");
		keyAndView.put("opdt", "操作日期");
		keyAndView.put("oparea", "操作地区");
		keyAndView.put("op_gl_time", "操作格林时间");
		keyAndView.put("note", "备注");
		keyAndView.put("modify_time", "修改时间");
		keyAndView.put("modify_by", "修改人");
		keyAndView.put("modify_area", "修改地区");
		keyAndView.put("modify_gl_time", "修改格林时间");
		keyAndView.put("tr_gdesc", "托寄物内容翻译");
		keyAndView.put("tr_srccompany", "寄件公司翻译");
		keyAndView.put("tr_srcaddr", "寄件地址翻译");
		keyAndView.put("tr_srcname", "寄件联络人翻译");
		keyAndView.put("tr_descompany", "收件公司翻译");
		keyAndView.put("tr_desaddr", "收件地址翻译");
		keyAndView.put("tr_desname", "收件联络人翻译");
		keyAndView.put("tr_flag", "翻译标示");
		keyAndView.put("sadinfo", "参考资料审核途径");
		keyAndView.put("selected", "是否选择");
		keyAndView.put("upsign", "修改标志");
		keyAndView.put("adsign", "审核标志");
		keyAndView.put("cfsign", "审核确认标志");
		keyAndView.put("passign", "清关标志");
		keyAndView.put("senssign", "敏感品名标志");
		keyAndView.put("tranflag", "品名精确翻译匹配标志");
		keyAndView.put("hscodeflag", "商品编码精确匹配标志");
		keyAndView.put("produce_src", "原产地");
		keyAndView.put("fee_currency", "运费币别");
		keyAndView.put("bak1", "备用字段1");
		keyAndView.put("bak2", "备用字段2");
		keyAndView.put("bak3", "备用字段3");
		keyAndView.put("bak4", "备用字段4");
		keyAndView.put("psrcnm", "列印原寄地代码");
		keyAndView.put("pdesnm", "列印目的地代码");
		keyAndView.put("audit_by", "审核人");
		keyAndView.put("audit_time", "审核时间");
		keyAndView.put("audit_area", "审核地区");
		keyAndView.put("audit_gl_time", "审核格林时间");
		keyAndView.put("confirm_by", "审核确认人");
		keyAndView.put("confirm_time", "审核确认时间");
		keyAndView.put("confirm_area", "审核确认地区");
		keyAndView.put("confirm_gl_time", "审核确认格林时间");
		keyAndView.put("org_cusbatch", "预报关批次");
		keyAndView.put("deal_flag", "处理标志");
		keyAndView.put("edi_flag", "edi标志");
		keyAndView.put("sub_waybill_no", "子运单号");
		keyAndView.put("consumption_tax_rate", "消费税税率");
		keyAndView.put("consumption_tax", "消费税");
		keyAndView.put("sensvalueflag", "敏感价值标识");
		keyAndView.put("maxpcsflag", "最大件数标识");
		keyAndView.put("maxaweightflag", "最大重量标识");
		keyAndView.put("src_business_unit_name", "寄件经营单位公司名称");
		keyAndView.put("des_business_unit_name", "收件经营单位公司名称");
		//##########################################
		keyAndView.put("flight_start_date", "航班出发日期");
		keyAndView.put("flight_start_time", "航班出发时间");
		keyAndView.put("flight_arrival_datea", "航班到达日期");
		keyAndView.put("flight_arrival_time", "航班到达时间");
		keyAndView.put("company_name", "公司名称");
		keyAndView.put("filiale_name", "分公司名称");
		keyAndView.put("declaration_type", "申报类型");
		keyAndView.put("shipside_code", "码头/货场代码");
	}

	/**
	 * <pre>
	 * 返回不可修改的keyAndView对应关系
	 * </pre>
	 * 
	 * @return
	 */
	public static Map<String, String> getKeyAndViewMap() {
		return Collections.unmodifiableMap(KeyAndViewDefine.keyAndView);
	}

	/**
	 * <pre>
	 * 返回key的view值
	 * </pre>
	 * 
	 * @param key
	 * @return
	 */
	public static String getViewByKey(String key) {
		if (key == null)
			return null;
		if (KeyAndViewDefine.keyAndView.containsKey(key)) {
			return KeyAndViewDefine.keyAndView.get(key);
		} else {
			return key;
		}
	}

	/**
	 * 排序字段
	 */
	private static List<String> orderByList = new ArrayList<String>();
	static {
		orderByList.add("");
		orderByList.addAll(dataBaseFieldList);
	}

	/**
	 * <pre>
	 * 排序条件中是否包含该key
	 * </pre>
	 * 
	 * @param key
	 * @return
	 */
	public static boolean containsOrderKey(String key) {
		return orderByList.contains(key);
	}

	/**
	 * <pre>
	 * 返回不可修改的排序字段list
	 * </pre>
	 * 
	 * @return
	 */
	public static List<String> getOrderByKey() {
		return Collections.unmodifiableList(orderByList);
	}

	/**
	 * <pre>
	 * 获取排序字段数组
	 * </pre>
	 * 
	 * @return
	 */
	public static String[] getOrderByArrays() {
		return orderByList.toArray(new String[0]);
	}

	/**
	 * groovy字段
	 */
	private static List<String> groovyList = new ArrayList<String>();
	static {
		groovyList.add("GLOBAL_CONTEXT");
		groovyList.addAll(dataBaseFieldList);
	}

	/**
	 * <pre>
	 * groovyList中是否包含该key
	 * </pre>
	 * 
	 * @param key
	 * @return
	 */
	public static boolean containsGroovyKey(String key) {
		return groovyList.contains(key);
	}

	/**
	 * <pre>
	 * 返回不可修改的groovyList
	 * </pre>
	 * 
	 * @return
	 */
	public static List<String> getGroovyList() {
		return Collections.unmodifiableList(groovyList);
	}

	/**
	 * <pre>
	 * 获取groovyList数组
	 * </pre>
	 * 
	 * @return
	 */
	public static String[] getGroovyListArrays() {
		return groovyList.toArray(new String[0]);
	}

}
