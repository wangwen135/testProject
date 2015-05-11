package com.swing.dll;

import java.io.File;
import java.io.IOException;

import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;
import org.xvolks.jnative.exceptions.NativeException;

public class JNativeTest {
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

	public static void main(String[] args) {
		String xml = "<?xml version=\"1.0\" encoding=\"gb2312\"?><root><master><OrderNo></OrderNo><CarNo></CarNo><CarNo></CarNo><currency>HKD</currency><cusdate>2012-03-14</cusdate><KJOrderNo></KJOrderNo><QYGDYD>CAN</QYGDYD><JCKDM></JCKDM><FromArea></FromArea><AreaNo></AreaNo></master><row><DESNAME>3</DESNAME><VALUE>11.000000</VALUE><QUANTITY>11</QUANTITY><GATETYPE>aaa</GATETYPE><SRCNM>整改部门</SRCNM><GDESC>好吃点</GDESC><AWEIGHT>11.00000</AWEIGHT><DESNM>陕西区部</DESNM><CUSPC>CAN0100HKG</CUSPC><ADDTAXVALUE>1.87000</ADDTAXVALUE><SRCCOMPANY>1</SRCCOMPANY><SRCTEL>1</SRCTEL><DESCOMPANY>2</DESCOMPANY><GMODEL>无牌40码|无牌38码|无牌36码|无牌45码|无牌43码|无牌42码|无牌41码|无牌39码|无牌37码</GMODEL><BNO>028607462708</BNO><DESADDR>3</DESADDR><VELIST>A</VELIST><CUSTOMVALUE>0.00000</CUSTOMVALUE><SRCNAME>1</SRCNAME><GOODNO>0402100000</GOODNO><SRCADDR>1</SRCADDR><WEIGHT>0.00000</WEIGHT><ADDTAX>17.00000</ADDTAX><CUSDATE>2012-03-03</CUSDATE><CUSTOM>0.00000</CUSTOM><DESTEL>3</DESTEL><CUSTYPE>制衣辅料（拉链、钮扣、胶珠）</CUSTYPE></row><row><DESNAME>2</DESNAME><VALUE>2.000000</VALUE><QUANTITY>2</QUANTITY><GATETYPE>A</GATETYPE><SRCNM>整改部门</SRCNM><GDESC>达到</GDESC><AWEIGHT>2.00000</AWEIGHT><DESNM>陕西区部</DESNM><CUSPC>CAN0100HKG</CUSPC><ADDTAXVALUE>0.34000</ADDTAXVALUE><NOTE>null</NOTE><SRCCOMPANY>2</SRCCOMPANY><SRCTEL>2</SRCTEL><DESCOMPANY>2</DESCOMPANY><GMODEL>无牌40码|无牌38码|无牌36码|无牌45码|无牌43码|无牌42码|无牌41码|无牌39码|无牌37码</GMODEL><BNO>619438330077</BNO><DESADDR>2</DESADDR><VELIST>A</VELIST><CUSTOMVALUE>0.00000</CUSTOMVALUE><SRCNAME>2</SRCNAME><GOODNO>0402100000</GOODNO><SRCADDR>2</SRCADDR><WEIGHT>0.00001</WEIGHT><ADDTAX>17.00000</ADDTAX><CUSDATE>2012-03-03</CUSDATE><CUSTOM>0.00000</CUSTOM><DESTEL>2</DESTEL><CUSTYPE>制衣辅料（拉链、钮扣、胶珠）</CUSTYPE></row><row><DESNAME>1</DESNAME><VALUE>1.000000</VALUE><QUANTITY>1</QUANTITY><GATETYPE>A</GATETYPE><SRCNM>整改部门</SRCNM><GDESC>奶粉</GDESC><AWEIGHT>1.00000</AWEIGHT><DESNM>整改部门</DESNM><CUSPC>CAN0100HKG</CUSPC><ADDTAXVALUE>0.17000</ADDTAXVALUE><SRCCOMPANY>1</SRCCOMPANY><SRCTEL>1</SRCTEL><DESCOMPANY>1</DESCOMPANY><GMODEL>无牌40码|无牌38码|无牌36码|无牌45码|无牌43码|无牌42码|无牌41码|无牌39码|无牌37码</GMODEL><BNO>581518516212</BNO><DESADDR>1</DESADDR><VELIST>A</VELIST><CUSTOMVALUE>0.00000</CUSTOMVALUE><SRCNAME>1</SRCNAME><GOODNO>0402100000</GOODNO><SRCADDR>1</SRCADDR><WEIGHT>0.00000</WEIGHT><ADDTAX>17.00000</ADDTAX><CUSDATE>2012-03-03</CUSDATE><CUSTOM>0.00000</CUSTOM><DESTEL>1</DESTEL><CUSTYPE>制衣辅料（拉链、钮扣、胶珠）</CUSTYPE></row><row><DESNAME>1</DESNAME><VALUE>1.000000</VALUE><QUANTITY>1</QUANTITY><GATETYPE>aaa</GATETYPE><SRCNM>整改部门</SRCNM><GDESC>奶粉</GDESC><AWEIGHT>1.00000</AWEIGHT><DESNM>整改部门</DESNM><CUSPC>CAN0100HKG</CUSPC><ADDTAXVALUE>0.17000</ADDTAXVALUE><SRCCOMPANY>1</SRCCOMPANY><SRCTEL>1</SRCTEL><DESCOMPANY>1</DESCOMPANY><GMODEL>无牌40码|无牌38码|无牌36码|无牌45码|无牌43码|无牌42码|无牌41码|无牌39码|无牌37码</GMODEL><BNO>610438330077</BNO><DESADDR>1</DESADDR><VELIST>A</VELIST><CUSTOMVALUE>0.00000</CUSTOMVALUE><SRCNAME>11</SRCNAME><GOODNO>0402100000</GOODNO><SRCADDR>1</SRCADDR><WEIGHT>0.00000</WEIGHT><ADDTAX>17.00000</ADDTAX><CUSDATE>2012-03-03</CUSDATE><CUSTOM>0.00000</CUSTOM><DESTEL>1</DESTEL><CUSTYPE>制衣辅料（拉链、钮扣、胶珠）</CUSTYPE></row><row><DESNAME>2</DESNAME><VALUE>1.000000</VALUE><QUANTITY>1</QUANTITY><GATETYPE>HKA</GATETYPE><SRCNM>整改部门</SRCNM><GDESC>奶粉</GDESC><AWEIGHT>1.00000</AWEIGHT><DESNM>陕西区部</DESNM><CUSPC>CAN0100HKG</CUSPC><ADDTAXVALUE>0.17000</ADDTAXVALUE><SRCCOMPANY>1</SRCCOMPANY><SRCTEL>1</SRCTEL><DESCOMPANY>2</DESCOMPANY><GMODEL>无牌40码|无牌38码|无牌36码|无牌45码|无牌43码|无牌42码|无牌41码|无牌39码|无牌37码</GMODEL><BNO>102586941848</BNO><DESADDR>2</DESADDR><VELIST>A</VELIST><CUSTOMVALUE>0.00000</CUSTOMVALUE><SRCNAME>1</SRCNAME><GOODNO>0402100000</GOODNO><SRCADDR>1</SRCADDR><WEIGHT>0.00000</WEIGHT><ADDTAX>17.00000</ADDTAX><CUSDATE>2012-03-03</CUSDATE><CUSTOM>0.00000</CUSTOM><DESTEL>2</DESTEL><CUSTYPE>制衣辅料（拉链、钮扣、胶珠）</CUSTYPE></row><row><DESNAME>3</DESNAME><VALUE>2.000000</VALUE><QUANTITY>2</QUANTITY><GATETYPE>HKA</GATETYPE><SRCNM>陕西区部</SRCNM><GDESC>奶粉</GDESC><AWEIGHT>2.00000</AWEIGHT><DESNM>整改部门</DESNM><CUSPC>CAN0100HKG</CUSPC><ADDTAXVALUE>0.34000</ADDTAXVALUE><SRCCOMPANY>3</SRCCOMPANY><SRCTEL>3</SRCTEL><DESCOMPANY>3</DESCOMPANY><GMODEL>无牌,型号mht-014|无牌,型号mht-015|无牌,型号mht-016|无牌,型号mht-017</GMODEL><BNO>102586941848</BNO><DESADDR>3</DESADDR><VELIST>A</VELIST><CUSTOMVALUE>0.00000</CUSTOMVALUE><SRCNAME>3</SRCNAME><GOODNO>0402100000</GOODNO><SRCADDR>3</SRCADDR><WEIGHT>0.00000</WEIGHT><ADDTAX>17.00000</ADDTAX><CUSDATE>2012-03-03</CUSDATE><CUSTOM>0.00000</CUSTOM><DESTEL>3</DESTEL><CUSTYPE>制衣辅料（拉链、钮扣、胶珠）</CUSTYPE></row><row><DESNAME>2</DESNAME><VALUE>1.000000</VALUE><QUANTITY>1</QUANTITY><GATETYPE>aaa</GATETYPE><SRCNM>整改部门</SRCNM><GDESC>奶粉</GDESC><AWEIGHT>1.00000</AWEIGHT><DESNM>陕西区部</DESNM><CUSPC>CAN0100HKG</CUSPC><ADDTAXVALUE>0.17000</ADDTAXVALUE><SRCCOMPANY>1</SRCCOMPANY><SRCTEL>1</SRCTEL><DESCOMPANY>2</DESCOMPANY><GMODEL>无牌,型号mht-014|无牌,型号mht-015|无牌,型号mht-016|无牌,型号mht-017</GMODEL><BNO>137489346064</BNO><DESADDR>2</DESADDR><VELIST>A</VELIST><CUSTOMVALUE>0.00000</CUSTOMVALUE><SRCNAME>1</SRCNAME><GOODNO>0402100000</GOODNO><SRCADDR>11</SRCADDR><WEIGHT>0.00000</WEIGHT><ADDTAX>17.00000</ADDTAX><CUSDATE>2012-03-03</CUSDATE><CUSTOM>0.00000</CUSTOM><DESTEL>2</DESTEL><CUSTYPE>制衣辅料（拉链、钮扣、胶珠）</CUSTYPE></row><row><DESNAME>3</DESNAME><VALUE>2.000000</VALUE><QUANTITY>2</QUANTITY><GATETYPE>HKA</GATETYPE><SRCNM>陕西区部</SRCNM><GDESC>奶粉</GDESC><AWEIGHT>2.00000</AWEIGHT><DESNM>陕西区部</DESNM><CUSPC>CAN0100HKG</CUSPC><ADDTAXVALUE>0.34000</ADDTAXVALUE><SRCCOMPANY>3</SRCCOMPANY><SRCTEL>3</SRCTEL><DESCOMPANY>3</DESCOMPANY><GMODEL>无牌40码|无牌38码|无牌36码|无牌45码|无牌43码|无牌42码|无牌41码|无牌39码|无牌37码</GMODEL><BNO>137489346064</BNO><DESADDR>3</DESADDR><VELIST>A</VELIST><CUSTOMVALUE>0.00000</CUSTOMVALUE><SRCNAME>3</SRCNAME><GOODNO>0402100000</GOODNO><SRCADDR>3</SRCADDR><WEIGHT>0.00000</WEIGHT><ADDTAX>17.00000</ADDTAX><CUSDATE>2012-03-03</CUSDATE><CUSTOM>0.00000</CUSTOM><DESTEL>3</DESTEL><CUSTYPE>制衣辅料（拉链、钮扣、胶珠）</CUSTYPE></row></root>";

		JNative jn = null;
		try {
			jn = new JNative(DLL_NAME, "CreateRTFFile");
			jn.setRetVal(Type.VOID);
			jn.setParameter(0, Type.STRING, xml);
			jn.setParameter(1, Type.STRING,
					"E:/workspace/testProject/config/深圳机场进口A.rtf"
							.getBytes("utf8"));
			jn.setParameter(2, Type.STRING, "E:/测试dll导出文件.doc");
			jn.setParameter(3, Type.INT, "1");
			jn.invoke();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeJNative(jn);
		}

		System.out.println("aaaaaaaaaaaa");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("aaaaaaaaaaaabbb");
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
