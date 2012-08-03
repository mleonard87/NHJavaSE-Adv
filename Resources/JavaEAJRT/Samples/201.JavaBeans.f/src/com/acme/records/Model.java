package com.acme.records;

public interface Model {
	public void putRecord(BeanRecord record);

	public BeanRecord getRecord(String email);

	public Iterable<BeanRecord> allRecords();
}
