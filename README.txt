HOW TO RUN THE PROJECT :
=======================

1. Clone the project.
2. Install maven and set the suitable environment variables(if maven is not installed).
3. Go to the path of the project in command prompt and run "mvn clean".
4. Then, run "mvn install". This command will run all the junit test cases and once the build is successful,
   a jar "GEDCOM-0.0.1-SNAPSHOT.jar" wil be created in the target folder.
5. Place the input file in GEDCOM format in the project path and pass the name of the input file as arguments to the below command:
      mvn exec:java -Dexec.mainClass="main.com.GedComLauncher" -Dexec.args="sample.txt"
   Output XML file "GEDCOM_Parser_Output.xml" will be created in the project path.
   
DESIGN :
======

1. MODEL :- 
    The model classes for xml node elements "ParentXMLNode.java" and "ChildXMLNode.java" are created in "main.com.model" package.

2. PARSER & XMLWRITER:-
    The classes "GedComParser.java" and "XmlWriter.java" are written in "main.com.parser" package. 
    a) "GedComParser.java" is used to parse the input data and convert it into Map<ParentXmlNode,List<ChildXmlNode>>
        which can be used to load the XML Document object.
    b) "XmlWriter.java" is used to write the data into XML file.
 
3. UTILITY:-
   The classes "GedComConstants.java" and "GedComUtil.java" are written in "main.com.util" package.
   a) "GedComConstants.java" consists of common reusable constants.
   b) "GedComUtil.java" is an Utility class which will have common utility functions.
   
   
DEVELOPMENT :
===========

1. Java version used : jdk1.8.0_111 
2. Eclipse IDE was used for development.
3. Test-Driven Development(TDD) approach was followed.
4. Junit 4.12 was used.
5. Test-Driven Development was done by testing with different variation of sample files and developing the code to pass
   all failing cases.
    
   