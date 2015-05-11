package com.sf.module.EDI.EDIImpl.reportModel;

import java.io.Serializable;
import java.util.Map;

import com.sf.module.EDI.EDIImpl.reportModel.area.BodyArea;
import com.sf.module.EDI.EDIImpl.reportModel.area.HeadArea;
import com.sf.module.EDI.EDIImpl.reportModel.area.TailArea;
import com.sf.module.EDI.EDIImpl.reportModel.area.TitleArea;

/**
 * 描述：报表模型<br>
 * 模型只是模型，与数据无关<br>
 * 提供从模型到XML，从XML反构建模型的方法<br>
 * 从UI到模型 从模型到UI需要依赖不同的area
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-2-3      313921          Create
 *  2    2012-12-3     313921          增加一个全局上下文
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public interface IReportModel extends Serializable {

	/**
	 * 全局上下文标记
	 */
	public final static String GLOBAL_CONTEXT = "GLOBAL_CONTEXT";

	/**
	 * <pre>
	 * 转换成XML
	 * </pre>
	 * 
	 * @return
	 */
	String toXml();

	/**
	 * <pre>
	 * 从XML反构建模型对象
	 * </pre>
	 * 
	 * @param xml
	 * @throws Exception
	 */
	void fromXml(String xml) throws Exception;

	/**
	 * <pre>
	 * 获取最大列数
	 * </pre>
	 * 
	 * @return
	 */
	int getMaxColumnCount();

	/**
	 * <pre>
	 * 获取标题区域
	 * </pre>
	 * 
	 * @return
	 */
	TitleArea getTitle();

	/**
	 * <pre>
	 * 获取头部
	 * </pre>
	 * 
	 * @return
	 */
	HeadArea getHead();

	/**
	 * <pre>
	 * 获取表体
	 * </pre>
	 * 
	 * @return
	 */
	BodyArea getBody();

	/**
	 * <pre>
	 * 获取表尾
	 * </pre>
	 * 
	 * @return
	 */
	TailArea getTail();

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
