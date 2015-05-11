package com.sf.module.EDI.EDIImpl.ui.table.functionEditDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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

import com.sf.module.EDI.EDIImpl.dragDrop.FunctionEditDialogTableTransferHandler;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.ICell;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.function.IFunction;
import com.sf.module.EDI.EDIImpl.ui.table.EdiTableCellEditor;
import com.sf.module.EDI.EDIImpl.ui.table.EdiTableModel;
import com.sf.module.EDI.EDIImpl.ui.table.TableUtils;

/**
 * 描述： 函数编辑框
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-5-26      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class FunctionEditDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 416788629729200233L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;

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

		this.cell = cell;
		FunctionEditDialogTableModel dm = new FunctionEditDialogTableModel(cell);
		table.setModel(dm);
		// table.getColumnModel().getColumn(1).setPreferredWidth(0);
		// table.getColumnModel().getColumn(1).setWidth(0);
		table.getColumnModel().getColumn(1).setMaxWidth(10);
		// table.getColumnModel().getColumn(1).setMinWidth(0);
		// table.getColumnModel().getColumn(2).setPreferredWidth(0);
		// table.getColumnModel().getColumn(2).setWidth(0);
		table.getColumnModel().getColumn(2).setMaxWidth(10);
		// table.getColumnModel().getColumn(2).setMinWidth(0);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);

		this.setTitle("正在编辑函数  row[" + (cell.getRow() + 1) + "] column["
				+ TableUtils.convertIntegerToString(cell.getColumn()) + "]");
		setVisible(true);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FunctionEditDialog dialog = new FunctionEditDialog(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FunctionEditDialog(Window windows, EdiTableCellEditor editor) {

		super(windows);
		this.cellEditor = editor;

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
				cellEditor.cancelCellEditing();
			}
		});

		setBounds(100, 100, 380, 400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				table = new JTable() {
					private static final long serialVersionUID = 628722428712957443L;

					@Override
					public String getToolTipText(MouseEvent event) {

						java.awt.Point p = event.getPoint();
						int rowIndex = rowAtPoint(p);

						int colIndex = columnAtPoint(p);

						if (rowIndex == -1 || colIndex == -1 || colIndex == 0)
							return super.getToolTipText(event);
						if (colIndex == 1) {
							boolean canEdit = (Boolean) getValueAt(rowIndex,
									colIndex);
							if (canEdit) {
								return "可以编辑";
							} else {
								return "不可以编辑";
							}
						} else if (colIndex == 2) {
							boolean canContain = (Boolean) getValueAt(rowIndex,
									colIndex);
							if (canContain) {
								return "可以包含变量、常量";
							} else {
								return "不可以包含变量、常量";
							}
						}

						return super.getToolTipText(event);
					}
				};

				table.setSelectionBackground(new Color(184, 207, 229));
				table.setRowHeight(30);
				table.setFillsViewportHeight(true);
				// table.setCellSelectionEnabled(true);
				// table.setColumnSelectionAllowed(true);
				// table.setRowSelectionAllowed(true);
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				// 设置渲染器和编辑器
				table.setDefaultEditor(IFunction.class, new FunctionEditor(
						windows));
				table.setDefaultRenderer(IFunction.class,
						new FunctionRenderer());

				// 表格拖动支持
				table.setDragEnabled(true);
				table.setDropMode(DropMode.USE_SELECTION);
				table.setTransferHandler(new FunctionEditDialogTableTransferHandler(
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
									table.setValueAt(null, row, 0);
								}
							}
						});
						pop.add(itemDel);
					}

					@Override
					public void mouseClicked(MouseEvent e) {
						TableCellEditor editor = table.getCellEditor();
						if (editor != null) {
							editor.stopCellEditing();
						}
						if (e.getClickCount() == 1
								&& SwingUtilities.isRightMouseButton(e)) {
							int row = table.rowAtPoint(e.getPoint());
							if (row == -1) {
								return;// 不在空白的地方显示右键菜单
							}
							int column = table.columnAtPoint(e.getPoint());

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
				JButton okButton = new JButton("确定(O)");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						TableCellEditor edit = table.getCellEditor();
						if (edit != null) {
							edit.stopCellEditing();
						}
						((FunctionEditDialogTableModel) table.getModel())
								.synchData();
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
				okButton.setMnemonic('O');
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("取消(C)");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cellEditor.cancelCellEditing();
						setVisible(false);
					}
				});
				cancelButton.setMnemonic('C');
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
