package com.swing.edi.test;

import java.awt.datatransfer.DataFlavor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;

import com.swing.edi.reportModel.area.cell.Cell;
import com.swing.edi.reportModel.area.cell.ICell;
import com.swing.edi.reportModel.area.cell.entity.BaseEntity;
import com.swing.edi.reportModel.area.cell.entity.IEntity;
import com.swing.edi.reportModel.area.cell.entity.TrimEntity;
import com.swing.edi.reportModel.area.cell.part.BasePart;
import com.swing.edi.reportModel.area.cell.part.IPart;
import com.swing.edi.reportModel.area.cell.part.PartEnum;

public class Test {

	public static void main(String[] args) {
		// 判断一个对象是否是数字
		// 1352.2
		BigDecimal b1 = new BigDecimal("12213110.1231523154215544655");
		BigDecimal b2 = new BigDecimal("3011532.1255564445587756422465");
		System.out.println(b1.divide(b2, 20, RoundingMode.HALF_UP));
		System.out.println(b1.divide(b2, MathContext.DECIMAL128));
		
		System.out.println(b1.multiply(b2));

		System.out.println(b2.add(b1));
		System.out.println(b2.subtract(b1));
		
		System.out.println(b1.remainder(b2));
		
	}

	public static BigDecimal getDecimal(Object o) {
		try {
			if (o instanceof BigDecimal)
				return (BigDecimal) o;
			if (o instanceof BigInteger)
				return new BigDecimal((BigInteger) o);
			if (o instanceof char[])
				return new BigDecimal((char[]) o);
			if (o instanceof Short)
				return new BigDecimal((Short) o);
			if (o instanceof Integer)
				return new BigDecimal((Integer) o);
			if (o instanceof Float)
				return new BigDecimal(Float.toString((Float) o));
			if (o instanceof Double)
				return new BigDecimal(Double.toString((Double) o));
			// 剩余的都做string处理
			return new BigDecimal(o.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main3(String[] args) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("ddsdasdfbb");
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main2(String[] args) {
		IPart part = BasePart.getPart(PartEnum.none);

		PartEnum p = PartEnum.add;
		System.out.println(p.name());
		System.out.println(p.ordinal());

		PartEnum p2 = Enum.valueOf(PartEnum.class, "add");
		System.out.println(p2.getPart().toXml());
		System.out.println(p2.ordinal());
	}

	public static void test1() {
		IEntity entity = new BaseEntity("键", "值", "表现串");
		IEntity entity2 = new BaseEntity("键2", "值2", "表现串2");
		TrimEntity te = new TrimEntity(entity);

		ICell cell = new Cell();
		cell.addEntityAndPart(entity);
		cell.addEntityAndPart(entity2);

		System.out.println(cell.getViewStr());
	}
}
