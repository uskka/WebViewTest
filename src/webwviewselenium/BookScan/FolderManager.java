/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webwviewselenium.BookScan;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import webwviewselenium.XMLHendler;

public class FolderManager {

    public static String PathToXmlConfig() throws URISyntaxException {

        String PathToThisClass = new File(XMLHendler.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();

        return PathToThisClass.substring(0, PathToThisClass.length() - 13) + "XMLdb/Config.xml";

    }

    public static String PathToScreenShotDB() throws URISyntaxException {

        String PathToThisClass = new File(XMLHendler.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();

        return PathToThisClass.substring(0, PathToThisClass.length() - 13) + "ScanDB";

    }

    public static void NewScanFolderSetup(String BookName, String BookID, String Date) throws URISyntaxException, ParserConfigurationException, TransformerConfigurationException, TransformerException {

        File folder = new File(PathToScreenShotDB());
        File[] listOfFiles = folder.listFiles();
        List<Integer> FolderNumbers = new ArrayList<Integer>();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isDirectory()) {

                FolderNumbers.add(Integer.parseInt(listOfFiles[i].getName()));
            }
        }

        if (listOfFiles.length == 0) {
            new File(PathToScreenShotDB() + "/1").mkdirs();
        } else {

            Collections.sort(FolderNumbers);

            int MaxNumberFromFolderNames = FolderNumbers.get(FolderNumbers.size() - 1);
            MaxNumberFromFolderNames++;
            new File(PathToScreenShotDB() + "/" + MaxNumberFromFolderNames).mkdirs();
        }
        //XML INFO CREATION
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("ScanInfo");
        doc.appendChild(rootElement);

        // staff elements
        Element staff = doc.createElement("Book");
        rootElement.appendChild(staff);

        // set attribute to staff element
        Attr attr = doc.createAttribute("Name");
        attr.setValue(BookName);
        staff.setAttributeNode(attr);

        // shorten way
        // staff.setAttribute("id", "1");
        // firstname elements
        Element firstname = doc.createElement("ID");
        firstname.appendChild(doc.createTextNode(BookID));
        staff.appendChild(firstname);

        // lastname elements
        Element lastname = doc.createElement("Date");
        lastname.appendChild(doc.createTextNode(Date));
        staff.appendChild(lastname);

        // write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);

        if (listOfFiles.length == 0) {
            int MaxNumberFromFolderNames = 1;
            StreamResult result = new StreamResult(new File(PathToScreenShotDB() + "/" + MaxNumberFromFolderNames + "/info.xml"));
            transformer.transform(source, result);
        } else {
            Collections.sort(FolderNumbers);

            int MaxNumberFromFolderNames = FolderNumbers.get(FolderNumbers.size()  -1)+1;
            StreamResult result = new StreamResult(new File(PathToScreenShotDB() + "/" + MaxNumberFromFolderNames + "/info.xml"));
            transformer.transform(source, result);
        }

    }

}
