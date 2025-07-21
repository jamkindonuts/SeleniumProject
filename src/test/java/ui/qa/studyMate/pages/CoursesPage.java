package ui.qa.studyMate.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import ui.utils.BrowserUtils;

public class CoursesPage {

    public CoursesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@href='/admin/courses']")
    WebElement coursesButton;

    @FindBy(xpath = "//div[@class='sc-fcCDlF gsXUTf']/div")
    WebElement header;

    public @FindBy(xpath = "//header//button[@type='button']")
    WebElement createCourseButton;

    public @FindBy(xpath = "//input[@type='file']")   // uploading is here
    WebElement uploadPhotoBox;

    public @FindBy(xpath = "//p[contains (text(), 'drag the photo')]")
    WebElement uploadPhotoMessage;

    public @FindBy(xpath = "//input[@name='courseName']")
    WebElement courseNameBox;

    public @FindBy(xpath = "//input[@placeholder='dd.mm.y']")
    WebElement dateBox;

    public @FindBy(xpath = "//textarea[@name='description']")
    WebElement descriptionBox;

    public @FindBy(xpath = "//div[contains(@class, 'sc-gKPRtg')]/button[@type='button']")
    WebElement cancelButton;

    public @FindBy(xpath = "//button[@form='courseForm']")
    WebElement createButton;

    public void checkCoursesButtonSpelling(String expectedCoursesButtonSpelling ){
        Assert.assertEquals(BrowserUtils.getText(coursesButton), expectedCoursesButtonSpelling);
    }

    public  void clickCoursesButton(){
        coursesButton.click();
    }
    public void uploadPhoto(String photoPath) {
        uploadPhotoBox.sendKeys(photoPath);
    }

    public void enterInformationAndCreateCourse(WebDriver driver, String nameForCourse, String dateForCourse, String descriptionForCourse, String expectedGroupName, String expectedDate, String expectedDescription) throws InterruptedException {
        courseNameBox.sendKeys(nameForCourse);
        dateBox.sendKeys(dateForCourse);
        descriptionBox.sendKeys(descriptionForCourse);
        Assert.assertEquals(courseNameBox.getAttribute("value"), expectedGroupName);
        Assert.assertEquals(dateBox.getAttribute("value"), expectedDate);
        Assert.assertEquals(BrowserUtils.getText(descriptionBox), expectedDescription);
        createButton.click();
        Thread.sleep(4000);
    }

    public void cancelCourses() {
        Assert.assertTrue(cancelButton.isDisplayed() && cancelButton.isEnabled());
        cancelButton.click();
    }

    }


