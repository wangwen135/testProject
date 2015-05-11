package com.swing.test;

public class ObjectTest {

	private String name;
	
	private int id;
	
	private CloneTest cloneTest;

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

	public CloneTest getCloneTest() {
		return cloneTest;
	}

	public void setCloneTest(CloneTest cloneTest) {
		this.cloneTest = cloneTest;
	}

	public static void main(String[] args) {
		ObjectTest objectTest = new ObjectTest();
		CloneTest cloneTest = new CloneTest();
		cloneTest.setName("whb");
		objectTest.setCloneTest(cloneTest);
		System.out.println("=======" + objectTest.getCloneTest().getName() );
		CloneTest cloneTest1 = objectTest.getCloneTest();
		cloneTest1.setName("wwh");
		System.out.println("=======" + objectTest.getCloneTest().getName() );
	}
}
