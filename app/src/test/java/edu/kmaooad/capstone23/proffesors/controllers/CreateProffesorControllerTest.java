package edu.kmaooad.capstone23.proffesors.controllers;

import edu.kmaooad.capstone23.proffesors.dal.Proffesor;
import edu.kmaooad.capstone23.proffesors.dal.ProffesorsRepository;
import edu.kmaooad.capstone23.students.dal.Student;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateProffesorControllerTest {

            @Inject
            ProffesorsRepository proffesorsRepository;
        private static final String id = "65242f15a322e97788871167";

        @BeforeEach
        void setup() {
            Proffesor proffesor = new Proffesor();
            proffesor.id= new ObjectId(id);
            proffesor.firstName = "name";
            proffesor.lastName = "name";
            proffesor.email = "email@mail.com";

            proffesorsRepository.persist(proffesor);
        }

        @AfterEach
        void clear(){
            proffesorsRepository.deleteById(new ObjectId(id));
        }


        @ParameterizedTest
        @MethodSource("validCVProvider")
        @DisplayName("Create CV: valid values")
        public void createValidCV_AndGetStatus200(Map<String, Object> jsonAsMap) {
            given().contentType("application/json")
                    .body(jsonAsMap)
                    .when()
                    .post("/proffesor/create")
                    .then()
                    .statusCode(200);
        }
    @Test
        @DisplayName("Create CV: invalid student id")
        public void createCVWithValidStudent_AndGetError(){
            Map<String, Object> cv = new HashMap<>();
            cv.put("studentId", id);


            given().contentType("application/json")
                    .body(cv)
                    .when()
                    .post("/proffesor/create")
                    .then()
                    .statusCode(200);

        }

}
