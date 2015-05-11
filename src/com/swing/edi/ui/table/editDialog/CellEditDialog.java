package com.swing.edi.ui.table.editDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellEditor;

import com.swing.edi.dragDrop.EditDialogTableTransferHandler;
import com.swing.edi.reportModel.area.cell.ICell;
import com.swing.edi.reportModel.area.cell.entity.IEntity;
import com.swing.edi.reportModel.area.cell.part.IPart;
import com.swing.edi.ui.table.EdiTableCellEditor;
import com.swing.edi.ui.table.EdiTableModel;
import com.swing.edi.ui.table.TableUtils;

/**
 * 描述：cell编辑器弹出框<br>
 * 与一个指定的编辑器关联，其中的cell属性是动态变化的，每次setVisible都需要传入一个cell对象
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
public class CellEditDialog extends JDialog {
	private static final long serialVersionUID = 5832505386744536413L;

	/**
	 * 单元格编辑器
	 */
	private EdiTableCellEditor cellEditor;

	/**
	 * 上级表格
	 */
	private JTable superTable;

	/**
	 * 上级表格编辑行
	 */
	private int superRow;

	/**
	 * 上级表格编辑列
	 */
	private int superColumn;
	/**
	 * 单元格对象
	 */
	private ICell cell;

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	public ICell getCell() {
		return cell;
	}

	public void setCell(ICell cell) {
		this.cell = cell;
	}

	public void setTableModelAndVisible(ICell cell, JTable t, int row, int col) {
		this.superTable = t;
		this.superRow = row;
		this.superColumn = col;

		// 此外常量是可以随意编辑的，变量是可以包装的，变量应该设计一个怎样的编辑界面还没有想好

		// 另外需要在设计一个编辑变量的编辑器，如果一个cell中只有一个变量可以直接弹出2编辑器，否则从1编辑器中选择后弹出2编辑器
		// 另外可能需要针对不同的包装设计不同的编辑器

		this.cell = cell;
		EditDialogTableModel dm = new EditDialogTableModel(cell);
		table.setModel(dm);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setResizable(false);
		this.setTitle("正在编辑  row[" + (cell.getRow() + 1) + "] column["
				+ TableUtils.convertIntegerToString(cell.getColumn()) + "]");
		setVisible(true);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CellEditDialog dialog = new CellEditDialog(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CellEditDialog(Window windows, EdiTableCellEditor editor) {
		super(windows);
		this.cellEditor = editor;

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
				cellEditor.cancelCellEditing();
			}
		});
		setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						CellEditDialog.class
								.getResource("/javax/swing/plaf/metal/icons/ocean/menu.gif")));

		// setUndecorated(true);
		// setModal(true);
		// setAlwaysOnTop(true);

		setBounds(100, 100, 450, 300);
		// 居中
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(1, 1, 1, 1));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				table = new JTable();
				table.setSelectionBackground(new Color(184, 207, 229));
				table.setAlignmentY(BOTTOM_ALIGNMENT);
				table.setShowGrid(false);
				table.setShowVerticalLines(false);

				table.setRowHeight(35);
				table.setRowMargin(5);
				table.setIntercellSpacing(new Dimension(0, 0));
				table.setFillsViewportHeight(true);

				table.setCellSelectionEnabled(true);
				table.setColumnSelectionAllowed(true);
				table.setRowSelectionAllowed(true);
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				// 设置渲染器和编辑器
				table.setDefaultEditor(IEntity.class, new EntityCellEditor());
				table.setDefaultEditor(IPart.class, new PartCellEditor());
				// table.setDefaultRenderer(IEntity.class,
				// new Render());

				table.setDefaultRenderer(IEntity.class,
						new EntityCellRenderer());

				table.setDefaultRenderer(IPart.class, new PartCellRenderer());
				// 表格拖动支持
				table.setDragEnabled(true);
				table.setDropMode(DropMode.USE_SELECTION);
				table.setTransferHandler(new EditDialogTableTransferHandler(
						table));
				// 表头
				table.getTableHeader().setReorderingAllowed(false);
				// 添加右键菜单
				table.addMouseListener(new MouseAdapter() {
					private JPopupMenu pop;
					{
						pop = new JPopupMenu();
						JMenuItem itemDel = new JMenuItem("删除");
						itemDel.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								int column = table.getSelectedColumn();
								int row = table.getSelectedRow();
								if (column != -1 && row != -1) {
									table.setValueAt(null, row, column);
								}
							}
						});
						pop.add(itemDel);
					}

					@Override
					public void mouseClicked(MouseEvent e) {
						if (e.getClickCount() == 1
								&& SwingUtilities.isRightMouseButton(e)) {
							int column = table.columnAtPoint(e.getPoint());
							if (column != 0) {
								return;// 只有第一列显示右键菜单
							}
							int row = table.rowAtPoint(e.getPoint());
							if (column >= 0) {
								table.addColumnSelectionInterval(column, column);
							}
							if (row >= 0) {
								table.addRowSelectionInterval(row, row);
							}
							// table.
							pop.show(e.getComponent(), e.getX(), e.getY());
						}
					}
				});

				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("确认 (O)");
				okButton.setMnemonic('O');
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						TableCellEditor edit = table.getCellEditor();
						if (edit != null) {
							edit.stopCellEditing();
						}
						((EditDialogTableModel) table.getModel()).synchData();
						cellEditor.stopCellEditing();

						// 设置值修改
						if (superTable != null) {
							EdiTableModel model = (EdiTableModel) superTable
									.getModel();
							if (model != null)
								// model.fireTableDataChanged();
								model.fireTableCellUpdated(
										superTable
												.convertRowIndexToModel(superRow),
										superTable
												.convertColumnIndexToModel(superColumn));
						}

						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("取消 (C)");
				cancelButton.setMnemonic('C');
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cellEditor.cancelCellEditing();
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
