package com.swing.component.my;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ToolTipManager;
import javax.swing.TransferHandler;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.swing.edi.dragDrop.TreeTransferable;
import com.swing.edi.reportModel.area.cell.entity.BaseEntity;
import com.swing.edi.reportModel.area.cell.entity.IEntity;
import com.swing.edi.ui.tree.EdiMutableTreeNode;

public class TreeTest extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

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
		setBounds(100, 100, 522, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(200, 200));
		panel.add(scrollPane, BorderLayout.CENTER);

		final JTree tree = new JTree() {
			@Override
			public String getToolTipText() {
				System.out.println("getToolTipText");
				return "<html>提示信息<br>提示信息2</html>";
			}

			@Override
			public String getToolTipText(MouseEvent event) {
				System.out.println(event);
				TreePath path = getPathForLocation(event.getX(), event.getY());
				if (path == null)
					return null;
				System.out.println(path);
				System.out.println(path.getLastPathComponent());
				return path.getLastPathComponent().toString();

			}

		};

		ToolTipManager toolTipManager = ToolTipManager.sharedInstance();
		toolTipManager.registerComponent(tree);

		tree.setPreferredSize(new Dimension(150, 64));
		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("tree") {
			{

				EdiMutableTreeNode node1 = new EdiMutableTreeNode(
						new BaseEntity("key", "测试值", "$测试"));
				add(node1);

				EdiMutableTreeNode node2 = new EdiMutableTreeNode(
						new BaseEntity("key2", "测试值2", "$测试2"));

				add(node2);

			}
		}));
		MouseListener ml = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int selRow = tree.getRowForLocation(e.getX(), e.getY());
				TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
				if (selRow != -1) {
					if (e.getClickCount() == 1) {
						// mySingleClick(selRow, selPath);
					} else if (e.getClickCount() == 2) {
						// myDoubleClick(selRow, selPath);
					}
					System.out.println("selRow=" + selRow);
					System.out.println("selPath=" + selPath);
				}
			}
		};
		tree.addMouseListener(ml);

		scrollPane.setViewportView(tree);

		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(150, 10));
		panel.add(panel_1, BorderLayout.EAST);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TreePath path = tree.getSelectionPath();
				if (path != null) {

					Object o = path.getLastPathComponent();

					if (o instanceof EdiMutableTreeNode) {
						EdiMutableTreeNode edinode = (EdiMutableTreeNode) o;
						IEntity entity = edinode.getEntity();
						System.out.println(entity.toXml());
					} else {

						DefaultMutableTreeNode selection = (DefaultMutableTreeNode) o;
						// 判断是否选择了树的叶子节点

						System.out.println("是叶子节点");
						System.out.println(selection.isLeaf());
						System.out.println(selection);
						System.out.println(selection.getUserObject());
					}
				}
			}
		});
		panel_1.add(btnNewButton);

		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);

		tree.setDragEnabled(true);
		tree.setTransferHandler(new TransferHandler() {
			@Override
			protected Transferable createTransferable(JComponent c) {
				TreePath path = tree.getSelectionPath();

				if (path != null) {

					EdiMutableTreeNode selection = (EdiMutableTreeNode) path
							.getLastPathComponent();
					System.out.println("是叶子节点");
					// TreeTransferable transferable = new TreeTransferable(
					// selection.getEntity());
					// return transferable;
				}
				return null;
			}

			@Override
			public int getSourceActions(JComponent c) {
				// 返回源支持的传输动作的类型
				return DnDConstants.ACTION_COPY;
				// return super.getSourceActions(c);
			}
		});
	}

}
