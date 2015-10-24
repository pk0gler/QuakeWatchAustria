package com.quakewatch.ekos.quakewatchaustria.SubACtivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.quakewatch.ekos.quakewatchaustria.R;

/**
 * Created by pkogler on 22.10.2015.
 */
public class SubActivity_DiesesBebenEintragen extends AppCompatActivity {
    public final static int SUCCESS_RETURN_CODE = 1;
    private TextView state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subactivity_beben);
        state = (TextView) findViewById(R.id.jetztodned);
        boolean state = (boolean) getIntent().getExtras().get("state");
        if (state) {
            setUpNow();
        } else {
            setUp();
        }
        //this.setUpView();

        // getActionBar().setDisplayHomeAsUpEnabled(true);

        /*Button button = (Button) findViewById(R.id.btnSub);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                setResult(SUCCESS_RETURN_CODE, i);
                finish();
            }
        });*/
    }

    private void setUp() {
        state.setText("Nicht Jetzt");
    }


    private void setUpNow() {
        state.setText("Jetzt");
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
