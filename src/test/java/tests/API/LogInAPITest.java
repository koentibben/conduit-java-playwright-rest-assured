package tests.API;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tests.BaseTest;
import tests.data.DataProviderClass;

import static io.restassured.RestAssured.given;

public class LogInAPITest extends BaseTest {

    @BeforeTest
    public void setUp() {
        setRestAssuredBaseURI();
    }

    @Test(dataProvider = "logInCredentialsDataProvider", dataProviderClass = DataProviderClass.class)
    public void postLogInUsingDataProvider(String username, String password, Boolean workingCredentials) {
        String jsonStringLogInRequestBody = "{\"user\":{\"email\": \"" + username + "\" ,\"password\": \"" + password + "\"}}";
        Response postLogInResponse = given()
                .contentType("application/json")
                .body(jsonStringLogInRequestBody)
                .when()
                .post("/users/login");

        if (workingCredentials) {
            Assert.assertEquals(postLogInResponse.getStatusCode() /*actual value*/, 200 /*expected value*/,
                    "Correct status 200 code returned for working credentials");
        } else {
            Assert.assertEquals(postLogInResponse.getStatusCode() /*actual value*/, 403 /*expected value*/,
                    "Correct status code returned");
        }
    }
}
