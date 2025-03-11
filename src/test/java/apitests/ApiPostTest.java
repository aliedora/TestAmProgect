package apitests;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Epic("API Testing")
@Feature("My post test")
public class ApiPostTest {

    @Test
    @Description("My beautiful test post")
    @Step("Send POST user data")
    void testCreateUser() {
        RestAssured.baseURI = "https://reqres.in/api";

        String requestBody = """
                {
                    "name": "John Doe",
                    "job": "QA Engineer"
                }
                """;

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract().response();

        Allure.addAttachment("Response", "application/json", response.prettyPrint());

        assertEquals("John Doe", response.jsonPath().getString("name"));
        assertEquals("QA Engineer", response.jsonPath().getString("job"));
        assertNotNull(response.jsonPath().getString("id"));
        assertNotNull(response.jsonPath().getString("createdAt"));
    }
}