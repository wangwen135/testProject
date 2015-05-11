package com.swing.component.tree;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class TreeTest extends JFrame {

	private JPanel contentPane;

	private JTree tree;

	/**
	 * Launch the application.
	 * 
	 * @throws Exception
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException, Exception {
		String windows = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		UIManager.setLookAndFeel(windows);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TreeTest frame = new TreeTest();
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
	public TreeTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 416, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		tree = new JTree();
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				System.out.println("值改变。。" + e);
				TreePath path = tree.getSelectionPath();
				System.out.println(path.toString());
				// 返回路径中的元素
				System.out.println(path.getPathCount());
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) path
						.getLastPathComponent();
				if (node.isLeaf()) {
					System.out.println(node.getParent());
				}
			}
		});
		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("root") {
			{
				DefaultMutableTreeNode node_1;
				node_1 = new DefaultMutableTreeNode("批次车次");
				node_1.add(new DefaultMutableTreeNode("SZX0100HKG A"));
				node_1.add(new DefaultMutableTreeNode("SZX0100HKG B"));
				node_1.add(new DefaultMutableTreeNode("SZX0100HKG C"));
				node_1.add(new DefaultMutableTreeNode("SZX0100HKG D"));
				node_1.add(new DefaultMutableTreeNode("SZX0100HKG E123131"));
				node_1.add(new DefaultMutableTreeNode("SZX0100HKG F123a"));
				node_1.add(new DefaultMutableTreeNode("SZX0100HKG G"));
				node_1.add(new DefaultMutableTreeNode("SZX0100HKG Xxs11s12123"));
				node_1.add(new DefaultMutableTreeNode("SZX0100HKG I"));
				node_1.add(new DefaultMutableTreeNode("SZX0100HKG J"));
				node_1.add(new DefaultMutableTreeNode("SZX0100HKG K"));
				node_1.add(new DefaultMutableTreeNode("SZX0100HKG L"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("车牌航班");
				node_1.add(new DefaultMutableTreeNode("SZX0100HKG A"));
				node_1.add(new DefaultMutableTreeNode("SZX0100HKG A"));
				node_1.add(new DefaultMutableTreeNode("SZX0100HKG A123af"));
				node_1.add(new DefaultMutableTreeNode("SZX0100HKG A"));
				node_1.add(new DefaultMutableTreeNode("SZX0100HKG X"));
				node_1.add(new DefaultMutableTreeNode("SZX0100HKG Basdf"));
				node_1.add(new DefaultMutableTreeNode("SZX0100HKG C"));
				node_1.add(new DefaultMutableTreeNode("SZX0100HKG Xssw2112312"));
				node_1.add(new DefaultMutableTreeNode("SZX0100HKG A"));
				add(node_1);
			}
		}));
		tree.expandRow(1);
		tree.expandRow(0);
		tree.setRootVisible(false);
		scrollPane.setViewportView(tree);

		JPanel panel = new JPanel();
		scrollPane.setRowHeaderView(panel);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.EAST);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.WEST);

		JButton btnNewButton = new JButton("清空数据");
		panel_2.add(btnNewButton);
		
				JButton btnNewButton_1 = new JButton("重新构建树");
				panel_2.add(btnNewButton_1);
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode(
								"root") {
							{
								DefaultMutableTreeNode node_1;
								node_1 = new DefaultMutableTreeNode("批次车次");
								node_1.add(new DefaultMutableTreeNode("SZX0100HKG A"));
								node_1.add(new DefaultMutableTreeNode("SZX0100HKG B"));
								node_1.add(new DefaultMutableTreeNode("SZX0100HKG C"));
								node_1.add(new DefaultMutableTreeNode("SZX0100HKG D"));
								node_1.add(new DefaultMutableTreeNode(
										"SZX0100HKG E123131"));
								node_1.add(new DefaultMutableTreeNode(
										"SZX0100HKG F123a"));
								node_1.add(new DefaultMutableTreeNode("SZX0100HKG G"));
								node_1.add(new DefaultMutableTreeNode(
										"SZX0100HKG Xxs11s12123"));
								node_1.add(new DefaultMutableTreeNode("SZX0100HKG I"));
								node_1.add(new DefaultMutableTreeNode("SZX0100HKG J"));
								node_1.add(new DefaultMutableTreeNode("SZX0100HKG K"));
								node_1.add(new DefaultMutableTreeNode("SZX0100HKG L"));
								add(node_1);
								node_1 = new DefaultMutableTreeNode("车牌航班");
								node_1.add(new DefaultMutableTreeNode("SZX0100HKG A"));
								node_1.add(new DefaultMutableTreeNode("SZX0100HKG A"));
								node_1.add(new DefaultMutableTreeNode(
										"SZX0100HKG A123af"));
								node_1.add(new DefaultMutableTreeNode("SZX0100HKG A"));
								node_1.add(new DefaultMutableTreeNode("SZX0100HKG X"));
								node_1.add(new DefaultMutableTreeNode(
										"SZX0100HKG Basdf"));
								node_1.add(new DefaultMutableTreeNode("SZX0100HKG C"));
								node_1.add(new DefaultMutableTreeNode(
										"SZX0100HKG Xssw2112312"));
								node_1.add(new DefaultMutableTreeNode("SZX0100HKG A"));
								add(node_1);
							}
						}));

						tree.expandRow(1);
						tree.expandRow(0);
					}
				});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode(
						"root") {
					{
						DefaultMutableTreeNode node_1;
						node_1 = new DefaultMutableTreeNode("批次车次");
						add(node_1);
						node_1 = new DefaultMutableTreeNode("车牌航班");
						add(node_1);
					}
				}));
			}
		});
	}

}
