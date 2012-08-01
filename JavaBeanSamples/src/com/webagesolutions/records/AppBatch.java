package com.webagesolutions.records;

import java.sql.SQLException;

import com.webagesolutions.records.jdbc.ModelJdbc;
import com.webagesolutions.records.jdbc.ModelJdbcDax;

public class AppBatch
{
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
      model = new ModelJdbc(ModelJdbcDax.getConnection());
      model.putRecord(r1);
      model.putRecord(r2);
      model.putRecord(r3);
      System.out.println("got: " + model.getRecord("dude@acme.com"));
      for (Record r : model.allRecords()) {
        System.out.println("all: " + r.getName());
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (model != null) {
        model.close();
      }
    }
    long t2 = System.nanoTime();
    
    System.out.println("elapsed ms: " + ((t2 - t1)/1000000));
    
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
