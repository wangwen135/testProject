package com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity;

import java.awt.Color;
import java.awt.Window;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sf.module.EDI.util.EDIUtils;



/**
 * 描述：人民币对美元汇率
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-6-14      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class ExchangeCNY2USDEntity extends AbstractEntity implements IEntity {
	private static Logger logger = LoggerFactory.getLogger(ExchangeCNY2USDEntity.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 841751194234729898L;

	public static final String TYPE = "ExchangeCNY2USDEntity";

	private String key = KEY_UNNEEDED_FIELD;

	private boolean search = false;
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
	}

	@Override
	public String getViewStr() {
		return "汇率 CNY-USD";
	}

	@Override
	public Object getValue() {
		// 如果一个模板中有多个汇率会提示多次的，要不要想办法解决？
		if (!search) {
			search = true;
			try {
				// 从数据库中取汇率
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				StringBuffer sql = new StringBuffer();
				sql.append("orgcurrency = '");
				sql.append("CNY");
				sql.append("' and tarcurrency = '");
				sql.append("USD");
				sql.append("' and _valid ='Y' and '");
				sql.append(sdf.format(new Date()));
				sql.append("' between efficientdatetime and invalidationdatetime");

				/*
				 * List<ExchangeSetting> exchangeList = RMIProxy.get(
				 * IExchangeSettingAction.class)
				 * .findByOrgcurrencyAndTarcurrency(sql.toString()); if
				 * (exchangeList != null && !exchangeList.isEmpty()) {
				 * ExchangeSetting exchangeSetting = exchangeList.get(0); value
				 * = exchangeSetting.getTarvalue();// 汇率 if (value != null)
				 * value = EDIUtils .removeZeroByBigDecimal((BigDecimal) value);
				 * }
				 */

//				List<Map<String, Object>> list = RMIProxy.get(
//						IExchangeSettingAction.class).findExchangeSettingBySql(
//						sql.toString());
//				if (list != null && !list.isEmpty()) {
//					Map<String, Object> m = list.get(0);
//					value = m.get("tarvalue");
//					if (value != null)
//						value = EDIUtils
//								.removeZeroByBigDecimal((BigDecimal) value);
//				}

			} catch (Exception e) {
				logger.error("取人民币对美元汇率失败！", e);
			}
		}
		// 如果没有值是否需要设置一个默认值，或者由用户手工填写
		if (value == null) {
			String input = JOptionPane
					.showInputDialog("没有查到人民币(CNY)对美元(USD)有效汇率\r\n请手工输入一个汇率：");
			value = (input == null ? "" : input);// 只提示一次吧！
		}
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
