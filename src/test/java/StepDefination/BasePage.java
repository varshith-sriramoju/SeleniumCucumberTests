package StepDefination;

import io.cucumber.java.en.Given;
import static pages.HomePage.*;

public class BasePage {

    @Given("^User opens the site home page (sign-in)")
    public void user_naviagte_loginpage() throws Throwable{
        clickButton();
    }

}
