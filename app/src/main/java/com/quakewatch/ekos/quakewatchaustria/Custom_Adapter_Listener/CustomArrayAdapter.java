package com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.quakewatch.ekos.quakewatchaustria.R;

import java.util.ArrayList;

/**
 * Created by pkogler on 22.10.2015.
 */
public class CustomArrayAdapter extends ArrayAdapter {

    public CustomArrayAdapter(Context context, ArrayList<String> resource) {
        super(context, R.layout.customrow,resource);
        // this.strich=1;
    }

    @Override
     public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = LayoutInflater.from(getContext());

        if (convertView == null)
        {
            // Gelöscht!!! Log.d("Seas", "Seas");dd asdasd
            convertView = inflater.inflate(R.layout.customrow, null);
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.listText);
            holder.icon = (ImageView) convertView.findViewById(R.id.imagebild);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.text.setTextColor(Color.BLACK);
        holder.icon.setImageResource(R.drawable.strich1);
        String temp = (String) getItem(position);
        holder.text.setText(temp);
        if ((temp.substring(0,2).equals("1.")) || (temp.substring(0,2).equals("2.")) || (temp.substring(0,2).equals("3.")))  {
            holder.text.setTextColor(Color.rgb(40, 130, 0));
            holder.icon.setImageResource(R.drawable.strich1);
        } else if ((temp.substring(0,2).equals("4.")) || (temp.substring(0,2).equals("5.")) || (temp.substring(0,2).equals("6."))) {
            holder.text.setTextColor(Color.rgb(206, 204, 0));
            holder.icon.setImageResource(R.drawable.strich2);
        } if ((temp.substring(0,2).equals("7.")) || (temp.substring(0,2).equals("8.")) || (temp.substring(0,2).equals("9."))) {
            holder.text.setTextColor(Color.rgb(255, 156, 0));
            holder.icon.setImageResource(R.drawable.strich3);
        } if ((temp.substring(0,3).equals("10.")) || (temp.substring(0,3).equals("11.")) || (temp.substring(0,3).equals("12."))) {
            holder.text.setTextColor(Color.rgb(175,0,0));
            holder.icon.setImageResource(R.drawable.strich4);
        }

        return convertView;
    }
    static class ViewHolder {
        TextView text;
        ImageView icon;
    }
}

