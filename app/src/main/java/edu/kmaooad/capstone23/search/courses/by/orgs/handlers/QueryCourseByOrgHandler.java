package edu.kmaooad.capstone23.search.courses.by.orgs.handlers;

import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.search.QueryEssenceHandler;
import edu.kmaooad.capstone23.search.courses.by.orgs.events.CourseQueriedByOrg;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QueryCourseByOrgHandler extends QueryEssenceHandler<
        Course,
        Org,
        CourseRepository,
        OrgsRepository,
        CourseQueriedByOrg
        >
{
    public QueryCourseByOrgHandler() {
        super (course -> course.id, org -> org.id, CourseQueriedByOrg::new);
    }
}