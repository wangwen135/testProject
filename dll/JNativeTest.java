package ttttt;

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
		String xml = "<?xml version=\"1.0\" encoding=\"gb2312\"?><root><row><DESNAME>收件联络人0</DESNAME><VALUE>100</VALUE><QUANTITY>0/单位</QUANTITY><GATETYPE>0</GATETYPE><SRCNM>原寄地名称x0</SRCNM><GDESC>托寄物内容0</GDESC><AWEIGHT>012.20</AWEIGHT><PDESNM>列印目的地代码0</PDESNM><DESNM>目的地名称x0</DESNM><CUSPC>报关批次0</CUSPC><NOTE>备注0</NOTE><SRCCOMPANY>寄件公司0</SRCCOMPANY><SRCTEL>寄件电话0</SRCTEL><UPSIGN>修改标志0</UPSIGN><DESCOMPANY>收件公司0</DESCOMPANY><GMODEL>规格03型号00</GMODEL><SENDTIME>寄件时间0</SENDTIME><DESADDR>收件地址0</DESADDR><ADSIGN>审核标志0</ADSIGN><VELIST>车次0</VELIST><SRCNAME>寄件联络人0</SRCNAME><GOODNO>TX320</GOODNO><SRCADDR>寄件地址0</SRCADDR><WEIGHT>0.10</WEIGHT><SENDDATE>寄件日期0</SENDDATE><CUSDATE>2011-02-12</CUSDATE><PASSSIGN>清关标志0</PASSSIGN><DESTEL>收件电话0</DESTEL><PSRCNM>列印原寄地代码0</PSRCNM><CUSTYPE>类别1020</CUSTYPE></row></root>";

		JNative jn = null;
		try {
			jn = new JNative(DLL_NAME, "CreateRTFFile");
			jn.setRetVal(Type.VOID);
			jn.setParameter(0, Type.STRING, xml);
			jn.setParameter(1, Type.STRING,
					"D:/JavaProject/新建文件夹/深圳机场进口A.rtf");
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
