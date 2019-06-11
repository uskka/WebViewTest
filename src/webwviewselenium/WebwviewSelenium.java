package webwviewselenium;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
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

 

    public static String GetBookID(String BookName) throws AWTException { //chmod +x chromedriver
        if (GetDriverPreferedByUser().equals("Chrome")) {
            System.setProperty("webdriver.chrome.driver", GetDriverPath("Chrome"));
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("window-size=1024,1500");
            WebDriver driver = new ChromeDriver(chromeOptions);
            BrowserStart(GetFirstPageURL(BookName), driver);
            driver.findElement(By.cssSelector("#metadata-tab > span")).click();
            String id = driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div/div[4]/div/dl/dd[2]")).getText();
            driver.quit();
            return id;
        } else if (GetDriverPreferedByUser().equals("Firefox")) {
            System.setProperty("webdriver.gecko.driver", GetDriverPath("Firefox"));
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--headless");
            firefoxOptions.addArguments("window-size=1024,1500");
            WebDriver driver = new FirefoxDriver(firefoxOptions);
            BrowserStart(GetFirstPageURL(BookName), driver);
            driver.findElement(By.cssSelector("#metadata-tab > span")).click();
            String id = driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div/div[4]/div/dl/dd[2]")).getText();
            driver.quit();
            return id;

        }
        return null;
    }

    public static String MakeScreenShots(String BookName, String Folder) throws AWTException, InterruptedException, IOException { //chmod +x chromedriver

        if (GetDriverPreferedByUser().equals("Chrome")) {
            System.setProperty("webdriver.chrome.driver", GetDriverPath("Chrome"));
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("window-size=1024,1500");

            WebDriver driver = new ChromeDriver(chromeOptions);
            BrowserStart(GetFirstPageURL(BookName), driver);

            boolean isThisLastPage = false;
            while (!driver.getCurrentUrl().endsWith("/Index")) {

                int ScrollNumber = 150;
                checkIfIsReady(driver);
                Number PageHeight = (Number) ((JavascriptExecutor) driver).executeScript(" return document.body.offsetHeight");
                Double NumberOfScreenShots = PageHeight.doubleValue() / 1350;
                for (int i = 0; i < NumberOfScreenShots; i++) {

                    ((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + ScrollNumber + ")");
                    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

                    String FileName = driver.getCurrentUrl().replace("/", "+") + "__" + i;
                    String DBPath = Folder + "/" + FileName + ".png";

                    FileUtils.copyFile(screenshot, new File(DBPath));

                    ScrollNumber += 1350;

                }
                if (driver.getCurrentUrl().endsWith("/Index")) {
                    isThisLastPage = true;
                } else {
                    ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('next')[0].click()");
                }
            }

            driver.quit();

        } else if (GetDriverPreferedByUser().equals("Firefox")) {
            System.setProperty("webdriver.gecko.driver", GetDriverPath("Firefox"));
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--headless");
            firefoxOptions.addArguments("window-size=1024,1500");
            WebDriver driver = new FirefoxDriver(firefoxOptions);
            BrowserStart(GetFirstPageURL(BookName), driver);
            boolean isThisLastPage = false;
            while (!driver.getCurrentUrl().endsWith("/Index")) {

                int ScrollNumber = 150;
                checkIfIsReady(driver);
                Number PageHeight = (Number) ((JavascriptExecutor) driver).executeScript(" return document.body.offsetHeight");
                Double NumberOfScreenShots = PageHeight.doubleValue() / 1350;
                for (int i = 0; i < NumberOfScreenShots; i++) {

                    ((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + ScrollNumber + ")");
                    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

                    String FileName = driver.getCurrentUrl().replace("/", "+") + "__" + i;
                    String DBPath = Folder + "/" + FileName + ".png";

                    FileUtils.copyFile(screenshot, new File(DBPath));

                    ScrollNumber += 1350;

                }
                if (driver.getCurrentUrl().endsWith("/Index")) {
                    isThisLastPage = true;
                } else {
                    ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('next')[0].click()");
                }
            }

            driver.quit();
        }
        return null;
    }

    public static void checkIfIsReady(WebDriver driver) throws InterruptedException {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        //Initially bellow given if condition will check ready state of page.
        while (!js.executeScript("return document.readyState").toString().equals("complete")) {
            Thread.sleep(200);

        }
        Thread.sleep(2000);
    }

}
