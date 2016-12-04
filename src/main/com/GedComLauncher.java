/**
 * 
 */
package main.com;

import javax.xml.parsers.ParserConfigurationException;

import main.com.parser.GedComParser;
import main.com.parser.XmlWriter;
import main.com.util.GedComConstants;

/**
 * This class  is the application launcher.
 * @author Uma KL
 *
 */

public class GedComLauncher {

	/**
	 * Main method to launch the application.
	 * 
	 * @param args
	 * @throws ParserConfigurationException 
	 */
	public static void main(String[] args) throws ParserConfigurationException {
		
		XmlWriter xmlWriter = null;
		if(args.length == 0 || args.length > 2)
		{
			System.out.println("Invalid arguments.Kindly Provide 'inputfile' 'outputfile(optional)' as parameters.");
			
		}
		else if (args.length == 1)
		{
			if(validateArgument(args[0]))
			{
				xmlWriter = new XmlWriter(args[0],GedComConstants.OUTPUT_FILE);
				xmlWriter.generateXmlFile();
			}
			else{
				System.out.println("Invalid argument.Kindly provide the input file.");
			}
		}
		else 
		{
			if(validateArgument(args[0]) && validateArgument(args[1]))
			{
				xmlWriter = new XmlWriter(args[0],args[1]);
				xmlWriter.generateXmlFile();
			}
			else{
				System.out.println("Invalid arguments.Kindly Provide 'inputfile' 'outputfile(optional)' as parameters. ");
			}
		}
	}
	
	
	/**
	 * This method returns true if the given argument is valid
	 * else returns false.
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean validateArgument(String str){
		return (str != null && str.trim().length()>0);
	}

}
