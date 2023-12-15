package com.codersworld.jplibs.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StateBean implements Serializable {

    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;

    public StateBean() {
    }

    public StateBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}