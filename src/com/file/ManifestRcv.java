package com.file;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述： 舱单返回回执
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
public class ManifestRcv {

	/**
	 * 根据文件名确定的类型<br>
	 * CTRCV = 数据中心接收回执<br>
	 * PTRCV = 海关接收回执
	 */
	private String type;

	private ManifestRcvHead head;

	private List<ManifestRcvBody> bodyList;

	public ManifestRcv() {
		bodyList = new ArrayList<ManifestRcvBody>();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ManifestRcvHead getHead() {
		return head;
	}

	public void setHead(ManifestRcvHead head) {
		this.head = head;
	}

	public List<ManifestRcvBody> getBodyList() {
		return bodyList;
	}

	public void setBodyList(List<ManifestRcvBody> bodyList) {
		this.bodyList = bodyList;
	}

	public boolean hasBody() {
		return !bodyList.isEmpty();
	}

	public void addBody(ManifestRcvBody body) {
		bodyList.add(body);
	}
}
