package com.artemis.handaako.adapter_list_home;

import android.graphics.drawable.Drawable;

public class HomeItem {
    private String name;
    private String description;
    private Integer bgColor;
    private Drawable imageURL;

    public HomeItem(String name, String description, Integer bgColor, Drawable imageURL) {
        this.name = name;
        this.description = description;
        this.bgColor = bgColor;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getBgColor() {
        return bgColor;
    }

    public void setBgColor(Integer bgColor) {
        this.bgColor = bgColor;
    }

    public Drawable getImageURL() {
        return imageURL;
    }

    public void setImageURL(Drawable imageURL) {
        this.imageURL = imageURL;
    }
}
