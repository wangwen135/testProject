package com.memoryManage;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.zip.DeflaterOutputStream;

/**
 * 测试
 * 
 */
public class MemoryTest implements Serializable {

	private static final long serialVersionUID = -936479977238731583L;

	byte[] arrays;

	public MemoryTest(int size) {

		Random r = new Random();
		arrays = new byte[size];
		for (int i = 0; i < size; i++) {
			arrays[i] = (byte) r.nextInt(256);
		}
	}

	public static void main(String[] args) {

		// while (true) {
		//
		// long t1 = System.currentTimeMillis();
		// List<MemoryTest> list = new ArrayList<MemoryTest>();
		//
		// for (int i = 0; i < 10240; i++) {
		// list.add(new MemoryTest());
		// }
		// long t2 = System.currentTimeMillis();
		//
		// System.out.println("耗时： " + (t2 - t1));

		// try {
		// Thread.sleep(10000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }

		long t1 = System.currentTimeMillis();

		MemoryTest mt = new MemoryTest(2000000);

		long t2 = System.currentTimeMillis();
		System.out.println("创建耗时： " + (t2 - t1));
		
		
		try{
			FileOutputStream fos = new FileOutputStream("d:/temp/未压缩.txt");			
			ObjectOutputStream oos = new ObjectOutputStream(
					fos);
			oos.writeObject(mt);
			oos.flush();
			oos.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		long t3 = System.currentTimeMillis();

		System.out.println("序列化耗时： " + (t3 - t2));
		

		// 此类为使用 "deflate" 压缩格式压缩数据实现输出流过滤器

		try {
			FileOutputStream fos = new FileOutputStream("d:/temp/压缩.txt");
			DeflaterOutputStream deflateroutputstream = new DeflaterOutputStream(
					fos);
			ObjectOutputStream oos = new ObjectOutputStream(
					deflateroutputstream);
			oos.writeObject(mt);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		long t4 = System.currentTimeMillis();

		System.out.println("压缩序列化耗时： " + (t4 - t3));

		// }
	}

}
