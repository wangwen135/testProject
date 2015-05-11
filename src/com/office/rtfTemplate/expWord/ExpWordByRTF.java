package com.office.rtfTemplate.expWord;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import net.sourceforge.rtf.RTFTemplate;
import net.sourceforge.rtf.UnsupportedRTFTemplate;
import net.sourceforge.rtf.helper.RTFTemplateBuilder;

import org.apache.log4j.Logger;

import com.office.rtfTemplate.expWord.model.BigModel;
import com.office.rtfTemplate.expWord.model.CustomModel;
import com.swing.edi.report.utils.ReportUtils;

/**
 * 描述： 根据RTF文件导出word文档<br>
 * 需要在客户端配置文件中配置一个开关指定导出报表时是否启用格式化<br>
 * 需要在客户端配置文件中配置一个字段指定导出报表时使用的编码<br>
 * 
 * <pre>
 * #是否进行编码
 * expword.docode=true
 * #编码方式(word中使用的字符编码)
 * expword.code=gbk
 * </pre>
 * 
 * 默认不启用编码<br>
 * 每次导出时会读取一次配置文件
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-2-27      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @see ExpWordUtils
 * @author 313921
 * @since 1.0
 */
public class ExpWordByRTF {

	public static Logger logger = Logger.getLogger(ExpWordByRTF.class);

	/**
	 * 日期格式
	 */
	public static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");

	/**
	 * 时间格式
	 */
	public static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

	/**
	 * 日期时间格式
	 */
	public static SimpleDateFormat datetimeFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * rtf模板
	 */
	private File rtfFile;
	/**
	 * 导出的wrod文档
	 */
	private File docFile;

	/**
	 * 数据列表
	 */
	private List<Map<String, String>> dataList;

	public ExpWordByRTF(File rtfFile, File docFile,
			List<Map<String, String>> dataList) {
		// 判断不能为空
		if (rtfFile == null) {
			throw new NullPointerException("rtfFile不能为空！");
		}
		if (docFile == null) {
			throw new NullPointerException("docFile不能为空！");
		}
		if (dataList == null) {
			throw new NullPointerException("dataList不能为空！");
		}
		this.rtfFile = rtfFile;
		this.docFile = docFile;
		this.dataList = dataList;
	}

	/**
	 * <pre>
	 * 导出报表
	 * </pre>
	 */
	public void doReport() {
		// 每次导出时读取一下配置文件
		ExpWordUtils.readProperties();

		logger.info("模板文件位于：" + rtfFile.getAbsolutePath());

		RTFTemplateBuilder builder = RTFTemplateBuilder.newRTFTemplateBuilder();

		// 2. Get RTFtemplate with default Implementation of template engine
		// (Velocity)
		RTFTemplate rtfTemplate;
		try {
			rtfTemplate = builder.newRTFTemplate();

			// 3. Set the RTF model source
			rtfTemplate.setTemplate(rtfFile);

			// 4. Put the context
			rtfTemplate.put("h", new BigModel(dataList));

			// 都没有中文
			rtfTemplate.put("date", dateFormat.format(new Date()));
			rtfTemplate.put("time", timeFormat.format(new Date()));
			rtfTemplate.put("datetime", datetimeFormat.format(new Date()));

			// 单独的字段
			putFildToTemplate(rtfTemplate, dataList.get(0));

			// 将map转换成对象保存为list
			rtfTemplate.put("list", mapToModel(dataList));

			// 5. Merge the RTF source model and the context
			rtfTemplate.merge(docFile);

			logger.info("导出word文件位于：" + docFile.getAbsolutePath());
			JOptionPane.showMessageDialog(null, "导出完成！");
		} catch (UnsupportedRTFTemplate e) {

			logger.error("不支持的模板", e);
			JOptionPane.showMessageDialog(null, "不支持的模板！\r\n" + e.getMessage(),
					"导出报表失败", JOptionPane.ERROR_MESSAGE);

		} catch (FileNotFoundException e) {
			logger.error("模板文件没有找到" + e);
			JOptionPane.showMessageDialog(null,
					"模板文件没有找到！\r\n" + e.getMessage(), "导出报表失败",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			logger.error("导出word报表异常" + e);
			JOptionPane.showMessageDialog(null,
					"导出word报表异常！\r\n" + e.getMessage(), "导出报表失败",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * <pre>
	 * 将字段保存到上下文中
	 * </pre>
	 * 
	 * @param rtfTemplate
	 * @param map
	 */
	public void putFildToTemplate(RTFTemplate rtfTemplate,
			Map<String, String> map) {
		if (rtfTemplate != null && map != null) {
			for (Entry<String, String> e : map.entrySet()) {
				// 对字符串进行编码
				rtfTemplate.put(e.getKey(), ExpWordUtils.encode(e.getValue()));
			}
		}
	}

	/**
	 * <pre>
	 * 编码list
	 * 同时编码字符串
	 * </pre>
	 * 
	 * @param dataList
	 * @return
	 */
	public List<Map<String, String>> encodeList(
			List<Map<String, String>> dataList) {

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> m = null;
		for (Map<String, String> map : dataList) {
			m = new HashMap<String, String>();
			for (Entry<String, String> ent : map.entrySet()) {
				m.put(ent.getKey(), ExpWordUtils.encode(ent.getValue()));
			}
			list.add(m);
		}
		return list;
	}

	/**
	 * <pre>
	 * 将List<code><</code>Map> 转换成  List<code><</code>CustomModel>
	 * 同时编码字符串
	 * </pre>
	 * 
	 * @param dataList
	 * @return
	 */
	public List<CustomModel> mapToModel(List<Map<String, String>> dataList) {

		List<CustomModel> list = new ArrayList<CustomModel>();

		for (Map<String, String> map : dataList) {
			CustomModel cm = new CustomModel();
			cm.fromMap(map);
			list.add(cm);
		}
		return list;
	}

	public static void main(String[] args) {
		File f = new File("rtfFile/test.rtf");
		File out = new File("rtfFile/test_out.doc");

		ExpWordByRTF exp = new ExpWordByRTF(f, out,
				ReportUtils.getAnalogData(3));
		exp.doReport();
	}
}
