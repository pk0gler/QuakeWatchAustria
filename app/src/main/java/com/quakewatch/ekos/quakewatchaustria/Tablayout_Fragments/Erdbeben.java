package com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments;

/**
 * Created by pkogler on 30.10.2015.
 */
public class Erdbeben {
    private double mag;
    private String region;
    private String timeWhole;
    private String time;
    private String date;

    public Erdbeben(double mag, String region, String timeWhole) {
        this.mag = mag;
        this.region = region;
        this.timeWhole = timeWhole;
        this.seperateTimeWhole(timeWhole);
    }

    private void seperateTimeWhole(String timeWhole) {
        this.date = timeWhole.substring(0,9);
        this.time = timeWhole.substring(11,16);
    }

    //Getter Methods
    public String getDate() {
        return date;
    }

    public double getMag() {
        return mag;
    }

    public String getRegion() {
        return region;
    }

    public String getTime() {
        return time;
    }

    public String getTimeWhole() {
        return timeWhole;
    }

    //Setter Methods

    public void setDate(String date) {
        this.date = date;
    }

    public void setMag(double  mag) {
        this.mag = mag;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTimeWhole(String timeWhole) {
        this.timeWhole = timeWhole;
    }
}
