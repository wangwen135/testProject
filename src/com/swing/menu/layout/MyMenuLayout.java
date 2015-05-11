package com.swing.menu.layout;

import java.awt.AWTError;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.io.PrintStream;
import java.io.Serializable;

import javax.swing.SizeRequirements;

/**
 * 描述：copy BoxLayout 并修改
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2014-9-24      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class MyMenuLayout implements LayoutManager2, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6446724515424786561L;

	public MyMenuLayout(Container target) {
		this.target = target;
	}

	MyMenuLayout(Container target, PrintStream dbg) {
		this(target);
		this.dbg = dbg;
	}

	/**
	 * Returns the container that uses this layout manager.
	 * 
	 * @return the container that uses this layout manager
	 * 
	 * @since 1.6
	 */
	public final Container getTarget() {
		return this.target;
	}

	/**
	 * Indicates that a child has changed its layout related information, and
	 * thus any cached calculations should be flushed.
	 * <p>
	 * This method is called by AWT when the invalidate method is called on the
	 * Container. Since the invalidate method may be called asynchronously to
	 * the event thread, this method may be called asynchronously.
	 * 
	 * @param target
	 *            the affected container
	 * 
	 * @exception AWTError
	 *                if the target isn't the container specified to the
	 *                BoxLayout constructor
	 */
	public synchronized void invalidateLayout(Container target) {
		checkContainer(target);
		xChildren = null;
		yChildren = null;
		xTotal = null;
		yTotal = null;
	}

	/**
	 * Not used by this class.
	 * 
	 * @param name
	 *            the name of the component
	 * @param comp
	 *            the component
	 */
	public void addLayoutComponent(String name, Component comp) {
		invalidateLayout(comp.getParent());
	}

	/**
	 * Not used by this class.
	 * 
	 * @param comp
	 *            the component
	 */
	public void removeLayoutComponent(Component comp) {
		invalidateLayout(comp.getParent());
	}

	/**
	 * Not used by this class.
	 * 
	 * @param comp
	 *            the component
	 * @param constraints
	 *            constraints
	 */
	public void addLayoutComponent(Component comp, Object constraints) {
		invalidateLayout(comp.getParent());
	}

	/**
	 * Returns the preferred dimensions for this layout, given the components in
	 * the specified target container.
	 * 
	 * @param target
	 *            the container that needs to be laid out
	 * @return the dimensions >= 0 && <= Integer.MAX_VALUE
	 * @exception AWTError
	 *                if the target isn't the container specified to the
	 *                BoxLayout constructor
	 * @see Container
	 * @see #minimumLayoutSize
	 * @see #maximumLayoutSize
	 */
	public Dimension preferredLayoutSize(Container target) {
		Dimension size;
		synchronized (this) {
			checkContainer(target);
			checkRequests();
			size = new Dimension(xTotal.preferred, yTotal.preferred);
		}

		Insets insets = target.getInsets();
		size.width = (int) Math.min((long) size.width + (long) insets.left
				+ (long) insets.right, Integer.MAX_VALUE);
		size.height = (int) Math.min((long) size.height + (long) insets.top
				+ (long) insets.bottom, Integer.MAX_VALUE);
		return size;
	}

	/**
	 * Returns the minimum dimensions needed to lay out the components contained
	 * in the specified target container.
	 * 
	 * @param target
	 *            the container that needs to be laid out
	 * @return the dimensions >= 0 && <= Integer.MAX_VALUE
	 * @exception AWTError
	 *                if the target isn't the container specified to the
	 *                BoxLayout constructor
	 * @see #preferredLayoutSize
	 * @see #maximumLayoutSize
	 */
	public Dimension minimumLayoutSize(Container target) {
		Dimension size;
		synchronized (this) {
			checkContainer(target);
			checkRequests();
			size = new Dimension(xTotal.minimum, yTotal.minimum);
		}

		Insets insets = target.getInsets();
		size.width = (int) Math.min((long) size.width + (long) insets.left
				+ (long) insets.right, Integer.MAX_VALUE);
		size.height = (int) Math.min((long) size.height + (long) insets.top
				+ (long) insets.bottom, Integer.MAX_VALUE);
		return size;
	}

	/**
	 * Returns the maximum dimensions the target container can use to lay out
	 * the components it contains.
	 * 
	 * @param target
	 *            the container that needs to be laid out
	 * @return the dimenions >= 0 && <= Integer.MAX_VALUE
	 * @exception AWTError
	 *                if the target isn't the container specified to the
	 *                BoxLayout constructor
	 * @see #preferredLayoutSize
	 * @see #minimumLayoutSize
	 */
	public Dimension maximumLayoutSize(Container target) {
		Dimension size;
		synchronized (this) {
			checkContainer(target);
			checkRequests();
			size = new Dimension(xTotal.maximum, yTotal.maximum);
		}

		Insets insets = target.getInsets();
		size.width = (int) Math.min((long) size.width + (long) insets.left
				+ (long) insets.right, Integer.MAX_VALUE);
		size.height = (int) Math.min((long) size.height + (long) insets.top
				+ (long) insets.bottom, Integer.MAX_VALUE);
		return size;
	}

	/**
	 * Returns the alignment along the X axis for the container. If the box is
	 * horizontal, the default alignment will be returned. Otherwise, the
	 * alignment needed to place the children along the X axis will be returned.
	 * 
	 * @param target
	 *            the container
	 * @return the alignment >= 0.0f && <= 1.0f
	 * @exception AWTError
	 *                if the target isn't the container specified to the
	 *                BoxLayout constructor
	 */
	public synchronized float getLayoutAlignmentX(Container target) {
		checkContainer(target);
		checkRequests();
		return xTotal.alignment;
	}

	/**
	 * Returns the alignment along the Y axis for the container. If the box is
	 * vertical, the default alignment will be returned. Otherwise, the
	 * alignment needed to place the children along the Y axis will be returned.
	 * 
	 * @param target
	 *            the container
	 * @return the alignment >= 0.0f && <= 1.0f
	 * @exception AWTError
	 *                if the target isn't the container specified to the
	 *                BoxLayout constructor
	 */
	public synchronized float getLayoutAlignmentY(Container target) {
		checkContainer(target);
		checkRequests();
		return yTotal.alignment;
	}

	/**
	 * Called by the AWT <!-- XXX CHECK! --> when the specified container needs
	 * to be laid out.
	 * 
	 * @param target
	 *            the container to lay out
	 * 
	 * @exception AWTError
	 *                if the target isn't the container specified to the
	 *                BoxLayout constructor
	 */
	public void layoutContainer(Container target) {
		checkContainer(target);
		int nChildren = target.getComponentCount();
		int[] xOffsets = new int[nChildren];
		int[] xSpans = new int[nChildren];
		int[] yOffsets = new int[nChildren];
		int[] ySpans = new int[nChildren];

		Dimension alloc = target.getSize();//在此之前会先调用一次,preferredLayoutSize 方法计算组件的大小
		Insets in = target.getInsets();
		alloc.width -= in.left + in.right;
		alloc.height -= in.top + in.bottom;

		// Resolve axis to an absolute value (either X_AXIS or Y_AXIS)
		ComponentOrientation o = target.getComponentOrientation();

		boolean ltr = o.isLeftToRight();

		// determine the child placements
		synchronized (this) {
			checkRequests();

			// 创建一组偏移量/区域对，指定如何按照指定对齐方式对一组组件进行布局
			// 这个方法不懂？？？
			// 将这个方法拷贝出来
			// SizeRequirements.
			calculateAlignedPositions(alloc.width, xTotal, xChildren, xOffsets,
					xSpans, ltr);

			// 创建一组表示如何对一组组件进行端到端布局的偏移量/区域对。
			//SizeRequirements.
			calculateTiledPositions(alloc.height, yTotal,
					yChildren, yOffsets, ySpans,true);

		}

		// flush changes to the container
		for (int i = 0; i < nChildren; i++) {
			Component c = target.getComponent(i);
			c.setBounds((int) Math.min((long) in.left + (long) xOffsets[i],
					Integer.MAX_VALUE), (int) Math.min((long) in.top
					+ (long) yOffsets[i], Integer.MAX_VALUE), xSpans[i],
					ySpans[i]);

		}
		if (dbg != null) {
			for (int i = 0; i < nChildren; i++) {
				Component c = target.getComponent(i);
				dbg.println(c.toString());
				dbg.println("X: " + xChildren[i]);
				dbg.println("Y: " + yChildren[i]);
			}
		}

	}

	public static void calculateAlignedPositions(int allocated,
			SizeRequirements total, SizeRequirements[] children, int[] offsets,
			int[] spans, boolean normal) {
		float totalAlignment = normal ? total.alignment
				: 1.0f - total.alignment;
		int totalAscent = (int) (allocated * totalAlignment);
		int totalDescent = allocated - totalAscent;
		for (int i = 0; i < children.length; i++) {
			SizeRequirements req = children[i];
			float alignment = normal ? req.alignment : 1.0f - req.alignment;
			int maxAscent = (int) (req.maximum * alignment);
			int maxDescent = req.maximum - maxAscent;
			int ascent = Math.min(totalAscent, maxAscent);
			int descent = Math.min(totalDescent, maxDescent);

			offsets[i] = totalAscent - ascent;
			spans[i] = (int) Math.min((long) ascent + (long) descent,
					Integer.MAX_VALUE);
		}
	}

	public static void calculateTiledPositions(int allocated,
			SizeRequirements total, SizeRequirements[] children, int[] offsets,
			int[] spans, boolean forward) {
		// The total argument turns out to be a bad idea since the
		// total of all the children can overflow the integer used to
		// hold the total. The total must therefore be calculated and
		// stored in long variables.
		long min = 0;
		long pref = 0;
		long max = 0;
		for (int i = 0; i < children.length; i++) {
			min += children[i].minimum;
			pref += children[i].preferred;
			max += children[i].maximum;
		}
		if (allocated >= pref) {
			expandedTile(allocated, min, pref, max, children, offsets, spans,
					forward);
		} else {
			compressedTile(allocated, min, pref, max, children, offsets, spans,
					forward);
		}
	}

	private static void expandedTile(int allocated, long min, long pref,
			long max, SizeRequirements[] request, int[] offsets, int[] spans,
			boolean forward) {

		// ---- determine what we have to work with ----
		float totalPlay = Math.min(allocated - pref, max - pref);
		float factor = (max - pref == 0) ? 0.0f : totalPlay / (max - pref);

		// ---- make the adjustments ----
		int totalOffset;
		if (forward) {
			// lay out with offsets increasing from 0
			totalOffset = 0;
			for (int i = 0; i < spans.length; i++) {
				offsets[i] = totalOffset;
				SizeRequirements req = request[i];
				int play = (int) (factor * (req.maximum - req.preferred));
				spans[i] = (int) Math.min((long) req.preferred + (long) play,
						Integer.MAX_VALUE);
				totalOffset = (int) Math.min((long) totalOffset
						+ (long) spans[i], Integer.MAX_VALUE);
			}
		} else {
			// lay out with offsets decreasing from the end of the allocation
			totalOffset = allocated;
			for (int i = 0; i < spans.length; i++) {
				SizeRequirements req = request[i];
				int play = (int) (factor * (req.maximum - req.preferred));
				spans[i] = (int) Math.min((long) req.preferred + (long) play,
						Integer.MAX_VALUE);
				offsets[i] = totalOffset - spans[i];
				totalOffset = (int) Math.max((long) totalOffset
						- (long) spans[i], 0);
			}
		}
	}

	private static void compressedTile(int allocated, long min, long pref,
			long max, SizeRequirements[] request, int[] offsets, int[] spans,
			boolean forward) {

		// ---- determine what we have to work with ----
		float totalPlay = Math.min(pref - allocated, pref - min);
		float factor = (pref - min == 0) ? 0.0f : totalPlay / (pref - min);

		// ---- make the adjustments ----
		int totalOffset;
		if (forward) {
			// lay out with offsets increasing from 0
			totalOffset = 0;
			for (int i = 0; i < spans.length; i++) {
				offsets[i] = totalOffset;
				SizeRequirements req = request[i];
				float play = factor * (req.preferred - req.minimum);
				spans[i] = (int) (req.preferred - play);
				totalOffset = (int) Math.min((long) totalOffset
						+ (long) spans[i], Integer.MAX_VALUE);
			}
		} else {
			// lay out with offsets decreasing from the end of the allocation
			totalOffset = allocated;
			for (int i = 0; i < spans.length; i++) {
				SizeRequirements req = request[i];
				float play = factor * (req.preferred - req.minimum);
				spans[i] = (int) (req.preferred - play);
				offsets[i] = totalOffset - spans[i];
				totalOffset = (int) Math.max((long) totalOffset
						- (long) spans[i], 0);
			}
		}
	}

	void checkContainer(Container target) {
		if (this.target != target) {
			throw new AWTError("BoxLayout can't be shared");
		}
	}

	void checkRequests() {
		if (xChildren == null || yChildren == null) {
			// The requests have been invalidated... recalculate
			// the request information.
			int n = target.getComponentCount();
			xChildren = new SizeRequirements[n];
			yChildren = new SizeRequirements[n];
			for (int i = 0; i < n; i++) {
				Component c = target.getComponent(i);
				if (!c.isVisible()) {
					xChildren[i] = new SizeRequirements(0, 0, 0,
							c.getAlignmentX());
					yChildren[i] = new SizeRequirements(0, 0, 0,
							c.getAlignmentY());
					continue;
				}
				Dimension min = c.getMinimumSize();
				Dimension typ = c.getPreferredSize();
				Dimension max = c.getMaximumSize();
				xChildren[i] = new SizeRequirements(min.width, typ.width,
						max.width, c.getAlignmentX());
				yChildren[i] = new SizeRequirements(min.height, typ.height,
						max.height, c.getAlignmentY());
			}

			// 确定对齐一组组件所需的总空间
			xTotal = SizeRequirements.getAlignedSizeRequirements(xChildren);

			// 确定端到端放置一组组件需要占用的总空间
			yTotal = SizeRequirements.getTiledSizeRequirements(yChildren);

		}
	}

	private Container target;

	private transient SizeRequirements[] xChildren;
	private transient SizeRequirements[] yChildren;
	private transient SizeRequirements xTotal;
	private transient SizeRequirements yTotal;

	private transient PrintStream dbg;
}
