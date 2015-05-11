package com.sf.module.EDI.EDIImpl.ui.function;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.function.DateFunction;

/**
 * 描述：日期函数编辑器<br>
 * 单例模式
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-2-8      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class DateFunctionEditor extends FunctionAbstractDialog<DateFunction> {

	private static final long serialVersionUID = 7633690010588916609L;

	private static DateFunctionEditor dateEditor;

	private JLabel lbl_dateDemo;
	private JComboBox comboBox_pattern;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DateFunctionEditor dialog = new DateFunctionEditor(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized DateFunctionEditor getInstance(Window windows) {
		if (dateEditor == null) {
			dateEditor = new DateFunctionEditor(windows);
		}
		return dateEditor;
	}

	@Override
	public void showEdit(DateFunction func) {
		this.function = func;
		comboBox_pattern.setSelectedItem(function.getPattern());
		// 显示示例
		confirm();
		setVisible(true);
	}

	/**
	 * Create the dialog.
	 */
	private DateFunctionEditor(Window windows) {
		super(windows);
		setResizable(false);
		setTitle("编辑日期时间格式");
		setModal(true);
		setBounds(100, 100, 473, 503);
		setLocationRelativeTo(null);

		contentPanel.setLayout(null);

		comboBox_pattern = new JComboBox();
		comboBox_pattern.setMaximumRowCount(14);
		comboBox_pattern.setEditable(true);
		comboBox_pattern.setModel(new DefaultComboBoxModel(new String[] {
				"yyyy-MM-dd", "yyyyMMdd", "yyyy年M月d日", "yyyy年MM月dd日",
				"yyyy-MM-dd HH:mm:ss", "yyyy年MM月dd日 HH时mm分ss秒", "HH:mm:ss",
				"HH:mm:ss:SSS", "HH时mm分ss秒", "a h点m分s秒", "HH时mm分ss秒SSS毫秒" }));
		comboBox_pattern.setBounds(128, 316, 262, 21);
		contentPanel.add(comboBox_pattern);

		JButton btn_test = new JButton("测  试");
		btn_test.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirm();
			}
		});
		btn_test.setBounds(10, 344, 93, 23);
		contentPanel.add(btn_test);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u793A\u4F8B\uFF1A",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 370, 426, 57);
		contentPanel.add(panel);

		lbl_dateDemo = new JLabel("2011-01-18");
		panel.add(lbl_dateDemo);

		JLabel label = new JLabel("日期和时间模式：");
		label.setBounds(12, 319, 117, 15);
		contentPanel.add(label);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 466, 306);
		contentPanel.add(scrollPane);

		JLabel lblNewLabel = new JLabel(
				"<html><table border=0 cellspacing=4 cellpadding=0 >     <tr bgcolor=\"#ccccff\"><th align=left>字母<th align=left>日期或时间元素<th align=left>示例     <tr>         <td><code>G</code><td>Era 标志符         <td><code>AD</code>     <tr bgcolor=\"#eeeeff\">         <td><code>y</code><td>年         <td><code>1996</code>; <code>96</code>     <tr>         <td><code>M</code><td>年中的月份         <td><code>July</code>; <code>Jul</code>; <code>07</code>     <tr bgcolor=\"#eeeeff\">         <td><code>w</code><td>年中的周数         <td><code>27</code>     <tr>         <td><code>W</code><td>月份中的周数         <td><code>2</code>     <tr bgcolor=\"#eeeeff\">         <td><code>D</code><td>年中的天数         <td><code>189</code>     <tr><td><code>d</code><td>月份中的天数         <td><code>10</code>     <tr bgcolor=\"#eeeeff\">         <td><code>F</code><td>月份中的星期         <td><code>2</code>     <tr>         <td><code>E</code><td>星期中的天数         <td><code>Tuesday</code>; <code>Tue</code>     <tr bgcolor=\"#eeeeff\">         <td><code>a</code><td>Am/pm 标记         <td><code>PM</code>     <tr>         <td><code>H</code><td>一天中的小时数（0-23）         <td><code>0</code>     <tr bgcolor=\"#eeeeff\">         <td><code>k</code><td>一天中的小时数（1-24）         <td><code>24</code>     <tr>         <td><code>K</code><td>am/pm 中的小时数（0-11）         <td><code>0</code>     <tr bgcolor=\"#eeeeff\">         <td><code>h</code><td>am/pm 中的小时数（1-12）         <td><code>12</code>     <tr>         <td><code>m</code><td>小时中的分钟数         <td><code>30</code>     <tr bgcolor=\"#eeeeff\">         <td><code>s</code><td>分钟中的秒数         <td><code>55</code>     <tr>         <td><code>S</code><td>毫秒数         <td><code>978</code>     <tr bgcolor=\"#eeeeff\">         <td><code>z</code><td>时区         <td><code>Pacific Standard Time</code>; <code>PST</code>; <code>GMT-08:00</code>     <tr>         <td><code>Z</code><td>时区         <td><code>-0800</code> </table>\r\n </html>");
		scrollPane.setViewportView(lblNewLabel);
		{
			JPanel buttonPane = new JPanel();
			FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.RIGHT);
			fl_buttonPane.setHgap(15);
			buttonPane.setLayout(fl_buttonPane);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("确认 (O)");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Object o = comboBox_pattern.getSelectedItem();
						if (o == null || "".equals(o)) {
							JOptionPane.showMessageDialog(
									DateFunctionEditor.this, "模式不能为空!");
							return;
						}
						try {
							new SimpleDateFormat(o.toString());
							function.setPattern(o.toString());
							setVisible(false);
						} catch (IllegalArgumentException e1) {
							JOptionPane.showMessageDialog(
									DateFunctionEditor.this,
									"模式错误！\r\n" + e1.getMessage());
						}
					}
				});
				okButton.setMnemonic('O');
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("取消 (C)");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setMnemonic('C');
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void confirm() {

		Object o = comboBox_pattern.getSelectedItem();
		if (o == null || "".equals(o)) {
			JOptionPane.showMessageDialog(DateFunctionEditor.this, "模式不能为空!");
			return;
		}
		try {
			SimpleDateFormat format = new SimpleDateFormat(o.toString());
			lbl_dateDemo.setText(format.format(new Date()));
		} catch (IllegalArgumentException e1) {
			JOptionPane.showMessageDialog(DateFunctionEditor.this, "模式错误！\r\n"
					+ e1.getMessage());
		}

	}

	@Override
	public void modelToView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void viewToModel() {
		// TODO Auto-generated method stub

	}
}
