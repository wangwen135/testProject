package com.sf.module.EDI.EDIImpl.report;

//import com.sf.module.common.util.PropritesUtils;

/**
 * 描述：导出报表的帮助类，封装需要的属性<br>
 * 文件编码没有弄成可配置的，先从配置文件中读取
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-6-7      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class ReportHelp {

	/**
	 * 配置文件编码的KEY
	 */
	public static final String EDI_TXT_ENCODING_KEY = "edi.txt.encoding";

	/**
	 * 默认的文本文件编码
	 */
	public static final String DEFAULT_TXT_FILE_ENCODE = "GBK";

	/**
	 * 文件类型
	 */
	private String filetype;
	/**
	 * 导出文本时的列分隔符号
	 */
	private String separator;
	/**
	 * 导出Excel时的工作表命名
	 */
	private String sheetName;

	/**
	 * 导出文本时的回车换行符号
	 */
	private String crlf;

	/**
	 * 文件编码
	 */
	private String fileEncode;

	/**
	 * 默认文件编码<br>
	 * edi.txt.encoding
	 */
	private static String defuatFileEncode;

	/**
	 * <pre>
	 * 取文件编码
	 * 按照 系统配置 --> 配置文件  --> 默认值  查找
	 * </pre>
	 * 
	 * @return
	 */
	public static synchronized String getDefaultFileEncode() {
		if (defuatFileEncode == null) {
			// 先取系统配置
			String code = System.getProperty(EDI_TXT_ENCODING_KEY);
			if (code != null && !"".equals(code.trim())) {
				defuatFileEncode = code;
				return defuatFileEncode;
			}
			try {
				// 再读配置文件
				//code = PropritesUtils.getValue(EDI_TXT_ENCODING_KEY);
				if (code != null && !"".equals(code.trim())) {
					defuatFileEncode = code;
					return defuatFileEncode;
				}
			} catch (Exception e) {

			}

			// 取当前系统的文件编码
			code = System.getProperty("sun.jnu.encoding");
			if (code != null && !"".equals(code.trim())) {
				defuatFileEncode = code;
				return defuatFileEncode;
			}

			// 使用默认值
			defuatFileEncode = DEFAULT_TXT_FILE_ENCODE;
		}

		return defuatFileEncode;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String getCrlf() {
		return crlf;
	}

	public void setCrlf(String crlf) {
		this.crlf = crlf;
	}

	public String getFileEncode() {
		if (fileEncode != null && !"".equals(fileEncode)) {
			return fileEncode;
		} else {
			return getDefaultFileEncode();
		}
	}

	public void setFileEncode(String fileEncode) {
		this.fileEncode = fileEncode;
	}

}
