package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.given;

@QuarkusTest
class GithubRepoResourceTest {
    @ParameterizedTest
    @CsvSource({
            "tvijas, 200",
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa, 404"
    })
    public void testGetRepositoriesHappyPath(String owner, Integer expectedStatus) {
        given()
                .when()
                .get("/api/v1/github/repo/user/" + owner)
                .then()
                .log().all()
                .statusCode(expectedStatus);
    }
}