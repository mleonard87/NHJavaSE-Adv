package com.webagesolutions.users;

import java.sql.Connection;
import java.sql.SQLException;

import com.webagesolutions.jdbc.DBUserStore;

public class TestDBUserStore {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/* Get a connection to the USERS database. */
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			UserStore store = new DBUserStore(conn);

			/* Print them out. */
			for (User user : store.listAllUsers()) {
				user.printTo(System.out);
				System.out.println();
			}

			/* Look up Jim Waldo. */
			System.out.println("Jim Waldo's user record is:");
			User waldo = store.getUser("waldoj@sun.com");
			if (waldo == null) {
				System.out.println("--Unknown--");
			} else {
				waldo.printTo(System.out);
			}
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
