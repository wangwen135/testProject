package com.swing.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.UIManager;

public class TimeDialog {
	
	public static void main(String[] args) {
		String msg = "消息弹出框提示信息\r\n该消息自动显示3秒钟，3秒之后自动消失\r\n不再阻塞当前调用线程";
		JOptionPane.showMessageDialog(null, "xasdfsadfasdfasd");


		System.out.println(UIManager.getString("OptionPane.messageDialogTitle"));
	}
	
	public static void main1(String[] args) {
		// JOptionPane.showMessageDialog(null, "message");
		JOptionPane pane = new JOptionPane(
				"消息弹出框提示信息\r\n该消息自动显示3秒钟，3秒之后自动消失\r\n不再阻塞当前调用线程");
		// pane.set.Xxxx(...); // Configure
		final JDialog dialog = pane.createDialog(null, "自动消失弹出框");
		Timer t = new Timer(3000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("触发");
				dialog.setVisible(false);

			}
		});
		t.setRepeats(false);
		t.start();

		System.out.println("被阻塞了");
		dialog.show();
		System.out.println("阻塞过了");
		System.out.println("设置time停止");
		t.stop();
		dialog.dispose();
		Object selectedValue = pane.getValue();
		System.out.println(selectedValue);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("end ");
		// if(selectedValue == null){
		// return CLOSED_OPTION;
		// //If there is not an array of option buttons:
		// if(options == null) {
		// if(selectedValue instanceof Integer)
		// return ((Integer)selectedValue).intValue();
		// return CLOSED_OPTION;
		// }
		// //If there is an array of option buttons:
		// for(int counter = 0, maxCounter = options.length;
		// counter < maxCounter; counter++) {
		// if(options[counter].equals(selectedValue))
		// return counter;
		// }
		// return CLOSED_OPTION;

	}
}
