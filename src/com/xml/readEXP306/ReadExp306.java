package com.xml.readEXP306;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.xml.readEXP306.domain.Exp306;
import com.xml.readEXP306.domain.Exp306DutyHead;
import com.xml.readEXP306.domain.Exp306DutyList;
import com.xml.readEXP306.domain.Exp306EntryList;

public class ReadExp306 {
	public static final String fileName = "CTRCV2013062110250439729040015.xml";

	public static void main(String[] args) {

		File file = new File(fileName);
		try {
			System.out.println(getMessageType(file));

			Exp306 exp306 = readExp306(file);

			System.out.println(exp306);

		} catch (DocumentException e) {
			e.printStackTrace();
		}

		// EXP306
		// 读取306回执
	}

	public static Exp306 readExp306(File xmlFile) throws DocumentException {
		Exp306 exp306 = new Exp306();

		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(xmlFile);
		Element root = document.getRootElement();

		String send_time = root.element("EnvelopInfo").element("send_time")
				.getText();
		Date sendTime = parseDate(send_time);

		Element EXP306Element = root.element("DataInfo").element("SignedData")
				.element("Data").element("EXP306");

		Exp306DutyHead head = getDutyHead(EXP306Element);
		head.setSendtime(sendTime);

		exp306.setHead(head);// 表头

		exp306.setDutyList(getDutyList(EXP306Element));// 税单列表

		exp306.setEntryList(getEntryList(EXP306Element));// 汇总纳税表体

		return exp306;
	}

	/**
	 * <pre>
	 * 读取 EntryList
	 * </pre>
	 * 
	 * @param EXP306Element
	 * @return
	 */
	public static List<Exp306EntryList> getEntryList(Element EXP306Element) {
		List<Exp306EntryList> list = new ArrayList<Exp306EntryList>();

		@SuppressWarnings("unchecked")
		Iterator<Element> iterator = EXP306Element.elementIterator("EntryList");

		Exp306EntryList entryList;
		for (; iterator.hasNext();) {
			Element element = iterator.next();

			entryList = new Exp306EntryList();

			entryList.setEntryid(element.elementText("EntryId"));
			entryList.setGno(element.elementText("GNo"));
			entryList.setCodets(element.elementText("CodeTS"));
			entryList.setGname(element.elementText("GName"));

			entryList.setRealduty(getBigDecimal(element, "RealDuty"));

			entryList.setRealduty(getBigDecimal(element, "RealDuty"));
			entryList.setRealtax(getBigDecimal(element, "RealTax"));
			entryList.setRealreg(getBigDecimal(element, "RealReg"));

			entryList.setRealanti(getBigDecimal(element, "RealAnti"));
			entryList.setRealrsv1(getBigDecimal(element, "RealRsv1"));
			entryList.setRealrsv2(getBigDecimal(element, "RealRsv2"));

			list.add(entryList);
		}

		return list;
	}

	private static BigDecimal getBigDecimal(Element e, String qname) {
		String str = e.elementText(qname);
		if (str != null && !"".equals(str)) {
			return new BigDecimal(str);
		}

		return null;
	}

	/**
	 * <pre>
	 * 读取 DutyFormList
	 * </pre>
	 * 
	 * @param EXP306Element
	 * @return
	 */
	public static List<Exp306DutyList> getDutyList(Element EXP306Element) {
		List<Exp306DutyList> list = new ArrayList<Exp306DutyList>();

		@SuppressWarnings("unchecked")
		Iterator<Element> iterator = EXP306Element
				.elementIterator("DutyFormList");

		Exp306DutyList dutyList;

		for (; iterator.hasNext();) {
			Element element = iterator.next();
			dutyList = new Exp306DutyList();

			dutyList.setTaxid(element.elementText("TaxID"));
			dutyList.setDelaymark(element.elementText("DelayMark"));
			dutyList.setDutyflag(element.elementText("DutyFlag"));
			dutyList.setDutyflagnote(element.elementText("DutyFlagNote"));
			dutyList.setTaxtype(element.elementText("TaxType"));

			String realTax = element.elementText("RealTax");
			if (realTax != null && !"".equals(realTax.trim())) {
				dutyList.setRealtax(new BigDecimal(realTax));
			}
			dutyList.setPlimit(element.elementText("PLimit"));

			String genDate = element.elementText("GenDate");

			dutyList.setGendate(parseDate2(genDate));

			list.add(dutyList);
		}

		return list;
	}

	/**
	 * <pre>
	 * 读取 DutyFormHead
	 * </pre>
	 * 
	 * @param EXP306Element
	 * @return
	 */
	public static Exp306DutyHead getDutyHead(Element EXP306Element) {
		Exp306DutyHead head = new Exp306DutyHead();
		head.setCusdept("SZX");
		Element ehead = EXP306Element.element("DutyFormHead");

		head.setOptype(ehead.elementText("OpType"));
		head.setTaxentryid(ehead.elementText("TaxEntryId"));
		head.setDutyformflag(ehead.elementText("DutyFormFlag"));
		head.setIeport(ehead.elementText("IEPort"));
		head.setDeclport(ehead.elementText("DeclPort"));
		head.setPayercode(ehead.elementText("PayerCode"));
		head.setPayername(ehead.elementText("PayerName"));
		head.setOwnercode(ehead.elementText("OwnerCode"));
		head.setOwnername(ehead.elementText("OwnerName"));
		head.setTradecode(ehead.elementText("TradeCode"));
		head.setTradename(ehead.elementText("TradeName"));
		head.setAgentcode(ehead.elementText("AgentCode"));
		head.setAgentname(ehead.elementText("AgentName"));
		String ddate = ehead.elementText("DDate");

		head.setDdate(parseDate2(ddate));

		// 接收时间
		head.setRecetime(new Date());

		return head;
	}

	// 2013-06-21T10:11:
	public static SimpleDateFormat sfd = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:");

	// 20130621101030
	public static SimpleDateFormat sfd2 = new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * <pre>
	 * 将日期字符串转换成日期
	 * yyyy-MM-dd'T'HH:mm:
	 * </pre>
	 * 
	 * @param date
	 * @return 如果异常将返回null
	 */
	public static Date parseDate(String date) {
		try {
			return sfd.parse(date);
		} catch (ParseException e) {
			return null;
		}

	}

	/**
	 * <pre>
	 * 将日期字符串转换成日期
	 * yyyyMMddHHmmss
	 * </pre>
	 * 
	 * @param date
	 * @return 如果异常将返回null
	 */
	public static Date parseDate2(String date) {
		try {
			return sfd2.parse(date);
		} catch (ParseException e) {
			return null;
		}

	}

	/**
	 * <pre>
	 * 获取xml文件的message_type
	 * </pre>
	 * 
	 * @param xmlFile
	 *            xml文件
	 * @return 返回 message_type
	 * @throws DocumentException
	 */
	public static String getMessageType(File xmlFile) throws DocumentException {
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(xmlFile);
		Element root = document.getRootElement();
		Element msgType = root.element("EnvelopInfo").element("message_type");

		return msgType.getText();
	}
}
