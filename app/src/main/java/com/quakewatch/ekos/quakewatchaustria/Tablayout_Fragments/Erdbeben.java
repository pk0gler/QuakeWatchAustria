package com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by pkogler on 30.10.2015.
 * Usage:   Erdbeben Class
 *          Used for storing recieved Quake Files in one Object of
 *          Type Erdbeben.
 *          It also contains an array which has all used Countries in Europe
 */
public class Erdbeben implements Serializable {
    public final static String[] countries = {
            "ALBANIA",
            "ANDORRA",
            "ARMENIA",
            "AUSTRIA",
            "AZERBAIJAN",
            "BELARUS",
            "BELGIUM",
            "BOSNIA AND HERZEGOVINA",
            "BULGARIA",
            "CROATIA",
            "CYPRUS",
            "CZECH REPUBLIC",
            "DENMARK",
            "ESTONIA",
            "FINLAND",
            "FRANCE",
            "GEORGIA",
            "GERMANY",
            "GREECE",
            "HUNGARY",
            "ICELAND",
            "IRELAND",
            "ITALY",
            "KOSOVO",
            "LATVIA",
            "LIECHTENSTEIN",
            "LITHUANIA",
            "LUXEMBOURG",
            "MACEDONIA",
            "MALTA",
            "MOLDOVA",
            "MONACO",
            "MONTENEGRO",
            "THE NETHERLANDS",
            "NORWAY",
            "POLAND",
            "PORTUGAL",
            "ROMANIA",
            "RUSSIA",
            "SAN MARINO",
            "SERBIA",
            "SLOVAKIA",
            "SLOVENIA",
            "SPAIN",
            "SWEDEN",
            "SWITZERLAND",
            "TURKEY",
            "UKRAINE",
            "UNITED KINGDOM",
            "VATICAN CITY"
    };
    private double mag;
    private String region;
    private String timeWhole;
    private String time;
    private double depth;
    private String date;

    /**
     * Constructor
     * @param mag
     * @param region
     * @param timeWhole
     * @param depth
     */
    public Erdbeben(double mag, String region, String timeWhole, double depth) {
        this.mag = mag;
        this.region = this.formatRegion(region);
        this.timeWhole = timeWhole;
        this.seperateTimeWhole(timeWhole);
        this.depth = depth;
    }

    /**
     * EMpty Constructor
     * Initilazie all attributes with standard Values
     */
    public Erdbeben() {
        this.mag = 0;
        this.region = "";
        this.timeWhole = "";
        this.depth = 0;
    }

    /**
     * Used for seperate the Whole Time String into
     * Time and Date
     * @param timeWhole
     */
    private void seperateTimeWhole(String timeWhole) {
        this.date = timeWhole.substring(0, 10);
        this.time = timeWhole.substring(11, 16);
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

    public void setMag(double mag) {
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

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    /**
     * Formats the recieved Region String so it can be displayed
     * @param t
     * @return
     */
    public String formatRegion(String t) {
        String a = t.toLowerCase();
        String[] temp = a.split(" ");
        String newStr = "";
        for (int i = 0; i < temp.length; i++) {
            if (i == 1) newStr += " ";
            newStr += temp[i].substring(0, 1).toUpperCase();
            newStr += temp[i].substring(1, temp[i].length());
        }
        Log.d("Region", newStr);
        if (newStr.length() >= 20) {
            //Work to do
        }
        return newStr;
    }

}
