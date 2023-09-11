package edu.kmaooad.capstone23.activities.events;

import java.util.Date;

public class ExtracurricularActivityUpdated {

    private String id;
    private String name;
    private Date extracurricularActivityDate;

    public String getName() {
        return name;
    }

    public Date getExtracurricularActivityDate() {
        return extracurricularActivityDate;
    }

    public ExtracurricularActivityUpdated(String id, String name, Date extracurricularActivityDate) {
        this.id = id;
        this.name = name;
        this.extracurricularActivityDate = extracurricularActivityDate;
    }
}

