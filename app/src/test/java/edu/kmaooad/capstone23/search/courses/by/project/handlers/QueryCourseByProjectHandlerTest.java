package edu.kmaooad.capstone23.search.courses.by.project.handlers;

import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.competences.dal.Project;
import edu.kmaooad.capstone23.competences.dal.MongoProjectRepository;
import edu.kmaooad.capstone23.relations.dal.Relation;
import edu.kmaooad.capstone23.relations.dal.RelationRepository;
import edu.kmaooad.capstone23.search.QueryByIdCommand;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class QueryCourseByProjectHandlerTest {
    @Inject
    QueryCourseByProjectHandler handler;

    @Inject
    CourseRepository courseRepository;

    @Inject
    MongoProjectRepository projectsRepository;

    @Inject
    RelationRepository relationRepository;

    private List<Course> defaultCourses;
    private List<Project> defaultProjects;
    private List<Relation> defaultRelations;

    private void testForProjectIndex(int projectToFindIndex) {
        assertTrue(projectToFindIndex < defaultProjects.size());

        var idOfProjectToFindBy = defaultProjects.get(projectToFindIndex).id;
        var command = new QueryByIdCommand();
        command.id = new ObjectId(idOfProjectToFindBy);

        var result = handler.handle(command);
        System.out.println(result.getMessage());
        assertTrue(result.isSuccess());

        var event = result.getValue();
        var foundCourses = event.queriedCourses();
        var manuallyFoundCourses = manualSearch(projectToFindIndex);

        assertTrue(areEqualSets(foundCourses, manuallyFoundCourses));
    }

    @Test
    @DisplayName("Basic Query")
    public void basicQueryTest() {
        testForProjectIndex(0);
    }

    @Test
    @DisplayName("Empty Query")
    public void emptyQueryTest() {
        testForProjectIndex(2);
    }

    @Test
    @DisplayName("Reverted Query")
    public void revertedQueryTest() {
        BiFunction<Integer, Integer, Relation> addRevertedRelation = (courseIndex, projectIndex) -> {
            var courseId = defaultCourses.get(courseIndex).id;
            var projectId = defaultProjects.get(projectIndex).id;
            var relation = new Relation(new ObjectId(projectId), courseId);

            var optCreatedRelation = relationRepository.createRelation("projects", "courses", relation);
            assertTrue(optCreatedRelation.isPresent());

            var createdRelation = optCreatedRelation.get();
            defaultRelations.add(createdRelation);

            return createdRelation;
        };

        var projectToFindByIndex = 4;
        var courseToFindIndex = 0;
        addRevertedRelation.apply(courseToFindIndex, projectToFindByIndex);

        var idOfProjectToFindBy = defaultProjects.get(projectToFindByIndex).id;
        var command = new QueryByIdCommand();
        command.id = new ObjectId(idOfProjectToFindBy);

        var result = handler.handle(command);
        assertTrue(result.isSuccess());

        var event = result.getValue();
        var foundCourses = event.queriedCourses();

        System.out.println();

        assertEquals(foundCourses.size(), 1);
        assertEquals(foundCourses.get(0).name, defaultCourses.get(courseToFindIndex).name);
    }

    private List<Course> manualSearch(int projectIndex) {
        var project = defaultProjects.get(projectIndex);
        var projectId = project.id;

        return defaultRelations
                .stream()
                .filter(relation -> relation.secondObjectId.equals(projectId))
                .map(Relation::getFirstObjectId)
                .map(id -> {
                    for(var course : defaultCourses) {
                        if(course.id.equals(id))
                            return course;
                    }
                    return null;
                })
                .toList();
    }

    private boolean isSubset(List<Course> subset, List<Course> set) {
        return subset
                .stream()
                .map(course -> course.name)
                .allMatch(name -> set.stream().anyMatch(course -> course.name.equals(name)));
    }

    private boolean areEqualSets(List<Course> set1, List<Course> set2) {
        return isSubset(set1, set2) && isSubset(set2, set1);
    }

    @BeforeEach
    public void setUp() {
        addDefaultCourses();
        addDefaultProjects();
        addDefaultRelations();
    }

    @AfterEach
    public void tearDown() {
        removeDefaultCourses();
        removeDefaultProjects();
        removeDefaultRelations();
    }

    private void addDefaultCourses() {
        defaultCourses = new ArrayList<>();
        defaultCourses.add(createCourse("course1"));
        defaultCourses.add(createCourse("course2"));
        defaultCourses.add(createCourse("course3"));
        defaultCourses.add(createCourse("course4"));

        defaultCourses = defaultCourses.stream()
                .map(courseRepository::insert)
                .toList();
    }

    private static Course createCourse(String name) {
        Course course = new Course();
        course.name = name;
        return course;
    }

    private void removeDefaultCourses() {
        defaultCourses.forEach(courseRepository::delete);
        defaultCourses = new ArrayList<>();
    }

    private void addDefaultProjects() {
        defaultProjects = new ArrayList<>();

        defaultProjects.add(createProject("project1"));
        defaultProjects.add(createProject("project2"));
        defaultProjects.add(createProject("project3"));
        defaultProjects.add(createProject("project4"));
        defaultProjects.add(createProject("project5"));

        defaultProjects = defaultProjects.stream()
                .map(projectsRepository::insert)
                .toList();
    }

    private static Project createProject(String name) {
        Project project = new Project();
        project.name = name;
        return project;
    }

    private void removeDefaultProjects() {
        defaultProjects.forEach(projectsRepository::delete);
        defaultProjects = new ArrayList<>();
    }

    private void addDefaultRelations() {
        defaultRelations = new ArrayList<>();

        var addRelation = new BiFunction<Integer, Integer, Relation>() {
            @Override
            public Relation apply(Integer courseIndex, Integer projectIndex) {
                var courseId = defaultCourses.get(courseIndex).id;
                var projectId = defaultProjects.get(projectIndex).id;
                var relation = new Relation(courseId, new ObjectId(projectId));

                var optCreatedRelation = relationRepository.createRelation("courses", "projects", relation);
                assertTrue(optCreatedRelation.isPresent());

                var createdRelation = optCreatedRelation.get();
                defaultRelations.add(createdRelation);

                return createdRelation;
            }
        };

        addRelation.apply(0, 0);
        addRelation.apply(1, 0);
        addRelation.apply(1, 1);
    }

    private void removeDefaultRelations() {
        defaultRelations.forEach(r -> relationRepository
                .deleteRelation("courses", "projects", r.getId()));
        defaultProjects = new ArrayList<>();
    }
}
