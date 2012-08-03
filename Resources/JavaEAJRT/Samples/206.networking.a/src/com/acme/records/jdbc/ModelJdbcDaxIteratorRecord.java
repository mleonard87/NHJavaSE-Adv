package com.acme.records.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import com.acme.records.Record;

public class ModelJdbcDaxIteratorRecord implements Iterator<Record> {
	private PreparedStatement st = null;
	private ResultSet rs = null;
	private Record nextResult = null;

	public ModelJdbcDaxIteratorRecord(Connection connection) {
		try {
			st = connection.prepareStatement("select * from records");
			rs = st.executeQuery();
			advance();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean hasNext() {
		return (nextResult != null);
	}

	@Override
	public Record next() {
		Record currentNext = nextResult;
		advance();
		return currentNext;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	private void advance() {
		try {
			if (rs == null) {
				return;
			} else if (rs.next()) {
				this.nextResult = ModelJdbcDax.readRecord(rs);
			} else {
				this.nextResult = null;
				if (st != null) {
					st.close(); // closes rs as well
				}
				rs = null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
