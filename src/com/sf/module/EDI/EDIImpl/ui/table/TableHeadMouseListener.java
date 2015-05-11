package com.sf.module.EDI.EDIImpl.ui.table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import com.sf.module.EDI.EDIImpl.TempletDragEditor;

/**
 * 描述：表头鼠标监听器
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
public class TableHeadMouseListener extends MouseAdapter {
	private TempletDragEditor teditor;
	private EdiTable table;
	private JPopupMenu pop;

	public TableHeadMouseListener(TempletDragEditor editor, EdiTable arg) {
		this.teditor = editor;
		this.table = arg;

		pop = new JPopupMenu();
		JMenuItem item01 = new JMenuItem("删除列");
		item01.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TableUtils.delColumnOnCurrent(teditor, table);
			}
		});
		pop.add(item01);
		JMenuItem item02 = new JMenuItem("增加列");
		pop.add(item02);
		item02.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TableUtils.addColumn(teditor, table);
			}
		});
		JMenuItem item03 = new JMenuItem("在之前插入列");
		pop.add(item03);
		item03.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TableUtils.addColumnOnCurrent(teditor, table);
			}
		});
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1 && SwingUtilities.isRightMouseButton(e)) {
			int column = table.columnAtPoint(e.getPoint());
			int row = table.rowAtPoint(e.getPoint());
			if (column >= 0) {
				table.addColumnSelectionInterval(column, column);
			}
			if (row >= 0) {
				table.addRowSelectionInterval(row, row);
			}
			pop.show(e.getComponent(), e.getX(), e.getY());
		}
	}

}