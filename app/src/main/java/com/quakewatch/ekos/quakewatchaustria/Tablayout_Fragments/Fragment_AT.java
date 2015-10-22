package com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener.CustomArrayAdapter;
import com.quakewatch.ekos.quakewatchaustria.R;
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

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.list_layout_at,container,false);
        //return v;
        ListView listView = (ListView) v.findViewById(R.id.listAt);
        ArrayList<String> values = new ArrayList<String>();
        for (int i=0; i<100; i++) {
            int zahl = (int)((Math.random()) * 9 + 1);
            int zahl2 = (int)((Math.random()) * 9 + 0);
            values.add(i, zahl+"."+zahl2);
        }
        ArrayAdapter<String> adapter = new CustomArrayAdapter(getContext(), values);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String wert = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(getContext(), wert, Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getContext(), SubActivity_DetailAnsicht.class);
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
            public void onClick(View v) {
                if (show) {
                    actionButtonAndere.hide();
                    actionButtonNow.hide();
                    show = false;
                } else {
                    actionButtonNow.setHideAnimation(ActionButton.Animations.ROLL_TO_RIGHT);
                    actionButtonAndere.setHideAnimation(ActionButton.Animations.ROLL_TO_RIGHT);
                    actionButtonAndere.show();
                    actionButtonNow.show();
                    show = true;
                }
            }
        });


        return v;
    }
}
