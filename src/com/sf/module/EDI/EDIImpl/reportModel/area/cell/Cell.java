package com.sf.module.EDI.EDIImpl.reportModel.area.cell;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity.EntityUtil;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity.IEntity;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.Format;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.IFormat;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.function.FunctionUtil;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.function.GroovyFunction;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.function.IFunction;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.part.BasePart;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.part.IPart;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.part.PartEnum;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.property.CellProperty;
import com.sf.module.EDI.EDIImpl.reportModel.utils.ReportModelUtils;
//import com.sf.module.EDI.EDIReport.biz.action.EDIAbstractAction;

/**
 * 描述：单元格实现。终态类。表示一个单元格
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-2-3      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public final class Cell extends AbstractCell implements ICell {
	/**
	 * 
	 */
	private static final long serialVersionUID = -854751412556569844L;

	/**
	 * 列位置
	 */
	private int column = -1;

	/**
	 * 行位置
	 */
	private int row = -1;
	// int columnSpace;
	// int rowSpace;
	// margin

	/**
	 * 实体对象列表
	 */
	private ArrayList<IEntity> entityList = new ArrayList<IEntity>();

	/**
	 * 零部件列表
	 */
	private ArrayList<IPart> partList = new ArrayList<IPart>();

	/**
	 * 函数列表
	 */
	private ArrayList<IFunction> functionList = new ArrayList<IFunction>();

	/**
	 * 是否显示值
	 */
	private boolean showValue = true;

	/**
	 * 格式
	 */
	private IFormat format;

	/**
	 * 属性
	 */
	private CellProperty property;

	/**
	 * 单元格计算之后的值
	 */
	private Object cellValue;

	@Override
	public Object getValue() {

		// 取值，需要根据part进行计算
		if (entityList.size() <= 0) {
			return NULL_VALUE;
		}
		// 取第一个的值
		Object obj = entityList.get(0).getValue();
		if (obj == null) {
			obj = NULL_VALUE;
		}

		// 顺序运算，无优先级
		for (int i = 0; i < partList.size() - 1; i++) {
			IPart part = partList.get(i);
			IEntity entity = entityList.get(i + 1);
			Object entityValue = entity.getValue();
			if (entityValue == null) {
				entityValue = NULL_VALUE;
			}
			obj = part.operation(obj, entityValue);
		}
		if (obj == null) {
			obj = NULL_VALUE;
		}
		return obj;
	}

	@Override
	public String getViewStr() {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		if (getFunctionSize() > 0) {
			sb.append("<FONT color=red>");
			for (int i = functionList.size() - 1; i >= 0; i--) {
				sb.append(functionList.get(i).getType());
				sb.append("(");
			}
			// &nbsp;
			sb.append("</FONT>");
		}

		// sb.append("<center>");

		for (int i = 0; i < entityList.size(); i++) {
			IEntity entity = entityList.get(i);

			if (entity.getColor() != null) {
				// 设置了颜色的Entity
				sb.append("<font color=\"");
				sb.append(ReportModelUtils.colorToHtmlColor(entity.getColor()));
				sb.append("\">");
				sb.append(entity.getViewStr());
				sb.append("</font>");
			} else {
				// sb.append("<font color=blue>");
				sb.append(entity.getViewStr());
				// sb.append("</font>");
			}
			if (i != entityList.size() - 1) {
				// 8CC6FF
				// 008000
				// &nbsp;
				sb.append("<font style=\"background-color: #8CC6FF\"><b>[");
				// sb.append(partList.get(i).getViewStr());
				sb.append(partList.get(i).getSymbol());
				sb.append("]</b></font>");
			}
		}

		// sb.append("</center>");

		if (getFunctionSize() > 0) {
			sb.append("<FONT color=red>");
			for (int i = 0; i < getFunctionSize(); i++) {
				sb.append(")");// // 添加右括号
			}
			// &nbsp;
			sb.append("</FONT>");
		}

		sb.append("</html>");
		return sb.toString();
	}

	@Override
	public String[] getEntityKeys() {
		IEntity[] entityArray = getEntitys();
		String[] keyArray = new String[entityArray.length];
		for (int i = 0; i < entityArray.length; i++) {
			keyArray[i] = entityArray[i].getKey();
		}
		return keyArray;
	}

	@Override
	public List<IEntity> getEntityList() {
		return entityList;
	}

	@Override
	public IEntity[] getEntitys() {
		return entityList.toArray(new IEntity[0]);
	}

	@Override
	public List<IFunction> getFunctionList() {
		return functionList;
	}

	@Override
	public IFunction[] getFunctions() {
		return functionList.toArray(new IFunction[0]);
	}

	@Override
	public IEntity findEntityByKey(String key) {
		IEntity entity;
		for (int i = 0; i < entityList.size(); i++) {
			entity = entityList.get(i);
			if (key.equals(entity.getKey())) {
				return entity;
			}
		}
		return null;
	}

	@Override
	public IEntity getEntity(int index) {
		return entityList.get(index);
	}

	@Override
	public IFunction getFunction(int index) {
		return functionList.get(index);
	}

	@Override
	public List<IPart> getPartList() {
		return partList;
	}

	@Override
	public IPart getPart(int index) {
		return partList.get(index);
	}

	@Override
	public void setValue(String key, Object value) {
		IEntity entity = findEntityByKey(key);
		if (entity != null) {
			entity.setValue(value);
		}

	}

	@Override
	public void setValue(int index, Object value) {
		getEntity(index).setValue(value);
	}

	@Override
	public String toXml() {
		StringBuilder sb = new StringBuilder();
		sb.append("<cell column=\"");
		sb.append(this.getColumn());
		sb.append("\" row=\"");
		sb.append(this.getRow());
		sb.append("\">");

		// 函数
		for (IFunction func : functionList) {
			sb.append(func.toXml());
		}
		// 格式
		if (getFormat() != null) {
			sb.append(getFormat().toXml());
		}
		// 属性
		if (getProperty() != null) {
			sb.append(getProperty().toXml());
		}
		// 内容和运算符
		for (int i = 0; i < entityList.size(); i++) {
			sb.append(entityList.get(i).toXml());
			if (i != entityList.size() - 1) {
				sb.append(partList.get(i).toXml());
			}
		}
		sb.append("</cell>");

		return sb.toString();
	}

	@Override
	public void fromXml(Element xml) {
		int column = Integer.valueOf(xml.attributeValue("column"));
		int row = Integer.valueOf(xml.attributeValue("row"));
		setColumn(column);
		setRow(row);

		for (Iterator<?> iterator = xml.elementIterator(); iterator.hasNext();) {
			Element e = (Element) iterator.next();
			if (IFunction.ELEMENT_NAME.equals(e.getName())) {
				IFunction functionByElement = FunctionUtil
						.getFunctionByElement(e);
				addFunction(functionByElement);
			} else if (CellProperty.ELEMENT_NAME.equals(e.getName())) {
				CellProperty p = new CellProperty();
				p.fromXml(e);
				setProperty(p);
			} else if (IFormat.ELEMENT_NAME.equals(e.getName())) {
				IFormat format = new Format();
				format.fromXml(e);
				setFormat(format);
			} else if (IEntity.ELEMENT_NAME.equals(e.getName())) {
				IEntity entityByElement = EntityUtil.getEntityByElement(e);
				entityList.add(entityByElement);
			} else if (IPart.ELEMENT_NAME.equals(e.getName())) {
				// part
				String partName = e.attributeValue("name");
				partList.add(BasePart.getPart(PartEnum.valueOf(partName)));
			}
		}
		// 再添加一个part
		partList.add(BasePart.getPart(PartEnum.none));
	}

	@Override
	public boolean isEmpty() {
		return entityList.isEmpty();
	}

	@Override
	public int getEntitySize() {
		return entityList.size();
	}

	@Override
	public int getFunctionSize() {
		return functionList.size();
	}

	@Override
	public boolean addEntity(IEntity e) {
		if (e == null) {
			throw new NullPointerException("要添加的 Entity 不能为空！");
		}
		return entityList.add(e);
	}

	@Override
	public boolean addFunction(IFunction f) {
		if (f == null) {
			throw new NullPointerException("要添加的 Function 不能为空！");
		}
		return functionList.add(f);
	}

	@Override
	public boolean addPart(IPart p) {
		if (p == null) {
			throw new NullPointerException("要添加的 Part 不能为空！");
		}
		return partList.add(p);
	}

	@Override
	public boolean addEntityAndPart(IEntity e) {
		if (e == null) {
			throw new NullPointerException("要添加的 Entity 不能为空！");
		}
		entityList.add(e);
		partList.add(BasePart.getPart(PartEnum.none));
		return true;
	}

	@Override
	public boolean addEntityAndPart(int index, IEntity e) {
		if (index < 0) {
			throw new IllegalArgumentException("索引位置不能小于0");
		}
		if (e == null) {
			throw new NullPointerException("要添加的 Entity 不能为空！");
		}
		entityList.add(index, e);
		partList.add(index, BasePart.getPart(PartEnum.none));
		return true;
	}

	@Override
	public boolean addFunction(int index, IFunction f) {
		if (index < 0) {
			throw new IllegalArgumentException("索引位置不能小于0");
		}
		if (f == null) {
			throw new NullPointerException("要添加的 Function 不能为空！");
		}
		functionList.add(index, f);
		return true;
	}

	@Override
	public IEntity setEntity(int index, IEntity e) {
		if (index < 0) {
			throw new IllegalArgumentException("索引位置不能小于0");
		}
		if (e == null) {
			throw new NullPointerException("要添加的 Entity 不能为空！");
		}
		return entityList.set(index, e);
	}

	@Override
	public IFunction setFunction(int index, IFunction func) {
		if (index < 0) {
			throw new IllegalArgumentException("索引位置不能小于0");
		}
		if (func == null) {
			throw new NullPointerException("要添加的 Function 不能为空！");
		}
		return functionList.set(index, func);
	}

	@Override
	public IPart setPart(int index, IPart p) {
		if (index < 0) {
			throw new IllegalArgumentException("索引位置不能小于0");
		}
		if (p == null) {
			throw new NullPointerException("要添加的 part 不能为空！");
		}
		return partList.set(index, p);
	}

	@Override
	public boolean removeEntityAndPart(IEntity e) {
		int index = entityList.indexOf(e);
		if (index < 0) {
			return false;
		}
		entityList.remove(index);
		partList.remove(index);
		return true;
	}

	@Override
	public boolean removeFunction(IFunction f) {
		return functionList.remove(f);
	}

	@Override
	public IEntity removeEntityAndPart(int index) {
		if (index < 0) {
			throw new IllegalArgumentException("index 不能小于0");
		}
		IEntity tmp = entityList.remove(index);
		partList.remove(index);
		return tmp;
	}

	@Override
	public IFunction removeFunction(int index) {
		return functionList.remove(index);
	}

	@Override
	public void clear() {
		entityList.clear();
		partList.clear();
	}

	@Override
	public void clearFunction() {
		functionList.clear();
	}

	@Override
	public int getColumn() {
		return this.column;
	}

	@Override
	public void setColumn(int column) {
		if (column < 0) {
			throw new IllegalArgumentException("列数必须大于0");
		}
		this.column = column;
	}

	@Override
	public int getRow() {
		return this.row;
	}

	@Override
	public void setRow(int row) {
		if (row < 0) {
			throw new IllegalArgumentException("行数必须大于0");
		}
		this.row = row;
	}

	@Override
	public void setValue(Map<String, Object> data) {
		// 如果属性中设置了多品名时不显示单元格内容则不需要设置值
		if (getProperty() != null) {
			if (!getProperty().isShowOnMultGoods()) {
				// 如果数据中有多品名标记
//				if (data.containsKey(EDIAbstractAction.SPLITMULTGOODSFLAG_KEY)) {
//					if (EDIAbstractAction.SPLITMULTGOODSFLAG_VALUE_NULTGOODS2
//							.equals(data
//									.get(EDIAbstractAction.SPLITMULTGOODSFLAG_KEY))) {
//						showValue = false;
//						cellValue = NULL_VALUE;
//						return;
//					}
//				}
			}
		}
		showValue = true;

		for (Iterator<IEntity> iterator = entityList.iterator(); iterator
				.hasNext();) {
			IEntity entity = iterator.next();
			if (entity == null
					|| IEntity.KEY_EDI_CONSTANT.equals(entity.getKey())
					|| IEntity.KEY_UNNEEDED_FIELD.equals(entity.getKey())) {
				// 为空，为常量，为无需字段的Entity，跳过
				continue;
			}

			if (IEntity.KEY_ALL_FIELDS.equals(entity.getKey())) {
				// 由于特殊的数量和价值需要从多个字段中取值，故将整条数据传过去
				entity.setValue(data);
			} else {
				Object obj = data.get(entity.getKey());
				if (obj instanceof BigDecimal) {
					BigDecimal b = (BigDecimal) obj;
					if (b.compareTo(new BigDecimal(0)) == 0) {
						entity.setValue("0");
					} else {
						b = b.stripTrailingZeros();
						entity.setValue(b.toPlainString());
					}
				} else {
					entity.setValue(obj);
				}
			}
		}
		cellValue = getValue();
		for (IFunction func : functionList) {
			// GroovyFunction需要整条数据
			if (GroovyFunction.TYPE.equals(func.getType())) {
				GroovyFunction gf = (GroovyFunction) func;
				gf.setRowMap(data);
			}
			func.setValue(cellValue);
			cellValue = func.getValue();
		}
	}

	@Override
	public Object getResult() {
		return cellValue;
	}

	@Override
	public IPart findPart(PartEnum part) {
		if (part == null)
			return null;
		for (IPart p : partList) {
			if (p.getEnum() == part) {
				return p;
			}
		}
		return null;
	}

	@Override
	public String getFormatResult() {
		if (!showValue) {
			return NULL_VALUE;
		}
		Object o = getResult();
		if (o == null) {
			o = NULL_VALUE;
		}

		if (getFormat() != null) {
			return getFormat().format(o);
		} else {
			if (o instanceof BigDecimal) {
				// 格式化不带指数
				return ((BigDecimal) o).toPlainString();
			}
			return o.toString();
		}
	}

	@Override
	public void setFormat(IFormat format) {
		this.format = format;
	}

	@Override
	public IFormat getFormat() {
		return this.format;
	}

	@Override
	public void setProperty(CellProperty property) {
		this.property = property;

	}

	@Override
	public CellProperty getProperty() {

		return this.property;
	}

	@Override
	public boolean containEntity() {
		for (IFunction fun : functionList) {
			if (!fun.containEntity()) {
				return false;
			}
		}
		return true;
	}

}
