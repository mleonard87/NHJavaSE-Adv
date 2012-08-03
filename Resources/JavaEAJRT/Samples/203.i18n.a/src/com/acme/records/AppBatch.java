package com.acme.records;

import java.sql.SQLException;

import com.acme.records.jdbc.ModelJdbc;
import com.acme.records.jdbc.ModelJdbcDax;
import com.acme.records.jdbc.ModelJdbcManaged;
import com.acme.records.jdbc.ModelJdbcPooled;

public class AppBatch {
	public static void main(String[] args) {
		Record r1 = new BeanRecord("dude@acme.com", "Ludwig van Beethoven",
				"dude", "haydn");
		Record r2 = new BeanRecord("coyote@acme.com", "Wiley Coyote", "coyote",
				"lovebird");
		Record r3 = new BeanRecord("runner@acme.com", "Road Runner", "runner",
				"meepbeep");

		System.out.println("model in jdbc ...");
		long t1 = System.nanoTime();
		ModelJdbc model = null;
		try {
			model = new ModelJdbc(ModelJdbcDax.getConnection());
			model.putRecord(r1);
			model.putRecord(r2);
			model.putRecord(r3);
			System.out.println("got: " + model.getRecord("dude@acme.com"));
			for (Record record : model.allRecords()) {
				System.out.println("all: " + record.getName());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (model != null) {
				model.close();
			}
		}
		long t2 = System.nanoTime();
		System.out.println("elapsed ms: " + ((t2 - t1) / 1000000));

		System.out.println("\nmodel in jdbc pooled ...");
		ModelJdbcPooled modelp = null;
		try {
			modelp = new ModelJdbcPooled(ModelJdbcDax.getPooledConnection());
			modelp.putRecord(r1);
			modelp.putRecord(r2);
			modelp.putRecord(r3);
			System.out.println("got: " + modelp.getRecord("dude@acme.com"));
			for (Record record : modelp.allRecords()) {
				System.out.println("all: " + record.getName());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (modelp != null) {
				modelp.close();
			}
		}
		long t3 = System.nanoTime();
		System.out.println("elapsed ms: " + ((t3 - t2) / 1000000));
		System.out.println("speed factor: " + (t2 - t1) / (t3 - t2));

		System.out.println("\nmodel in jdbc managed ...");
		ModelJdbcManaged modelm = null;
		modelm = new ModelJdbcManaged();
		modelm.putRecord(r1);
		modelm.putRecord(r2);
		modelm.putRecord(r3);
		System.out.println("got: " + modelm.getRecord("dude@acme.com"));
		for (Record record : modelm.allRecords()) {
			System.out.println("all: " + record.getName());
		}
		long t4 = System.nanoTime();
		System.out.println("elapsed ms: " + ((t4 - t3) / 1000000));
		System.out.println("speed factor: " + (t2 - t1) / (t4 - t3));

	}
}
