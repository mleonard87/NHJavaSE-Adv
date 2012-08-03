package com.acme.records;

public interface Model {
	public void putRecord(Record record);

	public Record getRecord(String email);

	public Iterable<Record> allRecords();
}
