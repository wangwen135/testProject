package com.sf.module.EDI.EDIImpl.ui.function;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.function.JFConvertFunction;

/**
 * 描述： 简繁转换编辑器
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-7-10      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class JFConvertFunctionEditor extends
		FunctionAbstractDialog<JFConvertFunction> {

	private static final long serialVersionUID = -3819239240008520003L;
	private static JFConvertFunctionEditor sqfEditor;

	private JRadioButton rdbtn_2f;
	private JRadioButton rdbtn_2j;

	public static void main(String[] args) {
		try {
			JFConvertFunctionEditor dialog = new JFConvertFunctionEditor(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized JFConvertFunctionEditor getInstance(
			Window windows) {
		if (sqfEditor == null) {
			sqfEditor = new JFConvertFunctionEditor(windows);
		}
		return sqfEditor;
	}

	@Override
	public void showEdit(JFConvertFunction func) {
		this.function = func;
		modelToView();
		rdbtn_2f.requestFocus();
		setVisible(true);
	}

	@Override
	public void modelToView() {
		rdbtn_2f.setSelected(function.isJ2f());
		rdbtn_2j.setSelected(!function.isJ2f());
	}

	@Override
	public void viewToModel() {
		function.setJ2f(rdbtn_2f.isSelected());
	}

	/**
	 * Create the dialog.
	 */
	private JFConvertFunctionEditor(Window windows) {
		super(windows);
		setResizable(false);
		setTitle("设置简体繁体转换");
		setModal(true);
		setBounds(100, 100, 285, 182);
		setLocationRelativeTo(null);

		contentPanel.setLayout(null);

		ButtonGroup bg = new ButtonGroup();

		rdbtn_2f = new JRadioButton("转换为繁体");
		rdbtn_2f.setSelected(true);
		rdbtn_2f.setBounds(22, 28, 121, 23);
		contentPanel.add(rdbtn_2f);
		bg.add(rdbtn_2f);

		rdbtn_2j = new JRadioButton("转换为简体");
		rdbtn_2j.setBounds(152, 28, 121, 23);
		contentPanel.add(rdbtn_2j);
		bg.add(rdbtn_2j);

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
