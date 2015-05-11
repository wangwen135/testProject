package com.sf.module.EDI.EDIImpl.reportModel.utils;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.dom4j.Element;

import com.sf.module.EDI.EDIImpl.reportModel.area.BodyArea;
import com.sf.module.EDI.EDIImpl.reportModel.area.HeadArea;
import com.sf.module.EDI.EDIImpl.reportModel.area.TailArea;
import com.sf.module.EDI.EDIImpl.reportModel.area.TitleArea;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.Cell;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.ICell;
import com.sf.module.EDI.EDIImpl.ui.table.EdiTableModel;

/**
 * 描述：报表模型工具类
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-2-3      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class ReportModelUtils {

	public static String toXml(TitleArea title, HeadArea head, BodyArea body,
			TailArea tail) {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<report>");
		sb.append(title.toXml());
		sb.append(head.toXml());
		sb.append(body.toXml());
		sb.append(tail.toXml());
		sb.append("</report>");

		return sb.toString();
	}

	/**
	 * <pre>
	 * 从XML中返回一个tableModel，xml必须是一个area节点
	 * </pre>
	 * 
	 * @param areaXML
	 *            areaXML
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static EdiTableModel getTableModelFromXml(Element areaXML) {

		int column = Integer.valueOf(areaXML.attributeValue("columnCount"));
		int row = Integer.valueOf(areaXML.attributeValue("rowCount"));

		EdiTableModel tableModel = new EdiTableModel(row, column);
		Vector<Vector<ICell>> data = tableModel.getDataVector();

		for (Iterator<Element> iterator = areaXML.elementIterator(); iterator
				.hasNext();) {
			Element e = iterator.next();
			ICell cell = new Cell();
			cell.fromXml(e);
			int tRow = cell.getRow();
			int tColumn = cell.getColumn();
			data.elementAt(tRow).set(tColumn, cell);
		}
		return tableModel;
	}

	/**
	 * <pre>
	 * 给区域模型中所有的cell设值
	 * 依赖表格模型
	 * </pre>
	 * 
	 * @param tm
	 * @param row
	 */
	public static void setAreaValue(EdiTableModel tm, Map<String, Object> row) {
		// 依赖表格模型，分别个每一个cell设置一下data
		Vector<Vector<ICell>> cModel = tm.getDataVector();
		for (int i = 0; i < cModel.size(); i++) {
			Vector<ICell> rowModel = cModel.elementAt(i);
			for (int j = 0; j < rowModel.size(); j++) {
				ICell cell = rowModel.elementAt(j);
				if (cell != null) {
					cell.setValue(row);
				}
			}
		}
	}

	/**
	 * <pre>
	 * 从区域模型中取值
	 * 依赖表格模型
	 * </pre>
	 * 
	 * @param tm
	 * @return {{一行},{二行}..}
	 */
	public static List<List<Object>> getAreaValue(EdiTableModel tm) {
		List<List<Object>> list = new ArrayList<List<Object>>();
		Vector<Vector<ICell>> cModel = tm.getDataVector();
		for (int i = 0; i < cModel.size(); i++) {
			Vector<ICell> rowModel = cModel.elementAt(i);
			List<Object> rowList = new ArrayList<Object>();
			for (int j = 0; j < rowModel.size(); j++) {
				ICell cell = rowModel.elementAt(j);
				if (cell != null) {
					rowList.add(cell.getFormatResult());
				} else {
					rowList.add(null);
				}
			}
			list.add(rowList);
		}
		return list;
	}

	/**
	 * <pre>
	 * 取单元格
	 * 调用之前注意是否需要同步表格
	 * </pre>
	 * 
	 * @param tm
	 *            表格模型
	 * @param row
	 *            行
	 * @param column
	 *            列
	 * @return 如果没有返回null
	 */
	public static ICell getCellAt(EdiTableModel tm, int row, int column) {
		if (tm == null)
			return null;
		if (row >= tm.getRowCount() || row < 0) {
			throw new IllegalArgumentException("错误的行数：[" + row
					+ "]，行数必须满足: 0 <= row < rowCount ");
		}
		if (column >= tm.getColumnCount() || column < 0) {
			throw new IllegalArgumentException("错误的列数：[" + column
					+ "]，列数必须满足: 0 <= column < columnCount ");
		}
		Vector<Vector<ICell>> cModel = tm.getDataVector();
		return cModel.elementAt(row).elementAt(column);
	}

	/**
	 * <pre>
	 * 将对象转换成BigDecimal 
	 * 不抛出异常
	 * </pre>
	 * 
	 * @param o
	 *            对象
	 * @return 如果对象为null或转换失败则返回null
	 */
	public static BigDecimal getDecimal(Object o) {
		if (o == null)
			return null;
		try {
			if (o instanceof BigDecimal)
				return (BigDecimal) o;
			if (o instanceof BigInteger)
				return new BigDecimal((BigInteger) o);
			if (o instanceof char[])
				return new BigDecimal((char[]) o);
			if (o instanceof Short)
				return new BigDecimal((Short) o);
			if (o instanceof Integer)
				return new BigDecimal((Integer) o);
			if (o instanceof Float)
				return new BigDecimal(Float.toString((Float) o));
			if (o instanceof Double)
				return new BigDecimal(Double.toString((Double) o));
			// 剩余的都做string处理
			return new BigDecimal(o.toString());
		} catch (Exception e) {
			// e.printStackTrace();
			return null;
		}
	}

	/**
	 * <pre>
	 * 将color对象转换成html颜色
	 * 如果c==null 将返回 黑色
	 * </pre>
	 * 
	 * @param c
	 * @return 如红色将返回：#FF0000
	 */
	public static String colorToHtmlColor(Color c) {
		if (c == null) {
			return "#000000";
		}
		StringBuffer colorBuf = new StringBuffer();
		String t = Integer.toHexString(c.getRed());
		if (t.length() == 1) {
			colorBuf.append("0");
		}
		colorBuf.append(t);
		t = Integer.toHexString(c.getGreen());
		if (t.length() == 1) {
			colorBuf.append("0");
		}
		colorBuf.append(t);
		t = Integer.toHexString(c.getBlue());
		if (t.length() == 1) {
			colorBuf.append("0");
		}
		colorBuf.append(t);

		return colorBuf.toString().toUpperCase();
	}

	/**
	 * <pre>
	 * 通过序列化和反序列化复制对象
	 * 需要实现Serializable接口
	 * </pre>
	 * 
	 * @param t
	 *            需要复制的对象
	 * @return 新对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T copySerializableObject(T t) {
		if (t == null)
			return null;
		ObjectOutputStream objOutStr = null;
		ObjectInputStream objInStr = null;
		try {
			ByteArrayOutputStream byteArrayOutStr = new ByteArrayOutputStream();
			objOutStr = new ObjectOutputStream(byteArrayOutStr);
			objOutStr.writeObject(t);
			objOutStr.flush();

			ByteArrayInputStream byteArrayInStr = new ByteArrayInputStream(
					byteArrayOutStr.toByteArray());
			objInStr = new ObjectInputStream(byteArrayInStr);
			Object readObj = objInStr.readObject();

			return (T) readObj;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (objOutStr != null)
				try {
					objOutStr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (objInStr != null)
				try {
					objInStr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return null;
	}

}
