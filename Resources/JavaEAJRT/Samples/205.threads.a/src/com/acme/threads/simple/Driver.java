package com.acme.threads.simple;

public class Driver {
	public static void main(String[] args) {
		Thread t = null;
		Worker[] workers = new Worker[5];
		for (int i = 0; i < 5; i++) {
			workers[i] = new Worker("worker" + i);
			t = new Thread(workers[i]);
			t.start();
		}
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < workers.length; i++) {
			workers[i].kill();
		}
		System.out.println("driver done ...");
	}
}
