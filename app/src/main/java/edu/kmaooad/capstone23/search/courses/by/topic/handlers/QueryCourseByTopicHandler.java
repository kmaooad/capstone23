package edu.kmaooad.capstone23.search.courses.by.topic.handlers;

import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.competences.dal.Topic;
import edu.kmaooad.capstone23.competences.dal.TopicRepository;
import edu.kmaooad.capstone23.search.QueryEssenceHandler;
import edu.kmaooad.capstone23.search.courses.by.topic.events.CourseQueriedByTopic;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QueryCourseByTopicHandler extends QueryEssenceHandler<
        Course,
        Topic,
        CourseRepository,
        TopicRepository,
        CourseQueriedByTopic
        >
{
    public QueryCourseByTopicHandler() {
        super (course -> course.id, group -> group.id, CourseQueriedByTopic::new);
    }
}