package com.increments.riseuplabs.models;

import android.text.TextUtils;

public class Image {
    private String medium;
    private String original;

    public Image() {
    }

    public Image(String medium, String original) {
        this.medium = medium;
        this.original = original;
    }

    public String getMedium() {
        if (TextUtils.isEmpty(medium)){
            return "_";
        }
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getOriginal() {
        if (TextUtils.isEmpty(original)){
            return "_";
        }
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }
}
