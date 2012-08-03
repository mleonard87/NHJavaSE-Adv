package com.acme.records.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.PooledConnection;

import com.acme.records.Model;
import com.acme.records.Record;

public class ModelJdbcPooled implements Model {
	private PooledConnection pooledConnection;

	public ModelJdbcPooled(PooledConnection pooledConnection) {
		this.pooledConnection = pooledConnection;
	}

	public void close() {
		if (pooledConnection != null) {
			try {
				pooledConnection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public void putRecord(Record record) {
		ModelJdbc modelJdbc = null;
		try {
			modelJdbc = new ModelJdbc(pooledConnection.getConnection());
			modelJdbc.putRecord(record);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (modelJdbc != null) {
				modelJdbc.close();
			}
		}
	}

	@Override
	public Record getRecord(String email) {
		ModelJdbc modelJdbc = null;
		try {
			modelJdbc = new ModelJdbc(pooledConnection.getConnection());
			return modelJdbc.getRecord(email);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (modelJdbc != null) {
				modelJdbc.close();
			}
		}
	}

	@Override
	public Iterable<Record> allRecords() {
		ModelJdbc modelJdbc = null;
		try {
			modelJdbc = new ModelJdbc(pooledConnection.getConnection());
			List<Record> list = new ArrayList<Record>();
			for (Record record : modelJdbc.allRecords()) {
				list.add(record);
			}
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (modelJdbc != null) {
				modelJdbc.close();
			}
		}
	}
}
