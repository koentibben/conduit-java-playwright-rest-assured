package tests.API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class TagsAPITest {

    @BeforeTest
    public void setUp() {
        RestAssured.baseURI = "https://api.realworld.io/api";
    }

    @Test
    public void getTagsShouldContainWelcomeTag() {
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/tags");

        // Retrieve the body of the Response
        ResponseBody body = response.getBody();

        String bodyAsString = body.asString();
        Assert.assertEquals(bodyAsString.contains("welcome"), true);
    }
}