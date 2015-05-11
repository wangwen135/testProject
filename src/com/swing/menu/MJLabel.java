package com.swing.menu;

import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.plaf.LabelUI;

public class MJLabel extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9220904195010942686L;

	public MJLabel(String string) {
		super(string);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}

	@Override
	public void paintAll(Graphics g) {
		super.paintAll(g);
	}

	@Override
	protected void paintBorder(Graphics g) {
		super.paintBorder(g);
	}

	@Override
	protected void paintChildren(Graphics g) {
		super.paintChildren(g);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
	}

	@Override
	public LabelUI getUI() {
		return super.getUI();
	}
	
	@Override
	public String getUIClassID() {
		return super.getUIClassID();
	}
		
}
