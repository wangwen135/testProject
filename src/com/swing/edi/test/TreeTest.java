package com.swing.edi.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.awt.Color;

public class TreeTest extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		final JTree tree = new JTree();
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TreePath path= tree.getSelectionPath();
				System.out.println(path);
				System.out.println(Arrays.toString(path.getPath()));
			}
		});
		tree.setShowsRootHandles(true);
		tree.setRootVisible(false);
		tree.setToolTipText("用鼠标拖动");
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("JTree") {
				{
					DefaultMutableTreeNode node_1;
					node_1 = new DefaultMutableTreeNode("常量");
						node_1.add(new DefaultMutableTreeNode("blue"));
						node_1.add(new DefaultMutableTreeNode("violet"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("变量");
						node_1.add(new DefaultMutableTreeNode("yellow"));
						node_1.add(new DefaultMutableTreeNode("sports"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("函数");
						node_1.add(new DefaultMutableTreeNode("soccer"));
						node_1.add(new DefaultMutableTreeNode("football"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("包装");
						node_1.add(new DefaultMutableTreeNode("colors"));
						node_1.add(new DefaultMutableTreeNode("blue"));
					add(node_1);
				}
			}
		));
		scrollPane.setViewportView(tree);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(176, 196, 222));
		contentPane.add(panel, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("展开");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tree.expandRow(2);
				tree.expandRow(1);
				tree.expandRow(0);
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("折叠");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tree.collapseRow(0);
				tree.collapseRow(1);
				tree.collapseRow(2);
			}
		});
		panel.add(btnNewButton_1);
	}

}
