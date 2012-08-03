package com.acme.records;

public class VMJFrameApp {
	private Model model;
	private BeanRecord beanRecord;

	public VMJFrameApp(Model model, BeanRecord beanRecord) {
		this.model = model;
		this.beanRecord = beanRecord;
	}

	public BeanRecord currentBeanRecord() {
		return this.beanRecord;
	}

	public void put() {
		model.putRecord(beanRecord);
	}

	public void get() {
		String email = beanRecord.getEmail();
		BeanRecord record = model.getRecord(email);
		if (record == null) {
			this.beanRecord = new BeanRecord();
			this.beanRecord.setEmail(email);
		} else {
			beanRecord = record;
		}
	}
}