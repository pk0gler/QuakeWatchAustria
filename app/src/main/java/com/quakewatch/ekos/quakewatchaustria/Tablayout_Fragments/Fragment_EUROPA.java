package com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener.CustomArrayAdapter;
import com.quakewatch.ekos.quakewatchaustria.MainActivity;
import com.quakewatch.ekos.quakewatchaustria.R;
import com.quakewatch.ekos.quakewatchaustria.SubACtivities.SubActivity_DetailAnsicht;

import java.util.ArrayList;

/**
 * Created by pkogler on 22.10.2015.
 */
public class Fragment_EUROPA extends Fragment {
    protected static final int SUB_ACTIVITY_REQUEST_CODE = 100;
    ListView listView;
    View v;
    private float mActionBarHeight;
    private ActionBar mActionBar;
    String magStaerke;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         v =inflater.inflate(R.layout.list_layout_eu,container,false);
        /*
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getContext());
        this.magStaerke = SP.getString("magType", "1").charAt(0)+"";
        Log.d("Magni",Integer.parseInt(magStaerke)+"");

        //return v;
        listView = (ListView) v.findViewById(R.id.listEu);
        ArrayList<String> values = new ArrayList<String>();
        for (int i=0; i<20; i++) {
            int zahl = (int)(Math.random() * ((9 - Integer.parseInt(magStaerke)) + 1) + Integer.parseInt(magStaerke));
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
                        i.putExtra("bebenData", wert);
                        startActivityForResult(i, SUB_ACTIVITY_REQUEST_CODE);
                    }
                }
        );

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "Beben auf map anzeigen", Toast.LENGTH_LONG).show();
                return true;
            }
        });


        final TypedArray styledAttributes = getContext().getTheme().obtainStyledAttributes(
                new int[]{android.R.attr.actionBarSize});
        mActionBarHeight = styledAttributes.getDimension(0, 0);
        mActionBar = ((MainActivity) getActivity()).getSupportActionBar();
        listView.setOnScrollListener(new MyOnScrollListner(mActionBar));*/
        this.createContent();
        return v;
    }
    private boolean jetzt = true;
    @Override
    public void onResume() {
        super.onResume();
        /*ListView listView = (ListView)v.findViewById(R.id.listEu);
        if (jetzt) {
            jetzt = false;
            listView.setDividerHeight(7);
        } else {
            jetzt = true;
            listView.setDividerHeight(1);
        }*/
        //this.createContent();
    }

    private void createContent() {
        listView = null;
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getContext());
        this.magStaerke = SP.getString("magType", "1").charAt(0)+"";
        Log.d("Magni",Integer.parseInt(magStaerke)+"");

        //return v;
        listView = (ListView) v.findViewById(R.id.listEu);
        ArrayList<Erdbeben> values = new ArrayList<>();
        for (int i=0; i<20; i++) {
            int zahl = (int)(Math.random() * ((9 - Integer.parseInt(magStaerke)) + 1) + Integer.parseInt(magStaerke));
            int zahl2 = (int)((Math.random()) * 9 + 0);
            values.add(new Erdbeben(Double.parseDouble(zahl+"."+zahl2),"Frankreich, Paris", "2015-10-29T23:09:50.0Z", 23.4));
        }
        ArrayAdapter<String> adapter = new CustomArrayAdapter(getContext(), values);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Erdbeben temp = (Erdbeben) parent.getItemAtPosition(position);
                        Toast.makeText(getContext(), temp.getMag()+"", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getContext(), SubActivity_DetailAnsicht.class);
                        i.putExtra("bebenData", temp);
                        i.putExtra("isAt", false);
                        startActivityForResult(i, SUB_ACTIVITY_REQUEST_CODE);
                    }
                }
        );

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getContext(), "Beben auf map anzeigen", Toast.LENGTH_LONG).show();
                ((MainActivity)getActivity()).setPager(3);
                return true;
            }
        });


        final TypedArray styledAttributes = getContext().getTheme().obtainStyledAttributes(
                new int[]{android.R.attr.actionBarSize});
        mActionBarHeight = styledAttributes.getDimension(0, 0);
        mActionBar = ((MainActivity) getActivity()).getSupportActionBar();
        //listView.setOnScrollListener(new MyOnScrollListner(mActionBar));
    }
}
