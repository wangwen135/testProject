package com.io;

import java.io.Serializable;

public class TestObject2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6596578707113697515L;

	private String name;

	private int id;

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("name = ");
		strBuf.append(name);
		strBuf.append("\r\n\t ID = ");
		strBuf.append(id);

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

}
