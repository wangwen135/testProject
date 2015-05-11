package com.swing.edi.reportModel.area.cell.entity;

import java.io.Serializable;

import org.dom4j.Element;

public interface IEntity extends Serializable {
	public static final String KEY_EDI_CONSTANT = "EDI_CONSTANT";

	// 使用装饰模式设计，具体的包装操作放到不同的实现中，可以相互包装
	// 设计的重点

	// 常量 变量 都使用它定义

	public String toXml();

	public void fromXml(Element xml);

	public void setValue(Object obj);

	public String getViewStr();

	public Object getValue();

	public String getKey();

	public String getType();
}
