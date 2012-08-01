package com.webagesolutions.domain.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.webagesolutions.domain.Money;

public class MoneyTest
{
  @Test
  public void testCompareTo()
  {
    Money ten = new Money(10, 0, "$");
    Money hundred = new Money(100, 0, "$");
    assertTrue(ten.compareTo(hundred) < 0);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testCompareTo_DifferentCurrencies()
  {
    Money australia = new Money(100, 0, "$");
    Money british = new Money(100, 0, "£");
    australia.compareTo(british);
  }

  @Test
  public void testEqualsObject()
  {
    Money money = new Money(100, 0, "$");
    Money other = new Money(100, 0, "$");
    assertTrue("The objects should be equal", money.equals(other));
  }
}
