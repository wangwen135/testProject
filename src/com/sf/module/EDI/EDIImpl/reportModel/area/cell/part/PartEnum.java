package com.sf.module.EDI.EDIImpl.reportModel.area.cell.part;

/**
 * 零部件枚举
 */
public enum PartEnum {

	add, // 加
	subtract, // 减去
	multiply, // 乘
	divide, // 除
	remainder, // 取余
	none// 不做操作，为字符串拼接
	// ,LF
	, CRLF // windows 下的回车换行
	// ,CR
	;
	public IPart getPart() {
		return BasePart.getPart(this);
	}
}
