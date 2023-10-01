package edu.kmaooad.capstone23.activities.events;

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
