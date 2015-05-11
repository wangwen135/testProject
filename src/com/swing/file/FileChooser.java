package com.swing.file;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileChooser frame = new FileChooser();
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
	public FileChooser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(26, 62, 278, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("选择文件");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser("c://");

				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setDialogTitle("请选择[理货清单表]文件保存目录");
				int returnVal = chooser.showDialog(FileChooser.this, "选择");
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose to open this file: "
							+ chooser.getSelectedFile().getName());
					File f = chooser.getSelectedFile();
					File f2 = new File(f, "aaa.txt");
					System.out.println(f2.getAbsolutePath());
					textField.setText(f2.getAbsolutePath());
				}

			}
		});
		btnNewButton.setBounds(26, 29, 93, 23);
		contentPane.add(btnNewButton);

		JButton btntxt = new JButton("选择TXT文件");
		btntxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"文本文档(*.txt)", "TXT");// 文本文档(*.txt)
				chooser.setFileFilter(filter);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setMultiSelectionEnabled(false);
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int returnVal = chooser.showSaveDialog(FileChooser.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File f = chooser.getSelectedFile();
					String name = f.getName();
					if (!name.toUpperCase().endsWith(".TXT")) {
						f = new File(f.getAbsolutePath() + ".txt");
					}
					System.out.println(name);
					System.out.println(f);
				}

			}
		});
		btntxt.setBounds(26, 152, 104, 23);
		contentPane.add(btntxt);

		textField_1 = new JTextField();
		textField_1.setBounds(26, 185, 278, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
	}
}
