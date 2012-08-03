package com.acme.threads.callable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Driver {
	public static void main(String[] args) {
		Worker[] workers = new Worker[7];
		ExecutorService e = Executors.newCachedThreadPool();
		for (int i = 0; i < 7; i++) {
			workers[i] = new Worker("worker" + i);
		}
		try {
			List<Future<String>> answers = e.invokeAll(Arrays.asList(workers));
			for (Future<String> future : answers) {
				System.out.println(future.get());
			}
			e.shutdown();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			e1.printStackTrace();
		}
		System.out.println("driver done ...");
	}
}
