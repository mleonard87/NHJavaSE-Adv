package com.acme.threads.deadlock;

import java.util.Random;

public class Philosopher implements Runnable {
	private String name;
	private ChopStick chopStickLeft, chopStickRight;
	private int i;
	private Random random;

	public Philosopher(String name, ChopStick chopStickLeft,
			ChopStick chopStickRight) {
		super();
		this.name = name;
		this.chopStickLeft = chopStickLeft;
		this.chopStickRight = chopStickRight;
		this.random = new Random();
	}

	@Override
	public void run() {
		while (true) {
			synchronized (chopStickLeft) {
				// think(); // deadlock immediately
				System.out.println(this.name + " grabs " + chopStickLeft);
				synchronized (chopStickRight) {
					System.out.println(this.name + " grabs " + chopStickRight);
					eat();
					System.out.println(this.name + " releases "
							+ chopStickRight);
				}
				System.out.println(this.name + " releases " + chopStickLeft);
				// think(); // deadlock soon
			}
			think(); // deadlock eventually
		}
	}

	private void eat() {
		System.out.println(this.name + " eats bite " + (++i) + " ...");
		try {
			Thread.sleep(random.nextInt(10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void think() {
		System.out.println(this.name + " thinks ...");
		try {
			Thread.sleep(random.nextInt(10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
