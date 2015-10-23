package com.quakewatch.ekos.quakewatchaustria.SubACtivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.quakewatch.ekos.quakewatchaustria.MainActivity;
import com.quakewatch.ekos.quakewatchaustria.R;

/**
 * Created by pkogler on 22.10.2015.
 */
public class SubActivity_DetailAnsicht extends AppCompatActivity {
    public final static int SUCCESS_RETURN_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subactivity);

        this.setUpView();

       // getActionBar().setDisplayHomeAsUpEnabled(true);

        Button button = (Button) findViewById(R.id.btnSub);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                setResult(SUCCESS_RETURN_CODE, i);
                finish();
            }
        });
    }

    private void setUpView() {
        String bebenMagn = (String) getIntent().getExtras().get("bebenData");
        TextView view1 = (TextView) findViewById(R.id.subText1);
        view1.setText(bebenMagn);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
