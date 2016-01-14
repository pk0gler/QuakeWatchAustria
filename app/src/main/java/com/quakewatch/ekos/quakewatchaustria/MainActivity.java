/**
 * package com.quakewatch.ekos.quakewatchaustria;
 * <p/>
 * import android.app.Activity;
 * import android.content.Context;
 * import android.content.DialogInterface;
 * import android.content.Intent;
 * import android.content.SharedPreferences;
 * import android.graphics.Color;
 * import android.graphics.PorterDuff;
 * import android.graphics.drawable.Drawable;
 * import android.net.ConnectivityManager;
 * import android.net.NetworkInfo;
 * import android.os.Bundle;
 * import android.preference.PreferenceManager;
 * import android.support.design.widget.NavigationView;
 * import android.support.v4.view.GravityCompat;
 * import android.support.v4.view.ViewPager;
 * import android.support.v4.widget.DrawerLayout;
 * import android.support.v7.app.ActionBarDrawerToggle;
 * import android.support.v7.app.AlertDialog;
 * import android.support.v7.app.AppCompatActivity;
 * import android.support.v7.widget.Toolbar;
 * import android.util.Log;
 * import android.view.MenuItem;
 * <p/>
 * import com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener.ViewPagerAdapter;
 * import com.quakewatch.ekos.quakewatchaustria.Libaries.SlidingTabLayout;
 * import com.quakewatch.ekos.quakewatchaustria.SubACtivities.SubActivity_App_Guide;
 * import com.quakewatch.ekos.quakewatchaustria.SubACtivities.SubActivity_Guide;
 * import com.quakewatch.ekos.quakewatchaustria.SubACtivities.SubActivity_News;
 * import com.quakewatch.ekos.quakewatchaustria.SubACtivities.SubActivity_SettingsActivity;
 * import com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments.Erdbeben;
 * <p/>
 * public class MainActivity extends AppCompatActivity {
 * <p/>
 * //Declaring View and Variable Elements
 * <p/>
 * Toolbar toolbar;
 * ViewPager pager;
 * ViewPagerAdapter adapter;
 * SlidingTabLayout tabs;
 * CharSequence Titles[] = {"AT", "EUROPA", "WELT", "MAP"};
 * int Numboftabs = 4;
 * int wantedPosition = 0;
 * protected static final int SUB_ACTIVITY_REQUEST_CODE = 100;
 * private ActionBarDrawerToggle hamburger;
 *
 * @Override protected void onCreate(Bundle savedInstanceState) {
 * super.onCreate(savedInstanceState);
 * this.checkInternet();
 * setContentView(R.layout.activity_main);
 * //Creating the Toolbar and setting it as the toolbar for the activity
 * toolbar = (Toolbar) findViewById(R.id.tool_bar);
 * setSupportActionBar(toolbar);
 * getSupportActionBar().setDisplayHomeAsUpEnabled(true);
 * final DrawerLayout navDraw = (DrawerLayout) findViewById(R.id.drawer_layout);
 * hamburger = new ActionBarDrawerToggle(this, navDraw, toolbar, R.string.app_name, R.string.app_name);
 * navDraw.setDrawerListener(hamburger);
 * NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
 * navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
 * @Override public boolean onNavigationItemSelected(MenuItem item) {
 * //Toast.makeText(getBaseContext(), "Hi",Toast.LENGTH_SHORT).show();
 * navDraw.closeDrawer(GravityCompat.START);
 * displayView(item);
 * return false;
 * }
 * });
 * if (!isNetworkAvailable(getBaseContext())) {
 * try {
 * new AlertDialog.Builder(this)
 * .setIcon(R.drawable.ic_warning_black_24dp)
 * .setTitle("No internet connection")
 * .setMessage("Please turn on mobile data")
 * .setPositiveButton("OK", new DialogInterface.OnClickListener() {
 * @Override public void onClick(DialogInterface dialog, int which) {
 * android.os.Process.killProcess(android.os.Process.myPid());
 * }
 * <p/>
 * })
 * .show();
 * } catch (Exception e) {
 * Log.d("hi", "Show Dialog: " + e.getMessage());
 * }
 * } else {
 * //toolbar.colo
 * <p/>
 * //Creating and assigning the View Pager Adapter
 * adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, Numboftabs, getBaseContext());
 * <p/>
 * <p/>
 * //Setting the Adapter
 * pager = (ViewPager) findViewById(R.id.pager);
 * pager.setAdapter(adapter);
 * pager.setOffscreenPageLimit(4);
 * //Assigning Tab Layout
 * tabs = (SlidingTabLayout) findViewById(R.id.tabs);
 * tabs.setDistributeEvenly(true); //To set the Tabs Fixes spacing evenly in width
 * <p/>
 * //Setting custom COlor for tab silder
 * tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
 * @Override public int getIndicatorColor(int position) {
 * return getResources().getColor(R.color.tabsScrollColor);
 * }
 * });
 * <p/>
 * //Setting the Viewpager for the SlidingTabsLayout
 * tabs.setViewPager(pager);
 * <p/>
 * SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
 * String email = SP.getString("email", "");
 * boolean notif = SP.getBoolean("notifications", false);
 * String magn = SP.getString("magType", "1");
 * Log.d("pref", "Email Addresse: " + email + "  -  Notifications: " + notif + "  -  Magnitude: " + magn);
 * }
 * }
 * <p/>
 * private void checkInternet() {
 * ConnectivityManager con = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
 * boolean wifi = con.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
 * boolean internet = con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
 * //check Internet connection
 * if (internet || wifi) {
 * //your code
 * } else {
 * new AlertDialog.Builder(this)
 * .setIcon(R.drawable.ic_warning_black_24dp)
 * .setTitle("No internet connection")
 * .setMessage("Please turn on mobile data")
 * .setPositiveButton("OK", new DialogInterface.OnClickListener() {
 * @Override public void onClick(DialogInterface dialog, int which) {
 * //code for exit
 * Intent intent = new Intent(Intent.ACTION_MAIN);
 * intent.addCategory(Intent.CATEGORY_HOME);
 * intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
 * startActivity(intent);
 * }
 * <p/>
 * })
 * .show();
 * }
 * }
 * <p/>
 * <p/>
 * public static boolean isNetworkAvailable(Context context) {
 * boolean outcome = false;
 * <p/>
 * if (context != null) {
 * ConnectivityManager cm = (ConnectivityManager) context
 * .getSystemService(Context.CONNECTIVITY_SERVICE);
 * <p/>
 * NetworkInfo[] networkInfos = cm.getAllNetworkInfo();
 * for (NetworkInfo tempNetworkInfo : networkInfos) {
 * <p/>
 * if (tempNetworkInfo.isConnected()) {
 * outcome = true;
 * break;
 * }
 * }
 * }
 * <p/>
 * return outcome;
 * }
 * <p/>
 * private void displayView(MenuItem item) {
 * if (item.toString().equals("Guide")) {
 * Log.d("NavDrawer", item.toString());
 * startActivity(new Intent(getBaseContext(), SubActivity_Guide.class));
 * } else if (item.toString().equals("Einstellungen")) {
 * Log.d("NavDrawer", item.toString());
 * startActivity(new Intent(getBaseContext(), SubActivity_SettingsActivity.class));
 * } else if (item.toString().equals("App Guide")) {
 * Log.d("NavDrawer", item.toString());
 * startActivity(new Intent(getBaseContext(), SubActivity_App_Guide.class));
 * } else if (item.toString().equals("News")) {
 * startActivity(new Intent(getBaseContext(), SubActivity_News.class));
 * }
 * }
 * <p/>
 * public ViewPager getAdapter() {
 * return pager;
 * }
 * <p/>
 * public void setPager(int i, Erdbeben temp) {
 * this.pager.setCurrentItem(i);
 * this.adapter.getMapf().setCurrentLoc(temp);
 * }
 * @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
 * super.onActivityResult(requestCode, resultCode, data);
 * try {
 * if (Integer.parseInt(data.getStringExtra("position")) == 3) {
 * pager.setCurrentItem(Integer.parseInt(data.getStringExtra("position")), true);
 * Log.d("NavDrawer", "philippkoggler" + data.getExtras().getSerializable("data"));
 * this.adapter.getMapf().setCurrentLoc((Erdbeben) data.getExtras().getSerializable("data"));
 * <p/>
 * }
 * } catch (NullPointerException e) {
 * e.printStackTrace();
 * }
 * }
 * @Override public void onBackPressed() {
 * Drawable temp = getResources().getDrawable(R.drawable.ic_warning_black_24dp);
 * temp.mutate().setColorFilter(Color.parseColor("#9E9E9E"), PorterDuff.Mode.MULTIPLY);
 * new AlertDialog.Builder(this)
 * .setIcon(temp)
 * .setTitle("App schließen")
 * .setMessage("Sind sie sicher dass sie die App beenden wollen")
 * .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
 * @Override public void onClick(DialogInterface dialog, int which) {
 * finish();
 * }
 * <p/>
 * })
 * .setNegativeButton("Nein", null)
 * .show();
 * }
 * }
 */
package com.quakewatch.ekos.quakewatchaustria;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener.ViewPagerAdapter;
import com.quakewatch.ekos.quakewatchaustria.Libaries.SlidingTabLayout;
import com.quakewatch.ekos.quakewatchaustria.SubACtivities.SubActivity_App_Guide;
import com.quakewatch.ekos.quakewatchaustria.SubACtivities.SubActivity_Guide;
import com.quakewatch.ekos.quakewatchaustria.SubACtivities.SubActivity_News;
import com.quakewatch.ekos.quakewatchaustria.SubACtivities.SubActivity_SettingsActivity;
import com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments.Erdbeben;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    //Declaring View and Variable Elements

    protected static final int SUB_ACTIVITY_REQUEST_CODE = 100;
    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[] = {"AT", "EUROPA", "WELT", "MAP"};
    int Numboftabs = 4;
    int wantedPosition = 0;
    SharedPreferences SP;
    private ActionBarDrawerToggle hamburger;

    public static boolean isNetworkAvailable(Context context) {
        boolean outcome = false;

        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo[] networkInfos = cm.getAllNetworkInfo();
            for (NetworkInfo tempNetworkInfo : networkInfos) {

                if (tempNetworkInfo.isConnected()) {
                    outcome = true;
                    break;
                }
            }
        }

        return outcome;
    }

    /**
     * Die Map wird mit den Inhalten gefuellt
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!prefs.getBoolean("firstTime", false)) {
            // <---- run your one time code here
            new SendfeedbackJob().execute();

            // mark first time has runned.
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }
        SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        Log.d("apikey", SP.getString("apikey", ""));
        setContentView(R.layout.activity_main);
        //Creating the Toolbar and setting it as the toolbar for the activity
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final DrawerLayout navDraw = (DrawerLayout) findViewById(R.id.drawer_layout);
        hamburger = new ActionBarDrawerToggle(this, navDraw, toolbar, R.string.app_name, R.string.app_name);
        navDraw.setDrawerListener(hamburger);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                //Toast.makeText(getBaseContext(), "Hi",Toast.LENGTH_SHORT).show();
                navDraw.closeDrawer(GravityCompat.START);
                displayView(item);
                return false;
            }
        });
        if (!isNetworkAvailable(getBaseContext())) {
            try {
                new AlertDialog.Builder(this)
                        .setIcon(R.drawable.fab_plus_icon)
                        .setTitle("No internet connection")
                        .setMessage("Please turn on mobile data")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                android.os.Process.killProcess(android.os.Process.myPid());
                            }

                        })
                        .show();
            } catch (Exception e) {
                Log.d("hi", "Show Dialog: " + e.getMessage());
            }
        } else {
            //toolbar.colo

            //Creating and assigning the View Pager Adapter
            adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, Numboftabs, getBaseContext());


            //Setting the Adapter
            pager = (ViewPager) findViewById(R.id.pager);
            pager.setAdapter(adapter);
            pager.setOffscreenPageLimit(4);
            //Assigning Tab Layout
            tabs = (SlidingTabLayout) findViewById(R.id.tabs);
            tabs.setDistributeEvenly(true); //To set the Tabs Fixes spacing evenly in width

            //Setting custom COlor for tab silder
            tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
                @Override
                public int getIndicatorColor(int position) {
                    return getResources().getColor(R.color.tabsScrollColor);
                }
            });

            //Setting the Viewpager for the SlidingTabsLayout
            tabs.setViewPager(pager);

            String email = SP.getString("email", "");
            boolean notif = SP.getBoolean("notifications", false);
            String magn = SP.getString("magType", "1");
            Log.d("pref", "Email Addresse: " + email + "  -  Notifications: " + notif + "  -  Magnitude: " + magn);
        }
    }

    private void displayView(MenuItem item) {
        if (item.toString().equals("Guide")) {
            Log.d("NavDrawer", item.toString());
            startActivity(new Intent(getBaseContext(), SubActivity_Guide.class));
        } else if (item.toString().equals("Einstellungen")) {
            Log.d("NavDrawer", item.toString());
            startActivity(new Intent(getBaseContext(), SubActivity_SettingsActivity.class));
        } else if (item.toString().equals("App Guide")) {
            Log.d("NavDrawer", item.toString());
            startActivity(new Intent(getBaseContext(), SubActivity_App_Guide.class));
        } else if (item.toString().equals("News")) {
            startActivity(new Intent(getBaseContext(), SubActivity_News.class));
        }
    }

    public ViewPager getAdapter() {
        return pager;
    }

    public void setPager(int i, Erdbeben temp) {
        this.pager.setCurrentItem(i);
        this.adapter.getMapf().setCurrentLoc(temp);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (Integer.parseInt(data.getStringExtra("position")) == 3) {
                pager.setCurrentItem(Integer.parseInt(data.getStringExtra("position")), true);
                Log.d("NavDrawer", "philippkoggler" + data.getExtras().getSerializable("data"));
                this.adapter.getMapf().setCurrentLoc((Erdbeben) data.getExtras().getSerializable("data"));

            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("App schließen")
                .setMessage("Sind sie sicher dass sie die App beenden wollen")
                .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("Nein", null)
                .show();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        hamburger.syncState();
    }

    private void makePostRequest() {


        HttpClient httpClient = new DefaultHttpClient();
        // replace with your url
        HttpPost httpPost = new HttpPost("http://geoweb.zamg.ac.at/quakeapi/v01/getapikey");


        //Encoding POST data
        //httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Authorization", "Basic cXVha2VhcGk6I3FrcCZtbGRuZyM=");

        //making POST request.
        try {
            HttpResponse response = httpClient.execute(httpPost);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            String json = reader.readLine();
            JSONTokener tokener = new JSONTokener(json);
            JSONObject finalResult = new JSONObject(tokener);
            Log.d("Http Post Response:", finalResult.getString("apikey"));
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("apikey", finalResult.getString("apikey"));
            editor.commit();
            // write response to log
        } catch (ClientProtocolException e) {
            // Log exception
            e.printStackTrace();
        } catch (Exception e) {
            // Log exception
            e.printStackTrace();
        }

    }

    private class SendfeedbackJob extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String[] params) {
            // do above Server call here
            makePostRequest();
            return null;
        }

        @Override
        protected void onPostExecute(String message) {
            //process message
        }
    }

}