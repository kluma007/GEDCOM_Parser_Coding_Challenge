package test.com;

import static org.junit.Assert.*;

import org.junit.Test;
import org.omg.PortableInterceptor.SUCCESSFUL;

import main.com.GedComLauncher;

/**
 * This is a test class to test GedComLauncher
 * 
 * 
 * @author Uma KL
 *
 */

public class GedComLauncherTest {

	/**
	 * Test Case with Input File.
	 * @throws Exception
	 */
	@Test
	public void testGedComLauncher() throws Exception 
	{
		String[] args = new String[1];
		args[0] = "src/test/com/sample.txt";
		GedComLauncher.main(args);
	}
	
	/**
	 * Test case without Input file
	 * @throws Exception
	 */
	@Test
	public void testGedComLauncherWithoutInputFile() throws Exception 
	{
		String[] args = new String[1];
		args[0] = "";
		GedComLauncher.main(args);
	}
	
	/**
	 * Test case with Input file and Incorrect Output file
	 * @throws Exception
	 */
	@Test
	public void testGedComLauncherWithIncorrectOutputFile() throws Exception 
	{
		String[] args = new String[2];
		args[0] = "src/test/com/sample.txt";
		args[1] = "";
		GedComLauncher.main(args);
	}
	
	/**
	 * Test case with correct Input file and correct Output file
	 * @throws Exception
	 */
	@Test
	public void testGedComLauncherWithCorrectArguments() throws Exception 
	{
		String[] args = new String[2];
		args[0] = "src/test/com/sample.txt";
		args[1] = "src/test/com/GEDCOM_Parser_Output.xml";
		GedComLauncher.main(args);
	}
	
	

}
