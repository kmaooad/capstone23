package edu.kmaooad.capstone23.tag.service;

import edu.kmaooad.capstone23.tag.dal.Tag;
import edu.kmaooad.capstone23.tag.dal.TagRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TagServiceImpl implements TagService {
    @Inject
    private TagRepository tagRepository;


    @Override
    public Tag find(String field, String value) {
        return tagRepository.find(field, value).firstResult();
    }
}
