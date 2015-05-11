package com.swing.IMTest;

import java.awt.EventQueue;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class InputFrameTest extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.setProperty("java.awt.im.style", "on-the-spot");
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					File f = new File("");
					System.out.println(f.getAbsolutePath());
					Process p = Runtime.getRuntime().exec("java -version");
					InputStream ins = p.getInputStream();
					InputStream eins = p.getErrorStream();

					BufferedReader bufr = new BufferedReader(
							new InputStreamReader(eins));

					String line = bufr.readLine();
					while (line != null) {
						System.out.println(line);
						line = bufr.readLine();
					}

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		t.start();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InputFrameTest frame = new InputFrameTest();
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
	public InputFrameTest() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 28, 402, 306);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {
				{ "aaa", "bbb", "ccc", "ddd", "eee", "fff" },
				{ null, null, null, null, null, null },
				{ null, null, null, null, null, null },
				{ null, null, null, null, null, null }, }, new String[] {
				"New column", "New column", "New column", "New column",
				"New column", "New column" }));
		scrollPane.setViewportView(table);
	}
}
