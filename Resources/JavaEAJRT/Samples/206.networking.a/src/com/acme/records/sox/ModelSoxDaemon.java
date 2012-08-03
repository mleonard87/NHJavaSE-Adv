package com.acme.records.sox;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.acme.records.Record;
import com.acme.records.jdbc.ModelJdbcManaged;

public class ModelSoxDaemon implements Runnable {
	private Socket socket;

	public ModelSoxDaemon(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			// dialog
			ObjectOutputStream output = new ObjectOutputStream(
					socket.getOutputStream());
			// negotiate
			output.writeObject("welcome to acme records.");
			output.flush();
			ObjectInputStream input = new ObjectInputStream(
					socket.getInputStream());
			String command = "";
			do {
				command = (String) input.readObject();
				System.out.println(Thread.currentThread().getName()
						+ " received " + command);
				String response = "Received '" + command + "'. ";
				Object responseObject = null;
				if ("bye".equals(command)) {
					response += "Thanks for using acme records, bye!";
				} else if ("get".equals(command)) {
					String email = (String) input.readObject();
					responseObject = ModelJdbcManaged.getInstance().getRecord(
							email);
					if (responseObject == null) {
						response += "No record found for " + email + ".";
					}
				} else if ("put".equals(command)) {
					Record record = (Record) input.readObject();
					ModelJdbcManaged.getInstance().putRecord(record);
					response += "Put record: " + record;
				}
				if (responseObject == null) {
					responseObject = response;
				}
				output.writeObject(responseObject);
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
			serverSocket = new ServerSocket(10002);
			System.out.println(Thread.currentThread().getName()
					+ " awaiting connections ...");
			while (true) {
				// dispatch daemons
				Socket socket = serverSocket.accept();
				System.out
						.println(Thread.currentThread().getName()
								+ " connection from "
								+ socket.getRemoteSocketAddress());
				executorService.execute(new ModelSoxDaemon(socket));
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
