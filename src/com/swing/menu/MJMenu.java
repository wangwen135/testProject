package com.swing.menu;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Rectangle;

import javax.swing.BoxLayout;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.DefaultMenuLayout;

import com.swing.menu.layout.MyMenuLayout;
import com.swing.menu.layout.MyMenuLayout2;

public class MJMenu extends JMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6081695718902441955L;

	public MJMenu(String string) {
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
	public void paintImmediately(int x, int y, int w, int h) {

		super.paintImmediately(x, y, w, h);
	}

	@Override
	public void paintImmediately(Rectangle r) {

		super.paintImmediately(r);
	}

	@Override
	public void updateUI() {

		super.updateUI();
	}

	@Override
	public void update(Graphics g) {

		super.update(g);
	}

	@Override
	public ButtonUI getUI() {

		return super.getUI();
	}

	@Override
	public String getUIClassID() {

		return super.getUIClassID();
	}

	@Override
	public void setPopupMenuVisible(boolean b) {
		// TODO Auto-generated method stub
		super.setPopupMenuVisible(b);
	}

	@Override
	public JPopupMenu getPopupMenu() {

		JPopupMenu jpm = super.getPopupMenu();

		LayoutManager laym = jpm.getLayout();

		DefaultMenuLayout dmly = new DefaultMenuLayout(jpm, BoxLayout.X_AXIS);
		// DefaultMenuLayout
		// 应该是Y布局

		// 换一个流布局
		// FlowLayout fly = new FlowLayout(FlowLayout.LEFT, 5, 2);

		MenuFlowLayout fly = new MenuFlowLayout(FlowLayout.LEFT, 5, 2);

//		jpm.setPopupSize(200, 600);
		//jpm.setLayout(fly);
		
		MyMenuLayout myml = new MyMenuLayout(jpm);
		//jpm.setLayout(myml);		

		
		
		BoxLayout bl = new BoxLayout(jpm, BoxLayout.Y_AXIS);
//		jpm.setLayout(bl);
		
		MyMenuLayout2 myml2 = new MyMenuLayout2(0,0);
		jpm.setLayout(myml2);
		
		return jpm;
	}

}
