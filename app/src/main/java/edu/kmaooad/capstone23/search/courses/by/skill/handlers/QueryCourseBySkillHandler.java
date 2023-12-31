package edu.kmaooad.capstone23.search.courses.by.skill.handlers;

import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.competences.dal.Skill;
import edu.kmaooad.capstone23.competences.dal.MongoSkillsRepository;
import edu.kmaooad.capstone23.search.QueryEssenceHandler;
import edu.kmaooad.capstone23.search.courses.by.skill.events.CourseQueriedBySkill;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QueryCourseBySkillHandler extends QueryEssenceHandler<
        Course,
        Skill,
        CourseRepository,
        MongoSkillsRepository,
        CourseQueriedBySkill
        >
{
    public QueryCourseBySkillHandler() {
        super (course -> course.id, skill -> skill.id, CourseQueriedBySkill::new);
    }
}
