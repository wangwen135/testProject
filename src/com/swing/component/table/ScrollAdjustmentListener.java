package com.swing.component.table;

import java.awt.Component;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JScrollPane;

/**
 * 描述：滚动同步
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-5-31      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class ScrollAdjustmentListener implements AdjustmentListener {

	private JScrollPane[] scrollpanes;

	public ScrollAdjustmentListener(JScrollPane... scrollpane) {
		scrollpanes = scrollpane;
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {

		for (JScrollPane pane : scrollpanes) {
			Component c = pane.getViewport().getView();
			if (c == null)
				return;
			MyTable table = (MyTable) c;
			if (table.isCodeScroll()) {
				pane.getHorizontalScrollBar().setValue(e.getValue());
			} 
		}

	}

}
