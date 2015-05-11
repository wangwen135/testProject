package com.newClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述：保存读取临时配置文件
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2013-7-16      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class TempPropertiesUtils {

	private static Logger logger = LoggerFactory
			.getLogger(TempPropertiesUtils.class);

	public static final File propertiesFile = new File("tmpConfig.properties");
	private static Properties properties;

	/**
	 * <pre>
	 * 创建新文件
	 * </pre>
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static boolean creatNewFile(File file) throws IOException {
		if (file.exists()) {
			return true;
		} else {
			// 创建新文件
			if (file.getParentFile() != null) {
				if (!file.getParentFile().mkdirs()) {
					if (!file.getParentFile().exists()) {
						return false;
					}
				}
			}
			return file.createNewFile();
		}
	}

	/**
	 * <pre>
	 * 取Properties对象，单例
	 * </pre>
	 * 
	 * @return
	 */
	public static synchronized Properties getProperties() {

		if (properties != null) {
			return properties;
		}
		try {
			// 如果文件不存在则创建新文件
			if (!creatNewFile(propertiesFile)) {
				return null;
			}
		} catch (IOException e) {
			logger.error("临时配置文件不存在，并且创建失败！", e);
			return null;
		}
		properties = new Properties();
		InputStream in = null;
		try {
			in = new FileInputStream(propertiesFile);
			properties.load(in);
			return properties;
		} catch (FileNotFoundException e) {
			logger.error("文件没有发现！", e);
		} catch (IOException e) {
			logger.error("读取临时配置文件失败！", e);
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

	/**
	 * <pre>
	 * 获取配置
	 * </pre>
	 * 
	 * @param key
	 * @return
	 */
	public static String getPropertiesValue(String key) {
		Properties p = getProperties();
		if (p != null) {
			return p.getProperty(key);
		}
		return null;
	}

	/**
	 * <pre>
	 * 保存配置到文件
	 * </pre>
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean putPropertiesValue(String key, String value) {
		Properties p = getProperties();
		if (p != null) {
			p.setProperty(key, value);
			// 文件肯定被创建了
			FileOutputStream outp = null;
			try {
				outp = new FileOutputStream(propertiesFile);
				p.store(outp, "temp config Properties");
				return true;
			} catch (FileNotFoundException e) {
				logger.error("写入临时配置文件时文件不存在", e);
			} catch (IOException e) {
				logger.error("写入配置失败 ", e);
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
	}
}
