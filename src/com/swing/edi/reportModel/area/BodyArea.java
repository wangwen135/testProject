package com.swing.edi.reportModel.area;

import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import com.swing.edi.reportModel.area.cell.ICell;
import com.swing.edi.reportModel.utils.ReportModelUtils;
import com.swing.edi.ui.table.EdiTableModel;

/**
 * 描述：表体区域
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
public class BodyArea implements IArea {

	private static final long serialVersionUID = -7283961377477161213L;

	// 定义一些属性

	private EdiTableModel tm;
//	private int rowLimit = -1;

	private int rowCount;
	private int columnCount;

	public BodyArea() {
	}

	public BodyArea(EdiTableModel model) {
		if (model == null) {
			throw new NullPointerException("模型不能为空！");
		}
//		if (limit <= 0 && limit != -1) {
//			throw new IllegalArgumentException("rowLimit必须大于0或等于-1");
//		}
		this.tm = model;
//		this.rowLimit = limit;
		this.rowCount = model.getRowCount();
		this.columnCount = model.getColumnCount();
	}

//	public int getRowLimit() {
//		return rowLimit;
//	}
//
//	public void setRowLimit(int rowLimit) {
//		this.rowLimit = rowLimit;
//	}

	@Override
	public String toXml() {
		StringBuilder sb = new StringBuilder();
		sb.append("<body rowCount=\"");
		sb.append(this.rowCount);
		sb.append("\" columnCount=\"");
		sb.append(this.columnCount);
//		sb.append("\" rowLimit=\"");
//		sb.append(getRowLimit());
		sb.append("\">");

		for (int i = 0; i < tm.getRowCount(); i++) {
			for (int j = 0; j < tm.getColumnCount(); j++) {
				ICell cell = (ICell) tm.getValueAt(i, j);
				if (cell != null) {
					sb.append(cell.toXml());
				}
			}
		}
		sb.append("</body>");

		return sb.toString();
	}

	@Override
	public void fromXml(Element xml) {
		// String s = xml.attributeValue("rowLimit");
		// setRowLimit(Integer.valueOf(s));
		this.tm = ReportModelUtils.getTableModelFromXml(xml);
		this.rowCount = tm.getRowCount();
		this.columnCount = tm.getColumnCount();
	}

	@Override
	public void setValue(Map<String, String> row) {
		ReportModelUtils.setAreaValue(tm, row);
	}

	@Override
	public List<List<Object>> getValue() {
		return ReportModelUtils.getAreaValue(tm);
	}

	@Override
	public EdiTableModel getTableModel() {
		return tm;
	}

	@Override
	public int getRowCount() {
		return this.rowCount;
	}

	@Override
	public int getColumnCount() {
		return this.columnCount;
	}
	@Override
	public Object getValueAt(int row, int column) {
		ICell cell = getCellAt(row, column);
		if (cell != null)
			return cell.getResult();
		return null;
	}

	@Override
	public ICell getCellAt(int row, int column) {
		return ReportModelUtils.getCellAt(tm, row, column);
	}
}
