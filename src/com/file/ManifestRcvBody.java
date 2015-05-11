package com.file;

import java.util.Date;

/**
 * 描述：舱单返回回执---数据体
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
public class ManifestRcvBody {

	/**
	 * 总运单号
	 */
	private String voyageNo;

	/**
	 * 分运单号
	 */
	private String billNo;

	/**
	 * 入库时间<br>
	 * yyyyMMddHHmm
	 */
	private Date entryDate;

	/**
	 * <pre>
	 * 回执结果
	 * NN：入库失败
	 * NY：入库成功
	 * MN：追加失败
	 * MY：追加成功
	 * UN:修改失败
	 * UY:修改成功
	 * 
	 * </pre>
	 */
	private String rtnFlag;

	/**
	 * 备注
	 */
	private String notes;

	public String getVoyageNo() {
		return voyageNo;
	}

	public void setVoyageNo(String voyageNo) {
		this.voyageNo = voyageNo;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public String getRtnFlag() {
		return rtnFlag;
	}

	public void setRtnFlag(String rtnFlag) {
		this.rtnFlag = rtnFlag;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}
