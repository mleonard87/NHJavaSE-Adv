package com.acme.records.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.sql.PooledConnection;

import org.apache.derby.jdbc.ClientConnectionPoolDataSource40;

import com.acme.records.BeanRecord;
import com.acme.records.Record;

public class ModelJdbcDax {
	public static Connection getConnection() throws SQLException {
		// Connection connection = DriverManager
		// .getConnection("jdbc:derby://localhost:1527/c:/records;create=true");
		Connection connection = getClientConnectionPoolDataSource40()
				.getConnection();
		System.out.println("connection: " + connection.getClass().getName());
		return connection;
	}

	static ClientConnectionPoolDataSource40 getClientConnectionPoolDataSource40() {
		ClientConnectionPoolDataSource40 dataSource = new ClientConnectionPoolDataSource40();
		System.out.println("dataSource: " + dataSource.getClass().getName());
		dataSource.setMaxStatements(20);
		dataSource.setDatabaseName("c:/records");
		dataSource.setCreateDatabase("create");
		dataSource.setServerName("localhost");
		dataSource.setPortNumber(1527);
		return dataSource;
	}

	public static PooledConnection getPooledConnection() throws SQLException {
		PooledConnection pooledConnection = getClientConnectionPoolDataSource40()
				.getPooledConnection();
		System.out.println("pooledConnection: "
				+ pooledConnection.getClass().getName());
		return pooledConnection;
	}

	public static Record readRecord(Connection connection, String email)
			throws SQLException {
		PreparedStatement st = null;
		try {
			st = connection
					.prepareStatement("select * from records where email=?");
			st.setString(1, email);
			ResultSet rs = st.executeQuery();
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
		PreparedStatement st1 = null, st2 = null;
		try {
			// try update
			st1 = connection
					.prepareStatement("update records set name=?, userid=?, password=? where email=?");
			st1.setString(1, record.getName());
			st1.setString(2, record.getUserId());
			st1.setString(3, record.getPassword());
			st1.setString(4, record.getEmail());
			int count = st1.executeUpdate();
			if (count == 0) {
				// no update, so insert
				st2 = connection
						.prepareStatement("insert into records(email, name, userid, password) values (?, ?, ?, ?)");
				st2.setString(1, record.getEmail());
				st2.setString(2, record.getName());
				st2.setString(3, record.getUserId());
				st2.setString(4, record.getPassword());
				st2.executeUpdate();
				System.out.println("jdbcmodel insert: " + record);
			} else {
				System.out.println("jdbcmodel update: " + record);
			}
		} finally {
			if (st1 != null) {
				st1.close();
			}
			if (st2 != null) {
				st2.close();
			}
		}
	}

	public static boolean login(Connection connection, String userId,
			String password) throws SQLException {
		CallableStatement cs = null;
		boolean authenticated = false;
		try {
			cs = connection.prepareCall("call login(?, ?, ?)");
			cs.setString(1, userId);
			cs.setString(2, password);
			cs.registerOutParameter(3, Types.INTEGER);
			cs.execute();
			int dbAuth = cs.getInt(3);
			if (dbAuth == 1) {
				authenticated = true;
			}
		} finally {
			if (cs != null) {
				cs.close();
			}
		}
		return authenticated;
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
				s.execute(StoredProcedures.login);
			} finally {
				if (s != null) {
					s.close();
				}
			}
		}
	}
}
