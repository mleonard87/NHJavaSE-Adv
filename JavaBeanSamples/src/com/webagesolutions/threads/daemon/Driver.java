package com.webagesolutions.threads.daemon;

public class Driver
{
  public static void main(String[] args)
  {
    DaemonController dc = new DaemonController();
    dc.start();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    dc.stop();
  }
}
