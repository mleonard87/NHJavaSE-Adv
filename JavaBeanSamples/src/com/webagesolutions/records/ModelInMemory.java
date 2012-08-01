package com.webagesolutions.records;

import java.util.Map;
import java.util.TreeMap;

public class ModelInMemory implements Model
{
  Map<String, Record> map = new TreeMap<String, Record>();
  
  @Override
  public void putRecord(Record record)
  {
    System.out.println("Model save: " + record);
    map.put(record.getEmail(), record);
  }

  @Override
  public Record getRecord(String email)
  {
    Record record = map.get(email);
    System.out.println("Model read: " + record);
    return record;
  }

  @Override
  public Iterable<Record> allRecords()
  {
    return map.values();
  }
  
  @Override
  public void close()
  {
  }
}
