package com.sf.module.EDI.DIYTemplet.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：DIY报表模板
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-2-8      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class DIYTemplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1101429624267509037L;
	private String tname; // 模板名称
	private String templet; // 模板内容
	private String note; // 备注
	private Date modifytime; // 修改时间
	private String modifyby; // 修改人
	private String modifydeptname; // 修改地区
	private Date modifygltime; // 修改格林时间
	private String createby;// 创建人

	public DIYTemplet() {

	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTemplet() {
		return templet;
	}

	public void setTemplet(String templet) {
		this.templet = templet;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	public String getModifyby() {
		return modifyby;
	}

	public void setModifyby(String modifyby) {
		this.modifyby = modifyby;
	}

	public String getModifydeptname() {
		return modifydeptname;
	}

	public void setModifydeptname(String modifydeptname) {
		this.modifydeptname = modifydeptname;
	}

	public Date getModifygltime() {
		return modifygltime;
	}

	public void setModifygltime(Date modifygltime) {
		this.modifygltime = modifygltime;
	}

	public String getCreateby() {
		return createby;
	}

	public void setCreateby(String createby) {
		this.createby = createby;
	}

}