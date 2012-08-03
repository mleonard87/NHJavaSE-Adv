package com.acme.records.jdbc;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.derby.client.ClientPooledConnection40;

public class ModelJdbcDaxDataSource implements DataSource {

	private static Map<String, ModelJdbcDaxDataSource> instances = new HashMap<String, ModelJdbcDaxDataSource>();
	private ClientPooledConnection40 pooledConnection40;

	public static DataSource getInstance(String name) {
		ModelJdbcDaxDataSource modelJdbcDaxDataSource = instances.get(name);
		if (modelJdbcDaxDataSource == null) {
			try {
				System.out.println("creating new " + name + " "
						+ ModelJdbcDaxDataSource.class.getName());
				modelJdbcDaxDataSource = new ModelJdbcDaxDataSource(
						(ClientPooledConnection40) ModelJdbcDax
								.getClientConnectionPoolDataSource40()
								.getPooledConnection());
				if (instances.size() == 0) {
					Connection connection = null;
					try {
						connection = modelJdbcDaxDataSource.pooledConnection40
								.getConnection();
						ModelJdbcDax.prepareTables(connection);
					} finally {
						if (connection != null) {
							connection.close();
						}
					}
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				System.out.println("exiting ...");
				System.exit(0);
			}
			instances.put(name, modelJdbcDaxDataSource);
		}
		return modelJdbcDaxDataSource;
	}

	private ModelJdbcDaxDataSource(
			ClientPooledConnection40 clientPooledConnection40) {
		this.pooledConnection40 = clientPooledConnection40;
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Connection getConnection() throws SQLException {
		Connection connection = this.pooledConnection40.getConnection();
		System.out.println("connection: " + connection.getClass().getName());
		return connection;
	}

	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
