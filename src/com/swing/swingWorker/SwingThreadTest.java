package com.swing.swingWorker;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SwingThreadTest extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingThreadTest frame = new SwingThreadTest();
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
	public SwingThreadTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JLabel lblNewLabel = new JLabel("正则执行");
		lblNewLabel.setBounds(10, 207, 139, 15);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(SwingThreadTest.this,
						textField.getText());
			}
		});
		btnNewButton.setBounds(71, 102, 93, 23);
		contentPane.add(btnNewButton);

		final JProgressBar progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setBounds(10, 237, 422, 19);
		contentPane.add(progressBar);
		textField = new JTextField();
		textField.setBounds(69, 71, 162, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		/**
		 * 不应该在事件指派线程 上运行耗时任务。否则应用程序将无响应。<br>
		 * ##### 只能在事件指派线程 上访问 Swing 组件。#####
		 **/

		Runnable r = new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						Thread.sleep(500);
						if (progressBar.getValue() == progressBar.getMaximum()) {
							progressBar.setValue(0);
							lblNewLabel.setText("正则执行"
									+ System.currentTimeMillis());
							textField.setText("正则执行"
									+ System.currentTimeMillis());
						}
						progressBar.setValue(progressBar.getValue() + 1);

					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		new Thread(r).start();
	}

}
