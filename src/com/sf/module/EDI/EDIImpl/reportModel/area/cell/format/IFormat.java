package com.sf.module.EDI.EDIImpl.reportModel.area.cell.format;

import java.io.Serializable;

import org.dom4j.Element;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.align.AlignFormat;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.border.BorderFormat;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.color.ColorFormat;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.font.FontFormat;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.value.IValueFormat;

/**
 * 描述：单元格格式接口
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-4-14      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public interface IFormat extends Serializable {

	/**
	 * XML节点名称
	 */
	public static final String ELEMENT_NAME = "format";

	/**
	 * <pre>
	 * 设置颜色
	 * </pre>
	 * 
	 * @param color
	 */
	public void setColor(ColorFormat color);

	/**
	 * <pre>
	 * 获取颜色
	 * </pre>
	 * 
	 * @return
	 */
	public ColorFormat getColor();

	/**
	 * <pre>
	 * 设置边框
	 * </pre>
	 * 
	 * @param border
	 */
	public void setBorder(BorderFormat border);

	/**
	 * <pre>
	 * 获取边框
	 * </pre>
	 * 
	 * @return
	 */
	public BorderFormat getBorder();

	/**
	 * <pre>
	 * 设置字体
	 * </pre>
	 * 
	 * @param font
	 */
	public void setFont(FontFormat font);

	/**
	 * <pre>
	 * 获取字体
	 * </pre>
	 * 
	 * @return
	 */
	public FontFormat getFont();

	/**
	 * <pre>
	 * 设置对齐方式
	 * </pre>
	 * 
	 * @param align
	 */
	public void setAlign(AlignFormat align);

	/**
	 * <pre>
	 * 获取对齐方式
	 * </pre>
	 * 
	 * @return
	 */
	public AlignFormat getAlign();

	/**
	 * <pre>
	 * 设置格式器
	 * </pre>
	 * 
	 * @param format
	 */
	public void setFormat(IValueFormat format);

	/**
	 * <pre>
	 * 获取格式器
	 * </pre>
	 * 
	 * @return
	 */
	public IValueFormat getFormat();

	/**
	 * <pre>
	 * 格式化
	 * </pre>
	 * 
	 * @param obj
	 * @return
	 */
	public String format(Object obj);

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
