package com.image;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * <pre>
 * 描述：显示大图片的对话框，将按照图片实际大小进行显示
 *      当图片超过MAX_WIDTH、MAX_HEIGHT 时将显示滚动条
 * 
 * 调用方法：
 * 	setImageAndShow(BufferedImage image)
 * 	setByteImageAndShow(byte[] imageByte)
 * </pre>
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2013-9-13      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class ShowBigImageDialog extends JDialog {

	private static final long serialVersionUID = -1025444637997682151L;

	/**
	 * 最大宽度
	 */
	public static final int MAX_WIDTH = 1000;

	/**
	 * 最大高度
	 */
	public static final int MAX_HEIGHT = 700;

	private JPanel imagePanel;

	private BufferedImage image;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ShowBigImageDialog dialog = new ShowBigImageDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ShowBigImageDialog() {

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					setVisible(false);
				}

			}
		});

		setTitle("查看大图片");
		// setSize(new Dimension(450, 300));
		// setPreferredSize(new Dimension(450, 300));
		setBounds(100, 100, 450, 300);

		setAlwaysOnTop(true);

		getContentPane().setLayout(new BorderLayout());

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		imagePanel = new JPanel() {

			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				if (image != null) {
					Graphics gg = g.create();
					// 先填充
					gg.setColor(Color.WHITE);
					gg.fillRect(0, 0, getWidth(), getHeight());

					gg.drawImage(image, 0, 0, getWidth(), getHeight(), this);
					gg.dispose();
				} else {
					super.paintComponent(g);
				}
			}

		};
		scrollPane.setViewportView(imagePanel);

		imagePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					setVisible(false);
				}

			}
		});
	}

	/**
	 * <pre>
	 * 设置图片并且显示
	 * </pre>
	 * 
	 * @param image
	 */
	public void setImageAndShow(BufferedImage image) {
		if (image == null) {
			JOptionPane.showMessageDialog(null, "图片为空！");
			return;
		}
		this.image = image;

		showDialog();

	}

	/**
	 * <pre>
	 * 设置图片并且显示
	 * </pre>
	 * 
	 * @param imageByte
	 */
	public void setByteImageAndShow(byte[] imageByte) {
		if (imageByte == null || imageByte.length < 1) {
			JOptionPane.showMessageDialog(null, "图片为空！");
			return;
		}

		try {
			BufferedImage image = ImageIO.read(new ByteArrayInputStream(
					imageByte));
			this.image = image;
			showDialog();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "获取图片出错", "错误",
					JOptionPane.ERROR_MESSAGE);

		}

	}

	/**
	 * <pre>
	 * 计算大小并显示
	 * </pre>
	 */
	private void showDialog() {

		int imageWidth = image.getWidth();

		int width = imageWidth > MAX_WIDTH ? MAX_WIDTH : imageWidth;

		int imageHeight = image.getHeight();

		int height = imageHeight > MAX_HEIGHT ? MAX_HEIGHT : imageHeight;

		setSize(width + 12, height + 37);

		imagePanel.setSize(imageWidth, imageHeight);
		imagePanel.setPreferredSize(new Dimension(imageWidth, imageHeight));

		// pack();

		setVisible(true);
	}
}
