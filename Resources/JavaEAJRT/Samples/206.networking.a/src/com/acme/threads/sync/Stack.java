package com.acme.threads.sync;

public class Stack {
	private int top = 0;
	private int size = 100;
	private char[] buffer = new char[size];

	public synchronized void push(char c) {
		while (top == size) { // avoid overflow
			try {
				System.out.println(Thread.currentThread().getName()
						+ ": push wait ...");
				this.wait();
				System.out.println(Thread.currentThread().getName()
						+ ": puhs resume ...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		buffer[top] = c;
		top++;
		this.notifyAll();
	}

	public synchronized char pop() {
		while (top == 0) { // avoid underflow
			try {
				System.out.println(Thread.currentThread().getName()
						+ ": pop wait ...");
				this.wait();
				System.out.println(Thread.currentThread().getName()
						+ ": pop resume ...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		top--;
		this.notifyAll();
		return buffer[top];
	}

}
