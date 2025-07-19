package api.studymate.tests;

import api.studymate.endpoints.EP_AddTeacher;
import api.studymate.endpoints.EP_DeleteTeacher;
import org.testng.annotations.Test;

public class Tst_DeleteTeacher {
    @Test
    public void validateDeleteTeacher(){

        EP_DeleteTeacher epDeleteTeacher = new EP_DeleteTeacher();
        epDeleteTeacher.validateDeleteTeacher();


    }
}
