package com.office.rtfTemplate.expWord.model;

import java.util.HashMap;
import java.util.Map;

import com.office.rtfTemplate.expWord.ExpWordUtils;

/**
 * 描述： 各口岸报关资料表模型<br>
 * 暂时不用
 * 
 * <pre>
 * 
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-2-27      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class CustomModel {
	// map
	public Map<String, String> cmap;
	// 定义一个用做循环的变量
	public String f;// 用于循环的变量

	public String id; // 编号
	public String bno; // 运单编号
	public String gdesc; // 托寄物内容
	public String aweight; // 实际重量
	public String weight; // 计费重量
	public String weight_unit; // 重量单位
	public String quantity; // 数量/单位
	public String pcs; // 件数
	public String cusvalue; // 申报价值
	public String cus_currency_symbol; // 申报币别
	public String price; // 单价
	public String invoice_value; // 发票申报价值
	public String hscode; // 商品编码
	public String gmodel; // 规格型号
	public String srccode; // 原寄地代码
	public String srcname; // 原寄地名称
	public String descode; // 目的地代码
	public String desname; // 目的地名称
	public String gate_type; // 报关类型
	public String customs_date; // 报关日期
	public String exp_customs_date; // 预报关日期
	public String custype; // 类别
	public String cusbatch; // 报关批次
	public String velist; // 车次
	public String srccompany; // 寄件公司
	public String srcaddr; // 寄件地址
	public String srcpname; // 寄件联络人
	public String srctel; // 寄件电话
	public String srcmobile; // 寄件手机
	public String src_union_no; // 寄件公司统编号
	public String src_business_unit_code; // 寄件公司经营单位编码
	public String src_postal_code; // 寄件地址邮政编码
	public String src_city_code; // 寄件城市三字代码
	public String src_city_name; // 寄件城市名称
	public String srcstate; // 寄件州、省名称
	public String srccountry; // 寄件国别地区代码
	public String src_country_code; // 寄件国别数字代码
	public String src_country_name; // 寄件国别地区名称
	public String descompany; // 收件公司
	public String desaddr; // 收件地址
	public String despname; // 收件联络人
	public String destel; // 收件电话
	public String desmobile; // 收件手机
	public String des_union_no; // 收件公司统编号
	public String des_business_unit_code; // 收件公司经营单位编码
	public String des_postal_code; // 收件地址邮政编码
	public String des_city_code; // 收件城市三字代码
	public String des_city_name; // 收件城市名称
	public String desstate; // 收件州、省名称
	public String descountry; // 收件国别地区代码
	public String des_country_code; // 收件国别数字代码
	public String des_country_name; // 收件国别地区名称
	public String tariff; // 关税税率
	public String tariff_value; // 关税
	public String vat; // 增值税税率
	public String vat_value; // 增值税
	public String payment_type; // 付款方式
	public String feeamt; // 运费
	public String insurance; // 保险费
	public String service_charge; // 服务费
	public String miscellaneous; // 杂费
	public String send_date; // 寄件日期
	public String send_time; // 寄件时间
	public String send_gl_time; // 寄件格林日期时间
	public String packageno; // 袋(包)号
	public String packagenum; // 袋(包)数
	public String express_type; // 快件类型
	public String fdacode; // FDA指标
	public String packing; // 包装方式
	public String storages; // 货物存处
	public String mawb; // 主提单号
	public String kjno; // KJ单号
	public String flightno; // 车牌/航班号
	public String transport_company_name; // 运输公司名称
	public String start_city_code; // 始发城市代码
	public String start_city_name; // 始发城市名称
	public String end_city_code; // 目的城市代码
	public String end_city_name; // 目的城市名称
	public String exemption_code; // 征免性质代码
	public String exemption_name; // 征免性质名称
	public String clearance_code; // 验放代码
	public String containerno; // 集装箱号
	public String trade_type; // 贸易方式
	public String transaction_method_code; // 成交方式代码
	public String transaction_method_name; // 成交方式名称
	public String transport_mode; // 运输方式
	public String source; // 数据来源
	public String opdt; // 操作日期
	public String oparea; // 操作地区
	public String op_gl_time; // 操作格林时间
	public String note; // 备注
	public String modify_time; // 修改时间
	public String modify_by; // 修改人
	public String modify_area; // 修改地区
	public String modify_gl_time; // 修改格林时间
	public String tr_gdesc; // 托寄物内容翻译
	public String tr_srccompany; // 寄件公司翻译
	public String tr_srcaddr; // 寄件地址翻译
	public String tr_srcname; // 寄件联络人翻译
	public String tr_descompany; // 收件公司翻译
	public String tr_desaddr; // 收件地址翻译
	public String tr_desname; // 收件联络人翻译
	public String tr_flag; // 翻译标示
	public String sadinfo; // 参考资料审核途径:1、录入时审核"录入时审核＋用户名＋计算机名＋日期时间";2、"修改中审核＋用户名＋计算机名＋日期时间"
	public String selected; // 是否选择
	public String upsign; // 修改标志
	public String adsign; // 审核标志
	public String cfsign; // 审核确认标志
	public String passign; // 清关标志
	public String confirm_gl_time; // 审核确认格林时间
	public String confirm_area; // 审核确认地区
	public String confirm_time; // 审核确认时间
	public String confirm_by; // 审核确认人
	public String audit_area; // 审核地区
	public String audit_gl_time; // 审核格林时间
	public String audit_by; // 审核人
	public String audit_time; // 审核时间
	public String psrcnm; // 列印原寄地代码
	public String pdesnm; // 列印目的地代码
	public String bak1; // 备用字段1
	public String bak2; // 备用字段2
	public String bak3; // 备用字段3
	public String bak4; // 备用字段4
	public String produce_src; // 原产地
	public String fee_currency; // 运费币别
	public String tranflag; // 品名精确翻译匹配标志
	public String hscodeflag; // 商品编码精确匹配标志
	public String senssign; // 敏感品名标志
	public String org_cusbatch; // 原始报关批次
	public String deal_flag; // 处理标志
	public String edi_flag; // edi标志

	// private CustomModel() {
	// // 受保护的构造方法，只能从fromMap方法构建对象
	// }

	// ###################### start get 方法 ######################

	public Map<String, String> getCmap() {
		return cmap;
	}

	public String getF() {
		return f;
	}

	public String getId() {
		return id;
	}

	public String getBno() {
		return bno;
	}

	public String getGdesc() {
		return gdesc;
	}

	public String getAweight() {
		return aweight;
	}

	public String getWeight() {
		return weight;
	}

	public String getWeight_unit() {
		return weight_unit;
	}

	public String getQuantity() {
		return quantity;
	}

	public String getPcs() {
		return pcs;
	}

	public String getCusvalue() {
		return cusvalue;
	}

	public String getCus_currency_symbol() {
		return cus_currency_symbol;
	}

	public String getPrice() {
		return price;
	}

	public String getInvoice_value() {
		return invoice_value;
	}

	public String getHscode() {
		return hscode;
	}

	public String getGmodel() {
		return gmodel;
	}

	public String getSrccode() {
		return srccode;
	}

	public String getSrcname() {
		return srcname;
	}

	public String getDescode() {
		return descode;
	}

	public String getDesname() {
		return desname;
	}

	public String getGate_type() {
		return gate_type;
	}

	public String getCustoms_date() {
		return customs_date;
	}

	public String getExp_customs_date() {
		return exp_customs_date;
	}

	public String getCustype() {
		return custype;
	}

	public String getCusbatch() {
		return cusbatch;
	}

	public String getVelist() {
		return velist;
	}

	public String getSrccompany() {
		return srccompany;
	}

	public String getSrcaddr() {
		return srcaddr;
	}

	public String getSrcpname() {
		return srcpname;
	}

	public String getSrctel() {
		return srctel;
	}

	public String getSrcmobile() {
		return srcmobile;
	}

	public String getSrc_union_no() {
		return src_union_no;
	}

	public String getSrc_business_unit_code() {
		return src_business_unit_code;
	}

	public String getSrc_postal_code() {
		return src_postal_code;
	}

	public String getSrc_city_code() {
		return src_city_code;
	}

	public String getSrc_city_name() {
		return src_city_name;
	}

	public String getSrcstate() {
		return srcstate;
	}

	public String getSrccountry() {
		return srccountry;
	}

	public String getSrc_country_code() {
		return src_country_code;
	}

	public String getSrc_country_name() {
		return src_country_name;
	}

	public String getDescompany() {
		return descompany;
	}

	public String getDesaddr() {
		return desaddr;
	}

	public String getDespname() {
		return despname;
	}

	public String getDestel() {
		return destel;
	}

	public String getDesmobile() {
		return desmobile;
	}

	public String getDes_union_no() {
		return des_union_no;
	}

	public String getDes_business_unit_code() {
		return des_business_unit_code;
	}

	public String getDes_postal_code() {
		return des_postal_code;
	}

	public String getDes_city_code() {
		return des_city_code;
	}

	public String getDes_city_name() {
		return des_city_name;
	}

	public String getDesstate() {
		return desstate;
	}

	public String getDescountry() {
		return descountry;
	}

	public String getDes_country_code() {
		return des_country_code;
	}

	public String getDes_country_name() {
		return des_country_name;
	}

	public String getTariff() {
		return tariff;
	}

	public String getTariff_value() {
		return tariff_value;
	}

	public String getVat() {
		return vat;
	}

	public String getVat_value() {
		return vat_value;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public String getFeeamt() {
		return feeamt;
	}

	public String getInsurance() {
		return insurance;
	}

	public String getService_charge() {
		return service_charge;
	}

	public String getMiscellaneous() {
		return miscellaneous;
	}

	public String getSend_date() {
		return send_date;
	}

	public String getSend_time() {
		return send_time;
	}

	public String getSend_gl_time() {
		return send_gl_time;
	}

	public String getPackageno() {
		return packageno;
	}

	public String getPackagenum() {
		return packagenum;
	}

	public String getExpress_type() {
		return express_type;
	}

	public String getFdacode() {
		return fdacode;
	}

	public String getPacking() {
		return packing;
	}

	public String getStorages() {
		return storages;
	}

	public String getMawb() {
		return mawb;
	}

	public String getKjno() {
		return kjno;
	}

	public String getFlightno() {
		return flightno;
	}

	public String getTransport_company_name() {
		return transport_company_name;
	}

	public String getStart_city_code() {
		return start_city_code;
	}

	public String getStart_city_name() {
		return start_city_name;
	}

	public String getEnd_city_code() {
		return end_city_code;
	}

	public String getEnd_city_name() {
		return end_city_name;
	}

	public String getExemption_code() {
		return exemption_code;
	}

	public String getExemption_name() {
		return exemption_name;
	}

	public String getClearance_code() {
		return clearance_code;
	}

	public String getContainerno() {
		return containerno;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public String getTransaction_method_code() {
		return transaction_method_code;
	}

	public String getTransaction_method_name() {
		return transaction_method_name;
	}

	public String getTransport_mode() {
		return transport_mode;
	}

	public String getSource() {
		return source;
	}

	public String getOpdt() {
		return opdt;
	}

	public String getOparea() {
		return oparea;
	}

	public String getOp_gl_time() {
		return op_gl_time;
	}

	public String getNote() {
		return note;
	}

	public String getModify_time() {
		return modify_time;
	}

	public String getModify_by() {
		return modify_by;
	}

	public String getModify_area() {
		return modify_area;
	}

	public String getModify_gl_time() {
		return modify_gl_time;
	}

	public String getTr_gdesc() {
		return tr_gdesc;
	}

	public String getTr_srccompany() {
		return tr_srccompany;
	}

	public String getTr_srcaddr() {
		return tr_srcaddr;
	}

	public String getTr_srcname() {
		return tr_srcname;
	}

	public String getTr_descompany() {
		return tr_descompany;
	}

	public String getTr_desaddr() {
		return tr_desaddr;
	}

	public String getTr_desname() {
		return tr_desname;
	}

	public String getTr_flag() {
		return tr_flag;
	}

	public String getSadinfo() {
		return sadinfo;
	}

	public String getSelected() {
		return selected;
	}

	public String getUpsign() {
		return upsign;
	}

	public String getAdsign() {
		return adsign;
	}

	public String getCfsign() {
		return cfsign;
	}

	public String getPassign() {
		return passign;
	}

	public String getConfirm_gl_time() {
		return confirm_gl_time;
	}

	public String getConfirm_area() {
		return confirm_area;
	}

	public String getConfirm_time() {
		return confirm_time;
	}

	public String getConfirm_by() {
		return confirm_by;
	}

	public String getAudit_area() {
		return audit_area;
	}

	public String getAudit_gl_time() {
		return audit_gl_time;
	}

	public String getAudit_by() {
		return audit_by;
	}

	public String getAudit_time() {
		return audit_time;
	}

	public String getPsrcnm() {
		return psrcnm;
	}

	public String getPdesnm() {
		return pdesnm;
	}

	public String getBak1() {
		return bak1;
	}

	public String getBak2() {
		return bak2;
	}

	public String getBak3() {
		return bak3;
	}

	public String getBak4() {
		return bak4;
	}

	public String getProduce_src() {
		return produce_src;
	}

	public String getFee_currency() {
		return fee_currency;
	}

	public String getTranflag() {
		return tranflag;
	}

	public String getHscodeflag() {
		return hscodeflag;
	}

	public String getSenssign() {
		return senssign;
	}

	public String getOrg_cusbatch() {
		return org_cusbatch;
	}

	public String getDeal_flag() {
		return deal_flag;
	}

	public String getEdi_flag() {
		return edi_flag;
	}

	// ###################### end get 方法 ######################
	// ###########################################################
	// ###################### start get小写 方法 ######################

	public Map<String, String> getcmap() {
		return cmap;
	}

	public String getid() {
		return id;
	}

	public String getbno() {
		return bno;
	}

	public String getgdesc() {
		return gdesc;
	}

	public String getaweight() {
		return aweight;
	}

	public String getweight() {
		return weight;
	}

	public String getweight_unit() {
		return weight_unit;
	}

	public String getquantity() {
		return quantity;
	}

	public String getpcs() {
		return pcs;
	}

	public String getcusvalue() {
		return cusvalue;
	}

	public String getcus_currency_symbol() {
		return cus_currency_symbol;
	}

	public String getprice() {
		return price;
	}

	public String getinvoice_value() {
		return invoice_value;
	}

	public String gethscode() {
		return hscode;
	}

	public String getgmodel() {
		return gmodel;
	}

	public String getsrccode() {
		return srccode;
	}

	public String getsrcname() {
		return srcname;
	}

	public String getdescode() {
		return descode;
	}

	public String getdesname() {
		return desname;
	}

	public String getgate_type() {
		return gate_type;
	}

	public String getcustoms_date() {
		return customs_date;
	}

	public String getexp_customs_date() {
		return exp_customs_date;
	}

	public String getcustype() {
		return custype;
	}

	public String getcusbatch() {
		return cusbatch;
	}

	public String getvelist() {
		return velist;
	}

	public String getsrccompany() {
		return srccompany;
	}

	public String getsrcaddr() {
		return srcaddr;
	}

	public String getsrcpname() {
		return srcpname;
	}

	public String getsrctel() {
		return srctel;
	}

	public String getsrcmobile() {
		return srcmobile;
	}

	public String getsrc_union_no() {
		return src_union_no;
	}

	public String getsrc_business_unit_code() {
		return src_business_unit_code;
	}

	public String getsrc_postal_code() {
		return src_postal_code;
	}

	public String getsrc_city_code() {
		return src_city_code;
	}

	public String getsrc_city_name() {
		return src_city_name;
	}

	public String getsrcstate() {
		return srcstate;
	}

	public String getsrccountry() {
		return srccountry;
	}

	public String getsrc_country_code() {
		return src_country_code;
	}

	public String getsrc_country_name() {
		return src_country_name;
	}

	public String getdescompany() {
		return descompany;
	}

	public String getdesaddr() {
		return desaddr;
	}

	public String getdespname() {
		return despname;
	}

	public String getdestel() {
		return destel;
	}

	public String getdesmobile() {
		return desmobile;
	}

	public String getdes_union_no() {
		return des_union_no;
	}

	public String getdes_business_unit_code() {
		return des_business_unit_code;
	}

	public String getdes_postal_code() {
		return des_postal_code;
	}

	public String getdes_city_code() {
		return des_city_code;
	}

	public String getdes_city_name() {
		return des_city_name;
	}

	public String getdesstate() {
		return desstate;
	}

	public String getdescountry() {
		return descountry;
	}

	public String getdes_country_code() {
		return des_country_code;
	}

	public String getdes_country_name() {
		return des_country_name;
	}

	public String gettariff() {
		return tariff;
	}

	public String gettariff_value() {
		return tariff_value;
	}

	public String getvat() {
		return vat;
	}

	public String getvat_value() {
		return vat_value;
	}

	public String getpayment_type() {
		return payment_type;
	}

	public String getfeeamt() {
		return feeamt;
	}

	public String getinsurance() {
		return insurance;
	}

	public String getservice_charge() {
		return service_charge;
	}

	public String getmiscellaneous() {
		return miscellaneous;
	}

	public String getsend_date() {
		return send_date;
	}

	public String getsend_time() {
		return send_time;
	}

	public String getsend_gl_time() {
		return send_gl_time;
	}

	public String getpackageno() {
		return packageno;
	}

	public String getpackagenum() {
		return packagenum;
	}

	public String getexpress_type() {
		return express_type;
	}

	public String getfdacode() {
		return fdacode;
	}

	public String getpacking() {
		return packing;
	}

	public String getstorages() {
		return storages;
	}

	public String getmawb() {
		return mawb;
	}

	public String getkjno() {
		return kjno;
	}

	public String getflightno() {
		return flightno;
	}

	public String gettransport_company_name() {
		return transport_company_name;
	}

	public String getstart_city_code() {
		return start_city_code;
	}

	public String getstart_city_name() {
		return start_city_name;
	}

	public String getend_city_code() {
		return end_city_code;
	}

	public String getend_city_name() {
		return end_city_name;
	}

	public String getexemption_code() {
		return exemption_code;
	}

	public String getexemption_name() {
		return exemption_name;
	}

	public String getclearance_code() {
		return clearance_code;
	}

	public String getcontainerno() {
		return containerno;
	}

	public String gettrade_type() {
		return trade_type;
	}

	public String gettransaction_method_code() {
		return transaction_method_code;
	}

	public String gettransaction_method_name() {
		return transaction_method_name;
	}

	public String gettransport_mode() {
		return transport_mode;
	}

	public String getsource() {
		return source;
	}

	public String getopdt() {
		return opdt;
	}

	public String getoparea() {
		return oparea;
	}

	public String getop_gl_time() {
		return op_gl_time;
	}

	public String getnote() {
		return note;
	}

	public String getmodify_time() {
		return modify_time;
	}

	public String getmodify_by() {
		return modify_by;
	}

	public String getmodify_area() {
		return modify_area;
	}

	public String getmodify_gl_time() {
		return modify_gl_time;
	}

	public String gettr_gdesc() {
		return tr_gdesc;
	}

	public String gettr_srccompany() {
		return tr_srccompany;
	}

	public String gettr_srcaddr() {
		return tr_srcaddr;
	}

	public String gettr_srcname() {
		return tr_srcname;
	}

	public String gettr_descompany() {
		return tr_descompany;
	}

	public String gettr_desaddr() {
		return tr_desaddr;
	}

	public String gettr_desname() {
		return tr_desname;
	}

	public String gettr_flag() {
		return tr_flag;
	}

	public String getsadinfo() {
		return sadinfo;
	}

	public String getselected() {
		return selected;
	}

	public String getupsign() {
		return upsign;
	}

	public String getadsign() {
		return adsign;
	}

	public String getcfsign() {
		return cfsign;
	}

	public String getpassign() {
		return passign;
	}

	public String getconfirm_gl_time() {
		return confirm_gl_time;
	}

	public String getconfirm_area() {
		return confirm_area;
	}

	public String getconfirm_time() {
		return confirm_time;
	}

	public String getconfirm_by() {
		return confirm_by;
	}

	public String getaudit_area() {
		return audit_area;
	}

	public String getaudit_gl_time() {
		return audit_gl_time;
	}

	public String getaudit_by() {
		return audit_by;
	}

	public String getaudit_time() {
		return audit_time;
	}

	public String getpsrcnm() {
		return psrcnm;
	}

	public String getpdesnm() {
		return pdesnm;
	}

	public String getbak1() {
		return bak1;
	}

	public String getbak2() {
		return bak2;
	}

	public String getbak3() {
		return bak3;
	}

	public String getbak4() {
		return bak4;
	}

	public String getproduce_src() {
		return produce_src;
	}

	public String getfee_currency() {
		return fee_currency;
	}

	public String gettranflag() {
		return tranflag;
	}

	public String gethscodeflag() {
		return hscodeflag;
	}

	public String getsenssign() {
		return senssign;
	}

	public String getorg_cusbatch() {
		return org_cusbatch;
	}

	public String getdeal_flag() {
		return deal_flag;
	}

	public String getedi_flag() {
		return edi_flag;
	}

	// ###################### end get小写 方法 ######################

	/**
	 * <pre>
	 * 格式化数字的方法，从map中取值，如果map为空返回null
	 * </pre>
	 * 
	 * @param key
	 *            字段名称
	 * @param integer
	 *            整数位
	 * @param fraction
	 *            小数位
	 * @return
	 */
	public String fmt(String key, int integer, int fraction) {
		if (cmap == null)
			return null;
		return ExpWordUtils.formatNumber(cmap.get(key), integer, fraction);
	}

	/**
	 * <pre>
	 * 根据key从map中取值，如果map为null，返回null
	 * </pre>
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		if (cmap == null)
			return null;
		return cmap.get(key);
	}

	/**
	 * <pre>
	 * 从map构建对象
	 * 生成的代码
	 * </pre>
	 * 
	 * @param map
	 */
	public void fromMap(Map<String, String> map) {
		if (map == null) {
			throw new NullPointerException("map不能为空");
		}
		this.cmap = map;
		if (map.containsKey("id")) {
			this.id = ExpWordUtils.encode(map.get("id"));
		}
		if (map.containsKey("bno")) {
			this.bno = ExpWordUtils.encode(map.get("bno"));
		}
		if (map.containsKey("gdesc")) {
			this.gdesc = ExpWordUtils.encode(map.get("gdesc"));
		}
		if (map.containsKey("aweight")) {
			this.aweight = ExpWordUtils.encode(map.get("aweight"));
		}
		if (map.containsKey("weight")) {
			this.weight = ExpWordUtils.encode(map.get("weight"));
		}
		if (map.containsKey("weight_unit")) {
			this.weight_unit = ExpWordUtils.encode(map.get("weight_unit"));
		}
		if (map.containsKey("quantity")) {
			this.quantity = ExpWordUtils.encode(map.get("quantity"));
		}
		if (map.containsKey("pcs")) {
			this.pcs = ExpWordUtils.encode(map.get("pcs"));
		}
		if (map.containsKey("cusvalue")) {
			this.cusvalue = ExpWordUtils.encode(map.get("cusvalue"));
		}
		if (map.containsKey("cus_currency_symbol")) {
			this.cus_currency_symbol = ExpWordUtils.encode(map
					.get("cus_currency_symbol"));
		}
		if (map.containsKey("price")) {
			this.price = ExpWordUtils.encode(map.get("price"));
		}
		if (map.containsKey("invoice_value")) {
			this.invoice_value = ExpWordUtils.encode(map.get("invoice_value"));
		}
		if (map.containsKey("hscode")) {
			this.hscode = ExpWordUtils.encode(map.get("hscode"));
		}
		if (map.containsKey("gmodel")) {
			this.gmodel = ExpWordUtils.encode(map.get("gmodel"));
		}
		if (map.containsKey("srccode")) {
			this.srccode = ExpWordUtils.encode(map.get("srccode"));
		}
		if (map.containsKey("srcname")) {
			this.srcname = ExpWordUtils.encode(map.get("srcname"));
		}
		if (map.containsKey("descode")) {
			this.descode = ExpWordUtils.encode(map.get("descode"));
		}
		if (map.containsKey("desname")) {
			this.desname = ExpWordUtils.encode(map.get("desname"));
		}
		if (map.containsKey("gate_type")) {
			this.gate_type = ExpWordUtils.encode(map.get("gate_type"));
		}
		if (map.containsKey("customs_date")) {
			this.customs_date = ExpWordUtils.encode(map.get("customs_date"));
		}
		if (map.containsKey("exp_customs_date")) {
			this.exp_customs_date = ExpWordUtils.encode(map
					.get("exp_customs_date"));
		}
		if (map.containsKey("custype")) {
			this.custype = ExpWordUtils.encode(map.get("custype"));
		}
		if (map.containsKey("cusbatch")) {
			this.cusbatch = ExpWordUtils.encode(map.get("cusbatch"));
		}
		if (map.containsKey("velist")) {
			this.velist = ExpWordUtils.encode(map.get("velist"));
		}
		if (map.containsKey("srccompany")) {
			this.srccompany = ExpWordUtils.encode(map.get("srccompany"));
		}
		if (map.containsKey("srcaddr")) {
			this.srcaddr = ExpWordUtils.encode(map.get("srcaddr"));
		}
		if (map.containsKey("srcpname")) {
			this.srcpname = ExpWordUtils.encode(map.get("srcpname"));
		}
		if (map.containsKey("srctel")) {
			this.srctel = ExpWordUtils.encode(map.get("srctel"));
		}
		if (map.containsKey("srcmobile")) {
			this.srcmobile = ExpWordUtils.encode(map.get("srcmobile"));
		}
		if (map.containsKey("src_union_no")) {
			this.src_union_no = ExpWordUtils.encode(map.get("src_union_no"));
		}
		if (map.containsKey("src_business_unit_code")) {
			this.src_business_unit_code = ExpWordUtils.encode(map
					.get("src_business_unit_code"));
		}
		if (map.containsKey("src_postal_code")) {
			this.src_postal_code = ExpWordUtils.encode(map
					.get("src_postal_code"));
		}
		if (map.containsKey("src_city_code")) {
			this.src_city_code = ExpWordUtils.encode(map.get("src_city_code"));
		}
		if (map.containsKey("src_city_name")) {
			this.src_city_name = ExpWordUtils.encode(map.get("src_city_name"));
		}
		if (map.containsKey("srcstate")) {
			this.srcstate = ExpWordUtils.encode(map.get("srcstate"));
		}
		if (map.containsKey("srccountry")) {
			this.srccountry = ExpWordUtils.encode(map.get("srccountry"));
		}
		if (map.containsKey("src_country_code")) {
			this.src_country_code = ExpWordUtils.encode(map
					.get("src_country_code"));
		}
		if (map.containsKey("src_country_name")) {
			this.src_country_name = ExpWordUtils.encode(map
					.get("src_country_name"));
		}
		if (map.containsKey("descompany")) {
			this.descompany = ExpWordUtils.encode(map.get("descompany"));
		}
		if (map.containsKey("desaddr")) {
			this.desaddr = ExpWordUtils.encode(map.get("desaddr"));
		}
		if (map.containsKey("despname")) {
			this.despname = ExpWordUtils.encode(map.get("despname"));
		}
		if (map.containsKey("destel")) {
			this.destel = ExpWordUtils.encode(map.get("destel"));
		}
		if (map.containsKey("desmobile")) {
			this.desmobile = ExpWordUtils.encode(map.get("desmobile"));
		}
		if (map.containsKey("des_union_no")) {
			this.des_union_no = ExpWordUtils.encode(map.get("des_union_no"));
		}
		if (map.containsKey("des_business_unit_code")) {
			this.des_business_unit_code = ExpWordUtils.encode(map
					.get("des_business_unit_code"));
		}
		if (map.containsKey("des_postal_code")) {
			this.des_postal_code = ExpWordUtils.encode(map
					.get("des_postal_code"));
		}
		if (map.containsKey("des_city_code")) {
			this.des_city_code = ExpWordUtils.encode(map.get("des_city_code"));
		}
		if (map.containsKey("des_city_name")) {
			this.des_city_name = ExpWordUtils.encode(map.get("des_city_name"));
		}
		if (map.containsKey("desstate")) {
			this.desstate = ExpWordUtils.encode(map.get("desstate"));
		}
		if (map.containsKey("descountry")) {
			this.descountry = ExpWordUtils.encode(map.get("descountry"));
		}
		if (map.containsKey("des_country_code")) {
			this.des_country_code = ExpWordUtils.encode(map
					.get("des_country_code"));
		}
		if (map.containsKey("des_country_name")) {
			this.des_country_name = ExpWordUtils.encode(map
					.get("des_country_name"));
		}
		if (map.containsKey("tariff")) {
			this.tariff = ExpWordUtils.encode(map.get("tariff"));
		}
		if (map.containsKey("tariff_value")) {
			this.tariff_value = ExpWordUtils.encode(map.get("tariff_value"));
		}
		if (map.containsKey("vat")) {
			this.vat = ExpWordUtils.encode(map.get("vat"));
		}
		if (map.containsKey("vat_value")) {
			this.vat_value = ExpWordUtils.encode(map.get("vat_value"));
		}
		if (map.containsKey("payment_type")) {
			this.payment_type = ExpWordUtils.encode(map.get("payment_type"));
		}
		if (map.containsKey("feeamt")) {
			this.feeamt = ExpWordUtils.encode(map.get("feeamt"));
		}
		if (map.containsKey("insurance")) {
			this.insurance = ExpWordUtils.encode(map.get("insurance"));
		}
		if (map.containsKey("service_charge")) {
			this.service_charge = ExpWordUtils
					.encode(map.get("service_charge"));
		}
		if (map.containsKey("miscellaneous")) {
			this.miscellaneous = ExpWordUtils.encode(map.get("miscellaneous"));
		}
		if (map.containsKey("send_date")) {
			this.send_date = ExpWordUtils.encode(map.get("send_date"));
		}
		if (map.containsKey("send_time")) {
			this.send_time = ExpWordUtils.encode(map.get("send_time"));
		}
		if (map.containsKey("send_gl_time")) {
			this.send_gl_time = ExpWordUtils.encode(map.get("send_gl_time"));
		}
		if (map.containsKey("packageno")) {
			this.packageno = ExpWordUtils.encode(map.get("packageno"));
		}
		if (map.containsKey("packagenum")) {
			this.packagenum = ExpWordUtils.encode(map.get("packagenum"));
		}
		if (map.containsKey("express_type")) {
			this.express_type = ExpWordUtils.encode(map.get("express_type"));
		}
		if (map.containsKey("fdacode")) {
			this.fdacode = ExpWordUtils.encode(map.get("fdacode"));
		}
		if (map.containsKey("packing")) {
			this.packing = ExpWordUtils.encode(map.get("packing"));
		}
		if (map.containsKey("storages")) {
			this.storages = ExpWordUtils.encode(map.get("storages"));
		}
		if (map.containsKey("mawb")) {
			this.mawb = ExpWordUtils.encode(map.get("mawb"));
		}
		if (map.containsKey("kjno")) {
			this.kjno = ExpWordUtils.encode(map.get("kjno"));
		}
		if (map.containsKey("flightno")) {
			this.flightno = ExpWordUtils.encode(map.get("flightno"));
		}
		if (map.containsKey("transport_company_name")) {
			this.transport_company_name = ExpWordUtils.encode(map
					.get("transport_company_name"));
		}
		if (map.containsKey("start_city_code")) {
			this.start_city_code = ExpWordUtils.encode(map
					.get("start_city_code"));
		}
		if (map.containsKey("start_city_name")) {
			this.start_city_name = ExpWordUtils.encode(map
					.get("start_city_name"));
		}
		if (map.containsKey("end_city_code")) {
			this.end_city_code = ExpWordUtils.encode(map.get("end_city_code"));
		}
		if (map.containsKey("end_city_name")) {
			this.end_city_name = ExpWordUtils.encode(map.get("end_city_name"));
		}
		if (map.containsKey("exemption_code")) {
			this.exemption_code = ExpWordUtils
					.encode(map.get("exemption_code"));
		}
		if (map.containsKey("exemption_name")) {
			this.exemption_name = ExpWordUtils
					.encode(map.get("exemption_name"));
		}
		if (map.containsKey("clearance_code")) {
			this.clearance_code = ExpWordUtils
					.encode(map.get("clearance_code"));
		}
		if (map.containsKey("containerno")) {
			this.containerno = ExpWordUtils.encode(map.get("containerno"));
		}
		if (map.containsKey("trade_type")) {
			this.trade_type = ExpWordUtils.encode(map.get("trade_type"));
		}
		if (map.containsKey("transaction_method_code")) {
			this.transaction_method_code = ExpWordUtils.encode(map
					.get("transaction_method_code"));
		}
		if (map.containsKey("transaction_method_name")) {
			this.transaction_method_name = ExpWordUtils.encode(map
					.get("transaction_method_name"));
		}
		if (map.containsKey("transport_mode")) {
			this.transport_mode = ExpWordUtils
					.encode(map.get("transport_mode"));
		}
		if (map.containsKey("source")) {
			this.source = ExpWordUtils.encode(map.get("source"));
		}
		if (map.containsKey("opdt")) {
			this.opdt = ExpWordUtils.encode(map.get("opdt"));
		}
		if (map.containsKey("oparea")) {
			this.oparea = ExpWordUtils.encode(map.get("oparea"));
		}
		if (map.containsKey("op_gl_time")) {
			this.op_gl_time = ExpWordUtils.encode(map.get("op_gl_time"));
		}
		if (map.containsKey("note")) {
			this.note = ExpWordUtils.encode(map.get("note"));
		}
		if (map.containsKey("modify_time")) {
			this.modify_time = ExpWordUtils.encode(map.get("modify_time"));
		}
		if (map.containsKey("modify_by")) {
			this.modify_by = ExpWordUtils.encode(map.get("modify_by"));
		}
		if (map.containsKey("modify_area")) {
			this.modify_area = ExpWordUtils.encode(map.get("modify_area"));
		}
		if (map.containsKey("modify_gl_time")) {
			this.modify_gl_time = ExpWordUtils
					.encode(map.get("modify_gl_time"));
		}
		if (map.containsKey("tr_gdesc")) {
			this.tr_gdesc = ExpWordUtils.encode(map.get("tr_gdesc"));
		}
		if (map.containsKey("tr_srccompany")) {
			this.tr_srccompany = ExpWordUtils.encode(map.get("tr_srccompany"));
		}
		if (map.containsKey("tr_srcaddr")) {
			this.tr_srcaddr = ExpWordUtils.encode(map.get("tr_srcaddr"));
		}
		if (map.containsKey("tr_srcname")) {
			this.tr_srcname = ExpWordUtils.encode(map.get("tr_srcname"));
		}
		if (map.containsKey("tr_descompany")) {
			this.tr_descompany = ExpWordUtils.encode(map.get("tr_descompany"));
		}
		if (map.containsKey("tr_desaddr")) {
			this.tr_desaddr = ExpWordUtils.encode(map.get("tr_desaddr"));
		}
		if (map.containsKey("tr_desname")) {
			this.tr_desname = ExpWordUtils.encode(map.get("tr_desname"));
		}
		if (map.containsKey("tr_flag")) {
			this.tr_flag = ExpWordUtils.encode(map.get("tr_flag"));
		}
		if (map.containsKey("sadinfo")) {
			this.sadinfo = ExpWordUtils.encode(map.get("sadinfo"));
		}
		if (map.containsKey("selected")) {
			this.selected = ExpWordUtils.encode(map.get("selected"));
		}
		if (map.containsKey("upsign")) {
			this.upsign = ExpWordUtils.encode(map.get("upsign"));
		}
		if (map.containsKey("adsign")) {
			this.adsign = ExpWordUtils.encode(map.get("adsign"));
		}
		if (map.containsKey("cfsign")) {
			this.cfsign = ExpWordUtils.encode(map.get("cfsign"));
		}
		if (map.containsKey("passign")) {
			this.passign = ExpWordUtils.encode(map.get("passign"));
		}
		if (map.containsKey("confirm_gl_time")) {
			this.confirm_gl_time = ExpWordUtils.encode(map
					.get("confirm_gl_time"));
		}
		if (map.containsKey("confirm_area")) {
			this.confirm_area = ExpWordUtils.encode(map.get("confirm_area"));
		}
		if (map.containsKey("confirm_time")) {
			this.confirm_time = ExpWordUtils.encode(map.get("confirm_time"));
		}
		if (map.containsKey("confirm_by")) {
			this.confirm_by = ExpWordUtils.encode(map.get("confirm_by"));
		}
		if (map.containsKey("audit_area")) {
			this.audit_area = ExpWordUtils.encode(map.get("audit_area"));
		}
		if (map.containsKey("audit_gl_time")) {
			this.audit_gl_time = ExpWordUtils.encode(map.get("audit_gl_time"));
		}
		if (map.containsKey("audit_by")) {
			this.audit_by = ExpWordUtils.encode(map.get("audit_by"));
		}
		if (map.containsKey("audit_time")) {
			this.audit_time = ExpWordUtils.encode(map.get("audit_time"));
		}
		if (map.containsKey("psrcnm")) {
			this.psrcnm = ExpWordUtils.encode(map.get("psrcnm"));
		}
		if (map.containsKey("pdesnm")) {
			this.pdesnm = ExpWordUtils.encode(map.get("pdesnm"));
		}
		if (map.containsKey("bak1")) {
			this.bak1 = ExpWordUtils.encode(map.get("bak1"));
		}
		if (map.containsKey("bak2")) {
			this.bak2 = ExpWordUtils.encode(map.get("bak2"));
		}
		if (map.containsKey("bak3")) {
			this.bak3 = ExpWordUtils.encode(map.get("bak3"));
		}
		if (map.containsKey("bak4")) {
			this.bak4 = ExpWordUtils.encode(map.get("bak4"));
		}
		if (map.containsKey("produce_src")) {
			this.produce_src = ExpWordUtils.encode(map.get("produce_src"));
		}
		if (map.containsKey("fee_currency")) {
			this.fee_currency = ExpWordUtils.encode(map.get("fee_currency"));
		}
		if (map.containsKey("tranflag")) {
			this.tranflag = ExpWordUtils.encode(map.get("tranflag"));
		}
		if (map.containsKey("hscodeflag")) {
			this.hscodeflag = ExpWordUtils.encode(map.get("hscodeflag"));
		}
		if (map.containsKey("senssign")) {
			this.senssign = ExpWordUtils.encode(map.get("senssign"));
		}
		if (map.containsKey("org_cusbatch")) {
			this.org_cusbatch = ExpWordUtils.encode(map.get("org_cusbatch"));
		}
		if (map.containsKey("deal_flag")) {
			this.deal_flag = ExpWordUtils.encode(map.get("deal_flag"));
		}
		if (map.containsKey("edi_flag")) {
			this.edi_flag = ExpWordUtils.encode(map.get("edi_flag"));
		}
	}

	/**
	 * <pre>
	 * 对象转换成map
	 * 生成代码
	 * </pre>
	 * 
	 * @return
	 */
	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("bno", bno);
		map.put("gdesc", gdesc);
		map.put("aweight", aweight);
		map.put("weight", weight);
		map.put("weight_unit", weight_unit);
		map.put("quantity", quantity);
		map.put("pcs", pcs);
		map.put("cusvalue", cusvalue);
		map.put("cus_currency_symbol", cus_currency_symbol);
		map.put("price", price);
		map.put("invoice_value", invoice_value);
		map.put("hscode", hscode);
		map.put("gmodel", gmodel);
		map.put("srccode", srccode);
		map.put("srcname", srcname);
		map.put("descode", descode);
		map.put("desname", desname);
		map.put("gate_type", gate_type);
		map.put("customs_date", customs_date);
		map.put("exp_customs_date", exp_customs_date);
		map.put("custype", custype);
		map.put("cusbatch", cusbatch);
		map.put("velist", velist);
		map.put("srccompany", srccompany);
		map.put("srcaddr", srcaddr);
		map.put("srcpname", srcpname);
		map.put("srctel", srctel);
		map.put("srcmobile", srcmobile);
		map.put("src_union_no", src_union_no);
		map.put("src_business_unit_code", src_business_unit_code);
		map.put("src_postal_code", src_postal_code);
		map.put("src_city_code", src_city_code);
		map.put("src_city_name", src_city_name);
		map.put("srcstate", srcstate);
		map.put("srccountry", srccountry);
		map.put("src_country_code", src_country_code);
		map.put("src_country_name", src_country_name);
		map.put("descompany", descompany);
		map.put("desaddr", desaddr);
		map.put("despname", despname);
		map.put("destel", destel);
		map.put("desmobile", desmobile);
		map.put("des_union_no", des_union_no);
		map.put("des_business_unit_code", des_business_unit_code);
		map.put("des_postal_code", des_postal_code);
		map.put("des_city_code", des_city_code);
		map.put("des_city_name", des_city_name);
		map.put("desstate", desstate);
		map.put("descountry", descountry);
		map.put("des_country_code", des_country_code);
		map.put("des_country_name", des_country_name);
		map.put("tariff", tariff);
		map.put("tariff_value", tariff_value);
		map.put("vat", vat);
		map.put("vat_value", vat_value);
		map.put("payment_type", payment_type);
		map.put("feeamt", feeamt);
		map.put("insurance", insurance);
		map.put("service_charge", service_charge);
		map.put("miscellaneous", miscellaneous);
		map.put("send_date", send_date);
		map.put("send_time", send_time);
		map.put("send_gl_time", send_gl_time);
		map.put("packageno", packageno);
		map.put("packagenum", packagenum);
		map.put("express_type", express_type);
		map.put("fdacode", fdacode);
		map.put("packing", packing);
		map.put("storages", storages);
		map.put("mawb", mawb);
		map.put("kjno", kjno);
		map.put("flightno", flightno);
		map.put("transport_company_name", transport_company_name);
		map.put("start_city_code", start_city_code);
		map.put("start_city_name", start_city_name);
		map.put("end_city_code", end_city_code);
		map.put("end_city_name", end_city_name);
		map.put("exemption_code", exemption_code);
		map.put("exemption_name", exemption_name);
		map.put("clearance_code", clearance_code);
		map.put("containerno", containerno);
		map.put("trade_type", trade_type);
		map.put("transaction_method_code", transaction_method_code);
		map.put("transaction_method_name", transaction_method_name);
		map.put("transport_mode", transport_mode);
		map.put("source", source);
		map.put("opdt", opdt);
		map.put("oparea", oparea);
		map.put("op_gl_time", op_gl_time);
		map.put("note", note);
		map.put("modify_time", modify_time);
		map.put("modify_by", modify_by);
		map.put("modify_area", modify_area);
		map.put("modify_gl_time", modify_gl_time);
		map.put("tr_gdesc", tr_gdesc);
		map.put("tr_srccompany", tr_srccompany);
		map.put("tr_srcaddr", tr_srcaddr);
		map.put("tr_srcname", tr_srcname);
		map.put("tr_descompany", tr_descompany);
		map.put("tr_desaddr", tr_desaddr);
		map.put("tr_desname", tr_desname);
		map.put("tr_flag", tr_flag);
		map.put("sadinfo", sadinfo);
		map.put("selected", selected);
		map.put("upsign", upsign);
		map.put("adsign", adsign);
		map.put("cfsign", cfsign);
		map.put("passign", passign);
		map.put("confirm_gl_time", confirm_gl_time);
		map.put("confirm_area", confirm_area);
		map.put("confirm_time", confirm_time);
		map.put("confirm_by", confirm_by);
		map.put("audit_area", audit_area);
		map.put("audit_gl_time", audit_gl_time);
		map.put("audit_by", audit_by);
		map.put("audit_time", audit_time);
		map.put("psrcnm", psrcnm);
		map.put("pdesnm", pdesnm);
		map.put("bak1", bak1);
		map.put("bak2", bak2);
		map.put("bak3", bak3);
		map.put("bak4", bak4);
		map.put("produce_src", produce_src);
		map.put("fee_currency", fee_currency);
		map.put("tranflag", tranflag);
		map.put("hscodeflag", hscodeflag);
		map.put("senssign", senssign);
		map.put("org_cusbatch", org_cusbatch);
		map.put("deal_flag", deal_flag);
		map.put("edi_flag", edi_flag);
		return map;
	}
}
