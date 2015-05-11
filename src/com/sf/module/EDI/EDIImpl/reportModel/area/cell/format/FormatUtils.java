package com.sf.module.EDI.EDIImpl.reportModel.area.cell.format;

import java.text.DecimalFormat;

/**
 * 描述：格式化工具
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-4-1      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class FormatUtils {
	/**
	 * 默认的数字格式化<br>
	 * 保留3位小数
	 */
	public static DecimalFormat numFormat = new DecimalFormat("####0.########");

	/**
	 * <pre>
	 * 使用默认的格式格式化数字
	 * </pre>
	 * 
	 * @param d
	 * @return
	 */
	public static String formatDouble(Double d) {
		return numFormat.format(d);
	}

	public static void main(String[] args) {

		System.out.println(formatDouble(12112223.123));
	}
}
