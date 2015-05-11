package com.sf.module.EDI.EDIImpl.reportModel.area;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.ICell;
import com.sf.module.EDI.EDIImpl.ui.table.EdiTableModel;

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
public class TitleArea extends AbstractArea {

	private static final long serialVersionUID = -2266024858046652349L;

	/**
	 * 报表名称
	 */
	private String reportName;

	/**
	 * 文件行数限制，0或-1 表示无限制
	 */
	private int fileRowLimit = -1;

	/**
	 * 默认宽度
	 */
	public static final int DEFAULT_WIDTH = 96;

	/**
	 * 默认高度
	 */
	public static final int DEFAULT_HEIGHT = 75;

	/**
	 * 表格宽度
	 */
	private List<Integer> widthList = new ArrayList<Integer>();

	/**
	 * <pre>
	 * 取指定的列的宽度
	 * </pre>
	 * 
	 * @param index
	 * @return
	 */
	public int getWidth(int index) {
		if (index >= widthList.size()) {
			return DEFAULT_WIDTH;
		}
		return widthList.get(index);

	}

	/**
	 * <pre>
	 * 设置指定的列的宽度
	 * </pre>
	 * 
	 * @param index
	 * @param width
	 */
	public void setWidth(int index, int width) {
		widthList.set(index, width);
	}

	public List<Integer> getWidthList() {
		return widthList;
	}

	public void setWidthList(List<Integer> widthList) {
		this.widthList = widthList;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public int getFileRowLimit() {
		return fileRowLimit;
	}

	public void setFileRowLimit(int fileRowLimit) {
		this.fileRowLimit = fileRowLimit;
	}

	public TitleArea() {
	}

	public TitleArea(String name) {
		this.reportName = name;
	}

	@Override
	public String toXml() {
		StringBuilder sb = new StringBuilder();
		sb.append("<title>");

		sb.append("<reportName>");
		sb.append(getReportName());
		sb.append("</reportName>");

		sb.append("<fileRowLimit>");
		sb.append(getFileRowLimit());
		sb.append("</fileRowLimit>");

		// 宽度列表
		sb.append("<widthList>");
		for (int i = 0; i < widthList.size(); i++) {
			if (i != 0) {
				sb.append(";");
			}
			sb.append(widthList.get(i));
		}
		sb.append("</widthList>");

		sb.append("</title>");
		return sb.toString();
	}

	@Override
	public void fromXml(Element xml) {
		setReportName(xml.element("reportName").getText());
		Element element = xml.element("fileRowLimit");
		if (element != null) {
			String rowlimit = element.getText();
			if (rowlimit != null && !"".equals(rowlimit)) {
				setFileRowLimit(Integer.valueOf(rowlimit));
			}
		}
		// 宽度列表
		element = xml.element("widthList");
		if (element != null) {
			String widthListStr = element.getText();
			if (widthListStr != null && !"".equals(widthListStr)) {
				String[] widthListArray = widthListStr.split(";");
				for (String wstr : widthListArray) {
					widthList.add(Integer.valueOf(wstr));
				}
			}
		}
	}

	@Override
	public void setValue(Map<String, Object> row) {

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

	@Override
	public void rebuildChildContext() {
		// 空实现
	}

}
