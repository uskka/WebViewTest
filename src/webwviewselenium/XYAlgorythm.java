package webwviewselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class XYAlgorythm {

    public static int WebPageContentXY(WebDriver driver) {//takes top left button and bottom right button and calculates number of screenshots that have to be taken based on webside hight

        WebElement TopicElement = driver.findElement(By.cssSelector("#main-content > div:nth-child(1) > div > div.title > h2 > span.os-text"));
        Point TopicElementPoint = TopicElement.getLocation();
        System.out.println(TopicElementPoint.x + " " + TopicElementPoint.y);

        WebElement MoreInforamtionElement = driver.findElement(By.cssSelector("  #metadata-tab > span"));
        Point MoreInforamtionElementPoint = MoreInforamtionElement.getLocation();
        System.out.println(MoreInforamtionElementPoint.x + " " + MoreInforamtionElementPoint.y);

        double WebSiteLength = MoreInforamtionElementPoint.y - TopicElementPoint.y;
        int NumberOfScreenShots = (int) (Math.ceil(WebSiteLength / 1350));
        double temp = WebSiteLength / 1350;

        return NumberOfScreenShots;

    }

}
