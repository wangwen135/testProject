package com.swing.swingWorker;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;

public class SwingWorkerTest2 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingWorkerTest2 frame = new SwingWorkerTest2();
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
	public SwingWorkerTest2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 591, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JProgressBar progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setBounds(10, 10, 286, 19);
		contentPane.add(progressBar);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(357, 10, 54, 15);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("New button");

		btnNewButton.setBounds(357, 55, 93, 23);
		contentPane.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 54, 286, 127);
		contentPane.add(scrollPane);

		final JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		final PrimeNumbersTask task = new PrimeNumbersTask(textArea, 20);
		task.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				System.out.println(evt.getPropertyName());
				System.out.println(evt.getOldValue());
				System.out.println(evt.getNewValue());
				if ("progress".equals(evt.getPropertyName())) {

					progressBar.setValue((Integer) evt.getNewValue());
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				task.execute();
				// try {
				// System.out.println(task.get());
				// } catch (InterruptedException e1) {
				// e1.printStackTrace();
				// } catch (ExecutionException e1) {
				// e1.printStackTrace();
				// }
			}
		});
	}
}

class PrimeNumbersTask extends SwingWorker<List<Integer>, Integer> {
	private JTextArea textArea;
	private int numbersToFind;
	boolean enough = false;
	Integer number;
	List<Integer> numbers = new ArrayList<Integer>();

	PrimeNumbersTask(JTextArea textArea, int numbersToFind) {
		this.textArea = textArea;
		this.numbersToFind = numbersToFind;
	}

	public Integer nextPrimeNumber() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Integer tmp = new Random().nextInt(numbersToFind);
		numbers.add(tmp);
		if (numbers.size() >= numbersToFind) {
			enough = true;
		}

		return tmp;
	}

	@Override
	public List<Integer> doInBackground() {
		while (!enough && !isCancelled()) {
			number = nextPrimeNumber();
			publish(number);
			setProgress(100 * numbers.size() / numbersToFind);
		}
		return numbers;
	}

	@Override
	protected void process(List<Integer> chunks) {
		for (int number : chunks) {
			textArea.append(number + "\n");
		}
	}

	@Override
	protected void done() {
		System.out.println("完成了，结果是：");
		try {
			System.out.println(get().toString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
