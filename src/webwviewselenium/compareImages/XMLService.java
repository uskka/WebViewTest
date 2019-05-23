package webwviewselenium.compareImages;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLService {
    
    public static int readValueOfAverageComparisionTime(String pathToXMLFile, String bookId) {

        try {
            File xmlFile = new File(pathToXMLFile);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);            
            document.getDocumentElement().normalize();
            
            NodeList nodeList = document.getElementsByTagName("Book");
            
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node theNode = nodeList.item(i);
                
                if (theNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element theElement = (Element) theNode;
                    
                    if (theElement.getAttribute("Name").equals(bookId)) {
                        return Integer.parseInt(theElement.getElementsByTagName("AverageScanTime").item(0).getTextContent());
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ReadAverageComparisonTime.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public static void saveValueOfAverageComparisonTime(String pathToXMLFile, String bookId, String newAverageComparisonTime) {
        try {
            File xmlFile = new File(pathToXMLFile);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("Book");
            
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node theNode = nodeList.item(i);
                
                if (theNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element theElement = (Element) theNode;
                    
                    if (theElement.getAttribute("Name").equals(bookId)) {
                        theElement.getElementsByTagName("AverageScanTime").item(0).setTextContent(newAverageComparisonTime);
                        
                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
                        Transformer transformer = transformerFactory.newTransformer();
                        DOMSource source = new DOMSource(document);
                        StreamResult result = new StreamResult(new File(pathToXMLFile));
                        transformer.transform(source, result);
                    }
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(ReadAverageComparisonTime.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
