package com.sf.module.EDI.EDIImpl.ui.function;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FontMetrics;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.SimpleBindings;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JOptionPane;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sf.module.EDI.EDIImpl.report.utils.ReportUtils;
import com.sf.module.EDI.EDIImpl.reportModel.IReportModel;
import com.sf.module.EDI.EDIImpl.reportModel.ReportModel;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.function.GroovyFunction;
import com.sf.module.EDI.EDIImpl.ui.KeyAndViewDefine;
import com.sf.module.EDI.EDIImpl.ui.comm.groovy.ConsoleDocument;
import com.sf.module.EDI.EDIImpl.ui.comm.groovy.ConsoleTextPane;
import com.sf.module.EDI.EDIImpl.ui.comm.groovy.ConsoleWriter;

/**
 * 描述：GroovyFunction编辑器
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-8-16      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class GroovyFunctionEditor extends
		FunctionAbstractDialog<GroovyFunction> {

	private static Logger logger = LoggerFactory.getLogger(GroovyFunctionEditor.class);

	private static final long serialVersionUID = -2897362583833273997L;

	/**
	 * 脚本
	 */
	private JTextPane textPane_script;

	/**
	 * 变量列表
	 */
	private JList list;

	/**
	 * 控制台
	 */
	private ConsoleTextPane textPane_console;

	private ScriptEngine engine;

	// IO
	private transient ConsoleWriter outWriter;

	private static GroovyFunctionEditor editor;

	public static synchronized GroovyFunctionEditor getInstance(Window windows) {
		if (editor == null) {
			editor = new GroovyFunctionEditor(windows);
		}
		return editor;
	}

	/**
	 * <pre>
	 * 发送消息的控制台
	 * </pre>
	 * 
	 * @param msg
	 */
	public void sendMsg(String msg) {
		if (outWriter != null) {
			outWriter.getWriter().println(msg);
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GroovyFunctionEditor frame = new GroovyFunctionEditor(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * <pre>
	 * 获取合并变量
	 * </pre>
	 * 
	 * @return
	 */
	public Map<String, Object> getMerge() {
		List<Map<String, Object>> list = ReportUtils.getAnalogData(1);
		return list.get(0);
	}

	/**
	 * <pre>
	 * 创建脚本引擎
	 * </pre>
	 */
	private void createScriptEngine() {
		ScriptEngineManager manager = new ScriptEngineManager();
		engine = manager.getEngineByName("Groovy");

		// 函数无需设置
		Bindings bind = engine.getBindings(ScriptContext.GLOBAL_SCOPE);
		// 合并变量
		bind.putAll(getMerge());

		// 设置输入输出
		ScriptContext context = engine.getContext();
		context.setErrorWriter(outWriter.getWriter());
		context.setWriter(outWriter.getWriter());
		// context.setReader(xx);

	}

	/**
	 * Create the frame.
	 */
	public GroovyFunctionEditor(Window windows) {
		super(windows);
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setModal(true);
		// setResizable(false);
		setTitle("Groovy 函数");

		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BorderLayout(0, 0));

		JToolBar toolBar = new JToolBar();
		contentPanel.add(toolBar, BorderLayout.NORTH);

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

		JButton btnx = new JButton("退出(X)");
		ActionListener xAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		};
		btnx.addActionListener(xAction);
		btnx.setMnemonic('X');
		toolBar.add(btnx);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.6);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPanel.add(splitPane, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);

		textPane_script = new JTextPane();

		scrollPane.setViewportView(textPane_script);

		// 设置tab
		setTabs(textPane_script, 4);

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
		getRootPane().registerKeyboardAction(xAction, "cancel",
				KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK),
				JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		// 取消ESC快捷键
		contentPanel.unregisterKeyboardAction(KeyStroke.getKeyStroke(
				KeyEvent.VK_ESCAPE, 0));

		outWriter = new ConsoleWriter(textPane_console);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setPreferredSize(new Dimension(140, 2));
		contentPanel.add(scrollPane_2, BorderLayout.EAST);

		list = new JList();
		scrollPane_2.setViewportView(list);

		list.setModel(new AbstractListModel() {
			private static final long serialVersionUID = 7383625850280468171L;
			String[] values = KeyAndViewDefine.getGroovyListArrays();

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});

		list.setCellRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = -8730673877779441394L;

			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				value = KeyAndViewDefine.getViewByKey((String) value);
				return super.getListCellRendererComponent(list, value, index,
						isSelected, cellHasFocus);
			}
		});
		list.setForeground(Color.BLUE);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textPane_script.replaceSelection(list.getSelectedValue()
						.toString());
			}
		});

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					createScriptEngine();
				} catch (Exception e) {
					e.printStackTrace(outWriter.getWriter());
					logger.error("创建脚本引擎失败", e);
					JOptionPane.showMessageDialog(GroovyFunctionEditor.this,
							"创建脚本引擎失败！\r\n" + e.getMessage());
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
			sbind.put("value", "value");
			sbind.put(IReportModel.GLOBAL_CONTEXT, ReportModel
					.getStaticInstance().getGlobalContext());
			Object o = engine.eval(textPane_script.getText(), sbind);
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
		viewToModel();
	}

	private void exit() {
		this.setVisible(false);
	}

	@Override
	public void showEdit(GroovyFunction func) {
		this.function = func;
		modelToView();
		clear();
		textPane_script.requestFocus();
		setVisible(true);

	}

	@Override
	public void modelToView() {
		textPane_script.setText(function.getScript());

	}

	@Override
	public void viewToModel() {
		function.setScript(textPane_script.getText());
	}

}
