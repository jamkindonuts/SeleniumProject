package ui.qa.studyMate.tests;

import ui.qa.studyMate.pages.LoginPage;
import ui.qa.studyMate.pages.TeachersPage;
import org.testng.annotations.Test;
import ui.utils.ConfigReader;

public class TeachersPageTest extends TestBase {

    @Test(dataProvider = "teachers", dataProviderClass = TeachersPageData.class)
    public void validateTeachersMenuFunctionality(String name, String lastName, String phoneNumber, String email,
                                                  String specialization, String expectedMessage,String deletedMessage) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.readProperty("loginEmail"), ConfigReader.readProperty("password"));


        TeachersPage teachersPage = new TeachersPage(driver);
        teachersPage.clickTeachersButton();

        teachersPage.verifyListOfTeachers();

        teachersPage.validateAddTeacherFunctionality(driver, name, lastName, phoneNumber, email, specialization, expectedMessage);

        Thread.sleep(3000);
        teachersPage.editTeacherFunctionality(driver, deletedMessage);


    }
}
