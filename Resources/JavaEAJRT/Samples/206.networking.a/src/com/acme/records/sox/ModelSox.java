package com.acme.records.sox;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.acme.records.Model;
import com.acme.records.Record;

public class ModelSox implements Model {
	private Socket socket;
	private ObjectInputStream input;
	private ObjectOutputStream output;

	public ModelSox(String serverName, int portNumber) {
		try {
			socket = new Socket(serverName, portNumber);
			input = new ObjectInputStream(socket.getInputStream());
			output = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("model sox connected, server said: "
					+ input.readObject());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void putRecord(Record record) {
		try {
			output.writeObject("put");
			output.writeObject(record);
			Object response = input.readObject();
			System.out.println("model sox putRecord: " + response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Record getRecord(String email) {
		try {
			output.writeObject("get");
			output.writeObject(email);
			Object response = input.readObject();
			System.out.println("model sox getRecord: " + response);
			if (response instanceof Record) {
				return (Record) response;
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Iterable<Record> allRecords() {
		throw new UnsupportedOperationException();
	}
}
