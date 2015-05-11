package com.office;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OperatorRTF {

	/**
	 * 字符串转换为rtf编码
	 * 
	 * @param content
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String strToRtf(String content) throws UnsupportedEncodingException {

//		try {
//			System.out.println(content);
//			content = new String(content.getBytes(), "GB2312");
//			System.out.println(content);
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		char[] digital = "0123456789ABCDEF".toCharArray();
		StringBuffer sb = new StringBuffer("");
		byte[] bs = null;
		bs = content.getBytes("GB2312");
		int bit;
		for (int i = 0; i < bs.length; i++) {
			bit = (bs[i] & 0x0f0) >> 4;
			sb.append("\\'");
			sb.append(digital[bit]);
			bit = bs[i] & 0x0f;
			sb.append(digital[bit]);
		}
		return sb.toString();
	}

	/**
	 * 替换文档的可变部分
	 * 
	 * @param content
	 * @param replacecontent
	 * @param flag
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String replaceRTF(String content, String replacecontent, int flag) throws UnsupportedEncodingException {
		String rc = strToRtf(replacecontent);
		String target = "";
		System.out.println(rc);
		if (flag == 0) {
			target = content.replace("$timetop$", rc);
		}
		if (flag == 1) {
			target = content.replace("$info$", rc);
		}
		if (flag == 2) {
			target = content.replace("$idea$", rc);
		}
		if (flag == 3) {
			target = content.replace("$advice$", rc);
		}
		if (flag == 4) {
			target = content.replace("$infosend$", rc);
		}
		return target;
	}

	/**
	 * 获取文件路径
	 * 
	 * @param flag
	 * @return
	 */
	public String getSavePath() {

		String path = "D:\\";
		File fDirecotry = new File(path);
		if (!fDirecotry.exists()) {
			fDirecotry.mkdirs();
		}
		return path;
	}

	/**
	 * 半角转为全角
	 */
	public String ToSBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 32) {
				c[i] = (char) 12288;
				continue;
			}
			if (c[i] < 127) {
				c[i] = (char) (c[i] + 65248);
			}
		}
		return new String(c);
	}

	public void rgModel(String targetname, String content) throws UnsupportedEncodingException {

		/* 字节形式读取模板文件内容,将结果转为字符串 */
		String strpath = getSavePath();
		String sourname = strpath + "\\" + "test.rtf";
		String sourcecontent = "";
		InputStream ins = null;
		try {
			ins = new FileInputStream(sourname);
			byte[] b = new byte[1024];
			int bytesRead = 0;
			while (true) {
				bytesRead = ins.read(b, 0, 1024); // return final read bytes
				// counts
				if (bytesRead == -1) {// end of InputStream
					System.out.println("读取模板文件结束");
					break;
				}
				// convert to string using bytes
				sourcecontent += new String(b, 0, bytesRead);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		/* 修改变化部分 */
		String targetcontent = "";
		/**
		 * 拆分之后的数组元素与模板中的标识符对应关系 array[0]:timetop array[1]:info array[2]:idea
		 * array[3]:advice array[4]:infosend
		 */
		String array[] = content.split("~");

		// 2008年11月27日：更新模板之后时间无需自动填充
		for (int i = 0; i < array.length; i++) {
			if (i == 0) {
				targetcontent = replaceRTF(sourcecontent, array[i], i);
			} else {
				targetcontent = replaceRTF(targetcontent, array[i], i);
			}
		}
		/* 结果输出保存到文件 */
		try {
			FileWriter fw = new FileWriter(getSavePath() + "\\" + targetname,
					true);
			PrintWriter out = new PrintWriter(fw);
			System.out.println(targetcontent);
			if (targetcontent.equals("") || targetcontent == "") {
				out.println(sourcecontent);
			} else {
				out.println(targetcontent);
			}
			out.close();
			fw.close();
			System.out.println(getSavePath() + "  该目录下生成文件" + targetname
					+ " 成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws UnsupportedEncodingException {

		OperatorRTF oRTF = new OperatorRTF();

		// 被替换内容以"~"符号分割,处理的时候将其拆分为数组即可
		String content = "2008Y10M12D9H-2008Y10M12D6H~idea~look~我们参照检asf验药品asd的方法~we";
		Map<String, String> map = new HashMap<String, String>();
		map.put("aaaa", "aaaaa");
		
		oRTF.rgModel("desc.doc", content);
	}
}
