package com.swing.window;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.awt.AWTUtilities;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

/**
 * 描述：
 * 
 * <pre>
 * HISTORY
 * 透明窗体：
 * 通过com.sun.awt.AWTUtilities.setWindowOpacity(Window window,float f)方法来设置。window 为要呈现的窗体，f为透明度，范围为0.0-1.0之间。
 * 不规则窗体
 * 通过方法：    com.sun.awt.AWTUtilities.setWindowShape(Window window, Shape shape);来设置
 * 
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class OpaCityFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpaCityFrame frame = new OpaCityFrame();
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
	public OpaCityFrame() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("New label");
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("New button");
		contentPane.add(btnNewButton, BorderLayout.CENTER);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
		contentPane.add(rdbtnNewRadioButton, BorderLayout.WEST);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("New radio button");
		contentPane.add(rdbtnNewRadioButton_1, BorderLayout.EAST);
		
		JComboBox comboBox = new JComboBox();
		contentPane.add(comboBox, BorderLayout.SOUTH);
		
		//setSize(new Dimension(300,400));
		
		
		
		AWTUtilities.setWindowShape(this, new Ellipse2D.Double(5,5, getWidth(), getHeight()));

		AWTUtilities.setWindowOpacity(this, 0.5f);
	}

}
