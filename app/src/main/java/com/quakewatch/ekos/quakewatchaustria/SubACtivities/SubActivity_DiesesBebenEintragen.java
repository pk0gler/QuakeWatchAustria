package com.quakewatch.ekos.quakewatchaustria.SubACtivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.quakewatch.ekos.quakewatchaustria.R;
import com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments.Erdbeben;

/**
 * Created by pkogler on 22.10.2015.
 */
public class SubActivity_DiesesBebenEintragen extends AppCompatActivity {
    public final static int SUCCESS_RETURN_CODE = 1;
    private TextView state;
    private Erdbeben bebenData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bebenData = (Erdbeben) getIntent().getExtras().get("bebenData");
        setContentView(R.layout.subactivity_beben);
        state = (TextView) findViewById(R.id.jetztodned);
        boolean state = (boolean) getIntent().getExtras().get("state");
        if (state) {
            setUpNow();
        } else {
            setUp();
        }
   }

    private void setUp() {
        state.setText("Nicht Jetzt\n"+bebenData.getRegion()+"\n"+bebenData.getMag());
    }


    private void setUpNow() {
        state.setText("Jetzt\n"+bebenData.getRegion()+"\n"+bebenData.getMag());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent i = new Intent();
                setResult(SUCCESS_RETURN_CODE, i);
                //startActivityForResult(new Intent(getBaseContext(), MainActivity.class), 12);
                i.putExtra("position", "1");
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
