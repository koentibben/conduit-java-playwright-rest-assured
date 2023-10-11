package tests.API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class ArticlesAPITest {

    @BeforeTest
    public void setUp() {
        RestAssured.baseURI = "https://api.realworld.io/api";
    }

    @Test
    public void getArticlesWithQueryParamLimit() {
        Response response = given()
                .when()
                .get("/articles?limit=3");
        JsonPath jsonPath = new JsonPath(response.asString());
        int amountOfArticles = jsonPath.getInt("articles.size()");
        assertThat(amountOfArticles).isEqualTo(3);
    }

    @Test
    public void getArticlesWithQueryParamOffset() throws Exception {
        Response responseWithoutOffset = given()
                .when()
                .get("/articles");
        JsonPath jsonPathWithoutOffset = new JsonPath(responseWithoutOffset.asString());
        String slugFirstArticleWithoutOffset = jsonPathWithoutOffset.get("articles[0].slug");

        Response responseWithOffset = given()
                .when()
                .get("/articles?offset=1");
        JsonPath jsonPathWithOffset = new JsonPath(responseWithOffset.asString());
        String slugFirstArticleWithOffset = jsonPathWithOffset.get("articles[0].slug");

        if (Objects.equals(slugFirstArticleWithoutOffset, slugFirstArticleWithOffset)) {
            throw new Exception(
                    "Extracted slugs with and without using ?offset=1 are the same: " + "\r\n"
                    + slugFirstArticleWithoutOffset + "\r\n"
                    + slugFirstArticleWithOffset
            );
        }
    }
}
