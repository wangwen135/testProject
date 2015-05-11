package com.swing.component;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.ScrollPaneConstants;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ScrollPaneTest extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtAsdfadffasdfasdfasdfdsf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ScrollPaneTest dialog = new ScrollPaneTest();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ScrollPaneTest() {
		setBounds(100, 100, 450, 245);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				JPanel panel = new JPanel();
				panel.setPreferredSize(new Dimension(100, 10));
				panel.setMaximumSize(new Dimension(100, 32767));
				FlowLayout flowLayout = (FlowLayout) panel.getLayout();
				flowLayout.setVgap(15);
				flowLayout.setHgap(15);
				scrollPane.setViewportView(panel);
				{
					JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
					panel.add(rdbtnNewRadioButton);
				}
				{
					JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("New radio button");
					panel.add(rdbtnNewRadioButton_2);
				}
				{
					JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("New radio button");
					panel.add(rdbtnNewRadioButton_3);
				}
				{
					JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("New radio button");
					panel.add(rdbtnNewRadioButton_1);
				}
				{
					txtAsdfadffasdfasdfasdfdsf = new JTextField();
					txtAsdfadffasdfasdfasdfdsf.setText("asdfadffasdfasdfasdfdsf");
					panel.add(txtAsdfadffasdfasdfasdfdsf);
					txtAsdfadffasdfasdfasdfdsf.setColumns(10);
				}
				{
					JLabel lblAsfasdfasdfasdfasdfasdfadfasdfasdfasdfasdf = new JLabel("asfasdfasdfasdfasdfasdfadfasdfasdfasdfasdf");
					panel.add(lblAsfasdfasdfasdfasdfasdfadfasdfasdfasdfasdf);
				}
				{
					JRadioButton rdbtnNewRadioButton_5 = new JRadioButton("New radio button");
					panel.add(rdbtnNewRadioButton_5);
				}
				{
					JButton btnAdfasdfadfafadfasdfadfasdf = new JButton("adfasdfadfafadfasdfadfasdf");
					panel.add(btnAdfasdfadfafadfasdfadfasdf);
				}
				{
					JButton btnAsdfasdfasdfasdfadfasdfasdf = new JButton("asdfasdfasdfasdfadfasdfasdf");
					panel.add(btnAsdfasdfasdfasdfadfasdfasdf);
				}
				{
					JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("New radio button");
					panel.add(rdbtnNewRadioButton_4);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
