package com.sf.module.EDI.EDIImpl.ui.function;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.function.FillFunction;

/**
 * 描述：填充函数编辑器
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-4-9      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class FillFunctionEditor extends FunctionAbstractDialog<FillFunction> {

	private static final long serialVersionUID = -2345657750084214118L;

	private JSpinner spn_lenght;
	private JRadioButton rdbtnLeft;
	private JRadioButton rdbtnRight;
	private JCheckBox checkBox_chinese;
	private JTextField textField_fillCharacter;

	private static FillFunctionEditor fillfEditor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FillFunctionEditor dialog = new FillFunctionEditor(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized FillFunctionEditor getInstance(Window windows) {
		if (fillfEditor == null) {
			fillfEditor = new FillFunctionEditor(windows);
		}
		return fillfEditor;
	}

	@Override
	public void showEdit(FillFunction func) {
		this.function = func;
		modelToView();
		spn_lenght.requestFocus();
		setVisible(true);
	}

	@Override
	public void modelToView() {
		if (function.isLeftFill()) {
			rdbtnLeft.setSelected(true);
		} else {
			rdbtnRight.setSelected(true);
		}
		spn_lenght.setValue(function.getLenght());
		checkBox_chinese.setSelected(function.isCheckChinese());
		textField_fillCharacter.setText(function.getFillCharacter());
	}

	@Override
	public void viewToModel() {
		function.setLeftFill(rdbtnLeft.isSelected());
		function.setLenght((Integer) spn_lenght.getValue());
		function.setCheckChinese(checkBox_chinese.isSelected());
		function.setFillCharacter(textField_fillCharacter.getText());
	}

	/**
	 * Create the dialog.
	 */
	private FillFunctionEditor(Window windows) {
		super(windows);
		setResizable(false);
		setTitle("设置填充条件");
		setModal(true);
		setBounds(100, 100, 276, 259);
		setLocationRelativeTo(null);

		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("长  度：");
		lblNewLabel.setBounds(24, 62, 60, 15);
		contentPanel.add(lblNewLabel);

		spn_lenght = new JSpinner();
		spn_lenght.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer
				.valueOf(0), null, Integer.valueOf(1)));
		spn_lenght.setBounds(94, 59, 84, 22);
		contentPanel.add(spn_lenght);

		ButtonGroup bg = new ButtonGroup();
		rdbtnLeft = new JRadioButton("左补");
		bg.add(rdbtnLeft);
		rdbtnLeft.setSelected(true);
		rdbtnLeft.setBounds(97, 21, 58, 23);
		contentPanel.add(rdbtnLeft);

		rdbtnRight = new JRadioButton("右补");
		bg.add(rdbtnRight);
		rdbtnRight.setBounds(178, 21, 58, 23);
		contentPanel.add(rdbtnRight);

		checkBox_chinese = new JCheckBox("判断中文（中文占两个长度）");
		checkBox_chinese.setSelected(true);
		checkBox_chinese.setBounds(24, 98, 222, 23);
		contentPanel.add(checkBox_chinese);

		JLabel label = new JLabel("填充字符：");
		label.setBounds(24, 139, 66, 15);
		contentPanel.add(label);

		textField_fillCharacter = new JTextField();
		textField_fillCharacter.setBounds(93, 136, 153, 21);
		contentPanel.add(textField_fillCharacter);
		textField_fillCharacter.setColumns(10);

		JLabel label_1 = new JLabel("填充方向：");
		label_1.setBounds(24, 25, 66, 15);
		contentPanel.add(label_1);
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
