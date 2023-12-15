package com.codersworld.jplibs.beans;

import java.io.Serializable;

public class ResultRouteBean implements Serializable {
    public int icon1,no,imgBus2;
    public String stop1,noStops,stop,endStop,noBus;
    public boolean source,destination;
    public ResultRouteBean(){
        super();
    }
    public ResultRouteBean(String stop1,String noStops,String noBus,String stop,String endStop,boolean source,boolean destination,int no,int imgBus2){
        this.stop1=stop1;
        this.noStops=noStops;
        this.noBus=noBus;
        this.stop=stop;
        this.endStop=endStop;
        this.source = source;
        this.destination = destination;
        this.no=no;
        this.imgBus2=imgBus2;
    }

    public int getIcon1() {
        return icon1;
    }

    public void setIcon1(int icon1) {
        this.icon1 = icon1;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getImgBus2() {
        return imgBus2;
    }

    public void setImgBus2(int imgBus2) {
        this.imgBus2 = imgBus2;
    }

    public String getStop1() {
        return stop1;
    }

    public void setStop1(String stop1) {
        this.stop1 = stop1;
    }

    public String getNoStops() {
        return noStops;
    }

    public void setNoStops(String noStops) {
        this.noStops = noStops;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public String getEndStop() {
        return endStop;
    }

    public void setEndStop(String endStop) {
        this.endStop = endStop;
    }

    public String getNoBus() {
        return noBus;
    }

    public void setNoBus(String noBus) {
        this.noBus = noBus;
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
