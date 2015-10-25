package com.quakewatch.ekos.quakewatchaustria;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener.ViewPagerAdapter;
import com.quakewatch.ekos.quakewatchaustria.Libaries.SlidingTabLayout;
import com.quakewatch.ekos.quakewatchaustria.SubACtivities.SubActivity_BebenEintragenStart;
import com.quakewatch.ekos.quakewatchaustria.SubACtivities.SubActivity_SettingsActivity;

public class MainActivity extends AppCompatActivity {

    //Declaring View and Variable Elements

    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"AT","EUROPA","WELT","MAP"};
    int Numboftabs = 4;
    protected static final int SUB_ACTIVITY_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Creating the Toolbar and setting it as the toolbar for the activity
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

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
        Log.d("pref","Email Addresse: "+email+"  -  Notifications: "+notif + "  -  Magnitude: "+magn);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(this.getBaseContext(), SubActivity_SettingsActivity.class);
            startActivityForResult(i, SUB_ACTIVITY_REQUEST_CODE);
            return true;
        } else {
            boolean isNow = false;
            //Toast.makeText(getContext(), wert, Toast.LENGTH_LONG).show();
            Intent i = new Intent(getBaseContext(), SubActivity_BebenEintragenStart.class);
            i.putExtra("state", isNow);
            startActivityForResult(i, SUB_ACTIVITY_REQUEST_CODE);
        }

        return super.onOptionsItemSelected(item);
    }
}
