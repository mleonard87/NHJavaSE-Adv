package com.acme.records;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import com.acme.records.jdbc.ModelJdbc;
import com.acme.records.jdbc.ModelJdbcDax;

public class AppLoad {
	public static void main(String[] args) {
		List<Record> records = new ArrayList<Record>();
		try {
			FileInputStream fin = new FileInputStream("composers.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fin,
					"UTF16"));
			while (br.ready()) {
				String name = br.readLine();
				name = name.trim();
				String userId = Normalizer.normalize(name, Normalizer.Form.NFD)
						.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
				if (!userId.equals(name)) {
					System.out.println(name + " --> " + userId);
				}
				userId = userId.toLowerCase().replace(" ", ".");
				records.add(new BeanRecord(userId + "@acme.com", name, userId,
						null));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("model in jdbc ...");
		long t1 = System.nanoTime();
		ModelJdbc model = null;
		try {
			model = new ModelJdbc(ModelJdbcDax.getConnection());
			for (Record record : records) {
				model.putRecord(record);
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
	}
}
