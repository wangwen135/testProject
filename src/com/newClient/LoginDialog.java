package com.newClient;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginDialog extends JDialog {

	public static final String LAST_SUCCESS_LOGIN = "lastSuccessLogin";

	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_username;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LoginDialog dialog = new LoginDialog();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LoginDialog() {
		setTitle("系统登录");
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setBounds(100, 100, 389, 272);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel label = new JLabel("用户名");
		label.setBounds(74, 56, 63, 15);
		contentPanel.add(label);

		JLabel label_1 = new JLabel("密码");
		label_1.setBounds(74, 110, 63, 15);
		contentPanel.add(label_1);

		textField_username = new JTextField();
		textField_username.setBounds(138, 53, 148, 21);
		contentPanel.add(textField_username);
		textField_username.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(138, 107, 148, 21);
		contentPanel.add(passwordField);

		JButton btnLogin = new JButton("登录");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField_username.getText().trim();
				if ("".equals(username)) {
					JOptionPane.showMessageDialog(LoginDialog.this, "用户名不能为空！");
					textField_username.requestFocus();
					return;
				}

				String passwd = String.valueOf(passwordField.getPassword());
				if ("".equals(passwd)) {
					JOptionPane.showMessageDialog(LoginDialog.this, "密码不能为空！");
					passwordField.requestFocus();
					return;
				}
				doLogin(username, passwd);
			}
		});
		btnLogin.setBounds(115, 167, 160, 38);
		contentPanel.add(btnLogin);

		// 加载上一次成功登录的用户名
		String last = TempPropertiesUtils
				.getPropertiesValue(LAST_SUCCESS_LOGIN);
		if (last != null) {
			textField_username.setText(last);
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					passwordField.requestFocus();
				}
			});
		}
	}

	private void doLogin(String userName, String password) {
		// 登录

		// 用户密码加密(MD5)
		String md5Str = getMD5Str(password);
		System.out.println(md5Str);

		// 与数据库中的用户名密码进行对比
		boolean b = check(userName, password);
		// 登录成功
		if (b) {
			// 显示主界面
			// 保存上次成功登录的用户名
			JOptionPane.showMessageDialog(LoginDialog.this, "登录成功......");

			TempPropertiesUtils
					.putPropertiesValue(LAST_SUCCESS_LOGIN, userName);
		} else {
			// 登录失败
			// 提示用户
			JOptionPane.showMessageDialog(LoginDialog.this, "用户名或密码错误！", "错误",
					JOptionPane.ERROR_MESSAGE);
			passwordField.setText("");
			textField_username.requestFocus();
		}
	}

	private boolean check(String username, String passwd) {
		if (username.equals(passwd)) {
			return true;
		}
		return false;
	}

	/**
	 * MD5 加密
	 */
	public String getMD5Str(String str) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			byte b = byteArray[i];
			md5StrBuff.append(Integer.toHexString(0x0F & b));
		}

		return md5StrBuff.toString();
	}
}
