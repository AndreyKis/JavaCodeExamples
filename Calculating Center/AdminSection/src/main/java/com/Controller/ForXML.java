package com.Controller;
/**
 * Created by User on 28.03.2016.
 */

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class ForXML {

    //for tests
    public static void main(String[] args)
    {
        modifyXML("", "", "");
    }

    public static void modifyXML(String newIP, String userName, String userPass)
    {
        try {
            String filepath = "src/main/resources/META-INF/persistenceHelper";
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);

            // Get the root element
            Node persistence = doc.getFirstChild();

            // update urlProperty attribute
            // Get the url element by tag name directly
            Node url = doc.getElementsByTagName("property").item(0);

            NamedNodeMap urlAttributes = url.getAttributes();
            Node urlName = urlAttributes.getNamedItem("value");
            urlName.setTextContent("jdbc:mysql://" + newIP + "/dbName");

            // update userName attribute
            // Get the userName element by tag name directly
            Node userNameProp = doc.getElementsByTagName("property").item(2);

            NamedNodeMap userNameAttributes = userNameProp.getAttributes();
            Node userNameAttr = userNameAttributes.getNamedItem("value");
            userNameAttr.setTextContent(userName);

            // update userName attribute
            // Get the userName element by tag name directly
            Node userPassProp = doc.getElementsByTagName("property").item(3);

            NamedNodeMap userPassAttributes = userPassProp.getAttributes();
            Node userPassAttr = userPassAttributes.getNamedItem("value");
            userPassAttr.setTextContent(userPass);


            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath));
            transformer.transform(source, result);

            System.out.println("Done");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (SAXException sae) {
            sae.printStackTrace();
        }
    }

    public static String[] readXML()
    {
        String[] values = new String[3];
        try {
            //"C:\Users\User\Desktop\Военка\Project\NewsMonitoring\src\main\resources\META-INF\persistenceHelper"
            String filepath = "src/main/resources/META-INF/persistenceHelper";
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);

            Node urlProp = doc.getElementsByTagName("property").item(0);
            NamedNodeMap urlAttributes = urlProp.getAttributes();
            Node urlName = urlAttributes.getNamedItem("value");
            values[0] = urlName.getTextContent();

            Node userNameProp = doc.getElementsByTagName("property").item(2);
            NamedNodeMap userNameAttributes = userNameProp.getAttributes();
            Node userName = userNameAttributes.getNamedItem("value");
            values[1] = userName.getTextContent();

            Node userPassProp = doc.getElementsByTagName("property").item(3);
            NamedNodeMap userPassAttributes = userPassProp.getAttributes();
            Node userPass = userPassAttributes.getNamedItem("value");
            values[2] = userPass.getTextContent();

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (SAXException sae) {
            sae.printStackTrace();
        }
        return values;
    }
}
