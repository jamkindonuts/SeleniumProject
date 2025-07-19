package api.studymate.tests;

import api.studymate.endpoints.EP_GetTeachers;
import org.testng.annotations.Test;
import ui.utils.ConfigReader;
public class Tst_GetTeachers {
    @Test
    public void validateTeachersInfo(){
        String email = ConfigReader.readProperty("email");

        int id = EP_GetTeachers.validateGetTeachers(email); // ✅ direct static call
        System.out.println("Instructor ID stored: " + EP_GetTeachers.instructorIdToDelete);
    }
}