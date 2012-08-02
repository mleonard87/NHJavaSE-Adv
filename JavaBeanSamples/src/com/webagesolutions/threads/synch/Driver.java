package com.webagesolutions.threads.synch;

public class Driver
{
  public static void main(String[] args)
  {
    Stack stack = new Stack();
    Producer p = new Producer(stack);
    Consumer c = new Consumer(stack);
    
    Thread tp = new Thread(p, p.getName());
    Thread tc = new Thread(c, c.getName());
    tp.start();
    tc.start();
  }
}
