package com.image;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.FlowLayout;

/**
 * <pre>
 * 描述：图片面板
 * 
 * </pre>
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2013-9-12      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class ImagePanel extends JPanel {

	private static final long serialVersionUID = -3411106171875076175L;

	/**
	 * 上传图片标记
	 */
	private boolean uploadImage = false;

	private BufferedImage image;

	private JLabel downLoadLabel;

	private JLabel upLoadLabel;

	private JLabel flagLabel;

	private JFileChooser chooser = new JFileChooser();

	private FileNameExtensionFilter filter = new FileNameExtensionFilter(
			"Images", "jpg", "jpge", "gif", "png", "bmp");

	private ShowBigImageDialog dialog;

	public ImagePanel() {
		setToolTipText("双击看大图");

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() != 2 || image == null) {
					return;
				}
				if (dialog == null) {
					dialog = new ShowBigImageDialog();
				}

				dialog.setImageAndShow(image);

			}
		});
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setHgap(50);

		downLoadLabel = new JLabel("下载图片");
		downLoadLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		downLoadLabel.setFont(new Font("宋体", Font.BOLD, 12));
		add(downLoadLabel);

		downLoadLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				downLoadImage();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				downLoadLabel.setForeground(Color.RED);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				downLoadLabel.setForeground(Color.BLACK);
			}
		});

		upLoadLabel = new JLabel("上传图片");
		upLoadLabel.setFont(new Font("宋体", Font.BOLD, 12));
		upLoadLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(upLoadLabel);

		upLoadLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				upLoadImage();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				upLoadLabel.setForeground(Color.RED);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				upLoadLabel.setForeground(Color.BLACK);
			}
		});

		flagLabel = new JLabel("★");
		flagLabel.setForeground(Color.RED);
		flagLabel.setToolTipText("新上传的图片");
		flagLabel.setVisible(false);
		add(flagLabel);
	}

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

	/**
	 * <pre>
	 * 上传图片
	 * </pre>
	 */
	private void upLoadImage() {
		chooser.setFileFilter(filter);

		if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(this)) {
			File f = chooser.getSelectedFile();
			try {
				BufferedImage image = ImageIO.read(f);
				setImage(image);

				flagLabel.setVisible(true);

				uploadImage = true;

			} catch (IOException e1) {
				JOptionPane.showMessageDialog(this,
						"读取图片文件异常！\n" + e1.getMessage(), "错误",
						JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	/**
	 * <pre>
	 * 下载图片
	 * </pre>
	 * 
	 */
	private void downLoadImage() {
		if (image == null) {
			JOptionPane.showMessageDialog(this, "图片为空！");
			return;
		}

		chooser.setFileFilter(filter);

		if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {

			File f = chooser.getSelectedFile();
			String fname = f.getName();
			if (!fname.toUpperCase().endsWith(".PNG")) {
				f = new File(f.getParentFile(), fname + ".png");
			}

			try {
				boolean r = ImageIO.write(image, "png", f);
				if (r) {
					JOptionPane.showMessageDialog(this, "下载完成！");
				} else {
					JOptionPane.showMessageDialog(this, "写入图片失败！", "错误",
							JOptionPane.ERROR_MESSAGE);
				}

			} catch (IOException e1) {
				JOptionPane.showMessageDialog(this,
						"导出图片文件异常！\n" + e1.getMessage(), "错误",
						JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	/**
	 * <pre>
	 * 获取图片
	 * </pre>
	 * 
	 * @return
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * <pre>
	 * 设置图片
	 * </pre>
	 * 
	 * @param image
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
		this.uploadImage = false;
		flagLabel.setVisible(false);
		repaint();
	}

	/**
	 * <pre>
	 * 设置字节形式的图片
	 * </pre>
	 * 
	 * @param byteArray
	 *            图片文件字节数组
	 * @throws IOException
	 */
	public void setByteImage(byte[] byteArray) throws IOException {
		if (byteArray == null || byteArray.length < 1) {
			setImage(null);
		}
		BufferedImage image = ImageIO.read(new ByteArrayInputStream(byteArray));
		setImage(image);
	}

	/**
	 * <pre>
	 * 获取图片的字节表现形式
	 * </pre>
	 * 
	 * @return 图片文件字节数组
	 * @throws IOException
	 */
	public byte[] getByteImage() throws IOException {
		if (image == null) {
			return null;
		}

		ByteArrayOutputStream boutStream = new ByteArrayOutputStream();
		ImageIO.write(image, "png", boutStream);
		return boutStream.toByteArray();

	}

	/**
	 * <pre>
	 * 是否有上传新的图片
	 * 从调用setImage、setByteImage方法之后
	 * </pre>
	 * 
	 * @return
	 */
	public boolean isUploadImage() {
		return uploadImage;
	}

}
