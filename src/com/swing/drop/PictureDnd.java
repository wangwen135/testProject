package com.swing.drop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.HashSet;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

public class PictureDnd {
	JFrame mainFrame;
	JPanel mainPanel;
	PictureComponent[] pictures;

	public static void main(String[] args) {
		new PictureDnd();
	}

	public PictureDnd() {
		mainFrame = new JFrame();
		mainPanel = new JPanel(new GridLayout(2, 2));
		pictures = new PictureComponent[4];
		pictures[0] = new PictureComponent(
				new ImageIcon("buttons/b1.gif").getImage());
		pictures[1] = new PictureComponent(
				new ImageIcon("buttons/b3.gif").getImage());
		pictures[2] = new PictureComponent(null);
		pictures[3] = new PictureComponent(null);

		mainPanel.add(pictures[0]);
		mainPanel.add(pictures[1]);
		mainPanel.add(pictures[2]);
		mainPanel.add(pictures[3]);

		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		mainFrame.getContentPane().add(mainPanel);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(350, 400);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
}

class PictureComponent extends JComponent implements FocusListener,
		MouseListener {
	Image image;
	HashSet<PictureComponent> pcs = new HashSet<PictureComponent>();

	public PictureComponent(Image image) {
		this.image = image;
		setPreferredSize(new Dimension(125, 125));
		setFocusable(true);
		setTransferHandler(new PictureTransferHandler(this));
		addFocusListener(this);
		addMouseListener(this);
	}

	public HashSet<PictureComponent> getPcs() {
		return this.pcs;
	}

	public void setPcs(HashSet<PictureComponent> pcs) {
		this.pcs = pcs;
	}

	public Image getImage() {
		return this.image;
	}

	public void setImage(Image image) {
		this.image = image;
		repaint();
	}

	public void paintComponent(Graphics graphics) {
		Graphics g = graphics.create();
		g.setColor(Color.white);
		g.fillRect(0, 0, 125, 125);
		if (image != null) {
			g.drawImage(image, 0, 0, 125, 125, Color.BLACK, this);
		}
		if (isFocusOwner()) {
			g.setColor(Color.red);
		} else {
			g.setColor(Color.black);
		}
		g.drawRect(0, 0, 125, 125);
		g.dispose();
	}

	public void focusGained(FocusEvent e) {
		repaint();
	}

	public void focusLost(FocusEvent e) {
		repaint();
	}

	public void mouseClicked(MouseEvent e) {
		requestFocusInWindow();
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		JComponent c = (JComponent) e.getSource();
		TransferHandler handler = c.getTransferHandler();
		//
		handler.exportAsDrag(c, e, TransferHandler.COPY);
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}
}

/**
 * 传输操作提供数据
 */
class TransferablePicture implements Transferable {
	DataFlavor[] flavors = { DataFlavor.imageFlavor };
	Image image;

	public TransferablePicture(Image image) {
		this.image = image;
	}

	public DataFlavor[] getTransferDataFlavors() {
		return flavors;
	}

	public Object getTransferData(DataFlavor flavor) {
		if (flavor.equals(DataFlavor.imageFlavor)) {
			return image;
		}
		return null;
	}

	public boolean isDataFlavorSupported(DataFlavor flavor) {
		System.out.println("isDataFlavorSupported 返回此对象是否支持指定的数据 flavor");
		return flavor.equals(DataFlavor.imageFlavor);
	}
}

/**
 * 用于处理往返于 Swing 组件的 Transferable 的传输
 * 
 */
class PictureTransferHandler extends TransferHandler {

	private JComponent targer;

	public PictureTransferHandler(JComponent t) {
		targer = t;
	}

	public Transferable createTransferable(JComponent c) {
		System.out.println("createTransferable 返回要传输的数据表示形式");
		PictureComponent pc = (PictureComponent) c;
		return new TransferablePicture(pc.getImage());
	}

	public boolean canImport(JComponent c, DataFlavor[] flavors) {
		System.out.println("canImport 判断是否可以将该数据插入到组件");

		for (DataFlavor flavor : flavors) {
			if (flavor.equals(DataFlavor.imageFlavor)) {
				return true;
			}
		}
		return false;
	}

	public boolean importData(JComponent c, Transferable t) {
		System.out.println("importData 导致从剪贴板或 DND 放置操作向组件的传输");
		if (canImport(c, t.getTransferDataFlavors())) {
			PictureComponent pc = (PictureComponent) c;
			try {
				Image image = (Image) t.getTransferData(DataFlavor.imageFlavor);
				pc.setImage(image);
				System.out.println("它能接受");
				return true;
			} catch (UnsupportedFlavorException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("它不能接受");
		return false;
	}

	@Override
	public boolean canImport(TransferSupport support) {
		System.out.println("canImport 在拖放操作期间重复调用此方法，以允许开发人员配置传输的可接受性属性");
		return super.canImport(support);
	}

	public void exportDone(JComponent c, Transferable data, int action) {
		System.out.println("exportDone 在导出数据之后调用。");
		PictureComponent picture = (PictureComponent) c;
		if (action == MOVE) {
			picture.setImage(null);
		}
	}

	public int getSourceActions(JComponent c) {
		System.out.println("getSourceActions 返回源支持的传输动作的类型");
		// return COPY;
		return COPY_OR_MOVE;
	}

	public Icon getVisualRepresentation(Transferable t) {
		System.out.println("getVisualRepresentation 返回一个建立传输外观的对象");
		Image image = null;
		try {
			System.out.println("getVisualRepresentation");
			image = (Image) t.getTransferData(DataFlavor.imageFlavor);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ImageIcon(image);
	}
}
