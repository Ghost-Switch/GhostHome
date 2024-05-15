package com.example.ghosthome.ui.addroom.device.model;

public class Item {
    private String text;
    private int imageResourceId;

    public Item(String text, int imageResourceId) {
        this.text = text;
        this.imageResourceId = imageResourceId;
    }

    public String getText() {
        return text;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
