package com.swing.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

	// 内部处理异常--读取和写入时如发生错误，返回null
	public static final File propertiesFile = new File(
			"configuration/EDI.properties");
	private static Properties properties;

	public static boolean creatNewFile(File file) throws IOException {
		if (file.exists()) {
			return true;
		} else {
			// 创建新文件
			file.getParentFile().mkdirs();
			return file.createNewFile();
		}
	}

	public static Properties getProperties() {

		if (properties != null) {
			return properties;
		}

		try {
			// 如果文件不存在则创建新文件
			creatNewFile(propertiesFile);
		} catch (IOException e) {
			System.out.println("配置文件不存在，并且创建失败！");
			e.printStackTrace();
			return null;
		}
		properties = new Properties();
		InputStream in = null;
		try {
			in = new FileInputStream(propertiesFile);
			properties.load(in);
			return properties;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("读取配置文件失败！");
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static String getPropertiesValue(String key) {
		Properties p = getProperties();
		if (p != null) {
			return p.getProperty(key);
		}
		return null;
	}

	public static boolean putPropertiesValue(String key, String value) {
		Properties p = getProperties();
		if (p != null) {
			p.setProperty(key, value);

			// 写入到文件
			// 文件肯定被创建了
			FileOutputStream outp = null;
			try {
				outp = new FileOutputStream(propertiesFile);
				p.store(outp, "EDI Report Properties\r\nunimportant files");
				return true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (outp != null) {
					try {
						outp.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return false;
		} else {
			return false;
		}

	}

	public static void main(String[] args) {

		String aaa = getPropertiesValue("aaa1");
		if (aaa != null) {
			System.out.println("aaa1不为空");
			System.out.println("aaa1的值是：" + aaa);
		} else {
			System.out.println("aaa1为空");
			System.out.println("为aaa1设置新值：测试中文啊222");
			putPropertiesValue("aaa1", "测试中文啊222");
		}

		aaa = getPropertiesValue("aaa1");
		if (aaa != null) {
			System.out.println("aaa1不为空");
			System.out.println("aaa1的值是：" + aaa);
		} else {
			System.out.println("aaa1为空");
			System.out.println("为aaa1设置新值：测试中文2222");
			putPropertiesValue("aaa", "hello world");
		}
	}
}
