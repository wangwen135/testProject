package com.swing.edi.ui.function;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.NumberFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.swing.edi.reportModel.area.cell.function.SumFunction;

/**
 * 描述：sum函数编辑器
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-2-17      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class SumFunctionEditor extends JDialog {

	private static final long serialVersionUID = 4365123803206471420L;
	private final JPanel contentPanel = new JPanel();
	private JSpinner spn_minFraction;
	private JSpinner spn_maxFraction;
	private JSpinner spn_minInteger;
	private JSpinner spn_maxInteger;
	private JComboBox comboBox_RoundingMode;
	private JCheckBox checkBox_useGroup;
	private JTextField textField_target;
	private JTextField textField_source;

	private static SumFunctionEditor sumEditor;
	private SumFunction function;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SumFunctionEditor dialog = new SumFunctionEditor(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized SumFunctionEditor getInstance(Window window) {
		if (sumEditor == null) {
			sumEditor = new SumFunctionEditor(window);
		}
		return sumEditor;
	}

	public void showEdit(SumFunction func) {
		this.function = func;

		spn_maxInteger.setValue(function.getMaxInteger());
		spn_minInteger.setValue(function.getMinInteger());
		spn_maxFraction.setValue(function.getMaxFraction());
		spn_minFraction.setValue(function.getMinFraction());

		comboBox_RoundingMode.setSelectedItem(function.getRound());

		checkBox_useGroup.setSelected(function.isUseGroup());

		// 重置
		textField_source.setText("123456789.123456789");
		textField_target.setText("");

		spn_minFraction.requestFocus();
		setVisible(true);
	}

	/**
	 * Create the dialog.
	 */
	private SumFunctionEditor(Window window) {
		super(window);
		setTitle("编辑sum函数输出数字格式");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 486, 356);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u5C0F\u6570\u90E8\u5206",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 10, 460, 66);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("小数部分最小位数：");
		label.setBounds(10, 29, 120, 15);
		panel.add(label);

		spn_minFraction = new JSpinner();
		spn_minFraction.setBounds(132, 26, 69, 22);
		panel.add(spn_minFraction);
		spn_minFraction.setModel(new SpinnerNumberModel(0, 0, 1000, 1));

		JLabel label_1 = new JLabel("小数部分最大位数：");
		label_1.setBounds(259, 32, 120, 15);
		panel.add(label_1);

		spn_maxFraction = new JSpinner();
		spn_maxFraction.setModel(new SpinnerNumberModel(8, 0, 1000, 1));
		spn_maxFraction.setBounds(381, 29, 69, 22);
		panel.add(spn_maxFraction);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u6574\u6570\u90E8\u5206",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setLayout(null);
		panel_1.setBounds(10, 82, 460, 66);
		contentPanel.add(panel_1);

		JLabel label_2 = new JLabel("整数部分最小位数：");
		label_2.setBounds(10, 29, 120, 15);
		panel_1.add(label_2);

		spn_minInteger = new JSpinner();
		spn_minInteger.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		spn_minInteger.setBounds(132, 26, 69, 22);
		panel_1.add(spn_minInteger);

		JLabel label_3 = new JLabel("整数部分最大位数：");
		label_3.setBounds(259, 32, 120, 15);
		panel_1.add(label_3);

		spn_maxInteger = new JSpinner();
		spn_maxInteger.setModel(new SpinnerNumberModel(20, 0, 100, 1));
		spn_maxInteger.setBounds(381, 29, 69, 22);
		panel_1.add(spn_maxInteger);

		JLabel label_4 = new JLabel("舍入模式：");
		label_4.setBounds(10, 158, 70, 15);
		contentPanel.add(label_4);

		comboBox_RoundingMode = new JComboBox();
		comboBox_RoundingMode.setModel(new DefaultComboBoxModel(new String[] {
				"HALF_UP", "CEILING", "FLOOR" }));
		comboBox_RoundingMode.setRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = -5824386376783140625L;

			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, value,
						index, isSelected, cellHasFocus);
				if ("HALF_UP".equals(value)) {
					setText("四舍五入");
				} else if ("CEILING".equals(value)) {
					setText("向正无限大舍入");
				} else if ("FLOOR".equals(value)) {
					setText("向负无限大舍入");
				}
				return c;
			}
		});

		comboBox_RoundingMode.setBounds(84, 155, 128, 21);
		contentPanel.add(comboBox_RoundingMode);

		checkBox_useGroup = new JCheckBox("启用分组");
		checkBox_useGroup.setBounds(367, 154, 103, 23);
		contentPanel.add(checkBox_useGroup);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "\u6D4B\u8BD5",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 202, 460, 79);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);

		JButton btn_test = new JButton("测试 (T)");
		btn_test.setMnemonic('t');
		btn_test.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Double d;
				try {
					d = Double.valueOf(textField_source.getText());
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(SumFunctionEditor.this,
							"只能输入数字！");
					textField_source.requestFocus();
					return;
				}
				NumberFormat format = NumberFormat.getInstance();
				format.setMaximumFractionDigits((Integer) spn_maxFraction
						.getValue());
				format.setMinimumFractionDigits((Integer) spn_minFraction
						.getValue());
				format.setMaximumIntegerDigits((Integer) spn_maxInteger
						.getValue());
				format.setMinimumIntegerDigits((Integer) spn_minInteger
						.getValue());
				format.setRoundingMode(RoundingMode
						.valueOf((String) comboBox_RoundingMode
								.getSelectedItem()));
				format.setGroupingUsed(checkBox_useGroup.isSelected());
				textField_target.setText(format.format(d));

			}
		});
		btn_test.setBounds(189, 46, 81, 23);
		panel_2.add(btn_test);

		textField_target = new JTextField();
		textField_target.setBounds(282, 47, 165, 21);
		panel_2.add(textField_target);
		textField_target.setColumns(10);

		JLabel lblNewLabel = new JLabel("原数字");
		lblNewLabel.setBounds(12, 27, 54, 15);
		panel_2.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("目标数字");
		lblNewLabel_1.setBounds(282, 27, 54, 15);
		panel_2.add(lblNewLabel_1);

		textField_source = new JTextField();
		textField_source.setText("123456789.123456789");
		textField_source.setColumns(10);
		textField_source.setBounds(12, 47, 165, 21);

		panel_2.add(textField_source);

		// ---------------------------------
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("确认 (O)");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						function.setMaxFraction((Integer) spn_maxFraction
								.getValue());
						function.setMinFraction((Integer) spn_minFraction
								.getValue());
						function.setMaxInteger((Integer) spn_maxInteger
								.getValue());
						function.setMinInteger((Integer) spn_minInteger
								.getValue());
						function.setRound((String) comboBox_RoundingMode
								.getSelectedItem());
						function.setUseGroup(checkBox_useGroup.isSelected());
						setVisible(false);
					}
				});
				okButton.setMnemonic('o');
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
				cancelButton.setMnemonic('c');
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
