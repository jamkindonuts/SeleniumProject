package ui.qa.studyMate.tests;

import ui.qa.studyMate.pages.GroupsPage;
import ui.qa.studyMate.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ui.utils.ConfigReader;

public class GroupsPageTest extends TestBase {

    @DataProvider(name = "groupFullyTest")
    public Object[][] FullTest() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String Name = timestamp + "Group";
        String updatedName = Name + "Edited";
        return new Object[][]{
                {Name, "We are aliens", "26.05.2025", updatedName}};
    }

    @Test(dataProvider = "groupFullyTest")
    public void createEditDeleteGroupTest(String Name, String description, String date, String updatedName) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.readProperty("loginEmail"), ConfigReader.readProperty("password"));


        GroupsPage groupsPage = new GroupsPage(driver);
        groupsPage.clickGroupsLink();
        groupsPage.clickCreateGroupButton();

        String imagePath = System.getProperty("user.dir") + "/src/test/resources/project.png";

//        WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));
        WebElement input = driver.findElement(By.xpath("//input[@type='file']"));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'block';", input);
        input.sendKeys(imagePath);

        groupsPage.clickCreateGroupName(Name);
        groupsPage.clickCreateGroupDescription(description);
        groupsPage.clickCreateGroupDate(date);
        groupsPage.clickCreateButton();

        Assert.assertTrue(groupsPage.isGroupPresent(Name));

        groupsPage.editGroup();
        groupsPage.setEditGroup(updatedName, "Updated" + description, date);
        groupsPage.deleteGroup(updatedName);
        Thread.sleep(2000);
        Assert.assertFalse(groupsPage.isGroupPresent(updatedName));

    }
}

