package com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener.DetailStringAdapter;
import com.quakewatch.ekos.quakewatchaustria.R;
import com.quakewatch.ekos.quakewatchaustria.SubACtivities.DetailStrings;
import com.quakewatch.ekos.quakewatchaustria.SubACtivities.SubActivity_DetailAnsicht;

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
        date = (TextView) rootView.findViewById(R.id.textDate);
        region = (TextView) rootView.findViewById(R.id.textViewLocation);
        date.setText(beben.getDate());
        region.setText(beben.getRegion());
        ArrayList<String> values2 = new ArrayList<>();
        String[] values = new String[]{"Region: \t" + beben.getRegion(),
                "Magnitude: \t" + beben.getMag(),
                "Date: \t" + beben.getDate()
        };
        String[] values3 = new String[]{"Austria",
                "5.0",
                "2016"
        };
        for (int i = 0; i < values.length; i++) {
            values2.add(i, values[i]);
        }
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, values);
        DetailStrings strings[] = new DetailStrings[]
                {
                        new DetailStrings(R.drawable.ic_pin_drop_black_24dp, "Region", beben.getRegion()),
                        new DetailStrings(R.drawable.ic_my_location_black_24dp, "Location", beben.lat + ", " + beben.lon),
                        new DetailStrings(R.drawable.ic_line_style_black_24dp, "Depth", "" + beben.getDepth()),
                        new DetailStrings(R.drawable.ic_fingerprint_black_24dp, "ID", "" + beben.getId()),
                        new DetailStrings(R.drawable.ic_location_city_black_24dp, "Nearby Cities", beben.placesString)
                };
        DetailStringAdapter adapter = new DetailStringAdapter(getContext(),
                R.layout.deatiail_string, strings);
        listView.setAdapter(adapter);
        // Do something else



        double mag = beben.getMag();
        TextView magT = (TextView)rootView.findViewById(R.id.textMag);
        magT.setText(beben.getMag()+"");
        RelativeLayout img = (RelativeLayout) rootView.findViewById(R.id.header);


        if ((mag >= 0) && (mag <= 1.49)) {
            img.setBackgroundColor(Color.parseColor(SubActivity_DetailAnsicht.colorCodes[0]));
        } else if ((mag >= 1.50) && (mag <= 1.99)) {
            img.setBackgroundColor(Color.parseColor(SubActivity_DetailAnsicht.colorCodes[1]));
        } else if ((mag >= 2.0) && (mag <= 2.49)) {
            img.setBackgroundColor(Color.parseColor(SubActivity_DetailAnsicht.colorCodes[2]));
        }

         /*NEXT COLOR*/
        else if ((mag >= 2.50) && (mag <= 2.99)) {
            img.setBackgroundColor(Color.parseColor(SubActivity_DetailAnsicht.colorCodes[2]));
        }
        if ((mag >= 3.0) && (mag <= 3.49)) {
            img.setBackgroundColor(Color.parseColor(SubActivity_DetailAnsicht.colorCodes[4]));
        }
        if ((mag >= 3.50) && (mag <= 3.99)) {
            img.setBackgroundColor(Color.parseColor(SubActivity_DetailAnsicht.colorCodes[5]));
        }

    /*NEXT COLOR*/

        else if ((mag >= 4.0) && (mag <= 4.49))

        {
            img.setBackgroundColor(Color.parseColor(SubActivity_DetailAnsicht.colorCodes[6]));
        }

        if ((mag >= 4.50) && (mag <= 4.99))

        {
            img.setBackgroundColor(Color.parseColor(SubActivity_DetailAnsicht.colorCodes[7]));
        }

        if ((mag >= 5.0) && (mag <= 5.49))

        {
            img.setBackgroundColor(Color.parseColor(SubActivity_DetailAnsicht.colorCodes[8]));
        }

/*NEXT COLOR*/
        else if ((mag >= 5.50) && (mag <= 5.99)) {
            img.setBackgroundColor(Color.parseColor(SubActivity_DetailAnsicht.colorCodes[9]));
        }
        if ((mag >= 6.0) && (mag <= 6.49)) {
            img.setBackgroundColor(Color.parseColor(SubActivity_DetailAnsicht.colorCodes[10]));
        }
        if ((mag >= 6.50) && (mag <= 6.99)) {
            img.setBackgroundColor(Color.parseColor(SubActivity_DetailAnsicht.colorCodes[11]));
        }

        /*NEXT COLOR*/
        else if ((mag >= 7.0) && (mag <= 7.99)) {
            img.setBackgroundColor(Color.parseColor(SubActivity_DetailAnsicht.colorCodes[12]));
        }
        if ((mag >= 8.0) && (mag <= 8.99)) {
            img.setBackgroundColor(Color.parseColor(SubActivity_DetailAnsicht.colorCodes[13]));
        }
        /*NEXT COLOR*/
        else if ((mag >= 9.0) && (mag <= 12)) {
            img.setBackgroundColor(Color.parseColor(SubActivity_DetailAnsicht.colorCodes[14]));
        }
        return rootView;
    }
}