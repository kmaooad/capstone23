package edu.kmaooad.capstone23.profile_image.commands;

import org.bson.types.ObjectId;

public class AddImage {
    private String imageBase64;
    private ObjectId userId;
    
    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

}
