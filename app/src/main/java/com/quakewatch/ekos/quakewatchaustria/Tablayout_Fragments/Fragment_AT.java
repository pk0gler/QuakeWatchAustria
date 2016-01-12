package com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener.CustomArrayAdapter;
import com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener.ViewPagerAdapter;
import com.quakewatch.ekos.quakewatchaustria.Interfaces.onSpinnerClick;
import com.quakewatch.ekos.quakewatchaustria.MainActivity;
import com.quakewatch.ekos.quakewatchaustria.R;
import com.quakewatch.ekos.quakewatchaustria.SubACtivities.SubActivity_BebenEintragenStart;
import com.quakewatch.ekos.quakewatchaustria.SubACtivities.SubActivity_DetailAnsicht;
import com.software.shell.fab.ActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by pkogler on 22.10.2015.
 */
public class Fragment_AT extends Fragment implements onSpinnerClick, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    protected static final int SUB_ACTIVITY_REQUEST_CODE = 100;
    private ImageButton FAB;
    boolean show = false;
    private ActionButton actionButtonNow;
    private ActionButton actionButtonMain;
    private ActionButton actionButtonAndere;
    private Double minMag=0.0;
    ListView listView;

    private View v;
    private TextView tJetzt;
    private TextView tAndere;
    private TextView tmain;
    private Button bgone;

    Context context;

    private String magStaerke;

    private float mActionBarHeight;
    private ActionBar mActionBar;

    private ViewPagerAdapter pager;

    private ArrayList<Erdbeben> values = new ArrayList<>();
    private String[] spinnerValues = {"0.0+","","10","nach Datum"};
    private boolean filter;
    private boolean locTrue = false;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)  {
        setHasOptionsMenu(true);
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        v = inflater.inflate(R.layout.list_layout_at, container, false);
        actionButtonMain = (ActionButton) v.findViewById(R.id.action_button_main);
        actionButtonMain.setImageResource(R.drawable.fab_x_but_rotate);
        actionButtonMain.setButtonColor(Color.parseColor("#3F51B5"));
        actionButtonMain.setButtonColorPressed(getResources().getColor(R.color.ColorPrimaryDark));


        actionButtonNow = (ActionButton) v.findViewById(R.id.action_button_jetzt);
        actionButtonAndere = (ActionButton) v.findViewById(R.id.action_button_andere);

        actionButtonNow.setType(ActionButton.Type.MINI);
        actionButtonNow.setImageResource(R.drawable.fab_plus_icon);
        actionButtonNow.setButtonColor(Color.parseColor("#3F51B5"));
        actionButtonNow.setButtonColorPressed(getResources().getColor(R.color.ColorPrimaryDark));

        actionButtonAndere.setType(ActionButton.Type.MINI);
        actionButtonAndere.setImageResource(R.drawable.fab_plus_icon);
        actionButtonAndere.setButtonColor(Color.parseColor("#3F51B5"));
        actionButtonAndere.setButtonColorPressed(getResources().getColor(R.color.ColorPrimaryDark));

        actionButtonAndere.hide();
        actionButtonNow.hide();

        tJetzt = (TextView) v.findViewById(R.id.tJetzt);
        tJetzt.setVisibility(View.GONE);
        tAndere = (TextView) v.findViewById(R.id.tAndere);
        tAndere.setVisibility(View.GONE);
        tmain = (TextView) v.findViewById(R.id.bebenMelden);
        bgone = (Button) v.findViewById(R.id.bgone);
        bgone.getBackground().setAlpha(0);
        bgone.setVisibility(View.GONE);


        actionButtonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (show) {
                    //v.setAlpha(1f);
                    bgone.setVisibility(View.GONE);
                    //bgone.setEnabled(false);
                    tmain.setText("Erdbeben melden");
                    actionButtonMain.setImageResource(R.drawable.fab_x_but_rotate);
                    listView.setEnabled(true);
                    tJetzt.setVisibility(View.GONE);
                    tAndere.setVisibility(View.GONE);
                    listView.setAlpha(1f);
                    listView.setBackgroundColor(Color.WHITE);
                    actionButtonAndere.hide();
                    actionButtonNow.hide();
                    show = false;
                } else {
                    bgone.setVisibility(View.VISIBLE);
                    bgone.setEnabled(true);
                    tmain.setText("Abbrechen");
                    listView.setEnabled(false);
                    tJetzt.setVisibility(View.VISIBLE);
                    tAndere.setVisibility(View.VISIBLE);
                    tAndere.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.fab_fade_in));
                    tJetzt.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.fab_fade_in));

                    listView.setAlpha(0.35f);
                    actionButtonMain.setAlpha(1f);
                    //listView.setBackgroundColor(Color.DKGRAY);
                    actionButtonMain.setImageResource(R.drawable.fab_x_but);
                    actionButtonNow.setHideAnimation(ActionButton.Animations.ROLL_TO_RIGHT);
                    actionButtonAndere.setHideAnimation(ActionButton.Animations.ROLL_TO_RIGHT);
                    actionButtonAndere.show();
                    actionButtonNow.show();
                    show = true;
                }
            }


        });


        actionButtonNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setEnabled(true);
                tJetzt.setVisibility(View.GONE);
                tAndere.setVisibility(View.GONE);
                listView.setAlpha(1f);
                listView.setBackgroundColor(Color.WHITE);
                actionButtonAndere.hide();
                actionButtonNow.hide();
                show = false;

                boolean isNow = true;
                //Toast.makeText(getContext(), wert, Toast.LENGTH_LONG).show();
                Intent i = new Intent(getContext(), SubActivity_BebenEintragenStart.class);
                i.putExtra("state", isNow);
                if (mLastLocation!=null){
                    locTrue = true;
                    i.putExtra("loc", locTrue);
                    i.putExtra("locData", mLastLocation);
                } else {
                    locTrue = false;
                    i.putExtra("loc", locTrue);
                }

                startActivityForResult(i, SUB_ACTIVITY_REQUEST_CODE);
            }
        });


        actionButtonAndere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setEnabled(true);
                tJetzt.setVisibility(View.GONE);
                tAndere.setVisibility(View.GONE);
                listView.setAlpha(1f);
                listView.setBackgroundColor(Color.WHITE);
                actionButtonAndere.hide();
                actionButtonNow.hide();
                show = false;

                boolean isNow = false;
                //Toast.makeText(getContext(), wert, Toast.LENGTH_LONG).show();
                Intent i = new Intent(getContext(), SubActivity_BebenEintragenStart.class);
                i.putExtra("state", isNow);
                if (mLastLocation!=null){
                    locTrue = true;
                    i.putExtra("loc", locTrue);
                    i.putExtra("locData", mLastLocation);
                } else {
                    locTrue = false;
                    i.putExtra("loc", locTrue);
                }
                startActivityForResult(i, SUB_ACTIVITY_REQUEST_CODE);
            }
        });


        tJetzt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setEnabled(true);
                tJetzt.setVisibility(View.GONE);
                tAndere.setVisibility(View.GONE);
                listView.setAlpha(1f);
                listView.setBackgroundColor(Color.WHITE);
                actionButtonAndere.hide();
                actionButtonNow.hide();
                show = false;

                boolean isNow = true;
                //Toast.makeText(getContext(), wert, Toast.LENGTH_LONG).show();
                Intent i = new Intent(getContext(), SubActivity_BebenEintragenStart.class);
                i.putExtra("state", isNow);
                if (mLastLocation!=null){
                    locTrue = true;
                    i.putExtra("loc", locTrue);
                    i.putExtra("locData", mLastLocation);
                } else {
                    locTrue = false;
                    i.putExtra("loc", locTrue);
                }
                startActivityForResult(i, SUB_ACTIVITY_REQUEST_CODE);
            }
        });


        tAndere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setEnabled(true);
                tJetzt.setVisibility(View.GONE);
                tAndere.setVisibility(View.GONE);
                listView.setAlpha(1f);
                listView.setBackgroundColor(Color.WHITE);
                actionButtonAndere.hide();
                actionButtonNow.hide();
                show = false;

                boolean isNow = false;
                //Toast.makeText(getContext(), wert, Toast.LENGTH_LONG).show();
                Intent i = new Intent(getContext(), SubActivity_BebenEintragenStart.class);
                i.putExtra("state", isNow);
                if (mLastLocation!=null){
                    locTrue = true;
                    i.putExtra("loc", locTrue);
                    i.putExtra("locData", mLastLocation);
                } else {
                    locTrue = false;
                    i.putExtra("loc", locTrue);
                }
                startActivityForResult(i, SUB_ACTIVITY_REQUEST_CODE);
            }
        });


        tmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (show) {
                    //v.setAlpha(1f);
                    bgone.setVisibility(View.GONE);
                    //bgone.setEnabled(false);
                    tmain.setText("Erdbeben melden");
                    actionButtonMain.setImageResource(R.drawable.fab_x_but_rotate);
                    listView.setEnabled(true);
                    tJetzt.setVisibility(View.GONE);
                    tAndere.setVisibility(View.GONE);
                    listView.setAlpha(1f);
                    listView.setBackgroundColor(Color.WHITE);
                    actionButtonAndere.hide();
                    actionButtonNow.hide();
                    show = false;
                } else {
                    bgone.setVisibility(View.VISIBLE);
                    bgone.setEnabled(true);
                    tmain.setText("Abbrechen");
                    listView.setEnabled(false);
                    tJetzt.setVisibility(View.VISIBLE);
                    tAndere.setVisibility(View.VISIBLE);
                    tAndere.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.fab_fade_in));
                    tJetzt.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.fab_fade_in));

                    listView.setAlpha(0.35f);
                    actionButtonMain.setAlpha(1f);
                    //listView.setBackgroundColor(Color.DKGRAY);
                    actionButtonMain.setImageResource(R.drawable.fab_x_but);
                    actionButtonNow.setHideAnimation(ActionButton.Animations.ROLL_TO_RIGHT);
                    actionButtonAndere.setHideAnimation(ActionButton.Animations.ROLL_TO_RIGHT);
                    actionButtonAndere.show();
                    actionButtonNow.show();
                    show = true;
                }
            }
        });

        bgone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //v.setAlpha(1f);
                bgone.setVisibility(View.GONE);
                //bgone.setEnabled(false);
                tmain.setText("Erdbeben melden");
                actionButtonMain.setImageResource(R.drawable.fab_x_but_rotate);
                listView.setEnabled(true);
                tJetzt.setVisibility(View.GONE);
                tAndere.setVisibility(View.GONE);
                listView.setAlpha(1f);
                listView.setBackgroundColor(Color.WHITE);
                actionButtonAndere.hide();
                actionButtonNow.hide();
                show = false;
            }
        });


        final TypedArray styledAttributes = getContext().getTheme().obtainStyledAttributes(
                new int[]{android.R.attr.actionBarSize});
        mActionBarHeight = styledAttributes.getDimension(0, 0);
        mActionBar = ((MainActivity) getActivity()).getSupportActionBar();
        this.createConetent();

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_frag, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                Toast.makeText(getContext(), "hi", Toast.LENGTH_LONG).show();
                new AsyncTaskParseJson().execute();
                return false;
            case R.id.filter:
                FilterFragment dFragment = new FilterFragment(this,this.spinnerValues);
                dFragment.show(getFragmentManager(), "Dialog Fragment");
                filter = true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //getActivity().getIntent().getExtras().get("save");
        //this.createConetent();
        actionButtonMain.setImageResource(R.drawable.fab_x_but_rotate);
        tmain.setText("Erdbeben melden");
    }

    public void createConetent() {
        this.context = this.getContext();
        listView = null;
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getContext());
        this.magStaerke = SP.getString("magType", "1").charAt(0) + "";
        Log.d("Magni", Integer.parseInt(magStaerke) + "");
        listView = (ListView) v.findViewById(R.id.listAt);
        //listView.setOverScrollMode(ListView.OVER_SCROLL_NEVER);
        SharedPreferences spf = PreferenceManager.getDefaultSharedPreferences(getContext());
        this.minMag = Double.parseDouble(spf.getString("magType", "1"));
        new AsyncTaskParseJson().execute();
    }

    @Override
    public void saveSpinnerData(String[] values) {
        this.spinnerValues = values;
        new AsyncTaskParseJson().execute();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d("location","drinRight");
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            String loc = mLastLocation.getLatitude()+"--"+String.valueOf(mLastLocation.getLongitude());
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public class AsyncTaskParseJson extends AsyncTask<String, String, String> {
        ProgressDialog mDialog;
        final String TAG = "AsyncTaskParseJson.java";

        // contacts JSONArray
        JSONArray dataJsonArr = null;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mDialog = new ProgressDialog(context);
            mDialog.setMessage("Beben werden geladen...");
            mDialog.setCancelable(false);
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.show();
        }

        @Override
        protected String doInBackground(String... arg0) {
            try {
                values = new ArrayList<>();
                // instantiate our json parser
                JsonParser jParser = new JsonParser();

                // get the array of users
                JSONObject json = JsonParser.readJsonFromUrl("http://geoweb.zamg.ac.at/fdsnws/app/1/query?location=Austria&limit=" + spinnerValues[2] + "&orderby=time");
                minMag = Double.valueOf(spinnerValues[0].substring(0,2));
                dataJsonArr = json.getJSONArray("features");

                // loop through all users
                for (int i = 0; i < dataJsonArr.length(); i++) {

                    JSONObject c = dataJsonArr.getJSONObject(i);
                    JSONObject b = c.getJSONObject("properties");

                    //Jason Parsing Methode
                    //Server umstellungen dynamisch

                    // Storing each json item in variable
                    Double mag = Double.parseDouble(b.getString("mag"));
                    String flynn_region = b.getString("region");
                    String time = b.getString("time");
                    Double lat = b.getDouble("lat");
                    Double lon = b.getDouble("lon");
                    double depth = Double.parseDouble(b.getString("depth"));
                    JSONArray places = b.getJSONArray("places");
                    int id = c.getInt("id");
                    //String username = c.getString("magtype");
                    // show the values in our logcat
                    //Log.e("MyJsonAt", "|" + flynn_region + "|");
                    if ((mag >= minMag)) {
                        Log.e("testla", "JA");
                        values.add(new Erdbeben(mag, flynn_region, time, depth, lat, lon, places, id));
                    }//} else
                    //Log.e("testla", "NEIN");

                }
                //JSONObject ob = json.getJSONObject("properties");
                //values.add(0,ob.getString("magType"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            //values.add(0,"hi");
            if (spinnerValues[3].equals("nach Magnitude")) Collections.sort(values);
            return null;
        }


        @Override
        protected void onPostExecute(String strFromDoInBg) {
            mDialog.dismiss();
            if (filter) {
                filter = false;
                Toast.makeText(getContext(),values.size()+" Ergebnisse gefunden",Toast.LENGTH_SHORT).show();
            }
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    /**Erdbeben temp = (Erdbeben) parent.getItemAtPosition(position);
                     boolean isNow = true;
                     //Toast.makeText(getContext(), wert, Toast.LENGTH_LONG).show();
                     Intent i = new Intent(getContext(), SubActivity_BebenEintragenStart.class);
                     i.putExtra("state", isNow);
                     i.putExtra("bebenData", temp);
                     startActivityForResult(i, SUB_ACTIVITY_REQUEST_CODE);
                     return true;**/
                    //Toast.makeText(getContext(), "Beben auf map anzeigen", Toast.LENGTH_LONG).show();
                    Erdbeben temp = (Erdbeben) parent.getItemAtPosition(position);
                    ((MainActivity) getActivity()).setPager(3, temp);
                    return true;
                }
            });
            //values.add(new Erdbeben());
            ArrayAdapter<String> adapter = new CustomArrayAdapter(getContext(), values);
            listView.setAdapter(adapter);
            listView.deferNotifyDataSetChanged();
            //listView.addFooterView(new View());
            listView.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Erdbeben temp = (Erdbeben) parent.getItemAtPosition(position);
                            Intent i = new Intent(getContext(), SubActivity_DetailAnsicht.class);
                            i.putExtra("bebenData", temp);
                            i.putExtra("isAt", true);
                            startActivityForResult(i, SUB_ACTIVITY_REQUEST_CODE);
                        }
                    }
            );
            actionButtonMain.setImageResource(R.drawable.fab_x_but_rotate);
        }
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
