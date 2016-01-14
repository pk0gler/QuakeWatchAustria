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
import org.json.JSONException;
import org.json.JSONObject;

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
        return json;
    }

    private void makePostRequest() {


        HttpClient httpClient = new DefaultHttpClient();
        // replace with your url
        HttpPost httpPost = new HttpPost("http://geoweb.zamg.ac.at/quakeapi/v01/message");

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        //Encoding POST data
        //httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Authorization", "Basic cXVha2VhcGk6I3FrcCZtbGRuZyM=");
        httpPost.setHeader("X-QuakeAPIKey", prefs.getString("apikey", ""));

        //making POST request.
        try {
            HttpResponse response = httpClient.execute(httpPost);
        } catch (ClientProtocolException e) {
            // Log exception
            e.printStackTrace();
        } catch (Exception e) {
            // Log exception
            e.printStackTrace();
        }

    }

    public class SendfeedbackJob extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String[] params) {
            // do above Server call here
            //makePostRequest();
            return null;
        }

        @Override
        protected void onPostExecute(String message) {
            //process message
        }
    }

}
