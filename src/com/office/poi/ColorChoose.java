package com.office.poi;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.apache.poi.hssf.util.HSSFColor;

public class ColorChoose extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ColorChoose frame = new ColorChoose();
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
	public ColorChoose() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 538, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// JColorChooser.showDialog(null, "测试", null);
				System.out.println(btnNewButton.getUIClassID());
			}
		});
		btnNewButton.setBounds(24, 20, 93, 23);
		contentPane.add(btnNewButton);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(24, 110, 229, 146);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(7, 8, 1, 1));

		JPanel panel = new JPanel();
		panel.setBounds(276, 110, 229, 146);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(7, 8, 0, 0));

		Hashtable<Integer, HSSFColor> colorTable = HSSFColor.getIndexHash();
		for (int i = 0; i < 100; i++) {// 按照顺序来
			if (colorTable.containsKey(i)) {
				final HSSFColor hssfc = colorTable.get(i);
				short[] s = hssfc.getTriplet();
				JButton btn = new JButton() {
					private static final long serialVersionUID = -5570545546841490532L;
					@Override
					protected void paintComponent(Graphics g) {
						super.paintComponent(g);
						Color c = getForeground();
						g.setColor(c);
						g.fillRect(3, 3, getWidth()-6, getHeight()-6);
//						if (isFocusPainted() && hasFocus()) {
//							Color tmpc = new Color((255- c.getRed()),(255-c.getGreen()),(255 - c.getBlue()));
//							g.setColor(tmpc);
//							g.drawRect(0, 0, getWidth() - 2, getHeight() - 2);
//						}

					}
				};
				btn.setForeground(new Color(s[0], s[1], s[2]));

				btn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("" + hssfc);
					}
				});
				panel.add(btn);
			}
		}

		for (Entry<Integer, HSSFColor> element : colorTable.entrySet()) {
			JButton btn = new JButton();
			HSSFColor hssfc = element.getValue();
			short[] s = hssfc.getTriplet();
			btn.setBackground(new Color(s[0], s[1], s[2]));
			panel_1.add(btn);
		}

	}
}
