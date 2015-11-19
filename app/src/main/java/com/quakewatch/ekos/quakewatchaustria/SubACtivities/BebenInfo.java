package com.quakewatch.ekos.quakewatchaustria.SubACtivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;

import com.quakewatch.ekos.quakewatchaustria.R;

/**
 * Created by Okan on 18.11.2015.
 */
public class BebenInfo extends AppCompatActivity {
    NumberPicker np;
    CheckBox cb1,cb2;
    TextView stock,gegenstand,flucht;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beb_info);

        np = (NumberPicker) findViewById(R.id.numberPicker1);
        np.setMinValue(1);
        np.setMaxValue(4);
        np.setWrapSelectorWheel(false);

        stock = (TextView) findViewById(R.id.stock);
        stock.setText("In welchem Stock befanden Sie sich?");

        cb1 = (CheckBox) findViewById(R.id.checkBox);

        gegenstand = (TextView) findViewById(R.id.umgefallen);
        gegenstand.setText("Sind Gegenstände umgefallen? ");

        cb2 = (CheckBox) findViewById(R.id.flucht);
        flucht = (TextView) findViewById(R.id.gefluechtet);
        flucht.setText("Sind Sie aus Angst ins Freie geflüchtet?");


        };
    }




