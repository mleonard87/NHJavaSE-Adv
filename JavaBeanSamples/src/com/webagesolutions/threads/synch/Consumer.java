package com.webagesolutions.threads.synch;

import java.util.Random;

public class Consumer implements Runnable
{
  private Stack stack;
  private static int counter = 1;
  private int num;
  
  public Consumer(Stack stack)
  {
    this.stack = stack;
    num = counter++;
  }
  
  public String getName()
  {
    return "Consumer " + this.num; 
  }
  
  @Override
  public void run()
  {
    char c;
    String s = "";
    Random random = new Random();
    for (int i = 0; i < 30; i++) {
      c = this.stack.pop();
      System.out.println("POP: " + Thread.currentThread().getName() + ": " + i + ": " + c);
      s += c;
      try {
        Thread.sleep(random.nextInt(300));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println(Thread.currentThread().getName() + ": " + s);
  }
}
