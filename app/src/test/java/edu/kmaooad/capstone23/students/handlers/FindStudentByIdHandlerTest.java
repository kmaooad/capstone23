package edu.kmaooad.capstone23.students.handlers;

import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.students.commands.ReadStudent;
import edu.kmaooad.capstone23.students.dal.Student;
import edu.kmaooad.capstone23.students.dal.StudentRepository;
import edu.kmaooad.capstone23.students.events.StudentRead;
import edu.kmaooad.capstone23.students.parser.CSVStudent;
import edu.kmaooad.capstone23.students.parser.CreateCSVStudentParser;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.*;

import java.io.File;
import java.util.List;

@QuarkusTest
public class FindStudentByIdHandlerTest {
    @Inject
    StudentRepository studentRepository;
    @Inject
    CreateCSVStudentParser createCSVStudentParser;
    private ObjectId studentId;

    @Inject
    FindStudentByIdHandler findStudentByIdHandler;

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
    public void clean() {
        studentRepository.deleteAll();
    }

    @Test
    @DisplayName("Find student by id: Success")
    public void testFindStudentByIdSuccess() {
        ReadStudent readStudent = new ReadStudent();
        readStudent.setId(String.valueOf(studentId));
        Result<StudentRead> result = findStudentByIdHandler.handle(readStudent);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertEquals(1, result.getValue().students().size());
        Assertions.assertEquals("Ivan", result.getValue().students().get(0).firstName);
        Assertions.assertEquals("ivan.dobrovolskyi@ukma.edu.ua", result.getValue().students().get(0).email);
    }

    @Test
    @DisplayName("Find student by id: Fail")
    public void testFindStudentByIdFail() {
        ReadStudent readStudent = new ReadStudent();
        readStudent.setId(studentId + "q");
        Result<StudentRead> result = findStudentByIdHandler.handle(readStudent);
        Assertions.assertFalse(result.isSuccess());
        Assertions.assertNull(result.getValue());
    }

    @Test
    @DisplayName("Find student by id: no params")
    public void testFindStudentByIdNoParams() {
        ReadStudent readStudent = new ReadStudent();
        Result<StudentRead> result = findStudentByIdHandler.handle(readStudent);
        Assertions.assertFalse(result.isSuccess());
        Assertions.assertNull(result.getValue());
    }
}
