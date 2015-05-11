package com.swing.component.panel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.SwingConstants;

public class PanelTest extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PanelTest frame = new PanelTest();
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
	public PanelTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 559, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(138, 72, 298, 94);
		contentPane.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JLabel label = new JLabel("22");
		label.setMaximumSize(new Dimension(18, 15));
		label.setSize(new Dimension(22, 0));
		label.setMinimumSize(new Dimension(22, 15));
		label.setPreferredSize(new Dimension(22, 15));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		panel.add(chckbxNewCheckBox);
		
		JLabel lblNewLabel = new JLabel("信息");
		panel.add(lblNewLabel);
	}
}
