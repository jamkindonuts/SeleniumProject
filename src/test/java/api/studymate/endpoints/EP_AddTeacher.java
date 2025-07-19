package api.studymate.endpoints;

import api.studymate.pojo.PJ_AddTeacher;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import ui.utils.ConfigReader;

public class EP_AddTeacher {
    public void validateAddTeacher() {

        RestAssured.baseURI = ConfigReader.readProperty("url_api");
        RestAssured.basePath = ConfigReader.readProperty("teachers_path");

        String token = ConfigReader.readProperty("token");

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .header("Origin", "https://codewise.studymate.us")
                .contentType("application/json")
                .accept("application/json")
                .body("{\n" +
                        "  \"name\": \"Eva\",\n" +
                        "  \"lastName\": \"Luna\",\n" +
                        "  \"phoneNumber\": \"8504432123\",\n" +
                        "  \"email\": \"jamak@akara.kg\",\n" +
                        "  \"specialization\": \"teacher\",\n" +
                        "  \"courses\": [6]\n" +
                        "}")
                .when()
                .post()
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();

        PJ_AddTeacher deserializedResponse = response.as(PJ_AddTeacher.class);
        Assert.assertEquals(response.path("message"), "Instructor successfully saved");

    }
}
