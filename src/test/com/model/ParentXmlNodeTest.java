package test.com.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.com.model.ParentXmlNode;

/**
 * This is test class for ParentXmlNode
 * 
 * @author Uma KL
 *
 */

public class ParentXmlNodeTest {

	ParentXmlNode parentXmlNode = null;
	
	@Before
	public void setUp() throws Exception 
	{
		parentXmlNode = new ParentXmlNode();
	}

	
	/**
	 * This is test case for ParenTXmlNode Object Creation
	 */
	@Test
	public void testObjectCreationParentXmlNode() 
	{
		assertNotNull(parentXmlNode);
	}
}
