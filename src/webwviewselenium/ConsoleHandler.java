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
    
    public static void Console(){
        System.setProperty("webdriver.chrome.driver", "/Users/stefanmac/Downloads/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("window-size=1024,1500");
        WebDriver driver = new ChromeDriver(chromeOptions);
        
        Scanner scan = new Scanner(System.in);
        
        System.out.println("available commands: ScanTemplate , ClearTemplate , NewLocalScan , ClearScanFolder , close");
        String input = scan.nextLine();
        
       
        
        switch(input){
         
            case "ScanTemplate" :
            ScanTemplate(driver);   
                
            break;
         
            case "ClearTemplate" :
            ClearTemplate();
            
            break;
            
            case "close":
            System.exit(0);
            break;
            
            case"NewLocalScan":
            NewLocalScan(driver);   
            break;
            
            case"ClearScanFolder":
            ClearScanFolder();
            break;
                
        }
        
        
}}
