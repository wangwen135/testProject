package com.swing.dll;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;
import org.xvolks.jnative.exceptions.NativeException;

/**
 * 描述： DLL工具类
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-3-14      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class DllUtils {
	private static String DLL_NAME;

	static {
		try {
			String path = (new File("dll")).getCanonicalPath() + File.separator;

			DLL_NAME = path + "RTFDLL.dll";
			String jnative = path + "JNativeCpp.dll";

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

	/**
	 * <pre>
	 * 执行DLL中的方法
	 * </pre>
	 * 
	 * @param rtfFile
	 *            TRF文件
	 * @param outFile
	 *            输出文件
	 * @param datalist
	 *            数据
	 * @param assisMap
	 *            辅助信息数据
	 * @param flag
	 *            发票标识 (0=表示发票)
	 * @param unit
	 *            是否合并发票
	 * @throws Exception
	 *             调用失败后会抛出异常
	 */
	public static boolean runDll(String rtfFile, String outFile,
			List<Map<String, Object>> datalist, Map<String, String> assisMap,
			int flag, boolean unit) throws Exception {

		StringBuilder strb = new StringBuilder();
		strb.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		strb.append("<root>");
		// 加上辅助信息
		addAssis(assisMap, strb);

		for (Map<String, Object> map : datalist) {
			strb.append("<row>");
			for (Entry<String, Object> e : map.entrySet()) {
				String key = e.getKey();
				Object value = e.getValue();
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

			System.out.println("参数2:" + rtfFile);
			jn.setParameter(1, Type.STRING, rtfFile);

			System.out.println("参数3:" + outFile);
			jn.setParameter(2, Type.STRING, outFile);

			System.out.println("参数4:" + flag);
			jn.setParameter(3, Type.STRING, "" + flag);

			System.out.println("参数5:" + (unit ? "0" : "1"));
			jn.setParameter(4, Type.STRING, unit ? "0" : "1");// 0 = 表示合并
			jn.invoke();
			String ret = jn.getRetVal();
			return "0".equals(ret);
		} finally {
			freeJNative(jn);
		}

	}

	/**
	 * <pre>
	 * 添加辅助信息
	 * </pre>
	 * 
	 * @param assisMap
	 * @param strb
	 */
	public static void addAssis(Map<String, String> assisMap, StringBuilder strb) {
		strb.append("<master>");
		strb.append("<OrderNo>");
		strb.append(assisMap.get("OrderNo"));
		strb.append("</OrderNo>");
		strb.append("<CarNo>");
		strb.append(assisMap.get("CarNo"));
		strb.append("</CarNo>");
		strb.append("<CarNo>");
		strb.append(assisMap.get("CarNo"));
		strb.append("</CarNo>");
		strb.append("<currency>");
		strb.append(assisMap.get("currency"));
		strb.append("</currency>");
		strb.append("<cusdate>");
		strb.append(assisMap.get("cusdate"));
		strb.append("</cusdate>");
		strb.append("<KJOrderNo>");
		strb.append(assisMap.get("KJOrderNo"));
		strb.append("</KJOrderNo>");
		strb.append("<QYGDYD>");
		strb.append(assisMap.get("QYGDYD"));
		strb.append("</QYGDYD>");
		strb.append("<JCKDM>");
		strb.append(assisMap.get("JCKDM"));
		strb.append("</JCKDM>");
		strb.append("<FromArea>");
		strb.append(assisMap.get("FromArea"));
		strb.append("</FromArea>");
		strb.append("<AreaNo>");
		strb.append(assisMap.get("AreaNo"));
		strb.append("</AreaNo>");
		strb.append("</master>");
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
