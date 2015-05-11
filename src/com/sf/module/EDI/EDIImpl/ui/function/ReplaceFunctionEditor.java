package com.sf.module.EDI.EDIImpl.ui.function;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.function.ReplaceFunction;

/**
 * 描述：替换函数编辑器
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-5-25      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class ReplaceFunctionEditor extends
		FunctionAbstractDialog<ReplaceFunction> {

	private static final long serialVersionUID = -2345657750084214118L;

	private static ReplaceFunctionEditor editor;

	private JRadioButton rdbtn_replaceAll;
	private JRadioButton rdbtn_replaceFirst;
	private JCheckBox checkBox_useRegex;
	private JTextField textField_replacement;

	private JTextField textField_regex;
	private JTextField textField_TestSrc;
	private JTextField textField_TestDesc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ReplaceFunctionEditor dialog = new ReplaceFunctionEditor(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized ReplaceFunctionEditor getInstance(Window windows) {
		if (editor == null) {
			editor = new ReplaceFunctionEditor(windows);
		}
		return editor;
	}

	@Override
	public void showEdit(ReplaceFunction func) {
		this.function = func;
		textField_TestSrc.setText("");
		textField_TestDesc.setText("");
		modelToView();
		textField_regex.requestFocus();
		setVisible(true);
	}

	@Override
	public void modelToView() {
		if (function.getReplaceType().equals(ReplaceFunction.REPLACE_ALL)) {
			rdbtn_replaceAll.setSelected(true);
		} else {
			rdbtn_replaceFirst.setSelected(true);
		}
		checkBox_useRegex.setSelected(function.isUseRegex());

		textField_regex.setText(function.getRegex());
		textField_replacement.setText(function.getReplacement());
	}

	@Override
	public void viewToModel() {
		if (rdbtn_replaceAll.isSelected()) {
			function.setReplaceType(ReplaceFunction.REPLACE_ALL);
		} else {
			function.setReplaceType(ReplaceFunction.REPLACE_FIRST);
		}
		function.setUseRegex(checkBox_useRegex.isSelected());

		function.setRegex(textField_regex.getText());

		function.setReplacement(textField_replacement.getText());
	}

	/**
	 * <pre>
	 * 测试
	 * </pre>
	 */
	private void test() {
		try {
			String src = textField_TestSrc.getText();
			if ("".equals(src)) {
				textField_TestDesc.setText("");
				return;
			}
			// 进行计算
			String tregex = textField_regex.getText();
			if (!checkBox_useRegex.isSelected()) {// 不启用正则表达式
				tregex = Pattern.quote(tregex);
			}
			String value = "";
			String replacement = textField_replacement.getText();
			if (rdbtn_replaceAll.isSelected()) {
				value = src.replaceAll(tregex, replacement);
			} else {
				value = src.replaceFirst(tregex, replacement);
			}

			textField_TestDesc.setText(value);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,
					"计算错误，错误信息：\r\n" + e.getMessage(), "错误",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Create the dialog.
	 */
	private ReplaceFunctionEditor(Window windows) {
		super(windows);
		setResizable(false);
		setTitle("设置替换条件");
		setModal(true);
		setBounds(100, 100, 374, 300);
		setLocationRelativeTo(null);

		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("查找字符串：");
		lblNewLabel.setBounds(20, 73, 82, 15);
		contentPanel.add(lblNewLabel);

		ButtonGroup bg = new ButtonGroup();
		rdbtn_replaceAll = new JRadioButton("全部替换");
		bg.add(rdbtn_replaceAll);
		rdbtn_replaceAll.setSelected(true);
		rdbtn_replaceAll.setBounds(92, 16, 81, 23);
		contentPanel.add(rdbtn_replaceAll);

		rdbtn_replaceFirst = new JRadioButton("只替换第一个字符串");
		bg.add(rdbtn_replaceFirst);
		rdbtn_replaceFirst.setBounds(183, 16, 154, 23);
		contentPanel.add(rdbtn_replaceFirst);

		checkBox_useRegex = new JCheckBox("启用正则表达式");
		checkBox_useRegex.setSelected(true);
		checkBox_useRegex.setBounds(20, 41, 222, 23);
		contentPanel.add(checkBox_useRegex);

		JLabel label = new JLabel("替换字符串：");
		label.setBounds(20, 103, 82, 15);
		contentPanel.add(label);

		textField_replacement = new JTextField();
		textField_replacement.setBounds(113, 100, 220, 21);
		contentPanel.add(textField_replacement);
		textField_replacement.setColumns(10);

		JLabel label_1 = new JLabel("替换方式：");
		label_1.setBounds(20, 20, 66, 15);
		contentPanel.add(label_1);

		textField_regex = new JTextField();
		textField_regex.setColumns(10);
		textField_regex.setBounds(113, 70, 220, 21);
		contentPanel.add(textField_regex);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u6D4B\u8BD5",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 138, 348, 98);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("测试用的符串");
		lblNewLabel_1.setBounds(10, 30, 110, 15);
		panel.add(lblNewLabel_1);

		textField_TestSrc = new JTextField();
		textField_TestSrc.setBounds(10, 55, 110, 21);
		panel.add(textField_TestSrc);
		textField_TestSrc.setColumns(10);

		JButton btn_test = new JButton("测试(T)");
		btn_test.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				test();
			}
		});
		btn_test.setMnemonic('T');
		btn_test.setBounds(130, 54, 88, 23);
		panel.add(btn_test);

		textField_TestDesc = new JTextField();
		textField_TestDesc.setBounds(228, 55, 110, 21);
		panel.add(textField_TestDesc);
		textField_TestDesc.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("替换之后的字符串");
		lblNewLabel_2.setBounds(228, 30, 110, 15);
		panel.add(lblNewLabel_2);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("确认 (O)");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						viewToModel();
						setVisible(false);
					}
				});
				okButton.setMnemonic('O');
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("取消 (C)");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
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
