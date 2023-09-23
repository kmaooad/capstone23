package edu.kmaooad.capstone23.cvs.events;

import edu.kmaooad.capstone23.cvs.dal.CV;

import java.util.List;

public class CVRead {

    private final List<CV> cvs;

    public CVRead(List<CV> cvs) {
        this.cvs = cvs;
    }

    public List<CV> getCvs() {
        return cvs;
    }
}
