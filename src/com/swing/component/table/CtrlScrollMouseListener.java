package com.swing.component.table;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CtrlScrollMouseListener extends MouseAdapter {
	private MyTable[] tables;

	public CtrlScrollMouseListener(MyTable... myTables) {
		this.tables = myTables;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		setScroll(true);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		setScroll(false);
	}

	private void setScroll(boolean isScroll) {
		for (MyTable tab : tables) {
			tab.setCodeScroll(isScroll);
		}
	}
}
