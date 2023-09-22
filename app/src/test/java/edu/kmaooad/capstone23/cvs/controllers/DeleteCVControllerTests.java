package edu.kmaooad.capstone23.cvs.controllers;

import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.dal.CVRepository;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import org.bson.types.ObjectId;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class DeleteCVControllerTests {
    @BeforeAll
    static void deleteAllData() {
        CVRepository repository = new CVRepository();
        repository.deleteAll();
    }

    String createTestCV() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("dateTimeCreated", LocalDateTime.now());
        jsonAsMap.put("textInfo", "some info about a student");
        jsonAsMap.put("status", CV.Status.OPEN);
        jsonAsMap.put("visibility", CV.Visibility.VISIBLE);
        jsonAsMap.put("autoAddCompetences", false);

        String objectId = given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/cvs/create")
                .then()
                .statusCode(200)
                .extract()
                .path("cvid");
        return objectId;
    }

    @Test
    @DisplayName("Delete CV: delete existing cv")
    public void testBasicCVDeletion() {
        String cvToDelete = createTestCV();

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", cvToDelete);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/cvs/delete")
                .then()
                .statusCode(200);

    }

    @Test
    @DisplayName("Delete CV: delete non-existing cv")
    public void testBasicCVDeletionWithNonExistendId() {
        String cvToDelete = createTestCV();

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", "gregeig43934053");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/cvs/delete")
                .then()
                .statusCode(400);

    }

    @Test
    @DisplayName("Delete CV: delete deleted")
    public void testBasicCVDeletionDeleteDeleted() {
        String cvToDelete = createTestCV();

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", cvToDelete);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/cvs/delete")
                .then()
                .statusCode(200);
        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/cvs/delete")
                .then()
                .statusCode(400);

    }
}
