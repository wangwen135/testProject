package com.swing.menu;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPopupMenu;
import javax.swing.plaf.UIResource;

public class MenuFlowLayout extends FlowLayout implements UIResource {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2219189817444282721L;

	public MenuFlowLayout(int align, int hgap, int vgap) {
		super(align, hgap, vgap);
	}

	public Dimension preferredLayoutSize(Container target) {
		if (target instanceof JPopupMenu) {
			JPopupMenu popupMenu = (JPopupMenu) target;
			sun.swing.MenuItemLayoutHelper.clearUsedClientProperties(popupMenu);
			if (popupMenu.getComponentCount() == 0) {
				return new Dimension(0, 0);
			}
		}

		// Make BoxLayout recalculate cached preferred sizes
		// super.invalidateLayout(target);

		return super.preferredLayoutSize(target);
	}

}
