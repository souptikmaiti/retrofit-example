package com.example.retrofitexample;

import com.google.gson.annotations.SerializedName;

public class Comment {
    Integer postId;
    Integer id;
    String name;
    String email;
    @SerializedName("body")
    String text;

    public Integer getPostId() {
        return postId;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getText() {
        return text;
    }
}
