/**package com.quakewatch.ekos.quakewatchaustria;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

public class MainActivity extends AppCompatActivity {

    //Declaring View and Variable Elements

    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[] = {"AT", "EUROPA", "WELT", "MAP"};
    int Numboftabs = 4;
    int wantedPosition = 0;
    protected static final int SUB_ACTIVITY_REQUEST_CODE = 100;
    private ActionBarDrawerToggle hamburger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.checkInternet();
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
                        .setIcon(R.drawable.ic_warning_black_24dp)
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

            SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            String email = SP.getString("email", "");
            boolean notif = SP.getBoolean("notifications", false);
            String magn = SP.getString("magType", "1");
            Log.d("pref", "Email Addresse: " + email + "  -  Notifications: " + notif + "  -  Magnitude: " + magn);
        }
    }

    private void checkInternet() {
        ConnectivityManager con = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        boolean wifi = con.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
        boolean internet = con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
        //check Internet connection
        if (internet || wifi) {
            //your code
        } else {
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_warning_black_24dp)
                    .setTitle("No internet connection")
                    .setMessage("Please turn on mobile data")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //code for exit
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }

                    })
                    .show();
        }
    }


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
        Drawable temp = getResources().getDrawable(R.drawable.ic_warning_black_24dp);
        temp.mutate().setColorFilter(Color.parseColor("#9E9E9E"), PorterDuff.Mode.MULTIPLY);
        new AlertDialog.Builder(this)
                .setIcon(temp)
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
}
 */
package com.quakewatch.ekos.quakewatchaustria;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

public class MainActivity extends AppCompatActivity {

    //Declaring View and Variable Elements

    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[] = {"AT", "EUROPA", "WELT", "MAP"};
    int Numboftabs = 4;
    int wantedPosition = 0;
    protected static final int SUB_ACTIVITY_REQUEST_CODE = 100;
    private ActionBarDrawerToggle hamburger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                        .setIcon(R.drawable.ic_warning_black_24dp)
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

        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String email = SP.getString("email", "");
        boolean notif = SP.getBoolean("notifications", false);
        String magn = SP.getString("magType", "1");
        Log.d("pref", "Email Addresse: " + email + "  -  Notifications: " + notif + "  -  Magnitude: " + magn);
    }}



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
}