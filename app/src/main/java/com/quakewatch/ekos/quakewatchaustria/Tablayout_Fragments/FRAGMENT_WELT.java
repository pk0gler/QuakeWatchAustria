package com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
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
import com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener.MyOnScrollListner;
import com.quakewatch.ekos.quakewatchaustria.MainActivity;
import com.quakewatch.ekos.quakewatchaustria.R;
import com.quakewatch.ekos.quakewatchaustria.SubACtivities.SubActivity_DetailAnsicht;

import java.util.ArrayList;

/**
 * Created by pkogler on 22.10.2015.
 */
public class FRAGMENT_WELT extends Fragment {
    protected static final int SUB_ACTIVITY_REQUEST_CODE = 100;

    private float mActionBarHeight;
    private ActionBar mActionBar;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.list_layout_world,container,false);
        //return v;
        ListView listView = (ListView) v.findViewById(R.id.listWorld);
        ArrayList<String> values = new ArrayList<String>();
        for (int i=0; i<20; i++) {
            Log.d("Bin drin", "drin");
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

        listView.setOnScrollListener(new MyOnScrollListner(mActionBar));

        return v;
    }
}
