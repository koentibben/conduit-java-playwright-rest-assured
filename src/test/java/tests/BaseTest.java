package tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BaseTest {

    public void setRestAssuredBaseURI() {
        RestAssured.baseURI = "https://api.realworld.io/api";
    }

    public static String getAuthenticationToken() {
        String jsonStringLogInRequestBody = "{\"user\":{\"email\": \"koentest@test.com\" ,\"password\": \"Test12345!\"}}";
        Response logInPostCallResponse = given()
                .body(jsonStringLogInRequestBody)
                .contentType("application/json")
                .when()
                .post("/users/login");
        JsonPath jsonPathLogInPostCallResponse = new JsonPath(logInPostCallResponse.asString());
        return jsonPathLogInPostCallResponse.get("user.token");
    }
}
