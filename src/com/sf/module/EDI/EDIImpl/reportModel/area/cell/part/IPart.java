package com.sf.module.EDI.EDIImpl.reportModel.area.cell.part;

import java.io.Serializable;

/**
 * 连接部件，用于连接两个entity<br>
 * 提供两个entity之间的零部件，如'连接'、'换行'、‘+’、‘-’、‘*’、‘/’, 等<br>
 * 连接符号是系统指定的，不能自定义，如需要自定义则使用常量<br>
 * 
 * @see PartEnum
 */
public interface IPart extends Serializable {
	/**
	 * XML节点名称
	 */
	public static final String ELEMENT_NAME = "part";

	/**
	 * <pre>
	 * 获取类型
	 * </pre>
	 * 
	 * @return
	 */
	String getType();

	/**
	 * <pre>
	 * 获取符号
	 * </pre>
	 * 
	 * @return
	 */
	String getSymbol();

	/**
	 * <pre>
	 * 转换成xml
	 * </pre>
	 * 
	 * @return
	 */
	String toXml();

	// public void fromXml(Element xml);

	/**
	 * <pre>
	 * 获取描述字符串
	 * </pre>
	 * 
	 * @return
	 */
	String getViewStr();

	/**
	 * <pre>
	 * 进行运算，并返回结果
	 * </pre>
	 * 
	 * @param o1
	 *            参数1
	 * @param o2
	 *            参数2
	 * @return 运算结果
	 */
	Object operation(Object o1, Object o2);

	/**
	 * <pre>
	 * 取枚举
	 * </pre>
	 * 
	 * @return 枚举
	 */
	PartEnum getEnum();
}
