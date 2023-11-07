package edu.kmaooad.capstone23.students.controllers;

import edu.kmaooad.capstone23.students.dal.Student;
import edu.kmaooad.capstone23.students.dal.StudentService;
import edu.kmaooad.capstone23.students.parser.CSVStudent;
import edu.kmaooad.capstone23.students.parser.CreateCSVStudentParser;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class UpdateStudentControllerTest {

    @Inject
    StudentService repository;
    @Inject
    CreateCSVStudentParser createCSVStudentParser;

    private ObjectId idToUpdate;

    @BeforeEach
    void setup() {
        try {
            List<CSVStudent> csvStudents = createCSVStudentParser.parse(new File("src/test/resources/students/success.csv"));
            List<Student> students = repository.insert(csvStudents);
            idToUpdate = students.get(0).id;
        } catch (Exception ignored) {

        }
    }

    @Test
    @DisplayName("Update student's first name from csv: Success")
    public void testStudentsUpdateFirstNameFromCSVSuccess() {
        String path = "src/test/resources/students/update/testStudentsUpdateFirstNameFromCSVSuccess.csv";
        String student = idToUpdate + ",Makii,,,06/12/2002,ivan.dobrovolskyi@ukma.edu.ua";
        writeToFile(student, path);
        given()
                .multiPart("csvFile", new File(path), "text/csv")
                .when()
                .post("/students/update_csv")
                .then()
                .statusCode(200);
        File file = new File(path);
        file.delete();
    }

    @Test
    @DisplayName("Update student's middle name from csv: Success")
    public void testStudentsUpdateMiddleNameFromCSVSuccess() {
        String path = "src/test/resources/students/update/testStudentsUpdateMiddleNameFromCSVSuccess.csv";
        String student = idToUpdate + ",,Kyrylovych,,06/12/2002,ivan.dobrovolskyi@ukma.edu.ua";
        writeToFile(student, path);
        given()
                .multiPart("csvFile", new File(path), "text/csv")
                .when()
                .post("/students/update_csv")
                .then()
                .statusCode(200);
        File file = new File(path);
        file.delete();
    }

    @Test
    @DisplayName("Update student's last name from csv: Success")
    public void testStudentsUpdateLastNameFromCSVSuccess() {
        String path = "src/test/resources/students/update/testStudentsUpdateLastNameFromCSVSuccess.csv";
        String student = idToUpdate + ",,,Petrenko,06/12/2002,ivan.dobrovolskyi@ukma.edu.ua";
        writeToFile(student, path);
        given()
                .multiPart("csvFile", new File(path), "text/csv")
                .when()
                .post("/students/update_csv")
                .then()
                .statusCode(200);
        File file = new File(path);
        file.delete();
    }

    @Test
    @DisplayName("Update student's DOB from csv: Success")
    public void testStudentsUpdateDOBFromCSVSuccess() {
        String path = "src/test/resources/students/update/testStudentsUpdateDOBFromCSVSuccess.csv";
        String student = idToUpdate + ",,,,12/12/2002,ivan.dobrovolskyi@ukma.edu.ua";
        writeToFile(student, path);
        given()
                .multiPart("csvFile", new File(path), "text/csv")
                .when()
                .post("/students/update_csv")
                .then()
                .statusCode(200);
        File file = new File(path);
        file.delete();
    }

    @Test
    @DisplayName("Update student's email from csv: Success")
    public void testStudentsUpdateEmailFromCSVSuccess() {
        String path = "src/test/resources/students/update/testStudentsUpdateEmailFromCSVSuccess.csv";
        String student = idToUpdate + ",,,,,ivan2.dobrovolskyi@ukma.edu.ua";
        writeToFile(student, path);
        given()
                .multiPart("csvFile", new File(path), "text/csv")
                .when()
                .post("/students/update_csv")
                .then()
                .statusCode(200);
        File file = new File(path);
        file.delete();
    }

    @Test
    @DisplayName("Update student's email from csv: Invalid email")
    public void testStudentsUpdateEmailFromCSVInvalidEmail() {
        String path = "src/test/resources/students/update/testStudentsUpdateEmailFromCSVInvalidEmail.csv";
        String student = idToUpdate + ",,,,,ivan2.dobrovolskyi@";
        writeToFile(student, path);
        given()
                .multiPart("csvFile", new File(path), "text/csv")
                .when()
                .post("/students/update_csv")
                .then()
                .statusCode(400);
        File file = new File(path);
        file.delete();
    }

    @Test
    @DisplayName("Update students from csv: Invalid DOB")
    public void testStudentsUpdateFromCSVInvalidDOB() {
        String path = "src/test/resources/students/update/testStudentsUpdateFromCSVInvalidDOB.csv";
        String student = idToUpdate + ",Makii,,,12/2002,ivan.dobrovolskyi@ukma.edu.ua";
        writeToFile(student, path);
        given()
                .multiPart("csvFile", new File(path), "text/csv")
                .when()
                .post("/students/update_csv")
                .then()
                .statusCode(400);
        File file = new File(path);
        file.delete();
    }

    @Test
    @DisplayName("Update students from csv: Incorrect file type")
    public void testStudentsUpdateFromCSVIncorrectType() {
        given()
                .multiPart("csvFile", new File("src/test/resources/students/incorrect_type.json"), "application/json")
                .when()
                .post("/students/update_csv")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Update students from csv: Not enough fields")
    public void testStudentsUpdateFromCSVNotEnoughFields() {
        given()
                .multiPart("csvFile", new File("src/test/resources/students/update/missing_id.csv"), "application/json")
                .when()
                .post("/students/update_csv")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Update students from csv: Too many fields")
    public void testStudentsUpdateFromCSVTooManyFields() {
        given()
                .multiPart("csvFile", new File("src/test/resources/students/update/too_many_fields.csv"), "application/json")
                .when()
                .post("/students/update_csv")
                .then()
                .statusCode(400);
    }


    public void writeToFile(String student, String pathStr) {
        try {
            FileWriter myWriter = new FileWriter(pathStr);
            myWriter.write(student);
            myWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
