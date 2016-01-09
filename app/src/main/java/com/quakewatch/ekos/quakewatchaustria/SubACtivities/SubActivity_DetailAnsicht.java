package com.quakewatch.ekos.quakewatchaustria.SubACtivities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
    protected static final int SUB_ACTIVITY_REQUEST_CODE = 100;
    public final static String[] colorCodes2 = {
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

    public final static String[] colorCodes = {
            //Green
            "#66BB6A","#4CAF50","#43A047",
            //Yellow
            "#FFEE58","#FFEB3B","#FDD835",
            //Orange
            "#FFA726","#FF9800","#FB8C00",
            //Blue
            "#5C6BC0","#3F51B5","#3949AB",
            //Purple
            "#673AB7","#5E35B1",
            //Red
            "#C62828",
            //Status Bar Colors
            //Green
            "#2E7D32",
            //Yellow
            "#F9A825",
            //Orange
            "#EF6C00",
            //Blue
            "#283593",
            //Purple
            "#4527A0",
            //Red
            "#B71C1C"
    };

    public boolean isAt;
    private FloatingActionButton butJetzt;
    private FloatingActionButton butMap;

    private ViewPager pager;
    private Erdbeben bebenData;


    /**
     * OnCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //finish();
        super.onCreate(savedInstanceState);
        bebenData = (Erdbeben) getIntent().getExtras().getSerializable("bebenData");
        this.isAt = (boolean)getIntent().getExtras().get("isAt");
        if (isAt) {
            setContentView(R.layout.subactivity_deatailansicht_at);
            butJetzt = (FloatingActionButton) findViewById(R.id.fab_jetzt);
            butJetzt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isNow = true;
                    Intent i = new Intent(getBaseContext(), SubActivity_BebenEintragenStart.class);
                    i.putExtra("state", isNow);
                    i.putExtra("bebenData", bebenData);
                    startActivityForResult(i, SUB_ACTIVITY_REQUEST_CODE);
                    //finish();
                }
            });
        } else {
            setContentView(R.layout.subactivity_deatailansicht);
        }
        this.setUpView();
    }

    /**
     * SetUp View
     * for convenience
     */
    private void setUpView() {
        getSupportActionBar().setElevation(0f);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.detailLayout);
        //ImageView imgBottom = (ImageView) findViewById(R.id.imageViewBottom);
        TextView textMag = (TextView) findViewById(R.id.textMag);
        ImageView icon = (ImageView) findViewById(R.id.detailImg);
        butMap = (FloatingActionButton) findViewById(R.id.fab_map);
       // butMap.setImageResource(R.drawable.map);
        butMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onActivityResult(10,9,getIntent());
                Intent i = new Intent();
                i.putExtra("position", "3");
                i.putExtra("data",bebenData);
                setResult(SUCCESS_RETURN_CODE, i);
                //startActivityForResult(new Intent(getBaseContext(), MainActivity.class), 12);
                finish();
            }
        });
        TextView textDate = (TextView) findViewById(R.id.textDate);
        TextView textTime = (TextView) findViewById(R.id.textTime);
        TextView textLocation = (TextView) findViewById(R.id.textViewLocation);
        TextView values = (TextView) findViewById(R.id.textViewProperties);
        values.setText(bebenData.getRegion()+"\n"+bebenData.getMag()+"\n"+bebenData.getDepth()+"\n"+bebenData.getTime()+"\n"
        +"5.0, 1.3, -5.0");
        double temp = bebenData.getMag();
        double mag = temp;
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if ((mag >= 1) && (mag <= 2.49)) {
            if (android.os.Build.VERSION.SDK_INT >= 21) window.setStatusBarColor(Color.parseColor(colorCodes[15]));
            butMap.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(colorCodes[15])));
            if(this.butJetzt != null) butJetzt.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(colorCodes[15])));
            if ((mag >= 1.0) && (mag <= 1.49)) {
                icon.setBackgroundColor(Color.parseColor(colorCodes[0]));
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(colorCodes[0])));
            }
            if ((mag >= 1.50) && (mag <= 1.99)) {
                icon.setBackgroundColor(Color.parseColor(colorCodes[1]));
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(colorCodes[1])));
            }
            if ((mag >= 2.0) && (mag <= 2.49)) {
                icon.setBackgroundColor(Color.parseColor(colorCodes[2]));
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(colorCodes[2])));
            }

        } /*NEXT COLOR*/else if ((mag >= 2.50) && (mag <= 3.99)) {
            if (android.os.Build.VERSION.SDK_INT >= 21)window.setStatusBarColor(Color.parseColor(colorCodes[16]));
            butMap.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(colorCodes[16])));
            if(this.butJetzt != null) butJetzt.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(colorCodes[16])));
            if ((mag >= 2.50) && (mag <= 2.99)) {
                if (android.os.Build.VERSION.SDK_INT >= 21)window.setStatusBarColor(Color.parseColor(colorCodes[15]));
                butMap.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(colorCodes[15])));
                if(this.butJetzt != null) butJetzt.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(colorCodes[15])));
                icon.setBackgroundColor(Color.parseColor(colorCodes[2]));
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(colorCodes[2])));
            }
            if ((mag >= 3.0) && (mag <= 3.49)) {
                icon.setBackgroundColor(Color.parseColor(colorCodes[4]));
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(colorCodes[4])));
            }
            if ((mag >= 3.50) && (mag <= 3.99)) {
                icon.setBackgroundColor(Color.parseColor(colorCodes[5]));
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(colorCodes[5])));
            }

        }/*NEXT COLOR*/else if ((mag >= 4.0) && (mag <= 5.49)) {
            if (android.os.Build.VERSION.SDK_INT >= 21)window.setStatusBarColor(Color.parseColor(colorCodes[17]));
            butMap.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(colorCodes[17])));
            if(this.butJetzt != null) butJetzt.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(colorCodes[17])));
            if ((mag >= 4.0) && (mag <= 4.49)) {
                icon.setBackgroundColor(Color.parseColor(colorCodes[6]));
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(colorCodes[6])));
            }
            if ((mag >= 4.50) && (mag <= 4.99)) {
                icon.setBackgroundColor(Color.parseColor(colorCodes[7]));
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(colorCodes[7])));
            }
            if ((mag >= 5.0) && (mag <= 5.49)) {
                icon.setBackgroundColor(Color.parseColor(colorCodes[8]));
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(colorCodes[8])));
            }

        }/*NEXT COLOR*/else if ((mag >= 5.50) && (mag <= 6.99)) {
            if (android.os.Build.VERSION.SDK_INT >= 21)window.setStatusBarColor(Color.parseColor(colorCodes[18]));
            butMap.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(colorCodes[18])));
            if(this.butJetzt != null) butJetzt.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(colorCodes[18])));
            if ((mag >= 5.50) && (mag <= 5.99)) {
                icon.setBackgroundColor(Color.parseColor(colorCodes[9]));
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(colorCodes[9])));
            }
            if ((mag >= 6.0) && (mag <= 6.49)) {
                icon.setBackgroundColor(Color.parseColor(colorCodes[10]));
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(colorCodes[10])));
            }
            if ((mag >= 6.50) && (mag <= 6.99)) {
                icon.setBackgroundColor(Color.parseColor(colorCodes[11]));
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(colorCodes[11])));
            }

        }/*NEXT COLOR*/else if ((mag >= 7.0) && (mag <= 8.99)) {
            if (android.os.Build.VERSION.SDK_INT >= 21)window.setStatusBarColor(Color.parseColor(colorCodes[19]));
            butMap.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(colorCodes[19])));
            if(this.butJetzt != null) butJetzt.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(colorCodes[19])));
            if ((mag >= 7.0) && (mag <= 7.99)) {
                icon.setBackgroundColor(Color.parseColor(colorCodes[12]));
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(colorCodes[12])));
            }
            if ((mag >= 8.0) && (mag <= 8.99)) {
                if (android.os.Build.VERSION.SDK_INT >= 21)window.setStatusBarColor(Color.parseColor(colorCodes[20]));
                butMap.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(colorCodes[20])));
                if(this.butJetzt != null) butJetzt.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(colorCodes[20])));
                icon.setBackgroundColor(Color.parseColor(colorCodes[14]));
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(colorCodes[14])));
            }
        }/*NEXT COLOR*/else if ((mag >= 9.0) && (mag <= 12)) {
            if (android.os.Build.VERSION.SDK_INT >= 21)window.setStatusBarColor(Color.parseColor(colorCodes[20]));
            butMap.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(colorCodes[20])));
            if(this.butJetzt != null) butJetzt.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(colorCodes[20])));
            icon.setBackgroundColor(Color.parseColor(colorCodes[14]));
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(colorCodes[14])));
        }
        textMag.setText(bebenData.getMag()+"");
        textDate.setText(bebenData.getDate());
        textLocation.setText(bebenData.getRegion());
        textTime.setText(bebenData.getTime());
    }

    /**
     * When
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent i = new Intent();
                setResult(SUCCESS_RETURN_CODE, i);
                i.putExtra("position", "1");
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}