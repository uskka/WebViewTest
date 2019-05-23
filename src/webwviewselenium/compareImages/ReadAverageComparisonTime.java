package webwviewselenium.compareImages;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadAverageComparisonTime  {

    public static int readValue(String pathToXMLFile) {

        try {
            File xmlFile = new File(pathToXMLFile);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);            
            document.getDocumentElement().normalize();
            
            NodeList nodeList = document.getElementsByTagName("book");
            
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node theNode = nodeList.item(i);
                
                if (theNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element theElement = (Element) theNode;
                    
                    return Integer.parseInt(theElement.getElementsByTagName("averageScanningTime").item(0).getTextContent());
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ReadAverageComparisonTime.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}

