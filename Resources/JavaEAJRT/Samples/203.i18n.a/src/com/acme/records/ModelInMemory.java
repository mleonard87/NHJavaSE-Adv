package com.acme.records;

import java.util.Map;
import java.util.TreeMap;

public class ModelInMemory implements Model {
	private Map<String, Record> map = new TreeMap<String, Record>();

	@Override
	public void putRecord(Record record) {
		System.out.println("model save: " + record);
		map.put(record.getEmail(), record);
	}

	@Override
	public Record getRecord(String email) {
		Record record = map.get(email);
		System.out.println("model read: " + email + "->" + record);
		return record;
	}

	@Override
	public Iterable<Record> allRecords() {
		return map.values();
	}
}
