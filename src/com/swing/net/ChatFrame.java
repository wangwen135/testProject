package com.swing.net;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChatFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private UserInfo user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatFrame frame = new ChatFrame(null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param user
	 * @param object
	 */
	public ChatFrame(Object object, UserInfo user) {
		super("与" + user.getName() + "聊天中");
		// setIconImage(Toolkit
		// .getDefaultToolkit()
		// .getImage(""));
		// "ico/" + userInfo.getIcon() + ".gif"

		setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						ChatFrame.class
								.getResource("/com/sun/java/swing/plaf/windows/icons/Inform.gif")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 503, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		JLabel lblNewLabel = new JLabel("输入信息：");
		panel.add(lblNewLabel);

		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(29);

		JButton btnSend = new JButton("发送(S)");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//发送的代码还没有写
				addString(textField.getText());
				textField.setText("");
			}
		});
		btnSend.setMnemonic('s');
		panel.add(btnSend);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new SoftBevelBorder(BevelBorder.LOWERED,
				null, null, null, null));
		contentPane.add(scrollPane, BorderLayout.CENTER);

		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setRows(10);
		textArea.setColumns(10);
		scrollPane.setViewportView(textArea);
	}

	public void addString(String str) {
		if (textArea.getText().length() > 0)
			textArea.append("\r\n");
		textArea.append(str);

	}

}
