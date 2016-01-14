package com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener;

/**
 * Created by pkogler on 22.10.2015.
 */

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments.FRAGMENT_MAP;
import com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments.FRAGMENT_WELT;
import com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments.Fragment_AT;
import com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments.Fragment_EUROPA;


/**
 * Created by hp1 on 21-01-2015.
 * Usage:   Adapter for swiping through Fragments
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    public FRAGMENT_MAP mapf;
    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    Context context;
    String magStaerke;
    private FRAGMENT_WELT weltf;

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb, Context context) {
        super(fm);
        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
        this.context = context;

    }

    public FRAGMENT_MAP getMapf() {
        return this.mapf;
    }

    /**
     * This method return the fragment for the every position in the View Pager
     *
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Fragment_AT();
            case 1:
                return new Fragment_EUROPA();
            case 2:
                this.weltf = new FRAGMENT_WELT();
                return this.weltf;
            case 3:
                Log.d("drin page", "page");
                this.mapf = new FRAGMENT_MAP();
                //this.mapf.setMarker(this.weltf.getMarker());
                return this.mapf;
            default:
                return null;
        }
    }

    /**
     * This method return the titles for the Tabs in the Tab Strip
     *
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    /**
     * This method return the Number of tabs for the tabs Strip
     *
     * @return
     */
    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}