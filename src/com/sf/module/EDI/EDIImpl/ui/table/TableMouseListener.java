package com.sf.module.EDI.EDIImpl.ui.table;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import com.sf.module.EDI.EDIImpl.TempletDragEditor;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.Cell;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.ICell;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.IFormat;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.function.IFunction;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.property.CellProperty;
import com.sf.module.EDI.EDIImpl.reportModel.utils.ReportModelUtils;
import com.sf.module.EDI.EDIImpl.ui.format.FormatEditor;
import com.sf.module.EDI.EDIImpl.ui.property.PropertyEditor;

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
	private TempletDragEditor teditor;
	private EdiTable table;
	private boolean addRow = true;
	private JPopupMenu pop;
	private JMenuItem item_edit;
	private JMenuItem item_del;
	private JMenuItem item_delAll;
	private JMenuItem item_editFunc;
	private JMenuItem item_delFunc;
	private JMenuItem item_property;// 属性
	private JMenuItem item_format;
	private Window window;

	private JPopupMenu mul_pop;// 多个单元格选择右键菜单
	private JMenuItem item_mul_del;
	private JMenuItem item_mul_delAll;
	private JMenuItem item_mul_format;
	private JMenuItem item_mul_prop;

	public TableMouseListener(Window window, TempletDragEditor editor,
			EdiTable table) {
		this(window, editor, table, true);
	}

	public TableMouseListener(Window win, TempletDragEditor editor,
			EdiTable arg1, boolean rowAdd) {
		this.window = win;
		this.teditor = editor;
		this.table = arg1;
		this.addRow = rowAdd;

		pop = new JPopupMenu();
		item_edit = new JMenuItem("编辑 (E)");
		item_edit.setMnemonic('e');
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
		item_del = new JMenuItem("删除 (D)");
		item_del.setMnemonic('d');
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

		item_delAll = new JMenuItem("清空内容 (C)");
		item_delAll.setMnemonic('c');
		item_delAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int column = table.getSelectedColumn();
				int row = table.getSelectedRow();
				if (column != -1 && row != -1) {
					ICell cell = (ICell) table.getValueAt(row, column);
					cell.clear();
				}
			}
		});
		pop.add(item_delAll);
		// 分割线
		pop.addSeparator();
		item_editFunc = new JMenuItem("编辑函数 (U)");
		item_editFunc.setMnemonic('u');
		item_editFunc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int column = table.getSelectedColumn();
				int row = table.getSelectedRow();
				if (column != -1 && row != -1) {
					table.editFunctionAt(row, column);
				}
			}
		});
		pop.add(item_editFunc);

		item_delFunc = new JMenuItem("删除函数 (R)");
		item_delFunc.setMnemonic('r');
		item_delFunc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int column = table.getSelectedColumn();
				int row = table.getSelectedRow();
				if (column != -1 && row != -1) {
					ICell cell = (ICell) table.getValueAt(row, column);
					// 判断函数多于1个
					if (cell.getFunctionSize() > 1) {
						if (JOptionPane.YES_OPTION == JOptionPane
								.showConfirmDialog(table,
										"该单元格包含多个函数，确认删除所有函数？", "请确认",
										JOptionPane.YES_NO_OPTION))
							cell.clearFunction();
					} else {
						cell.clearFunction();
					}
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
		JMenuItem itemAddColumn = new JMenuItem("增加列 (A)");
		itemAddColumn.setMnemonic('a');
		itemAddColumn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TableUtils.addColumn(teditor, table);
			}
		});
		pop.add(itemAddColumn);

		// 删除列
		JMenuItem itemDelColumn = new JMenuItem("删除列 (M)");
		itemDelColumn.setMnemonic('m');
		itemDelColumn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TableUtils.delColumnOnCurrent(teditor, table);
			}
		});
		pop.add(itemDelColumn);
		// 在前面插入列
		JMenuItem itemAddColumnOnCurrent = new JMenuItem("在之前插入列 (I)");
		itemAddColumnOnCurrent.setMnemonic('i');
		itemAddColumnOnCurrent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TableUtils.addColumnOnCurrent(teditor, table);
			}
		});
		pop.add(itemAddColumnOnCurrent);
		pop.addSeparator();

		item_format = new JMenuItem("格式(F)");
		item_format.setMnemonic('f');
		item_format.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int column = table.getSelectedColumn();
				int row = table.getSelectedRow();
				if (column != -1 && row != -1) {
					ICell cell = (ICell) table.getValueAt(row, column);
					if (cell == null) {
						cell = new Cell();
						cell.setColumn(column);
						cell.setRow(row);
						table.setValueAt(cell, row, column);
					}
					FormatEditor.getInstance(window).showEdit(cell);
				}
			}
		});
		pop.add(item_format);

		item_property = new JMenuItem("属性(O)");
		item_property.setMnemonic('o');
		item_property.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int column = table.getSelectedColumn();
				int row = table.getSelectedRow();
				if (column != -1 && row != -1) {
					ICell cell = (ICell) table.getValueAt(row, column);
					if (cell == null) {
						cell = new Cell();
						cell.setColumn(column);
						cell.setRow(row);
						table.setValueAt(cell, row, column);
					}
					PropertyEditor.getInstance(window).showEdit(cell);
				}
			}
		});
		pop.add(item_property);

		// #@#@# 选择多个单元格后的右键菜单 #@#@#
		mul_pop = new JPopupMenu();

		item_mul_del = new JMenuItem("删除 (D)");
		item_mul_del.setMnemonic('d');
		item_mul_del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] columns = table.getSelectedColumns();
				int[] rows = table.getSelectedRows();
				for (int c : columns) {
					for (int r : rows) {
						table.setValueAt(null, r, c);
					}
				}
			}
		});
		mul_pop.add(item_mul_del);

		item_mul_delAll = new JMenuItem("删除内容 (C)");
		item_mul_delAll.setMnemonic('c');
		item_mul_delAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] columns = table.getSelectedColumns();
				int[] rows = table.getSelectedRows();
				for (int c : columns) {
					for (int r : rows) {
						ICell cell = (ICell) table.getValueAt(r, c);
						if (cell != null) {
							cell.clear();
						}
					}
				}
				// 需要让表格重新渲染
				table.repaint();
			}
		});
		mul_pop.add(item_mul_delAll);
		// 分割线
		mul_pop.addSeparator();
		
		item_mul_format = new JMenuItem("格式(F)");
		item_mul_format.setMnemonic('f');
		item_mul_format.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 获取原格式
				int column = table.getSelectedColumn();
				int row = table.getSelectedRow();
				ICell cell = (ICell) table.getValueAt(row, column);
				if (cell == null) {
					cell = new Cell();
				}
				FormatEditor fEditor = FormatEditor.getInstance(window);
				fEditor.showEdit(cell);
				if (fEditor.isConfirm()) {
					IFormat format = cell.getFormat();
					int[] columns = table.getSelectedColumns();
					int[] rows = table.getSelectedRows();
					for (int c : columns) {
						for (int r : rows) {
							ICell tcell = (ICell) table.getValueAt(r, c);
							if (tcell == null) {
								tcell = new Cell();
								tcell.setColumn(c);
								tcell.setRow(r);
								table.setValueAt(tcell, r, c);
							}
							// 复制format
							tcell.setFormat(ReportModelUtils
									.copySerializableObject(format));
						}
					}
				}
				// 表格重新渲染
				table.repaint();
			}
		});
		mul_pop.add(item_mul_format);

		item_mul_prop = new JMenuItem("属性(O)");
		item_mul_prop.setMnemonic('o');
		item_mul_prop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 获取原属性
				int column = table.getSelectedColumn();
				int row = table.getSelectedRow();
				ICell cell = (ICell) table.getValueAt(row, column);
				if (cell == null) {
					cell = new Cell();
				}
				PropertyEditor pEditor = PropertyEditor.getInstance(window);
				pEditor.showEdit(cell);
				if (pEditor.isConfirm()) {
					CellProperty prop = cell.getProperty();
					int[] columns = table.getSelectedColumns();
					int[] rows = table.getSelectedRows();
					for (int c : columns) {
						for (int r : rows) {
							ICell tcell = (ICell) table.getValueAt(r, c);
							if (tcell == null) {
								tcell = new Cell();
								tcell.setColumn(c);
								tcell.setRow(r);
								table.setValueAt(tcell, r, c);
							}
							// 复制prop
							tcell.setProperty(ReportModelUtils
									.copySerializableObject(prop));
						}
					}
					// 重新渲染表格
					table.repaint();
				}
			}
		});
		mul_pop.add(item_mul_prop);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// 只响应右键单击事件
		if (e.getClickCount() == 1 && SwingUtilities.isRightMouseButton(e)) {

			// 如果没有选中单元格
			int columnCount = table.getSelectedColumnCount();
			int rowCount = table.getSelectedRowCount();
			if (columnCount == 0 || rowCount == 0) {
				return;
			}

			// 只能在表格内点击
			int column = table.columnAtPoint(e.getPoint());
			int row = table.rowAtPoint(e.getPoint());
			if (column == -1 || row == -1) {
				return;
			}
			// 只能在选择的单元格上点右键
			int[] columns = table.getSelectedColumns();
			boolean columnInclud = false;
			int[] rows = table.getSelectedRows();
			boolean rowInclud = false;
			for (int c : columns) {
				if (c == column) {
					columnInclud = true;
					break;
				}
			}
			for (int r : rows) {
				if (r == row) {
					rowInclud = true;
					break;
				}
			}
			if (!columnInclud || !rowInclud) {
				return;
			}

			// if (column >= 0) {
			// table.addColumnSelectionInterval(column, column);
			// }
			// if (row >= 0) {
			// table.addRowSelectionInterval(row, row);
			// }

			// 如果是选择多个单元格
			if (columnCount > 1 || rowCount > 1) {

				mul_pop.show(e.getComponent(), e.getX(), e.getY());

			} else {// 选择的是单个单元格
				ICell cell = (ICell) table.getValueAt(row, column);
				if (cell == null) {
					item_edit.setEnabled(false);
					item_del.setEnabled(false);
					item_delAll.setEnabled(false);
					item_editFunc.setEnabled(false);
					item_delFunc.setEnabled(false);
					// 属性和格式在单元格为空时也可以编辑
					// item_format.setEnabled(false);
					// item_property.setEnabled(false);

				} else {
					// 只有cell不为空就可用删除
					item_del.setEnabled(true);

					// item_format.setEnabled(true);
					// item_property.setEnabled(true);

					if (cell.getEntitySize() > 0) {
						item_edit.setEnabled(true);
						item_delAll.setEnabled(true);
					} else {
						item_edit.setEnabled(false);
						item_delAll.setEnabled(false);
					}
					int size = cell.getFunctionSize();
					if (size > 0) {
						// 函数不为空就可以删除
						item_delFunc.setEnabled(true);
						if (size == 1) {// 如果只有一个函数需要判断该函数是否可以编辑
							IFunction func = cell.getFunction(0);
							if (func.canEdit()) {
								item_editFunc.setEnabled(true);
							} else {
								item_editFunc.setEnabled(false);
							}
						} else {// 如果多于1个函数就可以编辑
							item_editFunc.setEnabled(true);
						}
					} else {
						item_delFunc.setEnabled(false);
						item_editFunc.setEnabled(false);
					}
				}
				pop.show(e.getComponent(), e.getX(), e.getY());
			}

		}
	}

}