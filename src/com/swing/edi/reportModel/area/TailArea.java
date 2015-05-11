package com.swing.edi.reportModel.area;

import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import com.swing.edi.reportModel.area.cell.ICell;
import com.swing.edi.reportModel.utils.ReportModelUtils;
import com.swing.edi.ui.table.EdiTableModel;

/**
 * 描述：表尾区域
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
public class TailArea implements IArea {

	private static final long serialVersionUID = -5866634503621949263L;

	// private boolean showInAnyFiles = true;

	private EdiTableModel tm;

	private int rowCount;
	private int columnCount;

	public TailArea() {
	}

	public TailArea(EdiTableModel model) {
		if (model == null) {
			throw new NullPointerException("模型不能为空！");
		}
		this.tm = model;
		// this.showInAnyFiles = show;
		this.rowCount = model.getRowCount();
		this.columnCount = model.getColumnCount();
	}

	// public boolean isShowInAnyFiles() {
	// return showInAnyFiles;
	// }
	//
	// public void setShowInAnyFiles(boolean showInAnyFiles) {
	// this.showInAnyFiles = showInAnyFiles;
	// }

	@Override
	public String toXml() {
		StringBuilder sb = new StringBuilder();
		sb.append("<tail rowCount=\"");
		sb.append(this.rowCount);
		sb.append("\" columnCount=\"");
		sb.append(this.columnCount);
		// sb.append("\" showInAnyFiles=\"");
		// sb.append(isShowInAnyFiles());
		sb.append("\">");

		for (int i = 0; i < tm.getRowCount(); i++) {
			for (int j = 0; j < tm.getColumnCount(); j++) {
				ICell cell = (ICell) tm.getValueAt(i, j);
				if (cell != null) {
					sb.append(cell.toXml());
				}
			}
		}
		sb.append("</tail>");

		return sb.toString();
	}

	@Override
	public void fromXml(Element xml) {
		// String s = xml.attributeValue("showInAnyFiles");
		// setShowInAnyFiles(Boolean.valueOf(s));

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
