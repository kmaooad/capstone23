package edu.kmaooad.capstone23.students.events;

import org.bson.types.ObjectId;

import java.util.List;

public record StudentsFound(List<ObjectId> students) {
}
