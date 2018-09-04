/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webwviewselenium;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static webwviewselenium.WebwviewSelenium.ClearTestFolder;
import static webwviewselenium.WebwviewSelenium.ClearTemplate;
import static webwviewselenium.WebwviewSelenium.NewLocalScan;
import static webwviewselenium.WebwviewSelenium.ScanTemplate;
import static webwviewselenium.JsonHendler.*;
import static webwviewselenium.WebwviewSelenium.Compare;
import static webwviewselenium.WebwviewSelenium.JavaWalkBufferedImageTest1;

public class ConsoleHandler {

    public static void Console() throws FileNotFoundException, ParseException, IOException {
        System.setProperty("webdriver.chrome.driver", "/Users/stefanmac/Downloads/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("window-size=1024,1500");
        WebDriver driver = new ChromeDriver(chromeOptions);

        Scanner scan = new Scanner(System.in);

        
       

        while (true) {
            System.out.println("scan , clear , close , comp");
            String input = scan.nextLine();
            switch (input) {

                case "comp":
                JavaWalkBufferedImageTest1();
                break;
                    
                case "scan":
                    System.out.println("template or new");
                    String input2 = scan.nextLine();
                
                
                    switch (input2) {
                        case "template":
                            System.out.println("which book?(demo contains only biology type y and press enter or leave if u are not happy with the output I am doing my best)");
                            String input3 = scan.nextLine();

                            switch (input3) {
                                default:
                                    System.out.println("Wrong input");
                                    break;
                                    
                                case "y":
                                    String book = "Biology";

                                    System.out.println("This book has " + NuberOfSections(book) + " sections available for scan");
                                    System.out.println("Scan started");
                                    ScanTemplate(driver);
                                    System.out.println("Biology Template Scan Complete");
                                    continue;

                            }

                        case "new":
                        System.out.println("which book?(demo contains only biology type y and press enter or leave if u are not happy with the output I am doing my best)");
                        String input31 = scan.nextLine();    
                        switch (input31) {
                                default:
                                    System.out.println("Wrong input");
                                    break;
                                    
                                case "y":
                                    String book = "Biology";

                                    System.out.println("This book has " + NuberOfSections(book) + " sections available for scan");
                                    System.out.println("Scan started");
                                    NewLocalScan(driver);
                                    System.out.println("Biology Test Scan Complete");
                                    continue;

                            }

                            

                        default:
                            System.out.println("Wrong input");
                            break;

                    }



                case "close":
                    System.exit(0);
                    break;

                case "NewLocalScan":
                    NewLocalScan(driver);
                    break;


                    
                    case"clear":
                    System.out.println("Clear \"template\" or \"test\" folder?");
                    String input5 = scan.nextLine();
                    switch(input5){
                    
                        case("template"):
                        ClearTemplate();
                        System.out.println("Template folder is now empty");
                        break;
                        
                        case("test"):
                        ClearTestFolder();   
                        System.out.println("Test folder is now empty");
                        break;
                        
                        default:
                        System.out.println("Wrong input");
                        break;
                    }
                    default:
                    System.out.println("Wrong input");
                    
                    

            }
        }
    }
}
