package com.sf.module.EDI.DIYAssociate.domain;

import java.util.Date;

/**
 * 描述：自定义模板关联
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-2-11      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class DIYAssociate {

	private static final long serialVersionUID = 8739584350936169004L;

	private String templetid; // 模板编号
	private String cusdeptcode;// 口岸
	private String ieflag; // 进出口标识
	private String btnname;// 按钮名称
	private String filetype; // 导出文件类型
	private String note; // 备注
	private String filename;// 文件名称xml
	private String filepostfix;// 文件后缀名
	private Date modifytime; // 修改时间
	private String modifyby; // 修改人
	private String modifydeptname; // 修改地区
	private Date modifygltime; // 修改格林时间

	private String gatetype; // 报关类型
	private String colsplit; // 文本文件列分隔符
	private String splitmultgoods;// 拆分多品名
	private String sheetname;// Excel工作表命名
	private String orderby;// 排序条件

	private String crlf;// 回车换行符号

	public DIYAssociate() {

	}

	public String getGatetype() {
		return gatetype;
	}

	public void setGatetype(String gatetype) {
		this.gatetype = gatetype;
	}

	public String getColsplit() {
		return colsplit;
	}

	public void setColsplit(String colsplit) {
		this.colsplit = colsplit;
	}

	public String getTempletid() {
		return templetid;
	}

	public void setTempletid(String templetid) {
		this.templetid = templetid;
	}

	public String getCusdeptcode() {
		return cusdeptcode;
	}

	public void setCusdeptcode(String cusdeptcode) {
		this.cusdeptcode = cusdeptcode;
	}

	public String getIeflag() {
		return ieflag;
	}

	public void setIeflag(String ieflag) {
		this.ieflag = ieflag;
	}

	public String getBtnname() {
		return btnname;
	}

	public void setBtnname(String btnname) {
		this.btnname = btnname;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepostfix() {
		return filepostfix;
	}

	public void setFilepostfix(String filepostfix) {
		this.filepostfix = filepostfix;
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

	public String getSplitmultgoods() {
		return splitmultgoods;
	}

	public void setSplitmultgoods(String splitmultgoods) {
		this.splitmultgoods = splitmultgoods;
	}

	public String getSheetname() {
		return sheetname;
	}

	public void setSheetname(String sheetname) {
		this.sheetname = sheetname;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public String getCrlf() {
		return crlf;
	}

	public void setCrlf(String crlf) {
		this.crlf = crlf;
	}

}
