package test.com.parser;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import main.com.model.ChildXmlNode;
import main.com.model.ParentXmlNode;
import main.com.parser.GedComParser;

/**
 * This is the test class for GedComParser
 * 
 * @author Uma KL
 *
 */

public class GedComParserTest {
	GedComParser gedComParser = null;
	@Before
	public void setUp() throws Exception 
	{
		File inputFile = new File("src/test/com/sample.txt");
	    gedComParser = new GedComParser(inputFile);
	}

	/**
	 * test case to check if the object is created
	 */
	@Test
	public void testGedComParserObject() 
	{
		assertNotNull(gedComParser);
	}
	
	/**
	 * test case for generateXmlNodeMap method
	 */
	@Test
	public void testGenerateXmlNodeMap()
	{
		Map<ParentXmlNode,List<ChildXmlNode>> xmlNodeMap = gedComParser.generateXmlNodeMap();
		for (Map.Entry<ParentXmlNode, List<ChildXmlNode>> entry : xmlNodeMap.entrySet()){
			ParentXmlNode parentXMLNode = entry.getKey();
			List<ChildXmlNode> childXMLNode = entry.getValue();
			assertTrue(parentXMLNode.getTagName().equals("INDI"));
			assertNull(parentXMLNode.getTagValue());
			assertTrue(parentXMLNode.getAttributeName().equals("id"));
			assertTrue(parentXMLNode.getAttributeValue().equals("@I1@"));
			assertTrue(childXMLNode.size() == 2);
			assertTrue(childXMLNode.get(0).getTagName().equals("NAME"));
			assertNull(childXMLNode.get(0).getTagValue());
			assertTrue(childXMLNode.get(0).getAttributeName().equals("value"));
			assertTrue(childXMLNode.get(0).getAttributeValue().equals("James Gordon /Buck/"));
			assertTrue(childXMLNode.get(0).getChildNodes().get(0).getTagName().equals("SURN"));
			assertTrue(childXMLNode.get(0).getChildNodes().get(0).getTagValue().equals("Buck"));
			assertNull(childXMLNode.get(0).getChildNodes().get(0).getAttributeName());
			assertNull(childXMLNode.get(0).getChildNodes().get(0).getAttributeValue());
			assertTrue(childXMLNode.get(0).getChildNodes().get(1).getTagName().equals("GIVN"));
			assertTrue(childXMLNode.get(0).getChildNodes().get(1).getTagValue().equals("James Gordon"));
			assertNull(childXMLNode.get(0).getChildNodes().get(1).getAttributeName());
			assertNull(childXMLNode.get(0).getChildNodes().get(1).getAttributeValue());
			assertTrue(childXMLNode.get(1).getTagName().equals("SEX"));
			assertTrue(childXMLNode.get(1).getTagValue().equals("M"));
			assertNull(childXMLNode.get(1).getAttributeName());
			assertNull(childXMLNode.get(1).getAttributeValue());
		}
	}
	
	

}
