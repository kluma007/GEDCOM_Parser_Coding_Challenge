package main.com.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import main.com.model.ChildXmlNode;
import main.com.model.ParentXmlNode;
import main.com.util.GedComConstants;
import main.com.util.GedComUtil;

/**
 * This class is used to parse the input data
 * and convert it into Map<ParentXmlNode,List<ChildXmlNode>>
 * which can be used to load the XML Document object 
 * 
 * @author Uma KL
 *
 */
public class GedComParser {
	
	private File inputFile;
	private Map<Integer,List<ChildXmlNode>> tempChildListMap = new HashMap<Integer,List<ChildXmlNode>>();

	/**
	 * Constructor for GedComParser
	 * 
	 * @param inputFile
	 */
	public GedComParser(File inputFile)
	{
		this.inputFile = inputFile;
	}
	
	/**
	 * This method parses the input data
	 * and converts it into Map<ParentXmlNode,List<ChildXmlNode>>
	 * 
	 * 
	 * @return xmlNodeMap
	 */
	public Map<ParentXmlNode,List<ChildXmlNode>> generateXmlNodeMap()
	{
		String strInput = GedComUtil.convertToString(inputFile);
		Map<ParentXmlNode,List<ChildXmlNode>> xmlNodeMap = new LinkedHashMap<ParentXmlNode,List<ChildXmlNode>>();
		if(strInput != null && strInput.trim().length()>0)
		{
			String[] rootNodes = strInput.split(GedComConstants.DELIMITER);
			if(rootNodes != null && rootNodes.length > 0)
			{
				for (String node : rootNodes) 
				{
					if(node != null && node.trim().length() > 0)
					{
						String[] nodes = node.split(GedComConstants.NEW_LINE);
						if(nodes != null  && nodes.length > 0)
						{
							ParentXmlNode parentNode = createParentNode(nodes[0]);
							List<ChildXmlNode> childList = createChildNode(nodes,1,nodes.length-1);
							xmlNodeMap.put(parentNode, childList);
						}
					}
				}
		   }
	   }
	   return xmlNodeMap;
   }

	/**
	 * This method recursively creates a List of ChildXmlNode objects
	 * 
	 * @param nodes
	 * @param start
	 * @param end
	 * @return List<ChildXmlNode>
	 */
	private List<ChildXmlNode> createChildNode(String[] nodes,int start,int end) {
		
		if(start>end)
		{
			return null;
		}
		boolean isId = false;
		List<ChildXmlNode> childXmlNodeList = new ArrayList<ChildXmlNode>();
		List<ChildXmlNode> lstSameLevelNodes = null;
		List<ChildXmlNode> lstOtherLevelChildNodes = null;
		ChildXmlNode childNode = new ChildXmlNode();
		List<String> childElements = GedComUtil.parserString(nodes[start]);
		//the below line is written to check if the element is id or tag.
		isId = isID(childElements.get(1));
		if(isId)
		{
			childNode.setTagName(childElements.get(2));
		}
		else
		{
			childNode.setTagName(childElements.get(1));
		}
		
		//no more nodes
		if(start==end)
		{
			if(isId)
			{
				childNode.setAttributeName(GedComConstants.ID_ATTRIBUTE_NAME);
				childNode.setAttributeValue(childElements.get(1));
			}
			else if(childElements.size() == 3)
			{
				
				childNode.setTagValue(childElements.get(2));
			}
		}
		//if the node has children
		else if((hasChildren(nodes[start],nodes[start+1])))
		{
			if(isId)
			{
				childNode.setAttributeName(GedComConstants.ID_ATTRIBUTE_NAME);
				childNode.setAttributeValue(childElements.get(1));
			}
			else if(childElements.size() == 3)
			{
				childNode.setAttributeName(GedComConstants.VALUE_ATTRIBUTE_NAME);
				childNode.setAttributeValue(childElements.get(2));
			}
			
			List<ChildXmlNode> lstHierarchicalChildNodes=createChildNode(nodes, start+1, end);
			if(lstHierarchicalChildNodes != null)
			{
				childNode.setChildNodes(lstHierarchicalChildNodes);
			}
		}
		//if the next node is at the same level
		else if(isAtSameLevel(nodes[start],nodes[start+1]))
		{
			if(isId)
			{
				childNode.setAttributeName(GedComConstants.ID_ATTRIBUTE_NAME);
				childNode.setAttributeValue(childElements.get(1));
			}
			else if(childElements.size() == 3)
			{
				childNode.setTagValue(childElements.get(2));
			}
			lstSameLevelNodes = createChildNode(nodes, start+1, end);
		}
		//if the next node is at other level
		else if(isAtOtherLevel(nodes[start],nodes[start+1])){
			if(isId)
			{
				childNode.setAttributeName(GedComConstants.ID_ATTRIBUTE_NAME);
				childNode.setAttributeValue(childElements.get(1));
			}
			else if(childElements.size() == 3)
			{
				childNode.setTagValue(childElements.get(2));
			}
			lstOtherLevelChildNodes = createChildNode(nodes, start+1, end);
			int level=GedComUtil.getLevel(nodes[start+1]);
			if(lstOtherLevelChildNodes != null)
			{
				tempChildListMap.put(level, lstOtherLevelChildNodes);
			}
			
		}
		childXmlNodeList.add(childNode);
		if(lstSameLevelNodes != null){
			childXmlNodeList.add(lstSameLevelNodes.get(0));
		}
		int level=GedComUtil.getLevel(nodes[start]);
		if(tempChildListMap!=null && tempChildListMap.containsKey(level))
		{
			for(ChildXmlNode tempChildNode : tempChildListMap.get(level)){
				childXmlNodeList.add(tempChildNode);
			}
		}
		return childXmlNodeList;
	}

	/**
	 * This method returns true if the element is id else returns false.
	 * @param string
	 * @return boolean
	 */
	private boolean isID(String string) {
		// TODO Auto-generated method stub
	  return(string != null && string.charAt(0) == GedComConstants.ID_DELIMTER 
				&& string.charAt(string.length()-1) == GedComConstants.ID_DELIMTER);
	}

	/**
	 * This method returns true if the level of current line is greater
	 * than level of next line which means that the node is in other level.
	 * 
	 * @param stringCurr
	 * @param stringNext
	 * @return boolean
	 */
	private boolean isAtOtherLevel(String stringCurr, String stringNext) {
		// TODO Auto-generated method stub
		return (Integer.parseInt(stringCurr.split(" ")[0]) > Integer.parseInt(stringNext.split(" ")[0]));
	}

	/**
	 * This method returns true if the level of current line is equal
	 * level of next line which means that the node is at same level.
	 * 
	 * @param stringCurr
	 * @param stringNext
	 * @return boolean
	 */
	private boolean isAtSameLevel(String stringCurr, String stringNext){
		// TODO Auto-generated method stub
		return (Integer.parseInt(stringCurr.split(" ")[0]) == Integer.parseInt(stringNext.split(" ")[0]));
	}
	
	/**
	 * This method returns true if the level of current line is less
	 * than level of next line which means that the node has children.
	 * 
	 * @param stringCurr
	 * @param stringNext
	 * @return boolean
	 */
	private boolean hasChildren(String stringCurr, String stringNext) {
		// TODO Auto-generated method stub
		return (Integer.parseInt(stringCurr.split(" ")[0]) < Integer.parseInt(stringNext.split(" ")[0]));
			
	}

	/**
	 * This method is used to create the parent node.
	 * 
	 * @param string
	 * @return ParentXmlNode
	 */
	private ParentXmlNode createParentNode(String string) 
	{
		ParentXmlNode parentXmlNode = new ParentXmlNode();
		List<String> tokens = GedComUtil.parserString(string);
		
		if(tokens.size() > 0)
		{
			if(isID(tokens.get(0)))
			{
				parentXmlNode.setAttributeName(GedComConstants.ID_ATTRIBUTE_NAME);
				parentXmlNode.setAttributeValue(tokens.get(0));
				parentXmlNode.setTagName(tokens.get(1));
			}
			else
			{
				parentXmlNode.setTagName(tokens.get(0));
				if(tokens.size() == 2)
				{
					parentXmlNode.setAttributeName(GedComConstants.VALUE_ATTRIBUTE_NAME);
					parentXmlNode.setAttributeValue(tokens.get(1));
				}
			}
		}
		return parentXmlNode;
	}
}
