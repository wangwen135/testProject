package com.swing.component.tabbedPane;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TabbedPaneEvent extends JFrame {

	private JPanel contentPane;

	private JTabbedPane tabbedPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabbedPaneEvent frame = new TabbedPaneEvent();
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
	public TabbedPaneEvent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				System.out.println(e);
				System.out.println(tabbedPane.getSelectedIndex());
			}
		});
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		tabbedPane
				.addTab("标签一",
						new ImageIcon(
								TabbedPaneEvent.class
										.getResource("/org/apache/log4j/lf5/viewer/images/lf5_small_icon.gif")),
						panel, null);
		tabbedPane.setDisplayedMnemonicIndexAt(0, 1);
		tabbedPane.setForegroundAt(0, Color.GREEN);
		tabbedPane.setDisabledIconAt(
				0,
				new ImageIcon(TabbedPaneEvent.class
						.getResource("/sun/print/resources/duplex.png")));
		tabbedPane.setBackgroundAt(0, Color.YELLOW);
		tabbedPane.setEnabledAt(0, true);

		JLabel lblNewLabel = new JLabel("New label");
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);

		JButton btnNewButton = new JButton("New button");
		panel_1.add(btnNewButton);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);

		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		panel_2.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("New button");
		panel_2.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("New button");
		panel_2.add(btnNewButton_3);
	}

}
