package com.trayIcon;

import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author chenxiaoyang 系统工具快捷托盘
 */
public class SystemToolsTray {
	
	public static Runtime rt;
	

	public static void main(String[] args) throws Exception {
		rt = Runtime.getRuntime(); // java运行环境实例
		
		SystemTray tray = SystemTray.getSystemTray(); // 创建系统托盘
		
		final Map<String, String> commandMap = new LinkedHashMap<String, String>();
		
		PopupMenu trayMenu = new PopupMenu(); // 创建托盘右键菜单

		// 初始化命令库
		commandMap.put("计算器", "calc");
		commandMap.put("记事本", "notepad");
		commandMap.put("任务管理器", "taskmgr");
		commandMap.put("画图工具", "mspaint");
		commandMap.put("远程桌面", "mstsc");
		commandMap.put("屏幕键盘", "osk");
		 
		commandMap.put("打开文件夹", "explorer E:\\SVNworkspace\\doc");
		
		commandMap.put("定时关机", "shutdown -s -t 600");
		commandMap.put("取消关机", "shutdown -a");

		// 自动生成托盘右键菜单并绑定事件（执行命令）
		for (final String one : commandMap.keySet()) {
			MenuItem item = new MenuItem(one);
			item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						rt.exec(commandMap.get(one));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
			trayMenu.add(item);
		}

		MenuItem exitItem = new MenuItem("退出");
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		trayMenu.add(exitItem);

		Image image = Toolkit.getDefaultToolkit().getImage(
				"src/com/trayIcon/tool.png"); // 载入图片
		TrayIcon trayIcon = new TrayIcon(image, "快捷工具", trayMenu); // 创建trayIcon
		tray.add(trayIcon);
	}
}