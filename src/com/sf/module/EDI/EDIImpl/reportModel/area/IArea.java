package com.sf.module.EDI.EDIImpl.reportModel.area;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import com.sf.module.EDI.EDIImpl.reportModel.IReportModel;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.ICell;
import com.sf.module.EDI.EDIImpl.ui.table.EdiTableModel;

/**
 * 描述：区域接口<br>
 * 不同的区域实现公共的接口，然后为定义私有的属性提供get和set
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-2-3      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public interface IArea extends Serializable {

	/**
	 * <pre>
	 * 转换成xml
	 * </pre>
	 * 
	 * @return
	 */
	String toXml();

	/**
	 * <pre>
	 * 从XML反构建对象
	 * </pre>
	 * 
	 * @param xml
	 */
	void fromXml(Element xml);

	/**
	 * <pre>
	 * 设置一行数据
	 * </pre>
	 * 
	 * @param row
	 */
	void setValue(Map<String, Object> row);

	/**
	 * <pre>
	 * 取计算之后的值
	 * </pre>
	 * 
	 * @return {{一行},{二行}...}
	 */
	List<List<Object>> getValue();

	/**
	 * <pre>
	 * 获得一个tableModel
	 * </pre>
	 * 
	 * @return
	 */
	EdiTableModel getTableModel();

	/**
	 * <pre>
	 * 获取该区域的行数
	 * </pre>
	 * 
	 * @return
	 */
	int getRowCount();

	/**
	 * <pre>
	 * 获取该区域的列数
	 * </pre>
	 * 
	 * @return
	 */
	int getColumnCount();

	/**
	 * <pre>
	 * 获取指定单元格的值
	 * </pre>
	 * 
	 * @param row
	 *            行
	 * @param column
	 *            列
	 * @return
	 */
	Object getValueAt(int row, int column);

	/**
	 * <pre>
	 * 获取指定单元格
	 * </pre>
	 * 
	 * @param row
	 *            行
	 * @param column
	 *            列
	 * @return
	 */
	ICell getCellAt(int row, int column);

	/**
	 * <pre>
	 * 获取区域所属报表模型
	 * </pre>
	 * 
	 * @return
	 */
	IReportModel getReportModel();

	/**
	 * <pre>
	 * 设置区域所属报表模型
	 * </pre>
	 * 
	 * @param reportModel
	 *            报表模型
	 */
	void setReportModel(IReportModel reportModel);

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
