package com.swing.component;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

public class JTextPaneTabTest extends JFrame {
	public JTextPaneTabTest() {
		JPanel panel = new JPanel();
		setContentPane(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("monospaced ", Font.PLAIN, 12));
		textPane.setText("abcdefghijklmnop\n\tone\n\t\ttwo ");
		JScrollPane scrollPane = new JScrollPane(textPane);
		scrollPane.setPreferredSize(new Dimension(200, 200));
		panel.add(scrollPane);
		setTabs(textPane, 4);
	}

	public void setTabs(JTextPane textPane, int charactersPerTab) {
		FontMetrics fm = textPane.getFontMetrics(textPane.getFont());
		int charWidth = fm.charWidth('w');
		int tabWidth = charWidth * charactersPerTab;

		TabStop[] tabs = new TabStop[10];

		for (int j = 0; j < tabs.length; j++) {
			int tab = j + 1;
			tabs[j] = new TabStop(tab * tabWidth);
		}

		TabSet tabSet = new TabSet(tabs);
		SimpleAttributeSet attributes = new SimpleAttributeSet();
		StyleConstants.setTabSet(attributes, tabSet);
		textPane.getStyledDocument().setParagraphAttributes(0,
				textPane.getDocument().getLength(), attributes, true);
	}

	public static void main(String[] args) {
		JTextPaneTabTest frame = new JTextPaneTabTest();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}