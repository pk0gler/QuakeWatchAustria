package com.quakewatch.ekos.quakewatchaustria.SubACtivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener.CustomIntensity;
import com.quakewatch.ekos.quakewatchaustria.R;

public class SubActivity_News_Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_news);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //toolbar.setNavigationIcon(R.drawable.ic_menu_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        switch (getIntent().getIntExtra("position", 1)) {
            case 0:
                toolbar.setBackgroundDrawable(getResources().getDrawable(getBaseContext().getResources().getIdentifier(CustomIntensity.bilder[0], null, getBaseContext().getPackageName())));
                break;
            case 1:
                toolbar.setBackgroundDrawable(getResources().getDrawable(getBaseContext().getResources().getIdentifier(CustomIntensity.bilder[1], null, getBaseContext().getPackageName())));
                break;
            case 2:
                toolbar.setBackgroundDrawable(getResources().getDrawable(getBaseContext().getResources().getIdentifier(CustomIntensity.bilder[2], null, getBaseContext().getPackageName())));
                break;
            case 3:
                toolbar.setBackgroundDrawable(getResources().getDrawable(getBaseContext().getResources().getIdentifier(CustomIntensity.bilder[3], null, getBaseContext().getPackageName())));
                break;
            case 4:
                toolbar.setBackgroundDrawable(getResources().getDrawable(getBaseContext().getResources().getIdentifier(CustomIntensity.bilder[4], null, getBaseContext().getPackageName())));
                break;
            case 5:
                toolbar.setBackgroundDrawable(getResources().getDrawable(getBaseContext().getResources().getIdentifier(CustomIntensity.bilder[5], null, getBaseContext().getPackageName())));
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sub_activity__app_guide, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
