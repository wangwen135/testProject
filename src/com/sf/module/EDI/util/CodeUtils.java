package com.sf.module.EDI.util;

/**
 * 描述：编码工具类，用于HTML和XML
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-3-31      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class CodeUtils {
	/**
	 * <pre>
	 * XML编码特殊字符
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static String xmlEncode(String str) {
		if (str == null || "".equals(str)) {
			return "";
			// return str;
		}
		return str.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;").replaceAll("'", "&apos;")
				.replaceAll("\"", "&quot;");
	}

	/**
	 * <pre>
	 * XML解码特殊字符
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static String xmlDecode(String str) {
		if (str == null || "".equals(str)) {
			return "";
			// return str;
		}
		return str.replaceAll("&amp;", "&").replaceAll("&lt;", "<")
				.replaceAll("&gt;", ">").replaceAll("&apos;", "'")
				.replaceAll("&quot;", "\"");

	}

	/**
	 * <pre>
	 * HTML编码特殊字符
	 * 目前只处理   '&'  '&lt;'  '&gt;'  '"'  ' ' ;
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static String htmlEncode(String str) {
		if (str == null || "".equals(str)) {
			return "";
			// return str;
		}
		return str.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;").replaceAll("\"", "&quot;")
				.replaceAll("[ ]", "&nbsp;");
	}

	/**
	 * <pre>
	 * HTML解码特殊字符
	 * 目前只处理   '&'  '&lt;'  '&gt;'  '"'  ' ' ;
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static String htmlDecode(String str) {
		if (str == null || "".equals(str)) {
			return "";
			// return str;
		}
		return str.replaceAll("&amp;", "&").replaceAll("&lt;", "<")
				.replaceAll("&gt;", ">").replaceAll("&quot;", "\"")
				.replaceAll("&nbsp;", " ");

	}

	public static void main(String[] args) {
		String str = "|   |";
		String str2 = htmlEncode(str);
		System.out.println(str2);
		System.out.println(htmlDecode(str2));
	}
}
