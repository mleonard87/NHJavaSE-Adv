package com.webagesolutions.users;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateUsersTable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/* Get a connection to the USERS database. */
		Connection conn = null;
		/* Create the table. */
		Statement st = null;
		try {
			conn = DBUtils.getConnection();

			st = conn.createStatement();
			String ddlQuery = "CREATE TABLE USERS "
					+ "(EMAIL VARCHAR(60), FIRSTNAME VARCHAR(40), "
					+ "LASTNAME VARCHAR(60), PASSWORD VARCHAR(20), "
					+ "ISADMIN CHAR(1), USERNAME VARCHAR(20))";
			st.execute(ddlQuery);
			System.out.println("Users table was created succesfully.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			/* Close the connection. */
			if (st != null) {
				try {
					st.close();
				} catch (Exception e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
				}
			}

		}

	}
}
