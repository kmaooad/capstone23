package edu.kmaooad.capstone23.activities.commands;
import org.bson.types.ObjectId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateExtraActivity {
    private String name;

    public UpdateExtraActivity(ObjectId id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    private ObjectId id;
    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}