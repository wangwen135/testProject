package com.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
	public static void main(String[] args) {
		copyFile(new File("D://dome1.doc"), new File("//10.0.4.245//tempSourceImg//dome1.doc"));
	}

	public static void copyFile(File src, File desc) {
		BufferedInputStream ins = null;
		BufferedOutputStream outs = null;
		try {
			ins = new BufferedInputStream(new FileInputStream(src));

			desc.getParentFile().mkdirs();

			outs = new BufferedOutputStream(new FileOutputStream(desc));

			byte[] b = new byte[8192];
			int len;
			while ((len = ins.read(b)) != -1) {
				outs.write(b, 0, len);
			}

			outs.flush();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ins != null) {
				try {
					ins.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (outs != null) {
				try {
					outs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
