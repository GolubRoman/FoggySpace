package com.golubroman.golub.foggyspace.Shop.Fragments.Review;

/**
 * Created by roman on 11.07.17.
 */

public class ReviewElement {

    private String name, text, time, email, photoUrl, avatar;

    public ReviewElement(){}

    public ReviewElement(String name, String text, String time,
                         String email, String photoUrl, String avatar){
        this.name = name;
        this.text = text;
        this.time = time;
        this.email = email;
        this.photoUrl = photoUrl;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
