package webwviewselenium;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static webwviewselenium.ConsoleHandler.Console;
import static webwviewselenium.JsonHendler.NuberOfSections;

public class WebwviewSelenium {

    public static void main(String[] args) throws IOException, AWTException {

        try {
            Console();

            //JavascriptExecutor jse = (JavascriptExecutor)driver;
            //jse.executeScript("window.scrollTo(0,770)", "");
            //File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            //BufferedImage  fullImg = ImageIO.read(screenshot);
// Crop the entire page screenshot to get only element screenshot
//BufferedImage eleScreenshot= fullImg.getSubimage(startX, startY,
//    endX, endY);
//ImageIO.write(eleScreenshot, "png", screenshot);
//
//// Copy the element screenshot to disk
//File screenshotLocation = new File("/Users/stefanmac/Documents/2(770).png");
//FileUtils.copyFile(screenshot, screenshotLocation);
//
//driver.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WebwviewSelenium.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(WebwviewSelenium.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void NewLocalScan(WebDriver driver) {
        try {
            BrowserStart("https://katalyst01.cnx.org/contents/GFy_h8cu@11.2:agVo2CPX@12/1-1-The-Science-of-Biology", driver);
            ((JavascriptExecutor) driver).executeScript("document.getElementById(\"fs-id1695658\").innerHTML = \"The scientific process typically starts with an observation (often a problem to be solved) that leads to a question. Let’s think about a simple problem that starts with an observation and apply the scientific method to solve the problem. One Monday morning, a student arrives at class and quickly discovers that the classroom is too warm. That is an observation that also describes a problem: the dzem truskawkowy jest super polecam. The student then asks a question: “Why is the classroom so warm?”\";");
        } catch (AWTException ex) {
            Logger.getLogger(WebwviewSelenium.class.getName()).log(Level.SEVERE, null, ex);
        }

        

        try {
            MakeScreenShot(driver, 300, 300, 600, 600, 0);
        } catch (IOException ex) {
            Logger.getLogger(WebwviewSelenium.class.getName()).log(Level.SEVERE, null, ex);
        }
        driver.close();
    }

    public static void ClearTestFolder() {
        File clean = new File("/Users/stefanmac/Documents/WebviewTest/NewScan");
        try {
            FileUtils.cleanDirectory(clean);
        } catch (IOException ex) {
            Logger.getLogger(WebwviewSelenium.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void ClearTemplate() {
        File clean = new File("/Users/stefanmac/Documents/WebviewTest/Tempalate");
        try {
            FileUtils.cleanDirectory(clean);
        } catch (IOException ex) {
            Logger.getLogger(WebwviewSelenium.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void ScanTemplate(WebDriver driver) {
        try {
            BrowserStart("https://katalyst01.cnx.org/contents/GFy_h8cu@11.2:agVo2CPX@12/1-1-The-Science-of-Biology", driver);
        } catch (AWTException ex) {
            Logger.getLogger(WebwviewSelenium.class.getName()).log(Level.SEVERE, null, ex);
        }

        WebPageContentXY();

        try {
            MakeScreenShot(driver, 300, 300, 600, 600, 1);
        } catch (IOException ex) {
            Logger.getLogger(WebwviewSelenium.class.getName()).log(Level.SEVERE, null, ex);
        }
        driver.close();
    }

    public static int BrowserStart(String WebsiteFirstPageUrl, WebDriver driver) throws AWTException { //opens browser returns 1 if web page is loaded, 2 for timeout, 0 for unknown error

        driver.navigate().to(WebsiteFirstPageUrl);

        boolean timeout = false;
        Robot robot = new Robot();
        int IntTimeout = 0;
        int state = 0;// 0=? 1=page loaded 2=timeout

        while (!timeout) {  //loop that checks if the webpage is loaded, after about 30 sec timeout closes it

            robot.delay(1000);
            IntTimeout++;

            if (IntTimeout > 30) {
                System.out.println("Browser Opening func timeout, website loading took too long");
                timeout = true;
                state = 2;
            } else if (!driver.findElements(By.className("main-content")).isEmpty()) { //if driver can find class  that loads as last class on page
                robot.delay(15000);
                state = 1;
                timeout = true;
            }
        }
        //driver.findElement(By.cssSelector("#content > div.pinnable > div:nth-child(2) > div > div > button > span.fa.fa-th-list")).click();
        // clicks on the button so the page gets smaller and program has less conent to scan 
        return state;
    }

    public static int WebPageContentXY() {//takes top left button and bottom right button and calculates number of screenshots that have to be taken based on webside hight

        int NumberOfScreenShots = 7;
        //double temp = WebSiteLength / 1350;

        return NumberOfScreenShots;

    }

    public static void MakeScreenShot(WebDriver driver, int startX, int startY, int endX, int endY, int type) throws IOException {
        //makes number screenshots based on the output from WebPageContentXY func

        int NumberOfScreenShots = WebPageContentXY();
        int ScrollNumber = 150;

        for (int i = 0; i < NumberOfScreenShots; i++) {

            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + ScrollNumber + ")");

            System.out.println(((JavascriptExecutor) driver).executeScript(" return document.body.offsetHeight"));

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            BufferedImage fullImg = ImageIO.read(screenshot);
            BufferedImage eleScreenshot = fullImg.getSubimage(1, 1, 1, 1);

            if (type == 1) {
                String FileName = "Screen" + i;
                String DBPath = "/Users/stefanmac/Documents/WebviewTest/Tempalate/" + FileName + ".png";
                File Screenshot = new File(DBPath);
                FileUtils.copyFile(screenshot, Screenshot);
            } else if (type == 0) {
                String FileName = "Screen" + i;
                String DBPath = "/Users/stefanmac/Documents/WebviewTest/NewScan/" + FileName + ".png";
                File Screenshot = new File(DBPath);
                FileUtils.copyFile(screenshot, Screenshot);
            }

            ScrollNumber += 1350;
        }
    }

    public static void ScreenMaker(String book, String[] Sections) {

    }

    public static void Compare() throws IOException {

        String TemplateDBUrl;
        String ScannedDBUrl;
        
        
        
        
        

        
    }

 

    public static void JavaWalkBufferedImageTest1() throws IOException, FileNotFoundException, ParseException {

        
        for(int i=0;i<WebPageContentXY();i++){
        File URLTemplate = new File("/Users/stefanmac/Documents/WebviewTest/Tempalate/Screen" + i +".png");    
        File URLTest = new File("/Users/stefanmac/Documents/WebviewTest/NewScan/Screen" + i +".png");
        
      
        
        BufferedImage TemplateImage = ImageIO.read(URLTemplate);
        BufferedImage TestImage = ImageIO.read(URLTest);
        
        int w = TemplateImage.getWidth();
        int h = TemplateImage.getHeight();
        
        List<Integer> TemplateScan = new ArrayList<>();
        List<Integer> TestScan = new ArrayList<>();
        
        int startX = 150;

        for (int iq = 0; iq < h - 100; iq++) {
            for (int jq = startX; jq < w - 150; jq ++) {

                

                TemplateScan.add(TemplateImage.getRGB(jq, iq));

            }
            
//            if (startX < 154) {
//                    startX++;
//                } else {
//                    startX = 150;
//
//                }

        }
        
                for (int ii = 0; ii < h - 100; ii++) {
            for (int j = startX; j < w - 150; j ++) {

                

                TestScan.add(TestImage.getRGB(j, ii));

            }
            
           

        }
        
                
        for (int x = 0; x < TestScan.size(); x++) {

            if (TestScan.get(x).equals(TemplateScan.get(x))) {
                
                
            }else{
            System.out.println("issue found on pixel: " + x);    
            
            }
            
        }
        TemplateScan.clear();
        TestScan.clear();
        
        
        System.out.println("finished comparing page " + i);
        
        
        

//        for (int x = 0; x < list.size(); x++) {
//
//            System.out.println(list.get(x));
//            System.out.println(x);
//        }
    }
}}
