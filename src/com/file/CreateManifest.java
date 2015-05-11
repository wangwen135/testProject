package com.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 描述：创建舱单文件
 * 定长报文，通过转换成字节计算长度（中文两个长度）
 * </pre>
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2013-5-23      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class CreateManifest {

	/**
	 * 默认的文本文件编码
	 */
	public static final String DEFAULT_TXT_FILE_ENCODE = "GBK";

	/**
	 * 文件编码
	 */
	public static String file_code = null;

	// 先做到内存里面算了
	/**
	 * 日期串
	 */
	public static String dateStrKey;
	/**
	 * 序列号
	 */
	public static int seqNumber;

	public static void main(String[] args) throws UnsupportedEncodingException {

		Map<String, Object> dataHead = new HashMap<String, Object>();// 数据头

		// APPL_TYPE 记录类型 Varchar2(1) 否 “N” 新增 “M” 追加或修改
		dataHead.put("记录类型", 'N');

		// VOYAGE_NO 总运单号 Varchar2(32) 否 原运输工具编号，只能包含字母、数字、_
		dataHead.put("总运单号", "test87105240001");

		// SHIP_ID 运输工具航次 Varchar2(15) 否
		// 海运为船舶呼号,空运为航班号加日期,邮运为包裹单号，铁路为车厢编号或车次,汽车为车牌号
		dataHead.put("运输工具航次", "Y87986 20130524");

		// I_E_FLAG 舱单进/出口标志 Varchar2(1) 否 “I”为进口 “E”为出口
		dataHead.put("进出口标识", "I");

		// SHIP_NAME_CN 中文运输工具 Varchar2(32) 否
		dataHead.put("中文运输工具", "Y87986");

		// SHIP_NAME_EN 英文运输工具 Varchar2(32)
		dataHead.put("英文运输工具", "AIRLINE");

		// T_GROSS_WT 总重量 Number(18,5) 否 单位为公斤
		dataHead.put("总重量", new BigDecimal("13"));

		// T_PACK_NO 总件数 Number(18) 否
		dataHead.put("总件数", 1);

		// BILL_NUM 分运单总数 Number(4) 否 指本舱单文件中分运单总条数
		dataHead.put("分运单总数", 1);

		// TRAF_MODE 运输方式 Varchar2(1) 否 按运输方式代码表填报
		// 只能填一个
		dataHead.put("运输方式 ", "5");

		// I_E_DATE 进港日期 Date 否
		dataHead.put("进港日期", new Date());

		// L_D_PORT 起运港/抵运地 Varchar2(6) 否 进口填抵运地代码，出口填起运港代码，
		dataHead.put("起运港/抵运地 ", "110");

		// I_E_PORT 进出口岸代码 Varchar2(4) 否
		// 类型为字符型，长度为4位，内容不能为空，指接受舱单申报手续的海关编号（见海关代码表）
		dataHead.put("进出口岸代码 ", "2916");

		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < 1; i++) {// 数据体
			Map<String, Object> dataList1 = new HashMap<String, Object>();
			// APPL_TYPE 记录类型 Varchar2(1) 否 “N” 新增 “M” 追加 “U”修改
			dataList1.put("记录类型", 'N');

			// VOYAGE_NO 总运单号 Varchar2(32) 否 原运输工具编号
			dataList1.put("总运单号", "test87105240001");

			// BILL_NO 分运单号 Varchar2(32) 否 只能包含字母、数字、_
			dataList1.put("分运单号", "test852866036642");

			// SHIP_ID 运输工具航次 Varchar2(15) 否
			dataList1.put("运输工具航次", "Y87986 20130524");

			// MAIN_G_NAME 主要货物名称 Varchar2(50) 否
			dataList1.put("主要货物名称", "连接器13千克无牌");

			// PACK_NO 件数 Number (18) 否
			dataList1.put("件数", 1);

			// GROSS_WT 重量(公斤) Number(18,5) 否
			dataList1.put("重量", 113);

			// TOTAL_VALUE 价值 Number(11,2) 否
			dataList1.put("价值", 194);

			// CURR_CODE 币制 Varchar2 (3) 否 按照币制代码表填写
			dataList1.put("币制", "USD");

			dataList.add(dataList1);
		}

		try {
			createManifest(dataHead, dataList);

			System.out.println("文件生产成功！");

		} catch (IOException e) {
			System.out.println("生产文件失败!");
			e.printStackTrace();
		}

	
	}

	/**
	 * <pre>
	 * 创建舱单文件
	 * </pre>
	 * 
	 * @param dataHead
	 *            数据头
	 * @param dataList
	 *            数据体
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static void createManifest(Map<String, Object> dataHead,
			List<Map<String, Object>> dataList) throws IOException {
		// 这个应该是在配置文件中
		String path = "D:\\cmsp\\manifest";

		StringBuffer fileName = new StringBuffer("COSND");

		String dateStr = formatDate(new Date());

		fileName.append(dateStr);

		// 扫描文件系统，确定序列号
		// 先放到内存里面
		fileName.append(getSeqNum(dateStr));

		fileName.append(".MFT");

		File f = new File(path);
		f = new File(f, fileName.toString());

		// 处理数据？？
		// 比如说计算'记录类型'

		createManifest(dataHead, dataList, f);
	}

	/**
	 * <pre>
	 * 取序列号
	 * </pre>
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String getSeqNum(String dateStr) {
		if (dateStr.equals(dateStrKey)) {
			seqNumber++;
		} else {
			dateStrKey = dateStr;
			seqNumber = 0;
		}

		return formatNumber(seqNumber, 2, 0);
	}

	/**
	 * <pre>
	 * 创建舱单文件
	 * </pre>
	 * 
	 * @param dataHead
	 *            数据头
	 * @param dataList
	 *            数据体
	 * @param outFile
	 *            输出文件
	 * @param applType
	 *            记录类型
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	public static void createManifest(Map<String, Object> dataHead,
			List<Map<String, Object>> dataList, File outFile)
			throws IOException {

		// 创建文件
		createNewFile(outFile);

		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(outFile), getFileCode()));

			// 标识字段长度+标识字段
			bw.write("00000008DATAHEAD");
			// 写数据头
			writeDataHead(bw, dataHead);

			// 标识字段长度+标识字段
			bw.write("00000008DATALIST");
			// 循环
			for (Map<String, Object> map : dataList) {
				// 写数据体
				writeDataList(bw, map);
			}

			bw.flush();

		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * <pre>
	 * 写数据体
	 * </pre>
	 * 
	 * @param bw
	 *            缓冲输出流
	 * @param data
	 *            数据
	 * @throws IOException
	 */
	public static void writeDataList(BufferedWriter bw, Map<String, Object> data)
			throws IOException {
		// 单条表体数据长度+表体数据1

		// 单条表体数据长度
		bw.write("00000182");

		// APPL_TYPE 记录类型 Varchar2(1) 否 “N” 新增 “M” 追加 “U”修改

		char applType = (Character) data.get("记录类型");
		bw.write(applType);

		// VOYAGE_NO 总运单号 Varchar2(32) 否 原运输工具编号
		String mawb = (String) data.get("总运单号");
		bw.write(formatString(mawb, 32));

		// BILL_NO 分运单号 Varchar2(32) 否 只能包含字母、数字、_
		String bno = (String) data.get("分运单号");
		bw.write(formatString(bno, 32));

		// SHIP_ID 运输工具航次 Varchar2(15) 否
		String shipID = (String) data.get("运输工具航次");
		bw.write(formatString(shipID, 15));

		// MAIN_G_NAME 主要货物名称 Varchar2(50) 否
		String gdesc = (String) data.get("主要货物名称");
		bw.write(formatString(gdesc, 50));

		// PACK_NO 件数 Number (18) 否
		Number packNo = (Number) data.get("件数");
		bw.write(formatNumber(packNo, 18, 0));

		// GROSS_WT 重量(公斤) Number(18,5) 否
		Number weight = (Number) data.get("重量");
		bw.write(formatNumber(weight, 13, 5));

		// TOTAL_VALUE 价值 Number(11,2) 否
		Number value = (Number) data.get("价值");
		bw.write(formatNumber(value, 9, 2));

		// CURR_CODE 币制 Varchar2 (3) 否 按照币制代码表填写
		String currCode = (String) data.get("币制");
		bw.write(formatString(currCode, 3));

	}

	/**
	 * <pre>
	 * 写数据头
	 * </pre>
	 * 
	 * @param bw
	 *            缓冲输出流
	 * @param data
	 *            数据
	 * @throws IOException
	 */
	public static void writeDataHead(BufferedWriter bw, Map<String, Object> data)
			throws IOException {
		// 表头数据长度+表头数据

		// 表头数据长度
		bw.write("00000177");

		// APPL_TYPE 记录类型 Varchar2(1) 否 “N” 新增 “M” 追加或修改
		char applType = (Character) data.get("记录类型");
		bw.write(applType);

		// VOYAGE_NO 总运单号 Varchar2(32) 否 原运输工具编号，只能包含字母、数字、_
		String mawb = (String) data.get("总运单号");
		bw.write(formatString(mawb, 32));

		// SHIP_ID 运输工具航次 Varchar2(15) 否
		// 海运为船舶呼号,空运为航班号加日期,邮运为包裹单号，铁路为车厢编号或车次,汽车为车牌号
		String shipID = (String) data.get("运输工具航次");
		bw.write(formatString(shipID, 15));

		// I_E_FLAG 舱单进/出口标志 Varchar2(1) 否 “I”为进口 “E”为出口
		String ieFlag = (String) data.get("进出口标识");
		// 只能有一个长度
		ieFlag = ieFlag.toUpperCase();
		if (ieFlag.contains("进口") || ieFlag.contains("I")) {
			bw.write("I");
		} else {
			bw.write("E");
		}

		// SHIP_NAME_CN 中文运输工具 Varchar2(32) 否
		String shipNameCN = (String) data.get("中文运输工具");
		bw.write(formatString(shipNameCN, 32));

		// SHIP_NAME_EN 英文运输工具 Varchar2(32)
		String shipNameEN = (String) data.get("英文运输工具");
		bw.write(formatString(shipNameEN, 32));

		// T_GROSS_WT 总重量 Number(18,5) 否 单位为公斤
		Number totalWeight = (Number) data.get("总重量");
		bw.write(formatNumber(totalWeight, 13, 5));

		// T_PACK_NO 总件数 Number(18) 否
		Number totalPack = (Number) data.get("总件数");
		bw.write(formatNumber(totalPack, 18, 0));

		// BILL_NUM 分运单总数 Number(4) 否 指本舱单文件中分运单总条数
		Number billNum = (Number) data.get("分运单总数");
		bw.write(formatNumber(billNum, 4, 0));

		// TRAF_MODE 运输方式 Varchar2(1) 否 按运输方式代码表填报
		// 只能填一个
		String trafMode = (String) data.get("运输方式 ");
		// !!需要注意
		bw.write(trafMode);

		// I_E_DATE 进港日期 Date 否
		Date ieDate = (Date) data.get("进港日期");
		bw.write(formatDate(ieDate));

		// L_D_PORT 起运港/抵运地 Varchar2(6) 否 进口填抵运地代码，出口填起运港代码，
		String ldPort = (String) data.get("起运港/抵运地 ");
		bw.write(formatString(ldPort, 6));

		// I_E_PORT 进出口岸代码 Varchar2(4) 否
		// 类型为字符型，长度为4位，内容不能为空，指接受舱单申报手续的海关编号（见海关代码表）
		String iePort = (String) data.get("进出口岸代码 ");
		bw.write(formatString(iePort, 4));

	}

	/**
	 * <pre>
	 * 创建必要的上级目录和文件
	 * </pre>
	 * 
	 * @param file
	 * @throws IOException
	 */
	public static void createNewFile(File file) throws IOException {
		if (file.exists()) {
			return;
		} else {
			// 创建新文件
			if (!file.getParentFile().mkdirs()) {
				if (!file.getParentFile().exists()) {
					throw new IOException("不能创建文件路径");
				}
			}
			file.createNewFile();
		}
	}

	/**
	 * 获取文件编码
	 * 
	 * @return
	 */
	public static String getFileCode() {
		if (file_code == null) {
			// 取当前系统的文件编码
			file_code = System.getProperty("sun.jnu.encoding");
			if (file_code == null || "".equals(file_code.trim())) {
				// 使用默认值
				file_code = DEFAULT_TXT_FILE_ENCODE;
			}
		}

		return file_code;
	}

	/**
	 * <pre>
	 * 按照要求格式化字符串
	 * 长度过长将会截取，不会截取半个汉字
	 * </pre>
	 * 
	 * @param str
	 *            字符串
	 * @param length
	 *            长度
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String formatString(String str, int length)
			throws UnsupportedEncodingException {
		if (str == null) {
			str = "";
		}

		str = cutString(str, length);

		return fillString(str, length);
	}

	/**
	 * <pre>
	 * 截取字符串，按照指定的编码，计算字节长度
	 * 不会截取半个汉字
	 * </pre>
	 * 
	 * @param str
	 *            字符串
	 * @param length
	 *            长度
	 * @return 返回长度 <= length 的字符串
	 * @throws UnsupportedEncodingException
	 */
	public static String cutString(String str, int length)
			throws UnsupportedEncodingException {
		if (str.length() > length) {
			str = str.substring(0, length);
		}

		byte[] b = str.getBytes(getFileCode());
		int l = b.length;
		while (l > length) {
			str = str.substring(0, str.length() - 1);
			b = str.getBytes(getFileCode());
			l = b.length;
		}

		return str;
	}

	/**
	 * <pre>
	 * 右填充空格，按照指定的编码，计算字节长度
	 * </pre>
	 * 
	 * @param str
	 *            字符串
	 * @param length
	 *            长度(长度 >= 字符串的长度)
	 * @return 返回长度 = length 的字符串
	 * @throws UnsupportedEncodingException
	 */
	public static String fillString(String str, int length)
			throws UnsupportedEncodingException {

		StringBuffer strbuf = new StringBuffer(str);

		byte[] b = str.getBytes(getFileCode());

		int l = b.length;
		while (l < length) {
			strbuf.append(" ");

			b = strbuf.toString().getBytes(getFileCode());

			l = b.length;
		}

		return strbuf.toString();
	}

	/**
	 * <pre>
	 * 格式化数字
	 * </pre>
	 * 
	 * @param d
	 *            数字
	 * @param integer
	 *            整数部分长度
	 * @param fraction
	 *            小数部分长度
	 * @return
	 */
	public static String formatNumber(Number number, int integer, int fraction) {

		NumberFormat format = NumberFormat.getInstance();
		format.setMaximumFractionDigits(fraction);
		format.setMinimumFractionDigits(fraction);
		format.setMaximumIntegerDigits(integer);
		format.setMinimumIntegerDigits(integer);
		format.setRoundingMode(RoundingMode.HALF_UP);// 默认的四舍五入
		format.setGroupingUsed(false);

		return format.format(number);
	}

	/**
	 * <pre>
	 * 格式化日期
	 * yyyyMMddHHmm
	 * </pre>
	 * 
	 * @param d
	 *            日期
	 * @return
	 */
	public static String formatDate(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		return sdf.format(d);
	}

}
