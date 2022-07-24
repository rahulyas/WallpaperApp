package com.example.wallpaperapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo {
    @SerializedName("src")
    @Expose
    private Src src;

    public Photo(Src src) {
        this.src = src;
    }

    public Src getSrc() {
        return src;
    }

    public void setSrc(Src src) {
        this.src = src;
    }
}
