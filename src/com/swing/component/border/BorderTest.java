package com.swing.component.border;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;

public class BorderTest extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorderTest frame = new BorderTest();
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
	public BorderTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBebelborder = new JLabel("BebelBorder");
		lblBebelborder.setBorder(new BevelBorder(BevelBorder.RAISED, null, Color.GRAY, null, null));
		lblBebelborder.setBounds(66, 44, 100, 25);
		contentPane.add(lblBebelborder);
		
		JLabel lblNewLabel = new JLabel("EmptBorder");
		lblNewLabel.setBorder(new EmptyBorder(1, 2, 3, 0));
		lblNewLabel.setBounds(266, 44, 100, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("EtchedBorder");
		lblNewLabel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblNewLabel_1.setBounds(66, 114, 100, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("lineBorder");
		lblNewLabel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		lblNewLabel_2.setBounds(266, 114, 100, 25);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("MatteBorder");
		lblNewLabel_3.setBorder(new MatteBorder(2, 5, 1, 1, (Color) new Color(0, 0, 0)));
		lblNewLabel_3.setBounds(66, 185, 100, 25);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("SoftBevelBorder");
		lblNewLabel_4.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblNewLabel_4.setBounds(266, 185, 100, 25);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblTitledborder = new JLabel("TitledBorder");
		lblTitledborder.setBorder(new TitledBorder(null, "title", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		lblTitledborder.setBounds(66, 260, 143, 44);
		contentPane.add(lblTitledborder);
		
		JLabel label = new JLabel("SoftBevelBorder");
		label.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0))));
		label.setBounds(266, 266, 118, 44);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("组合边框");
		label_1.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
		label_1.setBounds(66, 339, 118, 44);
		contentPane.add(label_1);
	}
}
