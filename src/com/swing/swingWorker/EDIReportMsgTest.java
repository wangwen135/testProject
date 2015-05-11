package com.swing.swingWorker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Random;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;

public class EDIReportMsgTest extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private JTextArea txtraaaBbbCcc;
	private messageGetter mg;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EDIReportMsgTest frame = new EDIReportMsgTest();
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
	public EDIReportMsgTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(10, 35, 84, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 272, 228, 130);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		mg = new messageGetter(textArea, textField);

		JButton btnNewButton = new JButton("开始");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mg.execute();
			}
		});
		btnNewButton.setBounds(305, 34, 93, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("停止");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mg.cancel(true);
			}
		});
		btnNewButton_1.setBounds(408, 34, 93, 23);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("设置");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(104, 34, 93, 23);
		contentPane.add(btnNewButton_2);

		JButton button = new JButton("是否运行");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(mg.isDone());
			}
		});
		button.setBounds(305, 67, 93, 23);
		contentPane.add(button);

		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setBounds(408, 67, 93, 23);
		contentPane.add(btnNewButton_3);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 88, 228, 120);
		contentPane.add(scrollPane_1);

		txtraaaBbbCcc = new JTextArea();
		txtraaaBbbCcc.setText("<html>\r\n<h1>aaa<h1>\r\nbbb\r\nccc\r\n</html>");
		scrollPane_1.setViewportView(txtraaaBbbCcc);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(305, 100, 244, 120);
		contentPane.add(scrollPane_2);

		JEditorPane dtrpnaaaaBbbCcc = new JEditorPane();
		dtrpnaaaaBbbCcc
				.setText("<html>\r\n<h1>aaaa</h1>\r\nbbb\r\nccc\r\n</html>");
		dtrpnaaaaBbbCcc.setContentType("HTML");
		scrollPane_2.setViewportView(dtrpnaaaaBbbCcc);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(305, 268, 244, 134);
		contentPane.add(scrollPane_3);

		final JTextPane textPane = new JTextPane();
		scrollPane_3.setViewportView(textPane);

		JButton btnNewButton_4 = new JButton("New button");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Document d = textPane.getDocument();
				String str = textField_1.getText();
				SimpleAttributeSet attrSet = new SimpleAttributeSet();

				Random r = new Random();
				Color color = Color.RED;
				if (r.nextBoolean()) {
					color = Color.BLUE;
				}

				StyleConstants.setForeground(attrSet, color);
				try {
					d.insertString(d.getLength(), str, attrSet);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_4.setBounds(456, 243, 93, 23);
		contentPane.add(btnNewButton_4);

		textField_1 = new JTextField();
		textField_1.setBounds(305, 244, 141, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

	}

	class messageGetter extends SwingWorker<String, String> {

		public static final String clearCammand = "CLEARCOMMAND";
		private String currentHead;
		JTextArea textArea;
		JTextField textField;

		public messageGetter(JTextArea textArea, JTextField textField) {
			this.textArea = textArea;
			this.textField = textField;

		}

		@Override
		protected String doInBackground() throws Exception {
			while (!isCancelled()) {

				String head = textField.getText();
				if (!head.equals(currentHead)) {
					// 不等，清空，并取老数据

					publish(clearCammand);
					currentHead = head;

					getAllMsg();
				} else {
					// 相等，直接取数
					getNewMSG();
				}

				System.out.println("..............");
				// 休眠
				for (int i = 0; i < 3; i++) {
					Thread.sleep(1000);
					// 比较
					// 如果不同了就跳过
					if (hasChange()) {
						break;
					}
				}
			}
			return "over";
		}

		private boolean hasChange() {
			String head = textField.getText();
			return !head.equals(currentHead);

		}

		private void getAllMsg() {
			publish("aaa", "bbb");
		}

		private void getNewMSG() {
			publish("new msg" + System.currentTimeMillis());
		}

		@Override
		protected void process(List<String> chunks) {
			for (String str : chunks) {
				if (str.equals(clearCammand)) {
					textArea.setText("");
				} else {
					textArea.append(str + "\n");
				}
			}
		}

	}
}
