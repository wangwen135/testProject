package com.sf.module.EDI.EDIImpl.ui.function;

import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.function.IFunction;

/**
 * 描述：抽象函数编辑对话框
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-5-28      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public abstract class FunctionAbstractDialog<T extends IFunction> extends
		JDialog {

	private static final long serialVersionUID = 8593540693734232432L;

	/**
	 * 主内容面板，参考其他实现类
	 */
	public final JPanel contentPanel = new JPanel();

	/**
	 * 编辑器对应函数
	 */
	protected T function;

	/**
	 * 构造函数
	 * 
	 * @param windows
	 */
	public FunctionAbstractDialog(Window windows) {
		super(windows);

		contentPanel.registerKeyboardAction(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
	}

	public T getFunction() {
		return function;
	}

	public void setFunction(T function) {
		this.function = function;
	}

	/**
	 * <pre>
	 * 显示编辑器
	 * </pre>
	 * 
	 * @param func
	 */
	public abstract void showEdit(T func);

	/**
	 * <pre>
	 * 模型到视图
	 * </pre>
	 */
	public abstract void modelToView();

	/**
	 * <pre>
	 * 视图到模型
	 * </pre>
	 */
	public abstract void viewToModel();

}
