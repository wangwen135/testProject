package com.xml.readEXP306.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述：税单列表
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
public class Exp306DutyList {

	// 表头id
	private Long headid;
	// 税单序号
	private String taxid;
	// 滞纳标志
	private String delaymark;
	// 退补税标志
	private String dutyflag;
	// 退补税原因
	private String dutyflagnote;
	// 税费种类
	private String taxtype;
	// 税款金额
	private BigDecimal realtax;
	// 缴款期限
	private String plimit;
	// 税单生成时间
	private Date gendate;

	public Long getHeadid() {
		return headid;
	}

	public void setHeadid(Long headid) {
		this.headid = headid;
	}

	public String getTaxid() {
		return taxid;
	}

	public void setTaxid(String taxid) {
		this.taxid = taxid;
	}

	public String getDelaymark() {
		return delaymark;
	}

	public void setDelaymark(String delaymark) {
		this.delaymark = delaymark;
	}

	public String getDutyflag() {
		return dutyflag;
	}

	public void setDutyflag(String dutyflag) {
		this.dutyflag = dutyflag;
	}

	public String getDutyflagnote() {
		return dutyflagnote;
	}

	public void setDutyflagnote(String dutyflagnote) {
		this.dutyflagnote = dutyflagnote;
	}

	public String getTaxtype() {
		return taxtype;
	}

	public void setTaxtype(String taxtype) {
		this.taxtype = taxtype;
	}

	public BigDecimal getRealtax() {
		return realtax;
	}

	public void setRealtax(BigDecimal realtax) {
		this.realtax = realtax;
	}

	public String getPlimit() {
		return plimit;
	}

	public void setPlimit(String plimit) {
		this.plimit = plimit;
	}

	public Date getGendate() {
		return gendate;
	}

	public void setGendate(Date gendate) {
		this.gendate = gendate;
	}

}
