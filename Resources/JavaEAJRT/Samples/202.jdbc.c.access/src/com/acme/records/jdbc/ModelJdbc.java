package com.acme.records.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import com.acme.records.Model;
import com.acme.records.Record;

public class ModelJdbc implements Model {
	private Connection connection;

	public ModelJdbc(Connection connection) {
		this.connection = connection;
		try {
			ModelJdbcDax.prepareTables(connection);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	protected void finalize() throws Throwable {
		try {
			if (connection != null && !connection.isClosed()) {
				System.out.println("closing unclosed connection!");
				this.close();
			}
		} finally {
			super.finalize();
		}
	}

	public void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public void putRecord(Record record) {
		try {
			ModelJdbcDax.writeRecord(connection, record);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Record getRecord(String email) {
		try {
			return ModelJdbcDax.readRecord(connection, email);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Iterable<Record> allRecords() {
		throw new UnsupportedOperationException();
	}
}
