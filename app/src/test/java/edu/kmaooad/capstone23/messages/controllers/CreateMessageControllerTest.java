package edu.kmaooad.capstone23.messages.controllers;

import edu.kmaooad.capstone23.massages.dal.Message;
import edu.kmaooad.capstone23.massages.dal.MessagesRepository;
import edu.kmaooad.capstone23.students.dal.Student;
import edu.kmaooad.capstone23.students.dal.StudentRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;

import static io.restassured.RestAssured.given;




    @QuarkusTest
    public class CreateMessageControllerTest {

        @Inject
        MessagesRepository studentRepository;
        private static final String id = "65242f15a322e97788871167";

        @BeforeEach
        void setup() {

            Message student = new Message();
            student.id= new ObjectId(id);
            student.messageText = "cv is created";
            student.event_type = "cvCreated";
            student.method_of_sending = "sms";

            studentRepository.persist(student);
        }


        @AfterEach
        void clear(){
            studentRepository.deleteById(new ObjectId(id));
        }

        @ParameterizedTest
        @MethodSource("validCVProvider")
        @DisplayName("Create CV: valid values")
        public void createValidCV_AndGetStatus200(Map<String, Object> jsonAsMap) {
            given().contentType("application/json")
                    .body(jsonAsMap)
                    .when()
                    .post("/message/create")
                    .then()
                    .statusCode(200);
        }

    }