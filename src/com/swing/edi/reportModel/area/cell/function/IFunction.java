package com.swing.edi.reportModel.area.cell.function;

import java.awt.Window;
import java.io.Serializable;

import org.dom4j.Element;

/**
 * 描述：函数接口
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
public interface IFunction extends Serializable {

	/**
	 * <pre>
	 * 获取值
	 * </pre>
	 * 
	 * @return
	 */
	Object getValue();

	// 设计不太合理
	/**
	 * <pre>
	 * 设置值
	 * </pre>
	 * 
	 * @param value
	 */
	void setValue(Object value);

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
	 * 转换到xml
	 * </pre>
	 * 
	 * @return
	 */
	String toXml();

	/**
	 * <pre>
	 * 获取显示中文描述
	 * </pre>
	 * 
	 * @return
	 */
	String getViewStr();

	/**
	 * <pre>
	 * 从xml初始化对象
	 * </pre>
	 * 
	 * @param xml
	 */
	void fromXml(Element xml);

	/**
	 * <pre>
	 * 函数是否可以包含entity
	 * </pre>
	 * 
	 * @return
	 */
	boolean containEntity();

	/**
	 * <pre>
	 * 是否可以编辑
	 * </pre>
	 * 
	 * @return
	 */
	boolean canEdit();

	/**
	 * <pre>
	 * 是否可以删除
	 * </pre>
	 * 
	 * @return
	 */
	boolean canDelete();

	/**
	 * <pre>
	 * 显示编辑器，用于编辑该函数的属性
	 * </pre>
	 * 
	 * @param windows
	 */
	void showEditor(Window windows);
	
	/**
	 * <pre>
	 * 重置
	 * </pre>
	 */
	void reset();
}
