package com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity;

import java.awt.Color;
import java.awt.Window;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sf.module.EDI.util.EDIUtils;

/**
 * 描述：拆分多品名数量单位为：‘数量，数量，数量’ 格式
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
public class QuantityOneByOneEntity extends AbstractEntity implements IEntity {

	private static Logger logger = LoggerFactory.getLogger(QuantityOneByOneEntity.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 4594519292896949199L;

	public static final String TYPE = "QuantityOneByOneEntity";

	/**
	 * 数据库中的字段
	 */
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

		String strvalue = (String) obj;
		if (strvalue.contains(";")) {// 多品名
			try {
				StringBuffer strbuf = new StringBuffer();

				// 数量1,单位1,价值1,重量1;数量2,单位2,价值2,重量2
				String[] quantitys = strvalue.split(";");
				for (String quantity : quantitys) {
					String[] arrayQuantity = quantity.split(",", 4);
					if (strbuf.length() != 0) {
						strbuf.append(",");
					}
					strbuf.append(arrayQuantity[0]);
				}
				this.value = strbuf.toString();
			} catch (Exception e) {
				logger.error("处理多品名（数量,数量,数量Entity）失败！做单品名处理", e);
				this.value = EDIUtils.subStringDecimal(strvalue);
			}
		} else {
			this.value = EDIUtils.subStringDecimal(strvalue);
		}
	}

	@Override
	public String getViewStr() {
		return "数量X,X";
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
