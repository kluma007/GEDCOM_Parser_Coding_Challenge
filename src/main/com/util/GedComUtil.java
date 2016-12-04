/**
 * 
 */
package main.com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is an Utility class which will have common utility functions
 * 
 * @author Uma KL
 *
 */
public class GedComUtil {
	
	/**
	 * This method is returns true if the given string 
	 * can be used as valid XML tag else returns false.
	 * 
	 * @param tagName
	 * @return boolean
	 */
	public static boolean isValidTag(String tagName) {
		// TODO Auto-generated method stub
		int strLength = tagName != null ? tagName.trim().length() : 0;
		return (tagName != null && strLength>0 && (strLength == 3 || strLength == 4));
	}

	/**
	 * This method is returns true if the given string
	 * can be used as valid XML attribute for a tag else returns false.
	 * 
	 * @param attributeName
	 * @return boolean
	 */
	public static boolean isValidAttribute(String attributeName) {
		// TODO Auto-generated method stub
		return (attributeName != null && attributeName.trim().length()>0);
	}
	
	/**
	 * This method processes the given string
	 * and converts it into List<String> which will be used to 
	 * create XML nodes.
	 * 
	 * @param string
	 * @return List<String>
	 */
	public static List<String> parserString(String string)
	{
		List<String> lstInputTokens = new ArrayList<String>();
		String[] parts = string.split(" ");
		for(String str : parts)
		{
			str = str.trim();
			if(str != null && str.length()>0)
			{
				if(lstInputTokens.size() >= 3)
				{
					String temp = lstInputTokens.get(2);
					temp = temp + " " + str;
					lstInputTokens.set(2, temp);
				}
				else
				{
					lstInputTokens.add(str);
				}
			}
		}
		return lstInputTokens;
	}
	
	/**
	 * This method reads the first character of the string
	 * and returns the level.
	 * 
	 * @param string
	 * @return int
	 */
	public static int getLevel(String string)
	{
		return (Integer.parseInt(string.split(" ")[0]));
	}
	
	/**
	 * This method reads the input file and converts it into string
	 * 
	 * @param inputFile
	 * @return String
	 */
	public static String convertToString(File inputFile)
	{
		StringBuilder builder = new StringBuilder();
	    BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(inputFile));
		    String line;
		    while ((line = in.readLine()) != null) 
			{
		    	line=line.trim();
		    	if(line != null && line.trim().length()>0)
		    	{
		    		builder.append(line);
		    		builder.append("\n");
		    	}
			}
		    in.close();
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return builder.toString();
	}
	
}
