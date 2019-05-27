package com.github.aniketc.android.model;

import org.parceler.Parcel;


@Parcel
public class Photo {
    String image;
    String location;
    String comment;
    String date;

    public Photo(){

    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public String getComment() {
        return comment;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }
}
