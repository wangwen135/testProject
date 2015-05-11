package com.swing.edi.reportModel;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.swing.edi.reportModel.area.BodyArea;
import com.swing.edi.reportModel.area.HeadArea;
import com.swing.edi.reportModel.area.TailArea;
import com.swing.edi.reportModel.area.TitleArea;

public class ReportModel implements IReportModel {

	// 四个不同的区域

	TitleArea title;
	HeadArea head;
	BodyArea body;
	TailArea tail;

	/**
	 * 构造函数<br>
	 * 构造一个空的模型，一般需要从XML中构建
	 */
	public ReportModel() {

	}

	/**
	 * 构造函数<br>
	 * 需要提供不同Area对象
	 * 
	 * @param title
	 * @param head
	 * @param body
	 * @param tail
	 */
	public ReportModel(TitleArea title, HeadArea head, BodyArea body,
			TailArea tail) {
		this.title = title;
		this.head = head;
		this.body = body;
		this.tail = tail;
	}

	@Override
	public String toXml() {
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

	@Override
	public void fromXml(String xml) throws Exception {
		// 解析xml文件
		Document doc = DocumentHelper.parseText(xml);
		Element root = doc.getRootElement();
		//标题
		Element title = root.element("title");
		this.title = new TitleArea();
		this.title.fromXml(title);
		//头部
		Element head = root.element("head");
		this.head = new HeadArea();
		this.head.fromXml(head);
		
		Element body = root.element("body");
		this.body = new BodyArea();
		this.body.fromXml(body);
		
		Element tail = root.element("tail");
		this.tail = new TailArea();
		this.tail.fromXml(tail);
		
	}

	@Override
	public TitleArea getTitle() {
		return this.title;
	}

	@Override
	public HeadArea getHead() {
		return this.head;
	}

	@Override
	public BodyArea getBody() {
		return this.body;
	}

	@Override
	public TailArea getTail() {
		return this.tail;
	}

	public void setTitle(TitleArea title) {
		this.title = title;
	}

	public void setHead(HeadArea head) {
		this.head = head;
	}

	public void setBody(BodyArea body) {
		this.body = body;
	}

	public void setTail(TailArea tail) {
		this.tail = tail;
	}

}
