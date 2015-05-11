package com.swing.comboBox;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalComboBoxEditor;

public class ComboBoxTest extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JComboBox comboBox;
	private JTextField textField_1;
	private JComboBox comboBox_1;
	private JComboBox comboBox_CRLF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComboBoxTest frame = new ComboBoxTest();
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
	public ComboBoxTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(197, 18, 110, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.getEditor().getEditorComponent()
				.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent e) {
						System.out.println("获取焦点");
					}
				});
		comboBox.setBounds(80, 61, 110, 21);
		contentPane.add(comboBox);

		textField_1 = new JTextField();
		textField_1.setBounds(80, 107, 110, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnNewButton.setBounds(80, 17, 93, 23);
		contentPane.add(btnNewButton);

		comboBox_1 = new JComboBox();
		comboBox_1.setEditable(true);
		comboBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					System.out.println("aaaaaaaaaaaa");
				}
			}
		});
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "I", "E",
				"IE" }));
		comboBox_1.setBounds(227, 61, 110, 21);
		contentPane.add(comboBox_1);

		JLabel lblNewLabel = new JLabel("渲染器-编辑器");
		lblNewLabel.setBounds(80, 153, 151, 15);
		contentPane.add(lblNewLabel);

		comboBox_CRLF = new JComboBox();
		comboBox_CRLF.setBounds(80, 178, 151, 21);
		contentPane.add(comboBox_CRLF);

		comboBox_CRLF.setEditable(true);
		// \r 回车 ; \n 换行 ; \r\n 回车换行; tab \t

		comboBox_CRLF.setModel(new DefaultComboBoxModel(new String[] { "\r\n",
				"\r", "\n", "\t" }));
		comboBox_CRLF.setRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = -1448225903336491091L;

			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, value,
						index, isSelected, cellHasFocus);
				if ("\r\n".equals(value)) {
					setText("回车换行");
				} else if ("\r".equals(value)) {
					setText("回车");
				} else if ("\n".equals(value)) {
					setText("换行");
				} else if ("\t".equals(value)) {
					setText("TAB");
				}
				return c;
			}
		});

		// comboBox_2.setEditor(new BasicComboBoxEditor());

		comboBox_CRLF.setEditor(new MetalComboBoxEditor() {
			
		});

		
		
		JButton btnGetvalue = new JButton("getValue");
		btnGetvalue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println( comboBox_CRLF.getSelectedItem());
			}
		});
		btnGetvalue.setBounds(267, 177, 93, 23);
		contentPane.add(btnGetvalue);

		System.out.println(comboBox_CRLF.getEditor());
	}
}
