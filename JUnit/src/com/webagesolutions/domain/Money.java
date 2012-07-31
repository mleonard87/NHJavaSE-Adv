package com.webagesolutions.domain;

public class Money implements Comparable<Money>
{
  int dollars, cents;
  String currencySymbol;
  
  public Money(int dollars, int cents, String currenySymbol)
  {
    this.dollars = dollars;
    this.cents = cents;
    this.currencySymbol = currenySymbol;
  }
  
  @Override
  public int compareTo(Money other)
  {
    if (!this.currencySymbol.equals(other.currencySymbol)) {
      String message = "Cannot compare " + this.currencySymbol + " to " + other.currencySymbol + ".";
      throw new IllegalArgumentException(message);
    }
    
    int difference = (this.dollars * 100 + this.cents) - (other.dollars * 100 + other.cents);
    return difference;
  }
  
  @Override
  public boolean equals(Object obj)
  {
    if (obj != null && obj instanceof Money) {
      Money other = (Money) obj;
      return this.compareTo(other) == 0;
    }
        
    return false;
  }
  
  @Override
  public String toString()
  {
    String moneyStr = currencySymbol + dollars;
    
    if (cents != 0) {
      moneyStr += "." + ((cents < 10) ? "0" + cents : cents); 
    }
    
    return moneyStr;
  }
}
