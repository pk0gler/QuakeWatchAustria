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
import com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments.Erdbeben;

import java.util.ArrayList;

/**
 * Created by pkogler on 22.10.2015.
 * Usage:   Adapter for the ListViews to process data
 *          sets the color Codes for depending on magnitude
 */
public class CustomArrayAdapter extends ArrayAdapter {

    //colorCodes depending on Magnitude
    public final static String[] colorCodes2 = {
            //Green
            "#3EA739","#338B2E","#296F25",
            //Yellow
            "#FBFE00","#D5D800","#B1B300",
            //Blue
            "#39508A","#2F4273","#25355C",
            //Orange
            "#FFA415","#FF9C00","#E98F00",
            //Purple
            "#D91283","#BB006A",
            //Red
            "#CA0000"
    };
    public final static String[] colorCodes = {
            //Green
            "#66BB6A","#4CAF50","#43A047",
            //Yellow
            "#FFEE58","#FFEB3B","#FDD835",
            //Orange
            "#FFA726","#FF9800","#FB8C00",
            //Blue
            "#5C6BC0","#3F51B5","#3949AB",
            //Purple
            "#673AB7","#5E35B1",
            //Red
            "#C62828"
    };

    /**
     * Construktor
     * @param context   --> context
     * @param resource  --> ressorce hand over for processing
     */
    public CustomArrayAdapter(Context context, ArrayList<Erdbeben> resource) {
        super(context, R.layout.customrow,resource);
        // this.strich=1;
    }

    /**
     * Override Method
     * Called When: This Method is called when the ViewPagerAdapter
     *              generates the Fragments
     *              And the single Erdbeben Objects are created and hand over as ressource
     * Usage:       Calculates necessary Information about List Item
     * @param position      --> positon in ListView
     * @param convertView   --> the View the ListView belongs to
     * @param parent        --> the parent ViewGroup
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Creates The ViewHolder
        /**
         * The Viewholder is used to provide much better
         * performance
         * Basically it holds the different items and positions til the next call
         */
        ViewHolder holder;
        //Infalte the Layout
        LayoutInflater inflater = LayoutInflater.from(getContext());

        /**
         * If the View is empty e.g the first call
         * We have to set the ViewHolder elements
         * The ViewHolder Elements need to be initialized
         * and defined
         */
        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.customrow, null);
            holder = new ViewHolder();
            holder.textMag = (TextView) convertView.findViewById(R.id.listText);
            holder.region = (TextView) convertView.findViewById(R.id.textViewLocation);
            holder.time = (TextView) convertView.findViewById(R.id.textViewTime);
            holder.date = (TextView) convertView.findViewById(R.id.textViewDatum);
            holder.icon = (ImageView) convertView.findViewById(R.id.imagebild);
            convertView.setTag(holder);
        }
        else
        /**
         * If its not the First Call
         * The old ViewHolder object has to be set
         * For better performance
         */
        {
            holder = (ViewHolder) convertView.getTag();
        }
        /**
         * Set the holder properties and elements
         */
        holder.textMag.setTextColor(Color.BLACK);
        holder.icon.setImageResource(R.drawable.strichvorlage);
        Erdbeben temp = (Erdbeben) getItem(position);
        double mag = temp.getMag();
        holder.textMag.setText(mag+"");
        holder.region.setText(temp.getRegion());
        holder.date.setText(temp.getDate());
        holder.time.setText(temp.getTime());
        /**
         ----------------------------------------------------
         ---            COLOR CODES COLOR CODES           ---
         ----------------------------------------------------
         1 		- 		1.4     -->	    3EA739
         1.5 	- 		1.9	    -->     338B2E		| Grün
         2 		- 		2.4	    -->	    296F25
         ----------------------------------------------------
         2.5	- 		2.9	    -->	    FBFE00
         3		- 		3.4	    -->	    D5D800		| Gelb
         3.5	-		3.9	    -->	    B1B300
         ----------------------------------------------------
         4		- 		4.4	    -->	    39508A
         4.5	- 		4.9	    -->	    2F4273		| Blau
         5		- 		5.4	    -->	    25355C
         ----------------------------------------------------
         5.5	- 		5.9	    -->	    FFA415
         6		- 		6.4	    -->	    FF9C00		| Orange
         6.5	- 		6.9	    -->	    E98F00
         ----------------------------------------------------
         7		- 		7.9	    -->	    D91283		| Lila
         8		- 		8.9	    -->	    BB006A
         ----------------------------------------------------
         9		- 		12		-->	    CA0000
         10		- 		12		-->	    CA0000		| Rot
         11		- 		12		-->	    CA0000
         12		- 		12		-->	    CA0000
         ----------------------------------------------------
         ----------------------------------------------------
        /**
         * This if divides by the magnitude which color to use
         */
        if ((mag >= 0) && (mag <= 2.4)) {
            if ((mag >= 1) && (mag <= 1.49)) {
                holder.textMag.setTextColor(Color.parseColor(colorCodes[0]));
                holder.icon.setBackgroundColor(Color.parseColor(colorCodes[0]));
            }
            if ((mag >= 1.50) && (mag <= 1.99)) {
                holder.textMag.setTextColor(Color.parseColor(colorCodes[1]));
                holder.icon.setBackgroundColor(Color.parseColor(colorCodes[1]));
            }
            if ((mag >= 2.0) && (mag <= 2.49)) {
                holder.textMag.setTextColor(Color.parseColor(colorCodes[2]));
                holder.icon.setBackgroundColor(Color.parseColor(colorCodes[2]));
            }

        } /*NEXT COLOR*/else if ((mag >= 2.50) && (mag <= 3.99)) {
            if ((mag >= 2.50) && (mag <= 2.99)) {
                holder.textMag.setTextColor(Color.parseColor(colorCodes[2]));
                holder.icon.setBackgroundColor(Color.parseColor(colorCodes[2]));
            }
            if ((mag >= 3.0) && (mag <= 3.49)) {
                holder.textMag.setTextColor(Color.parseColor(colorCodes[4]));
                holder.icon.setBackgroundColor(Color.parseColor(colorCodes[4]));
            }
            if ((mag >= 3.5) && (mag <= 3.99)) {
                holder.textMag.setTextColor(Color.parseColor(colorCodes[5]));
                holder.icon.setBackgroundColor(Color.parseColor(colorCodes[5]));
            }

        }/*NEXT COLOR*/else if ((mag >= 4) && (mag <= 5.49)) {
            if ((mag >= 4) && (mag <= 4.49)) {
                holder.textMag.setTextColor(Color.parseColor(colorCodes[6]));
                holder.icon.setBackgroundColor(Color.parseColor(colorCodes[6]));
            }
            if ((mag >= 4.5) && (mag <= 4.99)) {
                holder.textMag.setTextColor(Color.parseColor(colorCodes[7]));
                holder.icon.setBackgroundColor(Color.parseColor(colorCodes[7]));
            }
            if ((mag >= 5) && (mag <= 5.49)) {
                holder.textMag.setTextColor(Color.parseColor(colorCodes[8]));
                holder.icon.setBackgroundColor(Color.parseColor(colorCodes[8]));
            }

        }/*NEXT COLOR*/else if ((mag >= 5.5) && (mag <= 6.99)) {
            if ((mag >= 5.5) && (mag <= 5.99)) {
                holder.textMag.setTextColor(Color.parseColor(colorCodes[9]));
                holder.icon.setBackgroundColor(Color.parseColor(colorCodes[9]));
            }
            if ((mag >= 6) && (mag <= 6.49)) {
                holder.textMag.setTextColor(Color.parseColor(colorCodes[10]));
                holder.icon.setBackgroundColor(Color.parseColor(colorCodes[10]));
            }
            if ((mag >= 6.5) && (mag <= 6.99)) {
                holder.textMag.setTextColor(Color.parseColor(colorCodes[11]));
                holder.icon.setBackgroundColor(Color.parseColor(colorCodes[11]));
            }

        }/*NEXT COLOR*/else if ((mag >= 7) && (mag <= 8.99)) {
            if ((mag >= 7) && (mag <= 7.99)) {
                holder.textMag.setTextColor(Color.parseColor(colorCodes[12]));
                holder.icon.setBackgroundColor(Color.parseColor(colorCodes[12]));
            }
            if ((mag >= 8) && (mag <= 8.99)) {
                holder.textMag.setTextColor(Color.parseColor(colorCodes[14]));
                holder.icon.setBackgroundColor(Color.parseColor(colorCodes[14]));
            }
        }/*NEXT COLOR*/else if ((mag >= 9) && (mag <= 12)) {
            holder.textMag.setTextColor(Color.parseColor(colorCodes[14]));
            holder.icon.setBackgroundColor(Color.parseColor(colorCodes[14]));
        }

        return convertView;
    }

    /**
     * View Holder class
     * inner class because it is simple and easier to use
     * in this particular case
     */
    static class ViewHolder {
        TextView textMag;
        TextView region;
        TextView time;
        TextView date;
        ImageView icon;
    }
}

