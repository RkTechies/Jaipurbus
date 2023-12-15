package com.codersworld.jplibs.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class CommonBean implements Serializable {

    @SerializedName("status")
    int status;
    @SerializedName("msg")
    String msg;
    @SerializedName("info")
    ArrayList<PlacesTypeBean> info;
    @SerializedName("places")
    ArrayList<PlacesBean> places;
    @SerializedName("states")
    ArrayList<StateBean> states;
    @SerializedName("ticket")
    TicketBean ticket;
    @SerializedName("user")
    UserBean user;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public ArrayList<StateBean> getStates() {
        return states;
    }

    public void setStates(ArrayList<StateBean> states) {
        this.states = states;
    }

    public TicketBean getTicket() {
        return ticket;
    }

    public void setTicket(TicketBean ticket) {
        this.ticket = ticket;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<PlacesTypeBean> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<PlacesTypeBean> info) {
        this.info = info;
    }

    public ArrayList<PlacesBean> getPlaces() {
        return places;
    }

    public void setPlaces(ArrayList<PlacesBean> places) {
        this.places = places;
    }
}
