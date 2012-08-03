package com.mycom.dom;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMNodeCount {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileName = args[0];
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(new File(fileName));
			Node docNode = doc.getDocumentElement();
			int count = countElements(docNode);
			//System.out.println("Success!!");
			writeXMLFile(doc, fileName);
			
			System.out.printf("Document %s contained %d elements.  \n", fileName, count );
			
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
	
	private static void writeXMLFile(Document doc, String filename){
		//Prepare the DOM document for writing
		Source source = new  DOMSource(doc);
		
		//Prepare the output file
		File file = new File(filename);
		Result result = new StreamResult(file);
		
		//Write the DOM document to the file
		try {
			Transformer xformer = TransformerFactory.newInstance().newTransformer();
			xformer.transform(source, result);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static int countElements(Node node) {
		if(node.getNodeType() != Node.ELEMENT_NODE){
		// TODO Auto-generated method stub
		return 0;
		}
		int thisCount =1;
		NodeList children = node.getChildNodes();
		for (int i=0; i<children.getLength();i++){
			thisCount = thisCount + countElements(children.item(i));
		}
		return thisCount;
	}
	
	

}
