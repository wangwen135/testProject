package com.swing.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class UITest extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UITest frame = new UITest();
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
	public UITest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JButton btn_1 = new JButton("New button");
		btn_1.setBounds(122, 58, 93, 23);
		contentPane.add(btn_1);

		JButton btn_2 = new JButton("New button");
		btn_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_1.setUI(new btnTestUI());
			}
		});
		btn_2.setBounds(122, 103, 93, 23);
		contentPane.add(btn_2);

		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance()
				.createTitle("New JGoodies title");
		lblNewJgoodiesTitle.setBounds(6, 6, 105, 18);
		contentPane.add(lblNewJgoodiesTitle);
		
		JComboBox comboBox_test = new JComboBox();
		comboBox_test.setModel(new DefaultComboBoxModel(new String[] {"", "客户自行网付", "先垫后收", "先收后付", "拒付"}));
		comboBox_test.setBounds(178, 159, 118, 21);
		contentPane.add(comboBox_test);
	}
}

class btnTestUI extends javax.swing.plaf.basic.BasicButtonUI {

	@Override
	public void paint(Graphics g, JComponent c) {
		// super.paint(g, c);
		System.out.println("----------------------");
		g.setFont(new Font("宋体", Font.BOLD, 14));
		System.out.println(g.getFont());
		g.setColor(Color.RED);
		System.out.println(g.getColor());

		g.drawString("hello", 1, 1);
		g.drawLine(0, 0, 22, 12);

	}
}
