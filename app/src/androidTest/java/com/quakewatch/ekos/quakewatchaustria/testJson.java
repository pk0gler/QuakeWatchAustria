package com.quakewatch.ekos.quakewatchaustria;

import android.test.AndroidTestCase;
import android.util.Log;

import com.quakewatch.ekos.quakewatchaustria.Json.FinalJson;
import com.quakewatch.ekos.quakewatchaustria.Json.JsonParser;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by pkogler on 14.01.2016.
 */
public class testJson extends AndroidTestCase {
    public void testHTTPWithJsonFetching() throws Throwable {
        String testurl = "http://geoweb.zamg.ac.at/fdsnws/app/1/query?location=Austria&limit=1";
        JSONObject expected = new JSONObject();
        InputStream is = new URL(testurl).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            StringBuilder sb = new StringBuilder();
            int cp;

            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            String jsonText = sb.toString();
            expected = new JSONObject(jsonText);
        } finally {
            is.close();
        }
        JSONObject actual = JsonParser.readJsonFromUrl(testurl);
        Log.d("json",actual.toString());
        assertEquals(expected.toString(), actual.toString());
    }

    public void testSendFinalJson() throws Throwable {
        FinalJson.klassifikation = "12";
        FinalJson.kontakt = "pkogler";
        FinalJson.mlocOrtsname = "Austria";
        JSONObject actual = FinalJson.toJson();
        JSONObject expected = new JSONObject();
        expected.put("referenzID", FinalJson.referenzID);
        expected.put("locLon", FinalJson.locLon);
        expected.put("locLat", FinalJson.locLat);
        expected.put("locPrecision", FinalJson.locPrecision);
        expected.put("locLastUpdate", FinalJson.locLastUpdate);
        expected.put("mlocPLZ", FinalJson.mlocPLZ);
        expected.put("mlocOrtsname", FinalJson.mlocOrtsname);
        expected.put("stockwerk", FinalJson.stockwerk);
        expected.put("klassifikation", FinalJson.klassifikation);
        expected.put("verspuert", FinalJson.verspuert);
        expected.put("kommentar", FinalJson.kommentar);
        expected.put("kontakt", FinalJson.kontakt);
        assertEquals(expected.toString(),actual.toString());
    }
}
