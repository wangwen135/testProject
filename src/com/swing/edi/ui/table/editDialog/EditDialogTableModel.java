package com.swing.edi.ui.table.editDialog;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.swing.edi.reportModel.area.cell.ICell;
import com.swing.edi.reportModel.area.cell.entity.IEntity;
import com.swing.edi.reportModel.area.cell.part.IPart;

/**
 * 描述：编辑对话框中的表格模型<br>
 * 模型只要两列，用两个list维护数据
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-2-1      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class EditDialogTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 2387353037321909872L;

	// 或者直接传一个cell进来 然后编辑完成在将cell传递出去
	// 取值和设置直接操作cell中的，
	// 只需要对cell中的两个列表进行浅克隆就ok了
	// 只是那两个列表对于cell来说是私有属性
	private ICell cell;

	private boolean changed = false;
	ArrayList<IEntity> entityList = new ArrayList<IEntity>();
	ArrayList<IPart> partList = new ArrayList<IPart>();

	public EditDialogTableModel(ICell cell) {
		this.cell = cell;

		// 获取cell中两列的全部对象，放到一个临时集合中，编辑完了再清空cell，然后从临时集合中添加
		// 只是如果后面需要对entity对象进行修改时需要注意一下，是否还是修改的原来的cell中的entity，是否需要克隆，或直接new一个新的对象
		for (int i = 0; i < cell.getEntitySize(); i++) {
			entityList.add(cell.getEntity(i));
			partList.add(cell.getPart(i));
		}
	}

	@Override
	public int getRowCount() {
		return entityList.size();
	}

	@Override
	public int getColumnCount() {
		// 只有两列
		return 2;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// 从临时集合中取值
		if (columnIndex == 0) {
			return entityList.get(rowIndex);
		} else if (columnIndex == 1) {
			if (rowIndex == (entityList.size() - 1)) {
				// 最后一个单元格是为空
				return null;
			}
			return partList.get(rowIndex);
		}
		return null;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		changed = true;
		// 如果设置null需要删除该行
		if (aValue == null) {
			entityList.remove(rowIndex);
			partList.remove(rowIndex);
			// 通知表格改变
			fireTableDataChanged();
			return;

		}

		if (columnIndex == 0) {
			IEntity entity = (IEntity) aValue;
			if (rowIndex < 0 || rowIndex >= getRowCount()) {
				entityList.add(entity);
				fireTableDataChanged();
			} else {
				entityList.set(rowIndex, entity);
				fireTableCellUpdated(rowIndex, columnIndex);
			}

		} else if (columnIndex == 1) {
			IPart part = (IPart) aValue;
			if (rowIndex < 0 || rowIndex >= getRowCount()) {
				partList.add(part);
				fireTableDataChanged();
			} else {
				partList.set(rowIndex, part);
				fireTableCellUpdated(rowIndex, columnIndex);
			}
		}

	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return true;
		} else {
			if (columnIndex == 1 && rowIndex == (getRowCount() - 1)) {
				// 最后一个单元格是不可以编辑的
				return false;
			}
		}
		return true;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex == 0) {
			return IEntity.class;
		} else {
			return IPart.class;
		}
	}

	@Override
	public String getColumnName(int column) {
		if (column == 0) {
			return "实体对象";
		} else if (column == 1) {
			return "连接符号";
		}
		return super.getColumnName(column);
	}

	/**
	 * <pre>
	 * 同步数据，将修改的内容保存到cell中
	 * </pre>
	 */
	public void synchData() {
		if (changed) {
			cell.clear();
			for (int i = 0; i < entityList.size(); i++) {
				cell.addEntity(entityList.get(i));
				cell.addPart(partList.get(i));
			}
			changed = false;
		}
	}
}
