package com.webagesolutions.jni;

public class Multiplier {
	public static native int multiply(int a, int b);

	static {
		// Load the DLL
		System.loadLibrary("Multiplier");
	}
}
