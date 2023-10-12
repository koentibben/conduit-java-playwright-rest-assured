package tests.API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tests.BaseTest;


public class TagsAPITest extends BaseTest {

    @BeforeTest
    public void setUp() {
        setRestAssuredBaseURI();
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