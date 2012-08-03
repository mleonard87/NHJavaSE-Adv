package com.webagesolutions.records.sox;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.webagesolutions.records.BeanRecord;
import com.webagesolutions.records.Record;
import com.webagesolutions.records.VJFrameApp;
import com.webagesolutions.records.VMJFrameApp;
import com.webagesolutions.records.jdbc.ModelJdbcManaged;

public class AppSox
{
  public static void main(String[] args)
  {
    setLookandFeel();
    
    Record r1 = new BeanRecord("dude@acme.com", "Ludwig van Beethoven", "dude", "haydn");
    Record r2 = new BeanRecord("coyote@scme.com", "Wilie Coyote", "coyote", "lovebird");
    Record r3 = new BeanRecord("runner@scme.com", "Road Runner", "runner", "meepbeep");        
    
    ModelSox model = new ModelSox("localhost", 10002);
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
