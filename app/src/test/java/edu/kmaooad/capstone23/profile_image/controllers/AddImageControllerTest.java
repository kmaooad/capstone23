package edu.kmaooad.capstone23.profile_image.controllers;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;

import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class AddImageControllerTest {

     @Inject
     UserRepository userRepository;

     @Test
     @DisplayName("Upload image")
     public void testUploadImageToProfile() {
         Map<String, Object> jsonAsMap = new HashMap<>();

         jsonAsMap.put("base64ImageString", "photka");
         jsonAsMap.put("id", "idishka");

         given()
                 .contentType("application/json")
                 .body(jsonAsMap)
                 .when()
                 .post("/profile/create-image")
                 .then()
                 .statusCode(500);
     }
}