package com.webagesolutions.records.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import com.webagesolutions.records.Record;

public class ModelJdbcDaxIteratorRecord implements Iterator<Record>
{
  private PreparedStatement stmt = null;
  private ResultSet rs = null;
  private Record nextResult;
  
  public ModelJdbcDaxIteratorRecord(Connection connection)
  {
    try {
      stmt = connection.prepareStatement("SELECT * FROM records");
      rs = stmt.executeQuery();
      advance();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean hasNext()
  {
    return (nextResult != null);
  }

  @Override
  public Record next()
  {
    Record currentNext = nextResult;
    advance();
    return currentNext;
  }

  @Override
  public void remove()
  {
    throw new UnsupportedOperationException("You cannont remove results from this ResultSet.");
  }
  
  private void advance()
  {
    try {
      if (rs == null) {
        return;
      } else if (rs.next()) {
        this.nextResult = ModelJdbcDax.readRecord(rs);
      } else {
        this.nextResult = null;
        if (stmt != null) {
          stmt.close();
        }
        rs = null;
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
