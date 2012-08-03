package com.acme.records;

import java.sql.SQLException;
import java.util.Random;

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
		ModelJdbc model = null;
		try {
			model = new ModelJdbc(ModelJdbcDax.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// if (model != null) {
			// model.close();
			// }
		}

		String s = "";
		while (true) {
			s += new String("foo");
			if (new Random().nextInt(100) > 98) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
