package com.webagesolutions.jni;

public class TestMultiplier
{
  public static void main(String[] args)
  {
    int a = 40;
    int b = 11;
    int result = Multiplier.multiply(a, b);
    System.out.printf("%d x %d = %d", a, b, result);
  }
}
