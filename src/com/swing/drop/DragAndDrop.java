package com.swing.drop;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
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
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class DragAndDrop extends JFrame {

	JScrollPane jScrollPane1 = new JScrollPane();
	JTextArea jTextArea1 = new JTextArea();
	
	public DragAndDrop() {
		try {
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		getContentPane().setLayout(null);

		jScrollPane1.getViewport().setBackground(new Color(105, 38, 125));
		jTextArea1.setBackground(Color.WHITE);
		jTextArea1.setToolTipText("");
		jTextArea1.setBounds(238, 8, 217, 283);
		JTree jtr = new JTree();
		jtr.setBackground(Color.WHITE);
		jScrollPane1.setViewportView(jtr);
		jScrollPane1.setBounds(16, 10, 217, 281);
		this.getContentPane().add(jTextArea1);
		this.getContentPane().add(jScrollPane1);

		DragSource dragSource = DragSource.getDefaultDragSource(); // 创建拖拽源

		dragSource.createDefaultDragGestureRecognizer(jtr,
				DnDConstants.ACTION_COPY_OR_MOVE,
				new DragAndDropDragGestureListener()); // 建立拖拽源和事件的联系

		DropTarget dropTarget = new DropTarget(jTextArea1,
				new DragAndDropDropTargetListener());

	}

	private void jbInit() throws Exception {

	}

	public static void main(String[] args) {

		DragAndDrop dad = new DragAndDrop();
		dad.setTitle("拖拽演示");
		dad.setSize(400, 300);
		dad.setVisible(true);

	}
}

class DragAndDropDragGestureListener implements DragGestureListener {
	public void dragGestureRecognized(DragGestureEvent dge) {
		System.out.println("已经检测到与平台有关的拖动启动动作，并且正通知此侦听器，以便启动用户操");
		// 将数据存储到Transferable中，然后通知组件开始调用startDrag()初始化
		JTree tree = (JTree) dge.getComponent();
		TreePath path = tree.getSelectionPath();
		if (path != null) {
			DefaultMutableTreeNode selection = (DefaultMutableTreeNode) path
					.getLastPathComponent();
			
			DragAndDropTransferable dragAndDropTransferable = new DragAndDropTransferable(
					selection);
			
			Image i = Toolkit.getDefaultToolkit().getImage("test.JPG");
			
			Cursor  c =Toolkit.getDefaultToolkit().createCustomCursor(i,new Point(1, 1),"名字");
			//DragSource.DefaultCopyDrop
			
			dge.startDrag(c, dragAndDropTransferable,
					new DragAndDropDragSourceListener());
		}
	}

}

class DragAndDropTransferable implements Transferable {
	private DefaultMutableTreeNode treeNode;

	DragAndDropTransferable(DefaultMutableTreeNode treeNode) {
		this.treeNode = treeNode;
	}

	static DataFlavor flavors[] = { DataFlavor.stringFlavor };

	public DataFlavor[] getTransferDataFlavors() {
		return flavors;
	}

	public boolean isDataFlavorSupported(DataFlavor flavor) {
		if (treeNode.getChildCount() == 0) {
			return true;
		}
		return false;
	}

	public Object getTransferData(DataFlavor flavor)
			throws UnsupportedFlavorException, IOException {

		return treeNode;

	}

}

class DragAndDropDragSourceListener implements DragSourceListener {
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
		
//		if ((dropAction & DnDConstants.ACTION_COPY) != 0) {
//			context.setCursor(DragSource.DefaultCopyDrop);
//		} else if ((dropAction & DnDConstants.ACTION_MOVE) != 0) {
//			context.setCursor(DragSource.DefaultMoveDrop);
//		} else {
//			context.setCursor(DragSource.DefaultCopyNoDrop);
//		}
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

class DragAndDropDropTargetListener implements DropTargetListener {
	public void dragEnter(DropTargetDragEvent dtde) {
		System.out.println("此时鼠标指针进入 DropTarget");
	}

	public void dragOver(DropTargetDragEvent dtde) {
		System.out.println("此时鼠标指针仍然处于 DropTarget");
		System.out.println(dtde.getLocation().x);
		System.out.println(dtde.getLocation().y);
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
		} catch (UnsupportedFlavorException ex) {
		}
		System.out.println(s);
		DropTarget c = (DropTarget) dtde.getSource();
		JTextArea d = (JTextArea) c.getComponent();
		if (s != null && s != "") {
			d.append(s + "\n");
		}
	}

}
