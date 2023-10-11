package edu.kmaooad.capstone23.activities.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class CourseRepository implements PanacheMongoRepository<Course> {
    public Optional<Course> findById(String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            return findByIdOptional(objectId);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public long findCoursesCountByIds(List<String> ids) {
        return ids.stream().filter(id -> {
            var course = findById(id);
            return course.isPresent();
        }).count();
    }

    public Course insert(Course course) {
        persist(course);
        return course;
    }

    public List<Course> bulkInsert(List<Course> courses) {
        persist(courses);
        return courses;
    }
    public List<Course> bulkUpdate(List<Course> courses) {
        persistOrUpdate(courses);
        return courses;
    }

    public void bulkDelete(List<Course> courses) {

        List<ObjectId> objectIds = courses.stream()
                .map(id -> new ObjectId(id.toString()))
                .collect(Collectors.toList());

        delete("id in ?1", objectIds);
    }


}
