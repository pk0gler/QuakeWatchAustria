package com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments;

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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by pkogler on 22.10.2015.
 */
public class FRAGMENT_WELT extends Fragment {
    protected static final int SUB_ACTIVITY_REQUEST_CODE = 100;

    private float mActionBarHeight;
    private ActionBar mActionBar;

    ListView listView;
    String magStaerke;

    View v;
    ArrayList<Erdbeben> values;
    ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.list_layout_world,container,false);
        this.createContent();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        //this.createContent();
    }

    private void createContent() {
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getContext());
        this.magStaerke = SP.getString("magType", "1").charAt(0) + "";
        Log.d("Magni", Integer.parseInt(magStaerke) + "");
        new AsyncTaskParseJson().execute();
        //return v;
    }


    public class AsyncTaskParseJson extends AsyncTask<String, String, String> {

        final String TAG = "AsyncTaskParseJson.java";

        // set your json string url here
        String yourJsonStringUrl = "http://demo.codeofaninja.com/tutorials/json-example-with-php/index.php";

        // contacts JSONArray
        JSONArray dataJsonArr = null;

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... arg0) {
            values = new ArrayList<>();
            try {
                // instantiate our json parser
                //JsonParser jParser = new JsonParser();

                // get json string from url
                //JSONObject json = jParser.getJSONFromUrl(yourJsonStringUrl);
                String t = "{\"type\":\"FeatureCollection\",\"metadata\":{\"totalCount\":593299},\"features\":[{   \"geometry\": {     \"type\": \"Point\",     \"coordinates\": [       -72.0,       -29.95,       -18.0     ]   },   \"type\": \"Feature\",   \"id\": \"20151029_0000094\",   \"properties\": {     \"lastupdate\": \"2015-10-29T23:37:00.0Z\",     \"magtype\": \"ml\",     \"evtype\": \"ke\",     \"lon\": -72.0,     \"auth\": \"GUC\",     \"lat\": -29.95,     \"depth\": 18.0,     \"unid\": \"20151029_0000094\",     \"mag\": 3.4,     \"time\": \"2015-10-29T23:20:24.0Z\",     \"source_id\": \"467245\",     \"source_catalog\": \"EMSC-RTS\",     \"flynn_region\": \"OFFSHORE COQUIMBO, CHILE\"   } },{   \"geometry\": {     \"type\": \"Point\",     \"coordinates\": [       -67.18,       -22.66,       -244.0     ]   },   \"type\": \"Feature\",   \"id\": \"20151029_0000093\",   \"properties\": {     \"lastupdate\": \"2015-10-29T23:24:00.0Z\",     \"magtype\": \"ml\",     \"evtype\": \"ke\",     \"lon\": -67.18,     \"auth\": \"GUC\",     \"lat\": -22.66,     \"depth\": 244.0,     \"unid\": \"20151029_0000093\",     \"mag\": 3.8,     \"time\": \"2015-10-29T23:09:50.0Z\",     \"source_id\": \"467244\",     \"source_catalog\": \"EMSC-RTS\",     \"flynn_region\": \"POTOSI, BOLIVIA\"   } },{   \"geometry\": {     \"type\": \"Point\",     \"coordinates\": [       23.52,       35.9,       -2.0     ]   },   \"type\": \"Feature\",   \"id\": \"20151029_0000092\",   \"properties\": {     \"lastupdate\": \"2015-10-29T22:59:00.0Z\",     \"magtype\": \"m\",     \"evtype\": \"ke\",     \"lon\": 23.52,     \"auth\": \"EMSC\",     \"lat\": 35.9,     \"depth\": 2.0,     \"unid\": \"20151029_0000092\",     \"mag\": 3.5,     \"time\": \"2015-10-29T22:54:40.2Z\",     \"source_id\": \"467243\",     \"source_catalog\": \"EMSC-RTS\",     \"flynn_region\": \"CRETE, GREECE\"   } },{   \"geometry\": {     \"type\": \"Point\",     \"coordinates\": [       -73.17,       6.81,       -147.0     ]   },   \"type\": \"Feature\",   \"id\": \"20151029_0000091\",   \"properties\": {     \"lastupdate\": \"2015-10-29T22:50:00.0Z\",     \"magtype\": \"mw\",     \"evtype\": \"ke\",     \"lon\": -73.17,     \"auth\": \"RSNC\",     \"lat\": 6.81,     \"depth\": 147.0,     \"unid\": \"20151029_0000091\",     \"mag\": 4.4,     \"time\": \"2015-10-29T22:40:26.0Z\",     \"source_id\": \"467241\",     \"source_catalog\": \"EMSC-RTS\",     \"flynn_region\": \"NORTHERN COLOMBIA\"   } },{   \"geometry\": {     \"type\": \"Point\",     \"coordinates\": [       42.86,       39.78,       -5.0     ]   },   \"type\": \"Feature\",   \"id\": \"20151029_0000090\",   \"properties\": {     \"lastupdate\": \"2015-10-29T22:47:00.0Z\",     \"magtype\": \"ml\",     \"evtype\": \"ke\",     \"lon\": 42.86,     \"auth\": \"ISK\",     \"lat\": 39.78,     \"depth\": 5.0,     \"unid\": \"20151029_0000090\",     \"mag\": 2.0,     \"time\": \"2015-10-29T22:28:43.5Z\",     \"source_id\": \"467239\",     \"source_catalog\": \"EMSC-RTS\",     \"flynn_region\": \"EASTERN TURKEY\"   } },{   \"geometry\": {     \"type\": \"Point\",     \"coordinates\": [       -96.91,       32.84,       -8.0     ]   },   \"type\": \"Feature\",   \"id\": \"20151029_0000089\",   \"properties\": {     \"lastupdate\": \"2015-10-29T22:37:00.0Z\",     \"magtype\": \"mb\",     \"evtype\": \"ke\",     \"lon\": -96.91,     \"auth\": \"NEIC\",     \"lat\": 32.84,     \"depth\": 8.0,     \"unid\": \"20151029_0000089\",     \"mag\": 2.5,     \"time\": \"2015-10-29T22:24:39.4Z\",     \"source_id\": \"467238\",     \"source_catalog\": \"EMSC-RTS\",     \"flynn_region\": \"NORTHERN TEXAS\"   } },{   \"geometry\": {     \"type\": \"Point\",     \"coordinates\": [       -73.28,       6.89,       -124.0     ]   },   \"type\": \"Feature\",   \"id\": \"20151029_0000088\",   \"properties\": {     \"lastupdate\": \"2015-10-29T22:35:00.0Z\",     \"magtype\": \"ml\",     \"evtype\": \"ke\",     \"lon\": -73.28,     \"auth\": \"RSNC\",     \"lat\": 6.89,     \"depth\": 124.0,     \"unid\": \"20151029_0000088\",     \"mag\": 2.5,     \"time\": \"2015-10-29T22:24:07.0Z\",     \"source_id\": \"467237\",     \"source_catalog\": \"EMSC-RTS\",     \"flynn_region\": \"NORTHERN COLOMBIA\"   } },{   \"geometry\": {     \"type\": \"Point\",     \"coordinates\": [       92.5,       24.74,       -30.0     ]   },   \"type\": \"Feature\",   \"id\": \"20151029_0000087\",   \"properties\": {     \"lastupdate\": \"2015-10-29T22:40:00.0Z\",     \"magtype\": \"mb\",     \"evtype\": \"ke\",     \"lon\": 92.5,     \"auth\": \"EMSC\",     \"lat\": 24.74,     \"depth\": 30.0,     \"unid\": \"20151029_0000087\",     \"mag\": 4.8,     \"time\": \"2015-10-29T22:15:55.5Z\",     \"source_id\": \"467236\",     \"source_catalog\": \"EMSC-RTS\",     \"flynn_region\": \"INDIA-BANGLADESH BORDER REGION\"   } },{   \"geometry\": {     \"type\": \"Point\",     \"coordinates\": [       79.68,       42.17,       -1.0     ]   },   \"type\": \"Feature\",   \"id\": \"20151029_0000086\",   \"properties\": {     \"lastupdate\": \"2015-10-29T22:08:00.0Z\",     \"magtype\": \"mb\",     \"evtype\": \"ke\",     \"lon\": 79.68,     \"auth\": \"NNC\",     \"lat\": 42.17,     \"depth\": 1.0,     \"unid\": \"20151029_0000086\",     \"mag\": 3.6,     \"time\": \"2015-10-29T21:50:26.1Z\",     \"source_id\": \"467234\",     \"source_catalog\": \"EMSC-RTS\",     \"flynn_region\": \"KYRGYZSTAN\"   } },{   \"geometry\": {     \"type\": \"Point\",     \"coordinates\": [       20.72,       38.13,       -2.0     ]   },   \"type\": \"Feature\",   \"id\": \"20151029_0000085\",   \"properties\": {     \"lastupdate\": \"2015-10-29T20:47:00.0Z\",     \"magtype\": \"m\",     \"evtype\": \"ke\",     \"lon\": 20.72,     \"auth\": \"EMSC\",     \"lat\": 38.13,     \"depth\": 2.0,     \"unid\": \"20151029_0000085\",     \"mag\": 3.6,     \"time\": \"2015-10-29T20:41:27.8Z\",     \"source_id\": \"467229\",     \"source_catalog\": \"EMSC-RTS\",\"flynn_region\":\"GREECE\"   } }]}";
                JSONObject json = new JSONObject(t);

                // get the array of users
                dataJsonArr = json.getJSONArray("features");

                // loop through all users
                for (int i = 0; i < dataJsonArr.length(); i++) {

                    JSONObject c = dataJsonArr.getJSONObject(i);
                    JSONObject b = c.getJSONObject("properties");

                    // Storing each json item in variable
                    Double mag = Double.parseDouble(b.getString("mag"));
                    String flynn_region = b.getString("flynn_region");
                    String time = b.getString("time");
                    double depth = Double.parseDouble(b.getString("depth"));
                    //String username = c.getString("magtype");

                    // show the values in our logcat
                    Log.e(TAG, "mag: " + mag
                            + ", flynn_region: " + flynn_region
                            + ", time: " + time);
                    values.add(i,new Erdbeben(mag, flynn_region, time, depth));
                }
                //JSONObject ob = json.getJSONObject("properties");
                //values.add(0,ob.getString("magType"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //values.add(0,"hi");
            adapter = new CustomArrayAdapter(getContext(), values);
            return null;
        }

        @Override
        protected void onPostExecute(String strFromDoInBg) {
            listView = (ListView) v.findViewById(R.id.listWorld);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Erdbeben temp = (Erdbeben) parent.getItemAtPosition(position);
                            Toast.makeText(getContext(), temp.getMag()+"", Toast.LENGTH_LONG).show();
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
                    Toast.makeText(getContext(), "Beben auf map anzeigen", Toast.LENGTH_LONG).show();
                    return true;
                }
            });

            final TypedArray styledAttributes = getContext().getTheme().obtainStyledAttributes(
                    new int[]{android.R.attr.actionBarSize});
            mActionBarHeight = styledAttributes.getDimension(0, 0);
            mActionBar = ((MainActivity) getActivity()).getSupportActionBar();

            //listView.setOnScrollListener(new MyOnScrollListner(mActionBar));
            String t = "{\"type\":\"FeatureCollection\",\"metadata\":{\"totalCount\":593299},\"features\":[{   \"geometry\": {     \"type\": \"Point\",     \"coordinates\": [       -72.0,       -29.95,       -18.0     ]   },   \"type\": \"Feature\",   \"id\": \"20151029_0000094\",   \"properties\": {     \"lastupdate\": \"2015-10-29T23:37:00.0Z\",     \"magtype\": \"ml\",     \"evtype\": \"ke\",     \"lon\": -72.0,     \"auth\": \"GUC\",     \"lat\": -29.95,     \"depth\": 18.0,     \"unid\": \"20151029_0000094\",     \"mag\": 3.4,     \"time\": \"2015-10-29T23:20:24.0Z\",     \"source_id\": \"467245\",     \"source_catalog\": \"EMSC-RTS\",     \"flynn_region\": \"OFFSHORE COQUIMBO, CHILE\"   } },{   \"geometry\": {     \"type\": \"Point\",     \"coordinates\": [       -67.18,       -22.66,       -244.0     ]   },   \"type\": \"Feature\",   \"id\": \"20151029_0000093\",   \"properties\": {     \"lastupdate\": \"2015-10-29T23:24:00.0Z\",     \"magtype\": \"ml\",     \"evtype\": \"ke\",     \"lon\": -67.18,     \"auth\": \"GUC\",     \"lat\": -22.66,     \"depth\": 244.0,     \"unid\": \"20151029_0000093\",     \"mag\": 3.8,     \"time\": \"2015-10-29T23:09:50.0Z\",     \"source_id\": \"467244\",     \"source_catalog\": \"EMSC-RTS\",     \"flynn_region\": \"POTOSI, BOLIVIA\"   } },{   \"geometry\": {     \"type\": \"Point\",     \"coordinates\": [       23.52,       35.9,       -2.0     ]   },   \"type\": \"Feature\",   \"id\": \"20151029_0000092\",   \"properties\": {     \"lastupdate\": \"2015-10-29T22:59:00.0Z\",     \"magtype\": \"m\",     \"evtype\": \"ke\",     \"lon\": 23.52,     \"auth\": \"EMSC\",     \"lat\": 35.9,     \"depth\": 2.0,     \"unid\": \"20151029_0000092\",     \"mag\": 3.5,     \"time\": \"2015-10-29T22:54:40.2Z\",     \"source_id\": \"467243\",     \"source_catalog\": \"EMSC-RTS\",     \"flynn_region\": \"CRETE, GREECE\"   } },{   \"geometry\": {     \"type\": \"Point\",     \"coordinates\": [       -73.17,       6.81,       -147.0     ]   },   \"type\": \"Feature\",   \"id\": \"20151029_0000091\",   \"properties\": {     \"lastupdate\": \"2015-10-29T22:50:00.0Z\",     \"magtype\": \"mw\",     \"evtype\": \"ke\",     \"lon\": -73.17,     \"auth\": \"RSNC\",     \"lat\": 6.81,     \"depth\": 147.0,     \"unid\": \"20151029_0000091\",     \"mag\": 4.4,     \"time\": \"2015-10-29T22:40:26.0Z\",     \"source_id\": \"467241\",     \"source_catalog\": \"EMSC-RTS\",     \"flynn_region\": \"NORTHERN COLOMBIA\"   } },{   \"geometry\": {     \"type\": \"Point\",     \"coordinates\": [       42.86,       39.78,       -5.0     ]   },   \"type\": \"Feature\",   \"id\": \"20151029_0000090\",   \"properties\": {     \"lastupdate\": \"2015-10-29T22:47:00.0Z\",     \"magtype\": \"ml\",     \"evtype\": \"ke\",     \"lon\": 42.86,     \"auth\": \"ISK\",     \"lat\": 39.78,     \"depth\": 5.0,     \"unid\": \"20151029_0000090\",     \"mag\": 2.0,     \"time\": \"2015-10-29T22:28:43.5Z\",     \"source_id\": \"467239\",     \"source_catalog\": \"EMSC-RTS\",     \"flynn_region\": \"EASTERN TURKEY\"   } },{   \"geometry\": {     \"type\": \"Point\",     \"coordinates\": [       -96.91,       32.84,       -8.0     ]   },   \"type\": \"Feature\",   \"id\": \"20151029_0000089\",   \"properties\": {     \"lastupdate\": \"2015-10-29T22:37:00.0Z\",     \"magtype\": \"mb\",     \"evtype\": \"ke\",     \"lon\": -96.91,     \"auth\": \"NEIC\",     \"lat\": 32.84,     \"depth\": 8.0,     \"unid\": \"20151029_0000089\",     \"mag\": 2.5,     \"time\": \"2015-10-29T22:24:39.4Z\",     \"source_id\": \"467238\",     \"source_catalog\": \"EMSC-RTS\",     \"flynn_region\": \"NORTHERN TEXAS\"   } },{   \"geometry\": {     \"type\": \"Point\",     \"coordinates\": [       -73.28,       6.89,       -124.0     ]   },   \"type\": \"Feature\",   \"id\": \"20151029_0000088\",   \"properties\": {     \"lastupdate\": \"2015-10-29T22:35:00.0Z\",     \"magtype\": \"ml\",     \"evtype\": \"ke\",     \"lon\": -73.28,     \"auth\": \"RSNC\",     \"lat\": 6.89,     \"depth\": 124.0,     \"unid\": \"20151029_0000088\",     \"mag\": 2.5,     \"time\": \"2015-10-29T22:24:07.0Z\",     \"source_id\": \"467237\",     \"source_catalog\": \"EMSC-RTS\",     \"flynn_region\": \"NORTHERN COLOMBIA\"   } },{   \"geometry\": {     \"type\": \"Point\",     \"coordinates\": [       92.5,       24.74,       -30.0     ]   },   \"type\": \"Feature\",   \"id\": \"20151029_0000087\",   \"properties\": {     \"lastupdate\": \"2015-10-29T22:40:00.0Z\",     \"magtype\": \"mb\",     \"evtype\": \"ke\",     \"lon\": 92.5,     \"auth\": \"EMSC\",     \"lat\": 24.74,     \"depth\": 30.0,     \"unid\": \"20151029_0000087\",     \"mag\": 4.8,     \"time\": \"2015-10-29T22:15:55.5Z\",     \"source_id\": \"467236\",     \"source_catalog\": \"EMSC-RTS\",     \"flynn_region\": \"INDIA-BANGLADESH BORDER REGION\"   } },{   \"geometry\": {     \"type\": \"Point\",     \"coordinates\": [       79.68,       42.17,       -1.0     ]   },   \"type\": \"Feature\",   \"id\": \"20151029_0000086\",   \"properties\": {     \"lastupdate\": \"2015-10-29T22:08:00.0Z\",     \"magtype\": \"mb\",     \"evtype\": \"ke\",     \"lon\": 79.68,     \"auth\": \"NNC\",     \"lat\": 42.17,     \"depth\": 1.0,     \"unid\": \"20151029_0000086\",     \"mag\": 3.6,     \"time\": \"2015-10-29T21:50:26.1Z\",     \"source_id\": \"467234\",     \"source_catalog\": \"EMSC-RTS\",     \"flynn_region\": \"KYRGYZSTAN\"   } },{   \"geometry\": {     \"type\": \"Point\",     \"coordinates\": [       20.72,       38.13,       -2.0     ]   },   \"type\": \"Feature\",   \"id\": \"20151029_0000085\",   \"properties\": {     \"lastupdate\": \"2015-10-29T20:47:00.0Z\",     \"magtype\": \"m\",     \"evtype\": \"ke\",     \"lon\": 20.72,     \"auth\": \"EMSC\",     \"lat\": 38.13,     \"depth\": 2.0,     \"unid\": \"20151029_0000085\",     \"mag\": 3.6,     \"time\": \"2015-10-29T20:41:27.8Z\",     \"source_id\": \"467229\",     \"source_catalog\": \"EMSC-RTS\",\"flynn_region\":\"GREECE\"   } }]}";
        }
    }
}
