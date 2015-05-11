package com.swing.menu;

import java.awt.Dimension;

import javax.swing.JMenuItem;

public class MyJMenuItem extends JMenuItem{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1566834232241196446L;

	public MyJMenuItem() {
		super();
	}
	
	public MyJMenuItem(String text) {
		super(text);
	}
	
	
	@Override
	public Dimension getPreferredSize() {
		
		Dimension d = super.getPreferredSize();
		
		return d;
	}
	
	
	@Override
	public void setPreferredSize(Dimension preferredSize) {
		//谁修改了它
		int w =  (int) preferredSize.getWidth();
		System.out.println(w);
		super.setPreferredSize(preferredSize);
	}
	
	@Override
	public void setBounds(int x, int y, int width, int height) {
		
		super.setBounds(x, y, width, height);
	}
}
