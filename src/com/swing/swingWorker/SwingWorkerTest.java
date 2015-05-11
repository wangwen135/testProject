package com.swing.swingWorker;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.AbstractListModel;
import javax.swing.JTextField;

public class SwingWorkerTest extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingWorkerTest frame = new SwingWorkerTest();
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
	public SwingWorkerTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 544, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JLabel lblNewLabel = new JLabel("结果");
		lblNewLabel.setBounds(92, 59, 220, 15);
		contentPane.add(lblNewLabel);

		final JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] { "1", "2", "3", "4", "5", "6", "7",
					"8", "9", "10", "11" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(92, 154, 93, 200);
		contentPane.add(list);

		class MeaningOfLifeFinder extends SwingWorker<String, Object> {
			@Override
			public String doInBackground() {
				return findTheMeaningOfLife();
			}

			@Override
			protected void done() {
				try {
					lblNewLabel.setText(get());
				} catch (Exception ignore) {

				}
			}

			protected void process(List<Object> chunks) {
				for (Object object : chunks) {
					list.setSelectedIndex((Integer) object);
				}
			};

			String findTheMeaningOfLife() {
				for (int i = 0; i < 10; i++) {
					try {
						System.out.println("Thread.sleep(500)");
						publish(i);
						Thread.sleep(500);

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				return new Date().toString();
			}
		}

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MeaningOfLifeFinder().execute();
			}
		});
		btnNewButton.setBounds(92, 100, 93, 23);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(224, 104, 88, 15);
		contentPane.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(16, 101, 66, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 10; i++) {
					try {
						System.out.println("Thread.sleep(500)");
						list.setSelectedIndex(i);
						Thread.sleep(500);

					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton_1.setBounds(342, 100, 93, 23);
		contentPane.add(btnNewButton_1);

	}
}
