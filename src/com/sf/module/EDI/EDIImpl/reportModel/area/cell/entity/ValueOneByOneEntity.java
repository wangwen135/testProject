package com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity;

import java.awt.Color;
import java.awt.Window;
import java.math.BigDecimal;
import java.util.Map;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.ICell;

/**
 * 描述：拆分多品名数量单位为：‘价值,价值,价值’ 价值<br>
 * 如果是单品名则去价值字段<br>
 * 这是一个特殊的Entity，需要从数据中多个列里面取值
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-6-12      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class ValueOneByOneEntity extends AbstractEntity implements IEntity {

	private static final long serialVersionUID = -7312510419589702014L;

	private static Logger logger = LoggerFactory.getLogger(ValueOneByOneEntity.class);

	public static final String TYPE = "ValueOneByOneEntity";

	/**
	 * 取数据库中所有的字段
	 */
	private String key = KEY_ALL_FIELDS;

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
		if (obj == null || !(obj instanceof Map)) {
			this.value = ICell.NULL_VALUE;
			return;
		}
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) obj;
		Object quantity = map.get("quantity");
		Object cusvalue = map.get("cusvalue");
		if (quantity == null) {
			this.value = cusvalue;
			return;
		}
		// 处理小数后面的0
		if (cusvalue instanceof BigDecimal) {
			BigDecimal b = (BigDecimal) cusvalue;
			if (b.compareTo(new BigDecimal(0)) == 0) {
				cusvalue = "0";
			} else {
				b = b.stripTrailingZeros();
				cusvalue = b.toPlainString();
			}
		}

		String strvalue = quantity.toString();
		if (strvalue.contains(";")) {// 多品名
			StringBuffer strbuf = new StringBuffer();
			try {
				// 数量1,单位1,价值1,重量1;数量2,单位2,价值2,重量2
				String[] quantitys = strvalue.split(";");
				for (String oneQuantity : quantitys) {
					String[] arrayQuantity = oneQuantity.split(",", 4);
					if (strbuf.length() != 0) {
						strbuf.append(",");
					}
					strbuf.append(arrayQuantity[2]);
				}
				this.value = strbuf.toString();
			} catch (Exception e) {
				logger.error("处理多品名（价值,价值,价值Entity）失败！做单品名处理", e);
				this.value = cusvalue;
			}
		} else {
			this.value = cusvalue;
		}
	}

	@Override
	public String getViewStr() {
		return "价值X,X";
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
