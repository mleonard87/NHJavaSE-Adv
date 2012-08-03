package com.acme.records;

public class AppBatch {
	public static void main(String[] args) {
		Record r1 = new BeanRecord("dude@acme.com", "Ludwig van Beethoven",
				"dude", "haydn");
		Record r2 = new BeanRecord("coyote@acme.com", "Wiley Coyote", "coyote",
				"lovebird");
		Record r3 = new BeanRecord("runner@acme.com", "Road Runner", "runner",
				"meepbeep");

		System.out.println("model in memory ...");
		Model model = new ModelInMemory();
		model.putRecord(r1);
		model.putRecord(r2);
		model.putRecord(r3);
		for (Record record : model.allRecords()) {
			System.out.println("all: " + record.getName());
		}
		System.out.println("got: " + model.getRecord("dude@acme.com"));
	}
}
