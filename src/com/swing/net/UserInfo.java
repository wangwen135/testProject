package com.swing.net;

import java.net.SocketAddress;

public class UserInfo {
	// 该用户的图标
	private String icon;
	// 该用户的名字
	private String name;
	// 该用户的MulitcastSocket所在的IP和端口
	private SocketAddress address;
	// 该用户失去联系的次数
	private int lost;
	// 该用户对应的交谈窗口
	private ChatFrame chatFrame;

	public UserInfo() {
	}

	// 有参数的构造器
	public UserInfo(String icon, String name, SocketAddress address, int lost) {
		this.icon = icon;
		this.name = name;
		this.address = address;
		this.lost = lost;
	}


	// 使用address作为该用户的标识，所以根据address作为
	// 重写hashCode()和equals方法的标准
	public int hashCode() {
		return address.hashCode();
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SocketAddress getAddress() {
		return address;
	}

	public void setAddress(SocketAddress address) {
		this.address = address;
	}

	public int getLost() {
		return lost;
	}

	public void setLost(int lost) {
		this.lost = lost;
	}

	public ChatFrame getChatFrame() {
		return chatFrame;
	}

	public void setChatFrame(ChatFrame chatFrame) {
		this.chatFrame = chatFrame;
	}

	public boolean equals(Object obj) {
		if (obj != null && obj.getClass() == UserInfo.class) {
			return ((UserInfo) obj).getAddress().equals(address);
		}
		return false;
	}
}
