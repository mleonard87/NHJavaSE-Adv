package com.acme.records;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class AppConvert {
	public static void main(String[] args) {
		try {
			FileInputStream fin = new FileInputStream("source.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fin,
					"UTF16"));
			FileOutputStream fos = new FileOutputStream("composers.txt");
			Writer out = new OutputStreamWriter(fos, "UTF16");
			while (br.ready()) {
				String name = br.readLine();
				if (name.contains("(")) {
					name = name.substring(0, name.indexOf('('));
				}
				if (name.contains(",")) {
					name = name.substring(0, name.indexOf(','));
				}
				name = name.trim();
				out.write(name + "\r\n");
			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
