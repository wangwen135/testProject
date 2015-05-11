package com.office;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.crypto.Data;

public class ExpWord {
	public static String readFile(File fileName) {
		BufferedReader in = null;
		StringBuilder sb = new StringBuilder();
		try {
			in = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
				sb.append("\r\n");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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

		return sb.toString();
	}

	public static String encode(String content) {
		StringBuilder sb = new StringBuilder();
		byte[] b;
		try {
			// 编码应该需要注意
			b = content.getBytes("GB2312");
			for (int i = 0; i < b.length; i++) {
				sb.append("\\'");
				sb.append(Integer.toHexString(b[i] & 0xff));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		Map<String, String> data = new HashMap<String, String>();
		data.put("$info$", "测adf试ad信adf息ad");
		data.put("$idea$", "阿萨德飞记录12312asdf 12312asdf aa阿萨德");
		data.put("$a$", "表格中的数据A");
		data.put("$b$", "表格中的数据b");
		data.put("$c$", "表格中的数据c");
		data.put("$date$", "2011-01-01");

		String fileContent = readFile(new File("D://aa.rtf"));
		System.out.println(fileContent);

		for (Entry<String, String> entry : data.entrySet()) {
			entry.getKey();

			fileContent = fileContent.replace(entry.getKey(),
					encode(entry.getValue()));
		}

		try {
			BufferedWriter bfw = new BufferedWriter(new FileWriter(new File(
					"D://test.doc")));
			// FileWriter fw =
			bfw.write(fileContent);
			bfw.flush();
			bfw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
