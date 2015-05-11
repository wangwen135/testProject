package com.regedit;

import java.util.prefs.*;

public class Registery {
	String[] keys = { "version", "initial", "creator" };
	String[] values = { "1.3", "ini.mp3", "caokai1818@sina.com" };

	// 把相应的值储存到变量中去
	public void writeValue() {
		// HKEY_LOCAL_MACHINE\Software\JavaSoft\prefs下写入注册表值.
		Preferences pre = Preferences.systemRoot().node("cmsp");

		pre.put("registermidas", "0");
		pre.put("pre", "pre");
	}

	public void readValue() {
		Preferences pre = Preferences.systemRoot().node("cmsp");
		System.out.println(pre.get("registermidas", "def"));

		System.out.println(pre.get("pre", "def"));
	}

	public static void main(String[] args) {
		Registery reg = new Registery();
		reg.writeValue();
		reg.readValue();
	}
}
