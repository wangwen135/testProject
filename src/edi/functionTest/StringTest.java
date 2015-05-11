package edi.functionTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringTest {

	private static Logger logger = LoggerFactory.getLogger(StringTest.class);

	public static void main(String[] args) {
		String value = "null";

		if (value != null && value != "") {
			System.out.println("aaaaaaaaaaaaaa");
		} else {
			System.out.println("bbbbbbbbbbbbb");
		}

		String acctCode = "";
		String tCode = "";
		if (acctCode.equals("8860180614"))
			tCode = "96972798";
		else if (acctCode.equals("8860149944"))
			tCode = "12981393";

		else if (acctCode.equals("8860394886"))
			tCode = "53719023";

		else if (acctCode.equals("8860229530"))
			tCode = "53729486";
		else if (acctCode.equals("8860370909"))
			tCode = "53972989";

		else if (acctCode.equals("8860402492") || acctCode.equals("8860402487")
				|| acctCode.equals("8860412622"))
			tCode = "24850430";

	}

	public static void main4(String[] args) {
		String s = "衣服1件鞋子1双棉布12.340公斤";
		// 判断字符串中的数字
		Pattern p = Pattern.compile("[1-9]+\\d*(\\.\\d+)?");

		Matcher m = p.matcher(s);

		while (m.find()) {
			System.out.println(m.group());
		}
	}

	public static void main3(String[] args) {
		String s = "50－100";
		System.out.println(s.split("－").length);
	}

	public static void main2(String[] args) {
		String hscode = "3926909090";

		String[] hscode1 = new String[] { "6702100000", "3926209000",
				"3926909090" };

		String[] hscode2 = new String[] { "27000000", "27000000", "27000000" };

		for (int i = 0; i < hscode1.length; i++) {
			String s = hscode1[i];
			if (s.equals(hscode)) {
				// return hscode2[i];
				System.out.println(hscode2[i]);
			}

		}
		// return "";

	}

	/**
	 * <pre>
	 * 
	 * </pre>
	 * 
	 * @param args
	 */
	public static void main1(String[] args) {
		String s = "1230 45  45465  ";
		s = s.replace(" ", "");
		System.out.println(s);
		// logger.debug("afafasfasfasd");
	}

}
