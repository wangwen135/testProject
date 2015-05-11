package com.swing.component.label;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

public class LabelTest extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LabelTest frame = new LabelTest();
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
	public LabelTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html><font color=green>测试html方法包围的文本在不同的对齐方式下的显示</font></html>");
		lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel.setBackground(Color.RED);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		lblNewLabel.setBounds(143, 97, 84, 76);
		
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("<html><font color=\"005000\">商品名称1商品名称2aaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbb商品名称3商品名称4</font></html>"){

			@Override
			public void repaint() {
				System.out.println("repaint");
				super.repaint();
			}
			
			@Override
			public void paint(Graphics g) {
				System.out.println("paint");
				super.paint(g);
			}
			
			@Override
			protected void paintComponent(Graphics g) {
				System.out.println("paintComponent");
				super.paintComponent(g);
			}
			
		};
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblNewLabel_1.setBounds(33, 24, 122, 49);
		
		contentPane.add(lblNewLabel_1);
	}
}
