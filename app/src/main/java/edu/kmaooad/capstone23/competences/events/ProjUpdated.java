package edu.kmaooad.capstone23.competences.events;
import org.bson.types.ObjectId;

import java.util.List;
public record ProjUpdated (
        String projId,
        String name,
        String description,
        List<ObjectId> skills,
        List<ObjectId> skillSets
) {
}