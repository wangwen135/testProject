package com.swing.component.spinnerTest;

import java.io.Serializable;

import javax.swing.AbstractSpinnerModel;

/**
 * 描述：数字转盘model<br>
 * 可以设置值为null
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
@SuppressWarnings("rawtypes")
public class MySpinnerNumberModel extends AbstractSpinnerModel implements
		Serializable {
	private static final long serialVersionUID = 7164341361627630806L;
	private Number stepSize, value;

	private Comparable minimum, maximum;

	@SuppressWarnings("unchecked")
	public MySpinnerNumberModel(Number value, Comparable minimum,
			Comparable maximum, Number stepSize) {
		if (stepSize == null) {
			throw new IllegalArgumentException("stepSize must be non-null");
		}
		if (value != null) {
			if (!(((minimum == null) || (minimum.compareTo(value) <= 0)) && ((maximum == null) || (maximum
					.compareTo(value) >= 0)))) {
				throw new IllegalArgumentException(
						"(minimum <= value <= maximum) is false");
			}
		}
		this.value = value;
		this.minimum = minimum;
		this.maximum = maximum;
		this.stepSize = stepSize;
	}

	public MySpinnerNumberModel(int value, int minimum, int maximum,
			int stepSize) {
		this(new Integer(value), new Integer(minimum), new Integer(maximum),
				new Integer(stepSize));
	}

	public MySpinnerNumberModel(double value, double minimum, double maximum,
			double stepSize) {
		this(new Double(value), new Double(minimum), new Double(maximum),
				new Double(stepSize));
	}

	public MySpinnerNumberModel() {
		this(new Integer(0), null, null, new Integer(1));
	}

	public void setMinimum(Comparable minimum) {
		if ((minimum == null) ? (this.minimum != null) : !minimum
				.equals(this.minimum)) {
			this.minimum = minimum;
			fireStateChanged();
		}
	}

	public Comparable getMinimum() {
		return minimum;
	}

	public void setMaximum(Comparable maximum) {
		if ((maximum == null) ? (this.maximum != null) : !maximum
				.equals(this.maximum)) {
			this.maximum = maximum;
			fireStateChanged();
		}
	}

	public Comparable getMaximum() {
		return maximum;
	}

	public void setStepSize(Number stepSize) {
		if (stepSize == null) {
			throw new IllegalArgumentException("null stepSize");
		}
		if (!stepSize.equals(this.stepSize)) {
			this.stepSize = stepSize;
			fireStateChanged();
		}
	}

	public Number getStepSize() {
		return stepSize;
	}

	@SuppressWarnings("unchecked")
	private Number incrValue(int dir) {
		// 如果value为空,value=minvalue
		if (value == null) {
			value = (Number) minimum;
		}
		Number newValue;
		if ((value instanceof Float) || (value instanceof Double)) {
			double v = value.doubleValue()
					+ (stepSize.doubleValue() * (double) dir);
			if (value instanceof Double) {
				newValue = new Double(v);
			} else {
				newValue = new Float(v);
			}
		} else {
			long v = value.longValue() + (stepSize.longValue() * (long) dir);

			if (value instanceof Long) {
				newValue = new Long(v);
			} else if (value instanceof Integer) {
				newValue = new Integer((int) v);
			} else if (value instanceof Short) {
				newValue = new Short((short) v);
			} else {
				newValue = new Byte((byte) v);
			}
		}

		if ((maximum != null) && (maximum.compareTo(newValue) < 0)) {
			return null;
		}
		if ((minimum != null) && (minimum.compareTo(newValue) > 0)) {
			return null;
		} else {
			return newValue;
		}
	}

	public Object getNextValue() {
		return incrValue(+1);
	}

	public Object getPreviousValue() {
		return incrValue(-1);
	}

	public Number getNumber() {
		return value;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		if ((value == null)) {
			this.value = null;
			fireStateChanged();
		} else if (!value.equals(this.value)) {
			this.value = (Number) value;
			fireStateChanged();
		}
	}
}
