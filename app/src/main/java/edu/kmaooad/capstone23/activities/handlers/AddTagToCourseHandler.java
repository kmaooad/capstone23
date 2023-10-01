package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.AddTagToCourse;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.activities.events.TagAddedToCourse;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.tag.dal.TagRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Objects;

@ApplicationScoped
public class AddTagToCourseHandler implements CommandHandler<AddTagToCourse, TagAddedToCourse> {

    @Inject
    TagRepository tagRepository;
    @Inject
    CourseRepository courseRepository;

    @Override
    public Result<TagAddedToCourse> handle(AddTagToCourse command) {
        var course = courseRepository.find("name", command.getCourseName()).firstResult();
        var tag = tagRepository.find("tagName", command.getTagName()).firstResult();

        if (tag == null) {
            return new Result<>(ErrorCode.NOT_FOUND, "Tag not found");
        }

        if (course == null) {
            return new Result<>(ErrorCode.NOT_FOUND, "Course not found");
        }
        course.tags.add(tag);
        courseRepository.update(course);
        var tagAddedToCourse = new TagAddedToCourse(course.name, tag.tagName);
        return new Result<>(tagAddedToCourse);
    }
}
