package edu.kmaooad.capstone23.orgs;

import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import java.util.HashMap;
import java.util.Map;

@QuarkusTest
public class OrgsControllerTests {

    @Test
    public void test() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("orgName", "NaUKMA");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/orgs")
                .then()
                .statusCode(200);
    }

}