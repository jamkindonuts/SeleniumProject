package ui.qa.studyMate.tests;

import org.testng.annotations.DataProvider;


public class TeachersPageData {
    @DataProvider
    public Object[][] teachers() {
        return new Object[][]{
                {"Benazir", "Baialiyeva", "3053452312", "hellhgfo@gmail.com", "teacher", "Instructor successfully saved","Instructor successfully deleted"  },


        };
    }



}