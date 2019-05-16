/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webwviewselenium;

import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import static webwviewselenium.ConsoleHandler.Console;

public class Main {

    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {

//Console();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();

            }
        });

    }

}
