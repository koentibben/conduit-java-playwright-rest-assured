package tests.API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tests.BaseTest;
import tests.data.GlobalVariables;

import java.util.Objects;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class UserAPITest extends BaseTest {

    Random random = new Random();

    @BeforeTest
    public void setUp() {
        setRestAssuredBaseURI();
        GlobalVariables.access_token = getAuthenticationToken();
    }

    @Test
    public void putUserUpdateBio() throws Exception {
        String updateBioText = "Bio updated via REST Assured, number: " + (random.nextInt(13371337 - 1) + 1);
        String jsonStringLogInRequestBody = "{\"user\":{\"email\": \"koentest@test.com\" ,\"username\": \"koentest\" ,\"bio\": \"" + updateBioText + "\" ,\"image\": \"https://api.realworld.io/images/smiley-cyrus.jpeg\" ,\"token\": \"" + GlobalVariables.access_token + "\" ,\"password\": \"Test12345!\"}}";

        Response putUserUpdateBioResponse = given()
                .header("Authorization", "Token " + GlobalVariables.access_token)
                .contentType("application/json")
                .body(jsonStringLogInRequestBody)
                .when()
                .put("/user");

        JsonPath jsonPathPutUserUpdateBioResponse = new JsonPath(putUserUpdateBioResponse.asString());
        String updatedUserResponseBio = jsonPathPutUserUpdateBioResponse.get("user.bio");

        if (!Objects.equals(updateBioText, updatedUserResponseBio)) {
            throw new Exception(
                    "To update user bio is not the same as after the PUT response: " + "\r\n"
                            + updateBioText + "\r\n"
                            + updatedUserResponseBio
            );
        }
    }
}
