package com.swing.window;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

public class ShapedWindow extends JFrame {
	public ShapedWindow() {
		super("不规则窗体");
		this.setLayout(new FlowLayout());
		this.add(new JButton("按钮"));
		this.add(new JCheckBox("复选按钮"));
		this.add(new JRadioButton("单选按钮"));
		this.add(new JProgressBar(0, 100));

		this.setSize(new Dimension(400, 400));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		com.sun.awt.AWTUtilities.setWindowShape(this, new Ellipse2D.Double(5,
				5, getWidth(), getHeight()));
		com.sun.awt.AWTUtilities.setWindowOpacity(this, 0.5f);
	}

	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Window w = new ShapedWindow();
				w.setVisible(true);

			}
		});
	}

}
