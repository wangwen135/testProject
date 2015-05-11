package com.swing.test;

import java.io.File;
import java.io.IOException;

public class FileTest {
	
	public static void main(String[] args) {
		String path = "C:\\etrade\\zs\\up\\";
		File f = new File(path);
		
		System.out.println(f.mkdirs());
		if(!f.exists()){
			System.out.println("文件目录不存在");
		}
		
		File errorDir = new File(f,"error");
		errorDir.mkdir();
		
		File fs = new File(errorDir,"abc.txt");
		
		try {
			fs.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File fxml = new File(f,"1.xml");
		
		boolean rm = fxml.renameTo(new File(errorDir,"bak1.xml"));
		
		System.out.println(rm);
	}
	
	public static void main1(String[] args) {

		File f = new File("c:/");

		try {
			creatNewFile(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(f);
		System.out.println(f.getParentFile());
		System.out.println(f.getParent());

	}

	public static boolean creatNewFile(File desc) throws IOException {
		if (!desc.getParentFile().mkdirs()) {
			if (!desc.getParentFile().exists()) {
				throw new IOException("创建上级目录失败");
			}
		}
		return true;
	}
}
