package com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.quakewatch.ekos.quakewatchaustria.R;
import com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments.Erdbeben;

import java.util.ArrayList;

/**
 * Created by pkogler on 09.01.2016.
 */
public class Mapadapter extends ArrayAdapter<String> {
    private Erdbeben beben;

    public Mapadapter(Context context, ArrayList<String> values) {
        super(context, R.layout.custom_detail_map);
        this.beben = beben;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        String temp = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_detail_map, parent, false);
        }
        TextView view1 = (TextView) convertView.findViewById(R.id.textView5);
        TextView view2 = (TextView) convertView.findViewById(R.id.textView6);
        return convertView;
    }
}

