package com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by pkogler on 30.10.2015.
 * Usage:   Erdbeben Class
 * Used for storing recieved Quake Files in one Object of
 * Type Erdbeben.
 * It also contains an array which has all used Countries in Europe
 */
public class Erdbeben implements Serializable, Comparable<Erdbeben> {
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
    public double lat;
    public double lon;
    private ArrayList<HashMap<String, String>> places;
    private int id;
    public String placesString;

    /**
     * Constructor
     *
     * @param mag
     * @param region
     * @param timeWhole
     * @param depth
     */
    public Erdbeben(double mag, String region, String timeWhole, double depth, double lat, double lon, JSONArray places, int id) {
        this.mag = mag;
        this.region = this.formatRegion(region);
        this.timeWhole = timeWhole;
        this.seperateTimeWhole(timeWhole);
        this.depth = depth;
        this.lon = lon;
        this.lat = lat;
        this.places = new ArrayList<>();
        getPlaces(places);
        this.placesString = placesToString(this.places);
        this.id = id;
    }

    private String placesToString(ArrayList<HashMap<String, String>> temp) {
        String erg = "";
        for (int i = 0; i < places.size(); i++) {
            if (i == 0) {
                erg = places.get(i).get("text");
            } else {
                erg += ",\n" + places.get(i).get("text");
            }
        }
        return erg;
    }

    private void getPlaces(JSONArray places) {
        /*HashMap<String,String> tempH;
        for (int i = 0; i < places.length(); i++) {
            tempH = new HashMap<>();
            try {
                JSONObject temp = places.getJSONObject(i);
                switch (i) {
                    case 0:
                        tempH.put("text", temp.getString("text"));
                        break;
                    case 1:
                        tempH.put("dist", temp.getString("dist"));
                        break;
                    case 2:
                        tempH.put("place", temp.getString("place"));
                        break;
                    case 3:
                        tempH.put("rank", temp.getString("rank"));
                        break;
                    case 4:
                        tempH.put("azi", temp.getString("azi"));
                        break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.places.add(tempH);
        }*/
        for (int i = 0; i < places.length(); i++) {
            HashMap<String, String> tempH = new HashMap<>();
            try {
                JSONObject temp = places.getJSONObject(i);
                for (int j = 0; j < temp.length(); j++) {
                    switch (j) {
                        case 0:
                            tempH.put("text", temp.getString("text"));
                            break;
                        case 1:
                            tempH.put("dist", temp.getString("dist"));
                            break;
                        case 2:
                            tempH.put("place", temp.getString("place"));
                            break;
                        case 3:
                            tempH.put("rank", temp.getString("rank"));
                            break;
                        case 4:
                            tempH.put("azi", temp.getString("azi"));
                            break;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.places.add(tempH);
        }
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
     *
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
     *
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

    public int getId() {
        return id;
    }


    @Override
    public int compareTo(Erdbeben another) {
        if (this.getMag() == another.getMag()) {
            return 0;
        } else if (this.getMag() >= another.getMag()) {
            return 1;
        } else if (this.getMag() <= another.getMag()) {
            return -1;
        } else {
            return 0;
        }
    }
}
