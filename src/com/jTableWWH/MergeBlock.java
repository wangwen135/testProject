package com.jTableWWH;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * 描述：
 * 表示合并的块对象，在tableModel中维护该对象的列表
 * 通过tableModel提供一些对外的查询方法
 * 之后需要将该对象映射到XML中，分别在Head，Body，Tail三个块中定义
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
public class MergeBlock {

	// 应该只有合并和被合并两种状态，如果不包含该单元格则返回错误状态

	/**
	 * 正常的单个单元格
	 */
	public static final int NORMAL_STATE = 0;

	/**
	 * 合并其他
	 */
	public static final int MERGE_OTHER_STATE = 1;

	/**
	 * 被其他合并
	 */
	public static final int BY_OTHER_MERGE_STATE = 2;

	/**
	 * 行集合
	 */
	private List<Integer> rowList;

	/**
	 * 列集合
	 */
	private List<Integer> columnList;

	/**
	 * 构造函数<br>
	 * 包含开始，不包含结束
	 * 
	 * @param rowFrom
	 *            开始行
	 * @param rowTo
	 *            结束行
	 * @param columnFrom
	 *            开始列
	 * @param columnTo
	 *            结束列
	 */
	public MergeBlock(int rowFrom, int rowTo, int columnFrom, int columnTo) {
		rowList = new ArrayList<Integer>();
		columnList = new ArrayList<Integer>();

		for (int i = rowFrom; i < rowTo; i++) {
			rowList.add(i);
		}

		for (int i = columnFrom; i < columnTo; i++) {
			columnList.add(i);
		}
	}

	/**
	 * 构造函数
	 * 
	 * @param rows
	 *            行数组
	 * @param columns
	 *            列数组
	 */
	public MergeBlock(int[] rows, int[] columns) {

		// 先排序
		Arrays.sort(rows);
		Arrays.sort(columns);
		// 只能是连续的行和列
		// 如果不连续抛出参数错误异常
		for (int i = 0; i < rows.length - 1; i++) {
			if (rows[i + 1] - rows[i] != 1) {
				throw new IllegalArgumentException("不连续的行数组");
			}
		}
		for (int i = 0; i < columns.length - 1; i++) {
			if (columns[i + 1] - columns[i] != 1) {
				throw new IllegalArgumentException("不连续的列数组");
			}
		}

		rowList = new ArrayList<Integer>();
		columnList = new ArrayList<Integer>();

		for (int i : rows) {
			rowList.add(i);
		}

		for (int i : columns) {
			columnList.add(i);
		}

	}

	/**
	 * <pre>
	 * 获取行跨度
	 * </pre>
	 * 
	 * @return
	 */
	public int getRowSpan() {
		return rowList.size() - 1;
	}

	/**
	 * <pre>
	 * 获取列跨度
	 * </pre>
	 * 
	 * @return
	 */
	public int getColumnSpan() {
		return columnList.size() - 1;
	}

	/**
	 * <pre>
	 * 返回合并块的行
	 * （最小行）
	 * </pre>
	 * 
	 * @return
	 */
	public int getRow() {
		return rowList.get(0);
	}

	/**
	 * <pre>
	 * 返回合并块所占的最大行
	 * </pre>
	 * 
	 * @return
	 */
	public int getMaxRow() {
		return rowList.get(rowList.size() - 1);
	}

	/**
	 * <pre>
	 * 返回合并块的列
	 * （最小列）
	 * </pre>
	 * 
	 * @return
	 */
	public int getColumn() {
		return columnList.get(0);
	}

	/**
	 * <pre>
	 * 返回合并块所占的最大列
	 * </pre>
	 * 
	 * @return
	 */
	public int getMaxColumn() {
		return columnList.get(columnList.size() - 1);
	}

	/**
	 * <pre>
	 * 获取行集合
	 * </pre>
	 * 
	 * @return
	 */
	public List<Integer> getRowList() {
		return rowList;
	}

	/**
	 * <pre>
	 * 获取列集合
	 * </pre>
	 * 
	 * @return
	 */
	public List<Integer> getColumnList() {
		return columnList;
	}

	/**
	 * <pre>
	 * 是否包含指定的单元格
	 * </pre>
	 * 
	 * @param row
	 *            行
	 * @param column
	 *            列
	 * @return 如果包含返回true，否则返回false
	 */
	public boolean contain(int row, int column) {
		if (!rowList.contains(row)) {
			return false;
		}
		if (!columnList.contains(column)) {
			return false;
		}

		return true;
	}

	/**
	 * <pre>
	 * 获取指定单元格状态
	 * </pre>
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	public int getState(int row, int column) {

		if (!contain(row, column)) {
			// 如果不包含则认为是单个单元格
			return MergeBlock.NORMAL_STATE;
		}

		// 如果行和列都是最小的，则认为是合并了其他的单元格
		if (rowList.get(0) == row && columnList.get(0) == column) {
			return MergeBlock.MERGE_OTHER_STATE;
		}

		return MergeBlock.BY_OTHER_MERGE_STATE;
	}

	/**
	 * <pre>
	 * 从列的维度移动该块
	 * </pre>
	 * @param fromIndex
	 * @param toIndex
	 */
	public void columnMoved(int fromIndex, int toIndex) {
		int range = fromIndex - toIndex;
		for (int i = 0; i < columnList.size(); i++) {
			int col = columnList.get(i);
			columnList.set(i, col - range);
		}
	}

	@Override
	public String toString() {
		StringBuffer strbuf = new StringBuffer();
		strbuf.append(super.toString());

		strbuf.append("  rowList=");
		strbuf.append(rowList);
		strbuf.append("  columnList=");
		strbuf.append(columnList);
		return strbuf.toString();
	}
}
