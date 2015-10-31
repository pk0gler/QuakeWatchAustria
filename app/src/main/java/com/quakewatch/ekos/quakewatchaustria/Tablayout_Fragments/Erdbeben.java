package com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by pkogler on 30.10.2015.
 */
public class Erdbeben implements Serializable {
    private double mag;
    private String region;
    private String timeWhole;
    private String time;
    private String date;

    public Erdbeben(double mag, String region, String timeWhole) {
        this.mag = mag;
        this.region = this.formatRegion(region);
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

    public String formatRegion(String t) {
        String a = t.toLowerCase();
        String[] temp = a.split(" ");
        String newStr = "";
        for (int i = 0; i<temp.length; i++) {
            if (i == 1) newStr += " ";
            newStr += temp[i].substring(0,1).toUpperCase();
            newStr += temp[i].substring(1,temp[i].length());
        }
        Log.d("Region", newStr);
        if (newStr.length() >= 20) {
            //Work to do
        }
        return newStr;
    }

}
