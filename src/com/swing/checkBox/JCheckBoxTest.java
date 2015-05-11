package com.swing.checkBox;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JCheckBoxTest extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JCheckBoxTest frame = new JCheckBoxTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	JCheckBox checkBox;
	JCheckBox checkBox_1;
	/**
	 * Create the frame.
	 */
	public JCheckBoxTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		checkBox = new JCheckBox("普通");
		checkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBox.isSelected()){
					checkBox_1.setSelected(false);
				}
			}
		});
		checkBox.setToolTipText("<html><p>拆分标准的多品名</p>\r\n<p>品名按照<b><font color='red'>,</font></b>分隔 &nbsp;&nbsp; 数量单位按照<b><font color='red'>;</font></b>分隔</p></html>");
		checkBox.setBounds(40, 56, 103, 23);
		contentPane.add(checkBox);
		
		checkBox_1 = new JCheckBox("台湾");
		checkBox_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkBox_1.isSelected()){
					checkBox.setSelected(false);
				}
			}
		});
		checkBox_1.setToolTipText("<html><p>拆分台湾格式的多品名</p>品名按照<b><font color='red'>,</font></b>分隔 &nbsp;&nbsp; 数量单位按照<b><font color='red'>,</font></b>分隔</html>");
		checkBox_1.setBounds(212, 56, 103, 23);
		contentPane.add(checkBox_1);
	}
}
