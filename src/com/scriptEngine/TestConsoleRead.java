package com.scriptEngine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.Reader;
import java.nio.ByteBuffer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.Font;

public class TestConsoleRead extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 732226993526497279L;
	private JPanel contentPane;
	private JTextField textField;
	private TTextPane textPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestConsoleRead frame = new TestConsoleRead();
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
	public TestConsoleRead() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		textPane = new TTextPane();
		textPane.setFont(new Font("Arial", Font.PLAIN, 12));
		TTDocument document = new TTDocument(textPane);
		textPane.setDocument(document);
		scrollPane.setViewportView(textPane);

		System.setIn(document.getInputStream());

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);

		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(25);

		JButton btnNewButton = new JButton("send");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPane.append(textField.getText());
			}
		});
		panel_1.add(btnNewButton);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);

		JButton btnNewButton_1 = new JButton("读取系统控制台");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						// 读取控制台
						InputStream inputs = System.in;
						byte[] b = new byte[100];
						int lenght;
						try {
							while ((lenght = inputs.read(b)) > 0) {
								String str = new String(b, 0, lenght);
								if ("exit\r\n".equals(str)
										|| "exit\n".equals(str)) {
									break;
								} else {

									textPane.append(getName() + ":" + str);
								}
							}

						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.out.println("end...");
					}
				}).start();

			}
		});
		panel_2.add(btnNewButton_1);

		JButton btnNewButton_22 = new JButton("读取模拟控制台");
		btnNewButton_22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		panel_2.add(btnNewButton_22);

		JButton btnNewButton_3 = new JButton("New button");
		panel_2.add(btnNewButton_3);
	}

}

class TTextPane extends JTextPane {
	private static final long serialVersionUID = 1L;

	public void append(String str) {
		Document doc = getDocument();
		if (doc != null) {
			try {
				((TTDocument) doc).appendString(str);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
	}

	public void setPosition(int position) {
		setSelectionStart(position);
		setSelectionEnd(position);
	}

}

class TTDocument extends DefaultStyledDocument {
	TTextPane textPane;
	
	PipedReader pipread;
	PipedWriter pipwriter;

	PipedInputStream pipinStream;
	PipedOutputStream pipoutStream;

	public TTDocument(TTextPane tarea) {
		this.textPane = tarea;

		pipread = new PipedReader();
		pipwriter = new PipedWriter();

		try {
			pipread.connect(pipwriter);
		} catch (IOException e) {
			e.printStackTrace();
		}

		pipinStream = new PipedInputStream();

		pipoutStream = new PipedOutputStream();

		try {
			pipinStream.connect(pipoutStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5359918496423825445L;
	// 可编辑的长度，大于这个长度可以编辑
	int editLenght = 0;

	@Override
	public void remove(int offs, int len) throws BadLocationException {
		if (offs >= editLenght) {
			super.remove(offs, len);
		} else {
			textPane.setPosition(getLength());
		}
	}

	@Override
	public void insertString(int offs, String str, AttributeSet a)
			throws BadLocationException {
		if (offs >= editLenght) {
			if (a != null) {
				if (a instanceof SimpleAttributeSet) {
					StyleConstants.setForeground((SimpleAttributeSet) a,
							Color.GREEN);
				} else {
					SimpleAttributeSet attrSet = new SimpleAttributeSet();
					StyleConstants.setForeground(attrSet, Color.GREEN);
					a = attrSet;
				}

			}
			if ("\n".equals(str)) {
				offs = getLength();
				textPane.setPosition(getLength());
			}

			int oldLenght = editLenght;
			// \n
			for (int i = 0; i < str.length(); i++) {
				if ('\n' == str.charAt(i)) {
					editLenght = (getLength() + i + 1);
				}
			}
			super.insertString(offs, str, a);

			try {
				if (oldLenght != editLenght) {
					String text = getText(oldLenght, editLenght - oldLenght);

					pipwriter.write(text);

					pipoutStream.write(text.getBytes());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			textPane.setPosition(getLength());
		}
	}

	/**
	 * <pre>
	 * 追加文本
	 * </pre>
	 * 
	 * @param str
	 * @throws BadLocationException
	 */
	public void appendString(String str) throws BadLocationException {
		super.insertString(getLength(), str, null);
		editLenght = getLength();
		textPane.setPosition(getLength());
	}

	public int getEditLenght() {
		return editLenght;
	}

	public void setEditLenght(int editLenght) {
		this.editLenght = editLenght;
	}

	public Reader getReader() {
		return pipread;
	}

	public InputStream getInputStream() {
		return pipinStream;
	}
}

class TTInputStream extends InputStream {

	//定时清空缓存
	
	//主动清空缓存
	
	//缓冲器
	ByteBuffer byteBuffer =ByteBuffer.allocate(102400);
	
	//计数器
	
	int count =0;
	
	int mark = 0;
	
	int pos =0;
	
	@Override
	public int read(byte[] b) throws IOException {
		
		return super.read(b);
	}
	
	@Override
	public void close() throws IOException {
		
		super.close();
	}
	
	
	@Override
	public int read() throws IOException {
		
		return 0;
	}
	

}
