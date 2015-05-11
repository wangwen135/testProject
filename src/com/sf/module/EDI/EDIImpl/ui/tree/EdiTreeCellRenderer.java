package com.sf.module.EDI.EDIImpl.ui.tree;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity.IEntity;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.function.IFunction;

/**
 * 描述：EDI模板编辑器树渲染器<br>
 * 颜色渲染
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-2-14      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class EdiTreeCellRenderer extends DefaultTreeCellRenderer {

	private static final long serialVersionUID = -8493876787623819745L;

	public EdiTreeCellRenderer() {
		super();
		setBackgroundSelectionColor(new Color(184, 207, 229));// 淡蓝色
	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		JLabel l = (JLabel) super.getTreeCellRendererComponent(tree, value,
				sel, expanded, leaf, row, hasFocus);

		// if (value == null) {
		// // do nothing
		// } else 
		if (value instanceof EdiMutableTreeNode) {
			EdiMutableTreeNode node = (EdiMutableTreeNode) value;
			if (node.isEntity()) {
				IEntity e = node.getEntity();
				l.setText(e.getViewStr());
				if (e.getColor() != null) {
					l.setForeground(e.getColor());
				} else {
					l.setForeground(Color.BLACK);
				}
			} else {
				IFunction f = node.getFunction();
				l.setText(f.getViewStr() + " " + f.getType() + "( )");
				l.setForeground(Color.RED);
			}
		} else {
			// 字符
			l.setForeground(Color.BLACK);
		}
		return l;
	}
}
