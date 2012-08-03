package com.acme.threads.sync;

public class Driver {
	public static void main(String[] args) {
		Stack stack = new Stack();
		Producer p = new Producer(stack);
		Consumer c = new Consumer(stack);
		Thread t1 = new Thread(p, p.getName());
		Thread t2 = new Thread(c, c.getName());
		t1.start();
		t2.start();
	}
}
