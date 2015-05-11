package com.collection;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 描述：Map比较器
 * 
 * <pre>
 * 根据设置的比较条件进行比较，比较map中某些key对应的值
 * condition的key为要比较的map中的key，value为true表示升序，false为降序
 * 比较BigDecimal，BigInteger，Date，java.sql.Date，类型；其余做String比较
 * 
 * <pre>
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-6-14      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class MapComparator implements Comparator<Map<String, Object>> {

	/**
	 * 条件: key,升序排列
	 */
	private LinkedHashMap<String, Boolean> condition;

	/**
	 * 构造函数
	 * 
	 * @param condition
	 *            比较条件，condition的key为要比较的map中的key，value为true表示升序，false为降序
	 */
	public MapComparator(LinkedHashMap<String, Boolean> condition) {
		this.condition = condition;
	}

	@Override
	public int compare(Map<String, Object> o1, Map<String, Object> o2) {
		if (condition == null || condition.isEmpty())
			return 0;
		if (o1 == null && o2 == null)
			return 0;
		if (o1 == null)
			return -1;
		if (o2 == null)
			return 1;
		for (Entry<String, Boolean> entry : condition.entrySet()) {
			String key = entry.getKey();
			Boolean ascend = entry.getValue();
			Object obj1 = o1.get(key);
			Object obj2 = o2.get(key);

			int i = comparable(obj1, obj2);
			if (i != 0) {
				if (!ascend) {
					return 0 - i;
				} else {
					return i;
				}
			}
		}

		return 0;
	}

	/**
	 * <pre>
	 * 比较
	 * </pre>
	 * 
	 * @param o1
	 *            对象1
	 * @param o2
	 *            对象2
	 * @return 如果对象1小于、等于或大于对象2，则分别返回负整数、零或正整数
	 */
	private int comparable(Object o1, Object o2) {
		if (o1 == null && o2 == null) {
			return 0;
		}
		if (o1 == null) {
			return -1;
		}
		if (o2 == null) {
			return 1;
		}
		// 常用类型放在前面
		if (o1 instanceof String) {
			String tmp1 = (String) o1;
			String tmp2 = (String) o2;
			return tmp1.compareTo(tmp2);
		}
		if (o1 instanceof BigDecimal) {
			BigDecimal tmp1 = (BigDecimal) o1;
			BigDecimal tmp2 = (BigDecimal) o2;
			return tmp1.compareTo(tmp2);
		}
		if (o1 instanceof Date) {
			Date tmp1 = (Date) o1;
			Date tmp2 = (Date) o2;
			return tmp1.compareTo(tmp2);
		}
		if (o1 instanceof BigInteger) {
			BigInteger tmp1 = (BigInteger) o1;
			BigInteger tmp2 = (BigInteger) o2;
			return tmp1.compareTo(tmp2);
		}
		if (o1 instanceof java.sql.Date) {
			java.sql.Date tmp1 = (java.sql.Date) o1;
			java.sql.Date tmp2 = (java.sql.Date) o2;
			return tmp1.compareTo(tmp2);
		}
		if (o1 instanceof Integer) {
			Integer tmp1 = (Integer) o1;
			Integer tmp2 = (Integer) o2;
			return tmp1.compareTo(tmp2);
		}
		if (o1 instanceof Double) {
			Double tmp1 = (Double) o1;
			Double tmp2 = (Double) o2;
			return tmp1.compareTo(tmp2);
		}
		if (o1 instanceof Long) {
			Long tmp1 = (Long) o1;
			Long tmp2 = (Long) o2;
			return tmp1.compareTo(tmp2);
		}
		// 做字符串处理
		String tmp1 = o1.toString();
		String tmp2 = o2.toString();
		return tmp1.compareTo(tmp2);

	}

	/**
	 * <pre>
	 * 设置比较条件
	 * </pre>
	 * 
	 * @return
	 */
	public LinkedHashMap<String, Boolean> getCondition() {
		return condition;
	}

	/**
	 * <pre>
	 * 获取比较条件
	 * </pre>
	 * 
	 * @param condition
	 */
	public void setCondition(LinkedHashMap<String, Boolean> condition) {
		this.condition = condition;
	}

	public static void main(String[] args) {
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("key1", "abc");
		map1.put("key2", new BigDecimal("1"));
		map1.put("key3", 13.1);

		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("key1", "bcd");
		map2.put("key2", new BigDecimal("1"));
		map2.put("key3", 12.1);

		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("key1", "def");
		map3.put("key2", new BigDecimal("3"));
		map3.put("key3", 110.1);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(map1);
		list.add(map2);
		list.add(map3);

		LinkedHashMap<String, Boolean> condition = new LinkedHashMap<String, Boolean>();
		condition.put("key3", true);
		condition.put("key2", true);
		condition.put("key1", false);

		System.out.println("排序前：");
		System.out.println(list);
		Collections.sort(list, new MapComparator(condition));
		System.out.println("排序后：");
		System.out.println(list);

	}

}
