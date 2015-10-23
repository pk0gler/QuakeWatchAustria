package com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener.CustomArrayAdapter;
import com.quakewatch.ekos.quakewatchaustria.R;
import com.quakewatch.ekos.quakewatchaustria.SubACtivities.SubActivity_BebenEintragenStart;
import com.quakewatch.ekos.quakewatchaustria.SubACtivities.SubActivity_DetailAnsicht;
import com.software.shell.fab.ActionButton;

import java.util.ArrayList;

/**
 * Created by pkogler on 22.10.2015.
 */
public class Fragment_AT extends Fragment {

    protected static final int SUB_ACTIVITY_REQUEST_CODE = 100;
    private ImageButton FAB;
    boolean show = false;
    private ActionButton actionButtonNow;
    private ActionButton actionButtonMain;
    private ActionButton actionButtonAndere;

    private View v;
    private TextView tJetzt;
    private TextView tAndere;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.list_layout_at,container,false);
        tJetzt = (TextView) v.findViewById(R.id.tJetzt);
        tJetzt.setVisibility(View.GONE);
        tAndere = (TextView) v.findViewById(R.id.tAndere);
        tAndere.setVisibility(View.GONE);
        //tJetzt.setBackground("#FFFFFF");
        //return v;
        final ListView listView = (ListView) v.findViewById(R.id.listAt);
        ArrayList<String> values = new ArrayList<String>();
        for (int i=0; i<100; i++) {
            int zahl = (int)((Math.random()) * 9 + 1);
            int zahl2 = (int)((Math.random()) * 9 + 0);
            values.add(i, zahl+"."+zahl2);
        }
        ArrayAdapter<String> adapter = new CustomArrayAdapter(getContext(), values);
        listView.setAdapter(adapter);
        //listView.setOnLongClickListener();
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String wert = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(getContext(), wert, Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getContext(), SubActivity_DetailAnsicht.class);
                        i.putExtra("bebenData", wert);
                        startActivityForResult(i, SUB_ACTIVITY_REQUEST_CODE);
                    }
                }
        );


        actionButtonMain = (ActionButton) v.findViewById(R.id.action_button_main);
        actionButtonMain.setImageResource(R.drawable.fab_plus_icon);

        actionButtonNow = (ActionButton) v.findViewById(R.id.action_button_jetzt);
        actionButtonAndere = (ActionButton) v.findViewById(R.id.action_button_andere);

        actionButtonNow.setType(ActionButton.Type.MINI);
        actionButtonNow.setImageResource(R.drawable.fab_plus_icon);

        actionButtonAndere.setType(ActionButton.Type.MINI);
        actionButtonAndere.setImageResource(R.drawable.fab_plus_icon);

        actionButtonAndere.hide();
        actionButtonNow.hide();

        actionButtonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (show) {
                    //v.setAlpha(1f);
                    listView.setEnabled(true);
                    tJetzt.setVisibility(View.GONE);
                    tAndere.setVisibility(View.GONE);
                    listView.setAlpha(1f);
                    listView.setBackgroundColor(Color.WHITE);
                    actionButtonAndere.hide();
                    actionButtonNow.hide();
                    show = false;
                } else {
                    listView.setEnabled(false);
                    tJetzt.setVisibility(View.VISIBLE);
                    tAndere.setVisibility(View.VISIBLE);
                    tAndere.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.fab_fade_in));
                    tJetzt.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.fab_fade_in));

                    listView.setAlpha(0.17f);
                    actionButtonMain.setAlpha(1f);
                    listView.setBackgroundColor(Color.DKGRAY);
                    actionButtonNow.setHideAnimation(ActionButton.Animations.ROLL_TO_RIGHT);
                    actionButtonAndere.setHideAnimation(ActionButton.Animations.ROLL_TO_RIGHT);
                    actionButtonAndere.show();
                    actionButtonNow.show();
                    show = true;
                }
            }
        });


        actionButtonNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setEnabled(true);
                tJetzt.setVisibility(View.GONE);
                tAndere.setVisibility(View.GONE);
                listView.setAlpha(1f);
                listView.setBackgroundColor(Color.WHITE);
                actionButtonAndere.hide();
                actionButtonNow.hide();
                show = false;

                boolean isNow = true;
                //Toast.makeText(getContext(), wert, Toast.LENGTH_LONG).show();
                Intent i = new Intent(getContext(), SubActivity_BebenEintragenStart.class);
                i.putExtra("state", isNow);
                startActivityForResult(i, SUB_ACTIVITY_REQUEST_CODE);
            }
        });


        actionButtonAndere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setEnabled(true);
                tJetzt.setVisibility(View.GONE);
                tAndere.setVisibility(View.GONE);
                listView.setAlpha(1f);
                listView.setBackgroundColor(Color.WHITE);
                actionButtonAndere.hide();
                actionButtonNow.hide();
                show = false;

                boolean isNow = false;
                //Toast.makeText(getContext(), wert, Toast.LENGTH_LONG).show();
                Intent i = new Intent(getContext(), SubActivity_BebenEintragenStart.class);
                i.putExtra("state", isNow);
                startActivityForResult(i, SUB_ACTIVITY_REQUEST_CODE);
            }
        });



                return v;
    }
}
