package com.swing.edi.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Dom4jTest {
	public static void main(String[] args) {
		Document doc;
		try {
			doc = DocumentHelper.parseText("<body row=\"1\" column=\"1\"><cell a=\"a\">asdef</cell></body>");
			Element root = doc.getRootElement();
			System.out.println(root.attributeValue("row"));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main2(String[] args) {
		String xml = "";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("d:\\user\\313921\\我的文档\\aaa.xml")));
			xml = br.readLine();
			br.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Document doc = DocumentHelper.parseText(xml);
			Element root = doc.getRootElement();
			
			Element title= root.element("title");
			System.out.println(title.element("reportName").getText());
			
			
			for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
				Element e = (Element) iterator.next();
				System.out.println(e.getName());
				System.out.println(e.asXML());
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void test1() {

		try {
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(new File(
					"d:\\user\\313921\\我的文档\\aaa.xml"));
			System.out.println(document.asXML());
			Element root = document.getRootElement();
			for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
				Element e = (Element) iterator.next();
				System.out.println(e.asXML());
				String attribute = e.attributeValue("showInAnyFiles");
				if (attribute == null) {
					System.out.println("值为null");
				}
				System.out.println(e.attributeValue("showInAnyFiles"));

			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
