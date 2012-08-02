package com.webagesolutions.records;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.webagesolutions.records.jdbc.ModelJdbc;
import com.webagesolutions.records.jdbc.ModelJdbcDax;
import com.webagesolutions.records.jdbc.ModelJdbcManaged;

public class App
{
  public static void main(String[] args)
  {
    setLookandFeel();
    
    Record r1 = new BeanRecord("dude@acme.com", "Ludwig van Beethoven", "dude", "haydn");
    Record r2 = new BeanRecord("coyote@scme.com", "Wilie Coyote", "coyote", "lovebird");
    Record r3 = new BeanRecord("runner@scme.com", "Road Runner", "runner", "meepbeep");
    
//    Model model = null;
//    //model = new ModelInMemory();
//    try {
//      Connection connection = ModelJdbcDax.getConnection();
//      model = new ModelJdbc(connection);
//
//      new VJFrameApp().setModel(new VMJFrameApp(model, r1));
//      new VJFrameApp().setModel(new VMJFrameApp(model, r2));
//      new VJFrameApp().setModel(new VMJFrameApp(model, r3));
//      new VJFrameApp().setModel(new VMJFrameApp(model, r3));
//    } catch (SQLException e) {
//      e.printStackTrace();
//    } finally {
//      // GUI must close this connection.
//      // model.close();
//    }
        
    
    ModelJdbcManaged model = new ModelJdbcManaged();
    new VJFrameApp().setModel(new VMJFrameApp(model, r1));
    new VJFrameApp().setModel(new VMJFrameApp(model, r2));
    new VJFrameApp().setModel(new VMJFrameApp(model, r3));
    new VJFrameApp().setModel(new VMJFrameApp(model, r3));   
  }
  
  private static void setLookandFeel(){
    try {
      UIManager.setLookAndFeel(
          UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (InstantiationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (UnsupportedLookAndFeelException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
