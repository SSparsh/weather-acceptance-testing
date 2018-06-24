package test.hooks;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

public class hooks {

    public static WebDriver driver;

    @Before
    public void beforeStartup(){

        System.setProperty("webdriver.chrome.driver","src/resources/chromedriver");
        //System.setProperty("webdriver.gecko.driver", "src/resources/geckodriver");
        driver = new ChromeDriver();
       // driver = new FirefoxDriver();
        driver.get("http://localhost:3000");

    }

    @After
    public void afterScenario(){
        driver.quit();
    }
}
