package com.sf.module.EDI.EDIImpl.ui.function;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.function.CountFunction;

/**
 * 描述：count函数编辑器
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-3-30      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class CountFunctionEditor extends FunctionAbstractDialog<CountFunction> {

	private static final long serialVersionUID = 8402560782678218769L;

	private JCheckBox checkBox_ignoreNull;

	private JCheckBox checkBox_distinct;

	private static CountFunctionEditor editor;

	public static synchronized CountFunctionEditor getInstance(Window windows) {
		if (editor == null) {
			editor = new CountFunctionEditor(windows);
		}
		return editor;
	}

	@Override
	public void showEdit(CountFunction func) {
		this.function = func;
		modelToView();
		checkBox_ignoreNull.requestFocus();
		setVisible(true);
	}

	@Override
	public void modelToView() {
		checkBox_ignoreNull.setSelected(function.isIgnoreNull());
		checkBox_distinct.setSelected(function.isDistinct());
	}

	@Override
	public void viewToModel() {
		function.setIgnoreNull(checkBox_ignoreNull.isSelected());
		function.setDistinct(checkBox_distinct.isSelected());
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CountFunctionEditor dialog = new CountFunctionEditor(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	private CountFunctionEditor(Window windows) {
		super(windows);
		setModal(true);
		setResizable(false);
		setTitle("设置count函数计算条件");
		setBounds(100, 100, 293, 195);
		setLocationRelativeTo(null);

		contentPanel.setLayout(null);
		{
			checkBox_ignoreNull = new JCheckBox("忽略空值 (包括 \"\" )");
			checkBox_ignoreNull.setBounds(33, 41, 248, 23);
			contentPanel.add(checkBox_ignoreNull);
		}

		checkBox_distinct = new JCheckBox("去除重复值 (重复的值只计算一次)");
		checkBox_distinct.setBounds(33, 80, 248, 23);
		contentPanel.add(checkBox_distinct);

		JLabel label = new JLabel("请将需要统计的字段拖入函数中");
		label.setBounds(10, 10, 267, 15);
		contentPanel.add(label);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("确定(O)");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						viewToModel();
						setVisible(false);
					}
				});
				okButton.setMnemonic('o');
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("取消(C)");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setMnemonic('c');
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
