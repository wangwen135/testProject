package com.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <pre>
 * 描述：读取舱单回执文件
 * 定长报文，通过本地编码转换成字节计算长度（中文两个长度）
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
public class ReadManifest {

	/**
	 * 默认的文本文件编码
	 */
	public static final String DEFAULT_TXT_FILE_ENCODE = "GBK";

	/**
	 * 文件编码
	 */
	public static String file_code = null;

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

	public static void main(String[] args) throws IOException {
		File f = new File("D:\\cmsp\\manifest\\PTRCV201304261411200031.MFT");

		try {
			ManifestRcv rcv = readManifest(f);

			System.out.println(rcv);

			System.out.println("读取回执成功");
		} catch (Exception e) {
			System.out.println("读取回执失败");
			e.printStackTrace();
		}

	}

	/**
	 * <pre>
	 * 读取舱单文件，返回回执对象
	 * 注意异常处理
	 * </pre>
	 * 
	 * @param f
	 *            需要读取的文件
	 * @return 回执对象
	 * @throws Exception
	 */
	public static ManifestRcv readManifest(File f) throws Exception {
		if (f == null || !f.exists()) {
			throw new FileNotFoundException("文件为空或没有找到");
		}

		ManifestRcv rcv = new ManifestRcv();
		String fileName = f.getName();
		// CTRCV = 数据中心接收回执
		// PTRCV = 海关接收回执
		rcv.setType(fileName.substring(0, 5));

		FileInputStream fis = null;

		FileChannel fc = null;

		try {
			fis = new FileInputStream(f);

			fc = fis.getChannel();

			// 分配与文件尺寸等大的缓冲区
			ByteBuffer bf = ByteBuffer.allocate((int) fc.size());

			// 整个文件内容全读入缓冲区
			fc.read(bf);

			// 反转此缓冲区
			bf.flip();

			// 有回车换行
			if (bf.remaining() <= 2) {
				throw new Exception("文件内容为空！");
			}

			// 读取表头标识
			byte[] b = new byte[16];
			bf.get(b);
			String s = new String(b, getFileCode());

			if (!"00000008RCPTHEAD".equals(s)) {
				throw new Exception("表头标识字段不正确");
			}

			ManifestRcvHead head = readHead(bf);
			rcv.setHead(head);

			if (bf.remaining() > 2) {
				b = new byte[16];
				bf.get(b);
				s = new String(b, getFileCode());

				if (!"00000008RCPTLIST".equals(s)) {
					throw new Exception("表体标识字段不正确");
				}
			}

			// 判断是否还有未读完的
			while (bf.remaining() > 2) {
				rcv.addBody(readBody(bf));
			}

		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fc != null) {
				try {
					fc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return rcv;
	}

	/**
	 * <pre>
	 * 读取表体数据
	 * </pre>
	 * 
	 * @param bf
	 * @return
	 * @throws Exception
	 */
	public static ManifestRcvBody readBody(ByteBuffer bf) throws Exception {
		// 标识字段长度+标识字段+表头数据长度+表头数据+标识字段长度+标识字段+单条表体数据长度+表体数据1+单条表体数据长度+表体数据2

		byte[] b = new byte[8];
		bf.get(b);
		String s = new String(b, getFileCode());
		if (!"00000108".equals(s)) {
			throw new Exception("表体数据长度不正确");
		}

		// 开始构建对象
		ManifestRcvBody rcvBody = new ManifestRcvBody();

		// VOYAGE_NO 总运单号 Varchar2(32) 否
		b = new byte[32];
		bf.get(b);
		s = new String(b, getFileCode());
		rcvBody.setVoyageNo(s.trim());

		// BILL_NO 分运单号 Varchar2(32) 否
		b = new byte[32];
		bf.get(b);
		s = new String(b, getFileCode());
		rcvBody.setBillNo(s.trim());

		// ENTRY_DATE 入库时间 Date(12) 否
		b = new byte[12];
		bf.get(b);
		s = new String(b, getFileCode());
		// 格式化日期
		rcvBody.setEntryDate(parseDate(s));

		// RTN_FLAG 回执结果 Varchar2(2) 否 NN：入库失败 NY：入库成功 MN：追加失败 MY：追加成功 UN:修改失败
		// UY:修改成功
		b = new byte[2];
		bf.get(b);
		s = new String(b, getFileCode());
		rcvBody.setRtnFlag(s.trim());

		// NOTES 备注 Varchar2(30) 回执结果详细说明。入库成功时，此字段为空
		b = new byte[30];
		bf.get(b);
		s = new String(b, getFileCode());
		rcvBody.setNotes(s.trim());

		return rcvBody;
	}

	/**
	 * <pre>
	 * 读取表头数据
	 * </pre>
	 * 
	 * @param bf
	 * @return ManifestRcvHead
	 * @throws Exception
	 */
	public static ManifestRcvHead readHead(ByteBuffer bf) throws Exception {
		// 标识字段长度+标识字段+表头数据长度+表头数据+标识字段长度+标识字段+单条表体数据长度+表体数据1+单条表体数据长度+表体数据2

		byte[] b = new byte[8];
		bf.get(b);
		String s = new String(b, getFileCode());
		if (!"00000091".equals(s)) {
			throw new Exception("表头数据长度不正确");
		}

		// 开始构建对象
		ManifestRcvHead rcvHead = new ManifestRcvHead();

		// SHIP_ID 运输工具航次 Varchar2(15) 否
		b = new byte[15];
		bf.get(b);
		s = new String(b, getFileCode());
		rcvHead.setShipID(s.trim());

		// VOYAGE_NO 总运单号 Varchar2(32) 否
		b = new byte[32];
		bf.get(b);
		s = new String(b, getFileCode());
		rcvHead.setVoyageNo(s.trim());

		// ENTRY_DATE 入库时间 Date(12) 否
		b = new byte[12];
		bf.get(b);
		s = new String(b, getFileCode());
		// 格式化日期
		rcvHead.setEntryDate(parseDate(s));

		// RTN_FLAG 回执结果 Varchar2(2) 否 NN：入库失败 NY：入库成功 MN：追加失败 MY：追加成功
		b = new byte[2];
		bf.get(b);
		s = new String(b, getFileCode());
		rcvHead.setRtnFlag(s.trim());

		// NOTES 备注 Varchar2(30) 回执结果详细说明。入库成功时，此字段为空
		b = new byte[30];
		bf.get(b);
		s = new String(b, getFileCode());
		rcvHead.setNotes(s.trim());

		return rcvHead;
	}

	/**
	 * <pre>
	 *  日期转换
	 *  yyyyMMddHHmm
	 * </pre>
	 * 
	 * @param str
	 *            字符串
	 * @return 日期
	 * @throws ParseException
	 */
	public static Date parseDate(String str) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		return sdf.parse(str);
	}

}
