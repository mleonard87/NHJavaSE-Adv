package com.acme.i18n;

import java.util.Locale;

public class Locales {
	public static void main(String[] args) {
		Locale[] locales = Locale.getAvailableLocales();
		for (Locale locale : locales) {
			System.out.print(locale.getLanguage());
			if (!"".equals(locale.getCountry()))
				System.out.print("_" + locale.getCountry());
			System.out.println(": " + locale.getDisplayName());
		}
		System.out.println("default: " + Locale.getDefault().getDisplayName());

		Locale def = Locale.getDefault();
		Locale français = new Locale("fr", "FR");
		Locale de = new Locale("de", "DE");
		Locale sv = new Locale("sv", "SE");
		for (Locale locale : new Locale[] { français, de, sv }) {
			Locale.setDefault(locale);
			System.out.println("In " + locale.getDisplayCountry(def));
			System.out.println("country: " + def.getDisplayCountry());
			System.out.println("language: " + def.getDisplayLanguage());
		}
		System.out.println("file.encoding: "
				+ System.getProperty("file.encoding"));
	}
}
