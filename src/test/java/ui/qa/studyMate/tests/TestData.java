package ui.qa.studyMate.tests;

import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name = "data")
    public Object[][] getData(){
        return new Object[][]{
                {"russian", "Письмо для сброса пароля отправлено на этот адрес электронной почты"},
                {"english", "Password reset email sent to this email"}
        };
    }

}
