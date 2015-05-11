package com.sf.module.EDI.EDIImpl.ui.format;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.value.DateValueFormat;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.value.IValueFormat;

/**
 * 描述：日期格式面板
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-4-13      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class DateValuePanel extends JPanel implements IValuePanel {

	private static final long serialVersionUID = 695860427514800317L;

	private JLabel label_preview;
	private JComboBox comboBox_srcPattern;
	private JComboBox comboBox_descPattern;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private String dateStr = dateFormat.format(new Date());

	/**
	 * Create the panel.
	 */
	public DateValuePanel() {
		setLayout(null);

		setBounds(0, 0, 329, 226);

		JLabel label = new JLabel("原日期和时间模式：");
		label.setBounds(10, 10, 170, 15);
		add(label);

		comboBox_srcPattern = new JComboBox();
		comboBox_srcPattern.setEditable(true);
		comboBox_srcPattern.setModel(new DefaultComboBoxModel(new String[] {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMdd", "HH:mm:ss", "HH:mm:ss:SSS"}));
		comboBox_srcPattern.setMaximumRowCount(14);
		comboBox_srcPattern.setBounds(10, 35, 262, 21);
		add(comboBox_srcPattern);

		JLabel label_1 = new JLabel("目标日期和时间模式：");
		label_1.setBounds(10, 78, 262, 15);
		add(label_1);

		comboBox_descPattern = new JComboBox();
		comboBox_descPattern.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (ItemEvent.SELECTED == e.getStateChange()) {
					try {
						dateFormat = new SimpleDateFormat(comboBox_descPattern
								.getSelectedItem().toString());
						label_preview.setText(dateFormat.format(new Date()));
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(DateValuePanel.this,
								"模式错误！\r\n" + ex.getMessage());
						comboBox_descPattern.setSelectedIndex(0);
					}
				}
			}
		});
		comboBox_descPattern.setModel(new DefaultComboBoxModel(new String[] {"yyyy-MM-dd", "yyyyMMdd", "yyyy年M月d日", "yyyy年MM月dd日", "yyyy-MM-dd HH:mm:ss", "yyyy年MM月dd日 HH时mm分ss秒", "HH:mm:ss", "HH:mm:ss:SSS", "HH时mm分ss秒", "a h点m分s秒", "HH时mm分ss秒SSS毫秒"}));
		comboBox_descPattern.setMaximumRowCount(14);
		comboBox_descPattern.setEditable(true);
		comboBox_descPattern.setBounds(10, 103, 262, 21);
		add(comboBox_descPattern);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(
				null,
				"\u76EE\u6807\u65E5\u671F\u65F6\u95F4\u793A\u4F8B (\u53D6\u7CFB\u7EDF\u65F6\u95F4)\uFF1A",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setLayout(null);
		panel_1.setBounds(10, 147, 309, 69);
		add(panel_1);

		label_preview = new JLabel(dateStr);
		label_preview.setHorizontalAlignment(SwingConstants.CENTER);
		label_preview.setBounds(10, 33, 289, 15);
		panel_1.add(label_preview);

	}

	@Override
	public void setValueFormat(IValueFormat valueFormat) {
		DateValueFormat format = (DateValueFormat) valueFormat;
		comboBox_srcPattern.setSelectedItem(format.getSrcPattern());
		comboBox_descPattern.setSelectedItem(format.getDescPattern());
	}

	@Override
	public IValueFormat getValueFormat() {
		if (isDefaultValue())
			return null;
		DateValueFormat format = new DateValueFormat();
		format.setSrcPattern(comboBox_srcPattern.getSelectedItem().toString());
		format.setDescPattern(comboBox_descPattern.getSelectedItem().toString());
		return format;
	}

	@Override
	public void reset() {
		comboBox_descPattern.setSelectedIndex(0);
		comboBox_descPattern.setSelectedIndex(0);

		label_preview.setText(dateStr);
	}

	@Override
	public boolean isDefaultValue() {
		return comboBox_srcPattern.getSelectedIndex() == 0
				&& comboBox_descPattern.getSelectedIndex() == 0;
	}

}
