package pages;

import Utility.BrowserDriver;
import org.openqa.selenium.By;

public class HomePage extends BrowserDriver {
    public static String clickHere="//*[@id=\"username\"]";

    public static void clickButton(){
        driver.findElement(By.xpath(clickHere)).click();
    }

}
