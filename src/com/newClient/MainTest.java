package com.newClient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.JButton;

public class MainTest {

	private JFrame frmxxxBbb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainTest window = new MainTest();
					window.frmxxxBbb.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainTest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmxxxBbb = new JFrame();
		frmxxxBbb.setTitle("XXXXX_xxxxxx");
		frmxxxBbb.setBounds(100, 100, 711, 649);
		frmxxxBbb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmxxxBbb.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		mnFile.setMnemonic('F');
		menuBar.add(mnFile);

		JMenu mnNew = new JMenu("New");
		mnFile.add(mnNew);

		JMenuItem mntmJava = new JMenuItem("java");
		mnNew.add(mntmJava);

		JMenuItem mntmJsp = new JMenuItem("jsp");
		mnNew.add(mntmJsp);

		JMenuItem mntmSql = new JMenuItem("sql");
		mnNew.add(mntmSql);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);

		JMenuItem mntmInput = new JMenuItem("input");
		mnFile.add(mntmInput);

		JMenuItem mntmExit = new JMenuItem("exit");
		mnFile.add(mntmExit);

		JMenu mnEdit = new JMenu("Edit");
		mnEdit.setMnemonic('E');
		menuBar.add(mnEdit);

		JMenu mnSearch = new JMenu("Search");
		mnSearch.setMnemonic('a');
		menuBar.add(mnSearch);

		JMenu mnNewMenu = new JMenu("Help");
		mnNewMenu.setMnemonic('H');
		menuBar.add(mnNewMenu);

		JMenuItem mntmAbout = new JMenuItem("about");
		mnNewMenu.add(mntmAbout);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		frmxxxBbb.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		// tabbedPane.addTab("签页1111", new
		// ImageIcon(MainTest.class.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")),
		// panel, "签页1111提示信息");
		tabbedPane.addTab("签页1111", panel);

		JButton btnNewButton = new JButton("");
		panel.add(btnNewButton);
		btnNewButton.setPreferredSize(new Dimension(55, 55));
		btnNewButton
				.setIcon(new ImageIcon(
						MainTest.class
								.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif")));

		btnNewButton.setBorder(null);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setOpaque(false);

		JLabel lblNewLabel = new JLabel("New label");
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(MainTest.class
				.getResource("/javax/swing/plaf/metal/icons/Error.gif")));

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("标签页2222", panel_1);

		tabbedPane
				.setTabComponentAt(
						0,
						new TabPanel(
								new ImageIcon(
										MainTest.class
												.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")),
								"签页1", tabbedPane));
		tabbedPane
				.setTabComponentAt(
						1,
						new TabPanel(
								new ImageIcon(
										MainTest.class
												.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")),
								"签页2", tabbedPane));

		tabbedPane.addTab("标签页3", new JPanel());
		tabbedPane.setTabComponentAt(2, new TabPanel("签页3没有icon", tabbedPane));

		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(10, 300));
		frmxxxBbb.getContentPane().add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		panel_2.add(tabbedPane_1, BorderLayout.CENTER);

		JPanel panel_4 = new JPanel();
		tabbedPane_1.addTab("New tab 暗示的法斯蒂芬", null, panel_4, null);

		JPanel panel_5 = new JPanel();
		tabbedPane_1.addTab("New tab 啊是打发打发", null, panel_5, null);

		JPanel panel_3 = new JPanel();
		tabbedPane_1.addTab("New tab暗示的法斯蒂芬", null, panel_3, null);

		JLabel lblNewLabel_1 = new JLabel("New label");
		panel_3.add(lblNewLabel_1);

		JPanel panel_6 = new JPanel();
		panel_6.setPreferredSize(new Dimension(50, 50));
		panel_3.add(panel_6);
	}

}
