package com.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializableObjectCopy {
	public static void main(String[] args) {

		TestObject2 t2 = new TestObject2();
		t2.setName("name2");
		t2.setId(22);

		TestObject t1 = new TestObject();
		t1.setName("name1");
		t1.setId(11);
		t1.setObj(t2);

		System.out.println(t1.hashCode());
		System.out.println(t1);

		System.out.println("复制。。。");
		TestObject tmp = CopySerializableObject(t1);
		System.out.println(tmp.hashCode());
		System.out.println(tmp);

		// 创建对象流

		// try {
		// ByteArrayOutputStream byteArrayOutStr = new ByteArrayOutputStream();
		// ObjectOutputStream objOutStr = new ObjectOutputStream(
		// byteArrayOutStr);
		// objOutStr.writeObject(t1);
		// objOutStr.flush();
		// objOutStr.close();
		// ByteArrayInputStream byteArrayInStr = new ByteArrayInputStream(
		// byteArrayOutStr.toByteArray());
		// ObjectInputStream objInStr = new ObjectInputStream(byteArrayInStr);
		// Object readObj = objInStr.readObject();
		// System.out.println("复制。。。");
		// System.out.println(readObj.hashCode());
		// System.out.println(readObj);
		//
		// } catch (IOException e) {
		// e.printStackTrace();
		// } catch (ClassNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

	@SuppressWarnings("unchecked")
	public static <T> T CopySerializableObject(T t) {
		if (t == null)
			return null;
		ObjectOutputStream objOutStr = null;
		ObjectInputStream objInStr = null;
		try {
			ByteArrayOutputStream byteArrayOutStr = new ByteArrayOutputStream();
			objOutStr = new ObjectOutputStream(byteArrayOutStr);
			objOutStr.writeObject(t);
			objOutStr.flush();

			ByteArrayInputStream byteArrayInStr = new ByteArrayInputStream(
					byteArrayOutStr.toByteArray());
			objInStr = new ObjectInputStream(byteArrayInStr);
			Object readObj = objInStr.readObject();

			return (T) readObj;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (objOutStr != null)
				try {
					objOutStr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (objInStr != null)
				try {
					objInStr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return null;
	}
}
