package com.sf.module.EDI.EDIImpl.ui.format;

import java.awt.Color;
import java.awt.FlowLayout;
import java.math.RoundingMode;
import java.text.NumberFormat;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.value.IValueFormat;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.value.NumberValueFormat;

/**
 * 描述：数字格式面板
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-4-14      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class NumberValuePanel extends JPanel implements IValuePanel {

	private static final long serialVersionUID = 1L;

	private JSpinner spn_minFraction;
	private JSpinner spn_maxFraction;
	private JSpinner spn_minInteger;
	private JSpinner spn_maxInteger;
	private JCheckBox checkBox_useGroup;
	private JLabel label_demo1;
	private JLabel label_demo2;
	private JLabel label_demo3;

	private NumberFormat format;

	/**
	 * Create the panel.
	 */
	public NumberValuePanel() {
		format = NumberFormat.getInstance();
		format.setMaximumFractionDigits(8);
		format.setMinimumFractionDigits(0);
		format.setMaximumIntegerDigits(20);
		format.setMinimumIntegerDigits(1);
		format.setRoundingMode(RoundingMode.HALF_UP);// 默认的四舍五入
		format.setGroupingUsed(false);

		setLayout(null);
		setBounds(0, 0, 329, 226);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"),
				"\u5C0F\u6570\u90E8\u5206\uFF1A", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 70, 213)));
		panel_1.setLayout(null);
		panel_1.setBounds(5, 0, 319, 80);
		add(panel_1);

		JLabel label = new JLabel("小数部分最小位数：");
		label.setBounds(10, 25, 120, 15);
		panel_1.add(label);

		spn_minFraction = new JSpinner();
		spn_minFraction.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Integer max = (Integer) spn_maxFraction.getValue();
				Integer min = (Integer) spn_minFraction.getValue();
				if (min > max) {
					spn_maxFraction.setValue(min);
				}

				format.setMinimumFractionDigits(min);
				preView();
			}
		});
		spn_minFraction.setModel(new SpinnerNumberModel(0, 0, 30, 1));
		spn_minFraction.setBounds(132, 22, 69, 22);
		panel_1.add(spn_minFraction);

		JLabel label_1 = new JLabel("小数部分最大位数：");
		label_1.setBounds(10, 53, 120, 15);
		panel_1.add(label_1);

		spn_maxFraction = new JSpinner();
		spn_maxFraction.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Integer max = (Integer) spn_maxFraction.getValue();
				Integer min = (Integer) spn_minFraction.getValue();
				if (max < min) {
					spn_minFraction.setValue(max);
				}
				format.setMaximumFractionDigits(max);
				preView();
			}
		});
		spn_maxFraction.setModel(new SpinnerNumberModel(8, 0, 30, 1));
		spn_maxFraction.setBounds(132, 50, 69, 22);
		panel_1.add(spn_maxFraction);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"),
				"\u6574\u6570\u90E8\u5206\uFF1A", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 70, 213)));
		panel_2.setLayout(null);
		panel_2.setBounds(5, 80, 319, 80);
		add(panel_2);

		JLabel label_2 = new JLabel("整数部分最小位数：");
		label_2.setBounds(10, 25, 120, 15);
		panel_2.add(label_2);

		spn_minInteger = new JSpinner();
		spn_minInteger.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Integer min = (Integer) spn_minInteger.getValue();
				Integer max = (Integer) spn_maxInteger.getValue();
				if (min > max) {
					spn_maxInteger.setValue(min);
				}
				format.setMinimumIntegerDigits(min);
				preView();
			}
		});
		spn_minInteger.setModel(new SpinnerNumberModel(1, 0, 50, 1));
		spn_minInteger.setBounds(132, 22, 69, 22);
		panel_2.add(spn_minInteger);

		JLabel label_3 = new JLabel("整数部分最大位数：");
		label_3.setBounds(10, 53, 120, 15);
		panel_2.add(label_3);

		spn_maxInteger = new JSpinner();
		spn_maxInteger.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Integer min = (Integer) spn_minInteger.getValue();
				Integer max = (Integer) spn_maxInteger.getValue();
				if (max < min) {
					spn_minInteger.setValue(max);
				}
				format.setMaximumIntegerDigits(max);
				preView();
			}
		});
		spn_maxInteger.setModel(new SpinnerNumberModel(20, 0, 50, 1));
		spn_maxInteger.setBounds(132, 50, 69, 22);
		panel_2.add(spn_maxInteger);

		checkBox_useGroup = new JCheckBox("千分位分隔符");
		checkBox_useGroup.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {

				format.setGroupingUsed(checkBox_useGroup.isSelected());
				preView();
			}
		});
		checkBox_useGroup.setBounds(207, 49, 103, 23);
		panel_2.add(checkBox_useGroup);

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(15);
		panel_3.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"),
				"\u793A\u4F8B\uFF1A( 154.64 , 1 , 0.5 )", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 70, 213)));
		panel_3.setBounds(5, 160, 319, 60);
		add(panel_3);

		label_demo1 = new JLabel("123.45");
		panel_3.add(label_demo1);

		JLabel label_4 = new JLabel("|");
		label_4.setForeground(Color.RED);
		panel_3.add(label_4);

		label_demo2 = new JLabel("1");
		panel_3.add(label_demo2);

		JLabel label_5 = new JLabel("|");
		label_5.setForeground(Color.RED);
		panel_3.add(label_5);

		label_demo3 = new JLabel("0.5");
		panel_3.add(label_demo3);

	}

	private void preView() {
		label_demo1.setText(format.format(123.45));
		label_demo2.setText(format.format(1));
		label_demo3.setText(format.format(0.5));
	}

	@Override
	public void setValueFormat(IValueFormat valueFormat) {
		NumberValueFormat format = (NumberValueFormat) valueFormat;
		spn_minFraction.setValue(format.getMinFraction());
		spn_maxFraction.setValue(format.getMaxFraction());
		spn_minInteger.setValue(format.getMinInteger());
		spn_maxInteger.setValue(format.getMaxInteger());
		checkBox_useGroup.setSelected(format.isUseGroup());
	}

	@Override
	public IValueFormat getValueFormat() {
		if (isDefaultValue())
			return null;
		NumberValueFormat format = new NumberValueFormat();
		format.setMinFraction((Integer) spn_minFraction.getValue());
		format.setMaxFraction((Integer) spn_maxFraction.getValue());
		format.setMinInteger((Integer) spn_minInteger.getValue());
		format.setMaxInteger((Integer) spn_maxInteger.getValue());
		format.setUseGroup(checkBox_useGroup.isSelected());
		return format;
	}

	@Override
	public void reset() {
		spn_minFraction.setValue(0);
		spn_maxFraction.setValue(8);
		spn_minInteger.setValue(1);
		spn_maxInteger.setValue(20);
		checkBox_useGroup.setSelected(false);
	}

	@Override
	public boolean isDefaultValue() {
		return false;// 数字格式需保存
		// return 0 == (Integer) spn_minFraction.getValue()
		// && 8 == (Integer) spn_maxFraction.getValue()
		// && 1 == (Integer) spn_minInteger.getValue()
		// && 20 == (Integer) spn_maxInteger.getValue()
		// && !checkBox_useGroup.isSelected();
	}

}
