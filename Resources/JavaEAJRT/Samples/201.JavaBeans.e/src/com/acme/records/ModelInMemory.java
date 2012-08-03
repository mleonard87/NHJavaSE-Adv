package com.acme.records;

import java.util.Map;
import java.util.TreeMap;

public class ModelInMemory implements Model {
	private Map<String, BeanRecord> map = new TreeMap<String, BeanRecord>();

	@Override
	public void putRecord(BeanRecord record) {
		System.out.println("model save: " + record);
		map.put(record.getEmail(), record);
	}

	@Override
	public BeanRecord getRecord(String email) {
		BeanRecord record = map.get(email);
		System.out.println("model read: " + email + "->" + record);
		return record;
	}

	@Override
	public Iterable<BeanRecord> allRecords() {
		return map.values();
	}
}
