package api.studymate.tests;

import api.studymate.endpoints.EP_AddTeacher;
import api.studymate.endpoints.EP_GetTeachers;
import api.studymate.endpoints.EP_PutTeachers;
import org.testng.annotations.Test;

public class Tst_PutTeachers {
    @Test
    public void validateUpdateInfoTeacher(){
        EP_PutTeachers epPutTeachers =new EP_PutTeachers();
        epPutTeachers.validateUpdateTeacher();
    }
}
