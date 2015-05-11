package com.swing.edi.reportModel.area;

import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import com.swing.edi.reportModel.area.cell.ICell;
import com.swing.edi.ui.table.EdiTableModel;

/**
 * 描述：标题区域，用于保存一些其他的属性
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
public class TitleArea implements IArea {

	private static final long serialVersionUID = -2266024858046652349L;

	// 可以不需要的属性，应该独立的保存在数据库字段中，暂时先保留
	private String reportName;

	public TitleArea() {
	}

	public TitleArea(String name) {
		this.reportName = name;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	@Override
	public String toXml() {
		StringBuilder sb = new StringBuilder();
		sb.append("<title>");

		sb.append("<reportName>");
		sb.append(getReportName());
		sb.append("</reportName>");

		sb.append("</title>");
		return sb.toString();
	}

	@Override
	public void fromXml(Element xml) {
		setReportName(xml.element("reportName").getText());

	}

	@Override
	public void setValue(Map<String, String> row) {

	}

	@Override
	public List<List<Object>> getValue() {
		return null;
	}

	@Override
	public EdiTableModel getTableModel() {
		return null;
	}

	@Override
	public int getRowCount() {
		return 0;
	}

	@Override
	public int getColumnCount() {
		return 0;
	}

	@Override
	public Object getValueAt(int row, int column) {
		return null;
	}

	@Override
	public ICell getCellAt(int row, int column) {
		return null;
	}

}
