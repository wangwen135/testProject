package com.sf.module.EDI.EDIImpl.reportModel.area.cell;

import java.util.List;
import java.util.Map;

import com.sf.module.EDI.EDIImpl.reportModel.area.IArea;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity.IEntity;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.function.IFunction;

/**
 * 描述：cell抽象类<br>
 * 主要用于全局变量
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
public abstract class AbstractCell implements ICell {

	private static final long serialVersionUID = -8803194993694133686L;

	/**
	 * 单元格所属区域
	 */
	private transient IArea area;

	@Override
	public IArea getArea() {
		return this.area;
	}

	@Override
	public void setArea(IArea area) {
		this.area = area;
	}

	@Override
	public void rebuildChildContext() {
		List<IEntity> entityList = getEntityList();
		for (IEntity iEntity : entityList) {
			iEntity.setCell(this);
		}
		List<IFunction> functionList = getFunctionList();
		for (IFunction iFunction : functionList) {
			iFunction.setCell(this);
		}

	}

	@Override
	public Map<String, Object> getGlobalContext() {
		return area.getGlobalContext();
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
