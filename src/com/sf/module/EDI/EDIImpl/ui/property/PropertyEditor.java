package com.sf.module.EDI.EDIImpl.ui.property;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.ICell;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.property.CellProperty;

/**
 * 描述：属性编辑框
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-3-30      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class PropertyEditor extends JDialog {

	private static final long serialVersionUID = -7371815304555491728L;

	private final JPanel contentPanel = new JPanel();
	private JCheckBox checkBox_showOnMultGoods;

	private static PropertyEditor editor;

	/**
	 * 确定完成编辑
	 */
	private boolean confirm = false;

	public static synchronized PropertyEditor getInstance(Window windows) {
		if (editor == null) {
			editor = new PropertyEditor(windows);
		}
		return editor;
	}

	@SuppressWarnings("unused")
	private ICell cell;
	private CellProperty property;

	public void showEdit(ICell cell) {// 不同步了，模式窗口
		confirm = false;
		this.cell = cell;
		property = cell.getProperty();
		if (property == null) {
			property = new CellProperty();
			cell.setProperty(property);
		}

		modelToView();
		setVisible(true);
	}

	/**
	 * <pre>
	 * 模型到视图
	 * </pre>
	 */
	private void modelToView() {
		checkBox_showOnMultGoods.setSelected(property.isShowOnMultGoods());
	}

	/**
	 * <pre>
	 * 视图到模型
	 * </pre>
	 */
	private void viewToModel() {
		property.setShowOnMultGoods(checkBox_showOnMultGoods.isSelected());
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PropertyEditor dialog = new PropertyEditor(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	private PropertyEditor(Window windows) {
		super(windows);
		setModal(true);
		setResizable(false);
		setTitle("单元格属性");
		setBounds(100, 100, 374, 250);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			checkBox_showOnMultGoods = new JCheckBox("拆分多品名时显示单元格内容");
			checkBox_showOnMultGoods.setBounds(6, 6, 266, 23);
			contentPanel.add(checkBox_showOnMultGoods);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("确定(O)");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						viewToModel();
						confirm = true;
						setVisible(false);
					}
				});
				okButton.setMnemonic('o');
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("取消(C)");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setMnemonic('c');
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	/**
	 * <pre>
	 * 是否是确定
	 * </pre>
	 * 
	 * @return
	 */
	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

}
