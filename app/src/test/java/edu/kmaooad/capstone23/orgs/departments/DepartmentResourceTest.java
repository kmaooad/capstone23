package edu.kmaooad.capstone23.orgs.departments;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class DepartmentResourceTest {

    @Test
    public void testCreateEndpoint() {
        given()
                .body("{\"name\":\"HR\",\"description\":\"Human Resources\",\"parent\":\"Corporate\"}")
                .header("Content-Type", "application/json")
                .when()
                .post("/departments/create")
                .then()
                .statusCode(201)
                .body(containsString("HR"), containsString("Human Resources"), containsString("Corporate"));
    }

    //  для /update и /delete...
}


