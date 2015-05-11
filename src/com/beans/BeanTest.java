package com.beans;

import java.beans.BeanInfo;
import java.beans.Expression;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;

public class BeanTest {
	public static void main(String[] args) {
		try {

			Class<?> c = Class.forName("com.beans.Customer");
			Customer cus = (Customer) c.newInstance();

			BeanInfo bi = Introspector.getBeanInfo(c);
			PropertyDescriptor[] prodesc = bi.getPropertyDescriptors();
			for (PropertyDescriptor prop : prodesc) {
				System.out.println(prop.getName());
				if(prop.getName().equals("class"))
					continue;
				System.out.println(prop.getPropertyType());

				PropertyEditor pe = PropertyEditorManager.findEditor(prop
						.getPropertyType());
				pe.setAsText("86");
				Object obj = pe.getValue();
				System.out.println(obj + " | " + obj.getClass());

				Expression e = new Expression(cus, prop.getWriteMethod()
						.getName(), new Object[] { obj });

				e.execute();
			}
			System.out.println(cus);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
