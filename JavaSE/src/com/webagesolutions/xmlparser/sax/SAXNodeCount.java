package com.webagesolutions.xmlparser.sax;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXNodeCount
{
  long startTime, endTime;
  
  private class DocHandler extends DefaultHandler
  {
    int elementCount;
//    Locator locator;

    public DocHandler()
    {
      super();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts)
    {
      elementCount++;
      
//      for(int i = 0 ; i < atts.getLength(); i++) {
//        System.out.println("(" + locator.getLineNumber() + ":" + locator.getColumnNumber() + ") Found attribute: " + atts.getQName(i) + "\t" + atts.getLocalName(i) + "\t\t" + atts.getValue(i) + ".");
//      }
    }

    @Override
    public void endDocument()
    {
      System.out.printf("Document contained %d elements.\n", elementCount);
    }
    
//    @Override
//    public void setDocumentLocator(Locator locator)
//    {
//      this.locator = locator;
//    }
  }
  
  public SAXNodeCount(String fileName)
  {
    System.out.println("\nSAX: " + fileName);
    try {
      startTime = System.currentTimeMillis();
      
      SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
      parser.parse(new File(fileName), new DocHandler());
      
      endTime = System.currentTimeMillis();
      System.out.printf("Elapsed time: %dms \n", endTime - startTime);
    } catch (ParserConfigurationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (SAXException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static void main(String[] args)
  {
    new SAXNodeCount(args[0]);
  }
}
