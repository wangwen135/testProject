package com.jTableWWH.tableRowHead;

import java.awt.AWTKeyStroke;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.TableCellEditor;

import com.jTableWWH.DragRowMouseListenerTwo;

/**
 * 描述：用做行头的表格
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2013-9-9      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class HeadTable extends JTable {

	// 构造时强制要求
	// 干脆 增加、删除行的方法都写到表格对象中算了
	// 不再依赖第三个工具类了

	private static final long serialVersionUID = 5982681880147725485L;

	private JTable mainTable;

	private HeadTableModel headTableModel;

	public HeadTable(HeadTableModel headTableModel, JTable mainTable) {
		super(headTableModel);
		this.headTableModel = headTableModel;
		this.mainTable = mainTable;

		// 焦点
		Set<AWTKeyStroke> ftkSet = new HashSet<AWTKeyStroke>();

		ftkSet.add(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0));
		ftkSet.add(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0));
		ftkSet.add(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
		ftkSet.add(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0));

		setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,
				ftkSet);

		Set<AWTKeyStroke> btkSet = new HashSet<AWTKeyStroke>();

		btkSet.add(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0));
		btkSet.add(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0));
		btkSet.add(KeyStroke.getKeyStroke(KeyEvent.VK_TAB,
				InputEvent.SHIFT_DOWN_MASK));

		setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS,
				btkSet);

		// 设置高度？
		setRowHeight(30);

		// 设置渲染器和编辑器
		setDefaultRenderer(RowHeadEntity.class, new HeadTableCellRenderer());
		setDefaultEditor(RowHeadEntity.class, new HeadTableCellEdit());

		// 表格行头的行高度调整
		DragRowMouseListenerTwo listenerTwo = new DragRowMouseListenerTwo(this,
				mainTable);
		addMouseListener(listenerTwo);
		addMouseMotionListener(listenerTwo);

	}

	/**
	 * <pre>
	 * 获取主表格
	 * </pre>
	 * 
	 * @return
	 */
	public JTable getMainTable() {
		return mainTable;
	}

	/**
	 * <pre>
	 * 获取行头模型
	 * </pre>
	 * 
	 * @return
	 */
	public HeadTableModel getHeadTableModel() {
		return headTableModel;
	}

	@Override
	public void setValueAt(Object aValue, int row, int column) {
		// 结束编辑后可能需要调整表格的高度
		super.setValueAt(aValue, row, column);
	}

	@Override
	public void setRowHeight(int row, int rowHeight) {
		// 行高度调整时
		TableCellEditor tce = getCellEditor();
		if (tce != null) {
			tce.stopCellEditing();
		}

		// 设置行高度后，需要重新模型中的值
		// 没有排序
		RowHeadEntity entity = headTableModel.getRowHeadEntity(row);
		entity.setHeight(rowHeight);

		super.setRowHeight(row, rowHeight);

	}

	@Override
	public boolean editCellAt(int row, int column, EventObject e) {

		if (e instanceof MouseEvent) {
			MouseEvent me = (MouseEvent) e;
			if (me.getClickCount() == 2) {
				TableCellEditor tce = mainTable.getCellEditor();
				if (tce != null) {
					tce.stopCellEditing();
				}
				mainTable.clearSelection();

				// 可能需要循环遍历
				mainTable.addRowSelectionInterval(row, row);
				mainTable.addColumnSelectionInterval(0, 0);

				mainTable.changeSelection(row, mainTable.getColumnCount() - 1,
						false, true);

				// 表格滚动
				Rectangle rect = mainTable.getCellRect(row, column, false);
				if (rect != null) {
					mainTable.scrollRectToVisible(rect);
				}

				return false;
			}
		}

		return super.editCellAt(row, column, e);
	}

}
