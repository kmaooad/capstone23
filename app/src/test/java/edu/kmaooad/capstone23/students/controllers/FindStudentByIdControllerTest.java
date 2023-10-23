package edu.kmaooad.capstone23.students.controllers;

import edu.kmaooad.capstone23.students.dal.Student;
import edu.kmaooad.capstone23.students.dal.StudentRepository;
import edu.kmaooad.capstone23.students.parser.CSVStudent;
import edu.kmaooad.capstone23.students.parser.CreateCSVStudentParser;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
public class FindStudentByIdControllerTest {
    @Inject
    StudentRepository studentRepository;
    @Inject
    CreateCSVStudentParser createCSVStudentParser;

    private ObjectId studentId;

    @BeforeEach
    public void setup() {
        studentRepository.deleteAll();
        try {
            List<CSVStudent> csvStudents = createCSVStudentParser.parse(new File("src/test/resources/students/success.csv"));
            List<Student> students = studentRepository.insert(csvStudents);
            studentId = students.get(0).id;
        } catch (Exception ignored) {

        }
    }

    @AfterEach
    public void clear() {
        studentRepository.deleteAll();
    }

    @Test
    @DisplayName("Find student by id: Success")
    public void testFindStudentByIdSuccess() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", studentId.toString());
        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/students/id")
                .then()
                .statusCode(200)
                .assertThat()
                .body("students", hasSize(1));
    }

    @Test
    @DisplayName("Find student by id: Wrong id")
    public void testFindStudentByIdWrongId() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", "4513908509458");
        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/students/id")
                .then()
                .statusCode(400);
    }
}
