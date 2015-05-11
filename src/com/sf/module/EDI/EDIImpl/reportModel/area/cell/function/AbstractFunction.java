package com.sf.module.EDI.EDIImpl.reportModel.area.cell.function;

import java.util.Map;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.ICell;

/**
 * 描述：函数抽象类
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-12-5      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public abstract class AbstractFunction implements IFunction {

	private static final long serialVersionUID = 6953972398024598772L;

	/**
	 * 函数所属单元格
	 */
	private transient ICell cell;

	@Override
	public IFunction aClone() {
		try {
			return (IFunction) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	@Override
	public ICell getCell() {
		return cell;
	}

	@Override
	public void setCell(ICell cell) {
		this.cell = cell;
	}

	@Override
	public Map<String, Object> getGlobalContext() {
		if (getCell() == null)
			return null;
		return getCell().getGlobalContext();
	}

	@Override
	public Object getContext(String key) {
		return getGlobalContext().get(key);
	}

	@Override
	public void putContext(String key, Object value) {
		getGlobalContext().put(key, value);
	}
}
