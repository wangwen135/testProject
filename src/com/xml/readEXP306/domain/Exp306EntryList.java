package com.xml.readEXP306.domain;

import java.math.BigDecimal;

/**
 * 描述：汇总纳税表体
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
public class Exp306EntryList {

	// 表头id
	private Long headid;
	// 报关单海关编号
	private String entryid;
	// 商品序号
	private String gno;
	// 商品编号
	private String codets;
	// 商品名称
	private String gname;
	// 实征关税额
	private BigDecimal realduty;
	// 实征增值税额
	private BigDecimal realtax;
	// 实征消费税额
	private BigDecimal realreg;
	// 实征反倾销税额
	private BigDecimal realanti;
	// 实征反补贴税款
	private BigDecimal realrsv1;
	// 实征废旧基金
	private BigDecimal realrsv2;

	public Long getHeadid() {
		return headid;
	}

	public void setHeadid(Long headid) {
		this.headid = headid;
	}

	public String getEntryid() {
		return entryid;
	}

	public void setEntryid(String entryid) {
		this.entryid = entryid;
	}

	public String getGno() {
		return gno;
	}

	public void setGno(String gno) {
		this.gno = gno;
	}

	public String getCodets() {
		return codets;
	}

	public void setCodets(String codets) {
		this.codets = codets;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public BigDecimal getRealduty() {
		return realduty;
	}

	public void setRealduty(BigDecimal realduty) {
		this.realduty = realduty;
	}

	public BigDecimal getRealtax() {
		return realtax;
	}

	public void setRealtax(BigDecimal realtax) {
		this.realtax = realtax;
	}

	public BigDecimal getRealreg() {
		return realreg;
	}

	public void setRealreg(BigDecimal realreg) {
		this.realreg = realreg;
	}

	public BigDecimal getRealanti() {
		return realanti;
	}

	public void setRealanti(BigDecimal realanti) {
		this.realanti = realanti;
	}

	public BigDecimal getRealrsv1() {
		return realrsv1;
	}

	public void setRealrsv1(BigDecimal realrsv1) {
		this.realrsv1 = realrsv1;
	}

	public BigDecimal getRealrsv2() {
		return realrsv2;
	}

	public void setRealrsv2(BigDecimal realrsv2) {
		this.realrsv2 = realrsv2;
	}

	public String getMainbno() {
		return mainbno;
	}

	public void setMainbno(String mainbno) {
		this.mainbno = mainbno;
	}

	public String getBno() {
		return bno;
	}

	public void setBno(String bno) {
		this.bno = bno;
	}

	// 主运单
	private String mainbno;
	// 分运单
	private String bno;

}
