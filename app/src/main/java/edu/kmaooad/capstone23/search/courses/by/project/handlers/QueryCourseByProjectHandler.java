package edu.kmaooad.capstone23.search.courses.by.project.handlers;

import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.competences.dal.Project;
import edu.kmaooad.capstone23.competences.dal.ProjsRepository;
import edu.kmaooad.capstone23.search.QueryEssenceHandler;
import edu.kmaooad.capstone23.search.courses.by.project.events.CourseQueriedByProject;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QueryCourseByProjectHandler extends QueryEssenceHandler<
        Course,
        Project,
        CourseRepository,
        ProjsRepository,
        CourseQueriedByProject
        >
{
    public QueryCourseByProjectHandler() {
        super(course -> course.id, project -> project.id, CourseQueriedByProject::new);
    }
}
