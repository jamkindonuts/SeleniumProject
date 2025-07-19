package ui.qa.studyMate.tests;

import org.testng.annotations.DataProvider;

public class StudentsData {

    @DataProvider(name = "students")
    public Object[][] getStudentsData(){
        return new Object[][]{
                { "Helen", "Smith", "1111111111", "hello@gmail.com", "online", "New student successfully saved"},
                { "John", "Doe", "1234567890", "johny@gmail.com", "offline", "New student successfully saved"},
                { "Liam", "Anderson", "1234589011", "johny@gmail.com", "offline", "User with the same email already exists"}
        };
    }
}
