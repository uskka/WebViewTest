package webwviewselenium;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static webwviewselenium.ConsoleHandler.Console;
import static webwviewselenium.XMLHendler.GetDriverPath;
import static webwviewselenium.XMLHendler.GetDriverPreferedByUser;
import static webwviewselenium.XMLHendler.GetFirstPageURL;

public class WebwviewSelenium {

    public static void main(String[] args) throws IOException, AWTException {

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
    }

    public static void NewLocalScan(WebDriver driver) {
        try {
            BrowserStart("file:////Users/stefanmac/Downloads/1.1 The Science of Biology - Biology - OpenStax CNX.htm", driver);
        } catch (AWTException ex) {
            Logger.getLogger(WebwviewSelenium.class.getName()).log(Level.SEVERE, null, ex);
        }

        WebPageContentXY(driver);

        try {
            MakeScreenShot(driver, 300, 300, 600, 600, 0);
        } catch (IOException ex) {
            Logger.getLogger(WebwviewSelenium.class.getName()).log(Level.SEVERE, null, ex);
        }
        driver.close();
    }

    public static void ScanTemplate(WebDriver driver) {
        try {
            BrowserStart("https://katalyst01.cnx.org/contents/GFy_h8cu@11.2:agVo2CPX@12/1-1-The-Science-of-Biology", driver);
        } catch (AWTException ex) {
            Logger.getLogger(WebwviewSelenium.class.getName()).log(Level.SEVERE, null, ex);
        }

        WebPageContentXY(driver);

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
        driver.findElement(By.cssSelector("#content > div.pinnable > div:nth-child(2) > div > div > button > span.fa.fa-th-list")).click();
        // clicks on the button so the page gets smaller and program has less conent to scan 
        return state;
    }

    public static int WebPageContentXY(WebDriver driver) {//takes top left button and bottom right button and calculates number of screenshots that have to be taken based on webside hight

        int NumberOfScreenShots = 8;
        //double temp = WebSiteLength / 1350;

        return NumberOfScreenShots;

    }

    public static void MakeScreenShot(WebDriver driver, int startX, int startY, int endX, int endY, int type) throws IOException {
        //makes number screenshots based on the output from WebPageContentXY func

        int NumberOfScreenShots = WebPageContentXY(driver);
        int ScrollNumber = 150;

        for (int i = 0; i < NumberOfScreenShots; i++) {

            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + ScrollNumber + ")");

            System.out.println(((JavascriptExecutor) driver).executeScript(" return document.body.offsetHeight"));

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            BufferedImage fullImg = ImageIO.read(screenshot);
            BufferedImage eleScreenshot = fullImg.getSubimage(1, 1, 1, 1);

            if (type == 1) {
                String FileName = "Screen: " + i;
                String DBPath = "/Users/stefanmac/Documents/WebviewTest/Tempalate/" + FileName + ".png";
                File Screenshot = new File(DBPath);
                FileUtils.copyFile(screenshot, Screenshot);
            } else if (type == 0) {
                String FileName = "Screen: " + i;
                String DBPath = "/Users/stefanmac/Documents/WebviewTest/NewScan/" + FileName + ".png";
                File Screenshot = new File(DBPath);
                FileUtils.copyFile(screenshot, Screenshot);
            }

            ScrollNumber += 1350;
        }
    }

    public static String GetBookID(String BookName) throws AWTException {
        if (GetDriverPreferedByUser().equals("Chrome")) {
            System.setProperty("webdriver.chrome.driver", GetDriverPath("Chrome"));
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("window-size=1024,1500");
            WebDriver driver = new ChromeDriver(chromeOptions);
            BrowserStart(GetFirstPageURL(BookName), driver);
            String id = driver.findElement(By.cssSelector("#main-content > div:nth-child(3) > div > div.metadata.tab-content > div > dl > dd:nth-child(4) > div")).getText();
            return id;
        }
return null;
    }

}
