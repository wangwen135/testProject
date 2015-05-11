package com.swing.edi.ui.table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Window;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import com.swing.edi.reportModel.area.cell.ICell;
import com.swing.edi.reportModel.area.cell.function.IFunction;

/**
 * 描述：表格鼠标监听
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
public class TableMouseListener extends MouseAdapter {
	private JTable table;
	private boolean addRow = true;
	private JPopupMenu pop;
	private JMenuItem item_edit;
	private JMenuItem item_del;
	private JMenuItem item_editFunc;
	private JMenuItem item_delFunc;
	private Window window;

	public TableMouseListener(Window window, JTable table) {
		this(window, table, true);
	}

	public TableMouseListener(Window win, JTable arg1, boolean arg2) {
		this.table = arg1;
		this.addRow = arg2;
		this.window = win;

		pop = new JPopupMenu();
		item_edit = new JMenuItem("编辑");
		item_edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int column = table.getSelectedColumn();
				int row = table.getSelectedRow();
				if (column != -1 && row != -1) {
					table.editCellAt(row, column);
				}
			}
		});
		pop.add(item_edit);
		item_del = new JMenuItem("删除");
		item_del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int column = table.getSelectedColumn();
				int row = table.getSelectedRow();
				if (column != -1 && row != -1) {
					table.setValueAt(null, row, column);
				}
			}
		});
		pop.add(item_del);
		// 分割线
		pop.addSeparator();
		item_editFunc = new JMenuItem("编辑函数");
		item_editFunc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int column = table.getSelectedColumn();
				int row = table.getSelectedRow();
				if (column != -1 && row != -1) {

					ICell cell = (ICell) table.getValueAt(row, column);
					IFunction func = cell.getFunction();
					if (func != null) {
						func.showEditor(window);
					}
				}
			}
		});
		pop.add(item_editFunc);

		item_delFunc = new JMenuItem("删除函数");
		item_delFunc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int column = table.getSelectedColumn();
				int row = table.getSelectedRow();
				if (column != -1 && row != -1) {
					ICell cell = (ICell) table.getValueAt(row, column);
					cell.setFunction(null);
				}
			}
		});
		pop.add(item_delFunc);
		// 分割线
		pop.addSeparator();

		JMenuItem itemAddRow = new JMenuItem("增加行");
		itemAddRow.setEnabled(addRow);
		itemAddRow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				TableUtils.addRow(table);
			}
		});
		pop.add(itemAddRow);
		// 删除行
		JMenuItem itemDelRow = new JMenuItem("删除行");
		itemDelRow.setEnabled(addRow);
		itemDelRow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TableUtils.delRow(table);
			}
		});
		pop.add(itemDelRow);
		// 分割线
		pop.addSeparator();

		// 增加列
		JMenuItem itemAddColumn = new JMenuItem("增加列");
		itemAddColumn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TableUtils.addColumn(table);
			}
		});
		pop.add(itemAddColumn);

		// 删除列
		JMenuItem itemDelColumn = new JMenuItem("删除列");
		itemDelColumn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TableUtils.delColumnOnCurrent(table);
			}
		});
		pop.add(itemDelColumn);
		// 在前面插入列
		JMenuItem itemAddColumnOnCurrent = new JMenuItem("在之前插入列");
		itemAddColumnOnCurrent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TableUtils.addColumnOnCurrent(table);
			}
		});
		pop.add(itemAddColumnOnCurrent);
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
			// 判断哪些选项是不可用的
			if (column != -1 && row != -1) {
				ICell cell = (ICell) table.getValueAt(row, column);
				if (cell == null) {
					item_edit.setEnabled(false);
					item_del.setEnabled(false);
					item_editFunc.setEnabled(false);
					item_delFunc.setEnabled(false);

				} else {
					// 只有cell不为空就可用删除
					item_del.setEnabled(true);

					if (cell.getEntitySize() > 0) {
						item_edit.setEnabled(true);
					} else {
						item_edit.setEnabled(false);
					}
					IFunction func = cell.getFunction();
					if (func != null) {
						// 函数不为空就可以删除
						item_delFunc.setEnabled(true);
						if (func.canEdit()) {
							item_editFunc.setEnabled(true);
						} else {
							item_editFunc.setEnabled(false);
						}
					} else {
						item_delFunc.setEnabled(false);
						item_editFunc.setEnabled(false);
					}
				}
			}
			pop.show(e.getComponent(), e.getX(), e.getY());
		}
	}

}