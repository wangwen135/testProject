package com.swing.edi.reportModel.area.cell;

import java.io.Serializable;
import java.util.Map;

import org.dom4j.Element;

import com.swing.edi.reportModel.area.cell.entity.IEntity;
import com.swing.edi.reportModel.area.cell.function.IFunction;
import com.swing.edi.reportModel.area.cell.part.IPart;
import com.swing.edi.reportModel.area.cell.part.PartEnum;

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
	 * <pre>
	 * 设置函数
	 * </pre>
	 * 
	 * @param funct
	 */
	void setFunction(IFunction funct);

	/**
	 * <pre>
	 * 获取函数
	 * </pre>
	 * 
	 * @return
	 */
	IFunction getFunction();

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
	 * 获取值，报表时计算用
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
	// List<IEntity> getEntityList();//这个列表应该由实现的cell自己维护

	/**
	 * <pre>
	 * 返回part序列
	 * </pre>
	 * 
	 * @return
	 */
	// List<IPart> getPartList();//同上

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
	 */
	void setValue(String key, String value);

	/**
	 * <pre>
	 * 给指定位置的entity设置值
	 * </pre>
	 * 
	 * @param index
	 *            位置不能小于0，并且不能大于entitySize
	 * @param value
	 */
	void setValue(int index, String value);

	/**
	 * <pre>
	 * 为Cell中所有的Entity设置值，根据key关联
	 * </pre>
	 * 
	 * @param data
	 */
	void setValue(Map<String, String> data);

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
	 * 方法给cell中entity的数量
	 * </pre>
	 * 
	 * @return
	 */
	int getEntitySize();

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
	boolean remove(IEntity e);

	/**
	 * <pre>
	 * 移除指定位置的entity，同时移除关联的part
	 * </pre>
	 * 
	 * @param index
	 * @return
	 */
	IEntity remove(int index);

	/**
	 * <pre>
	 * 清空该单元格中的内容
	 * </pre>
	 */
	void clear();

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

}
