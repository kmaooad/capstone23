
import edu.kmaooad.capstone23.cvs.dal.CV;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;

import java.util.Set;

public class ShowCV {

    @NotNull
    private ObjectId cvId;

    private CV.Status status;

    private CV.Visibility visibility;

    private String textInfo;

    private Set<ObjectId> skills;


    public ObjectId getCvId() {
        return cvId;
    }

    public void setCvId(ObjectId cvId) {
        this.cvId = cvId;
    }

    public CV.Status getStatus() {
        return status;
    }

    public void setStatus(CV.Status status) {
        this.status = status;
    }

    public CV.Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(CV.Visibility visibility) {
        this.visibility = visibility;
    }

    public String getTextInfo() {
        return textInfo;
    }

    public void setTextInfo(String textInfo) {
        this.textInfo = textInfo;
    }

    public Set<ObjectId> getSkills() {
        return skills;
    }

    public void setSkills(Set<ObjectId> skills) {
        this.skills = skills;
    }
}
