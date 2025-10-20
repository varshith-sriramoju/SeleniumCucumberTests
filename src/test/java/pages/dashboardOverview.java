package pages;

import Utility.BrowserDriver;
import org.openqa.selenium.By;

import static Utility.BrowserDriver.driver;

public class dashboardOverview extends BrowserDriver {
    public static String dashboardOverview_xpath="//*[@id=\"root\"]/div/main/div/div[1]/h2";

    public static String visibility_dashboard(){
        String actualProductCatagoty = driver.findElement(By.xpath(dashboardOverview_xpath)).getText();
        return actualProductCatagoty;
    }
}
