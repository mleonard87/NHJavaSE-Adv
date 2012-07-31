package com.webagesolutions.users;

import java.sql.Connection;
import java.sql.SQLException;

import com.webagesolutions.jdbc.DBUserStore;

public class PopulateUsersTable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/* Get a connection to the USERS database. */
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			UserStore store=new DBUserStore(conn);
			/* Load some default users. */
			UserStoreUtils.populate(store);

			System.out.println("Users table was populated succesfully.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			/* Close the connection. */
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
				}
			}

		}

	}
}
