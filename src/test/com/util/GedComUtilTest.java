package test.com.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.com.util.GedComUtil;
import java.util.List;


/**
 * This is the test class for GedComUtil
 * 
 * @author Uma KL
 *
 */

public class GedComUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * This is a test case for isValidTag method
	 */
	@Test
	public void testIsValidTag() {
		assertFalse(GedComUtil.isValidTag(null));
		assertTrue(GedComUtil.isValidTag("NAME"));
		assertFalse(GedComUtil.isValidTag("NA"));
	}

	
	/**
	 * This is a test case for isValidAttribute method
	 */
	@Test
	public void testIsValidAttribute() {
		assertFalse(GedComUtil.isValidAttribute(null));
		assertTrue(GedComUtil.isValidAttribute("value"));
	}

	/**
	 * This is a test case for parserString method
	 */
	@Test
	public void testParserString() {
		List<String> lstString1 = GedComUtil.parserString("1 NAME James Gordon /Buck/");
		assertTrue(lstString1.size() == 3);
		List<String> lstString2 = GedComUtil.parserString("1 NAME          James Gordon /Buck/");
		assertTrue(lstString2.size() == 3);
	}

	/**
	 * This is a test case for getLevel method
	 */
	@Test
	public void testGetLevel() {
		int level =  GedComUtil.getLevel("1 NAME James Gordon /Buck/");
		assertTrue(level == 1);
		
	}
}
