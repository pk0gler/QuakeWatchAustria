package com.quakewatch.ekos.quakewatchaustria.SubACtivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.quakewatch.ekos.quakewatchaustria.R;

/**
 * Created by pkogler on 24.10.2015.
 */
public class SubActivity_Settings extends AppCompatActivity {
    public final static int SUCCESS_RETURN_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subactivity_settings);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent i = new Intent();
                setResult(SUCCESS_RETURN_CODE, i);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
