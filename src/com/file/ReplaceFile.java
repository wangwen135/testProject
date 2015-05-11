package com.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReplaceFile {
	public static List<String> filter = new ArrayList<String>();
	static {
		filter.add(" bno ");
		filter.add(" hscode ");
		filter.add(" srccode ");
		filter.add(" descode ");
		filter.add(" gate_type ");
		filter.add(" cusbatch ");
		filter.add(" velist ");
		filter.add(" srctel ");
		filter.add(" srcmobile ");
		filter.add(" src_business_unit_code ");
		filter.add(" src_postal_code ");
		filter.add(" src_city_code ");
		filter.add(" srccountry ");
		filter.add(" destel ");
		filter.add(" desmobile ");
		filter.add(" des_business_unit_code ");
		filter.add(" des_postal_code ");
		filter.add(" des_city_code ");
		filter.add(" descountry ");
		filter.add(" express_type ");
		filter.add(" packing ");
		filter.add(" start_city_code ");
		filter.add(" produce_src ");
		filter.add(" fee_currency ");
		filter.add(" psrcnm ");
		filter.add(" audit_area ");
		filter.add(" org_cusbatch ");
	}

	/**
	 * <pre>
	 * 过滤字段
	 * </pre>
	 * 
	 * @param line
	 * @return
	 */
	public static boolean filterFiled(String line) {
		String newLine = line.toUpperCase();

		for (String str : filter) {
			if (newLine.contains(str.toUpperCase())) {
				System.out.println("过滤字段：" + line);
				return true;
			}
		}

		return false;
	}

	public static void main(String[] args) {

		File f = new File("cmsp_uat_20130805_final.sql");

		File f2 = new File("newFile.sql");

		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new FileReader(f));

			bw = new BufferedWriter(new FileWriter(f2));

			String line;

			String end;
			String number;

			StringBuffer strbuf;

			while ((line = br.readLine()) != null) {

				// VARCHAR2(30)

				int i = line.indexOf("VARCHAR2");
				if (i > 0 && !filterFiled(line)) {

					strbuf = new StringBuffer();

					int left = line.lastIndexOf("(");

					strbuf.append(line.substring(0, left + 1));

					end = line.substring(left + 1, line.length());

					int right = end.lastIndexOf(")");

					number = end.substring(0, right);

					int num = Integer.valueOf(number.trim());

					if (num <= 5) {
						System.out.println("小于5，过滤:" + line);
						bw.write(line);
						bw.newLine();
						continue;
					}

					int newNum = num * 3;

					if (newNum > 4000) {
						strbuf.append(4000);
					} else {
						strbuf.append(newNum);
					}

					strbuf.append(end.substring(right));

					bw.write(strbuf.toString());

					System.out
							.println(line + "   ====>   " + strbuf.toString());

				} else {
					// 不存在
					bw.write(line);
				}

				bw.newLine();
			}

			bw.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
