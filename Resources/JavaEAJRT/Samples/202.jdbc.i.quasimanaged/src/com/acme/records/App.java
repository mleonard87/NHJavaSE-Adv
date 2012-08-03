package com.acme.records;

import com.acme.records.jdbc.ModelJdbcManaged;

public class App {
	public static void main(String[] args) {
		Record r1 = new BeanRecord("dude@acme.com", "Ludwig van Beethoven",
				"dude", "haydn");
		Record r2 = new BeanRecord("coyote@acme.com", "Wiley Coyote", "coyote",
				"lovebird");
		Record r3 = new BeanRecord("runner@acme.com", "Road Runner", "runner",
				"meepbeep");

		// ModelJdbc model = null;
		// try {
		// Connection connection = ModelJdbcDax.getConnection();
		// model = new ModelJdbc(connection);
		// new VJFrameApp().setModel(new VMJFrameApp(model, r1));
		// new VJFrameApp().setModel(new VMJFrameApp(model, r2));
		// new VJFrameApp().setModel(new VMJFrameApp(model, r3));
		// new VJFrameApp().setModel(new VMJFrameApp(model, r3));
		// } catch (SQLException e) {
		// e.printStackTrace();
		// } finally {
		// // gui must close it!!
		// // model.close();
		// }

		ModelJdbcManaged model = new ModelJdbcManaged();
		new VJFrameApp().setModel(new VMJFrameApp(model, r1));
		new VJFrameApp().setModel(new VMJFrameApp(model, r2));
		new VJFrameApp().setModel(new VMJFrameApp(model, r3));
		new VJFrameApp().setModel(new VMJFrameApp(model, r3));
	}
}
