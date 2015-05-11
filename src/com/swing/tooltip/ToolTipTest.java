package com.swing.tooltip;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ToolTipManager;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ToolTipTest extends JFrame {

	private static final long serialVersionUID = 1L;

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
					ToolTipTest frame = new ToolTipTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private boolean showToolTip = false;

	/**
	 * Create the frame.
	 */
	public ToolTipTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JLabel lblNewLabel = new JLabel("New label  标签 标签");
		lblNewLabel
				.setToolTipText("<html><font color=RED size=+1>asdff测试测试测试测试</font></html>\r\n\r\n\r\n");
		lblNewLabel.setBounds(53, 42, 141, 15);
		contentPane.add(lblNewLabel);

		final JButton btnNewButton = new JButton("New button");
		btnNewButton.setToolTipText("12323231312");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 启动toolTip
				// lblNewLabel.action(new mou, what)
				// lblNewLabel.getActionForKeyStroke(aKeyStroke)
				// textField.setToolTipText("<html><font size='4' color='red'>asdff测试测试测试测试</font></html>");

				showToolTip = true;
				postToolTip(textField);

				// textField.setToolTipText(null);
				// postToolTip(btnNewButton);
			}
		});
		btnNewButton.setBounds(44, 83, 93, 23);
		contentPane.add(btnNewButton);

		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String tooltip = textField.getText();
				if (!"tooltip".equals(tooltip)) {
					textField.requestFocus();

					// textField.setToolTipText("<html><font size='4' color='red'>asdff测试测试测试测试</font></html>");
					showToolTip = true;
					postToolTip(textField);
				}
			}
		});
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// 按下键盘时隐藏 toolTip
				if (showToolTip) {
					showToolTip = false;
					hideToolTip(textField);
				}
			}
		});
		textField
				.setToolTipText("<html><font size='4' color='red'>输入tooltip 才能离开焦点</font></html>");
		textField.setBounds(152, 148, 66, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(264, 148, 66, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
	}

	// 1、显示tooltip
	public static void postToolTip(JComponent comp) {
		Action action = comp.getActionMap().get("postTip");
		if (action == null) // no tooltip
			return;
		ActionEvent ae = new ActionEvent(comp, ActionEvent.ACTION_PERFORMED,
				"postTip", EventQueue.getMostRecentEventTime(), 0);
		action.actionPerformed(ae);
	}

	// 2、隐藏tooltip
	public static void hideToolTip(JComponent comp) {
		Action action = comp.getActionMap().get("hideTip");
		if (action == null) // no tooltip
			return;
		ActionEvent ae = new ActionEvent(comp, ActionEvent.ACTION_PERFORMED,
				"hideTip", EventQueue.getMostRecentEventTime(), 0);
		action.actionPerformed(ae);
	}
}
