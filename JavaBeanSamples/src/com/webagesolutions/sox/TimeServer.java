package com.webagesolutions.sox;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class TimeServer
{
  public static void main(String[] args) throws IOException
  {
    DatagramSocket socket = new DatagramSocket(10003);
    while (true) {
      byte[] buffer = new byte[256];
      DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
      System.out.println("waiting for packets ...");
      socket.receive(packet);
      String date = new Date().toString();
      buffer = date.getBytes();
      InetAddress address = packet.getAddress();
      int port = packet.getPort();
      System.out.println("received packet from " + address);
      packet = new DatagramPacket(buffer, buffer.length, address, port);
      socket.send(packet);
    }
  }
}
