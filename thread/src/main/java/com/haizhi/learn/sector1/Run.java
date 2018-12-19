package com.haizhi.learn.sector1;

public class Run {

	public static void main(String[] args) {
		MyThread t1 = new MyThread(1);
		MyThread t2 = new MyThread(1);
		MyThread t3 = new MyThread(1);
		MyThread t4 = new MyThread(1);
		MyThread t5 = new MyThread(1);
		t1.start();
		System.out.println("t1.isAlive(): " + t1.isAlive());

		System.out.println("t2.isAlive(): " + t2.isAlive());
		t2.start();
		t2.stop();
		t3.start();
		t4.start();
		t4.interrupt();
		Thread.interrupted();
		t4.stop();
		t4.suspend();
		t4.resume();
		System.out.println("t4.isInterrupted():"+t4.isInterrupted());
		t5.start();

		System.out.println("运行结束: " + Thread.currentThread().getName());
	}

}
