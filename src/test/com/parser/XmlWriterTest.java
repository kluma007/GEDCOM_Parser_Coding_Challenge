package test.com.parser;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;

import main.com.model.ChildXmlNode;
import main.com.model.ParentXmlNode;
import main.com.parser.XmlWriter;

/**
 * This class is used to test XMLWriter Class.
 * 
 * @author Uma KL
 *
 */

public class XmlWriterTest {
    
	XmlWriter xmlWriter = null;
	
	@Before
	public void setUp() throws Exception 
	{
		String inputFile = "src/test/com/sample.txt";
		String outputFile = "src/test/com/GEDCOM_Parser_Output.xml";
	    xmlWriter = new XmlWriter(inputFile, outputFile);
	}

	/**
	 * test case to test the object creation
	 */
	@Test
	public void testXmlWriterObject()
	{
		assertNotNull(xmlWriter);
	}
	
	/**
	 * test case to test generateXmlFile method
	 * @throws ParserConfigurationException 
	 */
	@Test
	public void testGenerateXmlFile() throws ParserConfigurationException 
	{
		xmlWriter.generateXmlFile();
	}

}
