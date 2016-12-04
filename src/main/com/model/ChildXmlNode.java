package main.com.model;

import java.util.List;

/**
 * This class is the model class for child xml node.
 * 
 * @author Uma KL
 *
 */
public class ChildXmlNode {
	private String tagName;
	private String tagValue;
	private String attributeName;
	private String attributeValue;
	
	private List<ChildXmlNode> childNodes;

	/**
	 * @return the tagName
	 */
	public String getTagName() {
		return tagName;
	}

	/**
	 * @param tagName the tagName to set
	 */
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	/**
	 * @return the tagValue
	 */
	public String getTagValue() {
		return tagValue;
	}

	/**
	 * @param tagValue the tagValue to set
	 */
	public void setTagValue(String tagValue) {
		this.tagValue = tagValue;
	}

	/**
	 * @return the attributeName
	 */
	public String getAttributeName() {
		return attributeName;
	}

	/**
	 * @param attributeName the attributeName to set
	 */
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	/**
	 * @return the attributeValue
	 */
	public String getAttributeValue() {
		return attributeValue;
	}

	/**
	 * @param attributeValue the attributeValue to set
	 */
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	/**
	 * @return the childNodes
	 */
	public List<ChildXmlNode> getChildNodes() {
		return childNodes;
	}

	/**
	 * @param childNodes the childNodes to set
	 */
	public void setChildNodes(List<ChildXmlNode> childNodes) {
		this.childNodes = childNodes;
	}
	
	/**
	 * @return
	 */
	public boolean hasChildren() {
        return childNodes != null && childNodes.size() > 0;
    }
	
}
