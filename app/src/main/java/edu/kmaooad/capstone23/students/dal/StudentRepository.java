package edu.kmaooad.capstone23.students.dal;

import edu.kmaooad.capstone23.search.builder.PrefixSearchParam;
import edu.kmaooad.capstone23.search.builder.SearchBuilder;
import edu.kmaooad.capstone23.students.commands.FindStudent;
import edu.kmaooad.capstone23.students.parser.CSVStudent;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class StudentRepository implements PanacheMongoRepository<Student> {
    public List<Student> insert(List<CSVStudent> students){
        List<Student> result = new ArrayList<>();
        for (CSVStudent student : students) {
            result.add(map(student));
        }
        persist(result);
        return result;
    }

    public List<Student> find(FindStudent query) {
        var builder = new SearchBuilder();
        if (query.getFirstName() != null)
            builder.and(new PrefixSearchParam("firstName", query.getFirstName()));
        if (query.getMiddleName() != null)
            builder.and(new PrefixSearchParam("middleName", query.getMiddleName()));
        if (query.getLastName() != null)
            builder.and(new PrefixSearchParam("lastName", query.getLastName()));
        var searchPair = builder.build();

        return find(searchPair.a, searchPair.b)
                .page(query.getPage(), query.getSize())
                .list();
    }

    private Student map(CSVStudent student){
        Student result = new Student();
        result.firstName = student.getFirstName();
        result.middleName = student.getMiddleName();
        result.lastName = student.getLastName();
        result.DOBTimestamp = student.getDOBTimestamp();
        result.email = student.getEmail();
        return result;
    }
}
