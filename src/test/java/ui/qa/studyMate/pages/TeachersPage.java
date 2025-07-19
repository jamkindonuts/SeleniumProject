package ui.qa.studyMate.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ui.utils.BrowserUtils;
import ui.utils.WaitUtils;

import java.time.Duration;
import java.util.List;

public class TeachersPage {

    public TeachersPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@href='/admin/teachers']")
    WebElement teachersButton;

    @FindBy(xpath = "//tr[@class='MuiTableRow-root css-1ipg9de']")
    List<WebElement> teachersList;

    @FindBy(xpath = "//header//button[@type='button']")
    WebElement addTeacher;

    @FindBy(xpath = "//input[@name='name']")
    WebElement name;

    @FindBy(xpath = "//input[@name='lastName']")
    WebElement lastName;

    @FindBy(xpath = "//input[@name='phoneNumber']")
    WebElement phoneNumber;

    @FindBy(xpath = "//input[@name='email']")
    WebElement email;

    @FindBy(xpath = "//input[@name='specialization']")
    WebElement specialization;

    @FindBy(xpath = "//div[@role='button']")
    WebElement chooseCourses;

    @FindBy(xpath = "//input[@type='checkbox']")
    List<WebElement> checkBoxes;

    @FindBy(xpath = "//*[@id='menu-']/div[3]/ul/li[1]/span[1]/input")
    WebElement checkbox;

    @FindBy (xpath = "//*[@id='content']/form/div[1]/div[5]/label")
    WebElement cssChoose;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement submit;

    @FindBy (xpath = "//*[@id='root']/div[2]/div/div/div/div/div/div/p")
    WebElement successfulAddedTeacherMessage;

    @FindBy (xpath = "//*[@id='root']/div/div[2]/div/div/div/div[1]/div/table/tbody/tr[1]/td[6]/div/button")
    WebElement editButtonDots;

    @FindBy (xpath = "/html/body/div[2]/div[3]/ul/li[1]")
    WebElement editButton;

    @FindBy (xpath = " /html/body/div[2]/div[3]/ul/li[2]")
    WebElement deleteButton;

    @FindBy (xpath = "//button[.='Delete']")
    WebElement deletePopUp;

    @FindBy (xpath = "//p[@class='sc-dkrFOg hbyUzQ']")  // //*[@id='root']/div[2]/div/div/div/div/div/div/p
    WebElement messageDeletion;


    public void verifyListOfTeachers() {
        for (WebElement teacher : teachersList) {
            System.out.println(teacher.getText());


        }

    }

    public void clickTeachersButton(){
        teachersButton.click();
    }

    public void validateAddTeacherFunctionality(WebDriver driver,String name, String lastName, String phoneNumber, String email,
                                                String specialization,String expectedMessage) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        addTeacher.click();
        this.name.sendKeys(name);
        this.lastName.sendKeys(lastName);
        this.phoneNumber.click();
        this.phoneNumber.sendKeys(phoneNumber);
        WaitUtils.waitForClickAbility(driver, this.phoneNumber,10);
        WaitUtils.waitForVisibility(driver, this.phoneNumber,10);
        this.email.sendKeys(email);
        this.specialization.sendKeys(specialization);
        chooseCourses.click();
        checkbox.click();
        BrowserUtils.clickJS(driver,cssChoose);

        BrowserUtils.clickJS(driver,submit);

        wait.until(ExpectedConditions.visibilityOf(this.successfulAddedTeacherMessage));
        String actualStudentAddedMessage = this.successfulAddedTeacherMessage.getText();
        String expectedStudentAddedMessage = expectedMessage;
        Assert.assertEquals(actualStudentAddedMessage, expectedStudentAddedMessage);

    }

    public void  editTeacherFunctionality(WebDriver driver, String expectedMessage){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        editButtonDots.click();
        deleteButton.click();
        deletePopUp.click();
        wait.until(ExpectedConditions.visibilityOf(this.messageDeletion));
        Assert.assertEquals(messageDeletion.getText(),expectedMessage);

    }




}
