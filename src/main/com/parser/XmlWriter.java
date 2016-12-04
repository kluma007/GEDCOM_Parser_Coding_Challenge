package main.com.parser;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import main.com.model.ChildXmlNode;
import main.com.model.ParentXmlNode;
import main.com.util.GedComConstants;
import main.com.util.GedComUtil;

/**
 * This Class is used to write the data into XML file
 * 
 * @author Uma KL
 *
 */
public class XmlWriter {

	private File inputFile;
	private File outputFile;
	private Map<ParentXmlNode,List<ChildXmlNode>> xmlNodeMap;

	/**
	 * Constructor for XmlWriter
	 * @param inputFile
	 * @param outputFile
	 */
	public XmlWriter(String inputFile, String outputFile) {
		this.inputFile = new File(inputFile);
		this.outputFile = new File(outputFile);
	}
	
	/**
	 * This method calls a Parser to parse the input data
	 * and writes it to the XML file.
	 * @throws ParserConfigurationException
	 */
	public void generateXmlFile() throws ParserConfigurationException
	{
		GedComParser gedComParser = new GedComParser(inputFile);
		xmlNodeMap = gedComParser.generateXmlNodeMap();
		writeToXmlFile(xmlNodeMap);
	}
	
	/**
	 * This method creates XML Document object, loads the XML document object
	 * with the XML Nodes and writes the XML document object to the output file.
	 * 
	 * @param xmlNodeMap
	 * @throws ParserConfigurationException
	 */
	public void writeToXmlFile(Map<ParentXmlNode,List<ChildXmlNode>> xmlNodeMap) throws ParserConfigurationException
	{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = docFactory.newDocumentBuilder();
        Document xmlDocument = builder.newDocument();
        loadXmlDocument(xmlDocument,xmlNodeMap);
        writeToFile(xmlDocument,outputFile);
	}
	
	/**
	 * This method loads the XML document object with the XML Nodes.
	 * 
	 * @param xmlDocument
	 * @param xmlNodeMap
	 */
	private void loadXmlDocument(Document xmlDocument, Map<ParentXmlNode, List<ChildXmlNode>> xmlNodeMap) {
		// TODO Auto-generated method stub
		if(xmlNodeMap != null && xmlNodeMap.entrySet() != null && xmlNodeMap.entrySet().size() > 0 && xmlDocument != null){
			Element rootElement = xmlDocument.createElement(GedComConstants.GEDCOM_TAG_NAME);
			xmlDocument.appendChild(rootElement);
			for (Map.Entry<ParentXmlNode, List<ChildXmlNode>> entry : xmlNodeMap.entrySet()) {
				ParentXmlNode parentXmlNode = entry.getKey();
				if(GedComUtil.isValidTag(parentXmlNode.getTagName()))
				{
	                Element parentElement = xmlDocument.createElement(parentXmlNode.getTagName().toLowerCase());
	                if(GedComUtil.isValidAttribute(parentXmlNode.getAttributeName()))
	                {
	                 parentElement.setAttribute(parentXmlNode.getAttributeName(), parentXmlNode.getAttributeValue());
	                }
	                if(entry.getValue() != null){
		                for (ChildXmlNode childXmlNode : entry.getValue()) {
		                    addChildXmlNodes(parentElement, childXmlNode, xmlDocument);
		                }
	                }
	                rootElement.appendChild(parentElement);
				}
            }
		}
	}

	
	/**
	 * This method is used to add the Child XML Nodes along with recursion
	 * if the child XML Node has children.
	 * 
	 * @param parentElement
	 * @param childXmlNode
	 * @param xmlDocument
	 */
	private void addChildXmlNodes(Element parentElement, ChildXmlNode childXmlNode, Document xmlDocument) {
		// TODO Auto-generated method stub
		if(GedComUtil.isValidTag(childXmlNode.getTagName())){
			Element childElement = xmlDocument.createElement(childXmlNode.getTagName().toLowerCase());
	        childElement.setTextContent(childXmlNode.getTagValue());
	        if(GedComUtil.isValidAttribute(childXmlNode.getAttributeName())){
	          childElement.setAttribute(childXmlNode.getAttributeName(), childXmlNode.getAttributeValue());
	        }
	        if (childXmlNode.hasChildren()) {
	            for (ChildXmlNode secondLevelChild : childXmlNode.getChildNodes()) {
	            	addChildXmlNodes(childElement, secondLevelChild, xmlDocument);
	            }
	        }
	        parentElement.appendChild(childElement);
		}
	}

	/**
	 * This method writes the XML document object to the output file.
	 * 
	 * @param xmlDocument
	 * @param outputFile
	 */
	private void writeToFile(Document xmlDocument, File outputFile) {
		// TODO Auto-generated method stub
		if (xmlDocument == null || outputFile == null) {
            throw new IllegalArgumentException("XmlDocument or the Output file is null.");
        }
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(xmlDocument);
            StreamResult result = new StreamResult(outputFile);
            transformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
	}
	
}
