package com.example.ghosthome.ui.addroom.device.model;

public class SpinnerItem {
    private String text;
    private int imageResourceId;

    public SpinnerItem(String text, int imageResourceId) {
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
