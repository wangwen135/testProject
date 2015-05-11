package com.jTableWWH;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * <pre>
 * 描述： 支持合并的表格模型，继承自AbstractTableModel
 * 主要增加一个合并块列表
 * </pre>
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2013-1-5      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public abstract class MergeTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 881239392726863184L;

	/**
	 * 合并块列表
	 */
	private List<MergeBlock> mergeBlockList = new ArrayList<MergeBlock>();

	/**
	 * <pre>
	 * 获取合并块列表
	 * </pre>
	 * 
	 * @return
	 */
	public List<MergeBlock> getMergeBlockList() {
		return mergeBlockList;
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
		for (MergeBlock block : mergeBlockList) {
			if (block.contain(row, column)) {
				return block;
			}
		}
		return null;
	}

	/**
	 * <pre>
	 * 获取指定单元格状态
	 * </pre>
	 * 
	 * @param row
	 *            行
	 * @param column
	 *            列
	 * @return 返回状态，参考：{@link MergeBlock}
	 */
	public int getCellState(int row, int column) {
		MergeBlock block = findMergeBlock(row, column);
		if (block != null) {
			return block.getState(row, column);
		}
		return MergeBlock.NORMAL_STATE;
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
		Arrays.sort(rows);
		Arrays.sort(columns);

		if (rows[0] < 0) {
			return false;
		}
		if (columns[0] < 0) {
			return false;
		}

		if (rows[rows.length - 1] > getRowCount() - 1) {
			return false;
		}
		if (columns[columns.length - 1] > getColumnCount() - 1) {
			return false;
		}

		MergeBlock block = new MergeBlock(rows, columns);
		return addMergeBlock(block);

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
		Arrays.sort(rows);
		Arrays.sort(columns);

		if (rows[0] < 0) {
			return false;
		}
		if (columns[0] < 0) {
			return false;
		}

		if (rows[rows.length - 1] > getRowCount() - 1) {
			return false;
		}
		if (columns[columns.length - 1] > getColumnCount() - 1) {
			return false;
		}
		for (int r : rows) {
			for (int c : columns) {
				if (getCellState(r, c) != MergeBlock.NORMAL_STATE) {
					return false;
				}
			}
		}

		return true;
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
		Arrays.sort(rows);
		Arrays.sort(columns);

		if (rows[0] < 0) {
			return false;
		}
		if (columns[0] < 0) {
			return false;
		}

		if (rows[rows.length - 1] > getRowCount() - 1) {
			return false;
		}
		if (columns[columns.length - 1] > getColumnCount() - 1) {
			return false;
		}

		if (!canMerge(rows, columns)) {
			// 需要先拆分单元格
			for (int r : rows) {
				for (int c : columns) {
					split(r, c);
				}
			}
		}

		MergeBlock block = new MergeBlock(rows, columns);
		addMergeBlock(block);

		return true;
	}

	/**
	 * <pre>
	 * 合并单元格
	 * 包含开始，不包含结束
	 * 不会常用的方法
	 * </pre>
	 * 
	 * @param rowFrom
	 *            起始行
	 * @param rowTo
	 *            终止行
	 * @param columnFrom
	 *            起始列
	 * @param columnTo
	 *            终止列
	 * @return 如果最小行数<0 或 最大行数大于表格行数；返回false<br>
	 *         如果最小列数<0 或 最大列数大于表格列数；返回false<br>
	 *         否则返回true
	 */
	public boolean merge(int rowFrom, int rowTo, int columnFrom, int columnTo) {
		if (rowFrom < 0) {
			return false;
		}
		if (columnFrom < 0) {
			return false;
		}
		if (rowTo > getRowCount() - 1) {
			return false;
		}
		if (columnTo > getColumnCount() - 1) {
			return false;
		}

		MergeBlock block = new MergeBlock(rowFrom, rowTo, columnFrom, columnTo);
		addMergeBlock(block);
		return true;
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
		MergeBlock block = findMergeBlock(row, column);

		if (block != null) {
			return removeMergeBlock(block);
		}

		return false;
	}

	/**
	 * <pre>
	 * 移除一个合并块
	 * </pre>
	 * 
	 * @param block
	 *            合并块
	 * @return 是否成功
	 */
	public boolean removeMergeBlock(MergeBlock block) {
		return mergeBlockList.remove(block);
	}

	/**
	 * <pre>
	 * 添加一个合并块
	 * </pre>
	 * 
	 * @param block
	 *            合并块
	 * @return 是否成功
	 */
	public boolean addMergeBlock(MergeBlock block) {
		return mergeBlockList.add(block);
	}

	/**
	 * <pre>
	 * 返回合并块的数量
	 * </pre>
	 * 
	 * @return
	 */
	public int getMergeBlockSize() {
		return mergeBlockList.size();
	}

	/**
	 * <pre>
	 * 清空所有合并块
	 * </pre>
	 */
	public void clearMergeBlock() {
		mergeBlockList.clear();
	}

	// /**
	// * <pre>
	// * 列移动
	// * 将合并块从一列移动到另外一列
	// * </pre>
	// *
	// * @param fromIndex
	// * @param toIndex
	// */
	// public void columnMoved(int fromIndex, int toIndex) {
	// for (MergeBlock block : mergeBlockList) {
	// block.columnMoved(fromIndex, toIndex);
	// }
	// }

	// 模型需要保证块不重叠

	// 在某些方法中需要对操作进行检查

	/**
	 * <pre>
	 * 列移动
	 * 将合并块从一列移动到另外一列
	 * 只会移动块的列（最小）
	 * </pre>
	 * 
	 * @param fromIndex
	 * @param toIndex
	 */
	public void blockColumnMoved(int fromIndex, int toIndex) {

		// 先移动，完了再检查
		for (MergeBlock block : mergeBlockList) {
			if (fromIndex == block.getColumn()) {
				block.columnMoved(fromIndex, toIndex);
			} else if (toIndex == block.getColumn()) {
				block.columnMoved(toIndex, fromIndex);
			}
		}

		checkAndClean();

	}

	/**
	 * <pre>
	 * 循环检查每一个单元，如果某单元中有两个合并块
	 * 则清除后面的合并块
	 * </pre>
	 */
	public void checkAndClean() {
		for (int row = 0; row < getRowCount(); row++) {
			for (int col = 0; col < getColumnCount(); col++) {
				// 循环每一个格子，查找这个格子的合并块
				List<MergeBlock> list = findBlock(row, col);

				for (int i = list.size() - 1; i > 0; i--) {
					// 先移除后合并的格子
					removeMergeBlock(list.get(i));
				}

			}
		}
	}

	/**
	 * <pre>
	 * 查找某个单元的合并块
	 * 
	 * </pre>
	 * 
	 * @param row
	 *            行
	 * @param column
	 *            列
	 * @return
	 */
	private List<MergeBlock> findBlock(int row, int column) {
		List<MergeBlock> list = new ArrayList<MergeBlock>();
		for (MergeBlock block : mergeBlockList) {
			if (block.contain(row, column)) {
				list.add(block);
			}
		}
		return list;
	}
}
