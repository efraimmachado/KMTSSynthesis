package xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLParser {

	private Document document;
	
	public XMLParser(String fileFullPath) {
		super();
		document = transformFileInDocument(fileFullPath);
	}

	private Document transformFileInDocument(String fileFullPath) 
	{
		if (fileFullPath != null)
		{
			try
			{
				DocumentBuilderFactory factory;
				DocumentBuilder builder;
				File inputFile;
				
				factory = DocumentBuilderFactory.newInstance();
				builder = factory.newDocumentBuilder();
				inputFile = new File(fileFullPath);
				
				try {
					Document result = builder.parse(inputFile);
					result.getDocumentElement().normalize();
					return result;
				} catch (SAXException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
}
