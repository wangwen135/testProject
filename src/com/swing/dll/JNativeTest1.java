package com.swing.dll;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;
import org.xvolks.jnative.exceptions.NativeException;

import com.swing.edi.report.utils.ReportUtils;

public class JNativeTest1 {
	private static String DLL_NAME;

	static {
		try {
			String path = (new File("dll")).getCanonicalPath() + File.separator;

			DLL_NAME = path + "RTFDLL.dll";
			String jnative = path + "JNativeCpp.dll";

			System.out.println("lib: " + jnative);

			System.setProperty("jnative.loadNative", jnative);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Map<String, String> keyMaping = new HashMap<String, String>();
	static {

		keyMaping.put("bno", "BNO");// 运单编号
		// keyMaping.put("", "SELECTED");// 是否选择
		keyMaping.put("customs_date", "CUSDATE");// 报关日期
		keyMaping.put("cusbatch", "CUSPC");// 报关批次
		keyMaping.put("gate_type", "GATETYPE");// 报关类型
		keyMaping.put("cusvalue", "VALUE");// 价值 ---申报价值
		keyMaping.put("velist", "VELIST");// 车次
		keyMaping.put("srcname", "SRCNM");// 原寄地名称
		keyMaping.put("psrcnm", "PSRCNM");// 列印原寄地
		keyMaping.put("price", "PRICE");// 单价
		keyMaping.put("pcs", "PCS");// 件数

		// keyMaping.put("flightno", "VEHICLENO");// 车牌号 ????
		keyMaping.put("desname", "DESNM");// 目的地名称
		keyMaping.put("pdesnm", "PDESNM");// 列印目的地
		keyMaping.put("srccompany", "SRCCOMPANY");// 寄件公司
		keyMaping.put("srcaddr", "SRCADDR");// 寄件地址
		keyMaping.put("descompany", "DESCOMPANY");// 收件公司
		keyMaping.put("desaddr", "DESADDR");// 收件地址
		keyMaping.put("destel", "DESTEL");// 收件电话
		// keyMaping.put("", "SINOP");// 录入员工
		keyMaping.put("aweight", "AWEIGHT");// 实际重量
		keyMaping.put("gdesc", "GDESC");// 托寄物内容
		keyMaping.put("gmodel", "GMODEL");// 规格型号
		keyMaping.put("quantity", "QUANTITY");// 数量/单位
		keyMaping.put("custype", "CUSTYPE");// 类别
		keyMaping.put("note", "NOTE");// 备注
		// keyMaping.put("adsign", "ADSIGN");// 审核标志
		// keyMaping.put("send_date", "SENDDATE");// 寄件日期
		// keyMaping.put("send_time", "SENDTIME");// 寄件时间
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
		// keyMaping.put("upsign", "UPSIGN");// 修改标志
		// keyMaping.put("", "TORDERNO");// 总运单号
		// keyMaping.put("passign", "PASSSIGN");// 通关标志
		// keyMaping.put("", "KJORDERNO");
		// keyMaping.put("", "EDISIGN");
		// keyMaping.put("", "BACKSIGN");

	}

	public static void main(String[] args) {
		List<Map<String, String>> list = ReportUtils.getAnalogData(10);
		StringBuilder strb = new StringBuilder();
		strb.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		strb.append("<root>");

		strb.append("<master>");
		strb.append("<OrderNo>");
		strb.append("aaaaa");
		strb.append("</OrderNo>");
		strb.append("<CarNo>");
		strb.append("aaaaa");
		strb.append("</CarNo>");
		strb.append("<CarNo>");
		strb.append("aaaaa");
		strb.append("</CarNo>");
		strb.append("<currency>");
		strb.append("aaaaa");
		strb.append("</currency>");
		strb.append("<cusdate>");
		strb.append("2012-03-03");
		strb.append("</cusdate>");
		strb.append("<KJOrderNo>");
		strb.append("aaaaa");
		strb.append("</KJOrderNo>");
		strb.append("<QYGDYD>");
		strb.append("aaaaa");
		strb.append("</QYGDYD>");
		strb.append("<JCKDM>");
		strb.append("aaaaa");
		strb.append("</JCKDM>");
		strb.append("<FromArea>");
		strb.append("aaaaa");
		strb.append("</FromArea>");
		strb.append("<AreaNo>");
		strb.append("aaaaa");
		strb.append("</AreaNo>");
		strb.append("</master>");

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
		JNative jn = null;
		try {
			jn = new JNative(DLL_NAME, "CreateRTFFile");
			jn.setRetVal(Type.STRING);

			System.out.println("参数1:" + strb.toString());
			jn.setParameter(0, Type.STRING, strb.toString());

			String arg2 = "E:\\workspace\\testProject\\RTFFiles\\发票模板\\随机商业发票\\;随机发票模版CHS01.rtf;随机发票模版CHS02.rtf;随机发票模版CHS03.rtf";

			System.out.println("参数2:" + arg2);
			jn.setParameter(1, Type.STRING, arg2);

			String arg3 = "E:\\测试dll导出文件.doc";

			System.out.println("参数3:" + arg3);
			jn.setParameter(2, Type.STRING, arg3);

			String arg4 = "0";

			System.out.println("参数4:" + arg4);
			jn.setParameter(3, Type.STRING, "" + arg4);

			String arg5 = "0";

			System.out.println("参数5:" + arg5);
			jn.setParameter(4, Type.STRING, arg5);// 0 = 表示合并
			jn.invoke();
			String ret = jn.getRetVal();

			System.out.println("返回值是：" + ret);

			//如果是打发票的话，弹出文件比较麻烦，是根据运单编号生成文件
			if ("0".equals(ret)&& "0".equals(arg5))//合并成一个文件
				Desktop.getDesktop().open(new File("E:\\测试dll导出文件.doc"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeJNative(jn);
		}
	}

	@SuppressWarnings("deprecation")
	private static void freeJNative(JNative jn) {
		if (jn != null) {
			try {
				jn.dispose();
			} catch (NativeException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}
