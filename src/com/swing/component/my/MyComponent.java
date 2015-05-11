package com.swing.component.my;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import java.awt.SystemColor;
import java.awt.Cursor;
import java.awt.Dimension;

public class MyComponent extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	final Point p = new Point(0,0);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyComponent frame = new MyComponent();
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
	public MyComponent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane jsp = new JScrollPane();
		contentPane.add(jsp, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(1000, 1000));
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		panel.setBackground(SystemColor.inactiveCaptionText);
		panel.setAutoscrolls(true);
		jsp.setViewportView(panel);
		//jsp.add(panel, BorderLayout.CENTER);
		
		panel.setLayout(null);
		
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				System.out.println("鼠标移动");
				System.out.println(e.getX());
				System.out.println(e.getY());
				System.out.println("鼠标原位置");
				System.out.println(p.getX());
				System.out.println(p.getY());
				
				Component c= e.getComponent();
				Point loca = c.getLocation();
				
				Double x =loca.getX()+(e.getX()-p.getX());
				Double y =loca.getY()+(e.getY()-p.getY());
				loca.setLocation(x, y);
				c.setLocation(loca);
				//滚动
				
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("鼠标按下时：");
				System.out.println(p);
				System.out.println(e.getX());
				System.out.println(e.getY());
				p.setLocation(e.getX(), e.getY());	
			}
		});
		btnNewButton.setBounds(298, 67, 72, 42);
		panel.add(btnNewButton);
		
//		textField = new JTextField();
//		textField.setColumns(10);
//		panel.add(textField);
		
		//JRegularStarButton j = new JRegularStarButton();
		//j.setBounds(10, 10, 100, 100);
		//panel.add(j);
		

		//MyCpt m = new MyCpt();
		//m.setBounds(110, 10, 100, 100);
		//m.setBorder(BorderFactory.createEmptyBorder());
		//m.setBackground(Color.black);
		//m.setForeground(Color.red);
	//	panel.add(m);

	}
}

class MyCpt extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4285362396664950239L;
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		
		
		g.draw3DRect(getX(), getY(), getWidth()-10,getHeight()-10,true);
		g.drawString("aaaaa", 0, 0);
		g.drawOval(5, 5, 10, 10);
		g.drawOval(5, 5, 20, 20);
		g.drawOval(5, 5, 30, 30);
		g.drawOval(5, 5, 40, 40);
		g.drawOval(5, 5, 50, 50);
		g.drawOval(5, 5, 60, 60);
		g.drawOval(5, 5, 70, 70);
		g.drawOval(5, 5, 80, 80);
		g.drawOval(5, 5, 90, 90);
		g.drawOval(5, 5, 100, 100);
		g.drawOval(5, 5, 110, 110);
	}
	
	@Override
	public void setBounds(int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		super.setBounds(x, y, width, height);
	}
}
