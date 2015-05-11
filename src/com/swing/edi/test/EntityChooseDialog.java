package com.swing.edi.test;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dialog.ModalityType;

public class EntityChooseDialog extends JDialog {
	private static final long serialVersionUID = -7615049101869784560L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EntityChooseDialog dialog = new EntityChooseDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EntityChooseDialog() {
		setAlwaysOnTop(true);
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setTitle("entity选择器");
		setUndecorated(true);
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 103, 142);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JList list = new JList();
			list.setModel(new AbstractListModel() {
				String[] values = new String[] {"aaa", "bbbbb", "ccc", "dddddddddd", "eeeeeee"};
				public int getSize() {
					return values.length;
				}
				public Object getElementAt(int index) {
					return values[index];
				}
			});
			list.setVisibleRowCount(100);
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			contentPanel.add(list, BorderLayout.CENTER);
		}
	}

}
