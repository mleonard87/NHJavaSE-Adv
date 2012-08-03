package com.acme.records.sox;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.acme.records.Record;
import com.acme.records.jdbc.ModelJdbcManaged;

public class Daemon implements Runnable {
	private Socket socket;

	public Daemon(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			// dialog
			PrintWriter output = new PrintWriter(socket.getOutputStream());
			output.println("welcome to acme records: <email>|bye");
			output.flush();
			Scanner input = new Scanner(socket.getInputStream());
			String command = "";
			do {
				command = input.nextLine();
				System.out.println(Thread.currentThread().getName()
						+ " received " + command);
				String response = "Received '" + command + "'. ";
				if ("bye".equals(command)) {
					response += "Thanks for using acme records, bye!";
				} else {
					Record record = ModelJdbcManaged.getInstance().getRecord(
							command);
					if (record == null) {
						response += "No record found for " + command + ".";
					} else {
						response += "Found: " + record;
					}
				}
				output.println(response);
				output.flush();
			} while (!"bye".equals(command));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (socket != null) {
				System.out.println(Thread.currentThread().getName()
						+ " closing socket ...");
				try {
					socket.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		ExecutorService executorService = Executors.newCachedThreadPool();
		try {
			// connect
			serverSocket = new ServerSocket(10001);
			System.out.println(Thread.currentThread().getName()
					+ " awaiting connections ...");
			while (true) {
				// dispatch daemons
				Socket socket = serverSocket.accept();
				System.out
						.println(Thread.currentThread().getName()
								+ " connection from "
								+ socket.getRemoteSocketAddress());
				executorService.execute(new Daemon(socket));
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			executorService.shutdown();
			if (serverSocket != null) {
				System.out.println(Thread.currentThread().getName()
						+ " closing serverSocket ...");
				try {
					serverSocket.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
}
