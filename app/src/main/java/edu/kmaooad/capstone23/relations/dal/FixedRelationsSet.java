package edu.kmaooad.capstone23.relations.dal;

import jakarta.enterprise.context.ApplicationScoped;
import org.antlr.v4.runtime.misc.Pair;

import java.util.Set;
import java.util.TreeSet;

@ApplicationScoped
public class FixedRelationsSet {
    private final Set<Pair<String, String>> possibleRelationsBetweenCollections;

    public FixedRelationsSet() {
        possibleRelationsBetweenCollections = new TreeSet<>((fst, snd) -> {
            var cmp1 = fst.a.compareTo(snd.b);
            return cmp1 == 0
                    ? fst.b.compareTo(snd.b)
                    : cmp1;
        });

        fillThePossibleRelationsBetweenCollections();
    }

    private void fillThePossibleRelationsBetweenCollections() {
        emplacePossibleRelationBetweenCollections("courses", "projects");
        emplacePossibleRelationBetweenCollections("courses", "skill sets");
    }

    private void emplacePossibleRelationBetweenCollections(String collectionName1, String collectionName2) {
        var placed = possibleRelationsBetweenCollections.add(new Pair<>(collectionName1, collectionName2));
        assert placed;
    }

    public boolean relationExists(String collectionName1, String collectionName2) {
        return possibleRelationsBetweenCollections.contains(new Pair<>(collectionName1, collectionName2));
    }
}