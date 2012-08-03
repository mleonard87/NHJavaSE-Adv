package com.webagesolutions.sox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class SimpleBrowser
{
  public static void main(String[] args)
  {
    try {
//      URL target = new URL("http://www.google.com.au");
      URL target = new URL("file://localhost/C:/Documents and Settings/Admin/workspace/JavaBeanSamples/source-Cp1252.txt");
      System.out.println("target: " + target);
      URLConnection targetConnection = target.openConnection();
      BufferedReader in = new BufferedReader(new InputStreamReader(targetConnection.getInputStream()));
      String inputLine;
      while ((inputLine = in.readLine()) != null) {
        System.out.println(inputLine);
      }
    } catch (MalformedURLException e) {
      System.out.println(e.getMessage());
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    
  }
}
