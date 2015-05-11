package com.swing.component.table;

import javax.swing.event.TableColumnModelEvent;
import javax.swing.table.TableColumnModel;

public class MyTableColumnModelEvent extends TableColumnModelEvent {

	public MyTableColumnModelEvent(TableColumnModel source, int from, int to) {
		super(source, from, to);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6409914349634226124L;

	private boolean codeTrigger = true;;

	public boolean isCodeTrigger() {
		return codeTrigger;
	}

	public void setCodeTrigger(boolean codeTrigger) {
		this.codeTrigger = codeTrigger;
	}

}
