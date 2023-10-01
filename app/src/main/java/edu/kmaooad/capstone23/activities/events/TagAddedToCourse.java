package edu.kmaooad.capstone23.activities.events;

import edu.kmaooad.capstone23.tag.dal.Tag;

import java.util.List;

public class TagAddedToCourse {

    String courseName;
    String tagName;


    public TagAddedToCourse(String courseName, String tagName) {
        this.courseName = courseName;
        this.tagName = tagName;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getTagName() {
        return tagName;
    }
}
