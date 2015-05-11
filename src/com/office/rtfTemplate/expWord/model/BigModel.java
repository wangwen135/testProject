package com.office.rtfTemplate.expWord.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.office.rtfTemplate.expWord.ExpWordUtils;
import com.swing.edi.report.utils.ReportUtils;

/**
 * 描述： 大模型，里面包含一个list，list里面放了导出报表的数据<br>
 * 提供各种取值、计算、格式化、取日期的方法，便于在RTF模板中直接调用<br>
 * 定义通用、统一的语意 如:
 * <ol>
 * <li>$h.count() 求总数
 * <li>$h.sum('cusvalue') 求‘cusvalue’字段的总和
 * <li>$h.avg('weight',7,3) 求‘weight’字段的平均值，并格式化，7位整数，3位小数
 * <li>$h.getDate('yyyyMMdd') 取自定义的时间
 * <li>$h.add(1,2) 做加法运算
 * </ol>
 * 更多请参考具体代码和模板
 * 
 * <pre>
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
public class BigModel {

	public static Logger logger = Logger.getLogger(BigModel.class);
	private List<Map<String, String>> list;

	public BigModel(List<Map<String, String>> list) {
		if (list == null) {
			throw new NullPointerException("list不能为空！");
		}
		this.list = list;
	}

	// ###### start 统计类型的函数 ######//

	/**
	 * <pre>
	 * 求总和，并格式化返回结果
	 * </pre>
	 * 
	 * @param key
	 *            字段名称
	 * @param integer
	 *            整数位
	 * @param fraction
	 *            小数位
	 * @return 返回格式化之后的值
	 */
	public String sum(String key, int integer, int fraction) {
		return fmt(sum(key), integer, fraction);
	}

	/**
	 * <pre>
	 * 求总和
	 * </pre>
	 * 
	 * @param key
	 *            字段名称
	 * @return 返回未格式化的值
	 */
	public String sum(String key) {
		BigDecimal b = new BigDecimal(0);
		for (Map<String, String> row : list) {
			BigDecimal tmp = new BigDecimal(row.get(key));
			b = b.add(tmp);
		}
		return b.toString();
	}

	/**
	 * <pre>
	 * 求平均值，并格式化返回结果
	 * </pre>
	 * 
	 * @param key
	 *            字段名称
	 * @param integer
	 *            整数位
	 * @param fraction
	 *            小数位
	 * @return 格式化之后的字符串
	 */
	public String avg(String key, int integer, int fraction) {
		return fmt(avg(key), integer, fraction);
	}

	/**
	 * <pre>
	 * 求平均值
	 * </pre>
	 * 
	 * @param key
	 *            字段名称
	 * @return 返回未格式化的值
	 */
	public String avg(String key) {
		BigDecimal b = new BigDecimal(0);
		for (Map<String, String> row : list) {
			BigDecimal tmp = new BigDecimal(row.get(key));
			b = b.add(tmp);
		}
		return b.divide(new BigDecimal(list.size())).toString();
	}

	/**
	 * <pre>
	 * 求最大值，并格式化结果
	 * </pre>
	 * 
	 * @param key
	 *            字段名称
	 * @param integer
	 *            整数位
	 * @param fraction
	 *            小数位
	 * @return 格式化之后的字符串
	 */
	public String max(String key, int integer, int fraction) {
		return fmt(max(key), integer, fraction);
	}

	/**
	 * <pre>
	 * 求最大值
	 * </pre>
	 * 
	 * @param key
	 *            字段名称
	 * @return 返回未格式化的值
	 */
	public String max(String key) {
		BigDecimal b = new BigDecimal(Double.MIN_VALUE);
		for (Map<String, String> row : list) {
			BigDecimal tmp = new BigDecimal(row.get(key));
			b = b.max(tmp);
		}
		return b.toString();
	}

	/**
	 * <pre>
	 * 求最小值，并格式化返回结果
	 * </pre>
	 * 
	 * @param key
	 *            字段名称
	 * @param integer
	 *            整数位
	 * @param fraction
	 *            小数位
	 * @return 格式化之后的字符串
	 */
	public String min(String key, int integer, int fraction) {
		return fmt(min(key), integer, fraction);
	}

	/**
	 * <pre>
	 * 求最小值
	 * </pre>
	 * 
	 * @param key
	 *            字段名称
	 * @return 返回未格式化的值
	 */
	public String min(String key) {
		BigDecimal b = new BigDecimal(Double.MAX_VALUE);
		for (Map<String, String> row : list) {
			BigDecimal tmp = new BigDecimal(row.get(key));
			b = b.min(tmp);
		}
		return b.toString();
	}

	/**
	 * <pre>
	 * 总数量
	 * </pre>
	 * 
	 * @return
	 */
	public String count() {
		return list.size() + "";
	}

	// ###### end 统计类型的函数 ######//

	// ###### start 基本运算 ######//
	// 加
	public double add(double a, double b) {
		return a + b;
	}

	// 减 subtract
	public double sub(double a, double b) {
		return a - b;
	}

	// 乘 multiply
	public double mul(double a, double b) {
		return a * b;
	}

	// 除 divide
	public double div(double a, double b) {
		return a / b;
	}

	// 取模 remainder
	public double rem(double a, double b) {
		return a % b;
	}

	// ###### end 基本运算 ######//
	/**
	 * <pre>
	 * 格式化数字
	 * 使用四舍五入
	 * </pre>
	 * 
	 * @param value
	 *            字符串形式的值
	 * @param integer
	 *            整数位
	 * @param fraction
	 *            小数位
	 * @return 格式化之后的字符串
	 */
	public String fmt(String value, int integer, int fraction) {
		return ExpWordUtils.formatNumber(value, integer, fraction);
	}

	/**
	 * <pre>
	 * 根据格式取日期和时间，从系统当前时间取
	 * </pre>
	 * 
	 * @param format
	 *            格式，如果格式错误将使用默认格式
	 * @return 返回格式化之后的日期
	 */
	public String getDate(String format) {
		SimpleDateFormat s = null;
		try {
			s = new SimpleDateFormat(format);
		} catch (Exception e) {
			logger.error("取日期时格式错误，将使用默认格式", e);
			s = new SimpleDateFormat();
		}
		// 如果格式中出现非英文字符可能需要进行编码！！！
		return ExpWordUtils.encode(s.format(new Date()));

	}

	public static void main(String[] args) {
		BigModel b = new BigModel(ReportUtils.getAnalogData(100));
		System.out.println(b.sum("weight"));
		System.out.println(b.avg("weight"));
		System.out.println(b.max("weight"));
		System.out.println(b.min("weight"));
		System.out.println(b.fmt(b.sum("pcs"), 20, 12));
		System.out.println(b.getDate("yyyy年"));
	}

}
