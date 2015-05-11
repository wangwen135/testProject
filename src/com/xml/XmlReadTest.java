package com.xml;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlReadTest {

	public static final String fileName = "CTRCV2013062110250439729040015.xml";

	public static void main(String[] args) {
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(new File(fileName));
			Element root = document.getRootElement();
			System.out.println(root.getName());
			Element msgType = root.element("EnvelopInfo").element("message_type");
			System.out.println(msgType.getText());
			
		} catch (Exception e) {

		}
	}
}
