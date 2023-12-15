package com.codersworld.jplibs.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BusRoutesBean implements Serializable {
    @SerializedName("id")
    int id;
    @SerializedName("bus")
    String bus;
    @SerializedName("kms")
    String kms;

    public BusRoutesBean() {
    }

    public BusRoutesBean(String bus, String kms) {
        this.bus = bus;
        this.kms = kms;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getKms() {
        return kms;
    }

    public void setKms(String kms) {
        this.kms = kms;
    }
}
