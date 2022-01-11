package com.jvt.devthread.healthcare.Activities.Model;

public class GalleryModel {
    String pic, description;

    public GalleryModel() {
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GalleryModel(String pic, String description) {
        this.pic = pic;
        this.description = description;
    }
}

