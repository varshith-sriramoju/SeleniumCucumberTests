package Utility; // Package name; groups utility classes used by tests

import org.openqa.selenium.WebDriver; // Selenium interface representing the browser session
import org.openqa.selenium.chrome.ChromeDriver; // Selenium implementation to automate Google Chrome
import org.openqa.selenium.chrome.ChromeOptions; // Chrome-specific options/flags to configure the browser

public class BrowserDriver { // Wrapper class responsible for creating/managing a WebDriver instance
    public  static WebDriver driver; // Static reference to the WebDriver so other classes can access the same instance
    public ChromeOptions options; // Holds configuration flags for launching Chrome

    public BrowserDriver(){ // Constructor; runs when a new BrowserDriver is created (e.g., in test setup)
        options = new ChromeOptions(); // Instantiate ChromeOptions so we can add startup arguments
        options.addArguments("--remote-allow-origins=*"); // Example Chrome flag to allow remote origins (workaround for some Chrome/Selenium versions)

        System.setProperty("webdriver.http.factory", "jdk-http-client"); // Tell Selenium to use JDK's HTTP client (helps with modern Java compatibility)
        System.setProperty( // Define the path to the ChromeDriver executable so Selenium can start Chrome
            "webdriver.chrome.driver",
            System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver.exe"
        ); // On Selenium 4, Selenium Manager can often resolve this automatically (property may be unnecessary)

        this.driver = new ChromeDriver(); // Launch a new Chrome browser (NOTE: options created above are NOT passed here)
        driver.get("https://smartinventoryreact.netlify.app/"); // Navigate the browser to the application’s base URL
    } // End of constructor

    public void close(){ // Convenience method to close the browser at test teardown
        this.driver.close(); // Close the current browser window/tab (use quit() to end the entire session and driver process)
    } // End of close()
} // End of BrowserDriver class
