package com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.value;

import java.io.Serializable;

import org.dom4j.Element;

/**
 * 描述：数值格式器
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
public interface IValueFormat extends Serializable {

	/**
	 * XML节点名称
	 */
	public static final String ELEMENT_NAME = "valueFormat";

	/**
	 * <pre>
	 * 格式化
	 * </pre>
	 * 
	 * @param obj
	 * @return
	 */
	public Object format(Object obj);

	/**
	 * <pre>
	 * 获取类型
	 * </pre>
	 * 
	 * @return
	 */
	public String getType();

	/**
	 * <pre>
	 * 转换成XML
	 * </pre>
	 * 
	 * @return
	 */
	public String toXml();

	/**
	 * <pre>
	 * 从XML元素构建对象
	 * </pre>
	 * 
	 * @param xml
	 */
	public void fromXml(Element xml);

}
