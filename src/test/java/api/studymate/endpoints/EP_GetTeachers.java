package api.studymate.endpoints;

import api.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class EP_GetTeachers {
    public static int instructorIdToDelete;

    public static int validateGetTeachers(String email) {
        RestAssured.baseURI = ConfigReader.readProperty("url_api");
        RestAssured.basePath = ConfigReader.readProperty("teachers_path");
        String token = ConfigReader.readProperty("token");

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .header("Origin", "https://codewise.studymate.us")
                .contentType("application/json")
                .accept("application/json")
                .when()
                .get()
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();
        List<Map<String, Object>> instructors = response.jsonPath().getList("objects");

        if (instructors == null) {
            throw new RuntimeException("Could not extract 'objects'. Full response: " + response.asString());
        }

        for (Map<String, Object> inst : instructors) {
            if (email.equals(inst.get("email"))) {
                instructorIdToDelete = (int) inst.get("id");
                return instructorIdToDelete;
            }
        }

        throw new RuntimeException("Instructor not found with email: " + email);


    }}





