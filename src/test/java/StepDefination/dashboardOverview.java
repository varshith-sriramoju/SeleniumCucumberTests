package StepDefination;

import io.cucumber.java.en.Then;

import static org.junit.Assert.assertEquals;
import static pages.dashboardOverview.*;

public class dashboardOverview {
    @Then("^User should see the dashboard overview page$")
    public void product_catagory_page_viewed(){
        String actualProductCatagory = visibility_dashboard();
        assertEquals(actualProductCatagory,"Dashboard Overview");
    }
}
