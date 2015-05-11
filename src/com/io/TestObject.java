package com.io;

import java.io.Serializable;

public class TestObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5036708497624359022L;

	private String name;

	private int id;

	private TestObject2 obj;

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append(" name = ");
		strBuf.append(name);
		strBuf.append("\r\n ID = ");
		strBuf.append(id);
		strBuf.append("\r\n OBJ = {\r\n\t ");
		strBuf.append(obj.toString());
		strBuf.append("\r\n }");

		return strBuf.toString();

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TestObject2 getObj() {
		return obj;
	}

	public void setObj(TestObject2 obj) {
		this.obj = obj;
	}

}
