package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.FindCoursesByTag;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.activities.events.CoursesFoundByTag;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.tag.dal.TagRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FindCoursesByTagNameHandler implements CommandHandler<FindCoursesByTag, CoursesFoundByTag> {
    @Inject
    TagRepository tagRepository;
    @Inject
    CourseRepository courseRepository;

    @Override
    public Result<CoursesFoundByTag> handle(FindCoursesByTag command) {
        var tag = tagRepository.find("tagName", command.tagName).firstResult();
        if (tag == null) {
            return new Result<>(ErrorCode.NOT_FOUND, "Tag not found");
        }

        var courses = courseRepository.findByTag(tag);
        var foundCourses = new CoursesFoundByTag(courses);
        return new Result<>(foundCourses);
    }

}
