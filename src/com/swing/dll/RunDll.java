package com.swing.dll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.swing.edi.report.utils.ReportUtils;

public class RunDll {
	public interface CLibrary extends Library {
		CLibrary INSTANCE = (CLibrary) Native.loadLibrary("dll/RTFDLL.dll",
				CLibrary.class);

		void CreateRTFFile(String xmldate, String loadFile, String saveFile,
				int flag);

		void testdll(String a);
	}

	public static Map<String, String> keyMaping = new HashMap<String, String>();
	static {
		
		keyMaping.put("bno", "BNO");// 运单编号
		//keyMaping.put("", "SELECTED");// 是否选择 
		keyMaping.put("customs_date", "CUSDATE");// 报关日期
		keyMaping.put("cusbatch", "CUSPC");// 报关批次
		keyMaping.put("gate_type", "GATETYPE");// 报关类型
		keyMaping.put("cusvalue", "VALUE");// 价值 ---申报价值
		keyMaping.put("velist", "VELIST");// 车次
		keyMaping.put("srcname", "SRCNM");// 原寄地名称
		keyMaping.put("psrcnm", "PSRCNM");// 列印原寄地
		//keyMaping.put("flightno", "VEHICLENO");// 车牌号 ????
		keyMaping.put("desname", "DESNM");// 目的地名称
		keyMaping.put("pdesnm", "PDESNM");// 列印目的地
		keyMaping.put("srccompany", "SRCCOMPANY");// 寄件公司
		keyMaping.put("srcaddr", "SRCADDR");// 寄件地址
		keyMaping.put("descompany", "DESCOMPANY");// 收件公司
		keyMaping.put("desaddr", "DESADDR");// 收件地址
		keyMaping.put("destel", "DESTEL");// 收件电话
		//keyMaping.put("", "SINOP");// 录入员工
		keyMaping.put("aweight", "AWEIGHT");// 实际重量
		keyMaping.put("gdesc", "GDESC");// 托寄物内容
		keyMaping.put("gmodel", "GMODEL");// 规格型号
		keyMaping.put("quantity", "QUANTITY");// 数量/单位
		keyMaping.put("custype", "CUSTYPE");// 类别
		keyMaping.put("note", "NOTE");// 备注
		//keyMaping.put("adsign", "ADSIGN");// 审核标志
		//keyMaping.put("send_date", "SENDDATE");// 寄件日期
		//keyMaping.put("send_time", "SENDTIME");// 寄件时间
		keyMaping.put("srctel", "SRCTEL");// 寄件电话
		keyMaping.put("srcpname", "SRCNAME");// 寄件人
		keyMaping.put("despname", "DESNAME");// 收件人
		keyMaping.put("weight", "WEIGHT");// 计费重量
		keyMaping.put("hscode", "GOODNO");// 商品编码
		keyMaping.put("tariff", "CUSTOM");// 关税税率
		keyMaping.put("tariff_value", "CUSTOMVALUE");// 关税
		keyMaping.put("vat", "ADDTAX");// 增值税税率
		keyMaping.put("vat_value", "ADDTAXVALUE");// 增值税
		keyMaping.put("produce_src", "PRODUCESRC");// 原产国
		//keyMaping.put("upsign", "UPSIGN");// 修改标志
		//keyMaping.put("", "TORDERNO");// 总运单号
		//keyMaping.put("passign", "PASSSIGN");// 通关标志
		//keyMaping.put("", "KJORDERNO");
		//keyMaping.put("", "EDISIGN");
		//keyMaping.put("", "BACKSIGN");

	}

	public static void main(String[] args) {
		List<Map<String, String>> list = ReportUtils.getAnalogData(1);
		StringBuilder strb = new StringBuilder();
		strb.append("<?xml version=\"1.0\" encoding=\"GB2312\"?>");
		strb.append("<root>");
		for (Map<String, String> map : list) {
			strb.append("<row>");
			for (Entry<String, String> e : map.entrySet()) {
				String key = e.getKey();
				String value = e.getValue();
				if (keyMaping.containsKey(key)) {
					if (value != null) {
						strb.append("<" + keyMaping.get(key) + ">");
						strb.append(value);
						strb.append("</" + keyMaping.get(key) + ">");
					}
				}

			}
			strb.append("</row>");
		}
		strb.append("</root>");

		System.out.println("-----------------------------");
		System.out.println(strb.toString());
		System.out.println("-----------------------------");

		CLibrary.INSTANCE.CreateRTFFile("aaa",
				"E:/workspace/testProject/config/深圳机场出口KJC.rtf",
				"E:/测试dll导出文件.doc", 1);

		// CLibrary.INSTANCE.testdll("aaa");
		// System.out.println(r);

		System.out.println("ok....");
	}

}
