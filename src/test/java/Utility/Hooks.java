package Utility; // Package grouping utility/lifecycle classes used by the tests

import org.junit.After; // JUnit 4 annotation for a method to run after a test (tear-down)
import org.junit.Before; // JUnit 4 annotation for a method to run before a test (set-up)
import org.openqa.selenium.chrome.ChromeOptions; // Selenium type for passing Chrome-specific startup options

public class Hooks { // Class that defines setup/teardown hooks for the test lifecycle
    public static BrowserDriver driver; // Shared reference to the BrowserDriver wrapper (holds WebDriver)
    public static ChromeOptions options; // Placeholder for ChromeOptions if needed across tests (not used here)

    @Before // In JUnit 4, marks setup() to run before the test method; with a Cucumber JUnit runner this runs before the Cucumber runner, not each scenario
    public void setup(){ // Setup method to initialize resources needed for tests
        driver = new BrowserDriver(); // Construct BrowserDriver; launches Chrome and navigates to the base URL
    } // End of setup()

    @After // In JUnit 4, marks tearDown() to run after the test method; with a Cucumber JUnit runner this runs after the Cucumber runner, not each scenario
    public void tearDown(){ // Teardown method to release resources after tests finish
        driver.close(); // Close the browser via BrowserDriver (currently calls WebDriver.close(); prefer quit() to end the session)
    } // End of tearDown()
} // End of Hooks class
