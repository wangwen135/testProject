package com.swing.test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;



public class StringTest {

	
	public static void main(String[] args) {
		String srccompany = "寄件公司aab";
		
		String target = srccompany.toUpperCase();
		
		System.out.println(target);
		
		String reg ="公司、集团、中心、LTD、学院、美容、学校、小学、厅、行、办、厂、部、所、局、处、店、业、室";
		String[] regs = reg.split("、");
		for (String s : regs) {
			if(target.contains(s)){
				//返回原值
				System.out.println(srccompany);
			}
		}
		
		//返回增加公司的值
		System.out.println(srccompany+"公司");
		
	}
	
	public static void main29(String[] args) {
		String batch ="SZX2122HKG";
		String c =  batch.substring(0, 3);
		
		if("CAN".equals(c)){
			
		}else if("SZX".equals(c)){
			
		}
	}
	
	public static void main28(String[] args) {
		//首字母大写
		String value ="aaa";
		if(value!=null && !"".equals(value)){
			String s = value.substring(0, 1).toUpperCase()+value.substring(1);
			System.out.println(s);
		}else{
		
		System.out.println("空");
		}
	}
	
	public static void main27(String[] args) {
		BigDecimal exc = new BigDecimal("6.2");
		
		BigDecimal tax = new BigDecimal("100");
		//乘以汇率 四舍五入
		
		tax = tax.multiply(exc).setScale(2, BigDecimal.ROUND_HALF_UP);
		
		//税金小于50的不显示
		if(tax.compareTo(new BigDecimal("50"))==1){
			System.out.println(tax.stripTrailingZeros().toPlainString());
		}else{
			System.out.println("空");
		}
		
		//还有一个统计
	}
	
	public static void main26(String[] args) {
		// 拆单前托寄物
		String oldGdesc = "T恤,裤,内衣";
		// 拆单前数量单位
		String oldQuantity = "2,件,1,0.2;1,条,40,0.5;1,件,40,0.5";

		// 拆分多品名标记
		String splitmultgoodsFlag = "1";
		// 单品名 0
		// 多品名中的第一条 1
		// 多品名中的非第一条 2

		// 如果是多品名
		// 如果是多品名中第一条则显示
		// 多品名中非第一条不显示

		// 否是是单品名

		if (oldQuantity.contains(";")) {// 多品名
			// 非第一条
			if (!"1".equals(splitmultgoodsFlag)) {
				System.out.println("为空。。。。");
				return;
			}

			try {
				StringBuffer strbuf = new StringBuffer();

				String[] gdescs = oldGdesc.split(",");
				String[] quantitys = oldQuantity.split(";");

				for (int i = 0; i < gdescs.length; i++) {
					if (i != 0) {
						strbuf.append("、");
					}

					strbuf.append(gdescs[i]);
					strbuf.append("*");

					// 数量单位
					String tquantitys = quantitys[i];
					String[] arrayTquantity = tquantitys.split(",");

					String tquantity = arrayTquantity[0];// 数量
					String tvalue = arrayTquantity[2];// 价值

					strbuf.append(tquantity);
					strbuf.append("*");

					strbuf.append(new BigDecimal(tvalue)
							.divide(new BigDecimal(tquantity), 2,
									BigDecimal.ROUND_HALF_UP)
							.stripTrailingZeros().toPlainString());

				}

				System.out.println(strbuf.toString());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "运单号：多品名格式不正确！");
				return;
			}

		} else {// 单品名

		}

	}

	public static void main25(String[] args) {

		String s = System.getProperty("sun.java.command");
		System.out.println(s);

		// 净重：
		// 当实际重量≤1KG时，净重=实际重量
		// 当1KG＜实际重量≤30KG时，净重=实际重量*95%
		// 当30KG＜实际重量≤50KG时，净重=实际重量*94%
		// 当50KG＜实际重量时，净重=实际重量*93%

		double aweight = 1100121.3;
		double newWeight;

		if (aweight <= 1) {
			newWeight = aweight;
		} else if (1 < aweight && aweight <= 30) {
			newWeight = aweight * 0.95;
		} else if (30 < aweight && aweight <= 50) {
			newWeight = aweight * 0.94;
		} else {
			newWeight = aweight * 0.93;
		}

		System.out.println(newWeight);

		NumberFormat f = NumberFormat.getInstance();
		f.setMaximumFractionDigits(1);
		f.setGroupingUsed(false);
		System.out.println(f.format(newWeight));

	}

	public static void main24(String[] args) {
		String gdesc = "棉衣1件!@#$%^&*()_+棉布10米";

		String s2 = gdesc.replaceAll(
				"[^\u4e00-\u9fa5A-Za-z0-9.,\\-;()#_%|，*\\s&]", "");

		System.out.println(s2);
	}

	public static void main23(String[] args) {
		StringBuffer sb = new StringBuffer("1.5kg,14个,100.1");// 115.6
		for (int i = 0; i < sb.length(); i++) {

			if (!(sb.charAt(i) >= '0' && sb.charAt(i) <= '9')
					&& sb.charAt(i) != '.') {
				sb.setCharAt(i, ',');
			}
		}

		System.out.println(sb);

		System.out.println(getScount(sb.toString()));
	}

	public static double getScount(String _quantity) {

		StringBuffer sb = new StringBuffer(_quantity);
		for (int i = 0; i < sb.length(); i++) {

			if (!(sb.charAt(i) >= '0' && sb.charAt(i) <= '9')
					&& sb.charAt(i) != '.') {
				sb.setCharAt(i, ',');
			}
		}
		String[] strings = sb.toString().split(",");
		double sum = 0.0;// 算出来的数量/单位的总和
		for (int i = 0; i < strings.length; i++) {
			if ("".equals(strings[i])) {
				strings[i] = "0.0";
			}
			if (".".equals(strings[i])) {
				strings[i] = "0.0";
			}

			double dou = Double.parseDouble(strings[i]);
			sum += dou;// 算出来的数量/单位的总和
		}
		return sum;

	}

	public static void main22(String[] args) {
		String a1 = "\uFF11\uFF12\uFF13\uFF14";
		String a2 = "\u0661\u0662\u0663\u0664";
		String a3 = "\u06F1\u06F2\u06F3\u06F4";
		String a4 = "\u0967\u0968\u0969\u096A";

		System.out.println(a1);
		System.out.println(a2);
		System.out.println(a3);
		System.out.println(a4);

		int i1 = Integer.valueOf(a1);

		System.out.println(i1);
	}

	public static void main21(String[] args) {
		String sbno = "001897728248 001897728257 001897728239 001897728220";
		String[] sa = sbno.split(" ");
		System.out.println(sa.length);
		List<String> list = Arrays.asList(sa);
		System.out.println(list.size());
		System.out.println(list.getClass());
		System.out.println(list);
		System.out.println(Arrays.toString(sa));
	}

	public static void main20(String[] args) {
		String src_union_no = "123456789011321";
		if (src_union_no != null) {
			System.out.println(src_union_no.length() > 10 ? src_union_no
					.substring(0, 10) : src_union_no);
		}
	}

	public static void main19(String[] args) {
		String gdesc = "LED灯,棉布";
		String quantity = "2.5,千克,63,2;1,米,20,0.5";

		if (quantity.contains(";")) {

			String[] gdescs = gdesc.split(",");
			String[] quantitys = quantity.split(";");

			StringBuffer strbuf = new StringBuffer();

			String[] one = null;
			for (int i = 0; i < gdescs.length; i++) {
				if (i != 0) {
					strbuf.append(",");
				}
				strbuf.append(gdescs[i]);

				one = quantitys[i].split(",");
				strbuf.append(one[0]);
				strbuf.append(one[1]);
			}

			System.out.println(strbuf.toString());

		} else {
			System.out.println(gdesc + quantity);
		}

	}

	public static void main18(String[] args) {

		// 品名+数量单位
		// 如果是：
		// gdesc=棉布,纽扣 quantity=16米16千克
		// 则变成：棉布16米,纽扣16千克
		String gdesc = "棉布,纽扣,鞋子";
		String quantity = "16米16千克15只";

		if (gdesc.contains(",")) {
			String[] gdescs = gdesc.split(",");
			Pattern p = Pattern.compile("\\d+\\D+");
			Matcher m = p.matcher(quantity);
			StringBuffer strbuf = new StringBuffer();

			for (int i = 0; i < gdescs.length; i++) {
				if (i != 0) {
					strbuf.append(",");
				}
				strbuf.append(gdescs[i]);
				if (m.find()) {
					strbuf.append(m.group());
				}
			}

			System.out.println(strbuf.toString());

		} else {
			System.out.println(gdesc + quantity);
		}
	}

	public static void main17(String[] args) {
		String bno = "000002345678";

		if (bno.startsWith("00") && !bno.startsWith("000")) {
			System.out.println(bno);
			System.out.println("是子运单");
		}
	}

	public static void main16(String[] args) {
		String gdescStr = "DOC";
		double aweightDou = 0.4;
		if ("doc".equals(gdescStr) || "DOC".equals(gdescStr)
				&& aweightDou < 0.5) {
			System.out.println("0.5");
			System.out.println(new BigDecimal(0.5));
		} else {
			System.out.println("xxxx");
		}
	}

	public static void main15(String[] args) {
		String regex = "[\\\\/:*?\"<>|]";
		String targer = "a\\b/c:d*e?f\"g<h>i|jklm";
		Pattern p = Pattern.compile(regex);
		Matcher mt = p.matcher(targer);
		System.out.println(mt.find());
		while (mt.find()) {
			System.out.println(mt.group());
		}
		System.out.println(mt.replaceAll(""));
	}

	public static void main14(String[] args) {

		String str = "123.32KG个";
		// ^\d+(\.\d+)?
		Pattern p = Pattern.compile("^\\d+(\\.\\d+)?");
		Matcher matcher = p.matcher(str);
		if (matcher.find()) {
			String digit = matcher.group();
			String end = str.substring(matcher.end());
			String END = end.toUpperCase();
			if (END.startsWith("K") && !END.startsWith("KG")) {
				digit = digit + end.substring(0, 1);
				end = end.substring(1);
			}
			System.out.println(digit);
			System.out.println(end);

		} else {
			System.out.println("没有匹配");
		}

	}

	public static void main13(String[] args) {
		String str1 = "abc";
		String str2 = "abc";
		str1 = "bcd";
		String str3 = str1;
		System.out.println(str3); // bcd
		String str4 = "bcd";
		System.out.println(str1 == str4); // true
	}

	// 判断字符串中是否有数字
	public static void main12(String[] args) {
		String str = "棉布112321米";
		Pattern p = Pattern.compile("[0-9]");
		Matcher m = p.matcher(str);

		System.out.println(m.find());

		boolean b = Pattern.matches("[0-9]{5}", "12345");
		System.out.println(b);
	}

	public static void main11(String[] args) {
		String s = "asd   f     Bdf";
		String[] str = s.split("[ ]+");
		for (String string : str) {
			System.out.println(string);
		}
	}

	public static void main10(String[] args) {
		String val = "10.1000";
		val = val.replaceAll("[0]+$", "");
		val = val.replaceAll("[.]$", "");
		System.out.println(val);
	}

	public static void main9(String[] args) {
		String a1 = "棉布1米棉衣1件1米";
		String a2 = "1米";

		if (a1.endsWith(a2)) {
			System.out.println(a1.substring(0, a1.length() - a2.length()));
		}

		String quote = Pattern.quote(a2);
		System.out.println(quote);
		Pattern p = Pattern.compile(a2 + "$");
		Matcher matcher = p.matcher(a1);

		System.out.println(matcher.replaceAll("th"));

		// while(matcher.find()){
		// System.out.println("start:"+matcher.start());
		// System.out.println(matcher.group());
		// System.out.println("end:  "+matcher.end());
		// System.out.println(matcher.groupCount());
		// }

	}

	public static void main8(String[] args) {
		System.out.println("c219".equalsIgnoreCase("C219"));
		String str = ",规格型号,,,";
		String s2 = str.replaceAll("^[,]+$", "");
		System.out.println(s2);
	}

	public static void main7(String[] args) {
		String str = " ひの ソセノメムヘホフフフヒャモムミヲ 埃     保常de fg!@ #$%^asd f  ";
		String s1 = filterString(str);
		System.out.println(s1);

		String s2 = filterSonderzeichen(str);
		System.out.println(s2);

	}

	public static final String matcherStr = "[\\w[\u4e00-\u9fa5][(][)][,][.][;][ ][-][_][%][|]]";
	public static Pattern pattern = Pattern.compile(matcherStr);

	/**
	 * <pre>
	 * 过滤特殊字符
	 * @param src 要过滤的字符串
	 * @return 过滤之后的字符串
	 * </pre>
	 */
	public static String filterSonderzeichen(String src) {
		if (src == null || "".equals(src))
			return "";
		// StringBuffer sbr = new StringBuffer();
		// char[] chars = src.toCharArray();
		// for (char c : chars) {
		// if (pattern.matcher(new String(new char[] { c })).matches()) {
		// sbr.append(c);
		// }
		// }
		// return sbr.toString();
		//
		Matcher m = pattern.matcher(src);
		return m.replaceAll("");
	}

	public static String filterString(String str) {
		String regex = "[^\u2E80-\u9FFFA-Za-z0-9. ,-;()_%|，\\s]";
		Pattern p = Pattern.compile(regex);
		String reStr = null;
		if (str != null && !str.equals("")) {
			Matcher m = p.matcher(str);
			reStr = m.replaceAll("");
		}
		// 剔除首尾空格
		String regex1 = "^\\s*|\\s*$";
		Pattern p2 = Pattern.compile(regex1);
		if (reStr != null && !reStr.equals("")) {
			Matcher m = p2.matcher(reStr);
			reStr = m.replaceAll("");
		}
		return reStr;
	}

	public static void main6(String[] args) {
		String str = "abcasadfsfasdfa";
		StringBuffer sbr = new StringBuffer(str);
		System.out.println(sbr.indexOf("abc"));
	}

	public static void main5(String[] args) {
		String[] arrayTquantity = new String[] { null, "adef" };

		System.out.println((arrayTquantity[0] == null ? "" : arrayTquantity[0])
				+ (arrayTquantity[1] == null ? "" : arrayTquantity[1]));// 数量加单位

	}

	public static void main3(String[] args) {
		List<Integer> widthList = new ArrayList<Integer>();
		widthList.add(10);
		widthList.add(15);
		widthList.add(25);
		widthList.add(55);

		StringBuffer sbuf = new StringBuffer();
		for (Integer integer : widthList) {
			if (sbuf.length() != 0) {
				sbuf.append(";");
			}
			sbuf.append(integer);
		}

		System.out.println(sbuf.toString());

		String[] s2 = sbuf.toString().split(";");

		for (String str : s2) {
			System.out.println(Integer.valueOf(str));
		}

	}

	public static void main2(String[] args) {
		String str = "  adfsf  adf  asf  ";
		System.out.println(str);

		int len = str.length();
		int st = 0;
		char[] val = str.toCharArray();

		while ((st < len) && (val[st] <= ' ')) {
			st++;
		}

		String des = (st > 0) ? str.substring(st, len) : str;
		System.out.println(des);
	}
}
