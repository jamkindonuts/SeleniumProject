package ui.qa.studyMate.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ui.utils.WaitUtils;

import java.time.Duration;
import java.util.List;

public class StudentsPage {

    public StudentsPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public @FindBy(xpath = "//a[@href='/admin/students']")
    WebElement studentsButton;

//    @FindBy(css = "input[name='search']")
//    WebElement searchBar;

    @FindBy(xpath = "//button[contains(@class, 'MuiButton-containedSizeMedium css-79mk38')]")
    WebElement addStudentButton;

    @FindBy(css = "input[name='name']")
    WebElement firstNameInput;

    @FindBy(css = "input[name='lastName']")
    WebElement lastNameInput;

    @FindBy(css = "input[placeholder='+1 ___ ___ ____ ']")
    WebElement phoneNumberInput;

    @FindBy(xpath = "//div[@class='css-1fk3hej']//input[@name='email']")
    WebElement emailInputStudent;

    @FindBy(id = "mui-component-select-groupId")
    WebElement groupDropDown;

    @FindBy(xpath = "//ul[@role='listbox']/li")
    WebElement groupChoice;

    @FindBy(id = "mui-component-select-studyFormat")
    WebElement studyFormatDropDown;

    @FindBy(css = "li[data-value='ONLINE']")
    WebElement onlineStudyFormat;

    @FindBy(css = "li[data-value='OFFLINE']")
    WebElement offlineStudyFormat;

    @FindBy(css = "button[type='submit']")
    WebElement addFinalButton;

    @FindBy(css = "div[class='MuiAlert-message css-1xsto0d']")
    WebElement successStudentAddedMessage;

    public @FindBy(xpath = "//form//button[@type='button']")
    WebElement cancelButton;

    @FindBy(xpath = "//table[@class='MuiTable-root css-1owb465']")
    WebElement tableOfStudents;

    @FindBy(tagName = "tr")
    List<WebElement> rowsInStudentsTable;

    @FindBy(xpath = "//tr/td[7]//button")
    List<WebElement> threeDotsActionsButtons;

    @FindBy(xpath = "//div[contains(@class, 'css-xc95k')]//ul/li[.='Edit']")
    WebElement editButton;

    @FindBy(xpath = "//div[contains(@class, 'css-xc95k')]//ul/li[.='Block']")
    WebElement blockButton;

    @FindBy(xpath = "//button[.='Block']")
    WebElement confirmBlockButton;

    @FindBy(xpath = "//div[contains(@class, 'css-xc95k')]//ul/li[.='Unblock']")
    WebElement unblockButton;

    @FindBy(xpath = "//button[.='Unblock']")
    WebElement confirmUnblockButton;

    @FindBy(xpath = "//div[contains(@class, 'css-xc95k')]//ul/li[.='Delete']")
    WebElement deleteButton;

    @FindBy(xpath = "//button[.='Delete']")
    WebElement confirmDeleteButton;






    public void addNewStudent(WebDriver driver, String firstName, String lastName, String phoneNumber,
                              String studentEmail, String studyFormat, String studentAddedMessage){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        studentsButton.click();
        addStudentButton.click();
        WaitUtils.waitForClickAbility(driver, firstNameInput, 5);
        firstNameInput.sendKeys(firstName);
        WaitUtils.waitForClickAbility(driver, lastNameInput, 5);
//        wait.until(ExpectedConditions.elementToBeClickable(lastNameInput));
        lastNameInput.sendKeys(lastName);
        WaitUtils.waitForClickAbility(driver, phoneNumberInput, 5);
//        wait.until(ExpectedConditions.elementToBeClickable(phoneNumberInput));
        phoneNumberInput.click();
        phoneNumberInput.sendKeys(phoneNumber);
        emailInputStudent.sendKeys(studentEmail);
        wait.until(ExpectedConditions.elementToBeClickable(groupDropDown));
        groupDropDown.click();
        groupChoice.click();
        wait.until(ExpectedConditions.elementToBeClickable(studyFormatDropDown));
        studyFormatDropDown.click();
        if(studyFormat.equalsIgnoreCase("online")){
            onlineStudyFormat.click();
        } else if(studyFormat.equalsIgnoreCase("offline")){
            offlineStudyFormat.click();
        }
        wait.until(ExpectedConditions.elementToBeClickable(addFinalButton));
        addFinalButton.click();
        wait.until(ExpectedConditions.visibilityOf(this.successStudentAddedMessage));
        String actualStudentAddedMessage = this.successStudentAddedMessage.getText();
        String expectedStudentAddedMessage = studentAddedMessage;
        Assert.assertEquals(actualStudentAddedMessage, expectedStudentAddedMessage);
    }

    public void addNewStudentAndCancel() throws InterruptedException {
        studentsButton.click();
        addStudentButton.click();
        Thread.sleep(1000);
        cancelButton.click();
    }

    public void editStudent( WebDriver driver, int expectedNumberOfStudents) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        int numberOfStudents = rowsInStudentsTable.size() - 1;
//        Assert.assertEquals(numberOfStudents, expectedNumberOfStudents);

        for (WebElement threeDotsActionsButton : threeDotsActionsButtons) {
            wait.until(ExpectedConditions.elementToBeClickable(threeDotsActionsButton));
            threeDotsActionsButton.click();
            wait.until(ExpectedConditions.elementToBeClickable(editButton));
            editButton.click();
            wait.until(ExpectedConditions.elementToBeClickable(cancelButton));
            cancelButton.click();

        }

        wait.until(ExpectedConditions.elementToBeClickable(threeDotsActionsButtons.get(1)));
        Thread.sleep(300);
        threeDotsActionsButtons.get(1).click();
        wait.until(ExpectedConditions.elementToBeClickable(blockButton));
        blockButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmBlockButton));
        confirmBlockButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(threeDotsActionsButtons.get(1)));
        Thread.sleep(300);
        threeDotsActionsButtons.get(1).click();
        Thread.sleep(300);
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
        deleteButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton));
        confirmDeleteButton.click();
        Thread.sleep(300);
    }

}
