package com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity;

import java.awt.Color;
import java.awt.Window;

import org.dom4j.Element;

/**
 * 描述：去除空格的Entity<br>
 * 这是一个例子，由于一定的原因装饰模式没有用起来
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-1-17      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class TrimEntity extends AbstractEntity implements IEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7273934588143112738L;

	public static final String TYPE = "TrimEntity";

	public static final String TRIM_TYPE_LEFT = "left";
	public static final String TRIM_TYPE_RIGHT = "right";
	public static final String TRIM_TYPE_BOTH = "both";

	private IEntity entity;

	private String trimType = TrimEntity.TRIM_TYPE_BOTH;

	public String getTrimType() {
		return trimType;
	}

	public void setTrimType(String trimType) {
		if (trimType == null || "".equals(trimType)) {
			return;
		}
		if (trimType.equals(TrimEntity.TRIM_TYPE_LEFT)) {
			this.trimType = TrimEntity.TRIM_TYPE_LEFT;
		} else if (trimType.equals(TrimEntity.TRIM_TYPE_RIGHT)) {
			this.trimType = TrimEntity.TRIM_TYPE_RIGHT;
		} else if (trimType.equals(TrimEntity.TRIM_TYPE_BOTH)) {
			this.trimType = TrimEntity.TRIM_TYPE_BOTH;
		}

	}

	public TrimEntity(IEntity entity) {
		this.entity = entity;
	}

	@Override
	public String toXml() {
		StringBuilder sbxml = new StringBuilder("<Entity type=\"");
		sbxml.append(TrimEntity.TYPE);
		sbxml.append("\" TrimType=\"");
		sbxml.append(trimType);
		sbxml.append("\">");
		sbxml.append(entity.toXml());
		sbxml.append("</Entiyt>");
		return sbxml.toString();
	}

	@Override
	public void fromXml(Element xml) {

	}

	@Override
	public void setValue(Object obj) {
		entity.setValue(obj);

	}

	@Override
	public Object getValue() {
		Object obj = entity.getValue();
		if (obj == null) {
			return obj;
		}
		String strvar = obj.toString();
		if (trimType.equals(TrimEntity.TRIM_TYPE_LEFT)) {
			int len = strvar.length();
			int st = 0;
			char[] val = strvar.toCharArray();
			while ((st < len) && (val[st] <= ' ')) {
				st++;
			}
			return (st > 0) ? strvar.substring(st, len) : strvar;
		} else if (trimType.equals(TrimEntity.TRIM_TYPE_RIGHT)) {
			int len = strvar.length();
			char[] val = strvar.toCharArray();
			while ((len > 0) && (val[len - 1] <= ' ')) {
				len--;
			}
			return len < strvar.length() ? strvar.substring(0, len) : strvar;
		} else if (trimType.equals(TrimEntity.TRIM_TYPE_BOTH)) {
			return strvar.trim();
		}
		return obj;
	}

	@Override
	public String getKey() {
		return entity.getKey();
	}

	@Override
	public String getViewStr() {

		return entity.getViewStr();
	}

	@Override
	public String getType() {
		return TrimEntity.TYPE;
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
