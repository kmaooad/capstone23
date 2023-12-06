package edu.kmaooad.capstone23.activities.dal;

import edu.kmaooad.capstone23.tag.dal.Tag;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.util.List;

@ApplicationScoped
public class ExtracurricularActivityRepository implements PanacheMongoRepository<ExtracurricularActivity> {

    public ExtracurricularActivity findById(String id) {
        return findById(new ObjectId(id));
    }

    public List<ExtracurricularActivity> findAllByTag(Tag tag) {
        return findAll().stream().filter(extracurricularActivity ->
                        extracurricularActivity.tags != null && extracurricularActivity.tags.contains(tag))
                .toList();
    }

    public ExtracurricularActivity insert(ExtracurricularActivity extracurricularActivity) {
        persist(extracurricularActivity);
        return extracurricularActivity;
    }

    public void delete(ExtracurricularActivity extracurricularActivity) {
        deleteById(extracurricularActivity.id);
    }

    public void deleteExtracurricularActivity(ExtracurricularActivity extracurricularActivity) {
        delete(extracurricularActivity);
    }

    public ExtracurricularActivity updateActivity(ExtracurricularActivity extracurricularActivity) {
        persistOrUpdate(extracurricularActivity);
        return  extracurricularActivity;
    }
}
