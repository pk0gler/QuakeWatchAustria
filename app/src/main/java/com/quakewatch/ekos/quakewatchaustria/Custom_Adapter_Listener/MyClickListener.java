package com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.quakewatch.ekos.quakewatchaustria.MainActivity;
import com.quakewatch.ekos.quakewatchaustria.R;
import com.quakewatch.ekos.quakewatchaustria.SubACtivities.SubActivity_DetailAnsicht;
import com.software.shell.fab.ActionButton;

import javax.crypto.Mac;

/**
 * Created by philippkogler on 31.10.15.
 */
public class MyClickListener implements View.OnClickListener {
    private ActionButton button;
    private ViewPagerAdapter pager;
    private Context context;

    public MyClickListener(ActionButton button, Context context) {
        this.button = button;
        this.context = context;
    }
    public MyClickListener(ActionButton button, Context context,ViewPagerAdapter pager) {
        this.button = button;
        this.pager = pager;
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        if (button.getId() == R.id.action_button_map) {
            Log.d("ibins", "map");
            ((SubActivity_DetailAnsicht)context).finish();
        } else if (button.getId() == R.id.action_button_jetzt) {
            Log.d("ibins", "eintragen");
        }
    }

}
