package com.quakewatch.ekos.quakewatchaustria.SubACtivities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quakewatch.ekos.quakewatchaustria.R;
import com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments.Erdbeben;

import java.io.Serializable;

/**
 * Created by pkogler on 22.10.2015.
 * Usage:   Detailansicht when one Quake in the ListView is
 *          clicked
 *          It processes Data between the Fragment activities
 */
public class SubActivity_DetailAnsicht extends AppCompatActivity implements Serializable {
    public final static int SUCCESS_RETURN_CODE = 1;
    public final static String[] colorCodes = {
            //Green
            "#3EA739","#338B2E","#296F25",
            //Yellow
            "#FBFE00","#D5D800","#B1B300",
            //Blue
            "#39508A","#2F4273","#25355C",
            //Orange
            "#FFA415","#FF9C00","#E98F00",
            //Purple
            "#D91283","#BB006A",
            //Red
            "#CA0000"
    };


    /**
     * OnCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subactivity_deatailansicht);
        this.setUpView();
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

    /**
     * SetUp View
     * for convenience
     */
    private void setUpView() {
        Erdbeben bebenData = (Erdbeben) getIntent().getExtras().getSerializable("bebenData");
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.detailLayout);
        ImageView imgBottom = (ImageView) findViewById(R.id.imageViewBottom);
        TextView textMag = (TextView) findViewById(R.id.textMag);
        ImageView icon = (ImageView) findViewById(R.id.detailImg);

        TextView textDate = (TextView) findViewById(R.id.textDate);
        TextView textTime = (TextView) findViewById(R.id.textTime);
        TextView textLocation = (TextView) findViewById(R.id.textViewLocation);

        double temp = bebenData.getMag();
        if ((temp >= 1) && (temp <= 2.4)) {
            if ((temp >= 1) && (temp <= 1.4)) {
                icon.setBackgroundColor(Color.parseColor(colorCodes[0]));
                //imgBottom.setBackgroundColor(Color.parseColor(colorCodes[0]));
            }
            if ((temp >= 1.5) && (temp <= 1.9)) {
                icon.setBackgroundColor(Color.parseColor(colorCodes[1]));
            }
            if ((temp >= 2) && (temp <= 2.4)) {
                icon.setBackgroundColor(Color.parseColor(colorCodes[2]));
            }

        } /*NEXT COLOR*/else if ((temp >= 2.5) && (temp <= 3.9)) {
            if ((temp >= 2.5) && (temp <= 2.9)) {
                icon.setBackgroundColor(Color.parseColor(colorCodes[3]));
            }
            if ((temp >= 3) && (temp <= 3.4)) {
                icon.setBackgroundColor(Color.parseColor(colorCodes[4]));
            }
            if ((temp >= 3.5) && (temp <= 3.9)) {
                icon.setBackgroundColor(Color.parseColor(colorCodes[5]));
            }

        }/*NEXT COLOR*/else if ((temp >= 4) && (temp <= 5.4)) {
            if ((temp >= 4) && (temp <= 4.4)) {
                icon.setBackgroundColor(Color.parseColor(colorCodes[6]));
            }
            if ((temp >= 4.5) && (temp <= 4.9)) {
                icon.setBackgroundColor(Color.parseColor(colorCodes[7]));
            }
            if ((temp >= 5) && (temp <= 5.4)) {
                icon.setBackgroundColor(Color.parseColor(colorCodes[8]));
            }

        }/*NEXT COLOR*/else if ((temp >= 5.5) && (temp <= 6.9)) {
            if ((temp >= 5.5) && (temp <= 5.9)) {
                icon.setBackgroundColor(Color.parseColor(colorCodes[9]));
            }
            if ((temp >= 6) && (temp <= 6.4)) {
                icon.setBackgroundColor(Color.parseColor(colorCodes[10]));
            }
            if ((temp >= 6.5) && (temp <= 6.9)) {
                icon.setBackgroundColor(Color.parseColor(colorCodes[11]));
            }

        }/*NEXT COLOR*/else if ((temp >= 7) && (temp <= 8.9)) {
            if ((temp >= 7) && (temp <= 7.9)) {
                icon.setBackgroundColor(Color.parseColor(colorCodes[12]));
            }
            if ((temp >= 8) && (temp <= 8.9)) {
                icon.setBackgroundColor(Color.parseColor(colorCodes[13]));
            }
        }/*NEXT COLOR*/else if ((temp >= 9) && (temp <= 12)) {
            icon.setBackgroundColor(Color.parseColor(colorCodes[14]));
        }
        textMag.setText(bebenData.getMag()+"");
        textDate.setText(bebenData.getDate());
        textLocation.setText(bebenData.getRegion());
        textTime.setText(bebenData.getTime());
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