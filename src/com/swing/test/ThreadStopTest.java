package com.swing.test;

 /**
 * 描述：测试线程停止
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2014-4-21      313921         Create
 * ****************************************************************************
 * </pre>
 * @author 313921
 * @since 1.0
 */
public class ThreadStopTest extends Thread{

	private Object obj = new Object();
	
	private boolean runFlag = true;
	
	public static void main(String[] args) {
		ThreadStopTest tst = new ThreadStopTest();
		tst.start();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("停止");
		tst.runFlag = false;
		
		try {
			synchronized (tst) {
				tst.wait();
				
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("完成停止");
	}
	
	@Override
	public void run() {
		while(runFlag){
			System.out.println("111111");
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		yield();
		
		synchronized (this) {
			this.notifyAll();
		}
	}
}
