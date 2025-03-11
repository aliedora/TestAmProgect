package apitests;
import io.qameta.allure.*;
import io.restassured.response.Response;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("API Testing")
@Feature("My get test")
public class ApiGetTest {

    @ParameterizedTest
    @MethodSource("userDataProvider")
    @Description("My beautiful test - {0}")
    @Step("Send GET user data")
    void testGetUser(int userId, String firstName, String lastName) {
        String url = "https://reqres.in/api/users/" + userId;

        Response response = given()
                .when()
                .get(url)
                .then()
                .statusCode(200)
                .extract().response();

        Allure.addAttachment("Response for user " + userId, "application/json", response.prettyPrint());

        assertEquals(userId, response.jsonPath().getInt("data.id"));
        assertEquals(firstName, response.jsonPath().getString("data.first_name"));
        assertEquals(lastName, response.jsonPath().getString("data.last_name"));
    }

    static Stream<Object[]> userDataProvider() {
        return Stream.of(
                new Object[]{2, "Janet", "Weaver"},
                new Object[]{3, "Emma", "Wong"},
                new Object[]{4, "Eve", "Holt"}
        );
    }

}
