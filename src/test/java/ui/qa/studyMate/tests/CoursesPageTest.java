package ui.qa.studyMate.tests;

import ui.qa.studyMate.pages.CoursesPage;
import ui.qa.studyMate.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ui.utils.BrowserUtils;
import ui.utils.ConfigReader;

public class CoursesPageTest extends TestBase{
    private CoursesPage coursesPage;

    @BeforeMethod
    public void setUpCoursePageAndLogin() throws InterruptedException {
        this.coursesPage = new CoursesPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.readProperty("loginEmail"), ConfigReader.readProperty("password"));
        coursesPage.clickCoursesButton();
    }

    @Parameters({"expectedCourseCreateText"})
    @Test(priority = 1)
    public void validateCoursePageInformation(String expectedCourseCreateText) {
        Assert.assertEquals(BrowserUtils.getText(coursesPage.createCourseButton), expectedCourseCreateText);
        coursesPage.createCourseButton.click();
        Assert.assertTrue(coursesPage.uploadPhotoMessage.isDisplayed());
        Assert.assertTrue(coursesPage.courseNameBox.isDisplayed() && coursesPage.courseNameBox.isEnabled());
        Assert.assertTrue(coursesPage.dateBox.isDisplayed() && coursesPage.dateBox.isEnabled());
        Assert.assertTrue(coursesPage.descriptionBox.isDisplayed() && coursesPage.descriptionBox.isEnabled());
        Assert.assertTrue(coursesPage.cancelButton.isDisplayed() && coursesPage.cancelButton.isEnabled());
        Assert.assertTrue(coursesPage.createButton.isDisplayed() && !coursesPage.createButton.isEnabled());
        }


    @Parameters({"courseNameInput", "courseDateInput", "descriptionCourseInput", "expectedCourseName", "expectedCourseDate", "expectedDescriptionCourse"})
    @Test(priority = 3)
    public void validateCreateCourseFunctionality(String courseNameInput, String courseDateInput, String descriptionCourseInput, String expectedCourseName, String expectedCourseDate, String expectedDescriptionCourse) throws InterruptedException {
        coursesPage.createCourseButton.click();
        String imagePath = System.getProperty("user.dir") + "/src/test/resources/automationPhoto.jpeg";
        coursesPage.uploadPhoto(imagePath);
        coursesPage.enterInformationAndCreateCourse(driver, courseNameInput, courseDateInput, descriptionCourseInput, expectedCourseName, expectedCourseDate, expectedDescriptionCourse);
    }

    @Test(priority = 2)
    public void validateCancelCoursesCreationButton() {
        this.coursesPage.createCourseButton.click();
        this.coursesPage.cancelCourses();
    }


}
