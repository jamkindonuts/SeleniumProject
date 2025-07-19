package ui.qa.studyMate.tests;

import ui.qa.studyMate.pages.LoginPage;
import ui.qa.studyMate.pages.StudentsPage;
import org.testng.annotations.Test;
import ui.utils.ConfigReader;

public class StudentsPageTest extends TestBase{


    @Test
    public void validateAddStudentAndCancelFunctionality() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.readProperty("loginEmail"), ConfigReader.readProperty("password"));
        StudentsPage studentsPage = new StudentsPage(driver);
        studentsPage.addNewStudentAndCancel();

    }



    @Test(dataProvider = "students", dataProviderClass = StudentsData.class)
    public void validateAddStudentFunctionality(String firstName, String lastName, String phoneNumber, String studentEmail, String studyFormat, String studentAddedMessage) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.readProperty("loginEmail"), ConfigReader.readProperty("password"));
        StudentsPage studentsPage = new StudentsPage(driver);
        studentsPage.addNewStudent(driver, firstName, lastName, phoneNumber, studentEmail, studyFormat, studentAddedMessage);
    }

    @Test
    public void validateEditStudentsFunctionality() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.readProperty("loginEmail"), ConfigReader.readProperty("password"));
        StudentsPage studentsPage = new StudentsPage(driver);
        studentsPage.studentsButton.click();
        studentsPage.editStudent(driver, 3);
    }
}
