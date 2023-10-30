package edu.kmaooad.capstone23.activities.services;

import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;

public interface ExtracurricularCourseService {

    ExtracurricularActivity insert(ExtracurricularActivity extracurricularActivity);

    ExtracurricularActivity findById(String id);

    void deleteExtracurricularActivity(ExtracurricularActivity extracurricularActivity);

    ExtracurricularActivity update(ExtracurricularActivity extracurricularActivity);
}
