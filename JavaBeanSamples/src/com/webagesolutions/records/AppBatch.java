package com.webagesolutions.records;

import java.sql.Connection;
import java.sql.SQLException;

import com.webagesolutions.records.jdbc.ModelJdbc;
import com.webagesolutions.records.jdbc.ModelJdbcDax;
import com.webagesolutions.records.jdbc.ModelJdbcManaged;
import com.webagesolutions.records.jdbc.ModelJdbcPooled;

public class AppBatch
{
  @SuppressWarnings("static-access")
  public static void main(String[] args)
  {
    Record r1 = new BeanRecord("dude@acme.com", "Ludwig van Beethoven", "dude", "haydn");
    Record r2 = new BeanRecord("coyote@scme.com", "Wilie Coyote", "coyote", "lovebird");
    Record r3 = new BeanRecord("runner@scme.com", "Road Runner", "runner", "meepbeep");
    
    System.out.println("Model type: JDBC.");
    Model model = null;
    //model = new ModelInMemory();
    long t1 = System.nanoTime();
    try {
      Connection connection = ModelJdbcDax.getConnection();
      connection.setAutoCommit(false);
      connection.setTransactionIsolation(connection.TRANSACTION_SERIALIZABLE);
      model = new ModelJdbc(connection);
      model.putRecord(r1);
      model.putRecord(r2);
      model.putRecord(r3);
      System.out.println("got: " + model.getRecord("dude@acme.com"));
      for (Record r : model.allRecords()) {
        System.out.println("all: " + r.getName());
      }
      //Thread.sleep(10000);
      connection.setAutoCommit(true);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (model != null) {
        model.close();
      }
    }
    long t2 = System.nanoTime();
    
    System.out.println("elapsed ms: " + ((t2 - t1)/1000000));
    
    
    
    
    
    
    System.out.println("Model type: JDBC Pooled.");
    ModelJdbcPooled modelp = null;
    //model = new ModelInMemory();
    try {
      Connection connection = ModelJdbcDax.getConnection();
      connection.setAutoCommit(false);
      connection.setTransactionIsolation(connection.TRANSACTION_SERIALIZABLE);
      modelp = new ModelJdbcPooled(ModelJdbcDax.getPooledConnection());
      modelp.putRecord(r1);
      modelp.putRecord(r2);
      modelp.putRecord(r3);
      System.out.println("got: " + modelp.getRecord("dude@acme.com"));
      for (Record r : modelp.allRecords()) {
        System.out.println("all: " + r.getName());
      }
      //Thread.sleep(10000);
      connection.setAutoCommit(true);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (modelp != null) {
        modelp.close();
      }
    }
    long t3 = System.nanoTime();
    
    System.out.println("elapsed ms: " + ((t3 - t2)/1000000));
    System.out.println("Speed factor: " + ((t2 - t1)/(t3 - t2)));
    
    
    
    
    
    
    
    
    
    System.out.println("Model type: JDBC Managed.");
    ModelJdbcManaged modelm = null;
    //model = new ModelInMemory();
    modelm = new ModelJdbcManaged();
    modelm.putRecord(r1);
    modelm.putRecord(r2);
    modelm.putRecord(r3);
    System.out.println("got: " + modelm.getRecord("dude@acme.com"));
    for (Record r : modelm.allRecords()) {
      System.out.println("all: " + r.getName());
    }
    //Thread.sleep(10000);
    long t4 = System.nanoTime();
    
    System.out.println("elapsed ms: " + ((t4 - t3)/1000000));
    System.out.println("Speed factor: " + ((t2 - t1)/(t4 - t3)));
    
//    Used to fill the heap to force GC.
//    String s = "";
//    while(true) {
//      s += new String("foo");
//      if (new Random().nextInt(100) > 98) {
//        try {
//          Thread.sleep(1);
//        } catch (InterruptedException e) {
//          // TODO Auto-generated catch block
//          e.printStackTrace();
//        }
//      }
//    }
    
  }
}
