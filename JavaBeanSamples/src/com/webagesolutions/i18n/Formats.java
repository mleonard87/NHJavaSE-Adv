package com.webagesolutions.i18n;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Formats
{
  public static void main(String[] args)
  {
    Locale def = Locale.getDefault();
    Locale fr = new Locale("fr", "FR");
    Locale de = new Locale("de", "DE");
    Locale sv = new Locale("sv", "SE");
    Locale fi = new Locale("fi", "FI");
    Locale[] locales = { fr, de, sv, fi, def };

    Date date = new Date();

    for (Locale locale : locales) {
      System.out.println(locale.getDisplayLanguage(def)
          + " date is: "
          + DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL,
              locale).format(date));
    }
    
    System.out.println("Custom: " + new SimpleDateFormat("yyyy.MM.dd HH.mm.ss.SSS").format(date));
    
    double value = 89993.89;
    for (Locale locale : locales) {
      System.out.println(locale.getDisplayLanguage(def)
          + " value is: "
          + NumberFormat.getCurrencyInstance(locale).format(value));
    }
    
    
    
  }
}
