package com.cloneTest;

import java.io.Serializable;

public interface ClassInterface extends Serializable, Cloneable {

	String tt(String str);
	
	void toStr();
}
