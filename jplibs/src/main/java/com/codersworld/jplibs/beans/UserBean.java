package com.codersworld.jplibs.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserBean implements Serializable {

    @SerializedName("id")
    int id;
    @SerializedName("state")
    int state;
    @SerializedName("city")
    int city;
    @SerializedName("name")
    String name;
    @SerializedName("state_name")
    String state_name;
    @SerializedName("city_name")
    String city_name;
    @SerializedName("email")
    String email;
    @SerializedName("image")
    String image;
    @SerializedName("status")
    String status;

    public UserBean() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}