package com.acme.sox;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SimpleInetAddress {
	public static void main(String[] args) {
		try {
			System.out.println("localhost: " + InetAddress.getLocalHost());
			InetAddress addr = InetAddress.getByName("google.com");
			System.out.println("host: " + addr.getHostName());
			System.out.println("ip: " + addr.getHostAddress());

			InetAddress[] addresses = InetAddress.getAllByName("google.com");
			for (InetAddress inetAddress : addresses) {
				System.out.println("addr: " + inetAddress);
			}
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		}
	}
}
