package com.actionMap;

import java.awt.AWTKeyStroke;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.InputEvent;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class ExpandOrCollapse extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// 输入法
		// System.setProperty("java.awt.im.style","on-the-spot");

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExpandOrCollapse frame = new ExpandOrCollapse();
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
	public ExpandOrCollapse() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 708, 571);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		final JPanel North = new JPanel();
		North.addInputMethodListener(new InputMethodListener() {
			public void caretPositionChanged(InputMethodEvent event) {
			}

			public void inputMethodTextChanged(InputMethodEvent event) {
			}
		});
		North.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
			}
		});
		North.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				System.out.println("ancestorAdded");
			}

			public void ancestorMoved(AncestorEvent event) {
				System.out.println("ancestorMoved");
			}

			public void ancestorRemoved(AncestorEvent event) {
				System.out.println("ancestorRemoved");
			}
		});
		North.setPreferredSize(new Dimension(10, 100));
		North.setBorder(new TitledBorder(null, "north", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		contentPane.add(North, BorderLayout.NORTH);
		// North.setDebugGraphicsOptions(DebugGraphics.LOG_OPTION);

		JPanel west = new JPanel();
		west.setBorder(new TitledBorder(null, "west", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		contentPane.add(west, BorderLayout.WEST);
		// west.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);

		JButton btnNewButton = new JButton("New button");
		west.add(btnNewButton);

		final JPanel south = new JPanel();
		south.setPreferredSize(new Dimension(10, 100));
		south.setBorder(new TitledBorder(null, "south", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		contentPane.add(south, BorderLayout.SOUTH);
		// south.setDebugGraphicsOptions(DebugGraphics.BUFFERED_OPTION);

		JPanel ease = new JPanel();
		ease.setBorder(new TitledBorder(null, "east", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		contentPane.add(ease, BorderLayout.EAST);

		JButton btnNewButton_1 = new JButton("New button");
		ease.add(btnNewButton_1);

		JPanel center = new JPanel();
		center.setBorder(new TitledBorder(null, "center", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		contentPane.add(center, BorderLayout.CENTER);

		JButton btnExpand = new JButton("expand");
		btnExpand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 是不是安全的？？？
				// 跨线程
				// 只能在事件指派线程 上访问 Swing 组件
				final Timer t = new Timer(true);
				final TimerTask task2 = new TimerTask() {
					@Override
					public void run() {
						System.out.println("collapse");
						Dimension d = North.getPreferredSize();
						North.setPreferredSize(new Dimension(0, d.height - 1));
						North.revalidate();
						if (d.height <= 100) {
							this.cancel();
						}
					}
				};

				TimerTask task = new TimerTask() {
					@Override
					public void run() {
						System.out.println("expand");
						System.out.println(SwingUtilities
								.isEventDispatchThread());
						Dimension d = North.getPreferredSize();
						North.setPreferredSize(new Dimension(0, d.height + 6));
						North.revalidate();
						if (d.height > 100) {
							this.cancel();
							t.schedule(task2, 100, 10);
						}
					}
				};

				Dimension d = North.getPreferredSize();
				if (d.height < 100) {
					t.schedule(task, 100, 10);
				}

				// North.setPreferredSize(new Dimension(0, 100));
				// south.setPreferredSize(new Dimension(0, 100));
				// North.invalidate();
				// North.revalidate();
				// contentPane.doLayout();
				// North.setSize(North.getWidth(), North.getHeight()+10);
				// North.setBounds(North.getX(), North.getY(), North.getWidth(),
				// North.getHeight()+10);

			}
		});
		center.add(btnExpand);

		JButton btnCollapse = new JButton("collapse");
		btnCollapse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				North.setPreferredSize(new Dimension(0, 10));
				south.setPreferredSize(new Dimension(0, 10));
				// North.invalidate();
				North.revalidate();
				// contentPane.doLayout();
			}
		});
		center.add(btnCollapse);

		textField = new JTextField();
		textField.setInputVerifier(new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				JTextField tf = (JTextField) input;
				if ("pass".equals(tf.getText())) {
					return true;
				} else {
					JOptionPane.showMessageDialog(null, "请输入pass");
					return false;
				}

			}
		});
		center.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		InputMap cim = textField_1.getInputMap();
		System.out.println("已经有了的");
		KeyStroke[] keys = cim.allKeys();
		System.out.println(Arrays.toString(keys));

		for (int i = 0; i < keys.length; i++) {
			System.out.println("_____________________");
			System.out.println(keys[i]);
			Object obj = cim.get(keys[i]);
			System.out.println(obj.getClass().getName());
			System.out.println(obj);
			;
		}

		cim.put(KeyStroke.getKeyStroke('a'), "aaa");
		// cim.put(KeyStroke.getKeyStroke('b'), "bbb");

		textField_1.setInputMap(JComponent.WHEN_FOCUSED, cim);

		ActionMap am = new ActionMap();
		am.put("aaa", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("事件发生了");
				System.out.println(e);
			}
		});
		textField_1.setActionMap(am);

		// textField_1.addInputMethodListener(null);
		center.add(textField_1);
		textField_1.setColumns(10);
		// 焦点跳转
		// Set<? extends AWTKeyStroke> keystrokes = Collections.EMPTY_SET;
		Set<AWTKeyStroke> keystrokes = new HashSet<AWTKeyStroke>();
		keystrokes.add(KeyStroke.getKeyStroke(KeyEvent.VK_T, 0, true));

		textField_1.setFocusTraversalKeys(
				KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, keystrokes);

		Set<AWTKeyStroke> keystrokes2 = new HashSet<AWTKeyStroke>();
		keystrokes2.add(KeyStroke.getKeyStroke(KeyEvent.VK_T,
				InputEvent.SHIFT_DOWN_MASK, true));

		textField_1.setFocusTraversalKeys(
				KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, keystrokes2);

		textField_2 = new JTextField();
		textField_2.addInputMethodListener(new InputMethodListener() {
			public void caretPositionChanged(InputMethodEvent event) {
				System.out.println("caretPositionChanged");
				System.out.println(event);
			}

			public void inputMethodTextChanged(InputMethodEvent event) {
				System.out.println("inputMethodTextChanged");
				System.out.println(event);
			}
		});
		center.add(textField_2);
		textField_2.setColumns(10);

		// 注册最高级的
		ActionMap actionMap = this.getRootPane().getActionMap();
		System.out.println("测试ActionMap");
		Object[] obj = actionMap.allKeys();
		for (int i = 0; i < obj.length; i++) {
			System.out.println("___________________________");
			Object key = obj[i];
			System.out.println(key);
			Action a = actionMap.get(key);
			System.out.println(a);
		}

		actionMap.put("test", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("test触发了");
				JOptionPane.showMessageDialog(null, "保存");
			}
		});
		this.getRootPane().setActionMap(actionMap);

		InputMap inputMap = this.getRootPane().getInputMap();
		inputMap.put(
				KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK
						| InputEvent.SHIFT_DOWN_MASK), "test");
		this.getRootPane().setInputMap(
				JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, inputMap);
	}

}
