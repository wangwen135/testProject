package com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity;

import java.awt.Color;
import java.awt.Window;

import org.dom4j.Element;

import com.sf.module.EDI.util.EDIUtils;

/**
 * 描述：单位Entity<br>
 * 单位取值只需第一个品名的单位
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-5-11      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class UnitEntity1 extends AbstractEntity implements IEntity {

	private static final long serialVersionUID = 5790049157737737626L;

	public static final String TYPE = "Unit2Entity";

	private String key = "quantity";

	private Object value;

	@Override
	public String toXml() {
		StringBuilder sbxml = new StringBuilder("<entity type=\"");
		sbxml.append(TYPE);
		sbxml.append("\"/>");
		return sbxml.toString();
	}

	@Override
	public void fromXml(Element xml) {

	}

	@Override
	public void setValue(Object obj) {
		if (obj == null || "".equals(obj)) {
			this.value = obj;
			return;
		}
		// 计算
		String strvalue = (String) obj;
		if (strvalue.contains(";")) {// 多品名

			// 数量1,单位1,价值1,重量1;数量2,单位2,价值2,重量2
			String[] quantitys = strvalue.split(";");
			String[] arrayQuantity = quantitys[0].split(",", 4);
			if (arrayQuantity.length < 4) {
				this.value = EDIUtils.substringBeforeDigit(EDIUtils
						.substringAfterDecimal(strvalue));// 非多品名，做单品名处理
			} else {
				this.value = arrayQuantity[1];
			}

		} else {
			this.value = EDIUtils.substringBeforeDigit(EDIUtils
					.substringAfterDecimal(strvalue));
		}
	}

	@Override
	public String getViewStr() {
		return "单位1";
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public boolean canEdit() {
		return false;
	}

	@Override
	public IEntity edit(Window window) {
		return this;
	}

	@Override
	public Color getColor() {
		return null;
	}

}
