package com.swing.component.table;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

public class CtrlCellEditor extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CtrlCellEditor frame = new CtrlCellEditor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CtrlCellEditor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(72, 128, 441, 211);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {
				{ null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, { null, null, null, null }, },
				new String[] { "myObject", "boolean", "String", "int" }) {
			Class[] columnTypes = new Class[] { Object.class, Boolean.class,
					String.class, Integer.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		table.setDefaultEditor(Object.class, new myCellEdirot());
		table.getColumn("myObject").setCellEditor(new myCellEdirot());
		scrollPane.setViewportView(table);

		JButton button = new JButton("控制编辑器");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(72, 38, 118, 23);
		contentPane.add(button);

		JButton button_1 = new JButton("加载数据");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setBounds(282, 38, 118, 23);
		contentPane.add(button_1);

		JButton btnx = new JButton("退出（X）");
		btnx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// System.exit(0);
				dispose();
			}
		});
		btnx.setMnemonic('x');
		btnx.setBounds(439, 391, 118, 23);
		contentPane.add(btnx);

		JButton button_2 = new JButton("修改辑器");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableCellEditor editor = table.getColumn("myObject")
						.getCellEditor();
				myCellEdirot myedit = (myCellEdirot) editor;
				myedit.useCheckBox(!myedit.isCheckBox());
			}
		});
		button_2.setBounds(72, 73, 93, 23);
		contentPane.add(button_2);
	}

}

class myCellEdirot extends AbstractCellEditor implements TableCellEditor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JCheckBox checkBox = new JCheckBox();
	private JTextField textField = new JTextField();
	private boolean isCheckBox = true;

	public void useCheckBox(boolean use) {
		isCheckBox = use;
	}

	public boolean isCheckBox() {
		return isCheckBox;
	}

	@Override
	public Object getCellEditorValue() {
		if (isCheckBox) {
			return checkBox.isSelected();
		} else {
			return textField.getText();
		}
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		value = value == null ? "" : value;
		if (isCheckBox) {
			if ("true".equals(value)) {
				checkBox.setSelected(true);
			} else {
				checkBox.setSelected(false);
			}
			return checkBox;
		} else {
			textField.setText(value.toString());
			return textField;
		}
	}

}
