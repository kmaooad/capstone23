package edu.kmaooad.capstone23.proffesors.controllers;

import edu.kmaooad.capstone23.proffesors.dal.Proffesor;
import edu.kmaooad.capstone23.proffesors.dal.ProffesorsRepository;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UnassignActivityControllerTest {


    @Inject
    ProffesorsRepository proffesorsRepository;

    @Test
    @DisplayName("Update CV: valid values")
    public void updateCV_AndGetStatus200() {
        Proffesor proffesor = new Proffesor();
        proffesorsRepository.persist(proffesor);

        Map<String, Object> allValuesUpdate = new HashMap<>();
        allValuesUpdate.put("cvId", proffesor.id.toString());

        given().contentType("application/json")
                .body(allValuesUpdate)
                .when()
                .post("/proffesor/unassignActivity")
                .then()
                .statusCode(200);
    }
}


