package edu.kmaooad.capstone23.cvs.controllers;

import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.students.dal.Student;
import edu.kmaooad.capstone23.students.dal.StudentRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CreateCVControllerTests {

    @Inject
    StudentRepository studentRepository;
    private static final String id = "65242f15a322e97788871167";

    @BeforeEach
    void setup() {
        Student student = new Student();
        student.id= new ObjectId(id);
        student.firstName = "name";
        student.middleName = "name";
        student.lastName = "name";
        student.DOBTimestamp = 1039132800000L;
        student.email = "email@mail.com";

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
                .post("/cvs/create")
                .then()
                .statusCode(200);
    }

    @ParameterizedTest
    @MethodSource("invalidCVProvider")
    @DisplayName("Create CV: invalid values")
    public void createInvalidCV_AndGetError(Map<String, Object> jsonAsMap) {
        given().contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/cvs/create")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Create CV: invalid student id")
    public void createCVWithValidStudent_AndGetError(){
        Map<String, Object> cv = new HashMap<>();
        cv.put("studentId", id);
        cv.put("dateTimeCreated", LocalDateTime.now());
        cv.put("textInfo", "some info about a student");
        cv.put("status", CV.Status.OPEN);
        cv.put("visibility", CV.Visibility.VISIBLE);
        cv.put("autoAddCompetences", false);

        given().contentType("application/json")
                .body(cv)
                .when()
                .post("/cvs/create")
                .then()
                .statusCode(200);

    }

    @Test
    @DisplayName("Create CV: invalid student id")
    public void createCVWithInvalidStudent_AndGetError(){
        Map<String, Object> cv = new HashMap<>();
        cv.put("studentId", "invalid_id");
        cv.put("dateTimeCreated", LocalDateTime.now());
        cv.put("textInfo", "some info about a student");
        cv.put("status", CV.Status.OPEN);
        cv.put("visibility", CV.Visibility.VISIBLE);
        cv.put("autoAddCompetences", false);

        given().contentType("application/json")
                .body(cv)
                .when()
                .post("/cvs/create")
                .then()
                .statusCode(400);

    }

    public static Stream<Arguments> validCVProvider() {
        Map<String, Object> cvOpenVisible = new HashMap<>();
        cvOpenVisible.put("studentId", id);
        cvOpenVisible.put("dateTimeCreated", LocalDateTime.now());
        cvOpenVisible.put("textInfo", "some info about a student");
        cvOpenVisible.put("status", CV.Status.OPEN);
        cvOpenVisible.put("visibility", CV.Visibility.VISIBLE);
        cvOpenVisible.put("autoAddCompetences", false);

        Map<String, Object> cvClosedHidden = new HashMap<>();
        cvClosedHidden.put("studentId", id);
        cvClosedHidden.put("dateTimeCreated", LocalDateTime.now());
        cvClosedHidden.put("status", CV.Status.CLOSED);
        cvClosedHidden.put("visibility", CV.Visibility.HIDDEN);
        cvClosedHidden.put("autoAddCompetences", false);

        Map<String, Object> cvNoTextInfo = new HashMap<>();
        cvNoTextInfo.put("studentId", id);
        cvNoTextInfo.put("dateTimeCreated", LocalDateTime.now());
        cvNoTextInfo.put("status", CV.Status.OPEN);
        cvNoTextInfo.put("visibility", CV.Visibility.VISIBLE);
        cvNoTextInfo.put("autoAddCompetences", false);

        return Stream.of(
                Arguments.of(Named.of("open, visible", cvOpenVisible)),
                Arguments.of(Named.of("closed, hidden", cvClosedHidden)),
                Arguments.of(Named.of("no text info", cvNoTextInfo))
        );
    }

    public static Stream<Arguments> invalidCVProvider() {
        Map<String, Object> cvInvalidDateTime = new HashMap<>();
        cvInvalidDateTime.put("studentId", id);
        cvInvalidDateTime.put("dateTimeCreated", LocalDateTime.now().plusHours(1));
        cvInvalidDateTime.put("textInfo", "some info about a student");
        cvInvalidDateTime.put("status", CV.Status.OPEN);
        cvInvalidDateTime.put("visibility", CV.Visibility.VISIBLE);
        cvInvalidDateTime.put("autoAddCompetences", false);

        Map<String, Object> cvBlankTextInfo = new HashMap<>();
        cvBlankTextInfo.put("studentId", id);
        cvBlankTextInfo.put("dateTimeCreated", LocalDateTime.now());
        cvBlankTextInfo.put("textInfo", " ");
        cvBlankTextInfo.put("status", CV.Status.CLOSED);
        cvBlankTextInfo.put("visibility", CV.Visibility.HIDDEN);
        cvBlankTextInfo.put("autoAddCompetences", false);

        Map<String, Object> cvNoStatus = new HashMap<>();
        cvNoStatus.put("studentId", id);
        cvNoStatus.put("dateTimeCreated", LocalDateTime.now());
        cvNoStatus.put("textInfo", "abc");
        cvNoStatus.put("visibility", CV.Visibility.VISIBLE);
        cvNoStatus.put("autoAddCompetences", false);

        Map<String, Object> cvNoVisibility = new HashMap<>();
        cvNoVisibility.put("studentId", id);
        cvNoVisibility.put("dateTimeCreated", LocalDateTime.now());
        cvNoVisibility.put("textInfo", "abc");
        cvNoVisibility.put("status", CV.Status.OPEN);
        cvNoVisibility.put("autoAddCompetences", false);

        Map<String, Object> cvNoDateTime = new HashMap<>();
        cvNoDateTime.put("studentId", id);
        cvNoDateTime.put("textInfo", "abc");
        cvNoDateTime.put("status", CV.Status.OPEN);
        cvNoDateTime.put("visibility", CV.Visibility.VISIBLE);
        cvNoDateTime.put("autoAddCompetences", false);

        return Stream.of(
                Arguments.of(Named.of("invalid date", cvInvalidDateTime)),
                Arguments.of(Named.of("blank text info", cvBlankTextInfo)),
                Arguments.of(Named.of("no status", cvNoStatus)),
                Arguments.of(Named.of("no visibility", cvNoVisibility)),
                Arguments.of(Named.of("no date", cvNoDateTime))
        );
    }

}
