package com.webagesolutions.jni;

public class Multiplier
{
  static {
    // Load the DLL
    System.loadLibrary("Multiplier");
  }

  public static native int multiply(int a, int b);
}