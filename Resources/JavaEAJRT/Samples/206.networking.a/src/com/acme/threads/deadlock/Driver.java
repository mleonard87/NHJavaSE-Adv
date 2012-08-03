package com.acme.threads.deadlock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Driver {
	public static void main(String[] args) {
		int n = 5;
		List<Philosopher> philosophers = new ArrayList<Philosopher>();
		ExecutorService e = Executors.newCachedThreadPool();
		ChopStick chopStickLeft = new ChopStick("chopstick" + 1);
		ChopStick chopStickRight = null;
		ChopStick chopStickFirst = chopStickLeft;
		for (int i = 1; i < n + 1; i++) {
			if (i == n) {
				chopStickRight = chopStickFirst;
			} else {
				chopStickRight = new ChopStick("chopstick" + (i + 1));
			}
			Philosopher philosopher = new Philosopher("philosopher" + i,
					chopStickLeft, chopStickRight);
			philosophers.add(philosopher);
			chopStickLeft = chopStickRight;
		}
		for (Philosopher philosopher : philosophers) {
			e.execute(philosopher);
		}
		System.out.println("driver done ...");
	}
}
