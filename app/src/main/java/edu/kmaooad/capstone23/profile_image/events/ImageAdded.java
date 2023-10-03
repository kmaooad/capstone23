package edu.kmaooad.capstone23.profile_image.events;

public class ImageAdded {
    private String imageBase64;

    public String getImageBase64() {
        return imageBase64;
    }

    public ImageAdded(String imageBase64) {
        this.imageBase64 = imageBase64;
    }
}
