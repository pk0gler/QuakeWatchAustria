package com.quakewatch.ekos.quakewatchaustria;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener.ViewPagerAdapter;
import com.quakewatch.ekos.quakewatchaustria.Libaries.SlidingTabLayout;
import com.quakewatch.ekos.quakewatchaustria.SubACtivities.SubActivity_App_Guide;
import com.quakewatch.ekos.quakewatchaustria.SubACtivities.SubActivity_Guide;
import com.quakewatch.ekos.quakewatchaustria.SubACtivities.SubActivity_News;
import com.quakewatch.ekos.quakewatchaustria.SubACtivities.SubActivity_SettingsActivity;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void displayView(MenuItem item) {
        if (item.toString().equals("Guide")) {
            Log.d("NavDrawer",item.toString());
            startActivity(new Intent(getBaseContext(), SubActivity_Guide.class));
        } else if (item.toString().equals("Einstellungen")) {
            Log.d("NavDrawer",item.toString());
            startActivity(new Intent(getBaseContext(), SubActivity_SettingsActivity.class));
        } else if (item.toString().equals("App Guide")) {
            Log.d("NavDrawer",item.toString());
            startActivity(new Intent(getBaseContext(), SubActivity_App_Guide.class));
        } else if (item.toString().equals("News")) {
            startActivity(new Intent(getBaseContext(), SubActivity_News.class));
        }
    }

    public ViewPager getAdapter() {
        return pager;
    }

    public void setPager(int i) {
        this.pager.setCurrentItem(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (Integer.parseInt(data.getStringExtra("position")) == 3) {
                pager.setCurrentItem(Integer.parseInt(data.getStringExtra("position")),true);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        hamburger.syncState();
    }
}