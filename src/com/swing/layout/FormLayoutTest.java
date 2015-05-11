package com.swing.layout;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class FormLayoutTest extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormLayoutTest frame = new FormLayoutTest();
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
	public FormLayoutTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JButton btnNewButton = new JButton("New button");
		contentPane.add(btnNewButton, "2, 3");
		
		textField = new JTextField();
		contentPane.add(textField, "8, 3, fill, default");
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("New button");
		contentPane.add(btnNewButton_1, "6, 6");
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		contentPane.add(lblNewLabel_1, "2, 8");
		
		JLabel lblNewLabel = new JLabel("New label");
		contentPane.add(lblNewLabel, "4, 8");
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		contentPane.add(lblNewLabel_2, "6, 8");
		
		textField_2 = new JTextField();
		contentPane.add(textField_2, "2, 10, fill, default");
		textField_2.setColumns(10);
		
		textField_1 = new JTextField();
		contentPane.add(textField_1, "4, 10, fill, default");
		textField_1.setColumns(10);
		
		textField_3 = new JTextField();
		contentPane.add(textField_3, "6, 10, fill, default");
		textField_3.setColumns(10);
	}

}
