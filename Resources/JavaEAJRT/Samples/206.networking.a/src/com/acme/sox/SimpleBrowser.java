package com.acme.sox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class SimpleBrowser {
	public static void main(String[] args) {
		try {
			//URL target = new URL("http://google.com/");
			URL target = new URL("file://localhost/tmp/file.txt");
			System.out.println("target: " + target);
			URLConnection targetConnection = target.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					targetConnection.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
			}
			in.close();
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
}
