package com.acme.threads.daemon;

public class Driver {
	public static void main(String[] args) {
		DaemonController dc = new DaemonController();
		dc.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		dc.stop();
	}
}
