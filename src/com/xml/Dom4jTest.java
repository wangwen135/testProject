package com.xml;

import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class Dom4jTest {
	public static void main(String[] args) throws Exception {
		Document d = createDocument();
		String xml = d.asXML();
		write(d);

		System.out.println("\r\n------输出------\r\n");

		Document document = DocumentHelper.parseText(xml);

		Element e = document.getRootElement().element("author");
		System.out.println(e.attributeValue("name"));

		System.out.println(e.getText());

	}

	public static Document createDocument() {
		Document document = DocumentHelper.createDocument();

		Element root = document.addElement("root");
		
		Element nbsp = root.addElement("nbspTest");
		nbsp.addCDATA("asdfa     afdasdf   @!@#$%<>   asdf");
		
		Element author1 = root.addElement("author")
				.addAttribute("name", "Jam\t|\n|\tes")
				.addAttribute("location", "UK").addText("<James\t|\n|\tStrachan");
		
		

		author1.addElement("list").addAttribute("aaa", "a1a")
				.addAttribute("bbb", "b1b");
		author1.addElement("list2").addAttribute("aaa", "a1a")
		.addAttribute("bbb", "b1b");

		Element author2 = root.addElement("author").addAttribute("name", "Bob")
				.addAttribute("location", "US").addText("Bob McWhirter");
		return document;
	}

	public static void write(Document document) throws IOException {
		System.out.println("无格式");
		System.out.println(document.asXML());
		// 指定文件
		System.out.println("指定文件");
		XMLWriter writer = new XMLWriter(new FileWriter("output.xml"));
		writer.write(document);
		writer.flush();
		writer.close();
		// 美化格式
		System.out.println("美化格式");
		OutputFormat format = OutputFormat.createPrettyPrint();
		writer = new XMLWriter(System.out, format);
		writer.write(document);
		writer.flush();
		// 缩减格式
		System.out.println("缩减格式");
		format = OutputFormat.createCompactFormat();
		writer = new XMLWriter(System.out, format);
		writer.write(document);
		writer.flush();
	}
}
