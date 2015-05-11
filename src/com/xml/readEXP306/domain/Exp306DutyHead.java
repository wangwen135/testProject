package com.xml.readEXP306.domain;

import java.util.Date;

/**
 * 描述：汇总纳税表头
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2013-7-3      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class Exp306DutyHead {

	// 口岸
	private String cusdept;
	// 操作类型
	private String optype;
	// 税单海关编号
	private String taxentryid;
	// 税单标志
	private String dutyformflag;
	// 进出口岸代码
	private String ieport;
	// 申报口岸代码
	private String declport;
	// 缴款单位编码
	private String payercode;
	// 缴款单位名称
	private String payername;
	// 货主单位代码
	private String ownercode;
	// 货主单位名称
	private String ownername;
	// 经营单位代码
	private String tradecode;
	// 经营单位名称
	private String tradename;
	// 申报单位代码
	private String agentcode;
	// 申报单位名称
	private String agentname;
	// 申报日期
	private Date ddate;
	// 发送时间
	private Date sendtime;
	// 接收时间
	private Date recetime;

	public String getCusdept() {
		return cusdept;
	}

	public void setCusdept(String cusdept) {
		this.cusdept = cusdept;
	}

	public String getOptype() {
		return optype;
	}

	public void setOptype(String optype) {
		this.optype = optype;
	}

	public String getTaxentryid() {
		return taxentryid;
	}

	public void setTaxentryid(String taxentryid) {
		this.taxentryid = taxentryid;
	}

	public String getDutyformflag() {
		return dutyformflag;
	}

	public void setDutyformflag(String dutyformflag) {
		this.dutyformflag = dutyformflag;
	}

	public String getIeport() {
		return ieport;
	}

	public void setIeport(String ieport) {
		this.ieport = ieport;
	}

	public String getDeclport() {
		return declport;
	}

	public void setDeclport(String declport) {
		this.declport = declport;
	}

	public String getPayercode() {
		return payercode;
	}

	public void setPayercode(String payercode) {
		this.payercode = payercode;
	}

	public String getPayername() {
		return payername;
	}

	public void setPayername(String payername) {
		this.payername = payername;
	}

	public String getOwnercode() {
		return ownercode;
	}

	public void setOwnercode(String ownercode) {
		this.ownercode = ownercode;
	}

	public String getOwnername() {
		return ownername;
	}

	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}

	public String getTradecode() {
		return tradecode;
	}

	public void setTradecode(String tradecode) {
		this.tradecode = tradecode;
	}

	public String getTradename() {
		return tradename;
	}

	public void setTradename(String tradename) {
		this.tradename = tradename;
	}

	public String getAgentcode() {
		return agentcode;
	}

	public void setAgentcode(String agentcode) {
		this.agentcode = agentcode;
	}

	public String getAgentname() {
		return agentname;
	}

	public void setAgentname(String agentname) {
		this.agentname = agentname;
	}

	public Date getDdate() {
		return ddate;
	}

	public void setDdate(Date ddate) {
		this.ddate = ddate;
	}

	public Date getSendtime() {
		return sendtime;
	}

	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}

	public Date getRecetime() {
		return recetime;
	}

	public void setRecetime(Date recetime) {
		this.recetime = recetime;
	}

}
