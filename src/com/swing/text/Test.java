package com.swing.text;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.UndoableEditEvent;
import javax.swing.undo.UndoManager;

public class Test extends JFrame implements KeyListener {

    private JPanel jpContents = null;

    private JLabel jlTitle = null;

    private JLabel jlTitleEn = null;

    private JPanel jpButtom = null;

    private JButton jbSave = null;

    private JButton jbCancel = null;

    private JTextPane jtpContents = null;

    private JScrollPane jspContents = null;

	private UndoManager undomgr = new UndoManager() {
		private static final long serialVersionUID = 2443516350675241981L;

		public void undoableEditHappened(UndoableEditEvent e) {
			this.addEdit(e.getEdit());
		}
	};

    public Test() {
        init();
    }

    private void init() {

        JPanel jpNotes = new JPanel(new GridLayout(3, 1));
        jlTitle = new JLabel("编辑支持Ctrl+Z和Ctrl+Y的撤销/恢复操作。", JLabel.CENTER);
        jlTitleEn = new JLabel(
                "Editing supports undo(Ctrl+Z) and redo(Ctrl+Y).", JLabel.CENTER);
        jpNotes.add(new JLabel());
        jpNotes.add(jlTitle);
        jpNotes.add(jlTitleEn);

        jpContents = new JPanel(new BorderLayout());
        jtpContents = new JTextPane();
        jtpContents.getDocument().addUndoableEditListener(undomgr);
        jtpContents.addKeyListener(this);

        jtpContents.setText("");
        jtpContents.setFont(new Font(null, Font.BOLD, 16));
        jtpContents.setPreferredSize(new Dimension(760, 400));
        jtpContents.setSize(new Dimension(760, 400));
        jspContents = new JScrollPane(jtpContents);
        jspContents.setSize(new Dimension(760, 400));
        jspContents
                .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        jpContents.add(jpNotes, BorderLayout.NORTH);
        jpContents.add(jspContents, BorderLayout.CENTER);

        jpButtom = new JPanel(new FlowLayout());
        jbSave = new JButton("保存 Save");
        jbCancel = new JButton("取消 Cancel");
        jpButtom.add(jbSave);
        jpButtom.add(jbCancel);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(jpContents, BorderLayout.NORTH);
        this.getContentPane().add(jpButtom, BorderLayout.CENTER);
        this.setBounds(350, 350, 780, 540);
        this.setMaximizedBounds(new Rectangle(new Dimension(780, 540)));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String args[]) {
        new Test();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getSource().equals(this.jtpContents)) {
            int keycode = e.getKeyCode();
            if (e.getModifiers() == KeyEvent.CTRL_MASK) {
                if (keycode == KeyEvent.VK_Z) {
                    if (undomgr.canUndo())
                        undomgr.undo();
                }
                if (keycode == KeyEvent.VK_Y) {
                    if (undomgr.canRedo())
                        undomgr.redo();
                }
            }
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

}