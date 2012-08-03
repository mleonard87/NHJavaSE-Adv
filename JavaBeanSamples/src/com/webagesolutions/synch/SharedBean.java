package com.webagesolutions.synch;

public class SharedBean
{
  private int value;

  public int getValue()
  {
    return value;
  }

  public void setValue(int value)
  {
    if (value != (this.value + 1)) {
      System.out.println("Bad increment: current = " + this.value + ", new = " + value);
    }
    this.value = value;
  }
}
