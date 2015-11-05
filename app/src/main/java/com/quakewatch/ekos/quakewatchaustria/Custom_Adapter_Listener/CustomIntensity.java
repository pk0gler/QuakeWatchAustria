package com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.quakewatch.ekos.quakewatchaustria.R;

/**
 * Created by Okan on 05.11.2015.
 */
public class CustomIntensity extends ArrayAdapter<String>{

    public CustomIntensity(Context context, String[] text) {
        super(context, R.layout.customintensity_row, text);
    }

    String[] bilder = {"@drawable/schwach",
            "@drawable/deutlich",
            "@drawable/startk",
            "@drawable/leichte",
            "@drawable/gebaeudenschaden",
            "@drawable/schwere"};
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater cartoon = LayoutInflater.from(getContext());
        View customView = cartoon.inflate(R.layout.customintensity_row, parent, false);

        String test = getItem(position);
        TextView textAnzeige = (TextView) customView.findViewById(R.id.text);
        ImageView image = (ImageView) customView.findViewById(R.id.image);

        int imageRes = getContext().getResources().getIdentifier(bilder[position],null,getContext().getPackageName());
        Drawable res = getContext().getResources().getDrawable(imageRes);

        textAnzeige.setText(test);
        image.setImageDrawable(res);

        return customView;
    }
}
