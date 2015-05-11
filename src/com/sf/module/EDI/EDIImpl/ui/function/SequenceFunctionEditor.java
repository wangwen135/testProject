package com.sf.module.EDI.EDIImpl.ui.function;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.function.SequenceFunction;

/**
 * 描述：序列函数编辑器
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-2-8      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class SequenceFunctionEditor extends
		FunctionAbstractDialog<SequenceFunction> {

	private static final long serialVersionUID = 5908903466952857053L;

	private JSpinner spn_start;
	private JSpinner spn_increment;
	private JSpinner spn_maxValue;

	private static SequenceFunctionEditor sqfEditor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SequenceFunctionEditor dialog = new SequenceFunctionEditor(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized SequenceFunctionEditor getInstance(Window windows) {
		if (sqfEditor == null) {
			sqfEditor = new SequenceFunctionEditor(windows);
		}
		return sqfEditor;
	}

	@Override
	public void showEdit(SequenceFunction func) {
		this.function = func;
		modelToView();
		spn_start.requestFocus();
		setVisible(true);
	}

	@Override
	public void modelToView() {
		spn_start.setValue(function.getStart());
		spn_increment.setValue(function.getIncrement());
		spn_maxValue.setValue(function.getMaxValue());

	}

	@Override
	public void viewToModel() {
		Integer start = (Integer) spn_start.getValue();
		Integer increment = (Integer) spn_increment.getValue();
		Integer maxValue = (Integer) spn_maxValue.getValue();

		function.setStart(start);
		function.setIncrement(increment);
		function.setMaxValue(maxValue);
	}

	/**
	 * Create the dialog.
	 */
	private SequenceFunctionEditor(Window windows) {
		super(windows);
		setResizable(false);
		setTitle("设置序列生成条件");
		setModal(true);
		setBounds(100, 100, 285, 222);
		setLocationRelativeTo(null);

		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("起始值：");
		lblNewLabel.setBounds(40, 29, 54, 15);
		contentPanel.add(lblNewLabel);

		JLabel label = new JLabel("增  量：");
		label.setBounds(40, 69, 54, 15);
		contentPanel.add(label);

		JLabel label_1 = new JLabel("最大值：");
		label_1.setBounds(40, 109, 54, 15);
		contentPanel.add(label_1);

		spn_start = new JSpinner();
		spn_start.setModel(new SpinnerNumberModel(Integer.valueOf(0), null,
				null, Integer.valueOf(1)));
		spn_start.setBounds(104, 26, 110, 22);
		contentPanel.add(spn_start);

		spn_increment = new JSpinner();
		spn_increment.setModel(new SpinnerNumberModel(Integer.valueOf(1), null,
				null, Integer.valueOf(1)));
		spn_increment.setBounds(104, 66, 110, 22);
		contentPanel.add(spn_increment);

		spn_maxValue = new JSpinner();
		spn_maxValue.setModel(new SpinnerNumberModel(Integer.MAX_VALUE, null,
				null, Integer.valueOf(1)));
		spn_maxValue.setBounds(104, 106, 110, 22);
		contentPanel.add(spn_maxValue);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("确认 (O)");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						Integer start = (Integer) spn_start.getValue();
						Integer increment = (Integer) spn_increment.getValue();
						Integer maxValue = (Integer) spn_maxValue.getValue();
						if (maxValue < (start + increment)) {
							JOptionPane.showMessageDialog(
									SequenceFunctionEditor.this,
									"最大值不能小于（起始值+增量）");
							spn_maxValue.requestFocus();
							return;
						}

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
