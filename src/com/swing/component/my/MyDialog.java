package com.swing.component.my;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1052577790684558792L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MyDialog dialog = new MyDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MyDialog() {
		//setModalityType(ModalityType.APPLICATION_MODAL);
		//setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MyDialog.class.getResource("/javax/swing/plaf/metal/icons/ocean/menu.gif")));
		setTitle("编辑框");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblNewLabel = new JLabel("New label");
			contentPanel.add(lblNewLabel);
		}
		{
			textField_1 = new JTextField();
			contentPanel.add(textField_1);
			textField_1.setColumns(10);
		}
		{
			lblNewLabel_1 = new JLabel("New label");
			contentPanel.add(lblNewLabel_1);
		}
		{
			textField = new JTextField();
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			btnNewButton = new JButton("New button");
			contentPanel.add(btnNewButton);
		}
		{
			textField_3 = new JTextField();
			contentPanel.add(textField_3);
			textField_3.setColumns(10);
		}
		{
			textField_2 = new JTextField();
			contentPanel.add(textField_2);
			textField_2.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("New label");
			contentPanel.add(lblNewLabel_2);
		}
		{
			JButton btnNewButton_3 = new JButton("New button");
			contentPanel.add(btnNewButton_3);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("New label");
			contentPanel.add(lblNewLabel_3);
		}
		{
			textField_4 = new JTextField();
			contentPanel.add(textField_4);
			textField_4.setColumns(10);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("New label");
			contentPanel.add(lblNewLabel_4);
		}
		{
			JComboBox comboBox = new JComboBox();
			contentPanel.add(comboBox);
		}
		contentPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textField_1, lblNewLabel_1, textField, btnNewButton, lblNewLabel}));
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnNewButton_2 = new JButton("New button");
				buttonPane.add(btnNewButton_2);
			}
			{
				JButton btnNewButton_1 = new JButton("New button");
				buttonPane.add(btnNewButton_1);
			}
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
