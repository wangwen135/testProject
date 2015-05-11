package com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity;

import java.awt.Color;
import java.awt.Window;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.FormatUtils;
import com.sf.module.EDI.util.EDIUtils;

/**
 * 描述：数量Entity
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-3-30      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class QuantityEntity extends AbstractEntity implements IEntity {

	private static Logger logger = LoggerFactory.getLogger(QuantityEntity.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -2756594396486699050L;

	public static final String TYPE = "QuantityEntity";

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
	public void setValue(Object obj) {// 如果出现异常会怎么样
		if (obj == null || "".equals(obj)) {
			this.value = obj;
			return;
		}
		// 计算
		// 数量不会有小数，如果有小数则会出现截取错误
		String strvalue = (String) obj;
		// 如果计算过程中出错了就按照单品名处理
		if (strvalue.contains(";")) {// 多品名
			try {
				double tmpValue = 0;
				// 数量1,单位1,价值1,重量1;数量2,单位2,价值2,重量2
				String[] quantitys = strvalue.split(";");
				for (String quantity : quantitys) {
					String[] arrayQuantity = quantity.split(",", 4);
					String q = arrayQuantity[0];
					if (q != null && !"".equals(q)) {
						tmpValue += Double.valueOf(q);
					}
				}
				this.value = FormatUtils.formatDouble(tmpValue);
			} catch (Exception e) {
				logger.error("处理多品名数量Entity失败！做单品名处理", e);
				this.value = EDIUtils.subStringDecimal(strvalue);
			}
		} else {
			this.value = EDIUtils.subStringDecimal(strvalue);
		}

	}

	@Override
	public String getViewStr() {
		return "数量";
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
