package edu.kmaooad.capstone23.search.courses.by.skillset.handlers;

import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.competences.dal.SkillSet;
import edu.kmaooad.capstone23.competences.dal.SkillSetRepository;
import edu.kmaooad.capstone23.search.QueryEssenceHandler;
import edu.kmaooad.capstone23.search.courses.by.skillset.events.CourseQueriedBySkillSet;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QueryCourseBySkillSetHandler extends QueryEssenceHandler<
        Course,
        SkillSet,
        CourseRepository,
        SkillSetRepository,
        CourseQueriedBySkillSet
        >
{
    public QueryCourseBySkillSetHandler() {
        super (course -> course.id, group -> group.id, CourseQueriedBySkillSet::new);
    }
}