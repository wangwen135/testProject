package com.sf.module.EDI.EDIImpl.reportModel.area;

import java.util.Map;

import com.sf.module.EDI.EDIImpl.reportModel.IReportModel;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.ICell;
import com.sf.module.EDI.EDIImpl.ui.table.EdiTableModel;

/**
 * 描述：区域接口抽象类
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-12-3      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public abstract class AbstractArea implements IArea {

	private static final long serialVersionUID = -8565498126692294595L;

	/**
	 * 报表模型
	 */
	private transient IReportModel reportModel;

	@Override
	public void setReportModel(IReportModel reportModel) {
		this.reportModel = reportModel;
	}

	@Override
	public IReportModel getReportModel() {
		return reportModel;
	}

	@Override
	public Map<String, Object> getGlobalContext() {
		return reportModel.getGlobalContext();
	}

	@Override
	public Object getContext(String key) {
		return getGlobalContext().get(key);
	}

	@Override
	public void putContext(String key, Object value) {
		getGlobalContext().put(key, value);
	}

	/**
	 * <pre>
	 * 设置模型下级上下文
	 * </pre>
	 * 
	 * @param tm
	 */
	public void setModelChildContext(EdiTableModel tm) {
		int row = tm.getRowCount();
		int col = tm.getColumnCount();

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				ICell cell = getCellAt(i, j);
				if (cell != null) {
					cell.setArea(this);
					cell.rebuildChildContext();
				}
			}
		}
	}
}
