package com.acme.records.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModelJdbcDax {
	public static Connection getConnection() throws SQLException {
		Connection connection = DriverManager
				.getConnection("jdbc:derby://localhost:1527/c:/records;create=true");
		System.out.println("connection: " + connection.getClass().getName());
		return connection;
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
