package com.swing.component.my;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 *一个标准的五角星按钮，中间为空，通过五个点连接而成。
 *颜色从上到下渐变，由topColor和bottomColor控制
 *
 * @author sunsnowad
 * @date 2009-7-27
 */
//TODO to be continued
public class JRegularStarButton extends JComponent{
	private boolean isPressed;
	private ArrayList<ActionListener> listeners = 
		new ArrayList<ActionListener>();
	private Polygon region;//按钮实际区域
	private JRegularStarButton thisButton;//标识当前对象
	
	private int gapTopOrLeft = 1;//最上点或最左点距边界的像素值
	private int gapBottomOrRight = 1;//最下点或最右点距边界的像素值
	private Color topColor = Color.yellow;//颜色渐变1
	private Color bottomColor = Color.red;//颜色渐变2

	
	public JRegularStarButton(){
		isPressed = false;
		region = new Polygon();
		thisButton = this;
		this.addMouseListener(new CustomMouseListener());
	}
	
	public void addActionListener(ActionListener listener){
		listeners.add(listener);
	}
	
	public void removeActionListener(ActionListener listener){
		listeners.remove(listener);
	}
	
	protected void fireActionPerformed(ActionEvent event){
		for(ActionListener listener:listeners){
			listener.actionPerformed(event);
		}
	}
	/**
	 * 
	 * @return StarButton的star区域
	 */
	public Polygon getRegion(){
		return region;
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		//打开反锯齿开关antialiasing
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		//GradientPaint渐变颜色控制
		GradientPaint paint;
		
		if(isPressed){
			paint = new GradientPaint(
					getWidth()/2,0,topColor,getWidth()/2,getHeight(),bottomColor);
			g2.setPaint(paint);
			g2.fillPolygon(region);
		}
		else{
			paint = new GradientPaint(
					getWidth()/2,0,bottomColor,getWidth()/2,getHeight(),topColor);
			g2.setPaint(paint);
			g2.fillPolygon(region);
		}
	}
	
	@Override
	public void setBounds(int x, int y, int width, int height){
		region = new Polygon();
		Point[] pointSet = calcStarRegion(width,height);
		if(pointSet == null)
			return;
		for(Point p : pointSet){
			region.addPoint(p.x, p.y);
		}
		super.setBounds(x, y, width, height);
	}
	
	/**
	 * 设置按钮按下状态
	 * @param isPressed
	 */
	public void setPressed(boolean isPressed){
		this.isPressed = isPressed;
		if(isShowing())
			repaint();
	}
	/**
	 * 
	 * @return 返回按钮的按下状态
	 */
	public boolean isPressed(){
		return isPressed;
	}
	
	/**
	 * @return the topColor
	 */
	public Color getTopColor() {
		return topColor;
	}

	/**
	 * @param topColor the topColor to set
	 */
	public void setTopColor(Color topColor) {
		this.topColor = topColor;
		if(isShowing())
			repaint();
	}

	/**
	 * @return the bottomColor
	 */
	public Color getBottomColor() {
		return bottomColor;
	}

	/**
	 * @param bottomColor the bottomColor to set
	 */
	public void setBottomColor(Color bottomColor) {
		this.bottomColor = bottomColor;
		if(isShowing())
			repaint();
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return star区域的点集
	 */
	private Point[] calcStarRegion(int width, int height){
		int minSizeOfButton = gapTopOrLeft+gapBottomOrRight;
		if(height < minSizeOfButton || width < minSizeOfButton){
			return null;//如果太小的按钮则不显示，太小抛出异常
		}
		Point[] result = new Point[5];
		double sin36 = Math.sin(Math.PI/5);
		double cos36 = Math.cos(Math.PI/5);
		double sin72 = Math.sin(Math.PI*2/5);
		double cos72 = Math.cos(Math.PI*2/5);
		
		double radius;

		if(width > height){
			double heightOfStar = height - minSizeOfButton;
			radius = heightOfStar/(1+cos36);
			
			result[0] = new Point((int)(width/2.0), gapTopOrLeft);
			
			result[1] = new Point(
					(int)((int)(width/2.0) + radius*sin36), 
					height-gapBottomOrRight);
			
			result[2] = new Point(
					(int)((int)(width/2.0) - radius*sin72),
					(int)((int)(height/2.0) - radius*cos72));
			
			result[3] = new Point(
					(int)((int)(width/2.0) + radius*sin72),
					(int)((int)(height/2.0) - radius*cos72));
			
			result[4] = new Point(
					(int)((int)(width/2.0) - radius*sin36),
					height-gapBottomOrRight);
		}
		else{
			double widthOfStar = width - minSizeOfButton;
			radius = widthOfStar/(1+cos36);
			
			result[0] = new Point(
					(int)(width/2.0), 
					(int)((int)(height/2.0)-radius));
			
			result[1] = new Point(
					(int)((int)(width/2.0) + radius*sin36), 
					(int)((int)(height/2.0) + radius*cos36));
			
			result[2] = new Point(
					(int)((int)(width/2.0) - radius*sin72),
					(int)((int)(height/2.0) - radius*cos72));
			
			result[3] = new Point(
					(int)((int)(width/2.0) + radius*sin72),
					(int)((int)(height/2.0) - radius*cos72));
			
			result[4] = new Point(
					(int)((int)(width/2.0) - radius*sin36),
					(int)((int)(height/2.0) + radius*cos36));
		}
		return result;
	}
	/**
	 * 
	 * @author sunsnowad
	 * 处理鼠标按下，当按下时出发所有注册的actionListener
	 */
	class CustomMouseListener extends MouseAdapter{

		@Override
		public void mousePressed(MouseEvent e){
			Point p = e.getPoint();
			if(e.getButton() == MouseEvent.BUTTON1 && getRegion().contains(p)){
				isPressed = true;
				repaint();
				fireActionPerformed(new ActionEvent(thisButton, 0, null));
			}
		}
		@Override
		public void mouseReleased(MouseEvent e){
			if(e.getButton() == MouseEvent.BUTTON1){
				isPressed = false;
				repaint();
			}
		}
	}
}