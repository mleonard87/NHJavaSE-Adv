package com.webagesolutions.records.sox;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.management.RuntimeErrorException;

import com.webagesolutions.records.Model;
import com.webagesolutions.records.Record;

public class ModelSox implements Model
{
  private Socket socket;
  private ObjectInputStream input;
  private ObjectOutputStream output;
  
  public ModelSox(String serverName, int portNumber)
  {
    try {
      socket = new Socket(serverName, portNumber);
      input = new ObjectInputStream(socket.getInputStream());
      output = new ObjectOutputStream(socket.getOutputStream());
      System.out.println("ModelSox connected, the server said: " + input.readObject());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  
  public void close()
  {
    try {
      socket.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void putRecord(Record beanRecord)
  {
    try {
      output.writeObject("put");
      output.writeObject(beanRecord);
      Object response = input.readObject();
      System.out.println("ModelSox.putRecord: " + response);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Record getRecord(String email)
  {
    try {
      output.writeObject("get");
      output.writeObject(email);
      Object response = input.readObject();
      System.out.println("ModelSox.putRecord: " + response);
      if (response instanceof Record) {
        return (Record) response;
      } else {
        return null;
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Iterable<Record> allRecords()
  {
    throw new UnsupportedOperationException();
  }
}
