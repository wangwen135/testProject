package com.xml.readEXP306.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：306报文
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
public class Exp306 {

	private Exp306DutyHead head;

	private List<Exp306DutyList> dutyList;

	private List<Exp306EntryList> entryList;

	/**
	 * 构造函数
	 */
	public Exp306() {
		dutyList = new ArrayList<Exp306DutyList>();
		entryList = new ArrayList<Exp306EntryList>();
	}

	public Exp306DutyHead getHead() {
		return head;
	}

	public void setHead(Exp306DutyHead head) {
		this.head = head;
	}

	public List<Exp306DutyList> getDutyList() {
		return dutyList;
	}

	public void setDutyList(List<Exp306DutyList> dutyList) {
		this.dutyList = dutyList;
	}

	/**
	 * <pre>
	 * 添加一个税单列表
	 * </pre>
	 * 
	 * @param duty
	 * @return
	 */
	public boolean addDutyList(Exp306DutyList duty) {
		return dutyList.add(duty);
	}

	public List<Exp306EntryList> getEntryList() {
		return entryList;
	}

	public void setEntryList(List<Exp306EntryList> entryList) {
		this.entryList = entryList;
	}

	/**
	 * <pre>
	 * 添加一个汇总纳税表体
	 * </pre>
	 * 
	 * @param entry
	 * @return
	 */
	public boolean addEntryList(Exp306EntryList entry) {
		return entryList.add(entry);
	}
}
