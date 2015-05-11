package com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Element;

/**
 * 描述：Entity工具
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
public class EntityUtil {

	private static Map<String, Class<?>> entitysMap = new HashMap<String, Class<?>>();
	static {
		entitysMap.put(BaseEntity.TYPE, BaseEntity.class);
		entitysMap.put(TabEntity.TYPE, TabEntity.class);
		entitysMap.put(ConstantEntity.TYPE, ConstantEntity.class);
		entitysMap.put(QuantityEntity.TYPE, QuantityEntity.class);
		entitysMap.put(UnitEntity.TYPE, UnitEntity.class);
		entitysMap.put(UnitEntity1.TYPE, UnitEntity1.class);
		entitysMap.put(QuantityOneByOneEntity.TYPE,
				QuantityOneByOneEntity.class);
		entitysMap.put(ValueOneByOneEntity.TYPE, ValueOneByOneEntity.class);
		entitysMap.put(ExchangeCNY2USDEntity.TYPE, ExchangeCNY2USDEntity.class);
		entitysMap.put(GroovyEntity.TYPE, GroovyEntity.class);
		entitysMap.put(GoodNoUnitEntity.TYPE, GoodNoUnitEntity.class);
		entitysMap.put(GoodNoUnitNameEntity.TYPE, GoodNoUnitNameEntity.class);
	}

	/**
	 * <pre>
	 * 根据类型获取Entity，属性没有被初始化
	 * </pre>
	 * 
	 * @param type
	 *            类型
	 * @return 未经初始化的Entity
	 * @see EntityUtil#getEntityByElement(Element)
	 */
	public static IEntity getEntityByType(String type) {
		Class<?> c = entitysMap.get(type);
		if (c == null) {
			return null;
		} else {
			try {
				return (IEntity) c.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * <pre>
	 * 根据xml节点获取Entity对象，并对其进行初始化
	 * </pre>
	 * 
	 * @param e
	 *            xml元素
	 * @return if(e==null) return null;
	 */
	public static IEntity getEntityByElement(Element e) {
		if (e != null) {
			IEntity ent = getEntityByType(e.attributeValue("type"));
			ent.fromXml(e);
			return ent;
		}
		return null;
	}
}
