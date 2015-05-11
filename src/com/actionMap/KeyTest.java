package com.actionMap;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class KeyTest extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;

	private JTree tree;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			KeyTest dialog = new KeyTest();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 上次查找用的key
	 */
	private String lastSearchKey = null;
	/**
	 * 上次查找的结果
	 */
	private List<TreePath> lastSearchResult = null;
	/**
	 * 上次查找的位置
	 */
	private int lastResultIndex = 0;

	/**
	 * <pre>
	 * 循环查找
	 * 如果搜索的key没改变则从上次搜索的结果中查找
	 * </pre>
	 * 
	 * @param node
	 * @param key
	 * @return
	 */
	public TreePath circularSearch(DefaultMutableTreeNode node, String key) {
		if ("".equals(key)) {
			return null;
		}
		if (key.equals(lastSearchKey)) {// 从上次搜索的结果中取
			if (lastSearchResult == null || lastSearchResult.isEmpty()) {
				return null;
			}
			if (lastResultIndex < lastSearchResult.size() - 1) {
				lastResultIndex++;
			} else {
				lastResultIndex = 0;
			}
			return lastSearchResult.get(lastResultIndex);
		} else {
			// 重新查
			lastSearchResult = searchPathList(node, key);
			lastSearchKey = key;
			lastResultIndex = 0;
			if (lastSearchResult == null || lastSearchResult.isEmpty()) {
				return null;
			}
			return lastSearchResult.get(0);
		}
	}

	/**
	 * <pre>
	 * 递归查找所有匹配的节点
	 * </pre>
	 * 
	 * @param node
	 * @param key
	 * @return
	 */
	public List<TreePath> searchPathList(DefaultMutableTreeNode node, String key) {
		List<TreePath> list = new ArrayList<TreePath>();
		int childCount = node.getChildCount();

		for (int i = 0; i < childCount; i++) {
			DefaultMutableTreeNode node2 = (DefaultMutableTreeNode) node
					.getChildAt(i);
			String content = (String) node2.getUserObject();
			if (content.contains(key)) {
				list.add(new TreePath(node2.getPath()));
			}
			// 尝试递归
			if (node2.getChildCount() > 0) {
				List<TreePath> listPath = searchPathList(node2, key);
				if (listPath != null && !listPath.isEmpty()) {
					list.addAll(listPath);
				}
			}
		}
		return list;
	}

	/**
	 * <pre>
	 * 递归查单个找节点
	 * </pre>
	 * 
	 * @param node
	 * @param key
	 * @return
	 */
	public TreePath searchOnePath(DefaultMutableTreeNode node, String key) {
		int childCount = node.getChildCount();
		for (int i = 0; i < childCount; i++) {
			DefaultMutableTreeNode node2 = (DefaultMutableTreeNode) node
					.getChildAt(i);
			String content = (String) node2.getUserObject();
			if (content.contains(key)) {
				return new TreePath(node2.getPath());
			}
			// 尝试递归
			if (node2.getChildCount() > 0) {
				TreePath path = searchOnePath(node2, key);
				if (path != null) {
					return path;
				}
			}
		}
		return null;
	}

	/**
	 * Create the dialog.
	 */
	public KeyTest() {

		setBounds(100, 100, 580, 444);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textField = new JTextField();
			textField.setBounds(396, 143, 66, 21);
			contentPanel.add(textField);
			textField.setColumns(10);
		}

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(338, 146, 54, 15);
		contentPanel.add(lblNewLabel);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "adfadf",
				"adfadf", "adf123", "123", "adf123", "123123", "af", "12333" }));
		comboBox.setBounds(371, 93, 91, 21);
		contentPanel.add(comboBox);

		textField_1 = new JTextField();
		textField_1.setBounds(10, 14, 133, 21);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("查  找");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 连续查找
				// 先清空选择，回到最前面
				tree.clearSelection();
				tree.scrollRowToVisible(0);

				TreeModel treemodel = tree.getModel();
				DefaultMutableTreeNode root = (DefaultMutableTreeNode) treemodel
						.getRoot();
				String key = textField_1.getText();

				TreePath path = circularSearch(root, key);
				// TreePath path = searchOnePath(root, key);
				if (path != null) {
					tree.expandPath(path);
					tree.setSelectionPath(path);
					tree.scrollPathToVisible(path);
				}

			}
		});
		btnNewButton.setBounds(153, 12, 93, 23);
		contentPanel.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 150, 171);
		contentPanel.add(scrollPane);

		tree = new JTree();
		scrollPane.setViewportView(tree);
		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("JTree") {
			{
				DefaultMutableTreeNode node_1;
				node_1 = new DefaultMutableTreeNode("colors");
				node_1.add(new DefaultMutableTreeNode("blue"));
				node_1.add(new DefaultMutableTreeNode("violet"));
				node_1.add(new DefaultMutableTreeNode("red"));
				node_1.add(new DefaultMutableTreeNode("yellow"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("sports");
				node_1.add(new DefaultMutableTreeNode("basketball"));
				node_1.add(new DefaultMutableTreeNode("soccer"));
				node_1.add(new DefaultMutableTreeNode("football"));
				node_1.add(new DefaultMutableTreeNode("hockey"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("food");
				node_1.add(new DefaultMutableTreeNode("hot dogs"));
				node_1.add(new DefaultMutableTreeNode("pizza"));
				node_1.add(new DefaultMutableTreeNode("ravioli"));
				node_1.add(new DefaultMutableTreeNode("bananas"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("food2");
				node_1.add(new DefaultMutableTreeNode("hot dogs2"));
				node_1.add(new DefaultMutableTreeNode("pizza2"));
				node_1.add(new DefaultMutableTreeNode("ravioli2"));
				node_1.add(new DefaultMutableTreeNode("bananas2"));
				add(node_1);
			}
		}));
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		ActionMap actionMap = this.getRootPane().getActionMap();
		System.out.println("测试ActionMap");
		Object[] obj = actionMap.allKeys();
		for (int i = 0; i < obj.length; i++) {
			System.out.println("___________________________");
			Object key = obj[i];
			System.out.println(key);
			Action a = actionMap.get(key);
			System.out.println(a);
		}

		actionMap.put("test", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("test触发了");
				JOptionPane.showMessageDialog(null, "退出");
			}
		});
		this.getRootPane().setActionMap(actionMap);

		InputMap inputMap = this.getRootPane().getInputMap();

		// InputEvent.CTRL_DOWN_MASK|InputEvent.SHIFT_DOWN_MASK)
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "test");
		this.getRootPane().setInputMap(
				JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, inputMap);

		System.out.println("-------comboBox---------");
		System.out.println(comboBox.getActionCommand());
		ActionMap map = comboBox.getActionMap();
		Object[] allkey = map.allKeys();
		for (Object object : allkey) {
			System.out.println(object);
			System.out.println(map.get(object));
		}

		InputMap iMap = comboBox.getInputMap();
		KeyStroke[] keyStrokes = iMap.allKeys();
		// for (KeyStroke a : keyStrokes) {
		// System.out.println(a);
		// }
	}
}
