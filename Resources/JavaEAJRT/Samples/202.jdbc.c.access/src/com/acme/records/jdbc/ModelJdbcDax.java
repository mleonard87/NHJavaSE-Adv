package com.acme.records.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.acme.records.BeanRecord;
import com.acme.records.Record;

public class ModelJdbcDax {
	public static Connection getConnection() throws SQLException {
		Connection connection = DriverManager
				.getConnection("jdbc:derby://localhost:1527/c:/records;create=true");
		System.out.println("connection: " + connection.getClass().getName());
		return connection;
	}

	public static Record readRecord(Connection connection, String email)
			throws SQLException {
		Statement st = null;
		try {
			st = connection.createStatement();
			ResultSet rs = st
					.executeQuery("select * from records where email='" + email
							+ "'");
			if (rs.next()) {
				Record record = readRecord(rs);
				System.out.println("jdbcmodel read: " + record);
				return record;
			}
		} finally {
			if (st != null) {
				st.close();
			}
		}
		return null;
	}

	public static Record readRecord(ResultSet rs) throws SQLException {
		Record r = new BeanRecord(rs.getString("email"), rs.getString("name"),
				rs.getString("userid"), rs.getString("password"));
		return r;
	}

	public static void writeRecord(Connection connection, Record record)
			throws SQLException {
		Statement st = null;
		try {
			// try update
			st = connection.createStatement();
			int count = st.executeUpdate("update records set name='"
					+ record.getName() + "', userid='" + record.getUserId()
					+ "', password='" + record.getPassword()
					+ "' where email='" + record.getEmail() + "'");
			if (count == 0) {
				// no update, so insert
				st.executeUpdate("insert into records(email, name, userid, password) values ('"
						+ record.getEmail()
						+ "', '"
						+ record.getName()
						+ "', '"
						+ record.getUserId()
						+ "', '"
						+ record.getPassword() + "')");
				System.out.println("jdbcmodel insert: " + record);
			} else {
				System.out.println("jdbcmodel update: " + record);
			}
		} finally {
			if (st != null) {
				st.close();
			}
		}
	}

	public static void prepareTables(Connection connection) throws SQLException {
		boolean hasTable = false;
		ResultSet rs = connection.getMetaData().getTables(null, null, null,
				new String[] { "TABLE" });
		while (rs.next()) {
			if ("records".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
				System.out.println("found records ...");
				hasTable = true;
				break;
			}
		}
		if (!hasTable) {
			Statement s = null;
			try {
				System.out.println("creating records ...");
				s = connection.createStatement();
				s.execute("create table records (email varchar(50), name varchar(50), userId varchar(50), password varchar(50))");
			} finally {
				if (s != null) {
					s.close();
				}
			}
		}
	}
}
