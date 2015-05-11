package com.jTableWWH;

import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Enumeration;
import java.util.EventObject;

import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * 描述：支持合并的表格
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2013-1-6      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class MergeTable extends JTable {

	private static final long serialVersionUID = -5474610975739246699L;

	/**
	 * 带合并功能的Model
	 */
	private MergeTableModel mergeTableModel;

	/**
	 * 构造函数
	 * 
	 * @param tableModel
	 */
	public MergeTable(MergeTableModel tableModel) {
		super(tableModel);
		this.mergeTableModel = tableModel;
		getColumnModel().addColumnModelListener(new TableColumnModelListener() {

			@Override
			public void columnSelectionChanged(ListSelectionEvent e) {
			}

			@Override
			public void columnRemoved(TableColumnModelEvent e) {
			}

			@Override
			public void columnMoved(TableColumnModelEvent e) {
				// 合并块移动
				int fromIndex = e.getFromIndex();
				int toIndex = e.getToIndex();
				if (fromIndex != toIndex && fromIndex != -1 && toIndex != -1) {
					mergeTableModel.blockColumnMoved(fromIndex, toIndex);
				}
			}

			@Override
			public void columnMarginChanged(ChangeEvent e) {
			}

			@Override
			public void columnAdded(TableColumnModelEvent e) {
			}
		});
	}

	/**
	 * <pre>
	 * 返回MergeTableModel
	 * </pre>
	 * 
	 * @return
	 */
	public MergeTableModel getMergeTableModel() {
		return mergeTableModel;
	}

	/**
	 * <pre>
	 * 获取表格模型
	 * </pre>
	 * 
	 * @return
	 */
	public AbstractTableModel getTableModel() {
		return mergeTableModel;
	}

	/**
	 * <pre>
	 * 获取单元格状态
	 * </pre>
	 * 
	 * @param row
	 *            行
	 * @param column
	 *            列
	 * @return 返回状态，参考：{@link MergeBlock}
	 */
	public int getCellState(int row, int column) {
		return mergeTableModel.getCellState(row, column);
	}

	/**
	 * <pre>
	 * 查找合并块
	 * </pre>
	 * 
	 * @param row
	 *            行
	 * @param column
	 *            列
	 * @return 如果指定的单元格被合并则返回该单元格，否则返回null
	 */
	public MergeBlock findMergeBlock(int row, int column) {

		return mergeTableModel.findMergeBlock(row, column);
	}

	/**
	 * <pre>
	 * 合并单元格
	 * </pre>
	 * 
	 * @param rows
	 *            行数组
	 * @param columns
	 *            列数组
	 * @return 如果最小行数<0 或 最大行数大于表格行数；返回false<br>
	 *         如果最小列数<0 或 最大列数大于表格列数；返回false<br>
	 *         如果数组不连续抛出 IllegalArgumentException<br>
	 *         否则返回true
	 * */
	public boolean merge(int[] rows, int[] columns) {
		boolean b = mergeTableModel.merge(rows, columns);
		if (b) {
			repaint();
		}
		return b;
	}

	/**
	 * <pre>
	 * 判断是否能够合并
	 * 如果所选择的单元格都在表格范围内，并且都没有被合并则可以合并
	 * </pre>
	 * 
	 * @param rows
	 *            行
	 * @param columns
	 *            列
	 * @return 如果能合并返回true，否则返回false
	 */
	public boolean canMerge(int[] rows, int[] columns) {
		return mergeTableModel.canMerge(rows, columns);
	}

	/**
	 * <pre>
	 * 拆分并且合并单元格
	 * 如果所选择的单元格已经合并过则将其拆分之后再合并
	 * </pre>
	 * 
	 * @param rows
	 *            行数组
	 * @param columns
	 *            列数组
	 * @return 如果最小行数<0 或 最大行数大于表格行数；返回false<br>
	 *         如果最小列数<0 或 最大列数大于表格列数；返回false<br>
	 *         如果数组不连续抛出 IllegalArgumentException<br>
	 *         否则返回true
	 * */
	public boolean splitAndMerge(int[] rows, int[] columns) {
		boolean b = mergeTableModel.splitAndMerge(rows, columns);
		if (b) {
			repaint();
		}
		return b;
	}

	/**
	 * <pre>
	 * 拆分单元格
	 * 不管是被合并还是合并其他
	 * </pre>
	 * 
	 * @param row
	 *            行
	 * @param column
	 *            列
	 * @return 如果成功返回true
	 */
	public boolean split(int row, int column) {
		boolean b = mergeTableModel.split(row, column);

		if (b) {
			repaint();
		}
		return b;
	}

	@Override
	public Rectangle getCellRect(int row, int column, boolean includeSpacing) {

		// 正常的单元格
		if (MergeBlock.NORMAL_STATE == getCellState(row, column)) {
			return super.getCellRect(row, column, includeSpacing);
		} else {
			// 合并和被合并的格子

			// 先取这个块
			MergeBlock block = findMergeBlock(row, column);
			if (block == null) {
				return super.getCellRect(row, column, includeSpacing);
			}

			Rectangle r = super.getCellRect(block.getRow(), block.getColumn(),
					includeSpacing);

			// 计算宽和高

			// 计算高度，需要计算行间距
			for (Integer rIndex : block.getRowList()) {
				if (rIndex == block.getRow())
					continue;
				Rectangle tmpR = super.getCellRect(rIndex, block.getColumn(),
						true);
				r.height += tmpR.height;

			}
			// 计算宽度，需要计算列间距
			for (Integer cIndex : block.getColumnList()) {
				if (cIndex == block.getColumn())
					continue;
				Rectangle tmpR = super
						.getCellRect(block.getRow(), cIndex, true);
				r.width += tmpR.width;
			}

			return r;
		}

	}

	/**
	 * <pre>
	 * 返回正常的单元格矩形
	 * 正常的和合并其他单元格的
	 * 被合并的单元格将返回null
	 * </pre>
	 * 
	 * @param row
	 * @param column
	 * @param includeSpacing
	 * @return
	 */
	public Rectangle getNormalCellRect(int row, int column,
			boolean includeSpacing) {

		// 正常的单元格
		if (MergeBlock.NORMAL_STATE == getCellState(row, column)) {
			return super.getCellRect(row, column, includeSpacing);
		}

		// 合并其他的单元格
		if (MergeBlock.MERGE_OTHER_STATE == getCellState(row, column)) {
			Rectangle r = super.getCellRect(row, column, includeSpacing);
			// 先取这个块
			MergeBlock block = findMergeBlock(row, column);
			if (block == null) {
				return r;
			}

			// 计算宽和高

			// 计算高度，需要计算行间距
			for (Integer rIndex : block.getRowList()) {
				if (rIndex == row)
					continue;
				Rectangle tmpR = super.getCellRect(rIndex, column, true);
				r.height += tmpR.height;

			}
			// 计算宽度，需要计算列间距
			for (Integer cIndex : block.getColumnList()) {
				if (cIndex == column)
					continue;
				Rectangle tmpR = super.getCellRect(row, cIndex, true);
				r.width += tmpR.width;
			}

			return r;
		}

		// 被其他单元格合并
		if (MergeBlock.BY_OTHER_MERGE_STATE == getCellState(row, column)) {
			return null;
		}

		return null;
	}

	/**
	 * <pre>
	 * 获取该点的最大列，被合并的情况
	 * </pre>
	 * 
	 * @param point
	 * @return
	 */
	public int maxColumnAtPoint(Point point) {
		int col = super.columnAtPoint(point);
		if (col < 0)
			return col;
		int row = super.rowAtPoint(point);
		if (row < 0)
			return col;
		if (MergeBlock.NORMAL_STATE == getCellState(row, col)) {
			// 如果是正常的
			return col;
		} else {
			// 如果被其他的合并，则返回被合并的单元格
			MergeBlock block = findMergeBlock(row, col);
			return block.getMaxColumn();
		}

	}

	@Override
	public int columnAtPoint(Point point) {
		int col = super.columnAtPoint(point);
		if (col < 0)
			return col;
		int row = super.rowAtPoint(point);
		if (row < 0)
			return col;
		if (MergeBlock.NORMAL_STATE == getCellState(row, col)) {
			// 如果是正常的
			return col;
		}
		if (MergeBlock.MERGE_OTHER_STATE == getCellState(row, col)) {
			// 如果是合并其他
			return col;
		}
		if (MergeBlock.BY_OTHER_MERGE_STATE == getCellState(row, col)) {
			// 如果被其他的合并，则返回被合并的单元格
			MergeBlock block = findMergeBlock(row, col);
			return block.getColumn();
		}

		return col;
	}

	/**
	 * <pre>
	 * 获取该点的最大行，被合并的情况
	 * </pre>
	 * 
	 * @param point
	 * @return
	 */
	public int maxRowAtPoint(Point point) {
		int row = super.rowAtPoint(point);
		if (row < 0)
			return row;

		int col = super.columnAtPoint(point);
		if (col < 0)
			return row;

		if (MergeBlock.NORMAL_STATE == getCellState(row, col)) {
			// 如果是正常的
			return row;
		} else {
			// 如果被其他的合并，则返回被合并的单元格
			MergeBlock block = findMergeBlock(row, col);
			return block.getMaxRow();
		}

	}

	@Override
	public int rowAtPoint(Point point) {

		int row = super.rowAtPoint(point);
		if (row < 0)
			return row;

		int col = super.columnAtPoint(point);
		if (col < 0)
			return row;

		if (MergeBlock.NORMAL_STATE == getCellState(row, col)) {
			// 如果是正常的
			return row;
		}
		if (MergeBlock.MERGE_OTHER_STATE == getCellState(row, col)) {
			// 如果是合并其他
			return row;
		}
		if (MergeBlock.BY_OTHER_MERGE_STATE == getCellState(row, col)) {
			// 如果被其他的合并，则返回被合并的单元格
			MergeBlock block = findMergeBlock(row, col);
			return block.getRow();
		}

		return row;

	}

	@Override
	public Object getValueAt(int row, int column) {
		// 如果是获取被合并的单元格，则取左上角的value
		MergeBlock block = findMergeBlock(row, column);
		if (block == null) {
			return super.getValueAt(row, column);
		} else {
			return super.getValueAt(block.getRow(), block.getColumn());
		}
	}

	@Override
	public void setValueAt(Object aValue, int row, int column) {
		// 跟设置value一样，如果是被合并的单元格，则取左上角的那个格子
		MergeBlock block = findMergeBlock(row, column);
		if (block == null) {
			super.setValueAt(aValue, row, column);
		} else {
			super.setValueAt(aValue, block.getRow(), block.getColumn());
		}
	}

	@Override
	public boolean editCellAt(int row, int column, EventObject e) {
		if (getCellState(row, column) == MergeBlock.BY_OTHER_MERGE_STATE) {
			// 如果是被其他合并的单元格，编辑左上角的格子
			MergeBlock block = findMergeBlock(row, column);

			return super.editCellAt(block.getRow(), block.getColumn(), e);
		} else {
			return super.editCellAt(row, column, e);
		}
	}

	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row,
			int column) {
		Object value = getValueAt(row, column);

		boolean isSelected = false;
		boolean hasFocus = false;

		// Only indicate the selection and focused cell if not printing
		if (!isPaintingForPrint()) {
			isSelected = isCellSelected(row, column);

			hasFocus = hasFocus(row, column);
		}
		// 如果跨类型，将会类型转换出错
		return renderer.getTableCellRendererComponent(this, value, isSelected,
				hasFocus, row, column);
	}

	/**
	 * <pre>
	 * 判断是否具有焦点
	 * </pre>
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	private boolean hasFocus(int row, int column) {
		if (!isFocusOwner())
			return false;
		boolean rowIsLead = (selectionModel.getLeadSelectionIndex() == row);
		boolean colIsLead = (columnModel.getSelectionModel()
				.getLeadSelectionIndex() == column);
		if (rowIsLead && colIsLead) {
			return true;
		} else {
			MergeBlock block = findMergeBlock(row, column);
			if (block != null) {

				int leadRow = selectionModel.getLeadSelectionIndex();
				int leadCol = columnModel.getSelectionModel()
						.getLeadSelectionIndex();

				// 如果块中任意个点具有焦点，则认为有焦点
				for (int r : block.getRowList()) {
					for (int c : block.getColumnList()) {
						if (r == leadRow && c == leadCol) {
							return true;
						}
					}
				}

				// rowIsLead = block.getRow() == leadRow
				// || block.getMaxRow() == leadRow;
				// colIsLead = block.getColumn() == leadCol
				// || block.getMaxColumn() == leadCol;
				//
				// return rowIsLead && colIsLead;
			}
		}

		return false;
	}

	@Override
	public boolean isCellSelected(int row, int column) {

		boolean boo = super.isCellSelected(row, column);

		// 如果块中有一个是选中的，则都是选中的
		if (!boo) {
			MergeBlock block = findMergeBlock(row, column);
			if (block != null) {

				for (int r : block.getRowList()) {
					for (int c : block.getColumnList()) {
						if (super.isCellSelected(r, c)) {

							return true;
						}
					}
				}

			}
		}

		return boo;
	}

	@Override
	public void changeSelection(int rowIndex, int columnIndex, boolean toggle,
			boolean extend) {

		if (extend) {// 如果是连续的

			ListSelectionModel seModel = getSelectionModel();

			ListSelectionModel colSeModel = getColumnModel()
					.getSelectionModel();

			int anchroRow = seModel.getAnchorSelectionIndex();
			int anchroCol = colSeModel.getAnchorSelectionIndex();

			// 开始点的行和列
			int startRow = seModel.getAnchorSelectionIndex();
			int startCol = colSeModel.getAnchorSelectionIndex();

			// 结束点的行和列
			int endRow = rowIndex;
			int endCol = columnIndex;

			// 当前选择的行大于锚点的行
			boolean gtRow = rowIndex > startRow;
			boolean grCol = columnIndex > startCol;

			// 需要重新设置最初的选择位置

			// 最大行
			int maxRow;
			// 最小行
			int minRow;
			// 最大列
			int maxCol;
			// 最小列
			int minCol;

			if (gtRow) {
				maxRow = rowIndex;
				minRow = startRow;
			} else {
				maxRow = startRow;
				minRow = rowIndex;
			}

			if (grCol) {
				maxCol = columnIndex;
				minCol = startCol;
			} else {
				maxCol = startCol;
				minCol = columnIndex;
			}

			// 定义一个redo
			boolean redo = false;
			do {

				redo = false;

				for (int row = minRow, endr = maxRow; row <= endr; row++) {
					for (int col = minCol, endc = maxCol; col <= endc; col++) {
						MergeBlock block = findMergeBlock(row, col);
						if (block != null) {
							// 可能进行重复的动作

							// minRow = Math.min(block.getRow(), minRow);
							if (block.getRow() < minRow) {
								minRow = block.getRow();
								redo = true;
							}
							// maxRow = Math.max(block.getMaxRow(), maxRow);
							if (block.getMaxRow() > maxRow) {
								maxRow = block.getMaxRow();
								redo = true;
							}
							// minCol = Math.min(block.getColumn(), minCol);
							if (block.getColumn() < minCol) {
								minCol = block.getColumn();
								redo = true;
							}
							// maxCol = Math.max(block.getMaxColumn(), maxCol);
							if (block.getMaxColumn() > maxCol) {
								maxCol = block.getMaxColumn();
								redo = true;
							}
						}
					}
				}
			} while (redo);

			// 计算开始点
			if (gtRow) {
				// 开始点在上面
				startRow = Math.min(startRow, minRow);
				// 结束点在下面
				endRow = Math.max(endRow, maxRow);
			} else {
				// 开始点在下面
				startRow = Math.max(startRow, maxRow);
				// 结束点在上面
				endRow = Math.min(endRow, minRow);
			}

			if (grCol) {
				// 开始点的列在左边
				startCol = Math.min(startCol, minCol);
				// 结束点在右边
				endCol = Math.max(endCol, maxCol);
			} else {
				// 开始点的列在右边
				startCol = Math.max(startCol, maxCol);
				endCol = Math.min(endCol, minCol);
			}

			if (anchroRow != startRow) {
				seModel.setAnchorSelectionIndex(startRow);
			}
			if (anchroCol != startCol) {
				colSeModel.setAnchorSelectionIndex(startCol);
			}

			super.changeSelection(endRow, endCol, toggle, extend);

		} else {
			// 用于处理在合并的单元格内移动焦点时，焦点看起来移不动的问题
			changeSelection4oneCell(rowIndex, columnIndex, toggle, extend);
		}

		// 如果一旦选择改变之后就进行重绘则不会出现任何问题
		// 但是如果表格巨大的话重绘比较浪费资源
		// 如果重写paint方法则滚动时可能出现问题
		// RepaintManager repaintManager = RepaintManager.currentManager(this);
		// repaintManager.addDirtyRegion(this, 0, 0, 300, 300);
		repaint();
	}

	private void changeSelection4oneCell(int rowIndex, int columnIndex,
			boolean toggle, boolean extend) {

		// 用于处理在合并的单元格内移动焦点时，焦点看起来移不动的问题

		MergeBlock block = findMergeBlock(rowIndex, columnIndex);
		if (block == null) {
			super.changeSelection(rowIndex, columnIndex, toggle, extend);
		} else {
			ListSelectionModel seModel = getSelectionModel();

			ListSelectionModel colSeModel = getColumnModel()
					.getSelectionModel();
			// 判断上一个选中的格子
			int anchroRow = seModel.getAnchorSelectionIndex();
			int anchroCol = colSeModel.getAnchorSelectionIndex();

			// 之前选中的
			MergeBlock blockOld = findMergeBlock(anchroRow, anchroCol);
			if (blockOld == null || block != blockOld) {
				// 上一个选中的不是合并的块
				// 或者选中的的是两个不同的合并块
				super.changeSelection(rowIndex, columnIndex, toggle, extend);
			} else {
				// 在一个块内移动焦点才进行处理
				int newRow;
				int newCol;

				// 计算新行
				if (anchroRow == rowIndex) {
					// 在当前行内移动
					newRow = rowIndex;
				} else {
					// 重新计算新的行
					if (rowIndex > anchroRow) {
						// 从上到下
						// 如到底了，则不做处理
						if (getRowCount() == block.getMaxRow() + 1) {
							return;
						}
						newRow = block.getMaxRow() + 1;
					} else {
						// 从下到上
						if (block.getRow() == 0) {
							return;
						}
						newRow = block.getRow() - 1;
					}
				}

				// 计算新列
				if (anchroCol == columnIndex) {
					// 在当前列内移动
					newCol = columnIndex;
				} else {
					// 重新计算新的列
					if (columnIndex > anchroCol) {
						// 从左向右移动
						if (block.getMaxColumn() + 1 == getColumnCount()) {
							return;
						}
						newCol = block.getMaxColumn() + 1;
					} else {
						// 从右向左移动
						if (block.getColumn() == 0) {
							return;
						}

						newCol = block.getColumn() - 1;
					}
				}

				super.changeSelection(newRow, newCol, toggle, extend);

			}

		}
	}

	/**
	 * Notification from the <code>UIManager</code> that the L&F has changed.
	 * Replaces the current UI object with the latest version from the
	 * <code>UIManager</code>.
	 * 
	 * @see JComponent#updateUI
	 */
	@SuppressWarnings("rawtypes")
	public void updateUI() {
		// Update the UIs of the cell renderers, cell editors and header
		// renderers.
		TableColumnModel cm = getColumnModel();
		for (int column = 0; column < cm.getColumnCount(); column++) {
			TableColumn aColumn = cm.getColumn(column);
			updateSubComponentUI(aColumn.getCellRenderer());
			updateSubComponentUI(aColumn.getCellEditor());
			updateSubComponentUI(aColumn.getHeaderRenderer());
		}

		// Update the UIs of all the default renderers.
		Enumeration defaultRenderers = defaultRenderersByColumnClass.elements();
		while (defaultRenderers.hasMoreElements()) {
			updateSubComponentUI(defaultRenderers.nextElement());
		}

		// Update the UIs of all the default editors.
		Enumeration defaultEditors = defaultEditorsByColumnClass.elements();
		while (defaultEditors.hasMoreElements()) {
			updateSubComponentUI(defaultEditors.nextElement());
		}

		// Update the UI of the table header
		if (tableHeader != null && tableHeader.getParent() == null) {
			tableHeader.updateUI();
		}

		// Update UI applied to parent ScrollPane
		// configureEnclosingScrollPaneUI();

		// setUI((TableUI) UIManager.getUI(this));
		setUI(new MergeTableUI2());
	}

	private void updateSubComponentUI(Object componentShell) {
		if (componentShell == null) {
			return;
		}
		Component component = null;
		if (componentShell instanceof Component) {
			component = (Component) componentShell;
		}
		if (componentShell instanceof DefaultCellEditor) {
			component = ((DefaultCellEditor) componentShell).getComponent();
		}

		if (component != null) {
			SwingUtilities.updateComponentTreeUI(component);
		}
	}
}
