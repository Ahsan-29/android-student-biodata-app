package com.example.retrofit_put_patch_delete_request;

import com.google.gson.annotations.SerializedName;

public class Post {

    private int id;

    private String email;

    private String password;

    private String name;

    private String School;

    public Post(int id, String email, String password, String name, String school)
    {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.School = school;

    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSchool() {
        return School;
    }
}