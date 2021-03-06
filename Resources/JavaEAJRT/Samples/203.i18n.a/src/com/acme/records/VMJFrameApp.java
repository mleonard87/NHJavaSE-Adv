package com.acme.records;

public class VMJFrameApp {
	private Model model;
	private BeanRecord beanRecord;

	public VMJFrameApp(Model model, Record record) {
		this.model = model;
		this.beanRecord = new BeanRecord(record);
	}

	public BeanRecord currentBeanRecord() {
		return this.beanRecord;
	}

	public void put() {
		model.putRecord(new BeanRecord(this.beanRecord));
	}

	public void get() {
		String email = beanRecord.getEmail();
		Record record = model.getRecord(email);
		if (record == null) {
			this.beanRecord = new BeanRecord();
			this.beanRecord.setEmail(email);
		} else {
			beanRecord = new BeanRecord(record);
		}
	}
}