package api.studymate.tests;

import api.studymate.endpoints.EP_AddTeacher;
import org.testng.annotations.Test;

public class Tst_AddTeacher {
    @Test
    public void validateAddteacher(){

        EP_AddTeacher epAddTeacher = new EP_AddTeacher();
        epAddTeacher.validateAddTeacher();


    }
}
