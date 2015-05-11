package com.sf.module.EDI.EDIImpl.reportModel;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.sf.module.EDI.EDIImpl.reportModel.area.BodyArea;
import com.sf.module.EDI.EDIImpl.reportModel.area.HeadArea;
import com.sf.module.EDI.EDIImpl.reportModel.area.TailArea;
import com.sf.module.EDI.EDIImpl.reportModel.area.TitleArea;

/**
 * 描述：报表模型<br>
 * {@link IReportModel}
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
public class ReportModel implements IReportModel {

	private static final long serialVersionUID = -6701040992715401233L;

	// 四个不同的区域
	private TitleArea title;
	private HeadArea head;
	private BodyArea body;
	private TailArea tail;

	// 全局上下文
	private Map<String, Object> globalContext = new HashMap<String, Object>();

	/**
	 * 实例
	 */
	private static ReportModel instance;

	/**
	 * <pre>
	 * 获取一个静态的实例
	 * </pre>
	 * 
	 * @return
	 */
	public synchronized static ReportModel getStaticInstance() {
		if (instance == null) {
			instance = new ReportModel();
		}
		return instance;
	}

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
		// 标题
		Element title = root.element("title");
		this.title = new TitleArea();
		this.title.fromXml(title);
		// 头部
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
	public int getMaxColumnCount() {
		int maxColumnCount = 0;
		if (head != null) {
			maxColumnCount = Math.max(maxColumnCount, head.getColumnCount());
		}
		if (body != null) {
			maxColumnCount = Math.max(maxColumnCount, body.getColumnCount());
		}
		if (tail != null) {
			maxColumnCount = Math.max(maxColumnCount, tail.getColumnCount());
		}
		return maxColumnCount;
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

	@Override
	public Map<String, Object> getGlobalContext() {
		return globalContext;
	}

	@Override
	public Object getContext(String key) {
		return globalContext.get(key);
	}

	@Override
	public void putContext(String key, Object value) {
		globalContext.put(key, value);
	}

	@Override
	public void rebuildChildContext() {
		title.setReportModel(this);
		title.rebuildChildContext();
		head.setReportModel(this);
		head.rebuildChildContext();
		body.setReportModel(this);
		body.rebuildChildContext();
		tail.setReportModel(this);
		tail.rebuildChildContext();

	}

}
