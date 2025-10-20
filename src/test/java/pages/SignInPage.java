package pages;

import Utility.BrowserDriver;
import org.openqa.selenium.By;

public class SignInPage extends BrowserDriver {
    public static String username_text_path="//*[@id=\"username\"]";
    public static String password_text_path="//*[@id=\"password\"]";
    public static String signIn_button_path="//*[@id=\"root\"]/div/div/div[2]/div/div/form/button";

    public static void sendKeys_username(){
        driver.findElement(By.xpath(username_text_path)).sendKeys("sa");
    }

    public static void sendKeys_password(){
        driver.findElement(By.xpath(password_text_path)).sendKeys("sa");
    }

    public static void sendKeys_signIn(){
        driver.findElement(By.xpath(signIn_button_path)).click();
    }

}
