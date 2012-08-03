package com.acme.records.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StoredProcedures {
	public static String login = "create procedure login( in userId varchar(50), in password varchar(50), out authenticated integer)"
			+ " parameter style java language java external name 'com.acme.records.jdbc.StoredProcedures.login'";

	public static void login(String userId, String password, int[] authenticated)
			throws SQLException {
		Connection connection = DriverManager
				.getConnection("jdbc:default:connection");
		String sql = "select * from records where userid = ? and password = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, userId);
		preparedStatement.setString(2, password);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			authenticated[0] = 1;
		} else {
			authenticated[0] = 0;
		}
		System.out.println("authenticated " + userId + ": " + authenticated[0]);
	}

	public static void main(String[] args) {
		String userId = "dude";
		String password = "haydn";
		boolean authenticated = false;
		try {
			Connection connection = ModelJdbcDax.getConnection();
			authenticated = ModelJdbcDax.login(connection, userId, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("authenticated " + userId + ": " + authenticated);
	}
}
