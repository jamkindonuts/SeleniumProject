package ui.qa.studyMate.tests;

import ui.qa.studyMate.pages.LoginPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.utils.ConfigReader;

public class LoginPageTest extends TestBase{
    private LoginPage loginPage;

    @BeforeMethod
    public void setUpCoursePageAndLogin() throws InterruptedException {
        this.loginPage = new LoginPage(driver);
    }

    @Test(priority = 3)
    public void validateLogin() throws InterruptedException {
        loginPage.login(ConfigReader.readProperty("loginEmail"), ConfigReader.readProperty("password"));
    }

    @Test(priority = 1, dataProvider = "data", dataProviderClass = TestData.class)
    public void validateForgotPasswordAndLanguage(String language, String message){
        loginPage.chooseLanguage(language);
        loginPage.forgotPassword(ConfigReader.readProperty("loginEmail"));
        wait.until(ExpectedConditions.visibilityOf(loginPage.alertMessage));
        String actualAlertMessage = loginPage.alertMessage.getText();
        String expectedMessage = message;
        Assert.assertEquals(actualAlertMessage, expectedMessage);

    }


    @Test(priority = 2)
    public  void validateLoginInformation() {
        loginPage.checkLoginPageInformation(driver,"Log in");
    }
}
