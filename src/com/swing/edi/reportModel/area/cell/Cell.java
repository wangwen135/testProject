package com.swing.edi.reportModel.area.cell;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Element;

import com.swing.edi.reportModel.area.cell.entity.BaseEntity;
import com.swing.edi.reportModel.area.cell.entity.IEntity;
import com.swing.edi.reportModel.area.cell.function.FunctionUtil;
import com.swing.edi.reportModel.area.cell.function.IFunction;
import com.swing.edi.reportModel.area.cell.part.BasePart;
import com.swing.edi.reportModel.area.cell.part.IPart;
import com.swing.edi.reportModel.area.cell.part.PartEnum;

/**
 * 描述：单元格实现
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
public final class Cell implements ICell {
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

	// 维护两个列表
	/**
	 * 实体对象列表
	 */
	private ArrayList<IEntity> entityList = new ArrayList<IEntity>();

	/**
	 * 零部件列表
	 */
	private ArrayList<IPart> partList = new ArrayList<IPart>();

	private IFunction function;

	@Override
	public Object getValue() {

		// 取值，需要根据part进行计算
		if (entityList.size() <= 0) {
			return null;
		}
		// 取第一个的值
		Object obj = entityList.get(0).getValue();

		// 顺序运算，无优先级
		for (int i = 0; i < partList.size() - 1; i++) {
			IPart part = partList.get(i);
			IEntity entity = entityList.get(i + 1);
			obj = part.operation(obj, entity.getValue());
		}
		return obj;
	}

	@Override
	public String getViewStr() {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		if (getFunction() != null) {
			sb.append("<FONT color=red>");
			sb.append(getFunction().getType());
			sb.append("(&nbsp;</FONT>");
		}
		// sb.append("<center>");

		for (int i = 0; i < entityList.size(); i++) {
			IEntity entity = entityList.get(i);

			if (IEntity.KEY_EDI_CONSTANT.equals(entity.getKey())) {
				// 常量用不同的颜色
				sb.append("<font color=green>");
				sb.append(entity.getValue());
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
		if (getFunction() != null) {
			// 添加右括号
			sb.append("<FONT color=red>&nbsp;)</FONT>");
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
	public IEntity[] getEntitys() {
		return entityList.toArray(new IEntity[0]);
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
	public IPart getPart(int index) {
		return partList.get(index);
	}

	@Override
	public void setValue(String key, String value) {
		IEntity entity = findEntityByKey(key);
		if (entity != null) {
			entity.setValue(value);
		}

	}

	@Override
	public void setValue(int index, String value) {
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
		if (getFunction() != null) {
			sb.append(getFunction().toXml());
		}
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
			if ("function".equals(e.getName())) {
				setFunction(FunctionUtil.getFunctionByElement(e));
			} else if ("entity".equals(e.getName())) {
				// 现在不判断entity的type全部做BaseEntity处理
				BaseEntity b = new BaseEntity();
				b.fromXml(e);
				entityList.add(b);
			} else if ("part".equals(e.getName())) {
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
	public boolean addEntity(IEntity e) {
		if (e == null) {
			throw new NullPointerException("要添加的 Entity 不能为空！");
		}
		return entityList.add(e);
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
	public boolean remove(IEntity e) {
		int index = entityList.indexOf(e);
		if (index < 0) {
			return false;
		}
		entityList.remove(index);
		partList.remove(index);
		return true;
	}

	@Override
	public IEntity remove(int index) {
		if (index < 0) {
			throw new IllegalArgumentException("index 不能小于0");
		}
		IEntity tmp = entityList.remove(index);
		partList.remove(index);
		return tmp;
	}

	@Override
	public void clear() {
		entityList.clear();
		partList.clear();
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
	public void setValue(Map<String, String> data) {
		for (Iterator<IEntity> iterator = entityList.iterator(); iterator
				.hasNext();) {
			IEntity entity = iterator.next();
			if (entity != null
					&& !IEntity.KEY_EDI_CONSTANT.equals(entity.getKey())) {
				// 不为空，不为常量
				entity.setValue(data.get(entity.getKey()));
			}
		}

		// 暂时先改在这里，cell中还要其他设置value的方法
		if (getFunction() != null) {
			getFunction().setValue(getValue());
		}

	}

	@Override
	public void setFunction(IFunction funct) {
		this.function = funct;
	}

	@Override
	public IFunction getFunction() {
		return this.function;
	}

	@Override
	public Object getResult() {
		if (function == null) {
			return getValue();
		} else {
			return function.getValue();
		}
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

	/*
	 * @Override public List<IEntity> getEntityList() { return this.entityList;
	 * }
	 * 
	 * @Override public List<IPart> getPartList() { return this.partList; }
	 */

}
