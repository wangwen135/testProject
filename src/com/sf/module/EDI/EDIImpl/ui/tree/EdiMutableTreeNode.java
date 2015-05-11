package com.sf.module.EDI.EDIImpl.ui.tree;

import javax.swing.tree.DefaultMutableTreeNode;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity.IEntity;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.function.IFunction;

/**
 * 描述：EDI树节点
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-2-10      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class EdiMutableTreeNode extends DefaultMutableTreeNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8546080016584398037L;

	// 定义属性
	private IEntity entity;
	private boolean isEntity = false;
	private IFunction function;

	public boolean isEntity() {
		return isEntity;
	}

	public IFunction getFunction() {
		return function;
	}

	public void setFunction(IFunction function) {
		this.function = function;
	}

	public IEntity getEntity() {
		return entity;
	}

	public void setEntity(IEntity entity) {
		this.entity = entity;
	}

	public EdiMutableTreeNode(IEntity e) {
		super(e.getViewStr());
		this.entity = e;
		this.isEntity = true;
	}

	public EdiMutableTreeNode(IFunction func) {
		super(func.getViewStr());
		this.function = func;
		this.isEntity = false;
	}

	@Override
	public Object getUserObject() {
		if (entity != null) {
			return entity.getViewStr();
		}
		if (function != null) {
			return function.getViewStr();
		}
		return super.getUserObject();
	}

}
