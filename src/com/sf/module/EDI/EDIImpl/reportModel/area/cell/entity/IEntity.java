package com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity;

import java.awt.Color;
import java.awt.Window;
import java.io.Serializable;
import java.util.Map;

import org.dom4j.Element;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.ICell;

/**
 * 描述：Entity 接口,设计中的最小单元<br>
 * 使用装饰模式进行层叠包装，具体的包装操作放到不同的实现中，可以相互包装<br>
 * 
 * <pre>
 * 2012-08-16 -- 由于未知的原因，没有使用装饰模式
 * Entiry 应该知道自己是否可以编辑
 * 关联编辑器
 * 确定自己的颜色等
 * </pre>
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-1-17      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public interface IEntity extends Serializable {
	/**
	 * XML节点名称
	 */
	public static final String ELEMENT_NAME = "entity";

	/**
	 * EDI常量Key
	 */
	public static final String KEY_EDI_CONSTANT = "EDI_CONSTANT";

	/**
	 * 所有字段，即该Entity需要整条记录中所有的字段
	 */
	public static final String KEY_ALL_FIELDS = "ALL_FIELDS";

	/**
	 * 不需要设置字段，即此类型的Entity不需要设置字段
	 */
	public static final String KEY_UNNEEDED_FIELD = "KEY_UNNEEDED_FIELD";

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

	/**
	 * <pre>
	 * 设置值
	 * </pre>
	 * 
	 * @param obj
	 */
	public void setValue(Object obj);

	/**
	 * <pre>
	 * 获取描述字符串
	 * </pre>
	 * 
	 * @return
	 */
	public String getViewStr();

	/**
	 * <pre>
	 * 获取值
	 * </pre>
	 * 
	 * @return
	 */
	public Object getValue();

	/**
	 * <pre>
	 * 获取key
	 * </pre>
	 * 
	 * @return
	 */
	public String getKey();

	/**
	 * <pre>
	 * 获取类型，不同的实现应该返回不同的类型
	 * </pre>
	 * 
	 * @return
	 */
	public String getType();

	/**
	 * <pre>
	 * 是否能编辑
	 * </pre>
	 * 
	 * @return
	 */
	public boolean canEdit();

	/**
	 * <pre>
	 * 编辑，如果修改了需要返回一个新的对象
	 * </pre>
	 * 
	 * @return 返回修改后的对象
	 */
	public IEntity edit(Window window);

	/**
	 * <pre>
	 * 获取颜色
	 * </pre>
	 * 
	 * @return
	 */
	public Color getColor();

	// 2012-12-06 新增
	/**
	 * <pre>
	 * 获取Entity所属单元格
	 * </pre>
	 * 
	 * @return
	 */
	ICell getCell();

	/**
	 * <pre>
	 * 设置Entity所属单元格
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
