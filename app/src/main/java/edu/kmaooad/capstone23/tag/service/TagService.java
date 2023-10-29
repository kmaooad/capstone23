package edu.kmaooad.capstone23.tag.service;

import edu.kmaooad.capstone23.tag.dal.Tag;

public interface TagService {
    public Tag find(String field, String value);
}
