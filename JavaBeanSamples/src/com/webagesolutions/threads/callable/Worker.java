package com.webagesolutions.threads.callable;

import java.util.Random;
import java.util.concurrent.Callable;

public class Worker implements Callable<String>
{
  private String name;
  private String phrase;
  private String[][] words = { { "green", "fuzzy", "dead", "tired" },
      { "weasel", "gerbil", "cat", "mouse" },
      { "run", "eat", "sleep", "hide" },
      { "silently", "quickly", "carefully", "boldly" } };

  public Worker(String name)
  {
    this.name = name;
  }

  @Override
  public String call()
  {
    phrase = "I saw a";
    Random random = new Random();
    for (int i = 0; i < 4;) {
      int r = random.nextInt(100);
      if (r > 66) {
        System.out.println(this.name + " >yeild n:" + i);
        Thread.yield();
        System.out.println(this.name + " <yeild n:" + i);
      } else if (r > 33 ) {
        try {
          System.out.println(this.name + " >sleep n:" + i);
          Thread.sleep(random.nextInt(1000));
          System.out.println(this.name + " <sleep n:" + i);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }  else {
        System.out.println(this.name + " >work  n:" + i);
        phrase += " " + words[i][random.nextInt(4)];
        System.out.println(this.name + " <work  n:" + i);
        i++;
      }
    }
    System.out.println(this.name + " done!");
    return phrase;
  }
}
