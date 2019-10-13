package com.example.retrofitexample;

import com.google.gson.annotations.SerializedName;

public class Post {
    private Integer id;
    private Integer userId;
    private String title;
    @SerializedName("body")
    private String text;

    public Post(Integer userId, String title, String text) {
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
