package webwviewselenium;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import jdk.internal.org.xml.sax.SAXException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.NamedNodeMap;

public class XMLHendler {

    public static int NuberOfSections(String book) throws FileNotFoundException, ParseException {
        JSONParser parser = new JSONParser();

        switch (book) {
            case "Biology": {
                try {
                    Object object = parser.parse(new FileReader("/Users/stefanmac/Documents/json.json"));
                    JSONObject jsonObject = (JSONObject) object;
                    int size = jsonObject.size() / 2;
                    return size;

                } catch (IOException ex) {
                    Logger.getLogger(XMLHendler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

        }

        return 0;
    }

    public static void JsonRead() { // file read

        JSONParser parser = new JSONParser();
        try {
            Object object = parser.parse(new FileReader("/Users/stefanmac/Documents/json.json"));

            //convert Object to JSONObject
            JSONObject jsonObject = (JSONObject) object;

            //Reading the String
//            String name = (String) jsonObject.get("Name");
//            Long age = (Long) jsonObject.get("Age");
            //Reading the array
            JSONArray countries = (JSONArray) jsonObject.get("1.0"); //array i nazwa

            //Printing all the values
            System.out.println("Array:");

            for (int a = 0; a < countries.size(); a++) {

                System.out.println(countries.get(a)); //sciaganie jednego stringa z indexu 1
            }

//            for(Object country : countries)
//            {
//                System.out.println(country.toString());
//            }
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String PathToXmlConfig() throws URISyntaxException {

        String PathToThisClass = new File(XMLHendler.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
        System.out.print(PathToThisClass);
        return PathToThisClass.substring(0, PathToThisClass.length() - 13) + "XMLdb/Config.xml";

    }

    public static String GetDriverPath(String DriverType) { //input "Chrome" or later firefox safari etc
        //if there is no driver with this type in config.xml returns null

        try {

            File fXmlFile = new File(PathToXmlConfig());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = (Document) dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("WebDriver");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    String DriverTypeTemp = eElement.getAttribute("DriverType");
                    if (DriverTypeTemp.equals(DriverType)) {
                        return eElement.getElementsByTagName("Path").item(0).getTextContent();

                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static void changeDriverPath(String DriverType, String path) throws TransformerConfigurationException, TransformerException, org.xml.sax.SAXException, URISyntaxException {

        try {
            String filepath = PathToXmlConfig();
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);

            // Get the staff element , it may not working if tag has spaces, or
            // whatever weird characters in front...it's better to use
            // getElementsByTagName() to get it directly.
            // Node staff = company.getFirstChild();
            // Get the staff element by tag name directly
            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("WebDriver");

            // loop the staff child node
            for (int i = 0; i < list.getLength(); i++) {

                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) node;
                    String DriverTypeTemp = eElement.getAttribute("DriverType");
                    if (DriverTypeTemp.equals(DriverType)) {

                        eElement.getElementsByTagName("Path").item(0).setTextContent(path);
                    }

                }
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath));
            transformer.transform(source, result);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
