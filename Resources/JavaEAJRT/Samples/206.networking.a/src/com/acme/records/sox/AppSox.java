package com.acme.records.sox;

import com.acme.records.BeanRecord;
import com.acme.records.Record;
import com.acme.records.VJFrameApp;
import com.acme.records.VMJFrameApp;

public class AppSox {
	public static void main(String[] args) {
		Record r1 = new BeanRecord("dude@acme.com", "Ludwig van Beethoven",
				"dude", "haydn");
		Record r2 = new BeanRecord("coyote@acme.com", "Wiley Coyote", "coyote",
				"lovebird");
		Record r3 = new BeanRecord("runner@acme.com", "Road Runner", "runner",
				"meepbeep");

		ModelSox model = new ModelSox("localhost", 10002);
		new VJFrameApp().setModel(new VMJFrameApp(model, r1));
		new VJFrameApp().setModel(new VMJFrameApp(model, r2));
		new VJFrameApp().setModel(new VMJFrameApp(model, r3));
		new VJFrameApp().setModel(new VMJFrameApp(model, r3));
	}
}
