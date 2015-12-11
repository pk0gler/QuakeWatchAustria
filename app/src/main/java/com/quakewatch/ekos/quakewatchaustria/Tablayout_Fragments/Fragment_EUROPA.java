package com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener.CustomArrayAdapter;
import com.quakewatch.ekos.quakewatchaustria.MainActivity;
import com.quakewatch.ekos.quakewatchaustria.R;
import com.quakewatch.ekos.quakewatchaustria.SubACtivities.SubActivity_DetailAnsicht;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by pkogler on 22.10.2015.
 */
public class Fragment_EUROPA extends Fragment {
    protected static final int SUB_ACTIVITY_REQUEST_CODE = 100;
    ListView listView;
    View v;
    String magStaerke;
    private float mActionBarHeight;
    private ActionBar mActionBar;
    private boolean jetzt = true;
    private ArrayList<Erdbeben> values = new ArrayList<>();
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.list_layout_eu, container, false);
        this.createContent();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void createContent() {
        context = this.getContext();
        listView = null;
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getContext());
        this.magStaerke = SP.getString("magType", "1").charAt(0) + "";
        Log.d("Magni", Integer.parseInt(magStaerke) + "");

        //return v;
        listView = (ListView) v.findViewById(R.id.listEu);
        new AsyncTaskParseJson().execute();
    }

    public class AsyncTaskParseJson extends AsyncTask<String, String, String> {
        ProgressDialog mDialog;

        final String TAG = "AsyncTaskParseJson.java";

        // contacts JSONArray
        JSONArray dataJsonArr = null;

        @Override
        protected void onPreExecute() {
            mDialog = new ProgressDialog(context);
            mDialog.setMessage("Beben werden geladen...");
            mDialog.show();

        }

        @Override
        protected String doInBackground(String... arg0) {
            try {
                // instantiate our json parser
                JsonParser jParser = new JsonParser();

                // get the array of users
                JSONObject json = JsonParser.readJsonFromUrl("http://www.seismicportal.eu/fdsnws/event/1/query?limit=1000&format=json&minlatitude=47");
                dataJsonArr = json.getJSONArray("features");

                // loop through all users
                for (int i = 0; i < dataJsonArr.length(); i++) {

                    JSONObject c = dataJsonArr.getJSONObject(i);
                    JSONObject b = c.getJSONObject("properties");

                    //Jason Parsing Methode
                    //Server umstellungen dynamisch

                    // Storing each json item in variable
                    Double mag = Double.parseDouble(b.getString("mag"));
                    String flynn_region = b.getString("flynn_region");
                    String time = b.getString("time");
                    double depth = Double.parseDouble(b.getString("depth"));
                    //String username = c.getString("magtype");

                    // show the values in our logcat
                    Log.e("MyJsonAt", "|" + flynn_region + "|");
                    //if (flynn_region.equals("AUSTRIA")) {
                    //Log.e("testla", "JA");
                    if (Arrays.asList(Erdbeben.countries).contains(flynn_region))values.add(new Erdbeben(mag, flynn_region, time, depth));
                    //} else
                    // Log.e("testla", "NEIN");

                }
                //JSONObject ob = json.getJSONObject("properties");
                //values.add(0,ob.getString("magType"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            //values.add(0,"hi");
            return null;
        }


        @Override
        protected void onPostExecute(String strFromDoInBg) {
            mDialog.dismiss();
            ArrayAdapter<String> adapter = new CustomArrayAdapter(getContext(), values);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Erdbeben temp = (Erdbeben) parent.getItemAtPosition(position);
                            Toast.makeText(getContext(), temp.getMag() + "", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getContext(), SubActivity_DetailAnsicht.class);
                            i.putExtra("bebenData", temp);
                            i.putExtra("isAt", false);
                            startActivityForResult(i, SUB_ACTIVITY_REQUEST_CODE);
                        }
                    }
            );

            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    //Toast.makeText(getContext(), "Beben auf map anzeigen", Toast.LENGTH_LONG).show();
                    ((MainActivity) getActivity()).setPager(3);
                    return true;
                }
            });


            final TypedArray styledAttributes = getContext().getTheme().obtainStyledAttributes(
                    new int[]{android.R.attr.actionBarSize});
            mActionBarHeight = styledAttributes.getDimension(0, 0);
            mActionBar = ((MainActivity) getActivity()).getSupportActionBar();
        }
    }
}

