package com.artemis.handaako.adapter_list_learn;

import android.graphics.drawable.Drawable;

public class LearnItem {

    private String itemName;
    private Drawable itemImage;
    private Integer itemColor;

    public LearnItem(String itemName, Drawable itemImage, Integer itemColor) {
        this.itemName = itemName;
        this.itemImage = itemImage;
        this.itemColor = itemColor;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Drawable getItemImage() {
        return itemImage;
    }

    public void setItemImage(Drawable itemImage) {
        this.itemImage = itemImage;
    }

    public Integer getItemColor() {
        return itemColor;
    }

    public void setItemColor(Integer itemColor) {
        this.itemColor = itemColor;
    }
}
