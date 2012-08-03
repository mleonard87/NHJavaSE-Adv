package com.acme.records;

public class AppBatch {
	public static void main(String[] args) {
		BeanRecord r1 = new BeanRecord("dude@acme.com", "Ludwig van Beethoven",
				"dude", "haydn");
		BeanRecord r2 = new BeanRecord("coyote@acme.com", "Wiley Coyote",
				"coyote", "lovebird");
		BeanRecord r3 = new BeanRecord("runner@acme.com", "Road Runner",
				"runner", "meepbeep");

		System.out.println("model in memory ...");
		Model model = new ModelInMemory();
		model.putRecord(r1);
		model.putRecord(r2);
		model.putRecord(r3);
		for (BeanRecord record : model.allRecords()) {
			System.out.println("all: " + record.getName());
		}
		System.out.println("got: " + model.getRecord("dude@acme.com"));
	}
}
