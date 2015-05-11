package com.sf.module.EDI.EDIImpl.reportModel.area.cell.function;

import java.awt.Window;
import java.io.Serializable;
import java.util.Map;

import org.dom4j.Element;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.ICell;

/**
 * 描述：函数接口<br>
 * 
 * <pre>
 * 本接口继承了Cloneable接口，再后面需要用到clone功能。
 * IFunction实现应该尽量只使用简单属性，
 * 如果对象的属性类型是List，Map，或者用户自定义的其他类时，需要进行深克隆
 * </pre>
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
public interface IFunction extends Serializable, Cloneable {
	/**
	 * XML节点名称
	 */
	public static final String ELEMENT_NAME = "function";

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
	 * 获取参数描述串
	 * </pre>
	 * 
	 * @return
	 */
	String getParameterViewStr();

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

	/**
	 * <pre>
	 * 克隆
	 * try { 
	 *     return (IFunction)super.clone(); 
	 * } catch (CloneNotSupportedException e) { 
	 *     return null; 
	 * }
	 * </pre>
	 * 
	 * @return
	 */
	IFunction aClone();

	/**
	 * <pre>
	 * 获取函数所属单元格
	 * </pre>
	 * 
	 * @return
	 */
	ICell getCell();

	/**
	 * <pre>
	 * 设置函数所属单元格
	 * </pre>
	 * 
	 * @param cell
	 */
	void setCell(ICell cell);

	/**
	 * <pre>
	 * 获取全局上下文
	 * </pre>
	 * 
	 * @return
	 */
	Map<String, Object> getGlobalContext();

	/**
	 * <pre>
	 * 获取上下文值
	 * </pre>
	 * 
	 * @param key
	 *            键
	 * @return 值
	 */
	Object getContext(String key);

	/**
	 * <pre>
	 * 设置上下文值
	 * </pre>
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	void putContext(String key, Object value);

}
