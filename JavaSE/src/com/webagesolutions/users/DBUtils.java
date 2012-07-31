package com.webagesolutions.users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
			Connection conn=DriverManager.getConnection("jdbc:db2:USERS");
			return conn;

	}
	
	public static User readUser(ResultSet rs) throws SQLException {
		User u=null;
		String isAdmin=rs.getString("ISADMIN");
		if ("Y".equals(isAdmin)) {
			u=new Administrator();
		} else {
			u=new RegularUser();
		}
		u.setEmail(rs.getString("EMAIL"));
		u.setFirstName(rs.getString("FIRSTNAME"));
		u.setLastName(rs.getString("LASTNAME"));
		u.setUserName(rs.getString("USERNAME"));
		u.setPassword(rs.getString("PASSWORD"));
		return u;
	}
	
	public static void writeUser(Connection conn, User aUser) throws SQLException {
		String updQuery="UPDATE USERS "
			+ "SET FIRSTNAME=?, LASTNAME=?, EMAIL=?, "
			+ "PASSWORD=?, ISADMIN=? WHERE USERNAME=?";
		PreparedStatement update=conn.prepareStatement(updQuery);
		
		String insQuery="INSERT INTO USERS(FIRSTNAME, LASTNAME, "
			+ "EMAIL, USERNAME, PASSWORD, ISADMIN) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement insert=conn.prepareStatement(insQuery);
		
		/* Try the update. */
		update.setString(1, aUser.getFirstName());
		update.setString(2, aUser.getLastName());
		update.setString(3, aUser.getEmail());
		update.setString(4, aUser.getPassword());
		update.setString(5, aUser.isAdministrator()?"Y":"N");
		update.setString(6, aUser.getUserName());
		
		int count=update.executeUpdate();
		
		if (count ==0) {
			/* No records updated, so insert one. */
			insert.setString(1, aUser.getFirstName());
			insert.setString(2, aUser.getLastName());
			insert.setString(3, aUser.getEmail());
			insert.setString(4, aUser.getUserName());
			insert.setString(5, aUser.getPassword());
			insert.setString(6, aUser.isAdministrator()?"Y":"N");
			insert.executeUpdate();
		}
	}
}
