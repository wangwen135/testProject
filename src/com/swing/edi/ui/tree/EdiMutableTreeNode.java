package com.swing.edi.ui.tree;

import javax.swing.tree.DefaultMutableTreeNode;

import com.swing.edi.reportModel.area.cell.entity.IEntity;
import com.swing.edi.reportModel.area.cell.function.IFunction;

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
		//super(e);
		this.entity = e;
		this.isEntity = true;
	}

	public EdiMutableTreeNode(IFunction func) {
		super(func.getViewStr());
		//super(func);
		this.function = func;
		this.isEntity = false;
	}

}
