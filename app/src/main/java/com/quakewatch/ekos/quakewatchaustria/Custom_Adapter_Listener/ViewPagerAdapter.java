package com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener;

/**
 * Created by pkogler on 22.10.2015.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
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
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    Context context;
    String magStaerke;

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb, Context context) {
        super(fm);
        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
        this.context = context;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {
        
        switch (position) {
            case 0:
                return new Fragment_AT();
            case 1:
                return new Fragment_EUROPA();
            case 2:
                return new FRAGMENT_WELT();
            case 3:
                Log.d("drin page", "page");
                return new FRAGMENT_MAP();
            default:
                return null;
        }
    }

    // This method return the titles for the Tabs in the Tab Strip
    Drawable myDrawable;
    String title;
    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
        /*SpannableStringBuilder sb = new SpannableStringBuilder(" Page " + (position + 1)); // space added before text for convenience

        Drawable drawable = context.getResources().getDrawable( R.drawable.ic_launcher );
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
        sb.setSpan(span, 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return sb;*/
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }

    
}