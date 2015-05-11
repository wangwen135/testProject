package com.swing.file;

import java.io.File;
import java.io.FilenameFilter;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class ReadFile {
	public static final String filePath = "config";

	public static void main(String[] args) {

		File dir = new File(filePath);
		FilenameFilter filt = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.matches(".+(\\.rtf|\\.RTF)");
			}
		};
		DecimalFormat numberformat = new DecimalFormat("##.##");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		File[] files = dir.listFiles(filt);
		for (File file : files) {
			System.out.print(file.getPath());
			System.out.print("   ");
			double d = file.length() / 1024.0;
			System.out.print(numberformat.format(d)+"KB");
			System.out.print("   ");
			long time = file.lastModified();
			System.out.print(dateFormat.format(new Date(time)));
			System.out.print("   ");
			System.out.print("\r\n");

		}
	}
}
