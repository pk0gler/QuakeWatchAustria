package com.quakewatch.ekos.quakewatchaustria.Json;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by philippkogler on 13.01.16.
 */
public final class FinalJson {
    public static String referenzID = "null";
    public static String locLon = "null";
    public static String locLat = "null";
    public static String locPrecision = "null";
    public static String locLastUpdate = "null";
    public static String mlocPLZ = "null";
    public static String mlocOrtsname = "null";
    public static String stockwerk = "null";
    public static String klassifikation = "null";
    public static String verspuert = "null";
    public static JSONObject jsonObject = null;
    public static String kommentar = "null";
    public static String kontakt = "harald.bamberger@zamg.ac.at";
    public static Context context;

    public static JSONObject toJson() {
        JSONObject json = new JSONObject();
        try {
            json.put("referenzID", referenzID);
            json.put("locLon", locLon);
            json.put("locLat", locLat);
            json.put("locPrecision", locPrecision);
            json.put("locLastUpdate", locLastUpdate);
            json.put("mlocPLZ", mlocPLZ);
            json.put("mlocOrtsname", mlocOrtsname);
            json.put("stockwerk", stockwerk);
            json.put("klassifikation", klassifikation);
            json.put("verspuert", verspuert);
            json.put("kommentar", kommentar);
            json.put("kontakt", kontakt);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        jsonObject = json;
        return json;
    }

}
