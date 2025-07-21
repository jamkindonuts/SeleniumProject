package ui.qa.studyMate.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GroupsPage {

        WebDriver driver;

        public GroupsPage(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);

        }
        @FindBy(xpath = "//a[@class='active']")           //choice group on dashboard
        WebElement groupsTab;

//    @FindBy(xpath = "(//button[@aria-haspopup='true'])[1]")   //edit group
//    WebElement editGroupMenu;

        @FindBy(xpath = "(//li[@tabindex='0'])[1]")  //confirm edit
        WebElement editGroupOption;

//   @FindBy(xpath = "(//li[@tabindex='-1' and contains(.,'Delete group')])[1]")   //delete group option
//    WebElement deleteGroupOption;

        @FindBy(xpath = "//button[text()='Delete']")      //confirm delete
        WebElement deleteConfirmButton;


        @FindBy(xpath = "//button[contains(.,'Create group')]")       //create group button when pop up
        WebElement createGroupButton;

        @FindBy(xpath = "//input[@name='name']")         // pop up group name
        WebElement createGroupName;

        @FindBy(tagName = "textarea")      // pop up group description
        WebElement createGroupDescription;

        @FindBy(xpath = "//input[@name='dateOfFinish']")   //pop up group date
        WebElement createGroupDate;

        @FindBy(xpath = "//button[@form='group-form']")   //after edit "save" button
        WebElement saveEditButton;

        @FindBy(xpath = "//button[@type='submit']")
        WebElement getNewGroupButton;


        public void clickGroupsLink() {
            groupsTab.click();
        }
        public void clickCreateGroupButton() {
            createGroupButton.click();
        }
        public void clickCreateGroupName(String name) {
            createGroupName.sendKeys(name);
        }
        public void clickCreateGroupDescription(String description) {
            createGroupDescription.sendKeys(description);
        }
        public void clickCreateGroupDate(String date) {
            createGroupDate.sendKeys(date);
        }
        public void clickCreateButton() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",getNewGroupButton);

        }
        public void editGroup() {
            driver.findElement(By.xpath("//button[@aria-haspopup='true']")).click();
            editGroupOption.click();
        }
        public void deleteGroup(String groupName) throws InterruptedException {
            System.out.println("delete group " + groupName);
            WebElement groupCard = driver.findElement(By.xpath("//div[contains(text(),'" + groupName + "')]/ancestor::div[contains(@class,'MuiCard-root')]"));
            WebElement menuButton = groupCard.findElement(By.xpath(".//button[@aria-haspopup='true']"));
            menuButton.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement deleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@role='menu']/li[normalize-space()='Delete group']")));
            Thread.sleep(2000);
            deleteButton.click();
            deleteConfirmButton.click();

//        editGroupMenu.click();
//        driver.findElement(By.xpath("//button[@aria-haspopup='true']")).click();
//        deleteGroupOption.click();
//        deleteConfirmButton.click();
        }
        public void setEditGroup(String groupName, String groupDescription, String groupDate) {     //update information for group
            createGroupName.clear();
            createGroupName.sendKeys(groupName);
            createGroupDescription.clear();
            createGroupDescription.sendKeys(groupDescription);
            createGroupDate.clear();
            createGroupDate.sendKeys(groupDate);
            saveEditButton.click();
        }
        public void createNewGroup(String groupName, String groupDescription, String groupDate) {//creating new group
            createGroupName.clear();
            createGroupName.sendKeys(groupName);
            createGroupDescription.clear();
            createGroupDescription.sendKeys(groupDescription);
            createGroupDate.clear();
            createGroupDate.sendKeys(groupDate);
            getNewGroupButton.click();
            System.out.println("Create Group button clicked");
        }
        public boolean isGroupPresent(String groupName) {
            return !driver.findElements(By.xpath("//*[contains(text(),'" + groupName + "')]")).isEmpty();
        }
        public void waitForGroupToAppear(String groupName) {
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'" + groupName + "')]")));
        }
    }

