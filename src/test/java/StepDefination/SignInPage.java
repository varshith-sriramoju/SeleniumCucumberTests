package StepDefination;

import cucumber.api.java.en.When;
import static pages.SignInPage.*;

public class SignInPage {
    @When("^User signs in with valid credentials$")
    public void user_login_to_loginpage(){
        sendKeys_username();
        sendKeys_password();
        sendKeys_signIn();
    }
}
