package com.webagesolutions.threads.deadlock;

public class ChopStick
{
  private String name;

  public ChopStick(String name)
  {
    super();
    this.name = name;
  }
  
  @Override
  public String toString()
  {
    return this.name;
  }
}
