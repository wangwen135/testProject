package com.swing.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ThreadTest2 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThreadTest2 frame = new ThreadTest2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Thread t;
	private static boolean run = true;

	public void createThread() {
		run = true;

		t = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("线程开始");
				while (run) {
					for (int i = 0; i < 10; i++) {
						System.out.println("one ...");

						if (!run) {
							break;
						}

						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					if (!run) {
						break;
					}

					System.out.println("main sleep ...");
					try {
						Thread.sleep(300000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}

				//
				System.out.println("线程结束了");
			}

		});
	}

	/**
	 * Create the frame.
	 */
	public ThreadTest2() {

		// //
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 397);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton button = new JButton("启动");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createThread();
				t.start();
			}
		});
		button.setBounds(103, 33, 93, 23);
		contentPane.add(button);

		JButton button_1 = new JButton("停止");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				run = false;
				if (t.getState() == Thread.State.TIMED_WAITING) {
					t.interrupt();
				}
			}
		});
		button_1.setBounds(302, 33, 93, 23);
		contentPane.add(button_1);

		JButton button_2 = new JButton("状态");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(t.getState());
			}
		});
		button_2.setBounds(103, 87, 93, 23);
		contentPane.add(button_2);

		JButton button_3 = new JButton("持续状态");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 1000; i++) {
					System.out.println(t.getState());
				}
			}
		});
		button_3.setBounds(302, 87, 93, 23);
		contentPane.add(button_3);
	}
}
