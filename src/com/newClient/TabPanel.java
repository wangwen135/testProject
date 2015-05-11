package com.newClient;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

 /**
 * 描述：选项卡标题的组件
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2013-7-16      313921         Create
 * ****************************************************************************
 * </pre>
 * @author 313921
 * @since 1.0
 */
public class TabPanel extends JPanel {

	private static final long serialVersionUID = -1957432260845410598L;

	private JLabel titleLabel;
	private CloseButton closebutton;
	private final JTabbedPane tbpane;

	public TabPanel(String title, JTabbedPane tabbedPane) {
		this(null, title, tabbedPane);
	}

	public TabPanel(Icon icon, String title, JTabbedPane tabbedPane) {
		super(new FlowLayout(FlowLayout.LEFT, 3, 3));
		titleLabel = new JLabel(title);
		if (icon != null) {
			titleLabel.setIcon(icon);
		}
		this.tbpane = tabbedPane;
		closebutton = new CloseButton();
		add(titleLabel);
		add(closebutton);
		titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		setOpaque(false);
	}

	private class CloseButton extends JButton {
		private static final long serialVersionUID = 1L;

		private ImageIcon icon;

		public CloseButton() {
			icon = new ImageIcon(getClass().getResource(
					"Image/Button/close.png"));
			setSize(icon.getImage().getWidth(null),
					icon.getImage().getHeight(null));

			setIcon(icon);
			setBorder(null);
			setBorderPainted(false);
			setFocusPainted(false);
			setPressedIcon(new ImageIcon(getClass().getResource(
					"Image/Button/close_pressed.png")));
			setRolloverIcon(new ImageIcon(getClass().getResource(
					"Image/Button/close_rollover.png")));
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					tbpane.remove(tbpane.indexOfTabComponent(TabPanel.this));
				}
			});
		}
	}
}