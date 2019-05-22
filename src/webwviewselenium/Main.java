/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webwviewselenium;

import webwviewselenium.GUI.MainFrame;
import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import static webwviewselenium.XMLHendler.SetDriversPathToDefult;

public class Main {

    public static void main(String[] args) {
        
        try { // sets drirver path in cofing for the one inside app folders if its null in config.xml
            SetDriversPathToDefult();
        } catch (TransformerException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//Console();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();

            }
        });

    }

}
