package com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.Space;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener.CustomArrayAdapter;
import com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener.ViewPagerAdapter;
import com.quakewatch.ekos.quakewatchaustria.MainActivity;
import com.quakewatch.ekos.quakewatchaustria.R;
import com.quakewatch.ekos.quakewatchaustria.SubACtivities.SubActivity_BebenEintragenStart;
import com.quakewatch.ekos.quakewatchaustria.SubACtivities.SubActivity_DetailAnsicht;
import com.software.shell.fab.ActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by pkogler on 22.10.2015.
 */
public class Fragment_AT extends Fragment {

    protected static final int SUB_ACTIVITY_REQUEST_CODE = 100;
    private ImageButton FAB;
    boolean show = false;
    private ActionButton actionButtonNow;
    private ActionButton actionButtonMain;
    private ActionButton actionButtonAndere;

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


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
        new AsyncTaskParseJson().execute();
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
            mDialog.show();
        }

        @Override
        protected String doInBackground(String... arg0) {
            try {
                // instantiate our json parser
                JsonParser jParser = new JsonParser();

                // get the array of users
                JSONObject json = JsonParser.readJsonFromUrl("http://www.seismicportal.eu/fdsnws/event/1/query?limit=1000&format=json&minlatitude=46&maxlatitude=49&minlongitude=9&maxlongitude=18");
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
                    if ((flynn_region.equals("AUSTRIA") && mag >= 1)) {
                        Log.e("testla", "JA");
                        values.add(new Erdbeben(mag, flynn_region, time, depth));
                    } else
                        Log.e("testla", "NEIN");

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
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    Erdbeben temp = (Erdbeben) parent.getItemAtPosition(position);
                    boolean isNow = true;
                    //Toast.makeText(getContext(), wert, Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getContext(), SubActivity_BebenEintragenStart.class);
                    i.putExtra("state", isNow);
                    i.putExtra("bebenData", temp);
                    startActivityForResult(i, SUB_ACTIVITY_REQUEST_CODE);
                    return true;
                }
            });
            //values.add(new Erdbeben());
            ArrayAdapter<String> adapter = new CustomArrayAdapter(getContext(), values);
            listView.setAdapter(adapter);

            Space space = new Space(listView.getContext());
            space.setMinimumHeight(dpToPx(120));
            listView.addFooterView(space);

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
