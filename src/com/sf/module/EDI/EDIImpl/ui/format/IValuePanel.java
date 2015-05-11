package com.sf.module.EDI.EDIImpl.ui.format;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.value.IValueFormat;

/**
 * 描述：格式编辑器中值格式子项接口
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-4-17      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public interface IValuePanel {
	/**
	 * <pre>
	 * 设置值到控件
	 * </pre>
	 * 
	 * @param valueFormat
	 */
	public void setValueFormat(IValueFormat valueFormat);

	/**
	 * <pre>
	 * 获取值，每次new一个对象
	 * </pre>
	 * 
	 * @return
	 */
	public IValueFormat getValueFormat();

	/**
	 * <pre>
	 * 重置
	 * </pre>
	 */
	public void reset();

	/**
	 * <pre>
	 * 是不是默认值
	 * </pre>
	 * 
	 * @return
	 */
	public boolean isDefaultValue();

}
