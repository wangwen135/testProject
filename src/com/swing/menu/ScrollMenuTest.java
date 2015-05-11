package com.swing.menu;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.swing.menu.layout.MyMenuLayout2;

public class ScrollMenuTest {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScrollMenuTest window = new ScrollMenuTest();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ScrollMenuTest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 657, 447);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		//menuBar.setLayout(new MyMenuLayout2());
		
//		MJMenu mnNewMenu = new MJMenu("菜单测试");
		JMenu mnNewMenu = new JMenu("菜单测试");
			
		menuBar.add(mnNewMenu);
		
		mnNewMenu.add(new MyJMenuItem("测试大小用"));
		
		JMenuItem menuItem;
		
		for (int i = 1; i < 70; i++) {
			StringBuffer title = new StringBuffer();
		
			for (int j = 0; j < i; j++) {
				title.append("0");
			}
			
			menuItem = new JMenuItem(title.toString() + i);
			mnNewMenu.add(menuItem);
		}

		JMenu mnNewMenu2 = new JMenu("菜单测试2");
		menuBar.add(mnNewMenu2);
		
		JMenuItem menuItem2  = new JMenuItem("阿斯蒂芬");
		mnNewMenu2.add(menuItem2);
		
		JButton btn = new JButton("测试按钮");
		mnNewMenu2.add(btn);
		
		JMenu menu3 = new JMenu("菜单3");
		menuBar.add(menu3);
		
		JPanel panel_1 = new JPanel();
		menu3.add(panel_1);
		
		JButton btnNewButton_10 = new JButton("New button");
		panel_1.add(btnNewButton_10);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
		panel_1.add(rdbtnNewRadioButton);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		panel_1.add(lblNewLabel_1);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_11 = new JButton("New button");
		panel_1.add(btnNewButton_11);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
		panel_1.add(chckbxNewCheckBox);
		frame.getContentPane().setLayout(null);
		
	
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(85, 86, 54, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(201, 40, 333, 183);
		frame.getContentPane().add(panel);
		FlowLayout fl_panel = new FlowLayout(FlowLayout.LEFT, 5, 2);
		panel.setLayout(fl_panel);
		
		JButton btnNewButton = new JButton("New button1");
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button2");
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button3");
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("New button412312313");
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("New button");
		panel.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("New button");
		panel.add(btnNewButton_5);
		
		JButton btnNewButton_8 = new JButton("New button");
		panel.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("New button");
		panel.add(btnNewButton_9);
		
		JButton btnNewButton_6 = new JButton("New button");
		panel.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("New button");
		panel.add(btnNewButton_7);
		
		
//		menu3.add(c)
		
//		for (int i = 0; i < 10; i++) {
//			JMenu m = new JMenu("菜单测试");
//			
//			menuBar.add(m);	
//		}
		
		
		
		//弹出菜单测试
		
		MJPopupMenu popupMenu = new MJPopupMenu();
		addPopup(frame.getContentPane(), popupMenu);
		popupMenu.setLayout(new MyMenuLayout2());
		
		JButton button = new JButton("菜单修改");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeMenu();
			}
		});
		button.setBounds(54, 283, 93, 23);
		frame.getContentPane().add(button);
		
		JMenuItem mntm;
		for (int i = 1; i < 20; i++) {
			mntm = new JMenuItem("弹出菜单" + i);
			popupMenu.add(mntm);
		}
	}
	
	private void changeMenu(){
		//MenuBar mb = frame.getMenuBar();
		JMenuBar jmb = frame.getJMenuBar();
		
		
		
		for(int i = 0;i< jmb.getMenuCount();i++){
			JMenu m = jmb.getMenu(i);
			//m.setLayout(new MyMenuLayout2());
			JPopupMenu jpm = m.getPopupMenu();
			jpm.setLayout(new MyMenuLayout2());
		}
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
}
