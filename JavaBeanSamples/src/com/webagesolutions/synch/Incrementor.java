package com.webagesolutions.synch;

public class Incrementor implements Runnable
{
  SharedBean bean = null;
  
  public Incrementor(SharedBean bean)
  {
    this.bean = bean;
  }
  
  @Override
  public void run()
  {
    for (int i = 0; i < 300; i++) {
      synchronized (bean) {
        int oldValue = bean.getValue();
        try {
          Thread.sleep(75);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        bean.setValue(oldValue + 1);
        System.out.println("Completed " + i + " increments.");
      }
    }
  }
}
