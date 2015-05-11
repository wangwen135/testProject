package com.sf.module.EDI.EDIImpl.reportModel.area.cell;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import com.sf.module.EDI.EDIImpl.reportModel.area.IArea;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity.IEntity;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.IFormat;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.function.IFunction;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.part.IPart;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.part.PartEnum;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.property.CellProperty;

/**
 * 描述：单元格接口，指定单元格的一系列方法
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-1-31      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public interface ICell extends Serializable {

	/**
	 * 为空时用什么填充
	 */
	public final static String NULL_VALUE = "";

	/**
	 * <pre>
	 * 设置属性
	 * </pre>
	 * 
	 * @param property
	 */
	void setProperty(CellProperty property);

	/**
	 * <pre>
	 * 获取属性
	 * </pre>
	 * 
	 * @return
	 */
	CellProperty getProperty();

	/**
	 * <pre>
	 * 设置格式
	 * </pre>
	 * 
	 * @param format
	 */
	void setFormat(IFormat format);

	/**
	 * <pre>
	 * 获取格式
	 * </pre>
	 * 
	 * @return
	 */
	IFormat getFormat();

	/**
	 * <pre>
	 * 格式化化函数的结果
	 * 如果格式化失败，则返回result.toString
	 * 如果result==null，返回NULL_VALUE
	 * </pre>
	 * 
	 * @return 返回格式化之后的字符串
	 */
	String getFormatResult();

	/**
	 * <pre>
	 * 获取函数运算结果，如果没有函数返回getValue()
	 * </pre>
	 * 
	 * @return
	 */
	Object getResult();

	/**
	 * <pre>
	 * 获取值Entity列表计算之后的值
	 * </pre>
	 * 
	 * @return
	 */
	Object getValue();

	/**
	 * <pre>
	 * 获取显示的字符串
	 * </pre>
	 * 
	 * @return
	 */
	String getViewStr();

	String toString();

	/**
	 * <pre>
	 * 返回entity序列
	 * </pre>
	 * 
	 * @return
	 */
	List<IEntity> getEntityList();

	/**
	 * <pre>
	 * 返回part序列
	 * </pre>
	 * 
	 * @return
	 */
	List<IPart> getPartList();

	/**
	 * <pre>
	 * 返回key数组
	 * </pre>
	 * 
	 * @return
	 */
	String[] getEntityKeys();

	/**
	 * <pre>
	 * 返回entity数组
	 * </pre>
	 * 
	 * @return
	 */
	IEntity[] getEntitys();

	/**
	 * <pre>
	 * 返回function序列
	 * </pre>
	 * 
	 * @return
	 */
	List<IFunction> getFunctionList();

	/**
	 * <pre>
	 * 返回函数数组
	 * </pre>
	 * 
	 * @return
	 */
	IFunction[] getFunctions();

	/**
	 * <pre>
	 * 查找指定key的entity
	 * </pre>
	 * 
	 * @param key
	 * @return
	 */
	IEntity findEntityByKey(String key);

	/**
	 * <pre>
	 * 获取指定位置的entity
	 * </pre>
	 * 
	 * @param index
	 * @return
	 */
	IEntity getEntity(int index);

	/**
	 * <pre>
	 * 获取指定位置的Function
	 * </pre>
	 * 
	 * @param index
	 * @return
	 */
	IFunction getFunction(int index);

	/**
	 * <pre>
	 * 获取指定位置的part
	 * </pre>
	 * 
	 * @param index
	 * @return
	 */
	IPart getPart(int index);

	/**
	 * <pre>
	 * 根据part枚举查找Cell中的part
	 * </pre>
	 * 
	 * @param part
	 *            part枚举
	 * @return 如果有则返回part，否则返回null
	 */
	IPart findPart(PartEnum part);

	/**
	 * <pre>
	 * 为指定key的entity设置值
	 * </pre>
	 * 
	 * @param key
	 *            指定的entity key
	 * @param value
	 * @deprecated 不建议使用的，可能不会触发cell中函数的setValue方法
	 */
	void setValue(String key, Object value);

	/**
	 * <pre>
	 * 给指定位置的entity设置值
	 * </pre>
	 * 
	 * @param index
	 *            位置不能小于0，并且不能大于entitySize
	 * @param value
	 * @deprecated 不建议使用的，可能不会触发cell中函数的setValue方法
	 */
	void setValue(int index, Object value);

	/**
	 * <pre>
	 * 为Cell中所有的Entity设置值，根据key关联<br>
	 * 设置完成之后需要触发cell内函数的setValue方法
	 * </pre>
	 * 
	 * @param data
	 */
	void setValue(Map<String, Object> data);

	/**
	 * <pre>
	 * 将对象转换成xml
	 * </pre>
	 * 
	 * @return
	 */
	String toXml();

	/**
	 * <pre>
	 * 从xml转换成对象
	 * </pre>
	 */
	void fromXml(Element xml);

	/**
	 * <pre>
	 * 如果该cel中不包涵entity则返回true
	 * </pre>
	 * 
	 * @return
	 */
	boolean isEmpty();

	/**
	 * <pre>
	 * 获取cell中entity的数量
	 * </pre>
	 * 
	 * @return
	 */
	int getEntitySize();

	/**
	 * <pre>
	 * 获取cell中function的数量
	 * </pre>
	 * 
	 * @return
	 */
	int getFunctionSize();

	/**
	 * <pre>
	 * 添加一个entity到末尾
	 * </pre>
	 * 
	 * @param e
	 * @return
	 */
	boolean addEntity(IEntity e);

	/**
	 * <pre>
	 * 添加一个function到函数列表中
	 * </pre>
	 * 
	 * @param f
	 * @return
	 */
	boolean addFunction(IFunction f);

	/**
	 * <pre>
	 * 添加一个part到末尾
	 * </pre>
	 * 
	 * @param p
	 * @return
	 */
	boolean addPart(IPart p);

	/**
	 * <pre>
	 * 添加一个entity到末尾，并自动添加一个part
	 * </pre>
	 * 
	 * @param e
	 * @return
	 */
	boolean addEntityAndPart(IEntity e);

	/**
	 * <pre>
	 * 在指定的位置插入entity，并自动插入part
	 * </pre>
	 * 
	 * @param index
	 *            位置不能小于0，并且不能大于entitySize
	 * @param e
	 * @return
	 */
	boolean addEntityAndPart(int index, IEntity e);

	/**
	 * <pre>
	 * 在指定的位置插入function
	 * </pre>
	 * 
	 * @param index
	 *            位置不能小于0，并且不能大于functionSize
	 * @param f
	 *            要插入的函数
	 * @return
	 */
	boolean addFunction(int index, IFunction f);

	/**
	 * <pre>
	 * 用指定entity替换列表中指定位置的entity
	 * </pre>
	 * 
	 * @param index
	 *            位置不能小于0，并且不能大于等于entitySize
	 * @param e
	 *            要保存的entity
	 * @return 以前在该位置的entity
	 */
	IEntity setEntity(int index, IEntity e);

	/**
	 * <pre>
	 * 用指定function替换列表中指定位置的function
	 * </pre>
	 * 
	 * @param index
	 *            位置不能小于0，并且不能大于等于functionSize
	 * @param func
	 *            要保持的function
	 * @return 以前在该位置的function
	 */
	IFunction setFunction(int index, IFunction func);

	/**
	 * <pre>
	 * 用指定part替换列表中指定位置的part
	 * </pre>
	 * 
	 * @param index
	 *            位置不能小于0，并且不能大于等于entitySize
	 * @param p
	 *            要保存的part
	 * @return 以前在该位置的part
	 */
	IPart setPart(int index, IPart p);

	/**
	 * <pre>
	 * 移除首个出现的entity，同时移除关联的part
	 * </pre>
	 * 
	 * @param e
	 * @return
	 */
	boolean removeEntityAndPart(IEntity e);

	/**
	 * <pre>
	 * 移除首个出现的function
	 * </pre>
	 * 
	 * @param f
	 * @return
	 */
	boolean removeFunction(IFunction f);

	/**
	 * <pre>
	 * 移除指定位置的entity，同时移除关联的part
	 * </pre>
	 * 
	 * @param index
	 * @return
	 */
	IEntity removeEntityAndPart(int index);

	/**
	 * <pre>
	 * 移除指定位置的Function
	 * </pre>
	 * 
	 * @param index
	 * @return
	 */
	IFunction removeFunction(int index);

	/**
	 * <pre>
	 * 清空该单元格中的内容
	 * entity列表和part列表
	 * </pre>
	 */
	void clear();

	/**
	 * <pre>
	 * 清空该单元格中的函数
	 * </pre>
	 */
	void clearFunction();

	/**
	 * <pre>
	 * 获取该单元格在报表中的列
	 * </pre>
	 * 
	 * @return
	 */
	int getColumn();

	/**
	 * <pre>
	 * 设置该单元格在报表中的列
	 * </pre>
	 * 
	 * @param column
	 */
	void setColumn(int column);

	/**
	 * <pre>
	 * 获取该单元格在报表中的行
	 * </pre>
	 * 
	 * @return
	 */
	int getRow();

	/**
	 * <pre>
	 * 设置该单元格在报表中的行
	 * </pre>
	 * 
	 * @param row
	 */
	void setRow(int row);

	/**
	 * <pre>
	 * 是否可以包含entity
	 * 一般由function列表决定
	 * </pre>
	 * 
	 * @return
	 */
	boolean containEntity();

	/**
	 * <pre>
	 * 获取Cell所属区域
	 * </pre>
	 * 
	 * @return
	 */
	IArea getArea();

	/**
	 * <pre>
	 * 设置Cell所属区域
	 * </pre>
	 * 
	 * @param area
	 *            区域对象
	 */
	void setArea(IArea area);

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
	 * 重构下级上下文
	 * </pre>
	 */
	void rebuildChildContext();

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
