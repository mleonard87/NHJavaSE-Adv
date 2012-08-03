package com.acme.records;

import java.sql.Connection;
import java.sql.SQLException;

import com.acme.records.jdbc.ModelJdbc;
import com.acme.records.jdbc.ModelJdbcDax;

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
			Connection connection = ModelJdbcDax.getConnection();
			connection.setAutoCommit(false);
			connection
					.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			System.out.println(connection.getTransactionIsolation());
			model = new ModelJdbc(connection);
			// model.putRecord(r1);
			// model.putRecord(r2);
			// model.putRecord(r3);
			System.out.println("got: " + model.getRecord("dude@acme.com"));
			// for (Record record : model.allRecords()) {
			// System.out.println("all: " + record.getName());
			// }
			Thread.sleep(10000);
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (model != null) {
				model.close();
			}
		}
		long t2 = System.nanoTime();
		System.out.println("elapsed ms: " + ((t2 - t1) / 1000000));
	}
}
