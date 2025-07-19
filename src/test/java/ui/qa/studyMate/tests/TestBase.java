package ui.qa.studyMate.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ui.utils.ConfigReader;
import ui.utils.Driver;

import java.time.Duration;

public class TestBase {

    public WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    @BeforeMethod
    public void setup(){
        driver = Driver.getDriver("chrome");
//        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConfigReader.readProperty("url"));

    }

    @AfterMethod
    public void tearDown(){
        Driver.closeDriver();
    }

}
