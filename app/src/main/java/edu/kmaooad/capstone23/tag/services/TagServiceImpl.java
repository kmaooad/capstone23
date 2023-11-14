package edu.kmaooad.capstone23.tag.services;

import edu.kmaooad.capstone23.tag.dal.Tag;
import edu.kmaooad.capstone23.tag.dal.TagRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.Optional;

@ApplicationScoped
public class TagServiceImpl implements TagService {

    @Inject
    private TagRepository tagRepository;

    public Optional<Tag> findByIdOptional(ObjectId id) {
        return tagRepository.findByIdOptional(id);
    }

    public void delete(Tag tag) {
        tagRepository.delete(tag);
    }

    public void persist(Tag tagEntity) {
        tagRepository.persist(tagEntity);
    }

    public void update(Tag tag) {
        tagRepository.update(tag);
    }

    public Tag findById(ObjectId id) {
        return tagRepository.findById(id);
    }
}
