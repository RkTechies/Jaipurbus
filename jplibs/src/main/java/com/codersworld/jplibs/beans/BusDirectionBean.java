package com.codersworld.jplibs.beans;

import java.io.Serializable;

public class BusDirectionBean implements Serializable {
    public int icon1;
    public String root1;
    public int icon2;
    public String root2;
    public int arrow,seedetail;;
    public String stops;
    public String km;
    public boolean source,destination;
    public BusDirectionBean(int icon1,String root1,int arrow,int icon2,String root2,String stops,String km){

        this.icon1 = icon1;
        this.root1 = root1;
        this.arrow = arrow;
        this.icon2 = icon2;
        this.root2 = root2;
        this.km = km;
        this.stops = stops;


    }

    public int getIcon1() {
        return icon1;
    }

    public void setIcon1(int icon1) {
        this.icon1 = icon1;
    }

    public String getRoot1() {
        return root1;
    }

    public void setRoot1(String root1) {
        this.root1 = root1;
    }

    public int getIcon2() {
        return icon2;
    }

    public void setIcon2(int icon2) {
        this.icon2 = icon2;
    }

    public String getRoot2() {
        return root2;
    }

    public void setRoot2(String root2) {
        this.root2 = root2;
    }

    public int getArrow() {
        return arrow;
    }

    public void setArrow(int arrow) {
        this.arrow = arrow;
    }

    public int getSeedetail() {
        return seedetail;
    }

    public void setSeedetail(int seedetail) {
        this.seedetail = seedetail;
    }

    public String getStops() {
        return stops;
    }

    public void setStops(String stops) {
        this.stops = stops;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public boolean isSource() {
        return source;
    }

    public void setSource(boolean source) {
        this.source = source;
    }

    public boolean isDestination() {
        return destination;
    }

    public void setDestination(boolean destination) {
        this.destination = destination;
    }
}
