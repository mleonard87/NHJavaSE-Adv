package com.webagesolutions.xmlparser;

import com.webagesolutions.xmlparser.dom.DOMNodeCount;
import com.webagesolutions.xmlparser.sax.SAXNodeCount;

public class NodeCountRunner
{
  public static void main(String[] args)
  {
    for (String s : args) {
      new DOMNodeCount(s);
      new SAXNodeCount(s);
    }
  }
}
