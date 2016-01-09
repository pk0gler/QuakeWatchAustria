package com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.quakewatch.ekos.quakewatchaustria.R;

import java.util.ArrayList;

/**
 * Created by pkogler on 08.01.2016.
 */
public class Map_Fragment extends DialogFragment {
    private Erdbeben beben;
    private ListView listView;
    private FloatingActionButton fab;
    private TextView region;
    private TextView date;

    public Map_Fragment(Erdbeben temp) {
        this.beben = temp;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialogfragment, container,
                false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        listView = (ListView) rootView.findViewById(R.id.list);
        date = (TextView) rootView.findViewById(R.id.textView4);
        region = (TextView) rootView.findViewById(R.id.textView3);
        date.setText(beben.getDate());
        region.setText(beben.getRegion());
        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        ArrayList<String> values2 = new ArrayList<>();
        String[] values = new String[]{"Region: \t"+beben.getRegion(),
                "Magnitude: \t"+beben.getMag(),
                "Date: \t"+beben.getDate()
        };
        String[] values3 = new String[]{"Austria",
                "5.0",
                "2016"
        };
        for (int i=0; i<values.length; i++) {
            values2.add(i,values[i]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);
        // Do something else
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();

            }

        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),
                        "Bin da", Toast.LENGTH_LONG)
                        .show();
            }
        });
        return rootView;
    }
}