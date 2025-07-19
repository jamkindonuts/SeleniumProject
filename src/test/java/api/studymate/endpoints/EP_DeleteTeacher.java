package api.studymate.endpoints;

import api.studymate.pojo.PJ_AddTeacher;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.Assert;
import ui.utils.ConfigReader;

import static api.studymate.endpoints.EP_GetTeachers.instructorIdToDelete;

public class EP_DeleteTeacher {
    public void validateDeleteTeacher() {
        RestAssured.baseURI = ConfigReader.readProperty("url_api");
        String token = ConfigReader.readProperty("token");
        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .header("Origin", "https://codewise.studymate.us")
                .accept("application/json")
                .pathParam("instructorId", instructorIdToDelete)
                .when()
                .delete("/{instructorId}")
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();

        String actualMessage = response.jsonPath().getString("message");
        Assert.assertEquals(actualMessage, "Instructor successfully deleted");
    }
}




