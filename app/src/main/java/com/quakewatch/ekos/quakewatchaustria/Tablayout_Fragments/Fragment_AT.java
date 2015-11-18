package com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener.CustomArrayAdapter;
import com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener.ViewPagerAdapter;
import com.quakewatch.ekos.quakewatchaustria.MainActivity;
import com.quakewatch.ekos.quakewatchaustria.R;
import com.quakewatch.ekos.quakewatchaustria.SubACtivities.SubActivity_BebenEintragenStart;
import com.quakewatch.ekos.quakewatchaustria.SubACtivities.SubActivity_DetailAnsicht;
import com.quakewatch.ekos.quakewatchaustria.SubACtivities.SubActivity_DiesesBebenEintragen;
import com.software.shell.fab.ActionButton;

import java.util.ArrayList;

/**
 * Created by pkogler on 22.10.2015.
 */
public class Fragment_AT extends Fragment {

    protected static final int SUB_ACTIVITY_REQUEST_CODE = 100;
    private ImageButton FAB;
    boolean show = false;
    private ActionButton actionButtonNow;
    private ActionButton actionButtonMain;
    private ActionButton actionButtonAndere;

    ListView listView;

    private View v;
    private TextView tJetzt;
    private TextView tAndere;

    private String magStaerke;

    private float mActionBarHeight;
    private ActionBar mActionBar;

    private ViewPagerAdapter pager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.list_layout_at,container,false);
        actionButtonMain = (ActionButton) v.findViewById(R.id.action_button_main);
        actionButtonMain.setImageResource(R.drawable.fab_x_but_rotate);
        actionButtonMain.setButtonColor(R.color.fab_material_blue_500);

        actionButtonNow = (ActionButton) v.findViewById(R.id.action_button_jetzt);
        actionButtonAndere = (ActionButton) v.findViewById(R.id.action_button_andere);

        actionButtonNow.setType(ActionButton.Type.MINI);
        actionButtonNow.setImageResource(R.drawable.fab_plus_icon);

        actionButtonAndere.setType(ActionButton.Type.MINI);
        actionButtonAndere.setImageResource(R.drawable.fab_plus_icon);

        actionButtonAndere.hide();
        actionButtonNow.hide();

        actionButtonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (show) {
                    //v.setAlpha(1f);
                    actionButtonMain.setImageResource(R.drawable.fab_x_but_rotate);
                    listView.setEnabled(true);
                    tJetzt.setVisibility(View.GONE);
                    tAndere.setVisibility(View.GONE);
                    listView.setAlpha(1f);
                    listView.setBackgroundColor(Color.WHITE);
                    actionButtonAndere.hide();
                    actionButtonNow.hide();
                    show = false;
                } else {
                    listView.setEnabled(false);
                    tJetzt.setVisibility(View.VISIBLE);
                    tAndere.setVisibility(View.VISIBLE);
                    tAndere.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.fab_fade_in));
                    tJetzt.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.fab_fade_in));

                    listView.setAlpha(0.17f);
                    actionButtonMain.setAlpha(1f);
                    listView.setBackgroundColor(Color.DKGRAY);
                    actionButtonMain.setImageResource(R.drawable.fab_x_but);
                    actionButtonNow.setHideAnimation(ActionButton.Animations.ROLL_TO_RIGHT);
                    actionButtonAndere.setHideAnimation(ActionButton.Animations.ROLL_TO_RIGHT);
                    actionButtonAndere.show();
                    actionButtonNow.show();
                    show = true;
                }
            }


        });


        actionButtonNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setEnabled(true);
                tJetzt.setVisibility(View.GONE);
                tAndere.setVisibility(View.GONE);
                listView.setAlpha(1f);
                listView.setBackgroundColor(Color.WHITE);
                actionButtonAndere.hide();
                actionButtonNow.hide();
                show = false;

                boolean isNow = true;
                //Toast.makeText(getContext(), wert, Toast.LENGTH_LONG).show();
                Intent i = new Intent(getContext(), SubActivity_BebenEintragenStart.class);
                i.putExtra("state", isNow);
                startActivityForResult(i, SUB_ACTIVITY_REQUEST_CODE);
            }
        });


        actionButtonAndere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setEnabled(true);
                tJetzt.setVisibility(View.GONE);
                tAndere.setVisibility(View.GONE);
                listView.setAlpha(1f);
                listView.setBackgroundColor(Color.WHITE);
                actionButtonAndere.hide();
                actionButtonNow.hide();
                show = false;

                boolean isNow = false;
                //Toast.makeText(getContext(), wert, Toast.LENGTH_LONG).show();
                Intent i = new Intent(getContext(), SubActivity_BebenEintragenStart.class);
                i.putExtra("state", isNow);
                startActivityForResult(i, SUB_ACTIVITY_REQUEST_CODE);
            }
        });


        final TypedArray styledAttributes = getContext().getTheme().obtainStyledAttributes(
                new int[]{android.R.attr.actionBarSize});
        mActionBarHeight = styledAttributes.getDimension(0, 0);
        mActionBar = ((MainActivity) getActivity()).getSupportActionBar();
        this.createConetent();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        //getActivity().getIntent().getExtras().get("save");
        //this.createConetent();
        actionButtonMain.setImageResource(R.drawable.fab_x_but_rotate);

    }

    public void createConetent() {
        listView = null;
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getContext());
        this.magStaerke = SP.getString("magType", "1").charAt(0)+"";
        Log.d("Magni", Integer.parseInt(magStaerke) + "");
        tJetzt = (TextView) v.findViewById(R.id.tJetzt);
        tJetzt.setVisibility(View.GONE);
        tAndere = (TextView) v.findViewById(R.id.tAndere);
        tAndere.setVisibility(View.GONE);
        //tJetzt.setBackground("#FFFFFF");
        //return v;
        listView = (ListView) v.findViewById(R.id.listAt);
        ArrayList<Erdbeben> values = new ArrayList<>();
            for (int i = 1; i < 10; i++) {
                int z1 = i;
                for (int j = 0; j < 10; j++) {
                    int z2 = j;
                    if (Double.parseDouble(z1+"."+z2) == 12.1) break;
                    values.add(new Erdbeben(Double.parseDouble(z1+"."+z2),"Oestereich, Wien", "2015-10-29T23:09:50.0Z", 3.4));
                }
            }

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Erdbeben temp = (Erdbeben) parent.getItemAtPosition(position);
                boolean isNow = true;
                //Toast.makeText(getContext(), wert, Toast.LENGTH_LONG).show();
                Intent i = new Intent(getContext(), SubActivity_BebenEintragenStart.class);
                i.putExtra("state", isNow);
                i.putExtra("bebenData", temp);
                startActivityForResult(i, SUB_ACTIVITY_REQUEST_CODE);
                return true;
            }
        });
        ArrayAdapter<String> adapter = new CustomArrayAdapter(getContext(), values);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Erdbeben temp = (Erdbeben) parent.getItemAtPosition(position);
                        //Toast.makeText(getContext(), temp.getMag()+"", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getContext(), SubActivity_DetailAnsicht.class);
                        i.putExtra("bebenData", temp);
                        i.putExtra("isAt", true);
                        startActivityForResult(i, SUB_ACTIVITY_REQUEST_CODE);
                    }
                }
        );
        //listView.setOnScrollListener(new MyOnScrollListner(mActionBar));
        actionButtonMain.setImageResource(R.drawable.fab_x_but_rotate);
    }
}
