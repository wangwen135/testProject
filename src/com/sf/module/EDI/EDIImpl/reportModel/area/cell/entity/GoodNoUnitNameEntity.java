package com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity;

import java.awt.Color;
import java.awt.Window;
import java.util.List;

import org.dom4j.Element;


/**
 * 描述：商品编码对应第一计量单位名称Entity<br>
 * 获取商品编码对应的第一计量单位名称
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-10-16      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class GoodNoUnitNameEntity extends AbstractEntity implements IEntity {

	private static final long serialVersionUID = -7152097182551464727L;

	public static final String TYPE = "GoodNoUnitNameEntity";

	private String key = "hscode";// 商品编码

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
		String hscode = (String) obj;
		// 将商品编码 对应的 计量单位赋值给value
//		List<GoodsCode> list = ComboxUtil.$goodsCodeList;
//		for (GoodsCode goodsInfo : list) {
//			if (hscode.equals(goodsInfo.getProductcode())) {
//				this.value = goodsInfo.getUnitname();
//				return;
//			}
//		}
//		// 如果找不到则取前四位进行匹配
//		String hscodeSub = hscode.substring(0, 4);
//		String goodno;
//		for (GoodsCode goodsInfo : list) {
//			goodno = goodsInfo.getProductcode();
//			if (goodno == null)
//				continue;
//			if (goodno.startsWith(hscodeSub)) {
//				this.value = goodsInfo.getUnitname();
//				return;
//			}
//		}

	}

	@Override
	public String getViewStr() {
		return "第一计量单位名称";
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
