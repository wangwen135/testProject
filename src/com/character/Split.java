package com.character;

import java.util.Arrays;

public class Split {
public static void main(String[] args) {
	String a = "aaa,bbb,ccc";
	System.out.println(Arrays.toString(a.split(",",5)));
}
}
