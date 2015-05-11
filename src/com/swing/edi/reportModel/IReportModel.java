package com.swing.edi.reportModel;

import com.swing.edi.reportModel.area.BodyArea;
import com.swing.edi.reportModel.area.HeadArea;
import com.swing.edi.reportModel.area.TailArea;
import com.swing.edi.reportModel.area.TitleArea;

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
 *  1    2012-2-3      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public interface IReportModel {
	// 暂时是所有类型的报表都使用统一的模板

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

	TitleArea getTitle();

	HeadArea getHead();

	BodyArea getBody();

	TailArea getTail();

}
