package com.webagesolutions.domain.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.webagesolutions.domain.Money;

public class MoneyStringTests
{
  @Test
  public void testToString()
  {
    assertEquals("$7.32", new Money(7, 32, "$").toString());
  }
  
  @Test
  public void testToString_SingleDigitCents()
  {
    assertEquals("$7.02", new Money(7, 2, "$").toString());
  }
  
  @Test
  public void testToString_ZeroCents()
  {
    assertEquals("$7", new Money(7, 0, "$").toString());
  }
}
