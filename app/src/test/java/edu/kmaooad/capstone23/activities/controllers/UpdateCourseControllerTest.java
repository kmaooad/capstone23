package edu.kmaooad.capstone23.activities.controllers;

        import edu.kmaooad.capstone23.activities.dal.Course;
        import edu.kmaooad.capstone23.activities.dal.CourseRepository;
        import io.quarkus.test.junit.QuarkusTest;
        import jakarta.inject.Inject;
        import org.bson.types.ObjectId;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.DisplayName;
        import org.junit.jupiter.api.Test;
        import java.util.HashMap;
        import java.util.Map;
        import static io.restassured.RestAssured.given;

@QuarkusTest
    public class UpdateCourseControllerTest {

    private ObjectId idToUpdate;

    @Inject
    CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        Course course = new Course();
        course.name = "Initial Course";
        courseRepository.insert(course);
        idToUpdate = course.id;

    }

    @Test
    @DisplayName("Update Course: Non-Existent Course")
    public void testUpdateNonExistentCourse() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", "nonExistentId");
        jsonAsMap.put("courseName", "Updated Course");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/activities/courses/update")
                .then()
                .statusCode(400);
    }



    @Test
    @DisplayName("Update Course: Invalid Course Name")
    public void testUpdateWithInvalidCourseName() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", idToUpdate.toString());
        jsonAsMap.put("courseName", "Invalid Course @123");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/activities/courses/update")
                .then()
                .statusCode(400);
    }
}