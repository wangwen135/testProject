package com.beans;

public class Customer {
	private String name;
	private int age;
	private String tel;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {

		return "姓名：" + name + " 年龄：" + age + " 电话：" + tel;
	}
}
