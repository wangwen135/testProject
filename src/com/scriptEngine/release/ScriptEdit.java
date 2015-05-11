package com.scriptEngine.release;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.SimpleBindings;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.TabSet;
import javax.swing.text.TabStop;

public class ScriptEdit extends JFrame {

	private static final long serialVersionUID = -2897362583833273997L;

	private JPanel contentPane;

	private JTextPane textArea_script;

	/**
	 * 控制台
	 */
	private ConsoleTextPane textPane_console;

	private ScriptEngine engine;

	// IO
	ConsoleWriter outWriter;

	public void sendMsg(String msg) {
		if (outWriter != null) {
			outWriter.getWriter().println(msg);
		}
	}

	@Override
	public void dispose() {
		if (outWriter != null) {
			outWriter.interrupt();
		}
		super.dispose();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScriptEdit frame = new ScriptEdit();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Map<String, Object> getMerge() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "value3");
		return map;
	}

	private void createScriptEngine() {
		ScriptEngineManager manager = new ScriptEngineManager();
		engine = manager.getEngineByName("Groovy");

		Bindings bind = engine.getBindings(ScriptContext.GLOBAL_SCOPE);
		// 合并变量
		bind.putAll(getMerge());

		// 设置输入输出
		ScriptContext context = engine.getContext();
		context.setErrorWriter(outWriter.getWriter());
		context.setWriter(outWriter.getWriter());
		// context.setReader(xx);

		// context.setAttribute("out", System.out, ScriptContext.GLOBAL_SCOPE);
		// SimpleBindings sbind = new SimpleBindings();
		// sbind.putAll(map);
		// bind.putAll(toMerge);
	}

	/**
	 * Create the frame.
	 */
	public ScriptEdit() {
		setTitle("Groovy Engine");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);

		JButton btnr = new JButton("运行(R)");

		ActionListener rAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				run();
			}
		};
		btnr.addActionListener(rAction);
		btnr.setMnemonic('R');
		toolBar.add(btnr);

		JButton btnl = new JButton("清空(L)");
		ActionListener lAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		};
		btnl.addActionListener(lAction);
		btnl.setMnemonic('L');
		toolBar.add(btnl);
		toolBar.addSeparator();

		JButton btns = new JButton("保存(S)");
		ActionListener sAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		};
		btns.addActionListener(sAction);
		btns.setMnemonic('S');
		toolBar.add(btns);
		toolBar.addSeparator();

		JButton btnc = new JButton("退出(X)");
		ActionListener cAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		};
		btnc.addActionListener(cAction);
		btnc.setMnemonic('X');
		toolBar.add(btnc);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.6);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(splitPane, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);

		textArea_script = new JTextPane();
		textArea_script.setText("println('aaaa');");// test
		scrollPane.setViewportView(textArea_script);

		// 设置tab
		setTabs(textArea_script, 4);

		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane.setRightComponent(scrollPane_1);

		textPane_console = new ConsoleTextPane();
		ConsoleDocument document = new ConsoleDocument(textPane_console);
		// textPane_console.setEditable(false);
		textPane_console.setDocument(document);
		setTabs(textPane_console, 4);
		scrollPane_1.setViewportView(textPane_console);

		// 注册快捷键
		getRootPane().registerKeyboardAction(rAction, "run",
				KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK),
				JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		getRootPane().registerKeyboardAction(lAction, "clear",
				KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK),
				JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		getRootPane().registerKeyboardAction(sAction, "save",
				KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK),
				JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		getRootPane().registerKeyboardAction(cAction, "cancel",
				KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK),
				JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

		outWriter = new ConsoleWriter(textPane_console);

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					createScriptEngine();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(ScriptEdit.this,
							"创建脚本引擎失败！\r\n" + e.getMessage());
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * <pre>
	 * 设置tab
	 * </pre>
	 * 
	 * @param textPane
	 * @param charactersPerTab
	 */
	public void setTabs(JTextPane textPane, int charactersPerTab) {
		FontMetrics fm = textPane.getFontMetrics(textPane.getFont());
		int charWidth = fm.charWidth('a');
		int tabWidth = charWidth * charactersPerTab;

		TabStop[] tabs = new TabStop[20];

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

	private void run() {
		if (engine == null) {
			JOptionPane.showMessageDialog(this, "脚本引擎出错，无法运行脚本！");
			return;
		}

		try {
			// ScriptContext context = engine.getContext();// 设置输入输出
			
			SimpleBindings sbind = new SimpleBindings();
			sbind.put("values", "hello world");

			Object o = engine.eval(textArea_script.getText(),sbind);
			sendMsg("===>" + o);

		} catch (Exception e) {
			sendMsg("出现异常：");
			e.printStackTrace(outWriter.getWriter());
		}
	}

	private void clear() {
		textPane_console.clear();
	}

	private void save() {
		sendMsg("save...");
	}

	private void exit() {
		this.setVisible(false);
//		if (outWriter != null) {
//			outWriter.interrupt();
//		}
	}

}
