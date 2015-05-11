package com.swing.dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.ActionMap;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;
import javax.swing.UIManager;

public class TextDialog1 extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2418084539463264459L;
	private final JPanel contentPanel = new JPanel();
	private JEditorPane editorPane;
	private String msg;
	
	private boolean isConfirm = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		TextDialog1.showMsg(getMsg());
	}

	/**
	 * Create the dialog.
	 */
	public TextDialog1(String message) {
		this.msg = message;
		setResizable(false);
		setModal(true);
		setTitle("提示");
		setBounds(100, 100, 450, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBorder(null);
			scrollPane
					.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			contentPanel.add(scrollPane);
			{
				editorPane = new JEditorPane();
				editorPane.setBackground(UIManager.getColor("control"));
				editorPane.setEditable(false);
				scrollPane.setViewportView(editorPane);
				editorPane.setText(this.msg);
				{
					JPopupMenu popupMenu = new JPopupMenu();
					addPopup(editorPane, popupMenu);
					{
						JMenuItem copy = new JMenuItem("复制");
						popupMenu.add(copy);
						copy.setAccelerator(KeyStroke.getKeyStroke('C',
								InputEvent.CTRL_MASK));

						copy.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								editorPane.copy();
							}
						});
					}
				}

				ActionMap actionMap = editorPane.getActionMap();
				System.out.println(Arrays.toString(actionMap.allKeys()));

				// 快捷键
				editorPane.getInputMap().put(
						KeyStroke.getKeyStroke(KeyEvent.VK_A,
								InputEvent.CTRL_DOWN_MASK, true), "select-all");
				editorPane.getInputMap().put(
						KeyStroke.getKeyStroke(KeyEvent.VK_T,
								InputEvent.CTRL_DOWN_MASK, true), "insert-tab");
				editorPane.getInputMap().put(
						KeyStroke.getKeyStroke(KeyEvent.VK_D,
								InputEvent.CTRL_DOWN_MASK, true),
						"insert-break");
				editorPane.getInputMap().put(
						KeyStroke.getKeyStroke(KeyEvent.VK_K,
								InputEvent.CTRL_DOWN_MASK, true), "copy");
				editorPane.getInputMap().put(
						KeyStroke.getKeyStroke(KeyEvent.VK_F,
								InputEvent.CTRL_DOWN_MASK, true), "unselect");
				editorPane.getInputMap().put(
						KeyStroke.getKeyStroke(KeyEvent.VK_B,
								InputEvent.CTRL_DOWN_MASK, true), "page-down");
			}
		}
		{
			JPanel buttonPane = new JPanel();
			FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.CENTER);
			fl_buttonPane.setHgap(15);
			fl_buttonPane.setVgap(2);
			buttonPane.setLayout(fl_buttonPane);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("确定");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						isConfirm = true;
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton btny = new JButton("是(Y)");
				btny.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						isConfirm = true;
						setVisible(false);
					}
				});
				btny.setMnemonic('Y');
				buttonPane.add(btny);
				btny.setVisible(false);
			}
			{
				JButton btnn = new JButton("否(N)");
				btnn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						isConfirm = false;
						setVisible(false);
					}
				});
				btnn.setMnemonic('N');
				buttonPane.add(btnn);
				btnn.setVisible(false);
			}
		}
	}

	public static String getMsg() {
		StringBuffer msg = new StringBuffer("导出完成，但未全部成功！\n");

		if (1 != 0) {
			msg.append("以下运单生成发票失败：\n");
			for (int i = 0; i < 20; i++) {
				msg.append(i + "123123123;");
			}

			msg.append("\n");
		}

		if (1 != 0) {
			msg.append("以下运单生成分运单图片失败：\n");
			for (int i = 0; i < 20; i++) {
				msg.append(i + "222121233223222;");
			}
			msg.append("\n");
		}

		if (1 != 0) {
			msg.append("以下运单复制文件失败：\n");
			for (int i = 0; i < 20; i++) {
				msg.append(i + "3331322223333;");
			}
			msg.append("\n");
		}
		return msg.toString();
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	public static void showMsg(String msg) {
		TextDialog1 td = new TextDialog1(msg);
		td.setVisible(true);
		td.dispose();
	}
}
