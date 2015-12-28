package com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.quakewatch.ekos.quakewatchaustria.R;
import com.quakewatch.ekos.quakewatchaustria.SubACtivities.ImageFullScreen;
import com.quakewatch.ekos.quakewatchaustria.SubACtivities.SubActivity_Phase2;

/**
 * Created by Okan on 05.11.2015.
 * Usage: The custom adapter for the listView which contains the cartoons
 */
public class CustomIntensity extends ArrayAdapter<String>{

    //Sting array --> references to comics
    public static String[] bilder = {"@drawable/schwach",
            "@drawable/deutlich",
            "@drawable/startk",
            "@drawable/leichte",
            "@drawable/gebaeudenschaden",
            "@drawable/schwere"};

    /**
     * Constructor
     * @param context
     * @param text
     */
    public CustomIntensity(Context context, String[] text) {
        super(context, R.layout.customintensity_row, text);
    }

    /**
     * Inflating the Layout and setting its properties
     * After setting the custom properties the inflated View will be returned
     * to the calling class
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    boolean isImageFitToScreen;
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater cartoon = LayoutInflater.from(getContext());
        View customView = cartoon.inflate(R.layout.customintensity_row, parent, false);

        String test = getItem(position);
        Log.d("Okiskan",""+test);
        TextView textAnzeige = (TextView) customView.findViewById(R.id.text);
        final ImageView image = (ImageView) customView.findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Seas",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(v.getContext(), ImageFullScreen.class);
                i.putExtra("pos",position);
                v.getContext().startActivity(i);
            }
        });

        int imageRes = getContext().getResources().getIdentifier(bilder[position],null,getContext().getPackageName());
        Drawable res = getContext().getResources().getDrawable(imageRes);

        textAnzeige.setText(test);
        image.setImageDrawable(res);

        return customView;
    }
}
