package com.image;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.*;

/**
 * @author Hardneedl
 */
public class ImageEmbedded extends JFrame {
	public String getTitle() {
		return "frame title";
	}

	static private final Dimension size = new Dimension(600, 400);

	public Dimension getPreferredSize() {
		return size;
	}

	public Dimension getMaximumSize() {
		return size;
	}

	public Dimension getMinimumSize() {
		return size;
	}

	public Dimension getSize() {
		return size;
	}

	private File currentDir = new File(".");
	private JFileChooser chooser = new JFileChooser(currentDir);
	private FileNameExtensionFilter filter = new FileNameExtensionFilter(
			"Images", "jpg", "gif", "png");
	private BufferedImage image, overlayImage;
	private JSpinner transField, xField, yField;

	private JComponent canvas = new JComponent() {
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (image != null) {
				Graphics gg = g.create();
				gg.drawImage(image, 0, 0, getWidth(), getHeight(), this);
				gg.dispose();
			}

			if (overlayImage != null) {
				Graphics2D g2d = (Graphics2D) g.create();
				float trn = ((Double) transField.getValue()).floatValue();
				int x = (Integer) xField.getValue(), y = (Integer) yField
						.getValue();

				AlphaComposite alp = AlphaComposite.getInstance(
						AlphaComposite.SRC_OVER, trn);
				g2d.setComposite(alp);

				g2d.drawImage(overlayImage, x, y, this);
				g2d.dispose();
			}
		}

		public Border getBorder() {
			return BorderFactory.createLineBorder(Color.BLACK, 2);
		}
	};

	ImageEmbedded() throws HeadlessException {
		init();
		attachListeners();
		doLay();
	}

	private void init() {
		xField = new JSpinner(new SpinnerNumberModel(70, 0, 2000, 10));
		yField = new JSpinner(new SpinnerNumberModel(70, 0, 2000, 10));
		transField = new JSpinner(new SpinnerNumberModel(.6d, .1d, 1.0d, .1d));
	}

	private void attachListeners() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void doLay() {
		Container container = getContentPane();
		JPanel buttonsPane = new JPanel();
		buttonsPane.add(new JButton(new OpenFileAction()));
		buttonsPane.add(new JButton(new OpenOverlayAction()));
		buttonsPane.add(new JLabel("x:"));
		buttonsPane.add(xField);
		buttonsPane.add(new JLabel("y:"));
		buttonsPane.add(yField);
		buttonsPane.add(new JLabel("transparency:"));
		buttonsPane.add(transField);
		buttonsPane.add(new JButton(new RefreshButton()));
		buttonsPane.add(new JButton(new ExportImageAction()));

		container.add(buttonsPane, BorderLayout.NORTH);
		container.add(canvas, BorderLayout.CENTER);
		pack();
		setVisible(true);
	}

	private class OpenFileAction extends AbstractAction {

		private OpenFileAction() {
			super("Open Image...");
		}

		public void actionPerformed(ActionEvent e) {

			chooser.setFileFilter(filter);
			if (JFileChooser.APPROVE_OPTION == chooser
					.showOpenDialog(ImageEmbedded.this)) {
				File f = chooser.getSelectedFile();
				currentDir = f.getParentFile();
				try {
					image = ImageIO.read(f);
					canvas.paintImmediately(canvas.getBounds());
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		}
	}

	private class OpenOverlayAction extends AbstractAction {
		private OpenOverlayAction() {
			super("Open Overlay...");
		}

		public void actionPerformed(ActionEvent e) {
			chooser.setFileFilter(filter);
			if (JFileChooser.APPROVE_OPTION == chooser
					.showOpenDialog(ImageEmbedded.this)) {
				File f = chooser.getSelectedFile();
				currentDir = f.getParentFile();
				try {
					overlayImage = ImageIO.read(f);
					canvas.paintImmediately(canvas.getBounds());
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		}
	}

	private class RefreshButton extends AbstractAction {
		private RefreshButton() {
			super("Refresh");
		}

		public void actionPerformed(ActionEvent e) {
			canvas.paintImmediately(canvas.getBounds());
		}
	}

	private class ExportImageAction extends AbstractAction {
		private ExportImageAction() {
			super("Export...");
		}

		public void actionPerformed(ActionEvent e) {
			if (chooser.showSaveDialog(ImageEmbedded.this) == JFileChooser.APPROVE_OPTION) {
				BufferedImage img = new BufferedImage(canvas.getWidth(),
						canvas.getHeight(), BufferedImage.TYPE_INT_BGR);
				canvas.print(img.getGraphics());

				File f = chooser.getSelectedFile();
				try {
					ImageIO.write(img, "png", f);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		new ImageEmbedded();
	}
}
