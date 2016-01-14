package com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.quakewatch.ekos.quakewatchaustria.R;

/**
 * Created by pkogler on 09.01.2016.
 */
public class DetailStringAdapter extends ArrayAdapter<DetailStrings> {
    Context context;
    int layoutRes;
    DetailStrings data[] = null;

    public DetailStringAdapter(Context context, int layoutRes, DetailStrings[] data) {
        super(context, layoutRes, data);
        this.layoutRes = layoutRes;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        DetailStringsHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutRes, parent, false);

            holder = new DetailStringsHolder();
            holder.icon = (ImageView) row.findViewById(R.id.icon);
            holder.title = (TextView) row.findViewById(R.id.title);
            holder.subtitle = (TextView) row.findViewById(R.id.subtitle);
            holder.icon.setColorFilter(Color.parseColor("#666666"));
            row.setTag(holder);
        } else {
            holder = (DetailStringsHolder) row.getTag();
        }
        DetailStrings strings = data[position];
        holder.title.setText(strings.title);
        holder.subtitle.setText(strings.subtitle);
        holder.icon.setImageResource(strings.icon);

        return row;
    }

    private class DetailStringsHolder {
        ImageView icon;
        TextView title;
        TextView subtitle;
    }
}
