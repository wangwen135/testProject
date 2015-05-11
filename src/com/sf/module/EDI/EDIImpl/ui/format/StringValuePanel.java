package com.sf.module.EDI.EDIImpl.ui.format;

import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.value.IValueFormat;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.value.StringValueFormat;

import java.awt.FlowLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/**
 * 描述：字符串格式面板
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
public class StringValuePanel extends JPanel implements IValuePanel {

	private static final long serialVersionUID = -3348002836205028785L;

	private JCheckBox checkBox_toUpCase;

	private JCheckBox checkBox_toLowerCase;

	private JCheckBox checkBox_trim;

	/**
	 * Create the panel.
	 */
	public StringValuePanel() {
		setLayout(null);
		setBounds(0, 0, 329, 226);

		checkBox_trim = new JCheckBox("去除两端空格");
		checkBox_trim.setBounds(44, 92, 103, 23);
		add(checkBox_trim);

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setHgap(25);
		panel_1.setBorder(new TitledBorder(null,
				"\u5927\u5C0F\u5199\u8F6C\u6362\uFF1A", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 10, 309, 61);
		add(panel_1);

		checkBox_toUpCase = new JCheckBox("全部转换成大写");
		checkBox_toUpCase.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (checkBox_toUpCase.isSelected()) {
					checkBox_toLowerCase.setSelected(false);
				}
			}
		});
		panel_1.add(checkBox_toUpCase);

		checkBox_toLowerCase = new JCheckBox("全部转换成小写");
		checkBox_toLowerCase.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (checkBox_toLowerCase.isSelected()) {
					checkBox_toUpCase.setSelected(false);
				}
			}
		});
		panel_1.add(checkBox_toLowerCase);

	}

	@Override
	public void setValueFormat(IValueFormat valueFormat) {
		StringValueFormat format = (StringValueFormat) valueFormat;
		checkBox_toUpCase.setSelected(format.isToUpCase());
		checkBox_toLowerCase.setSelected(format.isToLowerCase());
		checkBox_trim.setSelected(format.isTrim());
	}

	@Override
	public IValueFormat getValueFormat() {
		if (isDefaultValue())
			return null;
		StringValueFormat format = new StringValueFormat();
		format.setToUpCase(checkBox_toUpCase.isSelected());
		format.setToLowerCase(checkBox_toLowerCase.isSelected());
		format.setTrim(checkBox_trim.isSelected());
		return format;
	}

	@Override
	public void reset() {
		checkBox_toUpCase.setSelected(false);
		checkBox_toLowerCase.setSelected(false);
		checkBox_trim.setSelected(false);
	}

	@Override
	public boolean isDefaultValue() {
		return !checkBox_toUpCase.isSelected()
				&& !checkBox_toLowerCase.isSelected()
				&& !checkBox_trim.isSelected();

	}

}
