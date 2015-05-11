package com.image;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ShowImagePanel extends JFrame {

	private JPanel contentPane;
	private ImagePanel imagePanel;
	private JButton btnnull;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowImagePanel frame = new ShowImagePanel();
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
	public ShowImagePanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 734, 603);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		imagePanel = new ImagePanel();
		imagePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		imagePanel.setBounds(49, 188, 646, 371);
		contentPane.add(imagePanel);

		JButton button = new JButton("从文件加载图片");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				readImageFromFile();
			}
		});
		button.setBounds(29, 10, 177, 23);
		contentPane.add(button);

		btnnull = new JButton("设置为null");
		btnnull.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imagePanel.setImage(null);
			}
		});
		btnnull.setBounds(236, 10, 93, 23);
		contentPane.add(btnnull);

		btnNewButton = new JButton("读取文件到字节");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				readImage4File2Byte();
			}
		});
		btnNewButton.setBounds(29, 54, 177, 23);
		contentPane.add(btnNewButton);

		JButton button_1 = new JButton("设置字节到图片");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					imagePanel.setByteImage(tmpByte);
				} catch (IOException e1) {

					e1.printStackTrace();
				}
			}
		});
		button_1.setBounds(29, 94, 177, 23);
		contentPane.add(button_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(236, 43, 452, 100);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

	}

	private byte[] tmpByte;
	private JTextArea textArea;

	private void readImage4File2Byte() {

		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Images",
				"jpg", "gif", "png");
		chooser.setFileFilter(filter);
		if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(this)) {
			File f = chooser.getSelectedFile();

			ByteArrayOutputStream baOutSt = new ByteArrayOutputStream();

			byte[] b = new byte[8192];

			FileInputStream fis = null;

			try {
				fis = new FileInputStream(f);

				int size;
				while ((size = fis.read(b)) != -1) {
					baOutSt.write(b, 0, size);
				}

				System.out.println("读完");

				tmpByte = baOutSt.toByteArray();

				// 写到文本框中

				String sb = new String(tmpByte);

				System.out.println("转换完成");

				if (sb.length() > 1000) {
					sb = sb.substring(0, 1000);
				}
				textArea.setText(sb);

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}

	}

	private void readImageFromFile() {
		File currentDir = new File(".");
		JFileChooser chooser = new JFileChooser(currentDir);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Images",
				"jpg", "gif", "png", "bmp");

		chooser.setFileFilter(filter);

		if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(this)) {
			File f = chooser.getSelectedFile();
			try {
				BufferedImage image = ImageIO.read(f);
				imagePanel.setImage(image);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
	}
}
