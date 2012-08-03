package com.webagesolutions.synch;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RunIncrementors {
	public static void main(String[] args) {
		Executor ex=Executors.newCachedThreadPool();
		SharedBean bean=new SharedBean();
		
		Incrementor r1=new Incrementor(bean);
		Incrementor r2=new Incrementor(bean);
		ex.execute(r1);
		ex.execute(r2);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
