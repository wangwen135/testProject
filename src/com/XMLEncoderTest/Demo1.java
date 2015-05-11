package com.XMLEncoderTest;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

import javax.swing.JButton;

public class Demo1 {
	public static void main(String[] args) throws FileNotFoundException {
		XMLEncoder e = new XMLEncoder(new BufferedOutputStream(
				new FileOutputStream("Test.xml")));
		e.writeObject(new JButton("Hello, world"));
		e.writeObject("OBJECT");
		e.writeObject(new Date());
		e.close();

	}
}
