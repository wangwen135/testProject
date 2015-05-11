package com.swing.drop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceContext;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.DropMode;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.TransferHandler.TransferSupport;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.swing.edi.dragDrop.EdiTableTransferHandler;
import com.swing.edi.dragDrop.EdiTreeTransferHandler;
import com.swing.edi.ui.table.EdiTableCellEditor;
import com.swing.edi.ui.table.EdiTableCellRenderer;
import com.swing.edi.ui.table.TableHeadCellRenderer;

/**
 * 拖动的时候还有问题，编辑器
 */
public class CopyOfDrop extends JFrame {

	private static final long serialVersionUID = -1291571444495787289L;
	private JPanel contentPane;
	private JTable table;
	private JTree tree;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CopyOfDrop frame = new CopyOfDrop();
					frame.setLocationRelativeTo(null);
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
	public CopyOfDrop() {
		setSize(new Dimension(1024, 768));
		setPreferredSize(new Dimension(1024, 768));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(100, 100, 584, 547);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(1, 1, 1, 1));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel_west = new JPanel();
		panel_west.setPreferredSize(new Dimension(0, 0));
		contentPane.add(panel_west, BorderLayout.WEST);

		JPanel panel_north = new JPanel();
		contentPane.add(panel_north, BorderLayout.NORTH);

		JButton btnNewButton_1 = new JButton("New button");
		panel_north.add(btnNewButton_1);

		JButton btnNewButton_5 = new JButton("New button");
		panel_north.add(btnNewButton_5);

		JButton btnNewButton_6 = new JButton("New button");
		panel_north.add(btnNewButton_6);

		JPanel panel_south = new JPanel();
		FlowLayout fl_panel_south = (FlowLayout) panel_south.getLayout();
		fl_panel_south.setHgap(80);
		contentPane.add(panel_south, BorderLayout.SOUTH);

		JButton btnNewButton_2 = new JButton("清空 (C)");
		panel_south.add(btnNewButton_2);

		JButton btnNewButton_7 = new JButton("保存 (S)");
		panel_south.add(btnNewButton_7);

		JButton btnNewButton_8 = new JButton("退出 (X)");
		panel_south.add(btnNewButton_8);

		JPanel panel_east = new JPanel();
		contentPane.add(panel_east, BorderLayout.EAST);

		JButton btnNewButton_3 = new JButton("New button");
		panel_east.add(btnNewButton_3);

		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);

		JPanel panel_4 = new JPanel();
		panel_4.setPreferredSize(new Dimension(100, 0));
		splitPane.setLeftComponent(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_6.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		panel_4.add(panel_6, BorderLayout.NORTH);

		JButton btnNewButton = new JButton("+");
		btnNewButton.setPreferredSize(new Dimension(20, 20));
		panel_6.add(btnNewButton);

		JButton btnNewButton_4 = new JButton("-");
		btnNewButton_4.setPreferredSize(new Dimension(20, 20));
		panel_6.add(btnNewButton_4);

		JButton button = new JButton("-");
		button.setPreferredSize(new Dimension(20, 20));
		panel_6.add(button);

		JButton button_1 = new JButton("-");
		button_1.setPreferredSize(new Dimension(20, 20));
		panel_6.add(button_1);

		JScrollPane scrollPane = new JScrollPane();
		panel_4.add(scrollPane, BorderLayout.CENTER);

		tree = new JTree();
		scrollPane.setViewportView(tree);

		JPanel panel_5 = new JPanel();
		splitPane.setRightComponent(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel_5.add(tabbedPane, BorderLayout.CENTER);

		JPanel panel_excel = new JPanel();
		tabbedPane
				.addTab("Excel",
						new ImageIcon(
								CopyOfDrop.class
										.getResource("/javax/swing/plaf/metal/icons/ocean/upFolder.gif")),
						panel_excel, null);
		panel_excel.setLayout(new BorderLayout(0, 0));

		JPanel panel_txt = new JPanel();
		tabbedPane
				.addTab(" Txt ",
						new ImageIcon(
								CopyOfDrop.class
										.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")),
						panel_txt, "生成文本文件模板\r\n");

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_txt.add(scrollPane_1);

		table = new JTable();
		scrollPane_1.setViewportView(table);

		JPanel panel_xml = new JPanel();
		tabbedPane
				.addTab(" Xml ",
						new ImageIcon(
								CopyOfDrop.class
										.getResource("/javax/swing/plaf/metal/icons/ocean/minimize.gif")),
						panel_xml, "生成Xml文件模板");

		// DragSource dragSource = DragSource.getDefaultDragSource(); // 创建拖拽源
		// dragSource.createDefaultDragGestureRecognizer(tree,
		// DnDConstants.ACTION_COPY_OR_MOVE, new MyDragGestureListener());
		// DropTarget dropTarget = new DropTarget(table,
		// new MyDropTargetListener());

		initTable();
		initTree();
	}

	private void initTree() {
		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("选择字段") {
			{
				add(new DefaultMutableTreeNode("<var>${编号}</var>"));
				add(new DefaultMutableTreeNode("运单编号"));
				add(new DefaultMutableTreeNode("托寄物内容"));
				add(new DefaultMutableTreeNode("实际重量"));
				add(new DefaultMutableTreeNode("计费重量"));
				add(new DefaultMutableTreeNode("重量单位"));
				add(new DefaultMutableTreeNode("数量/单位"));
				add(new DefaultMutableTreeNode("件数"));
				add(new DefaultMutableTreeNode("申报价值"));
				add(new DefaultMutableTreeNode("申报币别"));
				add(new DefaultMutableTreeNode("单价"));
				add(new DefaultMutableTreeNode("发票申报价值"));
				add(new DefaultMutableTreeNode("商品编码"));
				add(new DefaultMutableTreeNode("规格型号"));
			}
		}));
		// 树拖拽支持
		tree.setDragEnabled(true);
		// tree.setDropMode(DropMode.)
		tree.setTransferHandler(new EdiTreeTransferHandler(tree));

	}

	private void initTable() {
		table.setRowHeight(80);

		table.setModel(new DefaultTableModel(new Object[][] {
				{ "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "" },
				{ "", "", "", "", "", "", "" }, }, new String[] { "A", "B",
				"C", "D", "E", "F", "G", "B", "C" }));
		table.setSurrendersFocusOnKeystroke(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(false);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		// table.setFillsViewportHeight(true);

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// ###################################################
		// 应该重新写一个表格渲染器，表头和表体已经表尾 应该有标识
		// table.setCellEditor(new EdiTableCellEditor());
		table.setDefaultRenderer(Object.class, new EdiTableCellRenderer());
		// 是否可以使用行头
		// 表格右键
		table.addMouseListener(new TableMouseListener());
		// 表头渲染器
		table.getTableHeader().setDefaultRenderer(new TableHeadCellRenderer());
		// 表头右键
		table.getTableHeader().addMouseListener(new TableHeadMouseListener());
		// 表格拖拽支持
		table.setDragEnabled(true);
		table.setDropMode(DropMode.USE_SELECTION);
		table.setTransferHandler(new EdiTableTransferHandler(table));
	}

	private void addColumn() {
		TableColumn tc = new TableColumn();
		table.addColumn(tc);
		DefaultTableModel tm = (DefaultTableModel) table.getModel();
		tm.addColumn("??");
	}

	class TableMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1 && SwingUtilities.isRightMouseButton(e)) {
				JPopupMenu pop = new JPopupMenu();
				JMenuItem item01 = new JMenuItem("编辑");
				pop.add(item01);
				JMenuItem item02 = new JMenuItem("删除");
				pop.add(item02);
				JMenuItem item03 = new JMenuItem("重定义");
				pop.add(item03);
				pop.addSeparator();

				JMenuItem item04 = new JMenuItem("增加列");
				item04.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						addColumn();
					}
				});
				pop.add(item04);

				pop.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}

	class TableHeadMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1 && SwingUtilities.isRightMouseButton(e)) {
				JPopupMenu pop = new JPopupMenu();
				JMenuItem item01 = new JMenuItem("删除列");
				pop.add(item01);
				JMenuItem item02 = new JMenuItem("增加列");
				pop.add(item02);
				item02.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						addColumn();
					}
				});
				JMenuItem item03 = new JMenuItem("在之前增加列");
				pop.add(item03);

				pop.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}

}

/**
 * 检测到拖动启动动作时，调用此接口。
 * 
 * 接收到这种通知之后，此接口的实现者将负责启动拖动操作。
 */
class MyDragGestureListener implements DragGestureListener {

	public void dragGestureRecognized(DragGestureEvent dge) {
		System.out.println("已经检测到与平台有关的拖动启动动作，并且正通知此侦听器，以便启动用户操");
		// 将数据存储到Transferable中，然后通知组件开始调用startDrag()初始化
		JTree tree = (JTree) dge.getComponent();
		TreePath path = tree.getSelectionPath();
		if (path != null) {
			DefaultMutableTreeNode selection = (DefaultMutableTreeNode) path
					.getLastPathComponent();

			MyTransferable transferable = new MyTransferable(selection);
			dge.startDrag(DragSource.DefaultCopyDrop, transferable,
					new MyDragSourceListener());
		}
	}

}

/**
 * 定义为传输操作提供数据所使用的类的接口
 */
class MyTransferable implements Transferable {
	private DefaultMutableTreeNode treeNode;

	MyTransferable(DefaultMutableTreeNode treeNode) {
		this.treeNode = treeNode;
	}

	static DataFlavor flavors[] = { DataFlavor.stringFlavor };

	public DataFlavor[] getTransferDataFlavors() {
		return flavors;
	}

	public boolean isDataFlavorSupported(DataFlavor flavor) {
		// return true;
		if (treeNode.isLeaf()) {
			System.out.println("是叶子节点啊。。。");
			// if (treeNode.getChildCount() == 0) {
			return true;
		}
		return false;
	}

	public Object getTransferData(DataFlavor flavor)
			throws UnsupportedFlavorException, IOException {

		return treeNode.toString();

	}

}

class MyDragSourceListener implements DragSourceListener {
	public void dragDropEnd(DragSourceDropEvent dragSourceDropEvent) {
		System.out.println(" @@@@@@@@@@@@@@@@@调用此方法来通知 Drag 和 Drop 操作已完成");
		if (dragSourceDropEvent.getDropSuccess()) {
			// 拖拽动作结束的时候打印出移动节点的字符串
			int dropAction = dragSourceDropEvent.getDropAction();
			if (dropAction == DnDConstants.ACTION_MOVE) {
				System.out.println("MOVE: remove node");
			}
		}
	}

	public void dragEnter(DragSourceDragEvent dragSourceDragEvent) {
		System.out.println("@@@@@@@@@@@@光标的热点进入放置点（与平台相关）时调用该方法");
		DragSourceContext context = dragSourceDragEvent.getDragSourceContext();
		int dropAction = dragSourceDragEvent.getDropAction();
		if ((dropAction & DnDConstants.ACTION_COPY) != 0) {
			context.setCursor(DragSource.DefaultCopyDrop);
		} else if ((dropAction & DnDConstants.ACTION_MOVE) != 0) {
			context.setCursor(DragSource.DefaultMoveDrop);
		} else {
			context.setCursor(DragSource.DefaultCopyNoDrop);
		}
	}

	public void dragExit(DragSourceEvent dragSourceEvent) {
		System.out.println("@@@@@@@@@@@光标的热点离开放置点（与平台相关）时调用该方法");
	}

	public void dragOver(DragSourceDragEvent dragSourceDragEvent) {
		System.out.println("@@@@@@@@@光标的热点在放置点（与平台相关）上移动时调用该方法。");
	}

	public void dropActionChanged(DragSourceDragEvent dragSourceDragEvent) {
		System.out.println("@@@@@@@@@当用户修改放置操作时调用该方法。");
	}
}

class MyDropTargetListener implements DropTargetListener {
	public void dragEnter(DropTargetDragEvent dtde) {
		System.out.println("此时鼠标指针进入 DropTarget");
	}

	public void dragOver(DropTargetDragEvent dtde) {
		System.out.println("此时鼠标指针仍然处于 DropTarget");
		System.out.println(dtde.getLocation().x);
		System.out.println(dtde.getLocation().y);
		DropTarget c = (DropTarget) dtde.getSource();
		JTable t = (JTable) c.getComponent();

		int row = t.rowAtPoint(dtde.getLocation());
		int col = t.columnAtPoint(dtde.getLocation());
		if (row != -1 && col != -1) {
			t.addColumnSelectionInterval(col, col);
			t.addRowSelectionInterval(row, row);
		}
	}

	public void dropActionChanged(DropTargetDragEvent dtde) {
		System.out.println("在用户已经修改了当前 drop 操作后调用");
	}

	public void dragExit(DropTargetEvent dte) {
		System.out.println("此时鼠标指针已退出 DropTarget");
	}

	public void drop(DropTargetDropEvent dtde) {
		System.out.println("此时 drop 处于 DropTarget");
		Transferable tr = dtde.getTransferable();// 使用该函数从Transferable对象中获取有用的数据
		String s = "";
		try {
			if (tr.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				s = tr.getTransferData(DataFlavor.stringFlavor).toString();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (UnsupportedFlavorException ex) {
			ex.printStackTrace();
		}
		System.out.println(s);

		DropTarget c = (DropTarget) dtde.getSource();
		JTable t = (JTable) c.getComponent();
		if (s != null && s != "") {
			System.out.println(s);
			int col = t.getSelectedColumn();
			int row = t.getSelectedRow();
			if (col != -1 && row != -1) {
				t.setValueAt(s, row, col);
			}
		}
	}
}
