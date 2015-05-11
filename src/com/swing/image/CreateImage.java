package com.swing.image;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CreateImage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateImage frame = new CreateImage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		BufferedImage bimg = (BufferedImage) createImage(getWidth(),getHeight());
		Graphics2D big = bimg.createGraphics();

		big.setColor(Color.green);
		Rectangle graphOutlineRect = new Rectangle();
		graphOutlineRect.setBounds(50, 50, 50, 50);
		big.draw(graphOutlineRect);
		
		g.drawImage(bimg, 0,0 ,null);
		
		
	}
	/**
	 * Create the frame.
	 */
	public CreateImage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("文本框");
		label.setBounds(33, 28, 54, 15);
		contentPane.add(label);

		textField = new JTextField();
		textField.setBounds(81, 25, 66, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel label_1 = new JLabel("下拉框");
		label_1.setBounds(33, 70, 54, 15);
		contentPane.add(label_1);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "下拉选项1",
				"下拉选项1", "下拉选项1" }));
		comboBox.setBounds(100, 67, 88, 21);
		contentPane.add(comboBox);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(308, 10, 116, 71);
		contentPane.add(textArea);

		JLabel label_2 = new JLabel("文本域");
		label_2.setBounds(242, 14, 54, 15);
		contentPane.add(label_2);

		JButton button = new JButton("按钮");
		button.setBounds(302, 106, 93, 23);
		contentPane.add(button);

		JButton btnCreateimage = new JButton("CreateImage");
		btnCreateimage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 成长一个图片
				Image image = createImage(getWidth(), getHeight());
				//image.getw
				
//				BufferedImage bufimage = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_BGR);
//				Graphics g = bufimage.createGraphics();				
//				g.drawImage(image, 0, 0, null);
//				g.dispose();
				
				BufferedImage bufimage = Image2BufferImage.toBufferedImage(image);
				
				try {
					FileOutputStream fos = new FileOutputStream("./CreateImage.JPG");
					ImageIO.write(bufimage, "JPG", fos);
					fos.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
								
			}
		});
		btnCreateimage.setBounds(10, 206, 116, 23);
		contentPane.add(btnCreateimage);
	}
}
