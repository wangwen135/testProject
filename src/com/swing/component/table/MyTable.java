package com.swing.component.table;

import javax.swing.JTable;

public class MyTable extends JTable {

	boolean codeMoved = false;
	
	boolean codeScroll = true;
	

	public boolean isCodeScroll() {
		return codeScroll;
	}

	public void setCodeScroll(boolean codeScroll) {
		this.codeScroll = codeScroll;
	}

	public boolean isCodeMoved() {
		return codeMoved;
	}

	public void setCodeMoved(boolean codeMoved) {
		this.codeMoved = codeMoved;
	}

}
