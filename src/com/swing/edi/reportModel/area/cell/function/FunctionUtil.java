package com.swing.edi.reportModel.area.cell.function;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Element;

/**
 * 描述：函数工具
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-2-7      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class FunctionUtil {

	private static Map<String, Class<?>> functionsMap = new HashMap<String, Class<?>>();
	static {
		functionsMap.put(SumFunction.TYPE, SumFunction.class);
		functionsMap.put(SequenceFunction.TYPE, SequenceFunction.class);
		functionsMap.put(DateFunction.TYPE, DateFunction.class);
	}

	/**
	 * <pre>
	 * 根据函数类型获取函数，函数的其他属性没有被初始化
	 * </pre>
	 * 
	 * @param type
	 *            函数类型
	 * @return 未经初始化的函数
	 * @see FunctionUtil#getFunctionByElement(Element)
	 */
	public static IFunction getFunctionByType(String type) {
		Class<?> c = functionsMap.get(type);
		if (c == null) {
			return null;
		} else {
			try {
				return (IFunction) c.newInstance();
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
	 * 根据xml节点获取function对象，并对其进行初始化
	 * </pre>
	 * 
	 * @param e
	 *            xml元素
	 * @return if(e==null) return null;
	 */
	public static IFunction getFunctionByElement(Element e) {
		if (e != null) {
			IFunction f = getFunctionByType(e.attributeValue("type"));
			f.fromXml(e);
			return f;
		}
		return null;
	}
}
