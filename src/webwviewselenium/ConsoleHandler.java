/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webwviewselenium;

import java.util.Scanner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static webwviewselenium.WebwviewSelenium.ClearScanFolder;
import static webwviewselenium.WebwviewSelenium.ClearTemplate;
import static webwviewselenium.WebwviewSelenium.NewLocalScan;
import static webwviewselenium.WebwviewSelenium.ScanTemplate;
import webwviewselenium.WebwviewSelenium;

public class ConsoleHandler {

    public static void Console() {
        System.setProperty("webdriver.chrome.driver", "/Users/stefanmac/NetBeansProjects/WebwviewSelenium/build/classes/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("window-size=1024,1500");
        WebDriver driver = new ChromeDriver(chromeOptions);

        Scanner scan = new Scanner(System.in);

        System.out.println("scan , clear , close");
        String input = scan.nextLine();

        while (true) {
            switch (input) {

                case "scan":
                    System.out.println("template or new");
                    String input2 = scan.nextLine();

                    switch (input2) {
                        case "template":
                            System.out.println("which book?(demo contains only biology type y and press enter or leave if u are not happy with the output I am doing my best)");
                            String input3 = scan.nextLine();

                            switch (input3) {
                                case "y":
                                    NuberOfSections();
                                    System.out.println("");
                                    String input3 = scan.nextLine();
                                    switch (input4) {

                                    }
                                    break;

                                default:
                                    System.out.println("Wrong input");
                            }
                            break;

                        case "new":

                            break;

                        default:
                            System.out.println("Wrong input");

                    }

                    break;

                case "ClearTemplate":
                    ClearTemplate();

                    break;

                case "close":
                    System.exit(0);
                    break;

                case "NewLocalScan":
                    NewLocalScan(driver);
                    break;

                case "ClearScanFolder":
                    ClearScanFolder();
                    break;

                default:
                    System.out.println("Wrong input");
                    break;

            }

        }
    }
}
