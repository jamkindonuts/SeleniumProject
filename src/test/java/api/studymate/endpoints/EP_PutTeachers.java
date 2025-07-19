package api.studymate.endpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import ui.utils.ConfigReader;

import static api.studymate.endpoints.EP_GetTeachers.instructorIdToDelete;

public class EP_PutTeachers {
    public void validateUpdateTeacher() {

        RestAssured.baseURI = ConfigReader.readProperty("url_api");

        String token = ConfigReader.readProperty("token");

        String updatePayload = """
                {
                  "name": "Eva",
                  "lastName": "Vin",
                  "phoneNumber": "8504432123",
                  "email": "jamak@akara.kg",
                  "specialization": "teacher",
                  "courses": [6]
                }
                """;
        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .header("Origin", "https://codewise.studymate.us")
                .contentType("application/json")
                .accept("application/json")
                .body(updatePayload)
                .pathParam("instructorId",instructorIdToDelete)
                .when()
                .put("/{instructorId}")
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();

        String actualMessage = response.jsonPath().getString("message");
        Assert.assertEquals(actualMessage, "Instructor successfully updated");
    }
}


