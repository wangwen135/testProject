package com.swing.component.spinnerTest;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

/**
 * 描述：数字转盘编辑器<br>
 * 可以设置null值，当值为''时返回的是null
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-3-19      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class MySpinnerNumberEditor extends JFormattedTextField implements
		ChangeListener, PropertyChangeListener {
	private static final long serialVersionUID = 769923062119892407L;
	private JSpinner spinner;
	@SuppressWarnings("unused")
	private MySpinnerNumberModel model;

	public MySpinnerNumberEditor(JSpinner spinner, MySpinnerNumberModel model) {
		this(spinner, model, "##0.#####");
	}

	public MySpinnerNumberEditor(JSpinner spinner, MySpinnerNumberModel model,
			String Pattern) {
		super();
		this.spinner = spinner;
		this.model = model;

		NumberFormatter formatter = new NumberEditorFormatter(model,
				new DecimalFormat(Pattern));
		DefaultFormatterFactory factory = new DefaultFormatterFactory(formatter);
		setFormatterFactory(factory);

		setValue(spinner.getValue());
		setHorizontalAlignment(JTextField.RIGHT);
		addPropertyChangeListener(this);
		setEditable(true);
		setInheritsPopupMenu(true);

		String toolTipText = spinner.getToolTipText();
		if (toolTipText != null) {
			setToolTipText(toolTipText);
		}
		spinner.addChangeListener(this);
	}

	public void dismiss(JSpinner spinner) {
		spinner.removeChangeListener(this);
	}

	public JSpinner getSpinner() {
		return spinner;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (spinner == null) {
			return;
		}
		String name = evt.getPropertyName();
		if ("value".equals(name)) {
			Object lastValue = spinner.getValue();

			// Try to set the new value
			try {
				spinner.setValue(getValue());
			} catch (IllegalArgumentException iae) {
				try {
					spinner.setValue(lastValue);
				} catch (IllegalArgumentException iae2) {
				}
			}
		}

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (getValue() != spinner.getValue())
			setValue(spinner.getValue());
	}

}

@SuppressWarnings("rawtypes")
class NumberEditorFormatter extends NumberFormatter {
	private static final long serialVersionUID = -2955556899158702544L;
	private final MySpinnerNumberModel model;

	NumberEditorFormatter(MySpinnerNumberModel model, NumberFormat format) {
		super(format);
		this.model = model;
		// value有可能为空
		if (model.getValue() != null) {
			setValueClass(model.getValue().getClass());
		} else if (model.getMaximum() != null) {
			setValueClass(model.getMaximum().getClass());
		} else if (model.getMinimum() != null) {
			setValueClass(model.getMinimum().getClass());
		} else {
			setValueClass(model.getStepSize().getClass());
		}
	}

	public void setMinimum(Comparable min) {
		model.setMinimum(min);
	}

	public Comparable getMinimum() {
		return model.getMinimum();
	}

	public void setMaximum(Comparable max) {
		model.setMaximum(max);
	}

	public Comparable getMaximum() {
		return model.getMaximum();
	}

	@Override
	public Object stringToValue(String text) throws ParseException {
		if (text == null) {
			return null;
		}
		if ("".equals(text)) {
			return null;
		}

		return super.stringToValue(text);
	}
}
