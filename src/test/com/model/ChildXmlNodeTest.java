package test.com.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;

import main.com.model.ChildXmlNode;

/**
 * This is test class for ChildXmlNode
 * 
 * @author Uma KL
 *
 */

public class ChildXmlNodeTest {

	ChildXmlNode childXmlNode = null;
	@Before
	public void setUp() throws Exception
	{
		childXmlNode = new ChildXmlNode();
	}

	
	/**
	 * This is test case for ChildXmlNode object creation
	 */
	@Test
	public void testObjectCreationChildXmlNode()
	{
		assertNotNull(childXmlNode);
	}
	
	/**
	 * This is test case for hasChildren method
	 */
	@Test
	public void testHasChildren() 
	{
		ChildXmlNode childXmlNode1 = new ChildXmlNode();
		List<ChildXmlNode> lstChildXmlNode = new ArrayList<ChildXmlNode>();
		lstChildXmlNode.add(childXmlNode1);
		childXmlNode.setChildNodes(lstChildXmlNode);
		ChildXmlNode childXmlNode2 = new ChildXmlNode();
		assertTrue(childXmlNode.hasChildren());
		assertFalse(childXmlNode2.hasChildren());
	}

}
