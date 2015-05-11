package com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.value;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Element;

/**
 * 描述：格式器工具
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-4-11      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class ValueFormatUtil {

	private static Map<String, Class<?>> valueFormatMap = new HashMap<String, Class<?>>();
	static {
		valueFormatMap.put(NumberValueFormat.TYPE, NumberValueFormat.class);
		valueFormatMap.put(StringValueFormat.TYPE, StringValueFormat.class);
		valueFormatMap.put(DateValueFormat.TYPE, DateValueFormat.class);

	}

	/**
	 * <pre>
	 * 根据类型获取数值格式器，属性没有被初始化
	 * </pre>
	 * 
	 * @param type
	 *            类型
	 * @return 未经初始化的格式器
	 */
	public static IValueFormat getValueFormatByType(String type) {
		Class<?> c = valueFormatMap.get(type);
		if (c == null) {
			return null;
		} else {
			try {
				return (IValueFormat) c.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * <pre>
	 * 根据xml节点获取valueFormat对象，并对其进行初始化
	 * </pre>
	 * 
	 * @param e
	 *            xml元素
	 * @return if(e==null) return null;
	 */
	public static IValueFormat getValueFormatByElement(Element e) {
		if (e != null) {
			IValueFormat f = getValueFormatByType(e.attributeValue("type"));
			f.fromXml(e);
			return f;
		}
		return null;
	}

}
