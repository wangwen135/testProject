package com.sf.module.EDI.EDIImpl.ui.entity;

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

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity.IEntity;

/**
 * 描述：抽象Entity编辑对话框<br>
 * 从FunctionAbstractDialog复制
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
public abstract class EntityAbstractDialog<T extends IEntity> extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4527589210302053191L;

	/**
	 * 主内容面板，参考其他实现类
	 */
	public final JPanel contentPanel = new JPanel();

	/**
	 * 编辑器对应函数
	 */
	protected T entity;

	/**
	 * 构造函数
	 * 
	 * @param windows
	 */
	public EntityAbstractDialog(Window windows) {
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

	public T getEntity() {
		return entity;
	}

	public void setEntity(T cell) {
		this.entity = cell;
	}

	/**
	 * <pre>
	 * 显示编辑器
	 * </pre>
	 * 
	 * @param func
	 */
	public abstract void showEdit(T entity);

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
