package com.webagesolutions.i18n;

import java.util.Locale;

public class Locales
{
  public static void main(String[] args)
  {
    Locale[] locales = Locale.getAvailableLocales();

    for (Locale l : locales) {
      System.out.print(l.getLanguage());
      if (!"".equals(l.getCountry())) {
        System.out.print("_" + l.getCountry());
      }
      System.out.println(": " + l.getDisplayName());
    }

    System.out.println(Locale.getDefault().getDisplayName());

    Locale def = Locale.getDefault();
    Locale français = new Locale("fr", "FR");
    Locale de = new Locale("de", "DE");
    Locale sv = new Locale("sv", "SE");

    for (Locale l : new Locale[] { français, de, sv }) {
      Locale.setDefault(l);
      System.out.println("In " + l.getDisplayCountry(def));
      System.out.println("Country: " + def.getDisplayCountry());
      System.out.println("Language: " + def.getDisplayLanguage());
    }
    
    System.out.println("file.encoding: " + System.getProperty("file.encoding"));
  }
}
