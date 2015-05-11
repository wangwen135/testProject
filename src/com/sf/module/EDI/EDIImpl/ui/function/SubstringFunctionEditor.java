package com.sf.module.EDI.EDIImpl.ui.function;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.function.SubstringFunction;

/**
 * 描述：截取函数编辑器
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-4-10      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class SubstringFunctionEditor extends
		FunctionAbstractDialog<SubstringFunction> {

	private static final long serialVersionUID = 2127702917907879777L;

	private JSpinner spn_lenght;
	private JSpinner spn_offset;
	private JRadioButton rdbtnLeft;
	private JRadioButton rdbtnRight;
	private JRadioButton rdbtnDefine;
	private JCheckBox checkBox_chinese;

	private static SubstringFunctionEditor subEditor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SubstringFunctionEditor dialog = new SubstringFunctionEditor(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized SubstringFunctionEditor getInstance(
			Window windows) {
		if (subEditor == null) {
			subEditor = new SubstringFunctionEditor(windows);
		}
		return subEditor;
	}

	@Override
	public void showEdit(SubstringFunction func) {
		this.function = func;
		modelToView();
		spn_lenght.requestFocus();
		setVisible(true);
	}

	@Override
	public void modelToView() {
		int subType = function.getSubType();
		if (subType == SubstringFunction.SUB_TYPE_LEFT) {
			rdbtnLeft.setSelected(true);
		} else if (subType == SubstringFunction.SUB_TYPE_RIGHT) {
			rdbtnRight.setSelected(true);
		} else {
			rdbtnDefine.setSelected(true);
		}

		spn_lenght.setValue(function.getLength());
		spn_offset.setValue(function.getOffset());
		checkBox_chinese.setSelected(function.isCheckChinese());
	}

	@Override
	public void viewToModel() {
		int subType = SubstringFunction.SUB_TYPE_LEFT;
		if (rdbtnLeft.isSelected()) {
			subType = SubstringFunction.SUB_TYPE_LEFT;
		} else if (rdbtnRight.isSelected()) {
			subType = SubstringFunction.SUB_TYPE_RIGHT;
		} else {
			subType = SubstringFunction.SUB_TYPE_DEFINE;
		}

		function.setSubType(subType);

		function.setLength((Integer) spn_lenght.getValue());
		function.setOffset((Integer) spn_offset.getValue());
		function.setCheckChinese(checkBox_chinese.isSelected());

	}

	/**
	 * Create the dialog.
	 */
	private SubstringFunctionEditor(Window windows) {
		super(windows);
		setResizable(false);
		setTitle("设置截取条件");
		setModal(true);
		setBounds(100, 100, 370, 242);
		setLocationRelativeTo(null);

		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("截取长度：");
		lblNewLabel.setBounds(200, 116, 68, 15);
		contentPanel.add(lblNewLabel);

		spn_lenght = new JSpinner();
		spn_lenght.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer
				.valueOf(0), null, Integer.valueOf(1)));
		spn_lenght.setBounds(270, 113, 84, 22);
		contentPanel.add(spn_lenght);

		ButtonGroup bg = new ButtonGroup();

		checkBox_chinese = new JCheckBox("判断中文（中文占两个长度）");
		checkBox_chinese.setSelected(true);
		checkBox_chinese.setBounds(20, 73, 248, 23);
		contentPanel.add(checkBox_chinese);

		JLabel label = new JLabel("起始位置：");
		label.setBounds(20, 116, 68, 15);
		contentPanel.add(label);

		spn_offset = new JSpinner();
		spn_offset.setEnabled(false);
		spn_offset.setBounds(90, 113, 60, 22);
		contentPanel.add(spn_offset);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u622A\u53D6\u65B9\u5F0F",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 10, 344, 53);
		contentPanel.add(panel);
		panel.setLayout(null);
		rdbtnLeft = new JRadioButton("从左到右截取");
		rdbtnLeft.setBounds(8, 27, 103, 23);
		panel.add(rdbtnLeft);
		bg.add(rdbtnLeft);
		rdbtnLeft.setSelected(true);

		rdbtnRight = new JRadioButton("从右到左截取");
		rdbtnRight.setBounds(122, 27, 103, 23);
		panel.add(rdbtnRight);
		bg.add(rdbtnRight);

		rdbtnDefine = new JRadioButton("指定范围截取");
		rdbtnDefine.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (rdbtnDefine.isSelected()) {
					spn_offset.setEnabled(true);
				} else {
					spn_offset.setEnabled(false);
				}
			}
		});

		rdbtnDefine.setBounds(235, 27, 103, 23);
		panel.add(rdbtnDefine);
		bg.add(rdbtnDefine);
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
